import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ThreeInOneTest{

    private static ThreeInOne.ThreeStack s;

    @BeforeAll
    public static void setup() {
        System.out.println("Starting Tests");
    }

    @Test
    public void test1() throws Exception {
        this.s = new ThreeInOne.ThreeStack(3,3);
        assertEquals(true, s.isEmpty(0));
        assertEquals(true, s.isEmpty(1));
        assertEquals(true, s.isEmpty(2));
        s.push(1, 0);
        assertEquals(false, s.isEmpty(0));
        s.push(2, 1);
        assertEquals(false, s.isEmpty(1));
        s.push(3,2);
        assertEquals(false, s.isEmpty(2));
        assertEquals(1, s.pop(0));
        assertEquals(2, s.pop(1));
        assertEquals(3, s.pop(2));
        assertEquals(true, s.isEmpty(0));
        assertEquals(true, s.isEmpty(1));
        assertEquals(true, s.isEmpty(2));
    }

    @Test
    public void test2() throws Exception {
        this.s = new ThreeInOne.ThreeStack(1,3);
        assertEquals(true, s.isEmpty(0));
        s.push(0,0);
        assertEquals(false, s.isEmpty(0));
        s.push(1,0);
        assertEquals(false, s.isEmpty(0));
        s.push(3,0);
        assertEquals(false, s.isEmpty(0));
        assertEquals(3, s.pop(0));
        assertEquals(1, s.pop(0));
        assertEquals(0, s.pop(0));
        assertEquals(true, s.isEmpty(0));
    }

}