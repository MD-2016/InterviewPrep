package Chapter_7.Jukebox;

public class User
{
    private String name;
    public String getName()
    {
        return name;
    }

    public void setName(String n)
    {
        this.name = n;
    }

    private long ID;

    public void setID(long id)
    {
        this.ID = id;
    }

    public long getID()
    {
        return ID;
    }

    public User(String name, long ID)
    {
        this.name = name;
        ID = ID;
    }

    public User getUser()
    {
        return this;
    }

    public static User addUser(String name, long ID)
    {
        return new User(name,ID);
    }


}
