package com.example.arusyn.eightqueens;

import java.util.ArrayList;



/**
 * Created by arusyn on 1/26/2016.
 */
public class Game {
    protected Board playerBoard;   //represents users moves
    private ArrayList<Integer[]> solutions = new ArrayList<Integer[]>();

    public Game (){
        this.playerBoard = new Board();
    }


    public boolean solve(Board b, int col){
    	if(col >= 8){
//    		System.out.println("should be solved from the first if, solution is this:");
			this.playerBoard = b;
    		return true;
    	}
    	for (int row = 0; row < 8; row++){
    		if(b.isAllowed(col, row)){
    			Board newb = b;
//    			System.out.println("after cloning, newb has these properties: lastSetColumn = " + newb.getLastSetColumn());
    			newb.move(col, row);
    			boolean ans;
    			ans = solve(newb, col+1);
    			if(ans == true){
    				return true;
    			}
    			b.board[col] = null;
    			b.lastSetColumn = col - 1;
    		}
    	}
    	return false;
    }


//  public ArrayList<Integer[]> solve(Board b, int col){
//	if(col >= 8){
//		System.out.println("should be solved from the first if, here's a solution");
//		b.printBoard();
//		if(!solutions.contains(b)){
//			solutions.add(b.board);
//		}
//		else return solutions;
//	}
//	for (int row = 0; row < 8; row++){
//		if(b.isAllowed(col, row)){
//			Board newb = b;
//			System.out.println("after cloning, newb has these properties: lastSetColumn = " + newb.getLastSetColumn());
//			newb.move(col, row);
//			ArrayList<Integer[]> ans;
//			ans = solve(newb, col+1);
//			if(ans.size() == 92){
//				return solutions;
//			}
//			b.board[col] = null;
//			b.lastSetColumn = col - 1;
//		}
//	}
//	return solutions;
//  }
//
//    public boolean generateSolutions(){
//    	return false;
//    }
}
