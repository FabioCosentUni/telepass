package dao;

import model.Veicolo;

public interface VeicoloDAO extends BaseDao<Veicolo, String> {

    boolean deleteVeicoloByTarga(String targa);
}
