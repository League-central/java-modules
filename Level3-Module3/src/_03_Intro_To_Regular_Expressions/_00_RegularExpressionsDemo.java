package _03_Intro_To_Regular_Expressions;

public class _00_RegularExpressionsDemo {
    /*
     * Regular expressions in Java are strings that define a search
     * pattern. They are useful for matching multiple letters, characters,
     * and patterns within another string. For example, the replace String
     * method:
     * 
     * replace(char oldChar, char newChar)
     * 
     * This method is useful for replacing a single character everywhere in a
     * string with another character, but what if you wanted to replace
     * multiple letters or letter patterns? You would have to code each
     * possible pattern and call replace() each time. Regular expressions
     * allow multiple character patterns to be applied in a single line. Look
     * at the examples below using the String method replaceAll, which takes
     * a regular expression, represented by a string, as a input variable. 
     * 
     * Reference: http://www.rexegg.com/regex-quickstart.html
     */
    
    public static void main(String[] args) {
        final String testString = "Today, is the day we learn regular expressions!";
        String regEx = "";
        String output = "";
        
        System.out.println(testString);
        
        /*
         * Remove all vowels and exclamation points
         * 
         * Characters put between [ and ] are all matched in the testString
         */
        regEx = "[aei!ou]";
        output = testString.replaceAll(regEx, "");
        System.out.println(output);
        
        /*
         * Remove all non-vowels
         * 
         * The caret ^ indicates matching everything except for the characters
         * between the [ ]
         */
        regEx = "[^aeiou]";
        output = testString.replaceAll(regEx, "");
        System.out.println(output);
        
        /*
         * Replacing a range of letters OR any capital letter with another
         * letter
         * 
         * The dash - can also be used for a range of numbers: [0-9]
         */
        regEx = "[a-cA-Z]";
        output = testString.replaceAll(regEx, "Y");
        System.out.println(output);
        
        /*
         * Replacing words in a string that end in the letter 's' or 'y'
         * 
         * \\b means the next character is not part of the word, which is why
         * the 's' or 'y' in the middle of each word aren't replaced
         */
        regEx = "[sy]\\b";
        output = testString.replaceAll(regEx, "_");
        System.out.println(output);
        
        /*
         * Replace 3 characters if the first character is a vowel, the next
         * character is anything, and the next character is not a vowel or
         * whitespace
         * 
         * '.' represents any character (except a new line),
         * '\\s' represents a whitespace character, so it reads
         * <vowel><any character><not a vowel or space>
         */
        regEx = "[aeiou].[^aeiou\\s]";
        output = testString.replaceAll(regEx, "___");
        System.out.println(output);
        
        /*
         * Replace all characters including and in between ',' and 'x'  
         * 
         * '.' represents any character (except a new line),
         * '+' means match 1 or more characters, so it reads
         * <comma><1 or more characters><x character>
         */
        regEx = "[,].+[x]";
        output = testString.replaceAll(regEx, "_");
        System.out.println(output);
        
        /*
         * Replace a word that has n or more letters
         * 
         * '\\w' says to match a "word character": ASCII letter, digit or
         * underscore
         * '{6,}' says to match 6 or more times, so it matches
         * any words that contain 6 or more letters
         */
        regEx = "[\\w]{6,}";
        output = testString.replaceAll(regEx, "______");
        System.out.println(output);
    }
}
