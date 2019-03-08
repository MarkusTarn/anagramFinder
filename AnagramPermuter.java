import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AnagramPermuter {
    public static HashMap<String, Boolean> characterMap;
    public static void main(String[] argv) {
        long startTime = System.nanoTime();
        characterMap = new HashMap<>(24);
        String word = argv[1].toLowerCase();
        StringBuilder result = new StringBuilder("");
        int wordLength = word.length(), anagramCount = 0;
        permute(word, 0, wordLength - 1);
        characterMap.remove(word);

        try (Stream<String> stream = Files.lines(Paths.get(argv[0]))) {
            stream.forEach(line -> characterMap.computeIfPresent(line.toLowerCase(), (key, value) -> true));
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (HashMap.Entry<String, Boolean> entry : characterMap.entrySet()) {
            if (entry.getValue()) {
                result.append(",").append(entry.getKey());
                anagramCount++;
            }
        }
        System.out.println((System.nanoTime() - startTime) / 1000 + ""  + result + "," + anagramCount);
    }

    private static void permute(String word, int l, int r)
    {
        if (l == r)
            characterMap.putIfAbsent(word, false);
        else {
            for (int i = l; i <= r; i++) {
                word = swap(word,l,i);
                permute(word, l+1, r);
                word = swap(word,l,i);
            }
        }
    }

    private static String swap(String a, int i, int j) {
        char temp;
        char[] charArray = a.toCharArray();
        temp = charArray[i] ;
        charArray[i] = charArray[j];
        charArray[j] = temp;
        return String.valueOf(charArray);
    }
}