package com.example.arusyn.eightqueens;

/**
 * Created by arusyn on 2/3/2016.
 */

public class Board {
    protected Integer[] board;   //represents each column of board
    protected int lastSetColumn;

    public Board(){
        this.board = new Integer[8];
        this.lastSetColumn = -1;
    }

    public Integer[] getBoard(){
        return this.board;
    }

    public int getLastSetColumn(){
        return this.lastSetColumn;
    }

    public void setLastSetColumn(int lastSetColumn){
        this.lastSetColumn = lastSetColumn;
    }

    public void setBoard(Integer[] newBoard){
        this.board = newBoard;
    }

    public void move(int col, int row){
//        System.out.println("Gonna see if we can put a queen in ["+col+"]["+row+"]");
        if(isAllowed(col, row)){
            this.board[col] = row;
//            if (col != 0 && col == this.lastSetColumn){
//
//            } else {
                this.lastSetColumn = col;
//                System.out.println("You just set a new queen. Just updated lastSetColumn to: " + lastSetColumn);
//            }
        }
    }

    protected boolean isAllowed(int col, int row){
//        System.out.println("you're trying to place a queen at col: " + col + ", row: " + row + " and lastSetCol is: " + this.lastSetColumn);
//        System.out.println("and here's what the board looks like:");

        if(board[col] != null && board[col] == row && this.lastSetColumn == col){         //lets you click on existing cells, but only in the last set Column
//            System.out.println("You are clicking on an existing queen");
            if(col!=0){
                this.lastSetColumn = col - 1;
            }
//            System.out.println("You just flipped an existing queen. Just updated lastSetColumn to: " + this.lastSetColumn);
            return true;
        }

        if(col!= 0 && col!= this.lastSetColumn+1){   //check if move is in next open column
//            System.out.println("needs to be in the next open column (" + (this.lastSetColumn+1) + ") , you're trying to put it in col:" + col);
            return false;
        }

//        System.out.println("isAllowed received: (" + col + ", " + row + "), board[x] is: " + board[col]);


        if(board[col] != null){ //if there is an existing queen in this column
//            System.out.println("blocked in column by existing queen in: col:" + col + ", row: " + board[col]);
            return false;
        }
        for(int c = 0; c<8; c++){
            if(board[c] != null){   // calculating slope will break if this is null?
                if (board[c] == row){        //check if there is an existing queen in this row
//                    System.out.println("blocked in row by existing queen in: col:" +  c + ", row: " + board[c]);
                    return false;
                }
//                System.out.println("calculating slope using: row: " + row + ", board[c]: " + board[c] + ", col: " + col + ", c: " + c);
//                System.out.println("top part is = " + (row- board[c]));
//                System.out.println("bottom part is = " + (col-c));
                double slope = (double)(row- board[c])/(double)(col-c);  //calculate slope and check diagonal overlap
                if((slope==1) || (slope==-1)){
//                    System.out.println("blocked diagonally by existing queen in: col:" +  c + ", row: " + board[c] + " and slope is: " + slope);
                    return false;
                }
            }
        }
        return true;
    }


}
