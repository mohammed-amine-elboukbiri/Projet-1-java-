package model;
import java.time.LocalDate;

public class Commande {

    private int id_commande;
    private LocalDate date_commande;
    private int quantite_commande;
    private Plat plat;
    private Client client;

    public Commande(){
    }

    public Commande(LocalDate date_commande, int quantite_commande, Plat plat, Client client) {
        this.date_commande = date_commande;
        this.quantite_commande = quantite_commande;
        this.plat = plat;
        this.client = client;
    }

    public Commande(int id_commande, LocalDate date_commande, int quantite_commande, Plat plat, Client client) {
        this(date_commande, quantite_commande, plat, client);
        this.id_commande = id_commande;
    }

    public int getId_commande() {
        return id_commande;
    }
    public void setId_commande(int id_commande) {
        this.id_commande = id_commande;
    }

    public LocalDate getDate_commande() {
        return date_commande;
    }
    public void setDate_commande(LocalDate date_commande) {
        this.date_commande = date_commande;
    }

    public int getQuantite_commande() {
        return quantite_commande;
    }
    public void setQuantite_commande(int quantite_commande) {
        this.quantite_commande = quantite_commande;
    }

    public Client getClient() {
        return client;
    }
    public void setClient(Client client) {
        this.client = client;
    }

    public Plat getPlat() {
        return plat;
    }
    public void setPlat(Plat plat) {
        this.plat = plat;
    }
}
