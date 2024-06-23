package cmedina.cirion.tcpipstack.Auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data // getters y setters
@Builder
@AllArgsConstructor // Constructores
@RequiredArgsConstructor
public class LoginRequest {
    String Username;
    String Password;
}
