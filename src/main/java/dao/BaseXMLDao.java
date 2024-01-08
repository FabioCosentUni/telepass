package dao;

/**
 * Interfaccia che definisce le operazioni di base per l'accesso ai dati presenti in file XML.
 * @param <T> Il tipo dell'oggetto principale rappresentato dal file XML.
 * @param <E> Il tipo degli oggetti contenuti nell'XML, se pertinente.
 * @param <ID> Il tipo dell'ID utilizzato per identificare gli oggetti all'interno del file XML.
 */
public interface BaseXMLDao<T, E, ID> {

    /**
     * Restituisce l'oggetto corrispondente all'ID fornito.
     *
     * @param id L'ID dell'oggetto da cercare all'interno del file XML.
     * @return L'oggetto corrispondente all'ID fornito, se presente; altrimenti, null.
     */
    E findById(ID id);

    /**
     * Restituisce tutti gli oggetti presenti nel file XML.
     *
     * @return L'oggetto principale rappresentante tutti gli elementi presenti nel file XML.
     */
    T findAll();
}
