package service;

import model.Viaggio;

import java.util.List;

public interface ViaggioService {

             boolean insertViaggio(Viaggio viaggio);

             List<Viaggio> getAllViaggi();

             boolean updateViaggio(Viaggio viaggio);

             boolean deleteViaggioById(long viaggioId);

             Viaggio getViaggioById(long viaggioId);
}
