package cmedina.cirion.tcpipstack.Ticketing;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ticketing")
@RequiredArgsConstructor
public class TicketingController {
    @Autowired
    private TicketingServiceCreate ticketcreator;

    @PostMapping("create-ticket")
    public ResponseEntity<TicketingModel> CreateTicket(@RequestParam String title, @RequestParam String description, @RequestParam String estado, @RequestParam int idIngeniero) {
        try{
            TicketingModel Ticket = ticketcreator.CreateTicketInDataBase(title, description, estado, idIngeniero);
            return ResponseEntity.ok(Ticket);
        }catch (IllegalArgumentException e){
            System.out.println("e.getLocalizedMessage() = " + e.getLocalizedMessage());
            return ResponseEntity.badRequest().body(null);
        }catch (Exception e){
            System.out.println("e.getLocalizedMessage() = " + e.getLocalizedMessage());
            return ResponseEntity.status(500).body(null);
            
        }
    }

    @PutMapping("update-ticket")
    public String UpdateTicket() {
        return "El ticket ha sido actualizado";
    }

    @GetMapping("leer-ticket")
    public String LeerTicket() {
        return "leer información de un ticket";
    }
}
