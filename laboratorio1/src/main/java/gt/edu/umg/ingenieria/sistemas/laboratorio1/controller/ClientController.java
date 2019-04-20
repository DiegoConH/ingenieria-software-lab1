package gt.edu.umg.ingenieria.sistemas.laboratorio1.controller;

import gt.edu.umg.ingenieria.sistemas.laboratorio1.model.Client;
import gt.edu.umg.ingenieria.sistemas.laboratorio1.service.ClientService;
import gt.edu.umg.ingenieria.sistemas.laboratorio1.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.ServletContext;
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
    public Client gId(@RequestParam(name = "Id") long id) {
        return this._clientService.gClienteId(id);
    }

    @GetMapping("/nitbuscar")
    public Client gNit(@RequestParam(name = "Nit") String nit) {
        return this._clientService.gClienteNit(nit);
    }

    @GetMapping("/nombreapellidobuscar")
    public List<Client> gNameLastName(@RequestParam(name = "query") String nameAndLastName) {
        return this._clientService.gClientesNameLastName(nameAndLastName);
    }

    @GetMapping("/buscartodos")
    private List<Client> getByAll() {
        return this._clientService.gClientes();
    }

    @PostMapping("/creaCliente")
    public Object crea(@RequestBody Client client) {
        return this._clientService.creaCliente(client);
    }

    @PutMapping("/editar/{id}/{nit}")
    public Client uNit(@PathVariable long id, @PathVariable String nit) {
        Client client = gId(id);
        client.setNit(nit);
        return this._clientService.uCliente(client);
    }

    @PutMapping("/editar/{id}/{name}/{lastName}")
    public Client uNameLastName(@PathVariable long id, @PathVariable String name, @PathVariable String lastName) {
        Client client = gId(id);
        client.setFirstName(name);
        client.setLastName(lastName);
        return this._clientService.uCliente(client);
    }



    //@GetMapping("/generarReporteClientes")
    //public String generateReport() {
       // return this._reportService.generateReport(this._clientService.gClientes(), _servletContext);
    //}



}
