package utils;

import dao.CaselloDAO;
import dao.impl.CaselloDAOImpl;
import dto.CaselloDTO;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {
        System.out.println("Hello World!");

        CaselloDTO caselloDTO = new CaselloDTO();
        caselloDTO.setKm(100);
        caselloDTO.setIngressi(10);
        caselloDTO.setAutostrada("A1");

        CaselloDAO caselloDAO = new CaselloDAOImpl();
        caselloDAO.insertCasello(caselloDTO);

    }
}
