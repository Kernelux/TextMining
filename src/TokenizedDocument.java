import javax.xml.soap.Text;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by wbekh on 10/06/2016.
 */
public class TokenizedDocument {
    final ArrayList<String> words;
    final String uri;

    public TokenizedDocument(final ArrayList<String> word, final String uri) {
        this.words = word;
        this.uri = uri;
    }

    public static ArrayList<TokenizedDocument> analyze(final ArrayList<Document> docs, final ArrayList<TextProcessor> processors) {
        // Processed Data
        ArrayList<String> str = new ArrayList<String>();
        ArrayList<TokenizedDocument> td = new ArrayList<>();
        for (Document d : docs) {
            str.add(d.text);

        }
        for (TextProcessor t : processors) {
            str.forEach(s -> t.process(s));
        }
        String[] saux;
        for (int i = 0; i < str.size(); i++) {
            String s = str.get(i);
            saux = s.split("[\\p{Punct}\\s\\n]+");
            TokenizedDocument tokend = new TokenizedDocument(new ArrayList<String>(Arrays.asList(saux)), docs.get(i).uri);
            td.add(i, tokend);
        }
        return td;
    }


}