import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Exercise02Test {

    @Test
    void shouldBeCorrectFormat() {
        assertEquals("<b>a</b>", Exercise02.surroundWithTag("a", "b"));
        assertEquals("<span>Squidward Tentacles</span>", Exercise02.surroundWithTag("Squidward Tentacles", "span"));

    }

    @Test
    void shouldBeJustText() {
        assertEquals("splendid", Exercise02.surroundWithTag("splendid", null));
        assertEquals("yessir, let's ride", Exercise02.surroundWithTag("yessir, let's ride", ""));

    }

    @Test
    void shouldBeNull() {
        assertEquals(null, Exercise02.surroundWithTag(null, null));
    }

    @Test
    void shouldBeJustTags() {
        assertEquals("<a></a>", Exercise02.surroundWithTag(null, "a"));
    }


}