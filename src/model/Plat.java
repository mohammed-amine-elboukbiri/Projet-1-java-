package model;

public class Plat {

    private int id_plat;
    private String nom_plat;
    private String categorie_plat;
    private double prix_plat;

    public Plat(){
    }

    public Plat(String nom_plat, String categorie_plat, double prix_plat) {
        this.nom_plat = nom_plat;
        this.categorie_plat = categorie_plat;
        this.prix_plat = prix_plat;
    }

    public Plat(int id_plat, String nom_plat, String categorie_plat, double prix_plat) {
        this(nom_plat, categorie_plat, prix_plat);
        this.id_plat = id_plat;
    }

    public int getId_plat() {
        return id_plat;
    }
    public void setId_plat(int id_plat) {
        this.id_plat = id_plat;
    }

    public String getNom_plat() {
        return nom_plat;
    }
    public void setNom_plat(String nom_plat) {
        this.nom_plat = nom_plat;
    }

    public String getCategorie_plat() {
        return categorie_plat;
    }
    public void setCategorie_plat(String categorie_plat) {
        this.categorie_plat = categorie_plat;
    }

    public double getPrix_plat() {
        return prix_plat;
    }
    public void setPrix_plat(double prix_plat) {
        this.prix_plat = prix_plat;
    }
}
