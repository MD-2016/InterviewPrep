package Chapter_7.Circular_Array;

import java.util.Iterator;

public class CircularArray
{
    public static class CircularArrayStructure<T> implements Iterable<T>
    {
        private T[] array;
        private int head;

        public CircularArrayStructure(int size)
        {
            array = (T[]) new Object[size];
            head = 0;
        }

        public void rotate(int n)
        {
            head = shift(n);
        }

        public T get(int index)
        {
            if(index < 0 || index >= array.length)
            {
                throw new IllegalArgumentException("Invalid Index");
            }
            return array[shift(index)];
        }

        public void set(int index, T val)
        {
            if(index < 0 || index >= array.length)
            {
                throw new IllegalArgumentException("Invalid Index");
            }
            array[shift(index)] = val;
        }

        private int shift(int index)
        {
            int i = (head + index) % array.length;
            if(i < 0) i += array.length;
            return i;
        }

        @Override
        public Iterator<T> iterator()
        {
            return new CircularArrayIterator<T>();
        }

        public class CircularArrayIterator<I> implements Iterator<I>
        {
            private int current = 0;

            @Override
            public boolean hasNext()
            {
                return current <= array.length - 1;
            }

            @Override
            public I next()
            {
                return (I) get(current++);
            }

            @Override
            public void remove()
            {
                throw new UnsupportedOperationException();
            }
        }


    }
}
