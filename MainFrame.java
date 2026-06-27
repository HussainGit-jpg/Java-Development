
package parking_system;
import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame{
    public static SlotArray         slotArray;
    public static ActionStack       actionStack;
    public static WaitingQueue      waitingQueue;
    public static VehicleList       historyList;
    public static VehicleBST        vehicleBST;
    public static ParkingGraph      parkingGraph;
    
     public MainFrame() {
        initDSA();
        buildUI();
    }
     private void initDSA() {
        slotArray    = new SlotArray(20);
        actionStack  = new ActionStack(100);
        waitingQueue = new WaitingQueue(10);
        historyList  = new VehicleList();
        vehicleBST   = new VehicleBST();

        String[] zones = {"Entry", "Zone A", "Zone B", "Zone C", "Exit"};
        parkingGraph   = new ParkingGraph(5, zones);
        parkingGraph.addEdge(0, 1);
        parkingGraph.addEdge(0, 2);
        parkingGraph.addEdge(1, 3);
        parkingGraph.addEdge(2, 3);
        parkingGraph.addEdge(3, 4);
    }
     private void buildUI() {
        setTitle("🚗 Parking Management System");
        setSize(1000, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Header
        JLabel header = new JLabel("  PARKING MANAGEMENT SYSTEM", JLabel.LEFT);
        header.setFont(new Font("Arial", Font.BOLD, 20));
        header.setForeground(Color.WHITE);
        header.setOpaque(true);
        header.setBackground(new Color(34, 45, 65));
        header.setPreferredSize(new Dimension(1000, 50));

        // Tabs
        JTabbedPane tabs = new JTabbedPane();
        tabs.setFont(new Font("Arial", Font.BOLD, 13));
        tabs.addTab(" Dashboard",  new DashboardPanel());
        tabs.addTab(" Entry/Exit", new EntryPanel());
        tabs.addTab(" Search",     new SearchPanel());
        tabs.addTab(" Reports",    new ReportPanel());

        add(header, BorderLayout.NORTH);
        add(tabs,   BorderLayout.CENTER);

        setVisible(true);
    }

    public static class actionStack {

        public actionStack() {
        }
    }

    static class historyList {

        public historyList() {
        }
    }

    static class SlotArray {

        static ParkingSlot[] getAllSlots() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        public SlotArray(int par) {
        }

        String countFree() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        String countOccupied() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }
    }
}
