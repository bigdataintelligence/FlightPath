
import java.util.*;

public class FlightPath {

	// main function with two input parameters: source location and target location
	public static void main(String[] args){
		
		System.out.print("Thank you for testing this application; here are your input parameter(s): ");
		for(int i = 0; i < args.length; i++){
			System.out.print(args[i] + " ");;
		}
		System.out.println();
		
		// check correct usage with right number of input parameters
		if(args.length != 2){
			System.out.println("Correct Usage: Java FlightPath \"source location\" \"target location\" ");
			System.exit(1);
		}
		
		// define vertices and edges
		String[] vertices = {"Castle Black", "winterfell", "Riverrun", "King's Landing"};
		int[][] edges = {{0, 15, 80, 90},
						 {0, 0, 40, 50},
						 {0, 0, 0, 70},
						 {0, 0, 0, 0}};
		
		// map string to int for input parameters
		int source = -1;
		int target = -1;

		for(int i = 0; i < vertices.length; i++){
			if(vertices[i].equalsIgnoreCase(args[0])){
				source = i;
				break;
			}
		}
		
		for(int i = 0; i < vertices.length; i++){
			if(vertices[i].equalsIgnoreCase(args[1])){
				target = i;
				break;
			}
		}
		
		if(source == -1 || target == -1){
			System.out.println("Either source location or target location is unrecognisable");
			System.exit(1);
		}
		
		// find all possible routes starting from source, so we don't miss anything
		List<List<Integer>> allValidRoutes = new Vector();
		List<List<Integer>> returnAllRoutes = findAllRoutes(vertices, edges, source);
		
		System.out.println("\nprinting returnAllReoutes");
		printAllRoutes(returnAllRoutes);
		
		// find all valid routes starting from source AND ending at target.
		for(int i = 0; i < returnAllRoutes.size(); i++){
			List<Integer> returnRoute = returnAllRoutes.get(i);
			int index = returnRoute.indexOf(target);
			if(index > 0){
				List<Integer> validRoute = returnRoute.subList(0, index+1); 
				allValidRoutes.add(validRoute);
			}
		}

		System.out.println("\nprinting allValidRoutes");
		printAllRoutes(allValidRoutes);

		// deduplicate allValidRoutes for common routes
		List<List<Integer>> deduplicated = deduplicate(allValidRoutes);
		
		// final presentation
		System.out.println("\nFinal presentation: ");
		finalPresentation(vertices, edges, deduplicated);
	
	}

	// recursive function to find all possible routes starting from source
	public static List<List<Integer>> findAllRoutes(String[] vertices, int[][] edges, int source){
		
		List<List<Integer>> returnAllRoutes = new Vector();
		
		int[] children = edges[source];
		boolean hasChild = false;
		for(int i = 0; i < children.length; i++){
			int cost = children[i];
			if(cost > 0){ // has child, continue search
				List<List<Integer>> allRoutesByChild = findAllRoutes(vertices, edges, i);
				// add source to the head of each child's path
				for(int j = 0; j < allRoutesByChild.size(); j++){
					List<Integer> routeByChild = allRoutesByChild.get(j);
					routeByChild.add(0, new Integer(source));
					returnAllRoutes.add(routeByChild);
					hasChild = true;
				}
			}
		}
		
		// if there is no child for this vertex, then return itself
		if(!hasChild){
			List<Integer> returnRoute = new Vector();
			returnRoute.add(new Integer(source));
			returnAllRoutes.add(returnRoute);
		}
		
		return returnAllRoutes;
	}


	// print list of routes for debugging purpose
	public static void printAllRoutes(List<List<Integer>> allRoutes){
		
		for(int i = 0; i < allRoutes.size(); i++){
			List<Integer> route = allRoutes.get(i);
			System.out.print("route " + i + ": ");
			for(int j = 0;  j < route.size(); j++){
				System.out.print(route.get(j) + " ");
			}
			System.out.println();
		}

	}

	// deduplicate input list of routes
	public static List<List<Integer>> deduplicate(List<List<Integer>> input){
		
		List<List<Integer>> deduplicated = new Vector();
		
		for(int i = 0; i < input.size(); i++){
			List<Integer> inputList = input.get(i);
			boolean isIdentical = false;
			for(int j = 0; j < deduplicated.size(); j++){
				List<Integer> deduplicatedList = deduplicated.get(j);
				isIdentical = isIdentical(inputList, deduplicatedList);
				if(isIdentical){
					break;
				}
			}
			if(!isIdentical){
				deduplicated.add(inputList);
			}
		}
		
		return deduplicated;
		
	}
	
	
	// check if two routes are identical, used by deduplicate function
	public static boolean isIdentical(List<Integer> list1, List<Integer> list2){
		
		if(list1.size() != list2.size()){
			return false;
		}
		
		for(int i = 0; i < list1.size(); i++){
			if(list1.get(i).intValue() != list2.get(i).intValue()){
				return false;
			}
		}
		
		return true;
	}

	
	// render output in the required format
	public static void finalPresentation(String[] vertices, int[][] edges, List<List<Integer>> allValidRoutes){
		
		for(int i = 0; i < allValidRoutes.size(); i++){
			List<Integer> route = allValidRoutes.get(i);
			int parentIndex = -1;
			int childIndex = -1;
			int cost = 0;
			for(int j = 0;  j < route.size(); j++){
				if(j == 0){ // the first element
					System.out.print(vertices[route.get(j)] + " -> ");
					parentIndex = route.get(j);
				} else if(j < route.size()-1){ // from the second to the one before the last
					System.out.print(vertices[route.get(j)] + " -> ");
					childIndex = route.get(j);
					cost = cost + edges[parentIndex][childIndex];
					parentIndex = route.get(j);
				} else if(j == route.size()-1){ // the last element
					System.out.print(vertices[route.get(j)] + ": ");
					childIndex = route.get(j);
					cost = cost + edges[parentIndex][childIndex];
					System.out.println(cost);
				}
			} 
		}

	}

}
