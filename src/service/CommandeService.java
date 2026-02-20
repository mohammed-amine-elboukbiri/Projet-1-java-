package service;

import dao.CommandeDao;
import model.Commande;
import java.util.List;
import java.util.Map;

public class CommandeService {

    private final CommandeDao cmdao = new CommandeDao();

    public Commande getCommande(Commande cm) throws Exception {
        return cmdao.findById(cm.getId_commande());
    }

    public List<Commande> listCommandes() throws Exception {
        return cmdao.findAll();
    }

    public Commande createCommande(Commande cm) throws Exception {
        cmdao.insert(cm);
        return cm;
    }

    public boolean updateCommande(Commande cm) throws Exception {
        return cmdao.update(cm);
    }

    public boolean deleteCommande(Commande cm) throws Exception {
        return cmdao.delete(cm.getId_commande());
    }

    public Map<String, Double> chiffreAffaireParPlat() throws Exception {
        return cmdao.chiffreAffiarePlat();
    }

    public double calculerRecettesTotal() throws Exception {
        return cmdao.calculerRecettes();
    }

    public List<Map<String, Object>> platsPopulairesRestau() throws Exception {
        return cmdao.platsPopulaires();
    }
}