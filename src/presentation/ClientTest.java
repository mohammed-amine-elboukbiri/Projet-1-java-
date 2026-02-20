package presentation;

import model.Client;
import service.ClientService;

public class ClientTest {

    public static void main (String[] args) {

        ClientService cservi = new ClientService();

        try {

            Client c1 = new Client("Amine", "0781294503");
            cservi.createClient(c1);
            System.out.println("\nClient added to the database successfully . With an Id : " + c1.getId_client());
            Client c2 = new Client("Othman", "0619360376");
            cservi.createClient(c2);
            System.out.println("\nClient added to the database successfully . With an Id : " + c2.getId_client());


            System.out.println("\nList of the  clients :");
            cservi.listClients().forEach(cl ->
                    System.out.printf("\nId : %d  || Nom : %s || N°telephone : %s", cl.getId_client(), cl.getNom_client(), cl.getTelephone_client()));

            c1.setNom_client("Mohammed");
            System.out.println("\n\nClient Infos updated " + cservi.updateClient(c2));

//            System.out.println("\Client deleted from the database " + cservi.deleteClient(c));



        }
        catch (Exception exc) {
            exc.printStackTrace();
        }

    }


}