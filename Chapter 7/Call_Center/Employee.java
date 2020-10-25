package Chapter_7.Call_Center;

public abstract class Employee
{
    private Call current = null;
    protected Rank rank;
    private CallHandler callHand;

    public Employee(CallHandler handler)
    {
        callHand = handler;
    }

    public void receiveCall(Call call)
    {
        current = call;
    }

    public void callCompleted()
    {
        if(current != null)
        {
            current.disconnect();

            current = null;
        }

        assignNewCall();
    }

    public void escalateAndReassign()
    {
        if(current != null)
        {
            current.incrementRank();
            callHand.dispatchCall(current);

            current = null;
        }

        assignNewCall();
    }

    public boolean assignNewCall()
    {
        if(!isFree())
        {
            return false;
        }
        return callHand.assignCall(this);
    }

    public boolean isFree()
    {
        return current == null;
    }

    public Rank getRank()
    {
        return rank;
    }

}
