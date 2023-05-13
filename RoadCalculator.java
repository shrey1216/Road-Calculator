//@author Shreyan Wankavala
//112634232
//Recitation 01

import java.util.*;
import big.data.DataSource;
import big.data.DataSourceException;

public class RoadCalculator {

    private static HashMap<String, Node> graph;
    private static LinkedList<Edge> mst;
    private static String[] citiesList;
    private static String[] roadsList;
    private static ArrayList<Edge> edges;

    public static void main(String[] args){

        Scanner scan = new Scanner(System.in);

        System.out.println("\nWelcome to the program! A country will be represented below!\n");

        String command = "";

        HashMap<String,Node> cities = new HashMap<String,Node>();
        mst = new LinkedList<Edge>();


        //https://www3.cs.stonybrook.edu/~cse214/hw/hw7-images/hw7.xml


        boolean enter = true;
        while(enter) {
            System.out.print("Please enter graph URL:");
            String url = scan.nextLine();
            try {
                cities = buildGraph(url);
                System.out.println("Loading Map...");
                enter = false;
            } catch (DataSourceException e) {
                System.out.println("Try again with a proper xml file!\n");
            }
        }

        while(!command.equals("Q")){

            System.out.print("\nMenu:" +
                    "\n" +
                    "    T)Print Cities\n" +
                    "    R)Print Roads\n" +
                    "    P)Print Minimum Spanning Tree\n" +
                    "    C)Calculate Shortest Path\n" +
                    "    Q)Quit\n");

            System.out.print("Please select an option:");
            command = scan.nextLine().toUpperCase();

            switch(command){
                case "T":
                    System.out.println("\nCities:\n");
                    for(int i = 0; i < citiesList.length; i++){
                        System.out.println(citiesList[i]);
                    }
                    break;
                case "R":
                    System.out.println("\nRoads:\n");
                    for(int i = 0; i < roadsList.length; i++){
                        System.out.println(roadsList[i].substring(0,roadsList[i].indexOf(",")) + " to " + roadsList[i].substring(roadsList[i].indexOf(",")+1).replace(","," "));

                    }
                    break;
                case "P":
                    mst = buildMST(graph);
                    System.out.println("\nMinimum Spanning Tree:\n");
                    for(int i = 0; i < mst.size(); i++){
                        System.out.println(mst.get(i));
                    }
                    break;
                case "C":
                    System.out.print("Enter a starting point for shortest path:");
                    String start = scan.nextLine();
                    boolean check = false;
                    for(int i = 0; i < citiesList.length; i++){
                        if(citiesList[i].equals(start)){
                            check = true;
                        }
                    }
                    if(!check){
                        System.out.println("\nThis city does not exist! Try again.\n");
                        break;
                    }
                    System.out.print("Enter a destination:");
                    String dest = scan.nextLine();
                    check = false;
                    for(int i = 0; i < citiesList.length; i++){
                        if(citiesList[i].equals(dest)){
                            check = true;
                        }
                    }
                    if(!check){
                        System.out.println("\nThis city does not exist! Try again.\n");
                        break;
                    }
                    if(start.equals(dest)){
                        System.out.println("\nYou cannot pick the same city as your starting point! Try again.\n");
                        break;
                    }

                    System.out.println("Distance:" + Dijkstra(graph,start,dest));
                    System.out.println("Path:");

                    break;
                case "Q":

                    break;
                default:
                    System.out.println("\nThat was not a valid choice! Try again.");
                    break;
            }
        }

        System.out.println("\nGoodbye; PSA, there's a cop on the right in 3 miles!\n");


    }

