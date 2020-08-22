import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StackOfPlatesTest {

    StackOfPlates.SetOfStacks s;


    @BeforeEach
    void setUp() {
       s = new StackOfPlates.SetOfStacks(5);
    }

    @Test
    void test1()
    {
        assertEquals(true, s.heap.size() == 0);
        assertEquals(true, s.list.size() == 0);
        for(int i = 0; i < 34; i++)
        {
            s.push(i);
        }
        assertEquals(false, s.list.size() == 0);
        for(int i = 0; i < 35; i++)
        {
             int x = s.pop();
        }
        assertEquals(true, s.heap.size() == 0);
        assertEquals(true, s.list.size() == 0);
    }

}