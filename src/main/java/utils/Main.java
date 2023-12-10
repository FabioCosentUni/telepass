package utils;

import dao.CaselloDAO;
import dao.impl.CaselloDAOImpl;
import model.Casello;
import service.CaselloService;
import service.impl.CaselloServiceImpl;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {
        System.out.println("Hello World!");

        Casello caselloDTO = new Casello();
        caselloDTO.setIdCaselloPk((long)1);
        caselloDTO.setKm(100);
        caselloDTO.setIngressi(5);
        caselloDTO.setAutostrada("A1");

        CaselloDAO caselloDAO = new CaselloDAOImpl();
        //caselloDAO.insertCasello(caselloDTO);
        //caselloDAO.updateCasello(caselloDTO);
        //caselloDAO.deleteCaselloById((long)1);
        //caselloDAO.getAllCaselli().forEach(casello -> System.out.println(casello.getAutostrada()));
        CaselloService caselloService = new CaselloServiceImpl();
        caselloService.getAllCaselli().forEach(casello -> System.out.println(casello.getAutostrada()));
    }
}
