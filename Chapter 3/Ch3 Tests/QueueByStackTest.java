import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QueueByStackTest {

    QueueByStack.MyQueue q;
    QueueByStack.OppositeQueue q2;

    @BeforeEach
    void setUp() {
        q = new QueueByStack.MyQueue<Integer>();
        q2 = new QueueByStack.OppositeQueue<Integer>();
    }

    @Test
    void test1()
    {
        System.out.println("Running: Test series 1");
        assertEquals(true, q.isEmpty());
        assertEquals(0, q.size());
        q.enqueue(1);
        assertEquals(false, q.isEmpty());
        q.enqueue(2);
        q.enqueue(3);
        assertEquals(1, q.peek());
        q.enqueue(4);
        q.enqueue(5);
        assertEquals(5, q.size());
        assertEquals(1, q.dequeue());
        assertEquals(2, q.dequeue());
        assertEquals(3, q.dequeue());
        assertEquals(4, q.dequeue());
        assertEquals(5, q.dequeue());
        assertEquals(0, q.size());
        assertEquals(true, q.isEmpty());
    }

    @Test
    void test2()
    {
        System.out.println("Running: Test Series 2");
        assertEquals(true, q2.isEmpty());
        assertEquals(0, q2.size());
        q2.enqueue(1);
        q2.enqueue(2);
        assertEquals(false, q2.isEmpty());
        assertEquals(1, q2.peek());
        q2.enqueue(3);
        q2.enqueue(4);
        q2.enqueue(5);
        assertEquals(1, q2.peek());
        assertEquals(5, q2.size());
        assertEquals(1, q2.dequeue());
        assertEquals(2, q2.peek());
        assertEquals(2, q2.dequeue());
        assertEquals(3, q2.peek());
        assertEquals(3, q2.dequeue());
        assertEquals(4, q2.peek());
        assertEquals(4, q2.dequeue());
        assertEquals(5, q2.peek());
        assertEquals(5, q2.dequeue());
        assertEquals(0, q2.size());
        assertEquals(true, q2.isEmpty());
    }

    @AfterEach
    void tearDown()
    {
        System.out.println("Running: Tear Down");
        q = null;
        q2 = null;
        assertNull(q);
        assertNull(q2);
    }
}