package utils;

import model.Casello;
import model.Veicolo;
import model.Viaggio;

import java.util.Date;

public interface ViaggioBuilder {

    ViaggioBuilder setCaselloEntrata(Casello caselloEntrata);
    ViaggioBuilder setCaselloUscita(Casello caselloUscita);
    ViaggioBuilder setVeicolo(Veicolo veicolo);
    ViaggioBuilder setTimeEntry(Date timeEntry);
    ViaggioBuilder setTimeExit(Date timeExit);
    ViaggioBuilder setPedaggio(float pedaggio);
    ViaggioBuilder setPagatoFlag(int pagatoFlag);

    Viaggio build();



}
