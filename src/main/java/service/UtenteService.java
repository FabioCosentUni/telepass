package service;

public interface UtenteService {

     void insertUtente();

     void getAllUtenti();

     void updateUtente();

     void deleteUtenteById();

     void getUtenteByCodiceFiscale();

     void richiediNuovoVeicoloTransponder(String targa);

     void richiediTelepassPlus();

}
