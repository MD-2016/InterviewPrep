package Chapter_7.Book_Reader;

public class Book
{
    private int bookID;
    private String details;
    private String title;

    public Book(int id, String details, String title)
    {
        this.bookID = id;
        this.details = details;
        this.title = title;
    }

    public int getId()
    {
        return bookID;
    }

    public void setId(int id)
    {
        bookID = id;
    }

    public String getDetails()
    {
        return details;
    }

    public void setDetails(String details)
    {
        this.details = details;
    }

    public String getTitle()
    {
        return this.title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

}
