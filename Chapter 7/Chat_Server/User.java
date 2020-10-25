package Chapter_7.Chat_Server;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class User
{
    private int userID;
    private UserStatus status = null;
    private HashMap<Integer, PrivateChat> privateChats = new HashMap<>();
    private ArrayList<GroupChat> groupChats = new ArrayList<>();
    private HashMap<Integer, AddRequest> recievedAddRequests = new HashMap<>();
    private HashMap<Integer, AddRequest> sentAddRequests = new HashMap<>();

    private HashMap<Integer, User> contacts = new HashMap<>();
    private String accountName;
    private String fullName;

    public User(int userID, String accountName, String fullName)
    {
        this.accountName = accountName;
        this.fullName = fullName;
        this.userID = userID;
    }

    public boolean sendMessageToUser(User to, String content)
    {
        PrivateChat chat = privateChats.get(to.getID());
        if(chat == null)
        {
            chat = new PrivateChat(this, to);
            privateChats.put(to.getID(), chat);
        }
        Message message = new Message(content, new Date());
        return chat.addMessage(message);
    }

    public boolean sendMessageToGroupChat(int groupID, String content)
    {
        GroupChat chat = groupChats.get(groupID);
        if(chat != null)
        {
            Message message = new Message(content, new Date());
            return chat.addMessage(message);
        }
        return false;
    }

    public void setStatus(UserStatus status)
    {
        this.status = status;
    }

    public UserStatus getStatus()
    {
        return status;
    }

    public boolean addContact(User user)
    {
        if(contacts.containsKey(user.getID()))
        {
            return false;
        }
        else
        {
            contacts.put(user.getID(), user);
            return true;
        }
    }

    public void receivedAddRequest(AddRequest req)
    {
        int senderID = req.getFromUser().getID();
        if(!recievedAddRequests.containsKey(senderID))
        {
            recievedAddRequests.put(senderID, req);
        }
    }

    public void sentAddRequest(AddRequest req)
    {
        int receiverID = req.getFromUser().getID();
        if(!sentAddRequests.containsKey(receiverID))
        {
            sentAddRequests.put(receiverID, req);
        }
    }

    public void removeAddRequest(AddRequest req)
    {
        if(req.getToUser() == this)
        {
            recievedAddRequests.remove(req);
        }
        else if(req.getFromUser() == this)
        {
            sentAddRequests.remove(req);
        }
    }

    public void requestAddUser(String accountName)
    {
        UserManager.getInstance().addUser(this, accountName);
    }

    public void addConversation(PrivateChat conversation)
    {
        User other = conversation.getOtherParticipant(this);
        privateChats.put(other.getID(), conversation);
    }

    public void addConversation(GroupChat conversation)
    {
        groupChats.add(conversation);
    }

    public int getID()
    {
        return userID;
    }

    public String getAccountName()
    {
        return accountName;
    }

    public String getFullName()
    {
        return fullName;
    }

}
