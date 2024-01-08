package dao.impl;

import dao.AutostradaDAO;
import model.pojo.Autostrada;
import model.pojo.Autostrade;

import javax.xml.bind.JAXBException;
import java.io.IOException;

/**
 * Implementazione dell'interfaccia {@link AutostradaDAO} che gestisce l'accesso ai dati delle autostrade
 * utilizzando il meccanismo di gestione XML.
 */
public class AutostradaDAOImpl extends BaseXMLDaoImpl<Autostrade, Autostrada, String> implements AutostradaDAO {

    /**
     * Costruttore che inizializza l'oggetto XML tramite il file XML delle autostrade.
     *
     * @throws JAXBException se si verifica un errore durante la creazione del contesto JAXB.
     * @throws IOException se si verifica un errore di I/O durante la lettura del file XML.
     */
    public AutostradaDAOImpl() throws JAXBException, IOException {
        super(Autostrade.class);
    }

    /**
     * Trova un'autostrada basata sul nome fornito.
     *
     * @param nome Il nome dell'autostrada da cercare.
     * @return L'oggetto {@link Autostrada} corrispondente al nome fornito, se presente; altrimenti, null.
     */
    @Override
    public Autostrada findById(String nome) {
        for (Autostrada autostrada : xmlObject.getAutostrada()) {
            if (autostrada.getNome().equals(nome)) {
                return autostrada;
            }
        }
        return null;
    }

    /**
     * Restituisce tutte le autostrade.
     *
     * @return L'oggetto {@link Autostrade} contenente tutte le autostrade.
     */
    @Override
    public Autostrade findAll() {
        return xmlObject;
    }

    /**
     * Restituisce il nome del file XML contenente le informazioni sulle autostrade.
     *
     * @return Il nome del file XML delle autostrade.
     */
    @Override
    public String getXMLFileEntityName() {
        return "Autostrade";
    }
}
