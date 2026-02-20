package presentation;

import model.Plat;
import service.PlatService;
import java.util.List;

public class PlatTest {

    public static void main (String[] args) {

        PlatService pservi = new PlatService();

        try {
            Plat p = new Plat("Tajin", "Cuisine maroccain", 100);
            pservi.createPlat(p);
            System.out.println("\nPlat added to the database successfully . With an Id : " + p.getId_plat());

            Plat p2 = new Plat("Tiramisu", "Cuisine italien", 40);
            pservi.createPlat(p2);
            System.out.println("\nPlat added to the database successfully . With an Id : " + p2.getId_plat());

            Plat p3 = new Plat("Pizza", "Cuisine italien", 100);
            pservi.createPlat(p3);
            System.out.println("\nPlat added to the database successfully . With an Id : " + p3.getId_plat());


            System.out.println("\nList of the  Plates :");
            pservi.listPlats().forEach(pl ->
                    System.out.printf("\nId : %d  || Nom : %s || Catégorie : %s || Prix : %.2f DH",
                            pl.getId_plat(), pl.getNom_plat(), pl.getCategorie_plat(), pl.getPrix_plat()));


            System.out.println("\n\nFiltrage catégorie 'Cuisine italien' :");

            List<Plat> resultat = pservi.filtrageCategoriePlat("Cuisine italien");

            if(resultat.isEmpty()){
                System.out.println("Aucun plat trouvé !");
            } else {
                resultat.forEach(pl ->
                        System.out.printf("\nId : %d  || Nom : %s || Catégorie : %s || Prix : %.2f DH",
                                pl.getId_plat(),
                                pl.getNom_plat(),
                                pl.getCategorie_plat(),
                                pl.getPrix_plat()));
            }


            p.setNom_plat("Pastilla de poisson");
            System.out.println("\n\nPlat Infos updated " + pservi.updatePlat(p));

            //System.out.println("\nPlat deleted from the database " + pservi.deletePlat(p));

            System.out.println("\n\nNombre de plats italien = " + resultat.size());


        }
        catch (Exception exc) {
            exc.printStackTrace();
        }

    }


}