package Chapter_7.Call_Center;

public class Respondent extends Employee {

    public Respondent(CallHandler callHand)
    {
        super(callHand);
        rank = Rank.Responder;
    }

}
