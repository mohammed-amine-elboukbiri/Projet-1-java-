package service;

import model.Client;
import dao.ClientDao;
import java.util.List;

public class ClientService {

    private final ClientDao cdao = new ClientDao();

    public Client getClient(Client c) throws Exception {
        return cdao.findById(c.getId_client());
    }

    public List<Client> listClients() throws Exception {
        return cdao.findAll();
    }

    public Client createClient(Client c) throws Exception {
         cdao.insert(c);
         return c;
    }

    public boolean updateClient(Client c) throws Exception {
        return cdao.update(c);
    }

    public boolean deleteClient(Client c) throws Exception {
        return cdao.delete(c.getId_client());
    }
}