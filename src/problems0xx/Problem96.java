package problems0xx;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Problem96 {

	public static int[][][] parseFile() {
		int[][][] ret = new int[50][9][9];
		try {
			File txt = new File("MiscFiles/p096_sudoku.txt");
			@SuppressWarnings("resource")
			Scanner scan = new Scanner(txt);
			int currInd = -1;
			int currLine = 0;
			while (scan.hasNextLine()) {
				String s = scan.nextLine();
				if (s.charAt(0) == 'G') {
					currLine = 0;
					currInd++;
					continue;
				} else {
					for (int i = 0; i < 9; i++) {
						ret[currInd][currLine][i] = s.charAt(i) - 48;
					}
					currLine++;
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("oops");
		}
		return ret;
	}

	// by backtracking, i might try the exact cover solution at some point
	public static int[][] solveSudoku(int[][] sudoku) {
		solve(sudoku);
		return sudoku;
	}

	private static boolean solve(int[][] board) {
		for (int row = 0; row < 9; row++) {
			for (int column = 0; column < 9; column++) {
				if (board[row][column] == 0) {
					for (int k = 1; k <= 9; k++) {
						board[row][column] = k;
						if (isValid(board, row, column) && solve(board)) {
							return true;
						}
						board[row][column] = 0;
					}
					return false;
				}
			}
		}
		return true;
	}

	private static boolean isValid(int[][] board, int row, int column) {
		return rowConstraint(board, row) &&
				columnConstraint(board, column) &&
				subsectionConstraint(board, row, column);
	}

	private static boolean subsectionConstraint(int[][] board, int row, int column) {
		boolean[] constraint = new boolean[9];
		int subsectionRowStart = (row / 3) * 3;
		int subsectionRowEnd = subsectionRowStart + 3;

		int subsectionColumnStart = (column / 3) * 3;
		int subsectionColumnEnd = subsectionColumnStart + 3;

		for (int r = subsectionRowStart; r < subsectionRowEnd; r++) {
			for (int c = subsectionColumnStart; c < subsectionColumnEnd; c++) {
				if (!checkConstraint(board, r, constraint, c)) return false;
			}
		}
		return true;
	}

	private static boolean columnConstraint(int[][] board, int column) {
		boolean[] constraint = new boolean[9];
		return IntStream.range(0, 9).allMatch(row -> checkConstraint(board, row, constraint, column));
	}

	private static boolean rowConstraint(int[][] board, int row) {
		boolean[] constraint = new boolean[9];
		return IntStream.range(0, 9).allMatch(column -> checkConstraint(board, row, constraint, column));
	}

	private static boolean checkConstraint(int[][] board, int row, boolean[] constraint, int column) {
		if (board[row][column] != 0) {
			if (!constraint[board[row][column] - 1]) {
				constraint[board[row][column] - 1] = true;
			} else {
				return false;
			}
		}
		return true;
	}
	
	public static int sum() {
		int tot = 0;
		int[][][] sudokus = parseFile();
		for (int i = 0; i < sudokus.length; i++) {
			int[][] solved = solveSudoku(sudokus[i]);
			tot += (100 * solved[0][0] + 10 * solved[0][1] + solved[0][2]);
		}
		return tot;
	}

	public static void main(String[] args) {
		System.out.println(sum());
	}

}
