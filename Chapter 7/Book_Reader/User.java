package Chapter_7.Book_Reader;

public class User
{
    private int userID;
    private String name;
    private String details;

    public void renewMembership()
    {

    }

    public User(int id, String details, String name)
    {
        this.userID = id;
        this.details = details;
        this.name = name;
    }

    public int getId()
    {
        return userID;
    }

    public void setId(int id)
    {
        userID = id;
    }

    public String getDetails()
    {
        return details;
    }

    public void setDetails(String details)
    {
        this.details = details;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

}
