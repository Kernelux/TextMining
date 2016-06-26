import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by wbekh on 10/06/2016.
 */
public class Index implements Serializable {
    final HashMap<String, ArrayList<String>> wordToUris;

    public ArrayList<String> getDocFromWord(final String word)
    {
        return wordToUris.get(word);
    }


    public Index(HashMap<String, ArrayList<String>> wo)
    {
        this.wordToUris = wo;
    }

    public static Index build(final ArrayList<TokenizedDocument> docs)
    {
        HashMap<String, ArrayList<String>> map = new HashMap<>();
        for(TokenizedDocument t : docs)
        {
            for (String str : t.words)
            {
                ArrayList<String> d = map.get(str);
                if (d == null)
                    d = new ArrayList<>();
                d.add(t.uri);

                map.put(str, d);
            }
        }
        return new Index(map);
    }

    public void save(final String path) throws IOException {
        FileOutputStream fout = new FileOutputStream(path);
        ObjectOutputStream oos = new ObjectOutputStream(fout);
        oos.writeObject(this);
        oos.close();
    }
}
