package service;

import dto.UtenteDTO;

import java.util.List;

public interface UtenteService {

     boolean insertUtente(UtenteDTO utenteDTO);

     List<UtenteDTO> getAllUtenti();

     boolean updateUtente(UtenteDTO utenteDTO);

     boolean deleteUtenteByCodiceFiscale(String codiceFiscale);

     UtenteDTO getUtenteByCodiceFiscale(String codiceFiscale);

     boolean richiediNuovoVeicoloTransponder(String targa);

     boolean richiediTelepassPlus(long idTransponder);

}
