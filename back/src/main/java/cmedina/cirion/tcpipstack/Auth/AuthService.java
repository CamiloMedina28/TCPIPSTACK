package cmedina.cirion.tcpipstack.Auth;

import cmedina.cirion.tcpipstack.JWTAuthentication.JWTService;
import cmedina.cirion.tcpipstack.UserData.Role;
import cmedina.cirion.tcpipstack.UserData.Usuario;
import cmedina.cirion.tcpipstack.UserData.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository userrepo;
    private final JWTService jwt;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authmanager;

    public AuthResponse login(LoginRequest request){
        System.out.println("request = " + request);
        authmanager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        UserDetails Usuario = userrepo.findByUsername(request.getUsername()).orElseThrow();
        String token = jwt.getToken(Usuario);
        return AuthResponse.builder()
                .token(token)
                .build();
    }

    public AuthResponse register(RegisterRequest request){
        Usuario usuario = Usuario.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .primernombre(request.getPrimernombre())
                .segundonombre(request.getSegundonombre())
                .primerapellido(request.getPrimernombre())
                .segundoapellido(request.getSegundoapellido())
                .role(Role.USER).build();

        userrepo.save(usuario);

        return AuthResponse.builder()
                .token(jwt.getToken(usuario))
                .build();
    }

}
