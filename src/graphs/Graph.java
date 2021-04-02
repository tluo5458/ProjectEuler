package graphs;

import java.util.*;

/**
 * Implements a graph. We use two maps: one map for adjacency properties 
 * (adjancencyMap) and one map (dataMap) to keep track of the data associated 
 * with a vertex. 
 * 
 * @author cmsc132
 * 
 * @param <E>
 */
public class Graph<E> {
	/* You must use the following maps in your implementation */
	private HashMap<String, HashMap<String, Integer>> adjacencyMap;
	private HashMap<String, E> dataMap;

	private class PriorityNode implements Comparable<PriorityNode> {
		private String name;
		private int priority;

		private PriorityNode(String name, int priority) {
			this.name = name;
			this.priority = priority;
		}

		@Override
		public int compareTo(PriorityNode other) {
			int diff = priority - other.priority;
			if (diff != 0) {
				return diff;
			}
			return name.compareTo(other.name);
		}

		@Override
		public boolean equals(Object other) {
			if (other != null && other.getClass() == getClass()) {
				@SuppressWarnings("unchecked")
				PriorityNode node = (PriorityNode) other;
				return node.name.equals(name) && node.priority == priority;
			}
			return false;
		}
	}

	public Graph() {
		adjacencyMap = new HashMap<String, HashMap<String, Integer>>();
		dataMap = new HashMap<String, E>();
	}

	public void addDirectedEdge(String startVertexName, String endVertexName, int cost) {
		if (!dataMap.containsKey(startVertexName)) {
			throw new IllegalArgumentException(startVertexName + "does not exist.");
		}
		if (!dataMap.containsKey(endVertexName)) {
			throw new IllegalArgumentException(endVertexName + "does not exist.");
		}
		adjacencyMap.get(startVertexName).put(endVertexName, cost);
	}

	public void addVertex(String vertexName, E data) {
		if (dataMap.containsKey(vertexName)) {
			throw new IllegalArgumentException("Vertex already in graph.");
		}
		dataMap.put(vertexName, data);
		adjacencyMap.put(vertexName, new HashMap<String, Integer>());
	}

	public void doBreadthFirstSearch(String startVertexName, CallBack<E> callback) {
		HashMap<String, Boolean> tags = new HashMap<String, Boolean>();
		for (String s : dataMap.keySet()) {
			tags.put(s, false);
		}
		Queue<String> queue = new LinkedList<String>();
		queue.add(startVertexName);
		while (queue.peek() != null) {
			String curr = queue.peek();
			queue.remove(curr);
			if (!tags.get(curr)) {
				callback.processVertex(curr, dataMap.get(curr));
				tags.put(curr, true);
				TreeSet<String> adjacent = new TreeSet<String>(getAdjacentVertices(curr).keySet());
				for (String s : adjacent) {
					if (!tags.get(s)) {
						queue.add(s);
					}
				}
			}
		}
	}

	public void doDepthFirstSearch(String startVertexName, CallBack<E> callback) {
		HashMap<String, Boolean> tags = new HashMap<String, Boolean>();
		for (String s : dataMap.keySet()) {
			tags.put(s, false);
		}
		Stack<String> stack = new Stack<String>();
		stack.push(startVertexName);
		while (!stack.empty()) {
			String s = stack.pop();
			if (!tags.get(s)) {
				callback.processVertex(s, dataMap.get(s));
				tags.put(s, true);
				TreeSet<String> adjacent = new TreeSet<String>(getAdjacentVertices(s).keySet());
				for (String vertex : adjacent) {
					if (!tags.get(vertex)) {
						stack.push(vertex);
					}
				}
			}
		}
	}

