
package parking_system;

public class Vehicle {
    private String vehicleNumber;
    private String Owner_name;
    private String vehicle_type;
    private long Entery_Time;
    
    public Vehicle(String vehicle_Number,String Owner_name,String vehicle_type){
        this.vehicleNumber= vehicleNumber;
        this.Owner_name=Owner_name;
        this.vehicle_type=vehicle_type;
        this.Entery_Time=System.currentTimeMillis();
        
    }
    public String getvehicleNumber(){
        return vehicleNumber;
    }
    public String getOwner_name(){
        return Owner_name;
    }
    public String getvehicle_type(){
        return vehicle_type;
    }
    public long getEntery_Time(){
        return Entery_Time;
    }
    public String ToStrint(){
        return vehicleNumber + " | " + Owner_name + " | " + vehicle_type;
    }
    
    
}
