/**
 * @author Bolshedvorskyi Denys S19374
 */

package zad1;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static int max = 0;

    public static void main(String[] args) throws IOException {
        URL url = new URL("http://wiki.puzzlers.org/pub/wordlists/unixdict.txt");
        BufferedReader bf = new BufferedReader(new InputStreamReader(url.openStream()));
        Map<String, List<String>> mapAnagrams = bf.lines()
                .sorted()
                .collect(Collectors.groupingBy(Main::sort));
        mapAnagrams.forEach((e1, e2) -> maxSize(e2));
        List<List<String>> list = new ArrayList<>();
        for (Map.Entry<String, List<String>> entry : mapAnagrams.entrySet()) {
            if (entry.getValue().size() == max) list.add(entry.getValue());
        }
        list.sort(Comparator.comparing(o -> o.get(0)));
        for (List<String> strings : list) {
            for (int j = 0; j < strings.size(); j++) {
                if (j != strings.size() - 1) {
                    System.out.print(strings.get(j) + " ");
                } else {
                    System.out.print(strings.get(j));
                }
            }
            System.out.println();
        }
    }

    public static String sort(String s) {
        char[] chars = s.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }

    public static void maxSize(List<String> e) {
        if (max < e.size()) max = e.size();
    }
}
