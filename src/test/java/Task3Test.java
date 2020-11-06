import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.fail;

public class Task3Test extends Task3 {
    @Test
    public void testValidate() {
        expectError(() -> getExpr(""), "SExpr contain two Digits and an Operator and should therefore have a length of 3");
        expectError(() -> getExpr("("), "The SExpr starts with a '(' and should therefore end with a ')'");
        expectError(() -> getExpr(")"), "'(' is only allowed as the first letter after an optional operator\n" +
                "')' is only allowed as the last letter");
        expectError(() -> getExpr("()"), "SExpr contain two Digits and an Operator and should therefore have a length of 3");
        expectError(() -> getExpr("11+1"), "SExpr contain two Digits and an Operator and should therefore have a length of 3");
        expectError(() -> getExpr("123"), "The operator of the SExpr should be a value of [+,-,*,/]");
        expectError(() -> getExpr("-123"), "A '(' should follow a leading + or -");
        expectError(() -> getExpr("-(1+3)+2"), "The SExpr starts with a '(' and should therefore end with a ')'");
    }

    @Test
    public void testGeneration() {
        var expr1 = getExpr("2+3");
        assertThat(expr1.getOp1(), is(Optional.empty()));
        assertThat(expr1.getSexpr().getD1().getValue(), is(2.0));
        assertThat(expr1.getSexpr().getOp(), is(new Op1(Op1.Operand.PLUS)));
        assertThat(expr1.getSexpr().getD2().getValue(), is(3.0));
        assertThat(expr1.calulate(), is(5.0));

        var expr2 = getExpr("2-7");
        assertThat(expr2.getOp1(), is(Optional.empty()));
        assertThat(expr2.getSexpr().getD1().getValue(), is(2.0));
        assertThat(expr2.getSexpr().getOp(), is(new Op1(Op1.Operand.MINUS)));
        assertThat(expr2.getSexpr().getD2().getValue(), is(7.0));
        assertThat(expr2.calulate(), is(-5.0));

        var expr3 = getExpr("9*8");
        assertThat(expr3.getOp1(), is(Optional.empty()));
        assertThat(expr3.getSexpr().getD1().getValue(), is(9.0));
        assertThat(expr3.getSexpr().getOp(), is(new Op2(Op2.Operand.MULTIPLY)));
        assertThat(expr3.getSexpr().getD2().getValue(), is(8.0));
        assertThat(expr3.calulate(), is(72.0));

        var expr4 = getExpr("3/4");
        assertThat(expr4.getOp1(), is(Optional.empty()));
        assertThat(expr4.getSexpr().getD1().getValue(), is(3.0));
        assertThat(expr4.getSexpr().getOp(), is(new Op2(Op2.Operand.DIVIDE)));
        assertThat(expr4.getSexpr().getD2().getValue(), is(4.0));
        assertThat(expr4.calulate(), is(0.75));

        var expr5 = getExpr("(4+0)");
        assertThat(expr5.getOp1(), is(Optional.empty()));
        assertThat(expr5.getSexpr().getD1().getValue(), is(4.0));
        assertThat(expr5.getSexpr().getOp(), is(new Op1(Op1.Operand.PLUS)));
        assertThat(expr5.getSexpr().getD2().getValue(), is(0.0));
        assertThat(expr5.calulate(), is(4.0));

        var expr6 = getExpr("+(5-2)");
        assertThat(expr6.getOp1(), is(Optional.of(new Op1(Op1.Operand.PLUS))));
        assertThat(expr6.getSexpr().getD1().getValue(), is(5.0));
        assertThat(expr6.getSexpr().getOp(), is(new Op1(Op1.Operand.MINUS)));
        assertThat(expr6.getSexpr().getD2().getValue(), is(2.0));
        assertThat(expr6.calulate(), is(3.0));

        var expr7 = getExpr("-(2*4)");
        assertThat(expr7.getOp1(), is(Optional.of(new Op1(Op1.Operand.MINUS))));
        assertThat(expr7.getSexpr().getD1().getValue(), is(2.0));
        assertThat(expr7.getSexpr().getOp(), is(new Op2(Op2.Operand.MULTIPLY)));
        assertThat(expr7.getSexpr().getD2().getValue(), is(4.0));
        assertThat(expr7.calulate(), is(-8.0));
    }


    private void expectError(Runnable r, String message) {
        try {
            r.run();
            fail();
        } catch (RuntimeException e) {
            assertThat(e.getMessage(), is(message));
        }
    }
}