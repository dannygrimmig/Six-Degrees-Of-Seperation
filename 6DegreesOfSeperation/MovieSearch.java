/*
 *
 MovieSearch.java

 COSC 102, Colgate University

 DO NOT MODIFY ANY CODE IN THIS FILE
 */

import java.io.*;
import java.util.*;

public class MovieSearch
{

     private static final String QUIT_COMMAND = "!quit";

     //Parses data from file, uses parsed data to create Movie Graph object
     //terminates program if format is invalid
     private static MovieGraph createMovieGraph(String fname){
          try {
               Set<String[]> data = new HashSet<String[]>();
               Scanner s = new Scanner(new File(fname));
               while (s.hasNextLine())
                    data.add(s.nextLine().trim().split("/"));
               s.close();
               return new MovieGraph(data);
          }
          catch (FileNotFoundException e) {
               System.err.println("Movie Search: database file " + fname + " not found");
               System.exit(2);
          }
          catch (SecurityException e) {
               System.err.println("Movie Search: security violation reading file " + fname);
               System.exit(3);
          }

          return null; //dummy, program terminates if invalid file is provided
     }


     //Displays the shortest path between the argument actors/movies
     private static void displayPath(String target1, String target2, MovieGraph mg){

          System.out.println("Finding shortest link for targets: '" + target1 + "' and '" + target2 + "': \n");
          ArrayList<String> link = mg.findShortestPath(target1, target2);
          if (link.size() == 0)
               System.out.println("No link found!\n\n");
          else{
               int numOfLinks = link.size();
               System.out.println("Link found in " + numOfLinks + " steps!");
               for (int i = 0; i < numOfLinks - 1; i++)
                    System.out.print(link.get(i) + " --> ");
               System.out.println(link.get(numOfLinks - 1) + "\n\n");
          }
     }

     //Prompts user for movie/actor names, and displays results from MovieGraph
     //Stops when user enters !quit
     private static void promptUser(MovieGraph mg){
          Scanner s = new Scanner(System.in);
          int response = 0;
          String target1= null;
          String target2 = null;

          while (!QUIT_COMMAND.equals(target1) && !QUIT_COMMAND.equals(target2)) {
               if (target1 == null){
                    System.out.print("Enter a actor or movie name, or type !quit to exit: ");
                    target1 = s.nextLine();
               }
               else if (target2 == null){
                    System.out.print("Enter another actor or movie name: ");
                    target2 = s.nextLine();
               }
               else{
                    displayPath(target1, target2, mg);
                    target1 = null;
                    target2 = null;
               }
          }
     }


     /*
      MovieSearch.main({file})

      Takes the filename of a movie database as an argument.
      All but the first argument will be ignored.
      */
     public static void main (String[] args) {
          //retrieves Dataset file from command-line argument
          if (args == null || args.length < 1) {
           .get(traceSize)));
     	 	 traceSize++;
     	 }
     	 ArrayList<String> ordered = new ArrayList<String>();
     	 for(int i = traceSize; i >= 0; i--)
     	 	 ordered.add(trace.get(i));
     	 return ordered;
     }
        

}    System.out.println("Movies Search: error; no database file provided");
               System.exit(1);
          }

          MovieGraph mg = createMovieGraph(args[0]);
          promptUser(mg);
          System.out.println("Thanks for playing!");
     }


}
