package Chapter_7.Chat_Server;

public class GroupChat extends Conversation
{
    public void removeParticipant(User user)
    {
        participants.remove(user);
    }

    public void addParticipant(User user)
    {
        participants.add(user);
    }
}
