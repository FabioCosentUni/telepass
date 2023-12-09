package service;

import dto.VeicoloDTO;

import java.util.List;

public interface VeicoloService {

             boolean insertVeicolo(VeicoloDTO veicolo);

             List<VeicoloDTO> getAllVeicoli();

             boolean updateVeicolo(VeicoloDTO veicolo);

             boolean deleteVeicoloByTarga(String targa);

             VeicoloDTO getVeicoloByTarga(String targa);

             void entraNelCasello(VeicoloDTO veicolo, long idCasello);

             void esceDalCasello(VeicoloDTO veicolo, long idCasello);
}
