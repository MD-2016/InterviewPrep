package Chapter_7.File_System;

public abstract class Entry
{
    protected Directory parent;
    protected long created;
    protected long lastUpdate;
    protected long lastAccess;
    protected String fName;

    public Entry(String n, Directory p)
    {
        fName = n;
        parent = p;
        created = System.currentTimeMillis();
    }

    public boolean delete()
    {
        if(parent == null) {
            return false;
        }

        return parent.deleteEntry(this);
    }

    public abstract int size();

    public String getFullPath()
    {
        if(parent == null)
        {
            return fName;
        }
        else
        {
            return parent.getFullPath() + "/" + fName;
        }
    }

    public long getCreationTime()
    {
        return created;
    }

    public long getLastAccessedTime()
    {
        return lastAccess;
    }

    public long getLastUpdatedTime()
    {
        return lastUpdate;
    }

    public String getName()
    {
        return fName;
    }

}
