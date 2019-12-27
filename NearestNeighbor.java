import java.text.DecimalFormat;
import java.util.*;


public class NearestNeighbor {
	
	private final static String[] cities = {"Rockville", "Silver Spring", "Philadelphia", "PittsBurgh", "Baltimore", "Cleveland", "New York City"}; //Name of the cities to be visited
	
	private static ArrayList<Integer> visitedCities; 

	private static int[][] distanceMatrix;
	private static int distance;
	
	/*
	 * no-args constructor
	 */
	public NearestNeighbor() {
		visitedCities = new ArrayList<Integer>(); // List of traveled cities
	}
	
	
	/**
     * Test if the node argument was already visited.
     * @param node Node to be checked against the visited cities array.
     * @return True, if the node was already visited; otherwise, false.
     */
	private static boolean isVisited(int node) 
	{
		boolean found = false;
		for(int i=0; i<visitedCities.size();i++)
		{
			if(node == visitedCities.get(i))
			{
				found = true;
				break;
			}
		}
		return found;
	}
	
	/**
	 * nearestNeighBor Method as AarrayList 
	 * @param distanceMatrix
	 * @param initialNode
	 * @return visitedCities
	 */
	public static ArrayList<Integer> nearestNeighbor(int[][] distanceMatrix, int initialNode)
	{
		// Initialization
		visitedCities.add(initialNode); // Add the starting node to the visited cities
		int distance = 0; // Total traveled distance
		int currentNode = initialNode;  // The current city
		
		// looks for the next closest city while there are cities that have not been visited yet
		do {
			// Initialization
			int edge = -1; 				// The shortest distance of the nearest node
			int nearestNode = -1;      // The nearest city

			for(int i = 0; i < distanceMatrix[currentNode].length; i++)
			{
				if(-1==distanceMatrix[currentNode][i]) 
				{
					// Same City. Current and destination cities are the same.
	                // Same cities are encoded with a -1 (0) in the distance matrix.
					continue;
				}
				
				if(isVisited(i)) 
				{
					// This city has already been visited by some other 
	                // node/recursion.
					continue;
				}
				if((-1 == edge)&&(-1!=distanceMatrix[currentNode][i]))
				{
					// initialize this variable with the edge value of the
	                // first city, that is not the current city. This solves the 
	                // currentNode[0][0] issues, where it is originally set to -1.
					edge = distanceMatrix[currentNode][i];
					nearestNode = i;
				}
				
				if(distanceMatrix[currentNode][i] <= edge)
				{
					edge = distanceMatrix[currentNode][i];
					nearestNode = i; // Save the nearest node
				}
			}

			visitedCities.add(nearestNode); // Mark this as visited node
			distance += edge;   			// Add up the distance 
			currentNode = nearestNode;      // Mark this as a closest node
			
		}while(visitedCities.size() < distanceMatrix.length);

		distance += distanceMatrix[currentNode][initialNode]; // Add the distance from the last visited city to the initial city

		visitedCities.add(initialNode);
		
		visitedCities.add(distance); // Add distance to the end of the list of cities
		
		System.out.println("Number of cities is: " + distanceMatrix.length);
		
		printBestTour(visitedCities, cities);
		
		System.out.println("\n1.The Traveled time : " + traveledTime(distance) +  " hours");
		
		System.out.println("\n2.The Cost of diesel fuel: $" + costOfFuel(distance));
		
		System.out.println("\n3.The Salary for the tour driver for the entire tour : $" + tourDriverSalary(distance));
		
		System.out.println("\n4.The Salary for the tour conductor for the entire tour: $" + tourConductorSalary(distance));
		
		System.out.println("\n5.The Total revenue generated: $" + revenue(60));
		
		System.out.println("\n6.The Total of cost of hotel for all the guests: $" +costOfHotels(distanceMatrix.length + 1,61) );
		
		System.out.println("\n7.The Cost of Food and Attractions: $" + costOfMealAndAttraction(distanceMatrix.length + 1, 61));
		
		System.out.println("\n8.The Cost of Maintenance: $" + costOfMaintenance(distance));
		
		System.out.println("\n9.Total Profit for the Sight Seeing Company: $" + totalProfit(61, distance, distanceMatrix.length + 1));
		
		return visitedCities;
	}
	
	
	/**
	 * printBestTour Method to outpout the shortest path
	 * @param bestTour
	 * @param cities
	 */
    private static void printBestTour(ArrayList<Integer> bestTour, String[] cities ) {
    	System.out.print("\nThe best tour is: ");
    	for(int i = 0; i < bestTour.size() - 1; i++)
			if(bestTour.get(i) < cities.length)
			{
				System.out.print(cities[bestTour.get(i)] + ", ");
			}

			else
				System.out.print(bestTour.get(i) + " ");

		System.out.println("with a distance of: " + bestTour.get(bestTour.size() - 1) + " miles");
    	}//end of for loop.
    

