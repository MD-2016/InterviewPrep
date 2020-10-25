package Chapter_7.Chat_Server;

public class PrivateChat extends Conversation
{
    public PrivateChat(User u1, User u2)
    {
        participants.add(u1);
        participants.add(u2);
    }

    public User getOtherParticipant(User primary)
    {
        if(participants.get(0) == primary)
        {
            return participants.get(1);
        }
        else if(participants.get(1) == primary)
        {
            return participants.get(0);
        }
        return null;
    }

}
