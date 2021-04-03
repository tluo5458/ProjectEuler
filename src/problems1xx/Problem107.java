package problems1xx;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;

import graphs.Pair;

public class Problem107 {
	public static int[][] parseFile() {
		int[][] ret = new int[40][40];
		int row = 0;
		try {
			File txt = new File("MiscFiles/p107_network.txt");
			@SuppressWarnings("resource")
			Scanner scan = new Scanner(txt);
			while (scan.hasNextLine()) {
				int[] coords = new int[40];
				String[] line = scan.nextLine().split(",");
				for (int i = 0; i < 40; i++) {
					if (line[i].equals("-")) {
						coords[i] = -1;
					} else {
						coords[i] = Integer.parseInt(line[i]);
					}
				}
				ret[row] = coords;
				row++;
			}
		} catch (FileNotFoundException e) {
			System.out.println("oops");
		}
		return ret;
	}
	
	// use Kruskal's algorithm to find MST
	public static int totalSaved() {
		int[][] matrix = parseFile();
		ArrayList<Pair<Integer, Pair<Integer, Integer>>> edges = new ArrayList<Pair<Integer, Pair<Integer, Integer>>>();
		TreeMap<Integer, Integer> connections = new TreeMap<Integer, Integer>();
		int totalEdges = 0;
		for (int i = 0; i < 40; i++) {
			connections.put(i, i);
		}
		for (int i = 0; i < 40; i++) {
			for (int j = i + 1; j < 40; j++) {
				int elem = matrix[i][j];
				if (elem != -1) {
					totalEdges += elem;
					int k = 0;
					if (edges.size() == 0) {
						edges.add(new Pair<Integer, Pair<Integer, Integer>>(elem, new Pair<Integer, Integer>(i, j)));
					} else {
						while (edges.get(k).getLeft() < elem) {
							k++;
							if (k == edges.size()) {
								break;
							}
						}
						edges.add(k, new Pair<Integer, Pair<Integer, Integer>>(elem, new Pair<Integer, Integer>(i, j)));
					}
				}
			}
		}
		for (Pair<Integer, Pair<Integer, Integer>> edge : edges) {
			System.out.println(edge);
		}
		
		ArrayList<Integer> edgeLengths = new ArrayList<Integer>();
		while (edgeLengths.size() < 39) {
			Pair<Integer, Pair<Integer, Integer>> edge = edges.remove(0);
			int edge1 = edge.getRight().getLeft();
			int edge2 = edge.getRight().getRight();
			int height1 = 0;
			int height2 = 0;
			while (edge1 != connections.get(edge1)) {
				edge1 = connections.get(edge1);
				height1++;
			}
			while (edge2 != connections.get(edge2)) {
				edge2 = connections.get(edge2);
				height2++;
			}
			if (edge1 != edge2) {
				if (height1 >= height2) {
					connections.put(edge2, edge1);
				} else {
					connections.put(edge1, edge2);
				}
				edgeLengths.add(edge.getLeft());
			}
		}
		
		int mstTotal = 0;
		for (int i = 0; i < 39; i++) {
			mstTotal += edgeLengths.get(i);
		}
		return totalEdges - mstTotal;
	}
	
	public static void main(String[] args) {
		System.out.println(totalSaved());
	}
}
