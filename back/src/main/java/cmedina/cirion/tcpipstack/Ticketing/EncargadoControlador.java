package cmedina.cirion.tcpipstack.Ticketing;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/encargado")
@RequiredArgsConstructor
public class EncargadoControlador {
    @Autowired
    private EncargadoServiceCreate crearencargado;
    @Autowired
    private ServicioObtenerEncargados obtenerEncargados;

    @PostMapping("crear-encargado")
    public ResponseEntity<EngineerModel> crearEncargado(@RequestParam String nombre, @RequestParam String email) {
        try{
            EngineerModel EncargadoCreado = crearencargado.createEncargadoInDataBase(nombre, email);
            return ResponseEntity.ok(EncargadoCreado);
        }catch (IllegalArgumentException e){
            System.out.println("e.getLocalizedMessage() = " + e.getLocalizedMessage());
            return ResponseEntity.badRequest().body(null);
        }catch (Exception e){
            System.out.println("e.getLocalizedMessage() = " + e.getLocalizedMessage());
            return ResponseEntity.status(500).body(null);
        }
    }

    @GetMapping("obtener-encargados")
    public ResponseEntity<List<EngineerModel>> obtenerEncargados() {
        return ResponseEntity.ok(obtenerEncargados.listarEncargados());
    }
}
