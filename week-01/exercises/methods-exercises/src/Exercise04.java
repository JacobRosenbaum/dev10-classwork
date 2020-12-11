public class Exercise04 {

    public static void main(String[] args) {
        System.out.println(getFirstVowel("magnificent")); // Expected: a
        System.out.println(getFirstVowel("winsome")); // Expected: i
        System.out.println(getFirstVowel("xxx")); // Expected:
        System.out.println(getFirstVowel("fantastic")); // Expected a

        // 2. Call getFirstVowel at least one more time.
    }

    // getFirstVowel returns the first vowel in a string as a char.
    // 1. Complete getFirstVowel.
    // If no vowel is found, return 0. (As a char, 0 represents the NULL value.)
    public static char getFirstVowel(String value) {
        char a = 'a';
        char e = 'e';
        char iLetter = 'i';
        char o = 'o';
        char u = 'u';

        for (int i = 0; i < value.length();i ++){
        if (value.length() > 0){
            if(value.charAt(i) == a){
                return (value.charAt(i));
            }
            else if (value.charAt(i) == e) {
                return (value.charAt(i));
            }
            else if (value.charAt(i) == o){
                return (value.charAt(i));
            }
            else if (value.charAt(i) == u){
                return (value.charAt(i));
            }
            else if (value.charAt(i) == iLetter){
                return (value.charAt(i));
            }

        }
        else{
            return 0;
        }
    }
        return 0;

}}
