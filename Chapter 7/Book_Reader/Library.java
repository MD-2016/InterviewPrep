package Chapter_7.Book_Reader;

import java.util.HashMap;

public class Library
{
    private HashMap<Integer, Book> books;

    public Library()
    {
        books = new HashMap<>();
    }

    public boolean addBook(int id, String details, String title)
    {
        if(books.containsKey(id))
        {
            return false;
        }
        Book book = new Book(id, details, title);
        books.put(id, book);
        return true;
    }

    public boolean addBook(Book book)
    {
        if(books.containsKey(book.getId()))
        {
            return false;
        }

        books.put(book.getId(), book);
        return true;
    }

    public boolean remove(Book b)
    {
        return remove(b.getId());
    }

    public boolean remove(int id)
    {
        if(!books.containsKey(id))
        {
            return false;
        }
        books.remove(id);
        return true;
    }

    public Book find(int id)
    {
        return books.get(id);
    }

}
