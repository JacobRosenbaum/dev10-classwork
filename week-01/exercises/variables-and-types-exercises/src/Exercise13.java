public class Exercise13 {

    public static void main(String[] args) {

        int purchaseQuantity = 7;
        boolean hasCoupon = true;

        // 1. Declare a boolean variable hasDiscount.
        boolean hasDiscount = ((purchaseQuantity >= 6) || (hasCoupon));
        // 2. Use an expression with comparison operators to assign its value.
        // hasDiscount is true if purchaseQuantity is greater than or equal to 6 OR hasCoupon is true.
        System.out.println(hasDiscount);
        // 3. Print the result.
        // 4. Change the values of purchaseQuantity and hasCoupon to get both true and false results for hasDiscount
        // in different ways.
    }
}
