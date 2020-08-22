import java.util.*;

public class StackOfPlates {

    /*
     * Assumption: Integer type
     * Time O(1) operations overall is O(n)
     * Space O(n)
     */
    public static class SetOfStacks{
        List<Stack<Integer>> list;
        PriorityQueue<Integer> heap;
        int capacity;

        public SetOfStacks(int cap)
        {
            capacity = cap;
            heap = new PriorityQueue<>();
            list = new ArrayList<>();
        }

        public void push(int val)
        {
            if(!heap.isEmpty() && heap.peek() >= list.size())
            {
                heap.clear();
            }
            if(heap.isEmpty())
            {
                if(list.size() == 0 || list.get(list.size() - 1).size() == capacity)
                {
                    list.add(new Stack<>());
                }
                list.get(list.size() - 1).push(val);
            }
            else
            {
                list.get(heap.poll()).add(val);
            }
        }

        public int pop()
        {
            while(list.size() > 0 && list.get(list.size() - 1).size() == 0)
            {
                list.remove(list.size() - 1);
            }
            if(list.size() == 0)
            {
                return -1;
            }

            return list.get(list.size() - 1).pop();
        }

        public int popAt(int index)
        {
            if(index >= list.size())
            {
                return -1;
            }
            if(list.get(index).size() == 0)
            {
                return -1;
            }
            heap.add(index);
            return list.get(index).pop();
        }

    }

    /*
     * Book idea
     *
     */
    public class StackSet {
        ArrayList<Stack2> stacks = new ArrayList<>();
        public int capacity;
        public StackSet(int cap)
        {
            this.capacity = cap;
        }

        public Stack2 getLastStack()
        {
            if(stacks.size() == 0) return null;
            return stacks.get(stacks.size() - 1);
        }

        public void push(int v)
        {
            Stack2 last = getLastStack();
            if(last != null && !last.isFull())
            {
                last.push(v);
            }
            else
            {
                Stack2 s = new Stack2(capacity);
                s.push(v);
                stacks.add(s);
            }
        }

        public int pop()
        {
            Stack2 last = getLastStack();
            if(last == null) throw new EmptyStackException();
            int v = last.pop();
            if(last.size == 0) stacks.remove(stacks.size() - 1);
            return v;
        }

        public boolean isEmpty()
        {
            Stack2 last = getLastStack();
            return last == null || last.isEmpty();
        }

        public int popAt(int index)
        {
            return leftShift(index, true);
        }

        public int leftShift(int index, boolean removeTop)
        {
            Stack2 s = stacks.get(index);
            int removed;
            if(removeTop) removed = s.pop();
            else removed = s.removeBottom();
            if(s.isEmpty())
            {
                stacks.remove(index);
            }
            else if(stacks.size() > index + 1)
            {
                int v = leftShift(index + 1, false);
                s.push(v);
            }
            return removed;
        }


    }

    public class Stack2 {
        private int capacity;
        public Node top;
        public Node bottom;
        public int size = 0;

        public Stack2(int capacity) {
            this.capacity = capacity;
        }

        public boolean isFull() {
            return capacity == size;
        }

        public void join(Node above, Node below) {
            if (below != null) below.above = above;
            if (above != null) above.below = below;
        }

        public boolean push(int v) {
            if (size >= capacity) return false;
            size++;
            Node n = new Node(v);
            if (size == 1) bottom = n;
            join(n, top);
            top = n;
            return true;
        }

        public int pop() {
            if (top == null) throw new EmptyStackException();
            Node t = top;
            top = top.below;
            size--;
            return t.value;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public int removeBottom() {
            Node b = bottom;
            bottom = bottom.above;
            if (bottom != null) bottom.below = null;
            size--;
            return b.value;
        }
    }

    public class Node {
        public Node above;
        public Node below;
        public int value;
        public Node(int value) {
            this.value = value;
        }
    }

    public static void main(String[] args)
    {

    }

}
