package model;

public class Client {

    private int id_client;
    private String nom_client;
    private String telephone_client;

    public Client(){
    }

    public Client(String nom_client, String telephone_client) {
        this.nom_client = nom_client;
        this.telephone_client = telephone_client;
    }

    public Client(int id_client, String nom_client, String telephone_client) {
        this(nom_client, telephone_client);
        this.id_client = id_client;
    }

    public int getId_client() {
        return id_client;
    }
    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public String getNom_client() {
        return nom_client;
    }
    public void setNom_client(String nom_client) {
        this.nom_client = nom_client;
    }

    public String getTelephone_client() {
        return telephone_client;
    }
    public void setTelephone_client(String telephone_client) {
        this.telephone_client = telephone_client;
    }
}
