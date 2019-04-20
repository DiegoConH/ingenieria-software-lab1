package gt.edu.umg.ingenieria.sistemas.laboratorio1.dao;

import gt.edu.umg.ingenieria.sistemas.laboratorio1.model.Client;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Josué Barillas (jbarillas)
 */
public interface ClientRepository extends CrudRepository <Client,Long> {
    
}
