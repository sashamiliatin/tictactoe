package com.example.tictactoewithdatabase.controller;

import android.os.Bundle;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.tictactoewithdatabase.R;
import com.example.tictactoewithdatabase.adapter.BoardViewAdapter;
import com.example.tictactoewithdatabase.adapter.PlayerViewAdapter;
import com.example.tictactoewithdatabase.listener.OnBoardItemClickListener;
import com.example.tictactoewithdatabase.model.Marker;
import com.example.tictactoewithdatabase.model.User;
import com.example.tictactoewithdatabase.repository.UserRepository;
import com.example.tictactoewithdatabase.service.GameService;
import com.example.tictactoewithdatabase.viewmodel.UserViewModel;

import java.util.List;

public class GameActivity extends AppCompatActivity {

    private UserViewModel userViewModel;
    private UserRepository userRepository;
    private String androidId;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        androidId = getIntent().getStringExtra("id");
        init();
    }

    private void init() {
        initPlayers();
        initBoard();
    }

    private void initPlayers() {
        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        LiveData<List<User>> liveData = userViewModel.getUsers();
        userRepository = new UserRepository(this);
        List<User> users = userRepository.getUsers();
        final PlayerViewAdapter playerViewAdapter = new PlayerViewAdapter(this);
        playerViewAdapter.setUsers(users);
        liveData.observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                playerViewAdapter.setUsers(users);
                playerViewAdapter.notifyDataSetChanged();
            }
        });

        GridView playerGridView = findViewById(R.id.PlayersGridView);
        playerGridView.setAdapter(playerViewAdapter);
    }

    private void initBoard() {
        GameService gameService = new GameService();
        List<Marker> boardItems = gameService.getBoard();
        BoardViewAdapter boardViewAdapter = new BoardViewAdapter(this, boardItems);

        GridView boardGridView = findViewById(R.id.BoardGridView);
        boardGridView.setAdapter(boardViewAdapter);
        boardGridView.setOnItemClickListener(new OnBoardItemClickListener(gameService, boardViewAdapter, userRepository, androidId, this));
    }
}
