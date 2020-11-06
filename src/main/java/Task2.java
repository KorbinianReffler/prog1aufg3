import java.io.IOException;

public class Task2 {
    public static void main(String[] args) throws IOException {
        System.out.println("Please insert a term");
        var term = readLine();
        validateTerm(term); // validates the term

        var op1 = term.charAt(0) == 'T'; // the first character is always the first operand
        var op2 = term.charAt(term.length() - 1) == 'T'; // the last character is always the operand

        switch (term.substring(1, term.length() - 1)) { // crops the operator out of the term
            case "&" -> System.out.printf("%s & %s = %s%n", op1, op2, op1 & op2);
            case "&&" -> System.out.printf("%s && %s = %s%n", op1, op2, op1 && op2);
            case "|" -> System.out.printf("%s | %s = %s%n", op1, op2, op1 | op2);
            case "||" -> System.out.printf("%s || %s = %s%n", op1, op2, op1 || op2);
            case "^" -> System.out.printf("%s ^ %s = %s%n", op1, op2, op1 ^ op2);
        }
    }

    private static String readLine() throws IOException {
        String output = "";
        do {
            output += (char) System.in.read(); // read from the terminal and append to output
        } while (System.in.available() > 1); // stop as soon as an enter was found

        return output.toUpperCase(); // converts the term to uppercase so t/f -> T/F
    }

    private static void validateTerm(String term) {
        try {
            validateTermOrThrow(term);
        } catch (RuntimeException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }
    }

    protected static void validateTermOrThrow(String term) {
        // the term has to start (^) with an F or T (T|F)
        // followed by an operand ((&)|(&&)|(\|)|(\|\|)|(\^))
        // followed by a F or T again (T|F)
        // and no aditional characters ($)
        if (term.matches("^(F|T)((&)|(&&)|(\\|)|(\\|\\|)|(\\^))(F|T)$")) {
            // valid
            return;
        }

        // the first letter was neither F nor T
        if (!term.matches("^(F|T).*")) {
            throw new RuntimeException("^\nERROR: should be \"T\" or \"F\"");
        }

        // the second letter was neither & nor | nor ^
        if (!term.matches("^(F|T)((&)|(\\|)|(\\^)).*")) {
            throw new RuntimeException(" ^\nERROR: should be \"&\", \"|\" or \"^\"");
        }

        // the third letter was neither an & nor | if (started with & or |)
        // and the third letter was no F or T
        if (!term.matches("^(F|T)((&)|(\\|)|(\\^))|(F|T).*")
                || !term.matches("^(F|T)((&&)|(\\|\\|)).*")) {
            throw new RuntimeException("  ^\nERROR: should be \"T\", \"F\", \"&\", \"|\"");
        }

        // the term has || or && as operand but it was not followed by F or T
        if (!term.matches("^(F|T)((&)|(&&)|(\\|)|(\\|\\|)|(\\^))(F|T).*")) {
            throw new RuntimeException("  ^\nERROR: should be \"T\" or \"F\"");
        }

        throw new RuntimeException("Term is to long");
    }
}
