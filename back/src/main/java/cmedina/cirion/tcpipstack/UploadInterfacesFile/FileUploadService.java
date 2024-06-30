package cmedina.cirion.tcpipstack.UploadInterfacesFile;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class FileUploadService {

    public Map<String, Object> getData(MultipartFile file){
        Map<String, Object> data = new HashMap<String, Object>();
        data = DataReader(file, data);
        return data;
    }

    private Map DataReader(MultipartFile file, Map data){
        try {
            BufferedReader DataReader = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8));
            String line;
            String InterfaceName = "";
            Boolean InterfaceDescriptionSearcher = false;
            String InterfaceDescription = "";
            while ((line = DataReader.readLine()) != null) {
                if (InterfaceDescriptionSearcher){
                    if(!CheckDescriptionContent(line)){
                        InterfaceDescription += line;
//                        InterfaceDescription += "---";
                    }else{
                        InterfaceDescriptionSearcher = false;
                        data.put(InterfaceName, InterfaceDescription);
                        InterfaceDescription = "";
                    }
                }
                if (SearchInfoInterfacesName(line) != null && !InterfaceDescriptionSearcher){
                    InterfaceName = line;
                    InterfaceDescriptionSearcher = true;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return data;
    }

    private String SearchInfoInterfacesName(String line){
        String RegexForInterface = "^interface\\b.*";

        Pattern RegexPatternForInterface = Pattern.compile(RegexForInterface);

        Matcher MatcherForInterface = RegexPatternForInterface.matcher(line);

        if (MatcherForInterface.find()) {
            return line;
        }
        
        return null;
    }

    private void SearchInfoDescription(String line){
        String RegexForDescription = "^description\\b.*";
        Pattern RegexPatternForDescription = Pattern.compile(RegexForDescription);
        Matcher MatcherForDescription = RegexPatternForDescription.matcher(line);
    }

    private boolean CheckDescriptionContent(String line){
        if (line.contains("!")){
            return true;
        }
        return false;
    }

}
