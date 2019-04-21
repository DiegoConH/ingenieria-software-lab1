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
    private final ServletContext _servletContext;

    @Autowired
    public ClientController(ReportService reportService, ClientService clientService, ServletContext servletContext) {
        this._reportService = reportService;
        this._clientService = clientService;
        this._servletContext = servletContext;
    }

    @GetMapping("/porid")
    public Client buscarId(@RequestParam(name = "Id") long id) {
        return this._clientService.gClienteId(id);
    }

    @GetMapping("/buscarpornit")
    public Client buscarNit(@RequestParam(name = "Nit") String nit) {
        return this._clientService.gClienteNit(nit);
    }

    @GetMapping("/buscarpornombreapellido")
    public List<Client> buscarnombreapellido(@RequestParam(name = "query") String nameAndLastName) {
        return this._clientService.gClientesNameLastName(nameAndLastName);
    }

    @GetMapping("/buscartodos")
    private List<Client> getByAll() {
        return this._clientService.gClientes();
    }

    @PostMapping("/crearcliente")
    public Object crea(@RequestBody Client client) {
        return this._clientService.creaCliente(client);
    }

    @PutMapping("/editar/{id}/{nit}")
    public Client uNit(@PathVariable long id, @PathVariable String nit) {
        Client client = buscarId(id);
        client.setNit(nit);
        return this._clientService.uCliente(client);
    }

    @PutMapping("/editarcliente/{id}/{name}/{lastName}")
    public Client uNameLastName(@PathVariable long id, @PathVariable String name, @PathVariable String lastName) {
        Client client = buscarId(id);
        client.setFirstName(name);
        client.setLastName(lastName);
        return this._clientService.uCliente(client);
    }



    @GetMapping("/generarreporteclientes")
    public String generaReporte() {
       return this._reportService.generaReporte(this._clientService.gClientes(), _servletContext);
    }
        //diego


}
