package pne;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CcsrchTest {

    private static final String text_1 = "owwooo 5536914101482853";
    private static final String text_2 = "owwooo 5536 9141 0148 2853";
    private static final String assert_1 = "owwooo 55************53";
    private static final String assert_2 = "owwooo 55** **** **** **53";
    @Test
    void hideCreditCardInformationInText() {
        String output_1 = Ccsrch.HideCreditCardInformationInText(text_1);
        String output_2 = Ccsrch.HideCreditCardInformationInText(text_2);

        Assertions.assertEquals(assert_1, output_1);
        Assertions.assertEquals(assert_2, output_2);
    }
}