    /** a method to build a graph given the location of an XML file from the internet.
     *
     * @param location = a string containing the url for the XML file which needs to be converted
     * @return the XML file as a HashMap
     * @throws DataSourceException if the file is not found or is of the wrong type
     */
    public static HashMap<String, Node> buildGraph(String location) throws DataSourceException {
        ArrayList<Edge> edges = new ArrayList<Edge>();
        RoadCalculator obj = new RoadCalculator();


        DataSource ds = DataSource.connectXML(location);
        ds.load();
        String cityNamesStr = ds.fetchString("cities");
        String[] cityNames = cityNamesStr.substring(1,cityNamesStr.length()-1).replace("\"","").split(",");
        String roadNamesStr = ds.fetchString("roads");
        String[] roadNames = roadNamesStr.substring(2,roadNamesStr.length()-2).split("\",\"");

        citiesList = cityNames;
        roadsList = roadNames;

        graph = new HashMap<String,Node>();

        for(int i = 0; i < cityNames.length; i++){
            Node node = new Node(cityNames[i]);
            graph.put(cityNames[i],node);
        }

        for(int i = 0; i < roadNames.length;i++) {
            String[] splitted = roadNames[i].split(",");
            Edge edge = new Edge(graph.get(splitted[0]),graph.get(splitted[1]),Integer.parseInt(splitted[2]));
            graph.get(splitted[0]).addEdge(edge);
            edges.add(edge);
        }

        obj.setEdges(edges);

        return graph;

    }

    /** a method to set the edges in an ArrayList
     *
     * @param edges = the edges that you want to set in the ArrayList
     */
    public void setEdges(ArrayList<Edge> edges){
        this.edges = edges;
    }

    /** a method to return the edges given the edges ArrayList
     *
     * @return the edges given the edges ArrayList
     */
    public ArrayList<Edge> getEdges(){
        return this.edges;
    }

    /** a method which returns a minimum spanning tree in the form of a LinkedList
     *
     * @param graph = the HashMap which contains all of the nodes
     * @return a LinkedList in the form of a minimum spanning tree
     */
    public static LinkedList<Edge> buildMST(HashMap<String, Node> graph){

        RoadCalculator obj = new RoadCalculator();
        mst = new LinkedList<Edge>();
        ArrayList<Edge> edges = new ArrayList<Edge>();
        ArrayList<Node> visitedSet = new ArrayList<Node>();

        edges = obj.getEdges();
        Node[] temp = graph.values().toArray(new Node[graph.values().size()]);


        temp[0].setVisited(true);
        visitedSet.add(temp[0]);

        int count = 50;
        int index = -1;
        for(int i = 0; i < edges.size(); i++){
            if(edges.get(i).getA().getName().equals(temp[0].getName()) || edges.get(i).getB().getName().equals(temp[0].getName())){
                if(edges.get(i).getCost() < count){
                    count = edges.get(i).getCost();
                    index = i;
                }
            }
        }
        if(visitedSet.contains(edges.get(index).getA())){
            visitedSet.add(edges.get(index).getB());
            edges.get(index).getB().setVisited(true);
        }else{
            visitedSet.add(edges.get(index).getA());
            edges.get(index).getA().setVisited(true);
        }
        mst.add(edges.get(index));


        while(visitedSet.size() < temp.length){
            count = 50;
            index = -1;
            for(int i = 0; i < visitedSet.size(); i++){
                for(int j = 0; j < edges.size(); j++){
                    if((edges.get(j).getA().getName().equals(visitedSet.get(i).getName()) || edges.get(j).getB().getName().equals(visitedSet.get(i).getName())) && !(edges.get(j).getA().getVisited() && edges.get(j).getB().getVisited())){
                        if(edges.get(j).getCost() < count){
                            count = edges.get(j).getCost();
                            index = j;
                        }
                    }
                }
            }
            if(visitedSet.contains(edges.get(index).getA())){
                visitedSet.add(edges.get(index).getB());
                edges.get(index).getB().setVisited(true);
            }else{
                visitedSet.add(edges.get(index).getA());
                edges.get(index).getA().setVisited(true);
            }

            mst.add(edges.get(index));
        }
        return mst;
    }

    /** a method to return the shortest path between two locations using Dijkstra's algorithm
     *
     * @param graph = the HashMap which is taken in as a parameter
     * @param source = the starting location
     * @param dest = the ending location
     * @return an int as the shortest path between both cities
     */
    public static int Dijkstra(HashMap<String,Node> graph,String source,String dest){

        return 10;
    }

}
