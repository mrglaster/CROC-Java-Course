import java.util.Objects;

/**The matcher class given to us and which we may not to change*/
public class Matcher {

    /**Checks if the string contains some character*/
    public static boolean match(String str, String character) {
        Objects.requireNonNull(character);
        if (character.length() != 1) {
            throw new IllegalArgumentException("Template must have one character");
        }
        long delay = 100;
        // корректная обработка InterruptedException для метода, который не предполагает проброс этого исключения
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return str.contains(character);
    }
}
