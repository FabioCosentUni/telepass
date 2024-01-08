package dao;

import model.pojo.Autostrada;
import model.pojo.Autostrade;

/**
 * Interfaccia che estende un DAO (Data Access Object) per l'accesso ai dati delle autostrade
 * utilizzando il meccanismo di gestione XML.
 * Estende l'interfaccia {@link BaseXMLDao} fornendo operazioni specifiche per la gestione
 * delle entità {@link Autostrade} e {@link Autostrada}.
 */
public interface AutostradaDAO extends BaseXMLDao<Autostrade, Autostrada, String> {
}
