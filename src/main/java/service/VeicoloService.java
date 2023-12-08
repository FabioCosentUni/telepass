package service;

public interface VeicoloService {

             void insertVeicolo();

             void getAllVeicoli();

             void updateVeicolo();

             void deleteVeicoloById();

             void getVeicoloByTarga();

             void entraNelCasello(long idCasello);

             void esceDalCasello(long idCasello);
}
