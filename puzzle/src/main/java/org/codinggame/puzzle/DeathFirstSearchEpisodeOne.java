import java.util.*;
import java.util.stream.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Player {

  public static class Node {
    private int id;
    private boolean visited = false;

    public Node(int i){
      this.id = i;
    }

    int getId() {
      return this.id;
    }
    boolean isVisited() {
      return this.visited;
    }

    public void setVisited(boolean visited){
      this.visited = visited;
    }

    @Override
    public boolean equals(Object obj){


      if(obj == null) return false;

      //System.err.println("Equals:"+ this.id +" obj "+((Node)obj).id );

      return id == ((Node)obj).id;
    }

    @Override
    public String toString() {
      return new String("( id:"+this.id +", visited:"+this.visited+")");
    }

  }

  public static class Link {

    private Node n1;
    private Node n2;

    public Link(Node n1, Node n2) {
      this.n1 = n1;
      this.n2 = n2;
    }

    public Node getN1() {
      return this.n1;
    }
    public Node getN2() {
      return this.n2;
    }
    @Override
    public String toString() {
      return new String("("+this.n1 +","+this.n2+")");
    }
  }

  public static void main(String args[]) {
    Scanner in = new Scanner(System.in);
    int N = in.nextInt(); // the total number of nodes in the level, including the gateways
    int L = in.nextInt()+1; // the number of links
    int E = in.nextInt(); // the number of exit gateways.

    System.err.println("L:"+ L);

       /* List<Link> links = new ArrayList<Link>();
        List<Node> nodes = new ArrayList<Node>();
        for (int i = 0; i < L; i++) {
            int N1 = in.nextInt(); // N1 and N2 defines a link between these nodes
            int N2 = in.nextInt();

            Node n1 = new Node(N1);
            Node n2 = new Node(N2);

            if(!nodes.contains(n1)) nodes.add(n1);
            if(!nodes.contains(n2)) nodes.add(n2);

            if(n1 != n2) links.add(new Link(n1,n2));          
        }*/





    // System.err.println("Links:"+ links);
    //System.err.println("Nodes:"+ nodes);
    //System.err.println("Gateways:"+ gateways);
    //System.err.println("Bobnet pos:"+ SI);
    // System.err.println("BobnetLinks:"+ bobNetLinks);

    ArrayList<ArrayList<Integer>> adj =
            new ArrayList<ArrayList<Integer>>(L);

    for (int i = 0; i < L; i++) {
      adj.add(new ArrayList<Integer>());
    }

    for (int i = 0; i < L-1; i++) {
      int N1 = in.nextInt(); // N1 and N2 defines a link between these nodes
      int N2 = in.nextInt();

      System.err.println("Edge to add:"+ adj+" N1:"+N1+" N2:"+N2);

      addEdge(adj, N1, N2);
      System.err.println("Edges:"+ adj);

    }


    List<Integer> gateways = new ArrayList<Integer>();

    for (int i = 0; i < E; i++) {
      int EI = in.nextInt(); // the index of a gateway node
      gateways.add(EI);
    }

    System.err.println("Gateways:"+ gateways);

    // game loop
    while (true) {
      int SI = in.nextInt(); // The index of the node on which the Bobnet agent is positioned this turn



      System.err.println("SI: "+ SI);

      gateways.forEach(g -> Player.printShortestDistance(adj, SI, g, L));
      //System.err.println("NODES: "+ nodes);
      //nodes.forEach(n -> n.setVisited(false));
      //Node bobInitialNode = nodes.stream().filter( n -> n.getId() == SI).findFirst().get();

      //System.out.println( bobInitialNode.getId()+" "+nodes.stream().filter( n -> n.getId() != SI).findFirst().get().getId());
      //     cutDeeperLink(bobInitialNode, links, gateways);
      //  nodes.stream().filter(g -> gateways.contains(g)).forEach(b ->cutDeeperLink(b, links, gateways));
      // nodes.stream().forEach(b ->cutDeeperLink(b, links, gateways));
      // cutGatewayLink(bobInitialNode, links, gateways);

    }

  }

  // function to form edge between two vertices
  // source and dest
  private static void addEdge(ArrayList<ArrayList<Integer>> adj, int i, int j)
  {
    adj.get(i).add(j);
    adj.get(j).add(i);
  }

  // function to print the shortest distance and path
  // between source vertex and destination vertex
  private static void printShortestDistance(
          ArrayList<ArrayList<Integer>> adj,
          int s, int dest, int v)
  {
    // predecessor[i] array stores predecessor of
    // i and distance array stores distance of i
    // from s
    int pred[] = new int[v];
    int dist[] = new int[v];

    if (BFS(adj, s, dest, v, pred, dist) == false) {
      System.err.println("Given source and destination" +
              "are not connected");
      return;
    }

    // LinkedList to store path
    LinkedList<Integer> path = new LinkedList<Integer>();
    int crawl = dest;
    path.add(crawl);
    while (pred[crawl] != -1) {
      path.add(pred[crawl]);
      crawl = pred[crawl];
    }

    // Print distance
    System.err.println("Shortest path length is: " + dist[dest]);

    // Print path
    System.err.println("Path is ::");

    System.out.println(s+ " "+path.get(path.size() - 2) );
  }

  // a modified version of BFS that stores predecessor
  // of each vertex in array pred
  // and its distance from source in array dist
  private static boolean BFS(ArrayList<ArrayList<Integer>> adj, int src,
                             int dest, int v, int pred[], int dist[])
  {
    // a queue to maintain queue of vertices whose
    // adjacency list is to be scanned as per normal
    // BFS algorithm using LinkedList of Integer type
    LinkedList<Integer> queue = new LinkedList<Integer>();

    // boolean array visited[] which stores the
    // information whether ith vertex is reached
    // at least once in the Breadth first search
    boolean visited[] = new boolean[v];

    // initially all vertices are unvisited
    // so v[i] for all i is false
    // and as no path is yet constructed
    // dist[i] for all i set to infinity
    for (int i = 0; i < v; i++) {
      visited[i] = false;
      dist[i] = Integer.MAX_VALUE;
      pred[i] = -1;
    }

    // now source is first to be visited and
    // distance from source to itself should be 0
    visited[src] = true;
    dist[src] = 0;
    queue.add(src);

    // bfs Algorithm
    while (!queue.isEmpty()) {
      int u = queue.remove();
      for (int i = 0; i < adj.get(u).size(); i++) {
        if (visited[adj.get(u).get(i)] == false) {
          visited[adj.get(u).get(i)] = true;
          dist[adj.get(u).get(i)] = dist[u] + 1;
          pred[adj.get(u).get(i)] = u;
          queue.add(adj.get(u).get(i));

          // stopping condition (when we find
          // our destination)
          if (adj.get(u).get(i) == dest)
            return true;
        }
      }
    }
    return false;
  }






  public static void cutGatewayLink(Node currentBobNode, List<Link> links, List<Node> gateways ) {

    if(!currentBobNode.isVisited()) {
      currentBobNode.setVisited(true);
      List<Link> bobNetLinks= links.stream().filter(l -> l.getN1().getId() == currentBobNode.getId() || l.getN2().getId() == currentBobNode.getId())
              .collect(Collectors.toList());

      List<Node> nodesLinkedToBob =  bobNetLinks.stream()
              .flatMap( l-> Arrays.asList(l.getN1(), l.getN2()).stream() )
              //  .filter( b -> b.getId() != SI.getId())
              .distinct()
              .collect(Collectors.toList());

      System.err.println("Links:"+ links);
      System.err.println("Gateways:"+ gateways);
      System.err.println("Bobnet pos:"+ currentBobNode);
      System.err.println("BobnetLinks:"+ bobNetLinks);
      System.err.println("NodesLinkedToBob:"+ nodesLinkedToBob);

      for(Node nodeLinkedToBob : nodesLinkedToBob) {

        System.err.println("CheckingNode:"+ nodeLinkedToBob);
        if(gateways.contains(nodeLinkedToBob) &&  nodeLinkedToBob != currentBobNode ) {
          System.out.println( nodeLinkedToBob.getId()+" "+currentBobNode.getId());
          break;
        }

        // cutDeeperLink(nodeLinkedToBob,links,gateways);
      }
    }
  }


  public static void cutDeeperLink(Node currentNode, List<Link> links, List<Node> gateways ) {

    if(!currentNode.isVisited()) {
      currentNode.setVisited(true);
      List<Link> bobNetLinks= links.stream().filter(l -> l.getN1().getId() == currentNode.getId() || l.getN2().getId() == currentNode.getId())
              .collect(Collectors.toList());

      List<Node> nodesLinkedToBob =  bobNetLinks.stream()
              .flatMap( l-> Arrays.asList(l.getN1(), l.getN2()).stream() )
              //  .filter( b -> b.getId() != SI.getId())
              .distinct()
              .collect(Collectors.toList());

      System.err.println("Links:"+ links);
      System.err.println("Gateways:"+ gateways);
      System.err.println("Bobnet pos:"+ currentNode);
      System.err.println("BobnetLinks:"+ bobNetLinks);
      System.err.println("NodesLinkedToBob:"+ nodesLinkedToBob);

      for(Node nodeLinkedToBob : nodesLinkedToBob) {

        System.err.println("CheckingNode:"+ nodeLinkedToBob);
        if(gateways.contains(nodeLinkedToBob) &&  nodeLinkedToBob != currentNode ) {
          System.out.println( nodeLinkedToBob.getId()+" "+currentNode.getId());
          break;
        }

        // cutDeeperLink(nodeLinkedToBob,links,gateways);
      }
    }
  }

}