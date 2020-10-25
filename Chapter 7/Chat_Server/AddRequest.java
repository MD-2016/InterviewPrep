package Chapter_7.Chat_Server;

import java.util.Date;

public class AddRequest
{
    private User from;
    private User to;
    private Date date;
    RequestStatus status;

    public AddRequest(User from, User to, Date date)
    {
       this.from = from;
       this.to = to;
       this.date = date;
       status = RequestStatus.Unread;
    }

    public RequestStatus getStatus()
    {
        return status;
    }

    public User getFromUser()
    {
        return from;
    }

    public User getToUser()
    {
        return to;
    }

    public Date getDate()
    {
        return date;
    }

}
