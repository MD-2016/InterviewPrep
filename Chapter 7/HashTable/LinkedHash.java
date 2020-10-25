package Chapter_7.HashTable;

public class LinkedHash
{
    private  int key;
    private  int val;
    private LinkedHash next;

    LinkedHash(int key, int val)
    {
        this.key = key;
        this.val = val;
        this.next = null;
    }

    public int getValue()
    {
        return val;
    }

    public void setVal(int val)
    {
        this.val = val;
    }

    public int getKey()
    {
        return key;
    }

    public LinkedHash getNext()
    {
        return next;
    }

    public void setNext(LinkedHash next)
    {
        this.next = next;
    }
}
