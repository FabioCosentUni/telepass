package service;

import exception.TelepassException;
import model.Casello;
import model.bo.GetAutostradeOutputBO;

import java.util.List;

/**
 * Interfaccia che definisce i metodi disponibili per la gestione dei caselli autostradali.
 */
public interface CaselloService {

    /**
     * Restituisce una lista di tutti i caselli autostradali.
     *
     * @return Una lista di oggetti {@link Casello} rappresentanti tutti i caselli.
     */
    List<Casello> getAllCaselli();

    /**
     * Restituisce una lista di nomi di tutte le autostrade presenti nei caselli.
     *
     * @return {@link GetAutostradeOutputBO}
     */
    GetAutostradeOutputBO getAllAutostrade() throws TelepassException;
}
