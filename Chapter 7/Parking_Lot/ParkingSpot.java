package Chapter_7.Parking_Lot;

public class ParkingSpot
{
    private Vehicle veh;
    private  VehicleSize spotSize;
    private int row;
    private int spotNum;
    private Level lvl;

    public ParkingSpot(Level lvl, int r, int n, VehicleSize sz)
    {
        this.lvl = lvl;
        this.row = r;
        this.spotNum = n;
        this.spotSize = sz;
    }

    public boolean isAvailable()
    {
        return veh == null;
    }

    public boolean canFitVehicle(Vehicle v)
    {
        return isAvailable() && v.canFitInSpot(this);
    }

    public boolean park(Vehicle v)
    {
        if(!canFitVehicle(v))
        {
            return false;
        }
        veh = v;
        veh.parkInSpot(this);
        return true;
    }

    public int getRow()
    {
        return row;
    }

    public int getSpotNumber()
    {
        return spotNum;
    }

    public VehicleSize getSize()
    {
        return spotSize;
    }

    public void removeVehicle()
    {
        lvl.spotFreed();
        veh = null;
    }

    public void print()
    {
        if(veh == null)
        {
            if(spotSize == VehicleSize.Compact)
            {
                System.out.print("c");
            }
            else if(spotSize == VehicleSize.Large)
            {
                System.out.print("l");
            }
            else if(spotSize == VehicleSize.Motorcycle)
            {
                System.out.print("m");
            }
        }
        else
        {
            veh.print();
        }
    }

}
