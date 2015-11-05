public class Edge {

	private int node;
	private int cost;

	public Edge() {
		node = -1;
		cost = -1;
	}

	public Edge(int newNode, int newCost) {
		node = newNode;
		cost = newCost;
	}

	public void setNode(int newNode) {
		node = newNode;
	}

	public void setCost(int newCost) {
		cost = newCost;
	}

	public int getNode() {
		return node;
	}

	public int getCost() {
		return cost;
	}
}