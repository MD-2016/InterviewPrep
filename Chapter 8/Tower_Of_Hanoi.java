package Chapter_8;
import java.util.Stack;

public class Tower_Of_Hanoi
{

}


/*
 * My Approach
 * Time: O(2^n)
 * Space: O(n)
 */
class Tower
{
    int index;
    Stack<Integer> disks;

    public Tower(int i)
    {
        disks = new Stack<>();
        index = i;
    }

    public void moveDisks(int diskNum, Tower buffer, Tower dest) throws Exception
    {
        if(diskNum > 0)
        {
            moveDisks(diskNum - 1, dest, buffer);
            moveTo(dest);
            buffer.moveDisks(diskNum - 1, this, dest);
        }
        else
        {
            return;
        }
    }

    private void moveTo(Tower dest) throws Exception
    {
        int t = disks.pop();
        dest.add(t);
        System.out.println("Now moving " + t + "from " + this.index + " to " + dest.index);
    }

    public void add(int val) throws Exception
    {
        if((!disks.isEmpty()) && disks.peek() < val)
        {
            throw new Exception("Error placing disk " + val);
        }
        else
        {
            disks.push(val);
        }
    }

}

/*
 * Book Approach
 * Time: O(2^n)
 * Space: O(n)
 */
class TowerSol
{
    private Stack<Integer> disks = new Stack<Integer>();
    public String name;

    public void add(int d)
    {
        if(!disks.isEmpty() && disks.peek() <= d)
        {
            System.out.println("Error placing disk " + d);
        }
        else
        {
            disks.push(d);
        }
    }

    public void moveTopTo(TowerSol t)
    {
        int top = disks.pop();
        t.add(top);
    }

    public void print()
    {
        System.out.println("Contents of Tower " + name + ": " + disks.toString());
    }

    public void moveDisks(int quantity, TowerSol destination, TowerSol buffer)
    {
        if(quantity <= 0)
        {
            return;
        }
        moveDisks(quantity - 1, buffer, destination);
        System.out.println("Move " + disks.peek() + " from " + this.name + " to " + destination.name);
        moveTopTo(destination);
        buffer.moveDisks(quantity - 1, destination, this);
    }

}

class iterativeTower
{
    class Stack
    {
        int cap;
        int top;
        int array[];
    }

    Stack createStack(int capacity)
    {
        Stack stack = new Stack();
        stack.cap = capacity;
        stack.top = -1;
        stack.array = new int[capacity];
        return stack;
    }

    boolean isFull(Stack s)
    {
        return (s.top == s.cap - 1);
    }

    boolean isEmpty(Stack s)
    {
        return (s.top == -1);
    }

    void push(Stack s, int item)
    {
        if(isFull(s))
        {
            return;
        }
        s.array[++s.top] = item;
    }

    int pop(Stack s)
    {
        if(isEmpty(s))
        {
            return Integer.MIN_VALUE;
        }
        return s.array[s.top--];
    }

    void moveDisksBetweenTwoPoles(Stack start, Stack dest, char s, char d)
    {
        int pole1 = pop(start);
        int pole2 = pop(dest);

        if(pole1 == Integer.MIN_VALUE)
        {
            push(start, pole2);
            moveDisk(d, s, pole2);
        }

        else if(pole2 == Integer.MIN_VALUE)
        {
            push(dest, pole1);
            moveDisk(s, d, pole1);
        }

        else if(pole1 > pole2)
        {
            push(start, pole1);
            push(start, pole2);
            moveDisk(d, s, pole2);
        }

        else
        {
            push(dest, pole2);
            push(start, pole2);
            moveDisk(d, s, pole2);
        }
    }

    void moveDisk(char from, char to, int disk)
    {
        System.out.println("Move the disk " + disk + " from " + from + " to " + to);
    }

    void tohIterative(int numOfDisks, Stack start, Stack aux, Stack dest)
    {
        int i, totalMoves;
        char s = 'S', d = 'D', a = 'A';

        if(numOfDisks % 2 == 0)
        {
            char temp = d;
            d = a;
            a = temp;
        }

        totalMoves = (int) (Math.pow(2, numOfDisks) - 1);

        for(i = numOfDisks; i >= 1; i--)
        {
            push(start, i);
        }

        for(i = 1; i <= totalMoves; i++)
        {
            if(i % 3 == 1)
            {
                moveDisksBetweenTwoPoles(start, dest, s, d);
            }
            else if(i % 3 == 2)
            {
                moveDisksBetweenTwoPoles(start, aux, s, a);
            }
            else if(i % 3 == 0)
            {
                moveDisksBetweenTwoPoles(aux, dest, a, d);
            }
        }
    }
}