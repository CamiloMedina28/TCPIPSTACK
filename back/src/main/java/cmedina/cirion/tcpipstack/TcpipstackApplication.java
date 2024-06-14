package cmedina.cirion.tcpipstack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class TcpipstackApplication {

	public static void main(String[] args) {
		SpringApplication.run(TcpipstackApplication.class, args);
	}

	@GetMapping("/")
	public String apiIndex(){
		return "Hello World!";
	}
}

