
package parking_system;
import java.util.ArrayList;
import java.util.List;
import java.util.*;

public class VehicleList {
    public static class Node{
        public Vehicle vehicle;
        public long exitTime;
        public Node next;
        
        public Node(Vehicle v) {
            this.vehicle  = v;
            this.exitTime = System.currentTimeMillis();
            this.next     = null;
        }
    }
    
    private Node head;
    private int size;
   
    public VehicleList(){
        head = null;
        size =0;
    }
    public void AddRecord(Vehicle v){
        Node newNode = new Node(v);
        newNode.next = head;
        head = newNode;
        size++;
    }
    public List<String> getHistoryList(){
        List<String> list = new ArrayList<>();
        Node current = head;
        int count = 1;
        while (current != null) {
            list.add(count++ + ".  " + current.vehicle.toString());
            current = current.next;
        }
        return list;
    }
    
    public int getSize(){
        return size;
    }    
            
}
