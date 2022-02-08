/**
 * @author Bolshedvorskyi Denys S19374
 */

package zad3;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;

/*<--
 *  niezbędne importy
 */
public class Main {
    public static void main(String[] args) {
        Function<String, List<String>> flines = e -> {
            try {
                List<String> list = new ArrayList<>();
                Scanner sc = new Scanner(new File(e));
                while (sc.hasNextLine()) {
                    list.add(sc.nextLine());
                }
                return list;
            } catch (FileNotFoundException e1) {
                return null;
            }
        };
        Function<List<String>, String> join = e -> {
            StringBuilder s = new StringBuilder();
            for (int i = 1; i < e.size() + 1; i++) {
                if (i % 2 == 0) {
                    s.append(e.get(i - 1)).append(",");
                } else {
                    s.append(e.get(i - 1));
                }
            }
            s.deleteCharAt(s.length()-1);
            return s.toString();
        };
        Function<String, List<Integer>> collectInts = e -> {
            List<Integer> list = new ArrayList<>();
            e = e.replaceAll("[^\\d]", " ");
            e = e.trim();
            e = e.replaceAll(" +", " ");
            String[] s = e.split(" ");
            for (String value : s) {
                list.add(Integer.parseInt(value));
            }
            return list;
        };
        Function<List<Integer>, Integer> sum = e -> {
            int value = 0;
            for (Integer anE : e) value += anE;
            return value;
        };
        /*<--
         *  definicja operacji w postaci lambda-wyrażeń:
         *  - flines - zwraca listę wierszy z pliku tekstowego
         *  - join - łączy napisy z listy (zwraca napis połączonych ze sobą elementów listy napisów)
         *  - collectInts - zwraca listę liczb całkowitych zawartych w napisie
         *  - sum - zwraca sumę elmentów listy liczb całkowitych
         */

        String fname = System.getProperty("user.home") + "/LamComFile.txt";
        InputConverter<String> fileConv = new InputConverter<>(fname);
        List<String> lines = fileConv.convertBy(flines);
        String text = fileConv.convertBy(flines, join);
        List<Integer> ints = fileConv.convertBy(flines, join, collectInts);
        Integer sumints = fileConv.convertBy(flines, join, collectInts, sum);

        System.out.println(lines);
        System.out.println(text);
        System.out.println(ints);
        System.out.println(sumints);

        List<String> arglist = Arrays.asList(args);
        InputConverter<List<String>> slistConv = new InputConverter<>(arglist);
        sumints = slistConv.convertBy(join, collectInts, sum);
        System.out.println(sumints);
    }
}