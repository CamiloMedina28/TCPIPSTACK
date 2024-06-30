package cmedina.cirion.tcpipstack.DevOps.TeamsMagament.DataModelForTeams;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Equipo")
public class Equipo {
    @Id
    @Column(name = "equipo_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer equipo_id;

    @Column(nullable = false)
    private String equipo_nombre;

    @Column(nullable = false)
    private String equipo_descripcion;

    @ManyToMany
    @JoinTable(name = "UsuariosEnEquipoDevOps",
        joinColumns = @JoinColumn(name = "equipo_id"),
        inverseJoinColumns = @JoinColumn(name = "devops_id"))
    private Set<UsuarioDevOps> UserDevOps;
}
