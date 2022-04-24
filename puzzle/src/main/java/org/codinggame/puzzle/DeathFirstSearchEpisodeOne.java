package org.codinggame.puzzle;

import java.util.*;
import java.util.stream.*;

public class DeathFirstSearchEpisodeOne {


    public static class Node {
      private int id;
      private boolean visited;

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

        return id == ((Node)obj).id;
      }

      @Override
      public String toString() {
        return new String("( id:"+this.id +", visited:"+this.visited+")");
      }

    }

    public static class Link {

      private int n1;
      private int n2;

      public Link(int n1, int n2) {
        this.n1 = n1;
        this.n2 = n2;
      }

      public int getN1() {
        return this.n1;
      }
      public int getN2() {
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
      int L = in.nextInt(); // the number of links
      int E = in.nextInt(); // the number of exit gateways

      List<Link> links = new ArrayList<Link>();
      List<Node> nodes = new ArrayList<Node>();
      for (int i = 0; i < L; i++) {
        int N1 = in.nextInt(); // N1 and N2 defines a link between these nodes
        int N2 = in.nextInt();

        if(!nodes.contains(new Node(N1))) nodes.add(new Node(N1));
        if(!nodes.contains(new Node(N2))) nodes.add(new Node(N2));

        links.add(new Link(N1,N2));
      }

      List<Integer> gateways = new ArrayList<Integer>();
      for (int i = 0; i < E; i++) {
        int EI = in.nextInt(); // the index of a gateway node
        gateways.add(EI);
      }

      System.err.println("Links:"+ links);
      System.err.println("Nodes:"+ nodes);
      System.err.println("Gateways:"+ gateways);
      //System.err.println("Bobnet pos:"+ SI);
      // System.err.println("BobnetLinks:"+ bobNetLinks);

      // game loop
      while (true) {
        int SI = in.nextInt(); // The index of the node on which the Bobnet agent is positioned this turn

        // Write an action using System.out.println()
        // To debug: System.err.println("Debug messages...");

        List<Link> bobNetLinks= links.stream().filter(l -> l.getN1() == SI || l.getN2() == SI).collect(Collectors.toList());

        List<Integer> nodesLinkedToBob =  bobNetLinks.stream()
            .flatMap( l-> Arrays.asList(l.getN1(), l.getN2()).stream() )
            .distinct()
            .collect(Collectors.toList());

        for(int node : nodesLinkedToBob) {

        }

        for(Link nodeLink : bobNetLinks) {


          if( gateways.contains(nodeLink.getN1()) || gateways.contains(nodeLink.getN2())  ) {
            //found = true;
            // Example: 0 1 are the indices of the nodes you wish to sever the link between
            System.out.println( nodeLink.getN1()+" "+nodeLink.getN2());
            break;
          }

        }

            /*List<Integer> nodesLinkedToBob = new ArrayList<Integer>();

            System.err.println("Links:"+ links);
            System.err.println("Gateways:"+ gateways);
            System.err.println("Bobnet pos:"+ SI);
            System.err.println("BobnetLinks:"+ bobNetLinks);

            boolean found= false;



             findGatewayLink(links, gateways, nodesLinkedToBob);*/


      }

    }


    public static void findGatewayLink(List<Link> links, List<Integer> gateways, List<Integer> nodesLinkedToBob) {

      for(int nb: nodesLinkedToBob) {

        List<Link> nodeLinks= links.stream().filter(l -> l.getN1() == nb || l.getN2() == nb).collect(Collectors.toList());

        for(Link nodeLink: nodeLinks){
          if( gateways.contains(nodeLink.getN1()) || gateways.contains(nodeLink.getN2())  ) {
            //found = true;
            // Example: 0 1 are the indices of the nodes you wish to sever the link between
            System.out.println( nodeLink.getN1()+" "+nodeLink.getN2());
            break;
          }
        }

        //if(found) break;

        nodesLinkedToBob.remove(nb);

        findGatewayLink(links,gateways, nodesLinkedToBob);
      }
    }
  }
