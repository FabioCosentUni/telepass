package utils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;

public class JAXBUtils {


    private JAXBUtils() {
    }

    public static <T> T unmarshall(File xml, Class<T> clazz) throws JAXBException, IOException {
        JAXBContext jc = JAXBContext.newInstance(clazz);
        Unmarshaller unmarshaller = jc.createUnmarshaller();
        return clazz.cast(unmarshaller.unmarshal(xml));
    }
}
