import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;

class StackSortTest {

    static Stack<Integer> s;
    static Stack<Integer> s2;
    static Stack<Integer> r1;
    static Stack<Integer> r2;
    static Stack<Integer> sorted;

    @BeforeAll
    static void setUp()
    {
        s = new Stack<>();
        s2 = new Stack<>();
        r1 = new Stack<>();
        sorted = new Stack<>();
    }

    @Test
    void test1()
    {
        assertEquals(true, s.isEmpty());
        assertEquals(0, s.size());
        s.push(10);
        s.push(2);
        s.push(4);
        s.push(8);
        s.push(6);
        s.push(9);
        sorted = StackSort.stackSorter(s);
        r1.push(2);
        r1.push(4);
        r1.push(6);
        r1.push(8);
        r1.push(9);
        r1.push(10);
        assertEquals(r1, sorted);
        assertEquals(r1.size(), sorted.size());
        assertEquals(10, sorted.pop());
        assertEquals(9, sorted.pop());
        assertEquals(8, sorted.pop());
        assertEquals(6, sorted.pop());
        assertEquals(4, sorted.pop());
        assertEquals(2, sorted.pop());
        assertEquals(0, sorted.size());
        assertEquals(true, sorted.isEmpty());
    }

    @Test
    void test2()
    {
        assertEquals(true, s2.isEmpty());
        assertEquals(0, s2.size());
        s2.push(10);
        s2.push(2);
        s2.push(4);
        s2.push(8);
        s2.push(6);
        s2.push(9);
        StackSort.sortStack(s2);
        r1.push(2);
        r1.push(4);
        r1.push(6);
        r1.push(8);
        r1.push(9);
        r1.push(10);
        assertEquals(r1, s2);
        assertEquals(r1.size(), s2.size());
        assertEquals(10, s2.pop());
        assertEquals(9, s2.pop());
        assertEquals(8, s2.pop());
        assertEquals(6, s2.pop());
        assertEquals(4, s2.pop());
        assertEquals(2, s2.pop());
        assertEquals(0, s2.size());
        assertEquals(true, s2.isEmpty());
    }

    @AfterEach
    void tearDown()
    {
        r1.clear();
        s.clear();
        s2.clear();
        sorted.clear();
    }

}