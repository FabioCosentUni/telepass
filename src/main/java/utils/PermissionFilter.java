package utils;

import model.Utente;

public class PermissionFilter {

    public static boolean checkAdmin(Utente u) {
        return u != null && u.getAmministratore() == 1;
    }
}
