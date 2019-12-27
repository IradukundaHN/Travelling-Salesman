//********************************************************************
// TravellingSalesman.java
//
// Demonstration of the travelling salesman problem. For a given list
// of cities with coordinates, all possible routes from the first
// city to the last city are displayed along with the total length of 
// each route.
//********************************************************************

import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.MatchResult;
import java.util.InputMismatchException;

public class TravellingSalesman {
    //----------------------------------------------------------------
    // Number of routes we have computed
    //----------------------------------------------------------------
    int routes = 0;

    //----------------------------------------------------------------
    // Simple record class representing a City
    //----------------------------------------------------------------
    public static class City {
		double x = 0;
		double y = 0;
		String name = null;
	
		public City(String name, double x, double y) {
			this.name = name;
			this.x = x;
			this.y = y;
		}
	
		public String toString() {
			return name + " (" + x + ", " + y + ")";
		}
	
			//------------------------------------------------------------
		// Returns the distance between this city and the given city
			//------------------------------------------------------------
		public double getDistance(City c) {
			double xDiff = x - c.x;
			double yDiff = y - c.y;
			return Math.sqrt((xDiff * xDiff) + (yDiff * yDiff));
		}
    }

    //----------------------------------------------------------------
    // Finds all possible routes from the city with the given index
    // to the last city in the array. The total distance is returned.
    //----------------------------------------------------------------
    public double findRoute(City[] cities, int startIdx, double dist) {
		double distance = dist;
		City start = cities[startIdx];
	
		// For each city between the start city and the next-to-last,
		// swap it to the next spot, compute a route, then swap it
		// back
		for (int i = startIdx + 1; i < (cities.length - 1); i++) {
			City next = cities[i];
			cities[i] = cities[startIdx + 1];
			cities[startIdx + 1] = next;
			distance = dist + findRoute(
			cities, startIdx + 1, dist + start.getDistance(next));
			cities[startIdx + 1] = cities[i];	
			cities[i] = next;
		}
	
		// If we're at the next-to-last city print out the distance
		if (startIdx == (cities.length - 2)) {
			// Include the distance to the last city
			distance += start.getDistance(cities[cities.length - 1]);
	
			// Increment and print the route counter
			routes ++;
			System.out.println("Route # " + routes);
	
			// Print the contents of the array, which will reflect
			// the accumulated route
			for (int i = 0; i < cities.length; i++)
			System.out.println((i + 1) + ": " + cities[i]);
	
			// Finally, print out the total
			System.out.println("Total distance: " + distance);
			System.out.println();
		}
		
		return distance;
	}
	
	//----------------------------------------------------------------
	// Finds and prints all possible routes from the first city in
	// the list to the last city in the list.
	//----------------------------------------------------------------
	public void findRoutes(City[] cities) {
		// Reset the route counter
		routes = 0;
	
		// Call the recursive method to find the routes, starting at
		// index 0 with initial distance 0
		findRoute(cities, 0, 0.0);
	}

	public static void main(String[] args) throws IOException {
		// Create an ArrayList to hold the cities that the user enters
		ArrayList list = new ArrayList();
	
		// Make a Pattern describing a valid city name 
		Pattern cityPattern = Pattern.compile("(\\D+)");
	
		System.out.println(
			"Enter city names, followed by x and y coordinate values.");
		System.out.println(
			"Enter a blank line when you are finished.");
		Scanner in = new Scanner(System.in);
		
		// Read and parse lines, populating the cities list
		String line = in.nextLine();
		while ((line != null) && (line.length() > 0)) {
			Scanner scan = new Scanner(line);
			try {
				City city = new City(
				scan.findInLine(cityPattern),
				scan.nextDouble(),
				scan.nextDouble());
				list.add(city);
			}
			catch(Exception ise) {
				System.out.println("ERROR: Cannot parse \"" + line + "\"");
			}
			line = in.nextLine();
		}
	
		// Make sure they entered at least two cities
		if (list.size() < 2) {
			System.out.println("You must enter at least two cities");
		}
		else {
			City[] cities = new City[list.size()];
			list.toArray(cities);
			TravellingSalesman ts = new TravellingSalesman();
			ts.findRoutes(cities);
		}
    }
}

