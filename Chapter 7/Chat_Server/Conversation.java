package Chapter_7.Chat_Server;

import java.util.ArrayList;

public abstract class Conversation
{
    protected ArrayList<User> participants = new ArrayList<>();
    protected int userID;
    protected ArrayList<Message> mgs = new ArrayList<Message>();

    public ArrayList<Message> getMessages()
    {
        return mgs;
    }

    public boolean addMessage(Message m)
    {
        mgs.add(m);
        return true;
    }

    public int getID()
    {
        return userID;
    }
}
