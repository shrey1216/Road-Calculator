//@author Shreyan Wankavala

public class Edge implements Comparable{

    private Node A;
    private Node B;
    private int cost;

    /** a constructor which takes in two nodes and a cost as a parameter
     *
     * @param A = the first node or city
     * @param B = the second node or city
     * @param cost = the distance of the road between both cities
     */
    public Edge(Node A,Node B,int cost){
        this.A = A;
        this.B = B;
        this.cost = cost;
    }

    /**  a method to return the node which is stored in A
     *
     * @return the node stored in A
     */
    public Node getA(){
        return this.A;
    }

    /** a method to return the node which is stored in B
     *
     * @return the node stored in B
     */
    public Node getB(){
        return this.B;
    }

    /** a method to get the cost (or distance) between both of the nodes
     *
     * @return an int value of the weight of the road
     */
    public int getCost(){
        return this.cost;
    }

    /** a method to set node A
     *
     * @param A = the node you want to assign to A
     */
    public void setA(Node A){
        this.A = A;
    }

    /** a method to set Node B
     *
     * @param B = the node you want to assign to B
     */
    public void setB(Node B){
        this.B = B;
    }

    /** a method to set the cost of the road (or distance)
     *
     * @param cost = the int value of the weight of the road
     */
    public void setCost(int cost){
        this.cost = cost;
    }

    /** a method to compare two edges using their weights
     *
     * @param otherEdge = the other edge that is going to be compared
     * @return an int value depending on whether or not the edge was less than, greater than, or equal to the other edge.
     */
    public int compareTo(Object otherEdge){
        return -1;
    }

    /** a method to return the member variables in Edge as a string
     *
     * @return the member variables in edge as a String
     */
    public String toString(){
        return A.getName() + " to " + B.getName() + " " + cost;
    }
}
