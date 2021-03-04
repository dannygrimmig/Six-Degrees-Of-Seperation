/*
 *
 MovieGraph.java
 COSC 102, Colgate University

 Your code goes here.
 See instructions for explanation of methods.

 */

import java.io.*;
import java.util.*;



public class MovieGraph{
	
     
     private HashMap<String, String> history;
     private Deque<String> queue;
     private String current;
     private String target1;
     private String target2;
     
     private HashMap<String, ArrayList<String>> graph;
     
     public MovieGraph(Set<String[]> data){     
        
          graph = new HashMap<String, ArrayList<String>>();    
          Iterator<String[]> iterator = data.iterator();
          while(iterator.hasNext()){
          	  String[] line = iterator.next();
          	  addToGraph(line);
          }
     }
     private void addToGraph(String[] line){
     	 int lineSize = line.length;
     	 ArrayList<String> actors = new ArrayList<String>();
     	 ArrayList<String> movies;
     	 for(int i = 1; i < lineSize; i++){
     	 	 actors.add(line[i]);
     	 	 movies = new ArrayList<String>();
     	 	 if(graph.get(line[i]) != null) //not first time seeing actor
     	 	 	 movies = (graph.get(line[i])); // add movie to list of movies actor has starred in
     	 	 movies.add(line[0]);
     	 	 graph.put(line[i], movies);
     	 }
     	 graph.put(line[0], actors);
     }

     public ArrayList<String> findShortestPath(String target1, String target2){
     	 this.target1 = target1;
     	 this.target2 = target2;
     	 
     	 if(targetExists(target1) && targetExists(target2)){
     	 	 
         history = new HashMap<String,String>();
         queue = new LinkedList<String>();
         queue.add(target1);
         history.put(target1, "x");
 
         boolean searching = true;

         while(searching){
         	 if(queue.peek() == null) //no link!
         	 	 return targetDNE(); //empty array...
     
         	 if(queue.peekLast().equals(target2)){
         	 	 searching = false;
         	 	 break;
         	 }
         	 current = queue.remove();
         	 getEdges(current);
         }      
         return traceHistory(); 
         }
         return targetDNE();
     }
     
     private void getEdges(String current){
     	 ArrayList<String> edges = new ArrayList<String>();
     	 edges = graph.get(current);
     	 updateHQ(edges, current); //returns true if found target while updating
     }
       
     private boolean updateHQ(ArrayList<String> edges, String current){
     	 int edgesSize = edges.size();
     		for(int i = 0; i<edgesSize; i++){
     			if(!inHistory(edges.get(i))){
     				queue.add(edges.get(i));
     				history.put(edges.get(i), current);
     			}
     			if(targetFound())
     				return true;
     		}
     		return false;
     	 }

     private boolean inHistory(String edge){
     	if(history.containsKey(edge))
     		return true;
     	return false;
     }
     
     private boolean targetFound(){
     	 // check if last item in queue is target2
     	 if(!queue.isEmpty()){
     	 	 if(queue.peekLast().equals(target2))
     	 	 	 return true;
     	 }
     	 return false;
     }
     
     private ArrayList<String> traceHistory(){
     	 ArrayList<String> trace = new ArrayList<String>();
     	 trace.add(target2);
     	 int traceSize = 0;
     	 while(!trace.get(traceSize).equals(target1)){
     	 	 trace.add(history.get(trace.get(traceSize)));
     	 	 traceSize++;
     	 }
     	 ArrayList<String> ordered = new ArrayList<String>();
     	 for(int i = traceSize; i >= 0; i--)
     	 	 ordered.add(trace.get(i));
     	 return ordered;
     }
     
     private boolean targetExists(String key){
     	 if(graph.containsKey(key))
     	 	 return true;
     	 return false;
     }
     
     private ArrayList<String> targetDNE(){
     	 ArrayList<String> dne = new ArrayList<String>();
     	 if(!targetExists(target1) || !targetExists(target2)){
     	 	 System.out.println("Actor/Actress/Movie Not Found in Database!");
     	 }
     	 return dne;
     }
        

}
