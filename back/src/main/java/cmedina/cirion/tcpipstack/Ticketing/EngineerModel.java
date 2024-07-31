package cmedina.cirion.tcpipstack.Ticketing;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Encargado")
public class EngineerModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer IdIngeniero;
    @Column(nullable = false)
    String nombre;
    @Column(nullable = false)
    String email;
}
