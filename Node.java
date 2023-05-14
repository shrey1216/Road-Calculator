//@author Shreyan Wankavala

import java.util.HashSet;
import java.util.LinkedList;

public class Node {

    private String name;
    private HashSet<Edge> edges;
    private boolean visited;
    private LinkedList<String> path;
    private int distance;

    /** A constructor named Node which takes in a String as a parameter
     *
     * @param name = the name of the city that is in the node
     */
    public Node(String name){
        this.name = name;
        edges = new HashSet<Edge>();
        visited = false;
    }

    /** a method to return the name of the city in the node
     *
     * @return the name of the city
     */
    public String getName(){
        return this.name;
    }

    /** a method to return the edges in a city
     *
     * @return the edges given a city
     */
    public HashSet<Edge> getEdges(){
        return this.edges;
    }

    /** a method to return the boolean status of whether a city has been visited
     *
     * @return true or false
     */
    public boolean getVisited(){
        return this.visited;
    }

    /** a method to return a LinkedList of the path between two locations
     *
     * @return a LinkedList of type String
     */
    public LinkedList<String> getPath(){
        return this.path;
    }

    /** a method to get the distance between a city and the starting location
     *
     * @return the distance of type int
     */
    public int getDistance(){
        return this.distance;
    }

    /** a method to set the name of a city
     *
     * @param name = the name of the city you want to set
     */
    public void setName(String name){
        this.name = name;
    }

    /** a method to set the edges of a city using a HashSet
     *
     * @param edges = the edges of the city using a HashSet
     */
    public void setEdges(HashSet<Edge> edges){
        this.edges = edges;
    }

    /** A method to set the boolean status of whether or not a city has been visited
     *
     * @param visited = true or false for whether or not the city has been visited
     */
    public void setVisited(boolean visited){
        this.visited = visited;
    }

    /** a method to set the distance of a city from the starting location
     *
     * @param distance = an int value of the distance you want to set
     */
    public void setDistance(int distance){
        this.distance = distance;
    }

    /** a method to add an edge to the HashSet contained in this node
     *
     * @param edge = the edge that is going to be added
     */
    public void addEdge(Edge edge){
        edges.add(edge);
    }

    /** a method to return the member variables as a string using a toString() method
     *
     * @return the member variables as a string
     */
    public String toString(){
        return name + " " + visited;
    }

}

