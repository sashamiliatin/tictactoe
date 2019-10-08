package com.example.tictactoewithdatabase;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.widget.GridView;
import android.widget.Toast;

import java.util.List;
import java.util.Random;

public class  NewGameService  {
    User user;
     private int points =0;
     private Context context;
     private List<BoardItem.Marker> board;
     GridView pointsGridview , boardGridView;



     private GameService gameService ;//= new GameService();
public NewGameService(List<BoardItem.Marker> boardItems) {
    //this.context = context;
    //this.boardGridView = boardGridView;
    //this.pointsGridview =pointsGridview;

	//boardGridView =  ((Activity)context).findViewById(R.id.BoardGridView);
    board =  boardItems;
    if (gameService.isDraw()){
			board.clear();
        }
    if (gameService.isWinner()){
    	board.clear();
        }
    bestMoveDetect();


    }


         public void bestMoveDetect(){
           if (board.get(4).equals(BoardItem.Marker.BLANK)){
           /*if((!(board.get(1).equals(BoardItem.Marker.BLANK)) &&
				!(board.get(7).equals(BoardItem.Marker.BLANK))) ||
			   !((board.get(3).equals(BoardItem.Marker.BLANK)) &&
				!(board.get(5).equals(BoardItem.Marker.BLANK))) ||
			   !((board.get(0).equals(BoardItem.Marker.BLANK)) &&
				!(board.get(8).equals(BoardItem.Marker.BLANK))) ||
			   !((board.get(2).equals(BoardItem.Marker.BLANK)) &&
				!(board.get(6).equals(BoardItem.Marker.BLANK)))) {
           		gameService.markAtBoard(4, BoardItem.Marker.O);
           		return;
			}*/
           gameService.markAtBoard(4, BoardItem.Marker.O);
           		return;
		}

		// Next, check if there is a block move in the verticals.
		for(int r = 0; r < 9; r+=3) {
			int bCount = 0;
			int oCount = 0;
			int xCount = 0;
			for(int c = 0; c < 3; c++) {
				if(!board.get(r+c).equals(BoardItem.Marker.BLANK)) {
					bCount++;
				}
				 if (board.get(r+c).equals(BoardItem.Marker.O)){
					oCount++;
				}
				 if (board.get(r+c).equals(BoardItem.Marker.X)){
				 	xCount++;
				 }
			}

			// If there were two opponent markers and a blank,
			// move to the blank spot.
			if((oCount == 2) && (bCount == 1)||(xCount==2)&&(bCount==1)) {
				for(int c = 0; c < 3; c++) {
					if(board.get(r+c).equals(BoardItem.Marker.BLANK)) {
							gameService.markAtBoard(r+c , BoardItem.Marker.O);
							return;
					}
				}
			}
		}

		// Next, check rows for blockers.
		for(int c = 0; c < 3; c++) {
			int bCount = 0;
			int oCount = 0;
			int xCount = 0;
			for(int r = 3; r < 7; r+=3) {
				if(board.get(r+c).equals(BoardItem.Marker.O)) {
					oCount++;
				}
				if(board.get(r+c).equals(BoardItem.Marker.BLANK)) {
					bCount++;
				}
				if(board.get(r+c).equals(BoardItem.Marker.X)) {
					bCount++;
				}
			}

			// If there were two opponent markers and a blank,
			// move to the blank spot.
			if((oCount == 2) && (bCount == 1)||(xCount==2)&&(bCount==1)) {
				for(int r = 3; r < 7; r+=3) {
					if(board.get(r+c).equals(BoardItem.Marker.BLANK)) {
							gameService.markAtBoard(r+c, BoardItem.Marker.O);
							return;
					}
				}
			}
		}

		// And lastly for blockers, check for diagonals
		for (int j =0;j<1;j++) {
			int bCount = 0;
			int oCount = 0;
			int xCount = 0;
			for (int i = 0; i < 9; i += 4) {
				if (!board.get(i).equals(BoardItem.Marker.O)) {
					oCount++;
				}
				if (board.get(i).equals(BoardItem.Marker.BLANK)) {
					bCount++;
				}
			}
			if ((oCount == 2) && (bCount == 1) || (xCount == 2) && (bCount == 1)) {
				for (int i = 0; i < 9; i += 4) {
					if (board.get(i).equals(BoardItem.Marker.BLANK)) {
						gameService.markAtBoard(i, BoardItem.Marker.O);
						return;
					}
				}
			}

			bCount = 0;
			oCount = 0;
			xCount = 0;
			for (int i = 2; i < 7;i+=2) {
				if (board.get(i).equals(BoardItem.Marker.O)) {
					oCount++;
				}
				if (board.get(i).equals(BoardItem.Marker.BLANK)) {
					bCount++;
				}
				if (board.get(i).equals(BoardItem.Marker.X)) {
					bCount++;
				}
			}
			if ((oCount == 2) && (bCount == 1)||(xCount==2)&&(bCount==1)) {
				for (int i = 2; i < 7;i+=2) {
					if (board.get(i).equals(BoardItem.Marker.BLANK)) {
						gameService.markAtBoard(i, BoardItem.Marker.O);
						return;
					}
				}
			}
		}

		// If still available, take the center; always a good move.
		if(board.get(4).equals(BoardItem.Marker.BLANK)) {
				gameService.markAtBoard(4, BoardItem.Marker.O);
				return;
		}

		// TODO: Add logic that moves in such a way to force
		// human to make a block move.

		// Keep generating random positions until a blank spot is found
		boolean found = false;
		Random random = new Random();
		while(!found) {
			int r = random.nextInt(8);
			if(board.get(r).equals(BoardItem.Marker.BLANK)) {
					gameService.markAtBoard(r, BoardItem.Marker.O);
					found = true;

			}
		}
 }




}

