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

/**
 * Utility per l'unmarshalling di file XML in oggetti Java utilizzando JAXB.
 */
public class JAXBUtils {

    // Costruttore privato per impedire l'istanziazione della classe
    private JAXBUtils() {
    }

    /**
     * Esegue l'unmarshalling di un'InputStream contenente dati XML in un oggetto Java specifico.
     *
     * @param inputStream L'InputStream contenente i dati XML da unmarshallare.
     * @param clazz       La classe dell'oggetto Java nel quale verranno convertiti i dati XML.
     * @param <T>         Il tipo di oggetto Java.
     * @return Un'istanza dell'oggetto Java convertito dai dati XML.
     * @throws JAXBException se si verifica un errore durante l'unmarshalling.
     * @throws IOException  se si verifica un errore di I/O.
     */
    public static <T> T unmarshall(InputStream inputStream, Class<T> clazz) throws JAXBException, IOException {
        final File xml = getFileFromStream(inputStream);

        JAXBContext jc = JAXBContext.newInstance(clazz);
        Unmarshaller unmarshaller = jc.createUnmarshaller();
        return clazz.cast(unmarshaller.unmarshal(xml));
    }

    /**
     * Converte un InputStream in un file temporaneo.
     *
     * @param inputStream L'InputStream da convertire in un file temporaneo.
     * @return Il file temporaneo creato dall'InputStream.
     * @throws IOException se si verifica un errore durante la conversione in file.
     */
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
