package cmedina.cirion.tcpipstack.Ticketing;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Tickets")
public class TicketingModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer IdTicket;
    @Getter
    @Column(nullable = false)
    String title;
    @Getter
    @Column(nullable = false)
    String description;
    @Getter
    @Enumerated(EnumType.STRING)
    EstadoTickets estadoTickets;
    @Getter
    @ManyToOne
    @JoinColumn(nullable = false, name = "Encargado")
    private EngineerModel engineer;

    public int getId(){
        return IdTicket;
    }

}
