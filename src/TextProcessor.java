import java.text.Normalizer;

/**
 * Created by wbekh on 10/06/2016.
 */
public abstract class TextProcessor {

    public String process(final String word)
    {
        // seperate each accent to its letter
        String x = new String(word);
        x = Normalizer.normalize(x, Normalizer.Form.NFD);
        x = x.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
        return x.toLowerCase();
    }

    public static String processSearch(final String word)
    {
        // seperate each accent to its letter
        String x = new String(word);
        x = Normalizer.normalize(x, Normalizer.Form.NFD);
        x = x.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
        return x.toLowerCase();
    }
}
