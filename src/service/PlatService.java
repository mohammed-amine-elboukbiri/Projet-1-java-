package service;

import model.Plat;
import dao.PlatDao;
import java.util.List;

public class PlatService {

    private final PlatDao pdao = new PlatDao();

    public Plat getPlat(Plat p) throws Exception {
        return pdao.findById(p.getId_plat());
    }

    public List<Plat> listPlats() throws Exception {
        return pdao.findAll();
    }

    public Plat createPlat(Plat p) throws Exception {
        pdao.insert(p);
        return p;
    }

    public boolean updatePlat(Plat p) throws Exception {
        return pdao.update(p);
    }

    public boolean deletePlat(Plat p) throws Exception {
        return pdao.delete(p.getId_plat());
    }

    public List<Plat> filtrageCategoriePlat(String categorie) throws Exception {
        return pdao.filtrageCategorie(categorie);
    }
}