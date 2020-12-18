import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Exercise03Test {

    @Test
    void shouldBeFalse() {
        assertEquals(false, Exercise03.hasAllVowels("Kentucky"));
        assertEquals(false, Exercise03.hasAllVowels("Chicken"));
    }

    @Test
    void shouldBeCorrectWithDifferentCapitalization() {
        assertEquals(true, Exercise03.hasAllVowels("EducatiOn"));
        assertEquals(true, Exercise03.hasAllVowels("SeqUioA"));
        assertEquals(true, Exercise03.hasAllVowels("prECautIon"));
    }

    @Test
    void shouldBeCorrectWithAllCapitals() {
        assertEquals(true, Exercise03.hasAllVowels("ANEMIOUS"));
        assertEquals(true, Exercise03.hasAllVowels("ANNELIDIOUS"));
    }

    @Test
    void shouldBeCorrectWithLowerCase() {
        assertEquals(true, Exercise03.hasAllVowels("cauliflower"));
        assertEquals(true, Exercise03.hasAllVowels("abstemious"));
        assertEquals(true, Exercise03.hasAllVowels("facetious"));
    }

}
