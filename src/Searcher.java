import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Created by wbekh on 10/06/2016.
 */
public class Searcher {
    public static Index load(final String path) throws IOException, ClassNotFoundException {
        FileInputStream fin = new FileInputStream(path);
        ObjectInputStream oin = new ObjectInputStream(fin);
        return (Index)oin.readObject();
    }

    public static ArrayList<String> search(Index i, String word)
    {
        return i.getDocFromWord(word);
    }


    public static ArrayList<String> search(final Index i, final ArrayList<String> words)
    {
        ArrayList<ArrayList<String>> str = words.stream().map(x -> search(i, x)).collect(Collectors.toCollection(ArrayList<ArrayList<String>>::new));
        ArrayList<String> res = str.get(0);
        str.forEach(res::retainAll);
        return res;
    }
}
