/**
 * Interfaccia che estende {@link BaseHibernateDao} per la gestione dei dati relativi ai veicoli
 * utilizzando Hibernate come framework ORM.
 * Estende l'interfaccia {@link BaseHibernateDao} fornendo operazioni specifiche per l'entità {@link Veicolo}.
 */
package dao;

import model.Veicolo;

public interface VeicoloHibernateDAO extends BaseHibernateDao<Veicolo, String> {
}
