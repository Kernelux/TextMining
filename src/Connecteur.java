
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Created by wbekh on 10/06/2016.
 */
public class Connecteur {
    private static boolean recursive = true;
    public static ArrayList<Document>fetch(String path) throws IOException {

        return Connecteur.fetch(path, true);
    }

    public static ArrayList<Document>fetch(String path, boolean recursive) throws IOException {
        int depth = recursive ? Integer.MAX_VALUE : 1;
        ArrayList<Document> d = new ArrayList<Document>();
        ArrayList<Path> filePath = Files.walk(Paths.get(path), depth).filter(f -> !Files.isDirectory(f)).collect(Collectors.toCollection(ArrayList::new));
        filePath.forEach(p -> {
            try {
                d.add(new Document(new String(Files.readAllBytes(p)), p.toUri().toString()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        return d;
    }
}
