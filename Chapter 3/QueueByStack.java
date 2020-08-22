import java.util.Stack;
import java.util.NoSuchElementException;
public class QueueByStack {

    /*
     * Time is O(1) for enqueue and O(n) for dequeue
     * Space is O(n)
     */
    public static class MyQueue<T> {
        private Stack<T> s1;
        private Stack<T> s2;

        public MyQueue()
        {
            s1 = new Stack<>();
            s2 = new Stack<>();
        }

        private void moveOneStackToOther()
        {
            while(!s1.isEmpty())
            {
                s2.push(s1.pop());
            }
        }

        public boolean isEmpty()
        {
            return s1.isEmpty() && s2.isEmpty();
        }

        public int size()
        {
            return s1.size() + s2.size();
        }

        public T peek()
        {
            if(isEmpty()) throw new NoSuchElementException("Queue underflow error");
            if(s2.isEmpty()) moveOneStackToOther();
            return s2.peek();
        }

        public void enqueue(T elm)
        {
            s1.push(elm);
        }

        public T dequeue()
        {
            if(isEmpty()) throw new NoSuchElementException("Queue underflow error");
            if(s2.isEmpty()) moveOneStackToOther();
            return s2.pop();
        }
    }

    /*
     * Book approach
     */
    public class OtherQueue<T> {
        Stack<T> stackNewest, stackOldest;

        public OtherQueue()
        {
            stackNewest = new Stack<>();
            stackOldest = new Stack<>();
        }

        public int size()
        {
            return stackOldest.size() + stackNewest.size();
        }

        public void add(T value)
        {
            stackNewest.push(value);
        }

        private void shiftStacks()
        {
            if(stackOldest.isEmpty())
            {
                while(!stackNewest.isEmpty())
                {
                    stackOldest.push(stackNewest.pop());
                }
            }
        }

        public T peek()
        {
            shiftStacks();
            return stackOldest.peek();
        }

        public T remove()
        {
            shiftStacks();
            return stackOldest.pop();
        }
    }

    /*
     * Another possible implementation
     * Time is O(n) for enqueue but dequeue is O(1)
     * Space is O(n)
     */
    public static class OppositeQueue<T>{
        Stack<T> s1 = new Stack<>();
        Stack<T> s2 = new Stack<>();

        void enqueue(T x)
        {
            //move s1 to s2
            while(!s1.isEmpty())
            {
                s2.push(s1.pop());
            }

            s1.push(x);

            //push everything back
            while(!s2.isEmpty())
            {
                s1.push(s2.pop());
            }
        }

        T dequeue()
        {
            //first stack empty
            if(s1.isEmpty())
            {
                throw new NoSuchElementException("Queue empty");
            }

            //return top of s1
            T x = s1.peek();
            s1.pop();
            return x;
        }

        public int size()
        {
            return s1.size() + s2.size();
        }

        public boolean isEmpty()
        {
            return s1.isEmpty() && s2.isEmpty();
        }

        T peek()
        {
            //rotateStacks();
            return s1.peek();
        }

        private void rotateStacks()
        {
            if(s2.isEmpty())
            {
                while(!s1.isEmpty())
                {
                    s2.push(s1.pop());
                }
            }
        }

    }

    public static void main(String[] args)
    {

    }

}
