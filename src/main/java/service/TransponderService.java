package service;

import exception.TelepassException;
import model.Transponder;

import java.util.List;

public interface TransponderService {

        void insert(Transponder transponder) throws TelepassException;

        List<Transponder> getActiveTrasponders() throws TelepassException;

        void updateTransponder(Transponder transponder);
}
