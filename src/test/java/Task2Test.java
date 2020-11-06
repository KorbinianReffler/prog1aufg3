import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.fail;

public class Task2Test extends Task2 {
    @Test
    public void testValidate() {
        expectError(() -> validateTermOrThrow(""), "^\nERROR: should be \"T\" or \"F\"");
        expectError(() -> validateTermOrThrow("&F"), "^\nERROR: should be \"T\" or \"F\"");
        expectError(() -> validateTermOrThrow("T&"), "  ^\nERROR: should be \"T\", \"F\", \"&\", \"|\"");
        expectError(() -> validateTermOrThrow("T&TT"), "  ^\nERROR: should be \"T\", \"F\", \"&\", \"|\"");
        expectError(() -> validateTermOrThrow("TF"), " ^\nERROR: should be \"&\", \"|\" or \"^\"");
        expectError(() -> validateTermOrThrow("T&|F"), "  ^\nERROR: should be \"T\", \"F\", \"&\", \"|\"");
        expectError(() -> validateTermOrThrow("T&&&F"), "  ^\nERROR: should be \"T\" or \"F\"");
        expectError(() -> validateTermOrThrow("T"), " ^\nERROR: should be \"&\", \"|\" or \"^\"");
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