    /**
     * traveledTime method to calculate the total time traveled
     * @param d as distance traveled
     * @return traveledTime as total traveled time
     */
    public static double traveledTime (int d) {
    	DecimalFormat fm = new DecimalFormat("#.##");
    	double time = Double.valueOf(fm.format(d/60.0));
    	return time;
    }
    
    /**
     * CostOfFuel Method to calculate the cost of fuel needed for the tour
     * @param distanceTravelled
     * @return cost of fuel
     */
    public static double costOfFuel(int distanceTravelled)
    {
		if(distanceTravelled % 8 == 0)
			return (distanceTravelled / 8) * 3.25;
		else
			return (distanceTravelled / 8 + 1) * 3.25;

	}

    /**
     * tourDriverSalary Method to calculate the salary for the tour driver for the entire trip
     * @param distance
     * @return tourDriverSalary
     */
    public static double tourDriverSalary(int distanceTravelled)
    {
    	double tourDriverSalary;
    	
    	tourDriverSalary = (1000 + 0.55 * distanceTravelled);
    	
    	return tourDriverSalary;
	}
    
    /**
     * tourConductorSalary Method to calculate the salary for the tour conductor
     * @param distance
     * @return tourConductorSalary
     */
    public static double tourConductorSalary(int distanceTravelled)
    {
    	double tourConductorSalary;
    	
    	tourConductorSalary = (800 + 0.40 * distanceTravelled);
    	return tourConductorSalary;
	}
    
    /**
     * revenue Method to calculate the total revenue generated
     * @param numOfPassengers
     * @return revenue
     */
    public static double revenue(int numOfPassengers)
    {
		double revenue;
		
		revenue =numOfPassengers * 1945.0;
		
		return revenue;
	}
    
    /**
     * costOfHotells Method to calculate the cost of Hotel
     * @param visits
     * @param numOfPassengers
     * @return costOfHotel
     */
    public static double costOfHotels(int visits, int numOfPassengers)
    {
		return (visits * numOfPassengers * 90);
	}
    /**
     * 
     * @param visits
     * @param numOfPassengers
     * @return costOfMealAndAttraction
     */
    public static double costOfMealAndAttraction(int visits, int numOfPassengers)
    {
		return (visits * numOfPassengers * 60);
	}
    
    /**
     * costOfMaintenance Method to calculate the total cost Of Maintenance
     * @param distance
     * @return costOfMaintenance
     */
	public static double costOfMaintenance(int distance){
		return (distance * 0.6);
	}

	/**
	 * costsExpenses Method to calculate all the expenses 
	 * @param numOfPassengers
	 * @param distance
	 * @param visits
	 * @return costsExpenses
	 */
	public static double costExpenses(int numOfPassengers, int distance, int visits)
	{
		
		double costExpenses;
		
		costExpenses = costOfFuel(distance) + costOfMaintenance(distance) + tourDriverSalary(distance) +
		tourConductorSalary(distance) + costOfHotels(visits, numOfPassengers) + costOfMealAndAttraction(visits, numOfPassengers);
	
		return costExpenses;
	}
	
	/**
	 * totalProfit Method to calculate the profit 
	 * @param numOfPassengers
	 * @param distance
	 * @param visits
	 * @return profit
	 */
	public static double totalProfit(int numOfPassengers, int distance, int visits)
	{
		DecimalFormat fm = new DecimalFormat("#.##");
		double profit;
		
		profit = Double.valueOf(fm.format(revenue(numOfPassengers) - costExpenses(numOfPassengers, distance,visits)));
	
		return profit;
	}



}

