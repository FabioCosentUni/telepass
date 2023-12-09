package service;

import dto.ViaggioDTO;

import java.util.List;

public interface ViaggioService {

             boolean insertViaggio(ViaggioDTO viaggio);

             List<ViaggioDTO> getAllViaggi();

             boolean updateViaggio(ViaggioDTO viaggio);

             boolean deleteViaggioById(long viaggioId);

             ViaggioDTO getViaggioById(long viaggioId);
}
