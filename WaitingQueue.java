
package parking_system;


public class WaitingQueue {
    private Vehicle[] queu;
    private int front,rear,size,capacity;
    
    
    public WaitingQueue(int capacity) {
        this.capacity = capacity;
        queu = new Vehicle[capacity];
        front = 0; rear = -1; size = 0;
    }
    public boolean Enque(Vehicle v){
        if(isFull()){
            return false;
        }
        rear = (rear+1)%capacity;
        queu[rear]= v;
        return true;
    }
    public Vehicle dequeue() {
        if (isEmpty()) return null;
        Vehicle v = queu[front];
        front = (front+1)%capacity;
        size--;
        return v;
    }
    public Vehicle peek(){ 
        return isEmpty() ? null : queu[front];
    }
    public int getSize(){
        return size; 
    }
    public boolean isEmpty(){
        return size == 0; 
    }
    public boolean isFull(){
        return size == capacity; 
    }
    public Vehicle[] getAllWaiting() {
        Vehicle[] result = new Vehicle[size];
        for (int i = 0; i < size; i++)
            result[i] = queu[(front + i) % capacity];
        return result;
    }
}
