package cmedina.cirion.tcpipstack.Ticketing;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServicioObtenerEncargados {
    private final EncargadoRepository encargadoRepository;

    public List<EngineerModel> listarEncargados() {
        return encargadoRepository.findAll();
    }
}
