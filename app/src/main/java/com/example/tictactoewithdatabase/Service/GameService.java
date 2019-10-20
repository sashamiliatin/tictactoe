package com.example.tictactoewithdatabase.Service;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import com.example.tictactoewithdatabase.Marker;
import com.example.tictactoewithdatabase.User;
import com.example.tictactoewithdatabase.Repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameService {

     Application application ;
     private Context context;
     List<User> users;
     UserRepository userRepository ;



	public List<Marker> boardItems ;


     public GameService(Context context , Application application){
         boardItems = new ArrayList();
         boardItems.add(Marker.BLANK);
         boardItems.add(Marker.BLANK);
         boardItems.add(Marker.BLANK);
         boardItems.add(Marker.BLANK);
         boardItems.add(Marker.BLANK);
         boardItems.add(Marker.BLANK);
         boardItems.add(Marker.BLANK);
         boardItems.add(Marker.BLANK);
         boardItems.add(Marker.BLANK);
         //boardItems = resetBoard();
         application.getApplicationContext();
         this.context = context;
         this.application = application;
         this.userRepository = new UserRepository(application);
         this.users = userRepository.getAllUsers();

     }

     public List<Marker> resetBoard(){
         for (int i = 0;i <9;i++){
             boardItems.set(i,Marker.BLANK);
         }
         return boardItems;
     }

     public void markAtBoard(int position , Marker marker){
     	if (boardItems.get(position).equals(Marker.BLANK)){
         boardItems.set(position, marker);}
     	else Toast.makeText(context, "Is not Empty", Toast.LENGTH_SHORT).show();
     }

     public List<Marker> getBoard(){
         return boardItems;
     }

     public Boolean isDraw(){
         boolean draw = true;
         for (int i =0; i<9;i++){
             if (boardItems.get(i).equals(Marker.BLANK)){
                  return draw = false;
             }
         }
         return draw;
     }

     public Boolean isWinner(){
         if (!boardItems.get(0).equals(Marker.BLANK))
             if (boardItems.get(0).equals(boardItems.get(1))&&boardItems.get(0).equals(boardItems.get(2))||
                     boardItems.get(0).equals(boardItems.get(3))&&boardItems.get(0).equals(boardItems.get(6))||
                             boardItems.get(0).equals(boardItems.get(4))&&boardItems.get(0).equals(boardItems.get(8))){
                 updatePointsAdnGames(boardItems.get(0).toString());
                 Toast.makeText(context,""+boardItems.get(0).toString()+ " Wins!!!",Toast.LENGTH_LONG).show();
                return true;
         }
         if (!boardItems.get(1).equals(Marker.BLANK))
             if (boardItems.get(1).equals(boardItems.get(4))&&boardItems.get(1).equals(boardItems.get(7))){
                 updatePointsAdnGames(boardItems.get(1).toString());
                 Toast.makeText(context,""+boardItems.get(1).toString()+ " Wins!!!",Toast.LENGTH_LONG).show();
                return true;
             }
         if (!boardItems.get(2).equals(Marker.BLANK))
             if (boardItems.get(2).equals(boardItems.get(5))&&boardItems.get(2).equals(boardItems.get(8))){
                 updatePointsAdnGames(boardItems.get(2).toString());
                 Toast.makeText(context,""+boardItems.get(2).toString()+ " Wins!!!",Toast.LENGTH_LONG).show();
                return true;
             }


         if (!boardItems.get(3).equals(Marker.BLANK)){
             if (boardItems.get(3).equals(boardItems.get(4))&&boardItems.get(3).equals(boardItems.get(5))){
                 updatePointsAdnGames(boardItems.get(3).toString());
                 Toast.makeText(context,""+boardItems.get(3).toString()+ " Wins!!!",Toast.LENGTH_LONG).show();
                 return true;
             }
         }
         if (!boardItems.get(6).equals(Marker.BLANK)){
             if (boardItems.get(6).equals(boardItems.get(4))&&boardItems.get(6).equals(boardItems.get(2))||
             boardItems.get(6).equals(boardItems.get(7))&&boardItems.get(6).equals(boardItems.get(8))){
                 updatePointsAdnGames(boardItems.get(6).toString());
                 Toast.makeText(context,""+boardItems.get(6).toString()+ " Wins!!!",Toast.LENGTH_LONG).show();
                 return true;
             }
         }
         return false;
     }

     private void updatePointsAdnGames(String boardMarker){
        int wins = userRepository.getAllUsers().get(0).getWins();
        int games = userRepository.getAllUsers().get(0).getGames();
        if (boardMarker.equals("X")){
        	userRepository.getAllUsers().get(0).setWins(wins+1);
           	userRepository.getAllUsers().get(0).setGames(games+1);
           	//userRepository.update(users.get(0));
           	Toast.makeText(context,""+users.get(0).getWins(),Toast.LENGTH_SHORT).show();
        }

        if (boardMarker.equals("O")){
           users.get(0).setGames(games+1);
           //userRepository.update(users.get(0));
           Toast.makeText(context,""+ users.get(0).getWins() ,Toast.LENGTH_LONG).show();
        }

    }
    
     public void bestMoveDetect(){

     	if (isWinner()){
     		resetBoard();
     		return;
		}

     	if (isDraw()){
     		int games = userRepository.getAllUsers().get(0).getGames();
     		userRepository.getAllUsers().get(0).setGames(games+1);
     	    Toast.makeText(context,"Draw!!!",Toast.LENGTH_LONG).show();
     		resetBoard();
     		return;
		}

     	
           if (boardItems.get(4).equals(Marker.BLANK)){
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
           markAtBoard(4, Marker.O);
           		return;
		}

		// Next, check if there is a block move in the verticals.
		for(int r = 0; r < 7; r+=3) {
			int bCount = 0;
			int oCount = 0;
			int xCount = 0;
			for(int c = 0; c < 3; c++) {
				if(boardItems.get(r+c).equals(Marker.BLANK)) {
					bCount++;
				}
				 if (boardItems.get(r+c).equals(Marker.O)){
					oCount++;
				}
				 if (boardItems.get(r+c).equals(Marker.X)){
				 	xCount++;
				 }
			}

			// If there were two opponent markers and a blank,
			// move to the blank spot.
			if(((oCount == 2) && (bCount == 1))||((xCount==2)&&(bCount==1))) {
				for(int c = 0; c < 3; c++) {
					if(boardItems.get(r+c).equals(Marker.BLANK)) {
							markAtBoard(r+c , Marker.O);
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
			for(int r = 0; r < 7; r+=3) {
				if(boardItems.get(r+c).equals(Marker.O)) {
					oCount++;
				}
				if(boardItems.get(r+c).equals(Marker.BLANK)) {
					bCount++;
				}
				if(boardItems.get(r+c).equals(Marker.X)) {
					xCount++;
				}
			}

			// If there were two opponent markers and a blank,
			// move to the blank spot.
			if(((oCount == 2) && (bCount == 1))||((xCount==2)&&(bCount==1))) {
				for(int r = 0; r < 7; r+=3) {
					if(boardItems.get(r+c).equals(Marker.BLANK)) {
							markAtBoard(r+c, Marker.O);
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
				if (boardItems.get(i).equals(Marker.O)) {
					oCount++;
				}
				if (boardItems.get(i).equals(Marker.BLANK)) {
					bCount++;
				}
				if (boardItems.get(i).equals(Marker.X)) {
					xCount++;
				}

			}
			if (((oCount == 2) && (bCount == 1)) || ((xCount == 2) && (bCount == 1))) {
				for (int i = 0; i < 9; i += 4) {
					if (boardItems.get(i).equals(Marker.BLANK)) {
						markAtBoard(i, Marker.O);
						return;
					}
				}
			}

			bCount = 0;
			oCount = 0;
			xCount = 0;
			for (int i = 2; i < 7;i+=2) {
				if (boardItems.get(i).equals(Marker.O)) {
					oCount++;
				}
				if (boardItems.get(i).equals(Marker.BLANK)) {
					bCount++;
				}
				if (boardItems.get(i).equals(Marker.X)) {
					xCount++;
				}
			}
			if (((oCount == 2) && (bCount == 1))||((xCount==2)&&(bCount==1))) {
				for (int i = 2; i < 7;i+=2) {
					if (boardItems.get(i).equals(Marker.BLANK)) {
						markAtBoard(i, Marker.O);
						return;
					}
				}
			}
		}

		// If still available, take the center; always a good move.
		if(boardItems.get(4).equals(Marker.BLANK)) {
				markAtBoard(4, Marker.O);
				return;
		}

		// TODO: Add logic that moves in such a way to force
		// human to make a block move.

		// Keep generating random positions until a blank spot is found
		boolean found = false;
		Random random = new Random();
		while(!found) {
			int r = random.nextInt(8);
			if(boardItems.get(r).equals(Marker.BLANK)) {
					markAtBoard(r, Marker.O);
					found = true;

			}
		}
 }

}
