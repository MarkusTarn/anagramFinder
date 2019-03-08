import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AnagramMapper {
    public static void main(String[] argv) {
        long startTime = System.nanoTime();
        HashMap<Character, Integer> characterMap = new HashMap<>(30);
        String word = argv[1], result = "";
        int wordLength = word.length();
        for (char c : word.toCharArray()) characterMap.compute(c, (key, value) -> (value == null ? 1 : value + 1));

        try (Stream<String> stream = Files.lines(Paths.get(argv[0]))) {
            result = stream
                    .filter(line -> line.length() == wordLength && !line.equals(word))
                    .filter(line -> contains(line.toCharArray(), new HashMap<>(characterMap)))
                    .collect( Collectors.joining( "," ) );

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println((System.nanoTime() - startTime) / 1000 + "," + result + "," + result.split(",").length);
    }

    private static boolean contains(char[] chars, HashMap<Character, Integer> characterMap) {
        for (char c : chars) characterMap.compute(c, (key, value) -> (value == null ? -1 : value - 1));
        for (int value : characterMap.values()) if (value != 0) return false;
        return true;
    }
}
