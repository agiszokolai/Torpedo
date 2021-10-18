package torpedo.service.validators;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

    /**
     * Unit tests for {@link MapValidators}
     * */
    public class MapValidatorsTest {

    @Test
    void isValidInputFormat() {
        
        //assertAll használata, hogy több teszt is lefuthasson egyszerre
        Assertions.assertAll(() -> Assertions.assertEquals("A1", "A1"),
                             () -> Assertions.assertEquals("A1-A3", "A1-A3"),
                             () -> Assertions.assertEquals("A2-C2","A2-D2"));
    }

    }
