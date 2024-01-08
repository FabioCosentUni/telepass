package utils;

import model.Casello;
import model.Veicolo;
import model.Viaggio;

import java.util.Date;

/**
 * Interfaccia per la costruzione di oggetti Viaggio.
 */
public interface ViaggioBuilder {

    ViaggioBuilder setCaselloEntrata(Casello caselloEntrata);

    ViaggioBuilder setCaselloUscita(Casello caselloUscita);

    ViaggioBuilder setVeicolo(Veicolo veicolo);

    ViaggioBuilder setTimeEntry(Date timeEntry);

    ViaggioBuilder setTimeExit(Date timeExit);

    ViaggioBuilder setPedaggio(float pedaggio);

    ViaggioBuilder setPagatoFlag(int pagatoFlag);

    /**
     * Costruisce un'istanza di Viaggio con i valori forniti dai metodi set.
     *
     * @return Un'istanza di Viaggio configurata con i valori forniti.
     */
    Viaggio build();
}
