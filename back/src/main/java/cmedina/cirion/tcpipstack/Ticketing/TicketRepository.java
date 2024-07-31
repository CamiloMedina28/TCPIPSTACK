package cmedina.cirion.tcpipstack.Ticketing;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<TicketingModel, Integer> {
}
