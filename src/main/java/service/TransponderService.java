package service;

import model.Transponder;

import java.util.List;

public interface TransponderService {

        //Può essere usato per la registrazione transponder fatta dall'amministratore
        boolean insertTransponder(Transponder transponder);

        List<Transponder> getAllTransponders();

        //Può essere usato per la revoca transponder fatta dall'amministratore
        boolean updateTransponder(Transponder transponder);

        boolean deleteTransponderById(long id);

        Transponder getTransponderByCodice(long id);
}
