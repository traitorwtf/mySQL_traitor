import JUnit.JUnitMethod;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by traitorwtf on 03.05.2017.
 */

public class JUnitTest {

    @Test
    public void testing(){
        int result = JUnitMethod.multiplication(4,5);
        int expected = 10;

        assertEquals(result, expected);
    }

}
