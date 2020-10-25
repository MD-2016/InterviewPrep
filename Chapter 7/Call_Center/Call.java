package Chapter_7.Call_Center;

public class Call
{
    private Rank rank;

    private Caller caller;

    private Employee handler;

    public Call(Caller c)
    {
        rank = Rank.Responder;
        caller = c;
    }

    public void setHandler(Employee e)
    {
        handler = e;
    }

    public void reply(String msg)
    {
        System.out.println(msg);
    }

    public Rank getRank()
    {
        return rank;
    }

    public void setRank(Rank r)
    {
        rank = r;
    }

    public Rank incrementRank()
    {
        if(rank == Rank.Responder)
        {
            rank = Rank.Manager;
        }
        else if(rank == Rank.Manager)
        {
            rank = Rank.Director;
        }
        return rank;
    }

    public void disconnect()
    {
        reply("Thank you for calling our center.");
    }
}
