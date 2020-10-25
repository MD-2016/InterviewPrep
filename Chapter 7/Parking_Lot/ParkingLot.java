package Chapter_7.Parking_Lot;

public class ParkingLot
{
    private  Level[] lvls;
    private  final int NUM_LEVELS = 5;

    public ParkingLot()
    {
        lvls = new Level[NUM_LEVELS];
        for(int i = 0; i < NUM_LEVELS; i++)
        {
            lvls[i] = new Level(i,30);
        }
    }

    public boolean parkVehicle(Vehicle v)
    {
        for(int i = 0; i < lvls.length; i++)
        {
            if(lvls[i].parkVehicle(v))
            {
                return true;
            }
        }
        return false;
    }

    public void print()
    {
        for(int i = 0; i < lvls.length; i++)
        {
            System.out.print("Level " + i + ": ");
            lvls[i].print();
            System.out.println("");
        }
        System.out.println("");
    }

}
