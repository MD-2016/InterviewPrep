package Chapter_7.Book_Reader;

public class OnlineReaderSystem {
    private Library lib;
    private UserManager um;
    private Display display;
    private Book activeBook;
    private User activeUser;

    public OnlineReaderSystem() {
        um = new UserManager();
        lib = new Library();
        display = new Display();
    }

    public Library getLibrary() {
        return lib;
    }

    public UserManager getUserManager() {
        return um;
    }

    public Display getDisplay() {
        return display;
    }

    public Book getActiveBook() {
        return activeBook;
    }

    public void setActiveBook(Book book) {
        activeBook = book;
        display.displayBook(book);
    }

    public User getActiveUser() {
        return activeUser;
    }

    public void setActiveUser(User user)
    {
        activeUser = user;
        display.displayUser(user);
    }

}
