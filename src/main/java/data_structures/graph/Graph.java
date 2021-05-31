package data_structures.graph;
import java.util.ArrayList;
import java.util.List;

public class Graph {
    public List< Node > nodes = null;
    public int length = 0;
    public static int MAX_NODES = 128;

    Graph(int n){
        this.length = n;
        this.nodes = new ArrayList<>();
        this.nodes.add( null );
        for(int i = 1; i <= this.length; i ++) {
            this.nodes.add( new Node(i) );
        }

    }

    Graph(){
        this.nodes = new ArrayList<>();
        this.nodes.add( null );
    }

    public boolean edgeBetween(int i, int j) {
        int n = Integer.max(i, j);

        if(n > Graph.MAX_NODES) {
            System.out.print("Max node numbers reached");
            return false;
        }

        if(n > this.length) {
            while(this.length < n) {
                ++this.length;
                this.nodes.add( new Node(this.length) );
            }
        }
        return this.nodes.get(i).addNeighbor( this.nodes.get(j) ) && this.nodes.get(j).addNeighbor( this.nodes.get(i) );
    }

    public boolean removeEdgeBetween(int i, int j) {
        return this.nodes.get(i).removeNeighbor( this.nodes.get(j) ) && this.nodes.get(j).removeNeighbor( this.nodes.get(i) );
    }

    public boolean isComplete() {
        int sumDegree = 0, n = this.length;
        for(int i = 1; i <= n; i++) {
            sumDegree += this.nodes.get(i).getDegree();
        }
        if( sumDegree == n * ( n - 1 ) )
            return true;
        return false;
    }

    public Node getNode(int index) {
        return this.nodes.get(index);
    }

    public int getNodeDegree(Node n) {
        return n.getDegree();
    }

    public int getNodeDegree(int n) {
        for(Node node : this.nodes) {
            if(node != null) {
                if(node.getNodeNumber() == n)
                    return node.getDegree();
            }
        }
        return -1;
    }

    public List<Node> getNodes(){
        return this.nodes;
    }

    public void cleanNodes() {
        for(int i = 1; i <= this.length; i ++) {
            Node node = this.nodes.get(i);
            if(node.getDegree() == 0) {
                this.removeNode(node);
            }
        }
    }

    // BFS Algorithm
    public Graph findPathBetween(Node start, Node end) {
        List<Node> openList = new ArrayList<>();
        List<Node> closedList = new ArrayList<>();

        for(Node n : this.nodes) {
            if(n != null) {
                n.parent = null;
            }
        }

        openList.add(start);
        start.parent = null;

        while(!openList.isEmpty()) {
            Node q = openList.get(0);
            openList.remove(q);
            closedList.add(q);

            for(int i = 1; i <= q.getDegree(); i++) {
                Node n = q.neighbors.get(i);

                if(!closedList.contains(n)) {
                    closedList.add(n);

                    openList.add(n);
                }
                if(n.parent == null) {
                    n.parent = q;
                }

                if(n == end) {
                    Node e = n;
                    start.parent = null;

                    Graph g = new Graph();
                    int nodeNumber = n.getNodeNumber();

                    while(e != null) {
                        g.edgeBetween(nodeNumber, e.getNodeNumber());
                        nodeNumber = e.getNodeNumber();
                        e = e.parent;
                    }
                    g.cleanNodes();
                    return g;
                }

            }

        }
        System.out.print("Path don't exist");
        return null;
    }
    // BFS Algorithm
    public Graph findPathBetween(int st, int en) {
        List<Node> openList = new ArrayList<>();
        List<Node> closedList = new ArrayList<>();

        for(Node n : this.nodes) {
            if(n != null) {
                n.parent = null;
            }
        }

        Node start = null, end = null;
        for(Node node : this.nodes) {
            if(node != null) {
                if(start == null && st == node.getNodeNumber())
                    start = node;
                if(end == null && en == node.getNodeNumber())
                    end = node;
            }
        }

        if(start == null || end == null) {
            return null;
        }

        openList.add(start);
        start.parent = null;

        while(!openList.isEmpty()) {
            Node q = openList.get(0);
            openList.remove(q);
            closedList.add(q);

            for(int i = 1; i <= q.getDegree(); i++) {
                Node n = q.neighbors.get(i);

                if(!closedList.contains(n)) {
                    closedList.add(n);

                    openList.add(n);
                }
                if(n.parent == null) {
                    n.parent = q;
                }

                if(n == end) {
                    Node e = n;
                    start.parent = null;

                    Graph g = new Graph();
                    int nodeNumber = n.getNodeNumber();

                    while(e != null) {
                        g.edgeBetween(nodeNumber, e.getNodeNumber());
                        nodeNumber = e.getNodeNumber();
                        e = e.parent;
                    }
                    g.cleanNodes();
                    return g;
                }

            }

        }
        System.out.print("Path don't exist");
        return null;
    }

    public void addNode() {
        if(this.length + 1 > Graph.MAX_NODES) {
            return;
        }
        this.nodes.add( new Node(++this.length) );
    }

    public boolean removeNode(Node n) {
        if(n == null)
            return false;
        for(Node node : this.nodes) {
            if(node != null)
                node.removeNeighbor(n);
        }

        if( this.nodes.contains(n) ) {
            n.removeNeighbors();
            return true;
        }
        return false;

    }

    public boolean removeNode(int n) {
        Node nodeToRemove = null;

        for(Node node : this.nodes) {
            if(node != null) {
                if(node.getNodeNumber() == n) {
                    nodeToRemove = node;
                    break;
                }
            }
        }

        if(nodeToRemove == null) {
            return false;
        }

        for(Node node : this.nodes) {
            if(node != null)
                node.removeNeighbor(nodeToRemove);
        }

        if( this.nodes.contains(nodeToRemove) ) {
            nodeToRemove.removeNeighbors();
            return true;
        }
        return false;

    }

    public void showAdjacencyMatrix() {
        int[][] array = new int[this.length + 1][this.length + 1];

        for(Node n : this.nodes) {
            if(n != null) {
                for(Node m : n.getNeighbors()) {
                    if(m != null) {
                        array[n.getNodeNumber()][m.getNodeNumber()] = 1;
                    }
                }
            }
        }
        for(int i = 1; i < array.length; i ++) {
            for(int j = 1; j < array.length; j ++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.print("\n");
        }
    }

    public int[][] getAdjacencyMatrix() {
        int[][] array = new int[this.length + 1][this.length + 1];

        for(Node n : this.nodes) {
            if(n != null) {
                for(Node m : n.getNeighbors()) {
                    if(m != null) {
                        array[n.getNodeNumber()][m.getNodeNumber()] = 1;
                    }
                }
            }
        }
        return array;
    }

    public void showAdjacencyList() {
        for(int i = 1; i <= this.length; i++) {
            Node n = this.nodes.get(i);
            System.out.print(n.getNodeNumber() + " : ");
            n.showNeighbors();
            System.out.print("\n");
        }
    }

    public List<Node> getAdjacencyList() {
        return this.nodes;
    }

    public Graph clone() {
        Graph g = new Graph();
        for(Node n : this.nodes) {
            if(n != null) {
                for(Node m : n.getNeighbors()) {
                    if(m != null) {
                        g.edgeBetween(n.getNodeNumber(), m.getNodeNumber());
                    }
                }
            }
        }
        return g;
    }

}

