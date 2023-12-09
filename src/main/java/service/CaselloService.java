package service;

import dto.CaselloDTO;
import dto.UtenteDTO;

import java.util.List;

public interface CaselloService {

         boolean insertCasello(UtenteDTO utente, CaselloDTO casello);

         List<CaselloDTO> getAllCaselli();

         boolean updateCasello(UtenteDTO utente, CaselloDTO casello);

         boolean deleteCaselloById(UtenteDTO utente, long caselloId);

         CaselloDTO getCaselloById(long caselloId);

         void statisticheCasello();
}
