package Chapter_7.Book_Reader;

import java.util.HashMap;

public class UserManager
{
    private HashMap<Integer, User> users;

    public UserManager()
    {
        users = new HashMap<>();
    }

    public boolean addUser(User user)
    {
        if(users.containsKey(user.getId()))
        {
            return false;
        }
        users.put(user.getId(), user);
        return true;
    }

    public boolean remove(User u)
    {
        return remove(u.getId());
    }

    public boolean remove(int id)
    {
        if(users.containsKey(id))
        {
            return false;
        }
        users.remove(id);
        return true;
    }

    public User find(int id)
    {
        return users.get(id);
    }

}
