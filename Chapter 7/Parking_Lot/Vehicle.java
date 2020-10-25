package Chapter_7.Parking_Lot;

import java.util.ArrayList;

public abstract class Vehicle
{
    protected ArrayList<ParkingSpot> parkingSpots = new ArrayList<>();
    protected String licensePlate;
    protected int spotsNeeded;
    protected VehicleSize size;

    public int getSpotsNeeded()
    {
        return spotsNeeded;
    }

    public VehicleSize getSize()
    {
        return size;
    }

    public void parkInSpot(ParkingSpot sp)
    {
        parkingSpots.add(sp);
    }

    public void clearSpots()
    {
        for(int i = 0; i < parkingSpots.size(); i++)
        {
            parkingSpots.get(i).removeVehicle();
        }
        parkingSpots.clear();
    }

    public abstract boolean canFitInSpot(ParkingSpot sp);
    public abstract void print();
}
