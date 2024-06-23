package cmedina.cirion.tcpipstack.DashInfo;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin-dash")
@RequiredArgsConstructor
public class DashController {

    @PostMapping(value = "/actions")
    public String Welcome(){
        return "Welcome";
    }
}
