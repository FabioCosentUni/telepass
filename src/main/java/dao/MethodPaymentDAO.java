/**
 * Interfaccia che estende {@link BaseHibernateDao} per la gestione dei dati relativi ai metodi di pagamento
 * utilizzando Hibernate come framework ORM.
 * Estende l'interfaccia {@link BaseHibernateDao} fornendo operazioni specifiche per l'entità {@link MethodPayment}.
 */
package dao;

import model.MethodPayment;

public interface MethodPaymentDAO extends BaseHibernateDAO<MethodPayment, String> {
}
