package paint;

import java.util.LinkedList;
import java.util.Queue;

public class Paint {
	private static final char[][] board = new char[10][60];
	private static Point curentPoint;
	private static Queue <Point> queue = new  LinkedList <> ();
	private static int boardWidth  = board[0].length;
	private static int boardLength = board.length;
	public static void main(String[] args) {
		
		for(int i = 0; i < boardLength; i++) {
			for(int j = 0; j < boardWidth; j++) {
				board[i][j] = ' ';
			}
		}
		for(int c = 2; c < 8; c++) {
			board[c][5] = '*';
			board[c][50] = '*';
		}
		for(int r = 5; r <= 50; r++) {
			board[2][r] = '*';
			board[8][r] = '*';
		}
		
		fill(5,40);
		
		printBoard();
	}
	private static void fill(int i, int j) {
		if(i < 0 || i > boardLength || j < 0 || j > boardWidth) {
			throw new IllegalArgumentException();
		}
		queue.add(new Point (i,j));
		board[i][j] ='*';
		while (!queue.isEmpty()) {
			curentPoint = queue.remove();
			i = curentPoint.getX();
			j = curentPoint.getY();
			addNextValidPointInQueue(i,j);
		}
	}
		

	private static void printBoard(){
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[i].length; j++) {
				System.out.print(board[i][j]);
			}
			System.out.println();
		}
	}
	
	private static void addNextValidPointInQueue(int i,int j) {
		int temp = i + 1;
		if(temp < boardLength && board[temp][j] != '*') {
			board[temp][j] = '*';
			queue.add(new Point (temp,j));
		}
		temp = i - 1;
		if(temp > 0 && board[temp][j] != '*') {
			board[temp][j] = '*';
			queue.add(new Point (temp,j));
		}
		temp = j + 1;
		if(temp < boardWidth && board[i][temp] != '*' ) {
			 board[i][temp] = '*';
			queue.add(new Point (i,temp));
		}
		temp = j - 1;
		if(temp > 0 && board[i][temp] != '*') {
			board[i][temp] = '*';
			queue.add(new Point (i,temp));
		}
	}
}