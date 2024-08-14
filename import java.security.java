import java.security.SecureRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PasswordGenerator {
    private static final String LOWER_CASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPER_CASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String DIGITS = "0123456789";
    private static final String SPECIAL_CHARS = "!@#$%^&*()_+";

    private static final SecureRandom random = new SecureRandom();

    public static String generatePassword(int length, String charSet) {
        if (length <= 0) throw new IllegalArgumentException("Length must be positive");
        return IntStream.range(0, length)
                .map(i -> charSet.charAt(random.nextInt(charSet.length())))
                .mapToObj(c -> String.valueOf((char) c))
                .collect(Collectors.joining());
    }

    public static String getAllowedCharacters(boolean includeLower, boolean includeUpper, boolean includeDigits, boolean includeSpecial) {
        StringBuilder sb = new StringBuilder();
        if (includeLower) sb.append(LOWER_CASE);
        if (includeUpper) sb.append(UPPER_CASE);
        if (includeDigits) sb.append(DIGITS);
        if (includeSpecial) sb.append(SPECIAL_CHARS);
        return sb.toString();
    }
}
2. JUnit5 Test Cases
To test the password generator utility, you can write different JUnit5 test cases. These tests will cover generating passwords with various lengths and character sets.

java
Copy code
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PasswordGeneratorTest {

    @Test
    void testPasswordLength() {
        String charSet = PasswordGenerator.getAllowedCharacters(true, true, true, true);
        assertEquals(8, PasswordGenerator.generatePassword(8, charSet).length());
        assertEquals(12, PasswordGenerator.generatePassword(12, charSet).length());
        assertEquals(20, PasswordGenerator.generatePassword(20, charSet).length());
    }

    @Test
    void testPasswordCharSet() {
        String lowerCaseOnly = PasswordGenerator.getAllowedCharacters(true, false, false, false);
        assertTrue(PasswordGenerator.generatePassword(10, lowercaseOnly).matches("[a-z]+"));

        String digitsOnly = PasswordGenerator.getAllowedCharacters(false, false, true, false);
        assertTrue(PasswordGenerator.generatePassword(10, digitsOnly).matches("[0-9]+"));

        String specialOnly = PasswordGenerator.getAllowedCharacters(false, false, false, true);
        assertTrue(PasswordGenerator.generatePassword(10, specialOnly).matches("[!@#$%^&*()_+]+"));
    }

    @Test
    void testInvalidLength() {
        String charSet = PasswordGenerator.getAllowedCharacters(true, true, true, true);
        assertThrows(IllegalArgumentException.class, () -> PasswordGenerator.generatePassword(0, charSet));
    }
}
