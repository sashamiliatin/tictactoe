package com.example.tictactoewithdatabase.service;

import com.example.tictactoewithdatabase.model.Marker;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameService {

    public List<Marker> boardItems;

    public GameService() {
        fillBoard();
    }

    public void markAtBoard(int position, Marker marker) {
        boardItems.set(position, marker);
    }

    public List<Marker> getBoard() {
        return boardItems;
    }

    public Boolean isDraw() {
        for (int i = 0; i < 9; i++) {
            if (boardItems.get(i).equals(Marker.BLANK)) {
                return false;
            }
        }

        return true;
    }

    public Boolean isWinner() {
        if (!boardItems.get(0).equals(Marker.BLANK)) {
            if (boardItems.get(0).equals(boardItems.get(1)) && boardItems.get(0).equals(boardItems.get(2)) ||
                    boardItems.get(0).equals(boardItems.get(3)) && boardItems.get(0).equals(boardItems.get(6)) ||
                    boardItems.get(0).equals(boardItems.get(4)) && boardItems.get(0).equals(boardItems.get(8))) {
                return true;
            }
        }

        if (!boardItems.get(1).equals(Marker.BLANK)) {
            if (boardItems.get(1).equals(boardItems.get(4)) && boardItems.get(1).equals(boardItems.get(7))) {
                return true;
            }
        }

        if (!boardItems.get(2).equals(Marker.BLANK))
            if (boardItems.get(2).equals(boardItems.get(5)) && boardItems.get(2).equals(boardItems.get(8))) {
                return true;
            }


        if (!boardItems.get(3).equals(Marker.BLANK)) {
            if (boardItems.get(3).equals(boardItems.get(4)) && boardItems.get(3).equals(boardItems.get(5))) {
                return true;
            }
        }

        if (!boardItems.get(6).equals(Marker.BLANK)) {
            if (boardItems.get(6).equals(boardItems.get(4)) && boardItems.get(6).equals(boardItems.get(2)) ||
                    boardItems.get(6).equals(boardItems.get(7)) && boardItems.get(6).equals(boardItems.get(8))) {
                return true;
            }
        }

        return false;
    }

    public void bestMoveDetect() {
        int bestMove = detectBestMove();
        markAtBoard(bestMove, Marker.O);
    }

    private int detectBestMove() {
        if (boardItems.get(4).equals(Marker.BLANK)) {
            return 4;
        }

        // Next, check if there is a block move in the verticals.
        for (int r = 0; r < 7; r += 3) {
            int bCount = 0;
            int oCount = 0;
            int xCount = 0;
            for (int c = 0; c < 3; c++) {
                if (boardItems.get(r + c).equals(Marker.BLANK)) {
                    bCount++;
                }
                if (boardItems.get(r + c).equals(Marker.O)) {
                    oCount++;
                }
                if (boardItems.get(r + c).equals(Marker.X)) {
                    xCount++;
                }
            }

            // If there were two opponent markers and a blank,
            // move to the blank spot.
            if (((oCount == 2) && (bCount == 1)) || ((xCount == 2) && (bCount == 1))) {
                for (int c = 0; c < 3; c++) {
                    if (boardItems.get(r + c).equals(Marker.BLANK)) {
                        return r + c;
                    }
                }
            }
        }

        // Next, check rows for blockers.
        for (int c = 0; c < 3; c++) {
            int bCount = 0;
            int oCount = 0;
            int xCount = 0;
            for (int r = 0; r < 7; r += 3) {
                if (boardItems.get(r + c).equals(Marker.O)) {
                    oCount++;
                }
                if (boardItems.get(r + c).equals(Marker.BLANK)) {
                    bCount++;
                }
                if (boardItems.get(r + c).equals(Marker.X)) {
                    xCount++;
                }
            }

            // If there were two opponent markers and a blank,
            // move to the blank spot.
            if (((oCount == 2) && (bCount == 1)) || ((xCount == 2) && (bCount == 1))) {
                for (int r = 0; r < 7; r += 3) {
                    if (boardItems.get(r + c).equals(Marker.BLANK)) {
                        return r + c;
                    }
                }
            }
        }

        // And lastly for blockers, check for diagonals
        for (int j = 0; j < 1; j++) {
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
                        return i;
                    }
                }
            }

            bCount = 0;
            oCount = 0;
            xCount = 0;

            for (int i = 2; i < 7; i += 2) {
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
                for (int i = 2; i < 7; i += 2) {
                    if (boardItems.get(i).equals(Marker.BLANK)) {
                        return i;
                    }
                }
            }
        }

        // Keep generating random positions until a blank spot is found
        boolean found = false;
        Random random = new Random();

        while (!found) {
            int r = random.nextInt(8);

            if (boardItems.get(r).equals(Marker.BLANK)) {
                found = true;
                return r;
            }
        }

        return 0;
    }

    private void fillBoard() {
        boardItems = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            boardItems.add(Marker.BLANK);
        }
    }

    public void resetBoard() {
        for (int i = 0; i < 9; i++) {
            boardItems.set(i, Marker.BLANK);
        }
    }
}
