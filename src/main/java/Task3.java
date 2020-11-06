import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Supplier;

public class Task3 {
    public static void main(String[] args) throws IOException {
        var term = readLine();
    }

    protected Expr getExpr(String term) {
        var op1 = Optional.<Op1>empty();
        var currentTerm = term;
        if (term.startsWith("+")) {
            op1 = Optional.of(new Op1(Op1.Operand.PLUS));
            currentTerm = currentTerm.substring(1);
        }
        if (term.startsWith("-")) {
            op1 = Optional.of(new Op1(Op1.Operand.MINUS));
            currentTerm = currentTerm.substring(1);
        }

        if (op1.isPresent() && !currentTerm.startsWith("(")) {
            throw new RuntimeException("A '(' should follow a leading + or -");
        }

        if (currentTerm.startsWith("(")) {
            if (currentTerm.endsWith(")")) {
                currentTerm = currentTerm.substring(1, currentTerm.length()-1);
            } else {
                throw new RuntimeException("The SExpr starts with a '(' " +
                        "and should therefore end with a ')'");
            }
        }  else if (currentTerm.contains("(") || currentTerm.contains(")")) {
            throw new RuntimeException("'(' is only allowed as the first letter after an optional operator\n" +
                    "')' is only allowed as the last letter");
        }
        var sExpr = getSexpr(currentTerm);

        return new Expr(op1, sExpr);
    }

    private SExpr getSexpr(String term) {
        final BiFunction<Character, Supplier<RuntimeException>, Digit> parseDigit = (c, e) -> switch (c) {
            case '0' -> new Digit(0);
            case '1' -> new Digit(1);
            case '2' -> new Digit(2);
            case '3' -> new Digit(3);
            case '4' -> new Digit(4);
            case '5' -> new Digit(5);
            case '6' -> new Digit(6);
            case '7' -> new Digit(7);
            case '8' -> new Digit(8);
            case '9' -> new Digit(9);
            default -> throw e.get();
        };

        BiFunction<Character, Supplier<RuntimeException>, Op> parseOperator = (c, e) -> switch (c) {
            case '+' -> new Op1(Op1.Operand.PLUS);
            case '-' -> new Op1(Op1.Operand.MINUS);
            case '*' -> new Op2(Op2.Operand.MULTIPLY);
            case '/' -> new Op2(Op2.Operand.DIVIDE);
            default -> throw e.get();
        };

        if (term.length() != 3) {
            throw new RuntimeException("SExpr contain two Digits and an Operator " +
                    "and should therefore have a length of 3");
        }

        var d1 = parseDigit.apply(term.charAt(0),
                () -> new RuntimeException("The first digit of the SExpr should be a number between 0 and 9"));
        var op = parseOperator.apply(term.charAt(1),
                () -> new RuntimeException("The operator of the SExpr should be a value of [+,-,*,/]"));
        var d2 = parseDigit.apply(term.charAt(2),
                () -> new RuntimeException("The second digit of the SExpr should be a number between 0 and 9"));

        return new SExpr(d1, d2, op);
    }

    private static String readLine() throws IOException {
        String output = "";
        do {
            output += (char) System.in.read();
        } while (System.in.available() > 1);

        return output.toUpperCase();
    }

    @Getter
    @RequiredArgsConstructor
    protected static class Expr {
        private final Optional<Op1> op1;
        private final SExpr sexpr;

        protected Double calulate() {
            return op1.map(value -> value.calculate(0.0, sexpr.calculate()))
                    .orElseGet(sexpr::calculate);
        }
    }

    @Getter
    @RequiredArgsConstructor
    protected static class SExpr {
        final Digit d1;
        final Digit d2;
        final Op op;

        private Double calculate() {
            return op.calculate(d1, d2);
        }
    }

    protected interface Op {
        default Double calculate(Digit d1, Digit d2) {
            return getMethod().apply(d1.getValue(), d2.getValue());
        }

        default Double calculate(Double value1, Double value2) {
            return getMethod().apply(value1, value2);
        }

        BiFunction<Double, Double, Double> getMethod();
    }

    @Getter
    @EqualsAndHashCode
    @RequiredArgsConstructor
    protected static class Op1 implements Op {
        @Getter
        @RequiredArgsConstructor
        enum Operand {
            PLUS((value1, value2) -> value1 + value2), MINUS((value1, value2) -> value1 - value2);
            final BiFunction<Double, Double, Double> method;
        }

        private final Operand op;

        @Override
        public BiFunction<Double, Double, Double> getMethod() {
            return op.getMethod();
        }
    }

    @Getter
    @EqualsAndHashCode
    @RequiredArgsConstructor
    protected static class Op2 implements Op {
        @Getter
        @RequiredArgsConstructor
        enum Operand {
            MULTIPLY((value1, value2) -> value1 * value2), DIVIDE((value1, value2) -> value1 / value2);
            final BiFunction<Double, Double, Double> method;
        }

        private final Operand op;

        @Override
        public BiFunction<Double, Double, Double> getMethod() {
            return op.getMethod();
        }
    }

    @Getter
    @RequiredArgsConstructor
    protected static class Digit {
        private final double value;
    }
}
