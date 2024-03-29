package com.example.tictactoe;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.widget.AdapterView;
import android.widget.Button;
import android.view.View;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class PlayerVsComputerActivity extends AppCompatActivity  {
    private Button[][] markers = new Button[3][3];
    ArrayList<Player> playersModels =  new ArrayList<>();
    ArrayList<BoardMarker> boardModels = new ArrayList<>();
    private boolean player1Turn = true;
    private int roundCount;
    private GridView gridView;

    private int countP1 =0;
    private int countP2 =0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_vs_computer);
        gridView = findViewById(R.id.grid_view);
        GridView boardgridView = findViewById(R.id.board_grid_view);
        playersModels = new ArrayList<>();

        JsonPlaceHolderApi jsonPlaceHolderApi = RetrofitClientInstance.getRetrofitInstance().create(JsonPlaceHolderApi.class);
        Call<List<Player>> call = jsonPlaceHolderApi.getAllPlayers();
        call.enqueue(new Callback<List<Player>>() {
            @Override
            public void onFailure(Call<List<Player>> call, Throwable t) {
                Toast.makeText(PlayerVsComputerActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(Call<List<Player>> call, Response<List<Player>> response) {
                List<Player> playersModels = response.body();
                PlayerViewAdapter adapter = new PlayerViewAdapter(PlayerVsComputerActivity.this,playersModels);
                gridView.setAdapter(adapter);
            }
        });
        //playersModels = new ArrayList<>();
/*        playersModels.add( new Player("Player 1",0,"X", false,false));
