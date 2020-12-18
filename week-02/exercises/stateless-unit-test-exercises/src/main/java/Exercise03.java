public class Exercise03 {

    // 1. Read the hasAllVowels JavaDocs.
    // 2. Complete the hasAllVowels method.
    // 3. Create tests to fully test hasAllVowels and confirm that it's 100% correct.

    /**
     * Determines if a String contains all English vowels: a, e, i, o, and u.
     * Both uppercase and lowercase vowels are allowed.
     * The `null` value should return false.
     *
     * @param value the string to test
     * @return true if the value contains all 5 vowels, false if it doesn't
     */
    static boolean hasAllVowels(String value) {
        boolean hasA = false;
        boolean hasE = false;
        boolean hasI = false;
        boolean hasO = false;
        boolean hasU = false;

        for (int i = 0; i < value.length(); i++) {
            char letter = value.charAt(i);
            if ((letter == 'a') || (letter == 'A')){
                hasA = true;
            } else if ((letter == 'e') || (letter == 'E')) {
                hasE = true;
            } else if ((letter == 'i') || (letter == 'I')) {
                hasI = true;
            } else if ((letter == 'u') || (letter == 'U')) {
                hasU = true;
            } else if (letter == 'o' || letter == 'O') {
                hasO = true;
            } else {
                continue;
            }
        }
        if (hasA && hasE && hasI && hasO && hasU) {
            return true;
        }

        return false;
    }
}

