package utils;

import model.Utente;

/**
 * Classe per la gestione dei filtri di autorizzazione nel sistema Telepass.
 */
public class PermissionFilter {

    /**
     * Verifica se l'utente ha privilegi di amministratore.
     *
     * @param u L'utente da verificare.
     * @return true se l'utente è un amministratore, false altrimenti o se l'utente è nullo.
     */
    public static boolean checkAdmin(Utente u) {
        return u != null && u.getAmministratore() == 1;
    }
}
