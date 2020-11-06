import java.io.IOException;

public class Task2 {
    public static void main(String[] args) throws IOException {
        System.out.println("Please insert a term");
        var term = readLine();
        validateTerm(term);

        var op1 = term.charAt(0) == 'T';
        var op2 = term.charAt(term.length()-1) == 'T';

        switch (term.substring(1, term.length() - 1)) {
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
            output += (char) System.in.read();
        } while (System.in.available() > 1);

        return output.toUpperCase();
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
        if (term.matches("^(F|T)((&)|(&&)|(\\|)|(\\|\\|)|(\\^))(F|T)$")) {
            // valid
            return;
        }

        if (!term.matches("^(F|T).*")) {
            throw new RuntimeException("^\nERROR: should be \"T\" or \"F\"");
        }

        if (!term.matches("^(F|T)((&)|(\\|)|(\\^)).*")) {
            throw new RuntimeException(" ^\nERROR: should be \"&\", \"|\" or \"^\"");
        }

        if (!term.matches("^(F|T)((&)|(\\|)|(\\^))|(F|T).*")
                || !term.matches("^(F|T)((&&)|(\\|\\|)).*")) {
            throw new RuntimeException("  ^\nERROR: should be \"T\", \"F\", \"&\", \"|\"");
        }

        if (!term.matches("^(F|T)((&)|(&&)|(\\|)|(\\|\\|)|(\\^))(F|T).*")) {
            throw new RuntimeException("  ^\nERROR: should be \"T\" or \"F\"");
        }

        throw new RuntimeException("Term is to long");
    }
}
