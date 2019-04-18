package gt.edu.umg.ingenieria.sistemas.laboratorio1;

import gt.edu.umg.ingenieria.sistemas.laboratorio1.model.Client;
import gt.edu.umg.ingenieria.sistemas.laboratorio1.service.ClientService;
import gt.edu.umg.ingenieria.sistemas.laboratorio1.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 *
 * @author Josué Barillas (jbarillas)
 */
@RestController
@RequestMapping("/cliente")
public class ClientController {

    private final ReportService _reportService;
    private final ClientService _clientService;

    @Autowired
    public ClientController(ReportService reportService, ClientService clientService) {
        this._reportService = reportService;
        this._clientService = clientService;
    }

    @GetMapping("/porid")
    public Client gId(@RequestParam(name = "id") long id) {
        return this._clientService.gClientId(id);
    }

    @GetMapping("/nitbuscar")
    public Client gNit(@RequestParam(name = "nit") String nit) {
        return this._clientService.gClientNit(nit);
    }

    @GetMapping("/nombreapellidobuscar")
    public List<Client> geNameLastName(@RequestParam(name = "query") String nameAndLastName) {
        return this._clientService.gClientsNameLastName(nameAndLastName);
    }

    @GetMapping("/buscarTodos")
    private List<Client> getByAll() {
        return this._clientService.getAllClients();
    }

    @PostMapping("/creaCliente")
    public Object crea(@RequestBody Client client) {
        return this._clientService.creaClient(client);
    }

    @PutMapping("/editarCliente/{id}/{nit}")
    public Client uNit(@PathVariable long id, @PathVariable String nit) {
        Client client = gId(id);
        client.setNit(nit);
        return this._clientService.uClient(client);
    }

    @PutMapping("/editarCliente/{id}/{name}/{lastName}")
    public Client updateNameAndLastName(@PathVariable long id, @PathVariable String name, @PathVariable String lastName) {
        Client client = gId(id);
        client.setFirstName(name);
        client.setLastName(lastName);
        return this._clientService.uClient(client);
    }

    @GetMapping("/generarReporteClientes")
    public String generateReport() {
        return this._reportService.generateReport();
    }

}
