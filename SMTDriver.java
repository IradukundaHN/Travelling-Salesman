

public class SMTDriver {
	//main function
	public static void main(String[] args){
	
		//Create an object of NearestNeighbor
		NearestNeighbor nn = new NearestNeighbor();
		
		//2D array to store distances from one city to another
		//Testcase 1
		int[][] distanceMatrix = 
				{{-1, 13,  142, 225, 40,  352, 227},
				{13, -1, 136, 237, 34,  363, 222},
				{141, 135, -1, 305, 101, 432, 97},
				{226, 237, 304, -1, 248, 133, 371},
				{40,  34,  106, 248, -1, 374, 192 },
				{352, 364, 431, 133, 375, -1, 462},
				{228, 222, 97,  370, 188, 462, -1}};
	
		// Testcase 2
			int[][] distanceMatrix1 = 
			{{-1, 10, 100, 32, 455, 96},
			{10, -1, 105, 40, 460, 100},
			{111, 102, -1, 447, 101, 432},
			{32, 39, 304, -1, 248, 133},
			{455,  438,  106, 248, -1, 374},
			{98, 100, 432, 134, 375, -1}};

		nn.nearestNeighbor(distanceMatrix,0);
		//nn.nearestNeighbor(distanceMatrix1,0);	
	}


}
