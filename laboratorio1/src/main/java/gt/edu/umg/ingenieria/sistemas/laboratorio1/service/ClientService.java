package gt.edu.umg.ingenieria.sistemas.laboratorio1.service;

import gt.edu.umg.ingenieria.sistemas.laboratorio1.dao.ClientRepository;
import gt.edu.umg.ingenieria.sistemas.laboratorio1.model.Client;
import gt.edu.umg.ingenieria.sistemas.laboratorio1.model.Error;
import gt.edu.umg.ingenieria.sistemas.laboratorio1.utils.Helpers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Josué Barillas (jbarillas)
 */
@Service
public class ClientService {



    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client buscarNit (String nit) {
        List<Client> clients = gClientes();
        return clients.stream()
                .filter(client -> Objects.equals(client.getNit(), nit))
                .findFirst()
                .orElse(new Client());
    }

    public List<Client> gClientesNameLastName(String nameAndLastName) {
        List<Client> clients = gClientes();
        List<Client> result = new ArrayList<>();
        for (Client a: clients) {
            if(a.getFirstName().matches(nameAndLastName) || a.getLastName().matches(nameAndLastName)){
                result.add(a);
            }
        }
        return result;
    }

    public Client gClienteId(long id) {
        return this.clientRepository.findById(id).get();
    }

    public List<Client> gClientes() {
        return (List<Client>) this.clientRepository.findAll();
    }

    public Object crea(Client client) {
        String msg = "No se permitira el registro de clientes menores de edad.";
        if(!Helpers.isOverOrEqualsNYears(client.getBirthday(), 18)){
            try {
                throw new Exception(msg);
            } catch (Exception e) {
                e.printStackTrace();
                return new Error("Menor de edad", msg);
            }
        } else if (!(client.getNit().matches("\\d+") && client.getNit().length() == 10)) {
            msg = "NIT invalido.";
            try {
                throw new Exception(msg);
            } catch (Exception e) {
                e.printStackTrace();
                return new Error(msg, "Nit invalido o es de diferente longitud a 10");
            }
        } else {
            client.setFirstName(
                    client.getFirstName().substring(0, 1).toUpperCase() + client.getFirstName().substring(1).toLowerCase()
            );
            client.setLastName(
                    client.getLastName().substring(0, 1).toUpperCase() + client.getLastName().substring(1).toLowerCase()
            );
            return this.clientRepository.save(client);
        }
    }

    public Client uCliente(Client client) {
        return this.clientRepository.save(client);
    }





//viejo
}
