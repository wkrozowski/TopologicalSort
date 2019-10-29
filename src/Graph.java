import java.util.LinkedList;
import java.util.Stack;

public class Graph {
    private int v; //number of vertices
    private LinkedList<Integer> adjacencyList[];

    public Graph(int v) {
        this.v = v;
        adjacencyList = new LinkedList[v];
        for(int i = 0; i<v; i++) {
            adjacencyList[i]=new LinkedList<Integer>();
        }

    }

    public void addEdge(int v, int w) {
        adjacencyList[v].add(w);
    }

    public void topologicalSort() throws Exception {
        Stack stack = new Stack();

        int visited[] = new int[this.v];
        for(int i = 0; i<this.v; i++) {
            visited[i]=0;
        }

        for(int i = 0; i<this.v; i++) {
            if(visited[i]==0) {
                utilityFunction(i, visited, stack);
            }
        }

        while(!stack.empty()) {
            System.out.print(stack.pop() + " ");
        }
        System.out.println();
    }

    private void utilityFunction(int v, int[] visited, Stack stack) throws Exception{
        //If it is permanent mark
        if(visited[v]==2) {
            return;
        }
        //Check for temporary mark
        else if (visited[v]==1) {
            throw new Exception("Loop Detected");
        }
        //Put temporary mark
        visited[v]=1;
        for(Integer i : adjacencyList[v]) {
            utilityFunction(i, visited, stack);
        }
        //put permanent mark
        visited[v]=2;
        stack.push(v);
    }

    public static void main(String [] args) {
        Graph graph = new Graph(6);
        graph.addEdge(5, 2);
        graph.addEdge(5, 0);
        graph.addEdge(4, 0);
        graph.addEdge(4, 1);
        graph.addEdge(2, 3);
        graph.addEdge(3, 1);

        try {
            graph.topologicalSort();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
