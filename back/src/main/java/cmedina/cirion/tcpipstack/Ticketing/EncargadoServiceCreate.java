package cmedina.cirion.tcpipstack.Ticketing;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EncargadoServiceCreate {

    private final EncargadoRepository encargadoRepository;


    public EngineerModel createEncargadoInDataBase(String nombre, String email) {
        EngineerModel ingeniero = createEncargado(nombre, email);
        EngineerModel encargadoCreado = encargadoRepository.save(ingeniero);
        return encargadoCreado;
    }

    private EngineerModel createEncargado(String nombre, String email) {
        if (nombre == null || nombre.isEmpty() || email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Nombre y email no pueden ser vacíos");
        } else {
            return EngineerModel.builder().nombre(nombre).email(email).build();
        }
    }
}
