package cmedina.cirion.tcpipstack.Auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data // getters y setters
@Builder
@AllArgsConstructor // Constructores
@RequiredArgsConstructor
public class RegisterRequest {
    String username;
    String Password;
    String primernombre;
    String segundonombre;
    String primerapellido;
    String segundoapellido;
}
