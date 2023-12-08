package service;

public interface TransponderService {

        //Può essere usato per la registrazione transponder fatta dall'amministratore
        void insertTransponder();

        void getAllTransponders();

        //Può essere usato per la revoca transponder fatta dall'amministratore
        void updateTransponder();

        void deleteTransponderById();

        void getTransponderByCodice();
}
