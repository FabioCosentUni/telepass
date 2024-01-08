package dao.impl;

import dao.BaseXMLDao;
import utils.JAXBUtils;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Implementazione astratta dell'interfaccia {@link BaseXMLDao} che gestisce l'accesso a dati
 * presenti in file XML utilizzando JAXB come meccanismo di marshalling/unmarshalling.
 * @param <T> Il tipo dell'oggetto principale rappresentato dal file XML.
 * @param <E> Il tipo degli oggetti contenuti nell'XML, se pertinente.
 * @param <ID> Il tipo dell'ID utilizzato per identificare gli oggetti all'interno del file XML.
 */
public abstract class BaseXMLDaoImpl<T, E, ID> implements BaseXMLDao<T, E, ID> {
    protected T xmlObject;

    /**
     * Costruttore che inizializza l'oggetto XML tramite il file XML corrispondente.
     *
     * @param xmlObjectClass La classe dell'oggetto principale rappresentato dal file XML.
     * @throws JAXBException se si verifica un errore durante la creazione del contesto JAXB.
     * @throws IOException se si verifica un errore di I/O durante la lettura del file XML.
     */
    public BaseXMLDaoImpl(Class<T> xmlObjectClass) throws JAXBException, IOException {
        InputStream is = this.getClass().getResourceAsStream("/xml/" + getXMLFileEntityName() + ".xml");
        xmlObject = JAXBUtils.unmarshall(is, xmlObjectClass);
    }

    /**
     * Restituisce il nome del file XML relativo all'entità gestita.
     *
     * @return Il nome del file XML corrispondente all'entità gestita.
     */
    public abstract String getXMLFileEntityName();
}
