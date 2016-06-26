import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Created by wbekh on 25/06/2016.
 */
public class Main {
    static final Scanner reader = new Scanner(System.in);


    private static Index loadIndex(String name)
    {
        try {
            System.out.printf("Chargement de l'index en " + name);
            return Searcher.load(name);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    static private Index initProgram() {
        try {
            String f;
            Integer recur = 0;
            ArrayList<Document> d = null;
            do {
                System.out.println("Entrer votre nom de dossier: ");
                f = reader.next();
                System.out.println("Recherche de document récursive ?: (1: Oui, 0:Non)");
                try {
                    reader.nextLine();
                    recur = reader.nextInt();
                    System.out.println("Récupération des documents ...");
                    reader.nextLine();
                    d = Connecteur.fetch(f, recur == 1 ? true : false);
                } catch (NoSuchElementException | IllegalStateException e) {
                    recur = 2;
                }
                catch (NoSuchFileException e) {
                    System.err.println("Dossier non valide");
                    recur = 2;
                }
            }
            while (recur != 0 && recur != 1);
            ArrayList<TextProcessor> tp = new ArrayList<>();
            tp.add(new textNormalizer());
            System.out.println("Normalisation des documents ...");
            ArrayList<TokenizedDocument> td = TokenizedDocument.analyze(d, tp);
            System.out.println("Création de l'index...");
            Index i = Index.build(td);
            i.save("index");
            System.out.println("Chargements des documents ...");
            Index s = i; //Searcher.load("index");
            return s;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    static String help =
            "--help-- affiche l'aide\n" +
                    "--reinit-- relance l'initialisation du programme\n" +
                    "--load_index-- relance l'initialisation du programme\n" +
                    "--exit-- stop le programme\n";
    private static int option_parser(final String str)
    {
        if (str.equals("--help--")) {

            reader.nextLine();
            System.out.println(help);
            reader.nextLine();
            return 1;
        }
        else if(str.equals("--reinit--")) {
            used_index = initProgram();
            return 1;
        }
        else if(str.equals("--load_index--")) {
            System.out.printf("Chemin vers le nouvel index: ");
            reader.nextLine();
            loadIndex(reader.next());
            return 1;
        }
        else if(str.equals("--exit--"))
            return -1;
        return 0;
    }
    static Index used_index;
    public static void main(String... args) {
        used_index = initProgram();
        System.out.println("Rechercher: (taper --help-- pour voir les options)"); // les options marchent pas super mais pas trop mal. Pas le temps de tweak
        String str;
        ArrayList<String> dummy = new ArrayList<>();
        ArrayList<String> ls;
            while (true) {
                str = reader.next();
                str = TextProcessor.processSearch(str);
                if (option_parser(str) == -1)
                    break;
                else if((option_parser(str) == 1))
                        continue;
                ls = Searcher.search(used_index, str);
                if (ls != null)
                    ls.forEach(System.out::println);
                else
                    ls = dummy;
                System.out.println(new StringBuilder("Il y a ").append(ls.size()).append(" résultats pour ").append(str));
            }


    }
}
