package service;

import exception.TelepassException;
import model.Transponder;

import java.util.List;

public interface TransponderService {

        void insert(Transponder transponder) throws TelepassException;

        List<Transponder> getActiveTransponders() throws TelepassException;

        void updateTransponder(Transponder transponder);

        void revokeTransponder(String transponderCode) throws TelepassException;
}
