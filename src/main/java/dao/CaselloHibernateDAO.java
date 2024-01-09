/**
 * Interfaccia che estende {@link BaseHibernateDao} per la gestione dei dati relativi ai caselli autostradali
 * utilizzando Hibernate come framework ORM.
 * Estende l'interfaccia {@link BaseHibernateDao} fornendo operazioni specifiche per l'entità {@link Casello}.
 */
package dao;

import model.Casello;

import java.sql.SQLException;
import java.util.List;

public interface CaselloHibernateDAO extends BaseHibernateDAO<Casello, Long> {
}
