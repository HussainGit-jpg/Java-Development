
package parking_system;

public class VehicleBST {
    private static class BSTNode {
        Vehicle vehicle;
        BSTNode left, right;
        
        BSTNode(Vehicle v){
            this.vehicle = v; 
        }
    }
    private BSTNode root;
    
    public void inser(Vehicle v){
        root = insertRec(root,v);
    }
    private BSTNode insertRec(BSTNode node, Vehicle v) {
        if (node == null)
            return new BSTNode(v);
        int cmp = v.getvehicleNumber().compareTo(node.vehicle.getvehicleNumber());
        if(cmp < 0) 
            node.left = insertRec(node.left, v);
        else if(cmp > 0) 
            node.right = insertRec(node.right, v);
        return node;
    }
    public Vehicle search(String vehicleNumber) {
        return searchRec(root, vehicleNumber);
    }
    private Vehicle searchRec(BSTNode node, String num) {
        if (node == null) return null;
        int cmp = num.compareTo(node.vehicle.getvehicleNumber());
        if(cmp == 0) 
            return node.vehicle;
        else if (cmp < 0)  
            return searchRec(node.left, num);
        else               
            return searchRec(node.right, num);
    }
    public void delete(String vehicleNumber) {
        root = deleteRec(root, vehicleNumber);
    }

    private BSTNode deleteRec(BSTNode node, String num) {
        if (node == null) 
            return null;
        int cmp = num.compareTo(node.vehicle.getvehicleNumber());
        if      (cmp < 0) node.left  = deleteRec(node.left, num);
        else if (cmp > 0) node.right = deleteRec(node.right, num);
        else {
            if (node.left == null) 
                return node.right;
            if (node.right == null)
                return node.left;
            BSTNode min = findMin(node.right);
            node.vehicle = min.vehicle;
            node.right   = deleteRec(node.right, min.vehicle.getvehicleNumber());
        }
        return node;
    }
    private BSTNode findMin(BSTNode node) {
        while (node.left != null) 
            node = node.left;
        return node;
    }

}
