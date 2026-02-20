package presentation;

import model.Plat;
import model.Client;
import model.Commande;
import service.ClientService;
import service.CommandeService;
import service.PlatService;
import java.util.Map;
import java.util.List;

import java.time.LocalDate;

public class CommandeTest {

    public static void main (String[] args) {

        PlatService pservi = new PlatService();
        ClientService cservi = new ClientService();
        CommandeService cmservi = new CommandeService();

        try {

            Plat p1 = new Plat("Tajin", "Cuisine marocaine", 100);
            pservi.createPlat(p1);
            Plat p2 = new Plat("Couscous", "Cuisine marocaine", 120);
            pservi.createPlat(p2);
            Plat p3 = new Plat("Pizza", "Fast Food", 80);
            pservi.createPlat(p3);

            Client c1 = new Client("Amine", "0781294503");
            cservi.createClient(c1);
            Client c2 = new Client("Sara", "0681037492");
            cservi.createClient(c2);

            Commande cm1 = new Commande(LocalDate.now(), 2, p1, c1);
            cmservi.createCommande(cm1);
            System.out.println("\nCommande added to the database successfully . With an Id : " + cm1.getId_commande());
            Commande cm2 = new Commande(LocalDate.now(), 3, p2, c1);
            cmservi.createCommande(cm2);
            System.out.println("\nCommande added to the database successfully . With an Id : " + cm2.getId_commande());
            Commande cm3 = new Commande(LocalDate.now(), 1, p3, c1);
            cmservi.createCommande(cm3);
            System.out.println("\nCommande added to the database successfully . With an Id : " + cm3.getId_commande());
            Commande cm4 = new Commande(LocalDate.now(), 5, p1, c2);
            cmservi.createCommande(cm4);
            System.out.println("\nCommande added to the database successfully . With an Id : " + cm4.getId_commande());


            System.out.println("\nList of the  Commandes :");
            cmservi.listCommandes().forEach(com ->
                    System.out.printf("\nId : %d  || Date : %s || quantity : %d || Plat : %s (%d) || Client : %s (%d)",
                            com.getId_commande(), com.getDate_commande(), com.getQuantite_commande(), com.getPlat().getNom_plat(),
                            com.getPlat().getId_plat(), com.getClient().getNom_client(), com.getClient().getId_client()));

            cm1.setQuantite_commande(4);
            System.out.println("\n\nCommande Infos updated " + cmservi.updateCommande(cm1));

//            System.out.println("\Commande deleted from the database " + cmservi.deleteCommande(cm));

            System.out.println("\n===== Chiffre d'affaires par plat =====");

            Map<String, Double> chaPlat = cmservi.chiffreAffaireParPlat();

            chaPlat.forEach((plat, cha) ->
                    System.out.println("Plat : " + plat + " -> Chiffre d'affaires : " + cha + " DH"));

            System.out.println("\n===== Recette totale =====");

            double total = cmservi.calculerRecettesTotal();
            System.out.println("Recette totale = " + total + " DH");

            System.out.println("\n===== Plats populaires =====");

            List<Map<String, Object>> populaires = cmservi.platsPopulairesRestau();

            for (Map<String, Object> plpo : populaires) {
                System.out.println(
                        "Plat : " + plpo.get("nom_plat") +
                                " | Catégorie : " + plpo.get("categorie_plat") +
                                " | Prix : " + plpo.get("prix_plat") +
                                " | Commandé : " + plpo.get("total_commande") + " fois"
                );
            }



        }
        catch (Exception exc) {
            exc.printStackTrace();
        }

    }


}