	public int doDijkstras(String startVertexName, String endVertexName, ArrayList<String> shortestPath) {
		if (!dataMap.containsKey(startVertexName) || !dataMap.containsKey(endVertexName)) {
			throw new IllegalArgumentException("Vertex does not exist!");
		}
		PriorityQueue<PriorityNode> priorityQueue = new PriorityQueue<PriorityNode>();
		HashMap<String, Integer> distances = new HashMap<String, Integer>();
		HashMap<String, String> predecessors = new HashMap<String, String>();
		HashSet<String> done = new HashSet<String>();
		for (String s : dataMap.keySet()) {
			predecessors.put(s, null);
			distances.put(s, Integer.MAX_VALUE);
		}
		distances.put(startVertexName, 0);
		
		priorityQueue.add(new PriorityNode(startVertexName, 0));
		while (!priorityQueue.isEmpty()) {
			PriorityNode top = priorityQueue.poll();
			if (!done.contains(top.name)) { 
				done.add(top.name);
				System.out.println(top.name);
				Map<String, Integer> descendants = getAdjacentVertices(top.name);
				for (String s : descendants.keySet()) {
					if (!done.contains(s)) {
						int newDist = distances.get(top.name) + descendants.get(s);
						if (distances.get(s) > newDist) {
							distances.put(s, newDist);
							predecessors.put(s, top.name);
						}
						priorityQueue.add(new PriorityNode(s, distances.get(s)));
					}
				}
			}
		}
		if (distances.get(endVertexName) == Integer.MAX_VALUE) {
			shortestPath.add("None");
			return -1;
		}
		String curr = endVertexName;
		while (curr != null) {
			shortestPath.add(0, curr);
			curr = predecessors.get(curr);
		}
		return distances.get(endVertexName);
	}
	
	public HashMap<String, Pair<Integer, String>> doDijkstrasAll(String startVertexName) {
		if (!dataMap.containsKey(startVertexName)) {
			throw new IllegalArgumentException("Vertex does not exist!");
		}
		PriorityQueue<PriorityNode> priorityQueue = new PriorityQueue<PriorityNode>();
		HashMap<String, Integer> distances = new HashMap<String, Integer>();
		HashMap<String, String> predecessors = new HashMap<String, String>();
		HashSet<String> done = new HashSet<String>();
		for (String s : dataMap.keySet()) {
			predecessors.put(s, null);
			distances.put(s, Integer.MAX_VALUE);
		}
		distances.put(startVertexName, 0);
		
		priorityQueue.add(new PriorityNode(startVertexName, 0));
		while (!priorityQueue.isEmpty()) {
			PriorityNode top = priorityQueue.poll();
			done.add(top.name);
			Map<String, Integer> descendants = getAdjacentVertices(top.name);
			for (String s : descendants.keySet()) {
				if (!done.contains(s)) {
					int newDist = distances.get(top.name) + descendants.get(s);
					if (distances.get(s) > newDist) {
						distances.put(s, newDist);
						predecessors.put(s, top.name);
					}
					priorityQueue.add(new PriorityNode(s, distances.get(s)));
				}
			}
		}
		HashMap<String, Pair<Integer, String>> ret = new HashMap<String, Pair<Integer, String>>();
		for (String s : dataMap.keySet()) {
			ret.put(s, new Pair<Integer, String>(distances.get(s), predecessors.get(s)));
		}
		return ret;
	}

	public Map<String, Integer> getAdjacentVertices(String vertexName) {
		return adjacencyMap.get(vertexName);
	}

	public int getCost(String startVertexName, String endVertexName) {
		return adjacencyMap.get(startVertexName).get(endVertexName);
	}

	public E getData(String vertex) {
		if (!dataMap.containsKey(vertex)) {
			throw new IllegalArgumentException("Vertex does not exist!");
		}
		return dataMap.get(vertex);
	}

	public Set<String> getVertices() {
		return dataMap.keySet();
	}
	
	public String toString() {
		TreeSet<String> vertices = new TreeSet<String>(dataMap.keySet());
		String ret = new String();
		ret = "Vertices: " + vertices.toString() + "\nEdges:\n";
		for (String s : vertices) {
			ret += "Vertex(" + s + ")--->" + new TreeMap<String, Integer>(adjacencyMap.get(s)).toString() + "\n";
		}
		return ret;
	}

}