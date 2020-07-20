package paint;

import java.util.LinkedList;
import java.util.Queue;

public class Paint {
	private final char[][] board ; 
	private Point curentPoint;
	private Queue <Point> queue ; 
	private  int boardWidth ; 
	private  int boardLength ;

	
	public Paint (int column, int row) {
		board = new char[column][row];
		boardWidth = board[0].length;
		boardLength = board.length;
		queue = new  LinkedList <> ();
		
		for(int i = 0; i < boardLength; i++) {
			for(int j = 0; j < boardWidth; j++) {
				board[i][j] = ' ';
			}
		}
	}
	
	public   void drawShape(Point ...points ) {
		for(int i = 1; i<points.length; i++) {
			drawLine(points[i-1],points[i]);
		}
		
	}
	private  void drawLine(Point p1, Point p2) {
		int x1 = p1.getX();
		int x2 = p2.getX();
		int y1 = p1.getY();
		int y2 = p2.getY();
		if(y1 < y2) {
			for (int c = y1; c < y2 ; c++) {
				board[x1][c] = '*';
			} 
		}
		else {
			for (int c = y1; c > y2 ; c--) {
				board[x1][c] = '*';
			} 
		}
		if(x1 < x2) {
			for (int r = x1; r < x2 ; r++) {
				board[r][y2] = '*';
			}
		}
		else {
			for (int r = x1; r > x2 ; r--) {
				board[r][y2] = '*';
			}
		}
			
	}
	
	public static void main(String[] args) {
		Paint paint =new Paint(15,60);
		Point p1 = new Point(5,2);
		Point p2 = new Point(3,10);
		Point p3 = new Point(5,25);
		Point p4 = new Point(12,55);
		
		paint.drawShape(p1,p2,p3,p4,p1);
		
		paint.fill(14,6);
		
		paint.printBoard();
	}
	private  void fill(int i, int j) {
		if(i < 0 || i > boardLength || j < 0 || j > boardWidth) {
			throw new IllegalArgumentException();
		}
		while(board[i][j] =='*') {
			i++;
			j++;
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
		

	private  void printBoard(){
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[i].length; j++) {
				System.out.print(board[i][j]);
			}
			System.out.println();
		}
	}
	
	private  void addNextValidPointInQueue(int i,int j) {
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