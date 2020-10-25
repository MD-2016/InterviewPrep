package Chapter_7.Parking_Lot;

public class Level
{
    private int floor;
    private ParkingSpot[] spots;
    private int availableSpots = 0;
    private static final int SPOTS_PER_ROW = 10;

    public Level(int flr, int numSpots)
    {
        floor = flr;
        spots = new ParkingSpot[numSpots];
        int largeSpots = numSpots / 4;
        int bikeSpots = numSpots / 4;
        int compactSpots = numSpots - largeSpots - bikeSpots;
        for(int i = 0; i < numSpots; i++)
        {
            VehicleSize sz = VehicleSize.Motorcycle;
            if(i < largeSpots)
            {
                sz = VehicleSize.Large;
            }
            else if(i < largeSpots + compactSpots)
            {
                sz = VehicleSize.Compact;
            }
            int row = i / SPOTS_PER_ROW;
            spots[i] = new ParkingSpot(this, row, i, sz);
        }
        availableSpots = numSpots;
    }

    public int availableSpots()
    {
        return availableSpots;
    }

    public boolean parkVehicle(Vehicle v)
    {
        if(availableSpots() < v.getSpotsNeeded())
        {
            return false;
        }
        int spotNum = findAvailableSpots(v);
        if(spotNum < 0)
        {
            return false;
        }
        return parkStartingAtSpot(spotNum, v);
    }

    private boolean parkStartingAtSpot(int spotNum, Vehicle v)
    {
        v.clearSpots();
        boolean success = true;
        for(int i = spotNum; i < spotNum + v.spotsNeeded; i++)
        {
            success &= spots[i].park(v);
        }
        availableSpots -= v.spotsNeeded;
        return success;
    }

    private int findAvailableSpots(Vehicle v)
    {
        int spotsNeeded = v.getSpotsNeeded();
        int lastRow = -1;
        int spotsFound = 0;
        for(int i = 0; i < spots.length; i++)
        {
            ParkingSpot spot = spots[i];
            if(lastRow != spot.getRow())
            {
                spotsFound = 0;
                lastRow = spot.getRow();
            }
            if(spot.canFitVehicle(v))
            {
                spotsFound++;
            }
            else
            {
                spotsFound = 0;
            }
            if(spotsFound == spotsNeeded)
            {
                return i - (spotsNeeded - 1);
            }
        }
        return -1;
    }

    public void print()
    {
        int lastRow = -1;
        for(int i = 0; i < spots.length; i++)
        {
            ParkingSpot ps = spots[i];
            if(ps.getRow() != lastRow)
            {
                System.out.print(" ");
                lastRow = ps.getRow();
            }
            ps.print();
        }
    }

    public void spotFreed()
    {
        availableSpots++;
    }

}
