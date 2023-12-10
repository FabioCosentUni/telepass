package service;

import model.Casello;
import model.Utente;

import java.util.List;

public interface CaselloService {

         boolean insertCasello(Utente utente, Casello casello);

         List<Casello> getAllCaselli();

         boolean updateCasello(Utente utente, Casello casello);

         boolean deleteCaselloById(Utente utente, long caselloId);

         Casello getCaselloById(long caselloId);

         void statisticheCasello();
}
