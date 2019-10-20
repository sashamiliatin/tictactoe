package com.example.tictactoewithdatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tictactoewithdatabase.Repository.UserRepository;
import com.example.tictactoewithdatabase.Service.GameService;

import java.util.List;

public class GameActivity extends AppCompatActivity {
     UserRepository userRepository;
     List<User> users;
     private List<Marker> boardItems;
     GameService gameService;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        userRepository = new UserRepository(getApplication());
        gameService = new GameService(getApplicationContext(),getApplication());
        users = userRepository.getAllUsers();
        boardItems = gameService.getBoard();
        //set board and player adapters
        final GridView playerGridView = findViewById(R.id.PlayersGridView);
        final GridView boardGridView = findViewById(R.id.BoardGridView);
        final BoardViewAdapter boardViewAdapter = new BoardViewAdapter(this,boardItems);
        boardGridView.setAdapter(boardViewAdapter);
        final PlayerViewAdapter playerViewAdapter = new PlayerViewAdapter(this,users);
        playerGridView.setAdapter(playerViewAdapter);
        //set listener on board items
        boardGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (gameService.boardItems.get(position).equals(Marker.BLANK)){
                    gameService.markAtBoard(position, Marker.X);
                    gameService.bestMoveDetect();
                    gameService.isWinner();
                    gameService.isDraw();
                    boardViewAdapter.notifyDataSetChanged();
                    users= userRepository.getAllUsers();
                    playerViewAdapter.notifyDataSetChanged();
                }
            }

        });


    }
}
