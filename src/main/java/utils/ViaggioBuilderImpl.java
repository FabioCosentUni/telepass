package utils;

import model.Casello;
import model.Veicolo;
import model.Viaggio;

import java.util.Date;

/**
 * Implementazione dell'interfaccia ViaggioBuilder per la costruzione di oggetti Viaggio.
 */
public class ViaggioBuilderImpl implements ViaggioBuilder {

    private final Viaggio viaggio;

    /**
     * Costruttore che inizializza un nuovo oggetto Viaggio per la costruzione.
     */
    public ViaggioBuilderImpl() {
        this.viaggio = new Viaggio();
    }

    @Override
    public ViaggioBuilder setCaselloEntrata(Casello caselloEntrata) {
        viaggio.setCaselloEntryDTO(caselloEntrata);
        return this;
    }

    @Override
    public ViaggioBuilder setCaselloUscita(Casello caselloUscita) {
        viaggio.setCaselloExitDTO(caselloUscita);
        return this;
    }

    @Override
    public ViaggioBuilder setVeicolo(Veicolo veicolo) {
        viaggio.setVeicoloDTO(veicolo);
        return this;
    }

    @Override
    public ViaggioBuilder setTimeEntry(Date timeEntry) {
        viaggio.setTimeEntry(timeEntry);
        return this;
    }

    @Override
    public ViaggioBuilder setTimeExit(Date timeExit) {
        viaggio.setTimeExit(timeExit);
        return this;
    }

    @Override
    public ViaggioBuilder setPedaggio(float pedaggio) {
        viaggio.setPedaggio(pedaggio);
        return this;
    }

    @Override
    public ViaggioBuilder setPagatoFlag(int pagatoFlag) {
        viaggio.setPagatoFlag(pagatoFlag);
        return this;
    }

    @Override
    public Viaggio build() {
        return viaggio;
    }
}
