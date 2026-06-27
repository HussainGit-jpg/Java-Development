
package parking_system;


public class ActionStack {
    private String[] Stack;
    private int top;
    private int capacity;  
    
    public ActionStack(int capacity){
        this.capacity = capacity; 
        this.Stack= new String[capacity];
        this.top = -1;
    }
    public void Push(String action){
        if(top < capacity -1){
            Stack[++top]=action;
        }
    }
    public String pop(){
        if(isEmpty()){
            return null;
        }
        return Stack[top--];
    }
    public String peek(){
        if(isEmpty()){
            return null;
        }
        return Stack[top];
    }
    public boolean isEmpty(){
        return top==-1;
    }
    public int getSize(){
        return top+1;
    }
    public String[] getAllAction(){
        String[] result = new String[top+1];
        for(int i =0; i<= top;i++){
            result[i]=Stack[i];
        }
       return result; 
    }
      
}
