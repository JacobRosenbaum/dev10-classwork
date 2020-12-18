import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Exercise04Test {
    // doubles are notoriously hard to test because they use floating-point rounding.
    // The third argument in `assertEquals` is a delta. It represents the margin of error for equality.
    // As long as the expected and actual differ by less than the delta, the test passes.

    @Test
    void between1And15() {
        Exercise04 instance = new Exercise04();
        assertEquals(1.25, instance.calculateTotalCost(0.25, 5), 0.001);
    }

    @Test
    void between16And25() {
        Exercise04 instance = new Exercise04();
        assertEquals(25.3365, instance.calculateTotalCost(1.27, 21), 0.001);
    }

    @Test
    void between26And50() {
        Exercise04 instance = new Exercise04();
        assertEquals(89.577, instance.calculateTotalCost(2.69, 37), 0.001);
    }

    @Test
    void between51And75() {
        Exercise04 instance = new Exercise04();
        assertEquals(168.3, instance.calculateTotalCost(3.00, 66), 0.001);
    }

    @Test
    void over75() {
        Exercise04 instance = new Exercise04();
        assertEquals(143.6994, instance.calculateTotalCost(2.07, 89), 0.001);
    }

    @Test
    void priceIsLessThan0() {
        Exercise04 instance = new Exercise04();
        assertEquals(0, instance.calculateTotalCost(-2.07, 89), 0.001);
    }

    @Test
    void quantityIsLessThan0() {
        Exercise04 instance = new Exercise04();
        assertEquals(0, instance.calculateTotalCost(2.07, -89), 0.001);
    }


}