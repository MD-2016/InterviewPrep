package Chapter_7.HashTable;

import java.util.ArrayList;

public class HashedTable
{
    private static int TABLE_SIZE = 128;

    LinkedHash[] table;

    public HashedTable()
    {
        table = new LinkedHash[TABLE_SIZE];
        for(int i = 0; i < TABLE_SIZE; i++)
        {
            table[i] = null;
        }
    }

    public HashedTable(int size)
    {
        TABLE_SIZE = size;
        table = new LinkedHash[TABLE_SIZE];
        for(int i = 0; i < TABLE_SIZE; i++)
        {
            table[i] = null;
        }
    }

    public int get(int key)
    {
        int hash = (key % TABLE_SIZE);
        if(table[hash] == null)
        {
            return -1;
        }
        else
        {
            LinkedHash entry = table[hash];
            while(entry != null && entry.getKey() != key)
            {
                entry = entry.getNext();
            }
            if(entry == null)
            {
                return -1;
            }
            else
            {
                return entry.getValue();
            }
        }
    }

    public void put(int key, int value)
    {
        int hash = (key % TABLE_SIZE);
        if(table[hash] == null)
        {
            table[hash] = new LinkedHash(key, value);
        }
        else
        {
            LinkedHash entry = table[hash];
            while(entry.getNext() != null && entry.getKey() != key)
            {
                entry = entry.getNext();
            }
            if(entry.getKey() == key)
            {
                entry.setVal(value);
            }
            else
            {
                entry.setNext(new LinkedHash(key,value));
            }
        }
    }

    public void remove(int key)
    {
        int hash = (key % TABLE_SIZE);
        if(table[hash] != null)
        {
            LinkedHash prev = null;
            LinkedHash entry = table[hash];
            while(entry.getNext() != null && entry.getKey() != key)
            {
                prev = entry;
                entry = entry.getNext();
            }
            if(entry.getKey() == key)
            {
                if(prev == null)
                {
                    table[hash] = entry.getNext();
                }
                else
                {
                    prev.setNext(entry.getNext());
                }
            }
        }
    }


}
