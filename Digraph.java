//David Buff
//A graph data structure.
import java.util.ArrayList;
import java.util.HashMap;

public class Digraph {

	private ArrayList<City> nodes; //Container for nodes
	private ArrayList<HashMap<Integer, Integer>> edges; //list of edges that correspond with each node. Key is the To node and value is cost.

	public Digraph() {
		nodes = new ArrayList<>();
		edges = new ArrayList<>();
	}

	public void addNode(City node) {
		nodes.add(node);
		edges.add(new HashMap<>());
	}

	public boolean addEdge(int from, int to, int cost) {
		if (from < nodes.size() && to < nodes.size() && edges.get(from - 1).get(to) == null) { //check if the nodes exist and if the edge already exists
			edges.get(from - 1).put(to, cost);
			return true;
		}
		return false;
	}

	public boolean addEdge(String fromCode, String toCode, int cost) {
		City fromCity = getCity(fromCode);
		City toCity = getCity(toCode);
		if (fromCity != null && toCity != null) {
			int from = fromCity.getNumber();
			int to = toCity.getNumber();
			return addEdge(from, to, cost);
		}
		return false;
	}

	public boolean removeEdge(String fromCode, String toCode) {
		City fromCity = getCity(fromCode);
		City toCity = getCity(toCode);
		if (fromCity != null && toCity != null) {
			int from = fromCity.getNumber();
			int to = toCity.getNumber();
			if (edges.get(from - 1).get(to) != null) {
				edges.get(from - 1).remove(to);
				return true;
			}
		}
		return false;
	}

	public int[] distance(String fromCode, String toCode) {
		City fromCity = getCity(fromCode);
		City toCity = getCity(toCode);
		int prev[];
		if (fromCity != null && toCity != null) {
			int from = fromCity.getNumber();
			int to = toCity.getNumber();
			//dijkstra
			int dist[] = new int[nodes.size()];
			prev = new int[nodes.size() + 1];
			HashMap<Integer, Boolean> unvisited = new HashMap<>();//List of unvisited nodes, value is irrelevant.
			for (int i = 0; i < nodes.size(); i++) {
				dist[i] = Integer.MAX_VALUE; //setup
				prev[i] = -1;
				unvisited.put(nodes.get(i).getNumber(), true);
			}
			dist[from - 1] = 0;
			int u = from;
			while (!unvisited.isEmpty()) {
				ArrayList<Integer> adj = new ArrayList<>(edges.get(u - 1).keySet());
 				for (int i = 0; i < adj.size(); i++) {
					if (dist[u - 1] + edges.get(u - 1).get(adj.get(i)) < dist[adj.get(i) - 1]) {
						dist[adj.get(i) - 1] = dist[u - 1] + edges.get(u - 1).get(adj.get(i));
						prev[adj.get(i) - 1] = u;
					}
				}
				unvisited.remove(u);
				if (unvisited.isEmpty())
					break;
				ArrayList<Integer> unvis = new ArrayList<>(unvisited.keySet());
				u = unvis.get(0);
				for (int i = 1; i < unvis.size(); i++) { //find the lowest unvisited node
					if (dist[unvis.get(i) - 1] < dist[u - 1])
						u = unvis.get(i);
				}
			}
			prev[prev.length - 1] = dist[to - 1];        
		}
		else
			prev = new int[0];
		return prev;
	}

	public City getCity(String code) {
		if (!nodes.isEmpty()) {
			City city = nodes.get(0);
			int i = 1;
			while (i < nodes.size() && !city.getCode().equals(code)) {
				city = nodes.get(i);
				i++;
			}
			if (i != nodes.size())
				return city;
		}
		return null;
	}

	public City getCity(int number) {
		if (!nodes.isEmpty()) {
			City city = nodes.get(0);
			int i = 1;
			while (i < nodes.size() && city.getNumber() != number) {
				city = nodes.get(i);
				i++;
			}
			if (i != nodes.size())
				return city;
		}
		return null;
	}
}