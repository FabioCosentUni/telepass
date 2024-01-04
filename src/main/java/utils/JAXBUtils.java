package utils;

import org.apache.tomcat.util.http.fileupload.IOUtils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

public class JAXBUtils {


    private JAXBUtils() {
    }

    public static <T> T unmarshall(InputStream inputStream, Class<T> clazz) throws JAXBException, IOException {
        final File xml = getFileFromStream(inputStream);

        JAXBContext jc = JAXBContext.newInstance(clazz);
        Unmarshaller unmarshaller = jc.createUnmarshaller();
        return clazz.cast(unmarshaller.unmarshal(xml));
    }

    private static File getFileFromStream(InputStream inputStream) throws IOException {
        final File tempFile = Files.createTempFile("file", ".tmp").toFile();
        tempFile.setReadable(true, true);
        tempFile.setWritable(true, true);
        tempFile.setExecutable(true, true);

        tempFile.deleteOnExit();
        try (FileOutputStream out = new FileOutputStream(tempFile)) {
            IOUtils.copy(inputStream, out);
        }
        return tempFile;
    }
}
