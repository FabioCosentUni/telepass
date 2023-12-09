package service;

import dto.TransponderDTO;

import java.util.List;

public interface TransponderService {

        //Può essere usato per la registrazione transponder fatta dall'amministratore
        boolean insertTransponder(TransponderDTO transponder);

        List<TransponderDTO> getAllTransponders();

        //Può essere usato per la revoca transponder fatta dall'amministratore
        boolean updateTransponder(TransponderDTO transponder);

        boolean deleteTransponderById(long id);

        TransponderDTO getTransponderByCodice(long id);
}
