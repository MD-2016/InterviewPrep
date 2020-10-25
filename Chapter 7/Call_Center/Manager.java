package Chapter_7.Call_Center;

public class Manager extends Employee
{


    public Manager(CallHandler callHand)
    {
        super(callHand);
        rank = Rank.Manager;
    }

}
