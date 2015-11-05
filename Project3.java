//David Buff
//Test program for a graph data structure.
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Stack;

public class Project3 {

	public static void main(String[] args) {

		try {
			File cities = new File("city.dat");
			File road = new File("road.dat");
			Digraph graph = new Digraph();
			Scanner input = new Scanner(cities);
			while (input.hasNext()) {
				int number = input.nextInt();
				String code = input.next();
				String name = input.next();
				while (!input.hasNextInt()) { //for cities with multiple word names
					name += " " + input.next();
				}
				int population = input.nextInt();
				int elevation = input.nextInt();
				City city = new City(number, code, name, population, elevation);
				graph.addNode(city);
			}
			input = new Scanner(road);
			while (input.hasNext()) {
				int from = input.nextInt();
				int to = input.nextInt();
				int cost = input.nextInt();
				graph.addEdge(from, to, cost);
			}

			input = new Scanner(System.in);
			System.out.print("Command? ");
			String command = input.nextLine();
			char c;
			if (command.length() > 0)
				c = command.toLowerCase().charAt(0); //Get the command letter
			else
				c = 'z';
			while (c != 'e') { //Repeat the loop until they enter E or e or anything that starts with e really.
				switch (c) {
					case 'q': System.out.print("City Code: ");
						String code = input.nextLine();
						City city = graph.getCity(code);
						if (city != null)
							System.out.println(graph.getCity(code));
						else
							System.out.println("City does not exist.");
						break;
					case 'd': System.out.print("City codes: ");
						String[] values = input.nextLine().split(" ");
						if (values.length == 2) {
							int path[] = graph.distance(values[0], values[1]);
							if (path.length > 0) {
								System.out.print("The minimum distance between " + graph.getCity(values[0]).getName() + " and " + graph.getCity(values[1]).getName() + " is " + String.valueOf(path[path.length - 1]) + " through the route: ");
								int fromNum = graph.getCity(values[0]).getNumber();
								int toNum = graph.getCity(values[1]).getNumber();
								int current = path[toNum - 1];
								Stack<Integer> pat = new Stack<>(); //the path comes back as a list of previous nodes, we cam get the actual path by following it back, for this I use a stack.
								pat.push(current);
								while (current != fromNum) {
									current = path[current - 1];
									pat.push(current);
								}
								boolean comma = pat.size() > 1; // if there's only one city in the path then don't put commas between the two city names
								while (!pat.isEmpty()) {
									System.out.print(graph.getCity(pat.pop()).getName());
									if (comma)
										System.out.print(", ");
									else
										System.out.print(" ");
								}
								System.out.println("and " + graph.getCity(toNum).getName() + ".");
							}
							else
								System.out.println("City does not exist.");
						}
						else
							System.out.println("Wrong number of paramerters.");
						break;
					case 'i': System.out.print("City codes and distance: ");
						values = input.nextLine().split(" ");
						if (values.length == 3) {
							City from = graph.getCity(values[0]);
							City to = graph.getCity(values[1]);
							try {
								int cost = Integer.parseInt(values[2]);
								if (from != null && to != null && graph.addEdge(values[0], values[1], cost))
									System.out.println("You have inserted a road from " + from.getName() + " to " + to.getName() + " with a distance of " + values[2] + ".");
								else if (from != null && to != null)
									System.out.println("The road from " + from.getName() + " to " + to.getName() + " already exists.");
								else
									System.out.println("City does not exist.");
							}
							catch (NumberFormatException e) {
								System.out.println("Error reading parameters.");
							}
						}
						else
							System.out.println("Wrong number of parameters.");
						break;
					case 'r': System.out.print("City codes: ");
						values = input.nextLine().split(" ");
						if (values.length == 2) {
							City from = graph.getCity(values[0]);
							City to = graph.getCity(values[1]);
							if (from != null && to != null && graph.removeEdge(values[0], values[1]))
								System.out.println("The road from " + from.getName() + " to " + to.getName() + " has been removed.");
							else if (from != null && to != null)
								System.out.println("The road from " + from.getName() + " to " + to.getName() + " does not exist.");
							else
								System.out.println("City does not exist.");
						}
						else
							System.out.println("Wrong number of parameters.");
						break;
					case 'h': System.out.println(" Q Query the city information by entering the city code.\n D Find the minimum distance between two cities.\n I Insert a road by entering two city codes and distance.\n R Remove an existing road by entering two city codes.\n H Display this message.\n E Exit.");
						break;
					default: System.out.println("Invalid command. Enter H for help.");
						break;
				}
				System.out.print("Command? ");
				command = input.nextLine();
				if (command.length() > 0)
					c = command.toLowerCase().charAt(0); //Get the command letter
				else
					c = 'z';
			}

			System.out.println("Thank you for using!");
		}
		catch (FileNotFoundException e) {
			System.out.print("Error reading file.");
		}
	}
}