
package parking_system;
import java.util.*;

public class ParkingGraph {
    private int vertices;
    private LinkedList<Integer>[] adjList;
    private String[] zoneNames;

   
    public ParkingGraph(int vertices, String[] zoneNames) {
        this.vertices  = vertices;
        this.zoneNames = zoneNames;
        adjList = new LinkedList[vertices];
        for (int i = 0; i < vertices; i++)
            adjList[i] = new LinkedList<>();
    }

    public void addEdge(int src, int dest) {
        adjList[src].add(dest);
        adjList[dest].add(src);
    }

    public List<Integer> shortestPath(int src, int dest) {
        boolean[] visited = new boolean[vertices];
        int[]     parent  = new int[vertices];
        Arrays.fill(parent, -1);

        Queue<Integer> queue = new LinkedList<>();
        visited[src] = true;
        queue.add(src);

        while (!queue.isEmpty()) {
            int node = queue.poll();
            if (node == dest) break;
            for (int neighbor : adjList[node]) {
                if (!visited[neighbor]) {
                    visited[neighbor]  = true;
                    parent[neighbor]   = node;
                    queue.add(neighbor);
                }
            }
        }

        List<Integer> path = new ArrayList<>();
        for (int at = dest; at != -1; at = parent[at])
            path.add(0, at);
        return (path.size() > 0 && path.get(0) == src) ? path : new ArrayList<>();
    }

    public String getZoneName(int i){ 
        return zoneNames[i];
    }
    public int getVertices(){
        return vertices;
    }
}
