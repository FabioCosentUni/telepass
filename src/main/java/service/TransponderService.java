package service;

import exception.TelepassException;
import model.Transponder;

import java.sql.SQLException;
import java.util.List;

public interface TransponderService {

        //Può essere usato per la registrazione transponder fatta dall'amministratore
        void insert(Transponder transponder) throws TelepassException;

        List<Transponder> getTrasponders();

        //Può essere usato per la revoca transponder fatta dall'amministratore
        boolean updateTransponder(Transponder transponder);

        void deleteTransponderById(long id) throws TelepassException;

        Transponder getTransponderByCodice(String codice) throws SQLException;
}
