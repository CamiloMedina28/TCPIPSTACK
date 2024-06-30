package cmedina.cirion.tcpipstack.DevOps.TeamsMagament;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/devops-teams")
@RequiredArgsConstructor
public class TeamsController {

    @PostMapping(value = "hola")
    public String hola() {
        return "Hola";
    }

}
