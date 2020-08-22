import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;

class StackMinTest {

    private StackMin.MinStack s;

    @BeforeEach
    void setUp() {
      s = new StackMin.MinStack();
    }

    @Test
    public void test1()
    {
        assertEquals(true, s.isEmpty());
        s.push(1);
        assertEquals(false, s.isEmpty());
        assertEquals(1, s.getMin());
        assertEquals(1, s.top());
        s.push(2);
        assertEquals(2, s.top());
        assertEquals(1, s.getMin());
        assertEquals(false, s.isEmpty());
        s.push(0);
        assertEquals(0, s.getMin());
        assertEquals(0, s.top());
        assertEquals(0, s.pop());
        assertEquals(2, s.pop());
        assertEquals(1, s.pop());
        assertEquals(true, s.isEmpty());
    }

    @Test
    public void test2()
    {
        assertEquals(true, s.isEmpty());
        s.push(0);
        assertEquals(false, s.isEmpty());
        assertEquals(0, s.top());
        assertEquals(0, s.getMin());
        s.push(1);
        s.push(2);
        assertEquals(false, s.isEmpty());
        assertEquals(2, s.top());
        assertEquals(2, s.pop());
        assertEquals(1, s.pop());
        assertEquals(0, s.pop());
        assertEquals(true, s.isEmpty());
        s.push(2);
        assertEquals(false, s.isEmpty());
        s.push(1);
        assertEquals(false, s.isEmpty());
        assertEquals(1, s.getMin());
        s.push(0);
        assertEquals(false, s.isEmpty());
        assertEquals(0, s.getMin());
        assertEquals(0, s.top());
        assertEquals(0, s.pop());
        assertEquals(1, s.pop());
        assertEquals(2, s.pop());
        assertEquals(true, s.isEmpty());
    }
}