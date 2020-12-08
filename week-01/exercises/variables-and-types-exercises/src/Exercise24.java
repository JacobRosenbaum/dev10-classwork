public class Exercise24 {

    public static void main(String[] args) {
        String knockKnock = "Knack, knack. \n"
                + "Wha's there?\n"
                + "Interrupting caw.\n"
                + "Interrupti...\n"
                + "Maa!";

        // 1. Use the replace method to replace all occurrences of "a" with "o" from knockKnock.
        String replacedKnockKnock =knockKnock.replace('a', 'o');
        // 2. Assign the result to a new variable.
        // 3. Print it.
        System.out.println(replacedKnockKnock);

        // Expected Output:
        // Knock, knock.
        // Who's there?
        // Interrupting cow.
        // Interrupti...
        // Moo!
    }
}
