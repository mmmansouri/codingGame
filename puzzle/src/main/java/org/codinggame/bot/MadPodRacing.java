package org.codinggame.bot;

import java.util.*;
import java.io.*;
import java.math.*;

public class MadPodRacing {

    public static void main(String args[]) {
      Scanner in = new Scanner(System.in);

      // game loop
      while (true) {
        int x = in.nextInt();
        int y = in.nextInt();
        int nextCheckpointX = in.nextInt(); // x position of the next check point
        int nextCheckpointY = in.nextInt(); // y position of the next check point
        int nextCheckpointDist = in.nextInt(); // distance to the next checkpoint
        int nextCheckpointAngle = in.nextInt(); // angle between your pod orientation and the direction of the next checkpoint
        int opponentX = in.nextInt();
        int opponentY = in.nextInt();

        boolean boost = Math.abs(nextCheckpointAngle) < 90;
        int  tempThurst = (180 - Math.abs(nextCheckpointAngle)) ;
        int  thurst = tempThurst >= 100 ? 100 : tempThurst ;
        // boolean boost = (180 - Math.abs(nextCheckpointAngle)) >= 100 ;

        //int  tempThurst = (180 - Math.abs(nextCheckpointAngle)) ;

        // int thurst = tempThurst >= 0? tempThurst : 0;

        System.err.println(boost);

        // Write an action using System.out.println()
        // To debug: System.err.println("Debug messages...");


        // You have to output the target position
        // followed by the power (0 <= thrust <= 100)
        // i.e.: "x y thrust"
        String speed = boost == true ? "BOOST": String.valueOf(thurst);

        System.out.println(nextCheckpointX + " " + nextCheckpointY + " "+speed);
      }
    }
}
