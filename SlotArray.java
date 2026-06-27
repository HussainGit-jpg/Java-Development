package parking_system;

public class SlotArray {
    private ParkingSlot[] slots;
    private int totalSlots;

    public SlotArray(int totalSlots) {
        this.totalSlots = totalSlots;
        slots = new ParkingSlot[totalSlots];
        for (int i = 0; i < totalSlots; i++)
            slots[i] = new ParkingSlot(i + 1);
    }

    public int findFreeSlot() {
        for (int i = 0; i < totalSlots; i++)
            if (!slots[i].isOccupied())
                return i;
        return -1;
    }

    public boolean parkVehicle(int index, Vehicle v) {
        if (index < 0 || index >= totalSlots)
            return false;
        if (slots[index].isOccupied()) 
            return false;
        slots[index].parkVehicle(v);
        return true;
    }

    public boolean removeVehicle(int index) {
        if (index < 0 || index >= totalSlots) 
            return false;
        if (!slots[index].isOccupied()) 
            return false;
        slots[index].removeVehicle();
        return true;
    }

    public ParkingSlot   getSlot(int index){
        return slots[index]; 
    }
    public ParkingSlot[] getAllSlots(){
        return slots; 
    }
    public int getTotalSlots(){ 
        return totalSlots; 
    }

    public int countFree() {
        int count = 0;
        for (ParkingSlot s : slots) 
            if (!s.isOccupied()) 
                count++;
        return count;
    }

    public int countOccupied() {
        return totalSlots - countFree();
    }
}