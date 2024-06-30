package cmedina.cirion.tcpipstack.DevOps.TeamsMagament.DataModelForTeams;

import cmedina.cirion.tcpipstack.UserData.Usuario;
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
@Table(name = "UsuariosDevOps")
public class UsuarioDevOps {

    @Id
    @Column(name = "devops_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long devops_id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Usuario usuario;

    @Enumerated(EnumType.STRING)
    private Role rol;


}
