package Chapter_7.Call_Center;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class CallHandler
{
    private final int LEVELS = 3;

    private final int NUM_RESPONDENTS = 10;
    private final int NUM_MANAGERS = 4;
    private final int NUM_DIRECTORS = 2;

    List<List<Employee>> employeeLvls;

    List<List<Call>> callQueues;

    public CallHandler()
    {
        employeeLvls = new ArrayList<>(LEVELS);
        callQueues = new ArrayList<>(LEVELS);

        ArrayList<Employee> respondents = new ArrayList<>(NUM_RESPONDENTS);
        for(int k = 0; k < NUM_RESPONDENTS - 1; k++)
        {
            respondents.add(new Respondent(this));
        }
        employeeLvls.add(respondents);

        ArrayList<Employee> managers = new ArrayList<>(NUM_MANAGERS);
        managers.add(new Manager(this));
        employeeLvls.add(managers);

        ArrayList<Employee> directors = new ArrayList<>(NUM_DIRECTORS);
        directors.add(new Director(this));
        employeeLvls.add(directors);
    }

    public Employee getHandlerForCall(Call call)
    {
        for(int lvl = call.getRank().getVal(); lvl < LEVELS - 1; lvl++)
        {
            List<Employee> employeeLevel = employeeLvls.get(lvl);
            for(Employee emp : employeeLevel)
            {
                if(emp.isFree())
                {
                    return emp;
                }
            }
        }
        return null;
    }

    public void dispatchCall(Caller caller)
    {
        Call call = new Call(caller);
        dispatchCall(call);
    }

    public void dispatchCall(Call call)
    {
        Employee emp = getHandlerForCall(call);
        if(emp != null)
        {
            emp.receiveCall(call);
            call.setHandler(emp);
        }
        else
        {
            call.reply("Please wait for free employee to reply");
            callQueues.get(call.getRank().getVal()).add(call);
        }
    }

    public boolean assignCall(Employee emp)
    {
        for(int rank = emp.getRank().getVal(); rank >= 0; rank--)
        {
            List<Call> que = callQueues.get(rank);

            //remove first
            if(que.size() > 0)
            {
                Call call = que.remove(0);
                if(call != null)
                {
                    emp.receiveCall(call);
                    return true;
                }
            }
        }
        return false;
    }



}
