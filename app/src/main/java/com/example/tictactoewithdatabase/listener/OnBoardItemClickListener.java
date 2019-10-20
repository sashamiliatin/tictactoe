package com.example.tictactoewithdatabase.listener;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.example.tictactoewithdatabase.model.Marker;
import com.example.tictactoewithdatabase.model.User;
import com.example.tictactoewithdatabase.repository.UserRepository;
import com.example.tictactoewithdatabase.service.GameService;

public class OnBoardItemClickListener implements AdapterView.OnItemClickListener {
    private GameService gameService;
    private ArrayAdapter boardViewAdapter;
    private UserRepository userRepository;

    public OnBoardItemClickListener(GameService gameService, ArrayAdapter boardViewAdapter, UserRepository userRepository) {
        this.gameService = gameService;
        this.boardViewAdapter = boardViewAdapter;
        this.userRepository = userRepository;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (!gameService.boardItems.get(position).equals(Marker.BLANK)) {
            return;
        }

        gameService.markAtBoard(position, Marker.X);
        boardViewAdapter.notifyDataSetChanged();

        if (gameService.isWinner()) {
            gameService.resetBoard();
            User user = userRepository.getUsers().get(0);
            user.setWins(user.getWins() + 1);
            userRepository.update(user);
        } else if (gameService.isDraw()) {
            gameService.resetBoard();
            // TODO update user
        } else {
            gameService.bestMoveDetect();
            boardViewAdapter.notifyDataSetChanged();

            if (gameService.isWinner()) {
                gameService.resetBoard();
            } else if (gameService.isDraw()) {
                gameService.resetBoard();
            }
        }
    }
}
