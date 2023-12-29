package service;

import exception.TelepassException;
import model.Transponder;

import java.util.List;

public interface TransponderService {

        void insert(Transponder transponder) throws TelepassException;

        List<Transponder> getActiveTransponders() throws TelepassException;

        void makePlus(Transponder transponder) throws TelepassException;

        void revokeTransponder(String transponderCode) throws TelepassException;
}
