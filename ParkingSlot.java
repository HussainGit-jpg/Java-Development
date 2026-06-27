
package parking_system;


public class ParkingSlot {
    private int slotNumber;
    private boolean isOccupied;
    private Vehicle vehicle;
    
     public ParkingSlot(int slotNumber) {
        this.slotNumber  = slotNumber;
        this.isOccupied  = false;
        this.vehicle     = null;
    }
     public void parkVehicle(Vehicle v) {
        this.vehicle    = v;
        this.isOccupied = true;
    }
     public void removeVehicle() {
        this.vehicle    = null;
        this.isOccupied = false;
    }
     public int getSlotNumber(){
         return slotNumber;
    }
    public boolean isOccupied(){
        return isOccupied; 
    }
    public Vehicle getVehicle(){
        return vehicle; 
    }
      public String toString() {
        if (isOccupied)
            return "Slot " + slotNumber + " → " + vehicle.getvehicleNumber();
        return "Slot " + slotNumber + " → FREE";
    }
}
