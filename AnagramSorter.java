import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AnagramSorter {
    public static void main(String[] argv) {
        long startTime = System.nanoTime();
        String word = argv[1].toLowerCase(), result = "";
        int wordLength = word.length();
        char[] wordArray = word.toCharArray();
        Arrays.sort(wordArray);

        try (Stream<String> stream = Files.lines(Paths.get(argv[0]))) {
            result = stream
                    .filter(line -> line.length() == wordLength)
                    .filter(line -> !line.equals(word))
                    .filter(line -> isAnagram(line.toLowerCase().toCharArray(), wordArray))
                    .collect( Collectors.joining( "," ) );

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println((System.nanoTime() - startTime)/1000+","+result+","+result.split(",").length);
    }

    private static boolean isAnagram(char[] chars, char[] wordArray) {
        Arrays.sort(chars);
        return new String(chars).equals(new String(wordArray));
    }
}