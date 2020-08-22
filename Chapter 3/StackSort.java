import java.util.Stack;

public class StackSort {

    /*
     * Assumption: Integer type
     * Time is O(n^2) for size of stack of n and two loops
     * Space is O(n) using a temp stack and stack of n
     */
    public static Stack<Integer> stackSorter(Stack<Integer> org)
    {
        Stack<Integer> temp = new Stack<>();

        while(!org.isEmpty())
        {
            int x = org.pop();

            while(!temp.isEmpty() && temp.peek() > x)
            {
                org.push(temp.pop());
            }
            temp.push(x);
        }
        return temp;

    }

    /*
     * Book approach
     */
    public static void sort(Stack<Integer> s)
    {
        Stack<Integer> r = new Stack<>();
        while(!s.isEmpty())
        {
            //insert each element in s in sorted order into r.
            int tmp = s.pop();
            while(!r.isEmpty() && r.peek() > tmp)
            {
                s.push(r.pop());
            }
            r.push(tmp);
        }
        //copy elements from r back to s
        while(!r.isEmpty())
        {
            s.push(r.pop());
        }


    }

    /*
     * Sort stack by recursion
     * Time is O(n^2)
     * Space is O(n)
     */
    static void sortedInsert(Stack<Integer> s, int x)
    {
        //base case: stack empty or newly inserted
        //item is greater than top
        if(s.isEmpty() || x > s.peek())
        {
            s.push(x);
            return;
        }

        //top is greater, remove the top and recurse
        int temp = s.pop();
        sortedInsert(s,x);

        //put back the top item removed earlier
        s.push(temp);

    }

    static void sortStack(Stack<Integer> s)
    {
        //if not empty
        if(!s.isEmpty())
        {
            //remove top item
            int x = s.pop();

            //sort remaining stack
            sortStack(s);

            //push the top back into sorted stack
            sortedInsert(s,x);
        }
    }

    public static void main(String[] args)
    {
    }

}
