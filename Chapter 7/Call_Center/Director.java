package Chapter_7.Call_Center;

public class Director extends Employee
{


    public Director(CallHandler callHand)
    {
        super(callHand);
        rank = Rank.Director;
    }
}
