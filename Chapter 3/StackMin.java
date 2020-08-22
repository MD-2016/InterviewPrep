import java.util.Stack;

public class StackMin  {

     /*
      * Time Complexity O(1)
      * Space Complexity O(1)
      * Assumption: Type Integer
      */
     public static class MinStack
     {
         Stack<Integer> stack;
         Stack<Integer> minStack;

         public MinStack()
         {
             stack = new Stack<Integer>();
             minStack = new Stack<Integer>();

         }

         public void push(int val)
         {
             stack.push(val);
             if(minStack.isEmpty())
             {
                 minStack.push(val);
             }
             else if ((int) minStack.peek() >= val)
             {
                 minStack.push(val);
             }
         }

         public Integer pop()
         {
             int x = stack.pop();
             if(!minStack.isEmpty() && x == minStack.peek())
             {
                 minStack.pop();
             }
             return x;
         }

         public int top()
         {
             return stack.peek();
         }

         public int getMin()
         {
             return minStack.peek();
         }

         public boolean isEmpty()
         {
             return stack.isEmpty() && minStack.isEmpty();
         }
     }

     /*
      * Book Solution comparison
      * Time is O(1) for operations
      * Space O(1)
      */
    public static class StackWithMin2 extends Stack<Integer> {
        Stack<Integer> s2;
        public StackWithMin2()
        {
            s2 = new Stack<Integer>();
        }

        public void push(int val)
        {
            if (val <= min())
            {
                s2.push(val);
            }
            super.push(val);
        }

        public Integer pop()
        {
            int value = super.pop();
            if(value == min())
            {
                s2.pop();
            }
            return value;
        }

        public int min()
        {
            if(s2.isEmpty())
            {
                return Integer.MAX_VALUE;
            }
            else
            {
                return s2.peek();
            }
        }

     }

     public static void main(String[] args)
     {
     }

}
