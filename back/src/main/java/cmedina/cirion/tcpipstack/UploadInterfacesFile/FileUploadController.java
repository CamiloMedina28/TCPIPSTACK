package cmedina.cirion.tcpipstack.UploadInterfacesFile;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/upload-file")
@RequiredArgsConstructor
public class FileUploadController {

    private final FileUploadService fileUploadService;

    @PostMapping("uploader")
    public ResponseEntity<Map<String, String>> InterfacesFile(@RequestParam("file") MultipartFile file){
        Map data = fileUploadService.getData(file);
        return ResponseEntity.status(HttpStatus.OK).body(data);
    }
}
