/**
 * Interfaccia che estende {@link BaseHibernateDao} per la gestione dei dati relativi ai caselli autostradali
 * utilizzando Hibernate come framework ORM.
 * Estende l'interfaccia {@link BaseHibernateDao} fornendo operazioni specifiche per l'entità {@link Casello}.
 */
package dao;

import model.Casello;

public interface CaselloDAO extends BaseHibernateDAO<Casello, Long> {
}
