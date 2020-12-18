public class Exercise04 {

    // TESTING AN INSTANCE
    // (testing non-static methods)

    // 1. Read the calculateTotalCost JavaDocs.
    // 2. Review Exercise04Test.
    // 3. Implement calculateTotalCost.
    // 4. Tests are incomplete. Complete them to verify all scenarios.

    /**
     * Calculates the cost to the customer given an item price and the quantity purchased.
     * Negative price or quantity results in 0.0 cost.
     * Volume discounts apply.
     * 1 - 15 items: no discount
     * 16 - 25 items: 5% discount
     * 26 - 50 items: 10% discount
     * 51 - 75 items: 15% discount
     * Greater than 75 items: 22% discount
     *
     * @param price    the price of a single item
     * @param quantity the number of items to purchase
     * @return the total cost with volume discounts applied
     */
    public double calculateTotalCost(double price, int quantity) {

        if ((price < 0) || (quantity < 0)) {
            price = 0;
            return price;
        } else if ((quantity >= 1) && (quantity <= 15)) {
            return price * quantity;
        } else if ((quantity >= 16) && (quantity <= 25)) {
            price = (price * .95) * quantity;
            return price;
        } else if ((quantity >= 26) && (quantity <= 50)) {
            price = (price * .9) * quantity;
            return price;
        } else if ((quantity >= 51) && (quantity <= 75)) {
            price = (price * .85) * quantity;
            return price;
        } else if (quantity > 75) {
            price = (price * .78) * quantity;
            return price;
        }
        return price;
    }
}
