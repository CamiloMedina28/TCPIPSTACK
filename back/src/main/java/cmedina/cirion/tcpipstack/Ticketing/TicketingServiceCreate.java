package cmedina.cirion.tcpipstack.Ticketing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketingServiceCreate {

    private final TicketRepository ticketRepository;
    private final EncargadoRepository encargadoRepository;

    @Autowired
    public TicketingServiceCreate(TicketRepository ticketRepository, EncargadoRepository encargadoRepository) {
        this.ticketRepository = ticketRepository;
        this.encargadoRepository = encargadoRepository;
    }

    public TicketingModel CreateTicketInDataBase(String title, String description, String estado, int idIngeniero) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Ticket information is not valid.");
        }
        TicketingModel ticket = CreateTicketingModel(title, description, estado);
        return createTicket(ticket, idIngeniero);
    }

    private TicketingModel CreateTicketingModel(String title, String description, String estado) {
        TicketingModel ticket = TicketingModel.builder()
                .title(title)
                .description(description)
                .estadoTickets(EstadoTickets.valueOf(estado))
                .build();
        return ticket;
    }

    private TicketingModel createTicket(TicketingModel ticket, int idIngeniero) {
        EngineerModel engineer = encargadoRepository
                .findById(idIngeniero)
                .orElseThrow(() -> new IllegalArgumentException("El encargado del ticket no es válido"));
        ticket.setEngineer(engineer);
        return ticketRepository.save(ticket);
    }
}
