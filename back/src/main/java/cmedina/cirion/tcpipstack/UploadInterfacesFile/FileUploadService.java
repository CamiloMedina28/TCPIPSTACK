package cmedina.cirion.tcpipstack.UploadInterfacesFile;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class FileUploadService {

    public Map<String, Object> getData(MultipartFile file){
        Map<String, Object> data = new HashMap<String, Object>();
        data = DataReader(file, data);
        System.out.println("\033[31m data = " + data);
        return data;
    }

    private Map DataReader(MultipartFile file, Map data){
        Map<String, Object> InterfaceInformation = new HashMap<>();
        Map<String, Object> ServiceInstanceInformation = new HashMap<>();
        List<Object> ServiceInstances = new ArrayList<>();
        Boolean InformationExtractor = false;
        Boolean ServiceInstanceExtractor = false;
        try{
            BufferedReader DataReader = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8));
            String line;
            String InterfaceName = "";

            while((line = DataReader.readLine()) != null){
                line = line.replace(" ", "·");
                if (InformationExtractor){
                    if(IsEndOfInterface(line)) {
                        InformationExtractor = false;
                        ServiceInstanceExtractor = false;
                        InterfaceInformation.put("ServiceInstance", ServiceInstances);
                        Map<String, Object> InterfaceInformationCopy = new HashMap<>(InterfaceInformation);
                        data.put(InterfaceName, InterfaceInformationCopy);
                    }else if(IsEndOfServiceInstance(line)){
                        System.out.println("here");
                        ServiceInstanceExtractor = false;
                        System.out.println("ServiceInstanceInformation = " + ServiceInstanceInformation);
                        ServiceInstances.add(new HashMap<>(ServiceInstanceInformation));
                        ServiceInstanceInformation.clear();
                    }
                    else if(HasServiceInstance(line)){
                        ServiceInstanceExtractor = true;
                    }else{
                        String[] info = DataExtractor(line);
                        if (info[0] != null && info[1] != null) {

                            InterfaceInformation.put(info[0], info[1]);
                        }
                    }
                }
                if(ServiceInstanceExtractor){
                    String[] info = DataExtractor(line);
                    if (info[0] != null && info[1] != null) {
                        System.out.println("Extracting info = " + info[0] + "\t" + info[1]);
                        ServiceInstanceInformation.put(info[0], info[1]);
                    }
                }
                if (isInterfaceNameInLine(line)){
                    InterfaceName = line;
                    InterfaceInformation.clear();
                    InformationExtractor = true;
                }
            }
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
        return data;
    }

    private boolean isInterfaceNameInLine(String line){
        Pattern InterfacePattern = Pattern.compile("^\\binterface\\b");
        Matcher InterfaceMatcher = InterfacePattern.matcher(line);
        if (InterfaceMatcher.find()){
            return true;
        }
        return false;
    }

    private String[] DataExtractor(String line){
        String[] info = new String[2];
        if (line.contains("no")) {
            info[0] = line.substring(4).replace("·", " ");
            info[1] = "no";
        }else{
            String RegexForParameter = "^(·[a-zA-z]+·[a-zA-Z]+·|·[a-zA-z]+·|·[a-zA-Z]+-[a-zA-Z]+)";
            Pattern pattern = Pattern.compile(RegexForParameter);
            Matcher matcher = pattern.matcher(line);
            if (matcher.find()) {
                info[0] = matcher.group().substring(1).replace("·", " ");
                info[1] = line.substring(info[0].length() + 1, line.length()).replace("·", " ");
            }
        }
        return info;
    }

    private boolean HasServiceInstance(String line){
        if (line.contains("·service·instance")){
            return true;
        }
        return false;
    }

    private boolean IsEndOfInterface(String line){
        String RegexForInterfaceEnd = "·!";
        Pattern pattern = Pattern.compile(RegexForInterfaceEnd);
        Matcher matcher = pattern.matcher(line);
        if (matcher.find()){
            System.out.println("line = " + line);
            return true;
        }
        return false;
    }

    private boolean IsEndOfServiceInstance(String line){
        if(line.contains("·!")){
            return true;
        }
        return false;
    }
}
