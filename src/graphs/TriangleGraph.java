package graphs;

import java.util.ArrayList;
import java.util.HashMap;

public class TriangleGraph<T> extends Graph<T> {
	private int[][] layers;
	private int max;
	
	public TriangleGraph(int[][] layers, int max) {
		this.layers = layers;
		for (int i = 0; i < layers.length; i++) {
			for (int j = 0; j <= i; j++) {
				addVertex(i + "-" + j, null);
			}
		}
		for (int i = 0; i < layers.length - 1; i++) {
			for (int j = 0; j <= i; j++) {
				addDirectedEdge(i + "-" + j, (i + 1) + "-" + j, max - layers[i + 1][j]);
				addDirectedEdge(i + "-" + j, (i + 1) + "-" + (j + 1), max - layers[i + 1][j + 1]);
			}
		}
		this.max = max;
		System.out.println(this);
	}
	
	public int findMinPathToBottom(ArrayList<String> path) {
		HashMap<String, Pair<Integer, String>> costs = doDijkstrasAll("0-0");
		int min = Integer.MAX_VALUE;
		int good = -1;
		for (int i = 0; i < layers.length; i++) {
			Pair<Integer, String> curr = costs.get((layers.length - 1) + "-" + i);
			if (curr.getLeft() < min) {
				min = curr.getLeft();
				good = i;
			}
		}
		String curr = (layers.length - 1) + "-" + good;
		while (curr != null) {
			path.add(0, curr);
			curr = costs.get(curr).getRight();
		}
		return min;
	}
	
	public int convertToReg(ArrayList<String> path) {
		int minPath = findMinPathToBottom(path);
		for (int i = 0; i < path.size(); i++) {
			String[] split = path.get(i).split("-");
			int[] converted = {Integer.parseInt(split[0]), Integer.parseInt(split[1])};
			path.set(i, Integer.toString(layers[converted[0]][converted[1]]));
		}
		return (layers.length - 1) * max - minPath + layers[0][0];
	}
}
