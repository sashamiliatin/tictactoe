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
        playersModels.add(new Player("Player 2" , 0 , "O", false,false));*/
        PlayerViewAdapter adapter = new PlayerViewAdapter(getApplicationContext(),playersModels);
        gridView.setAdapter(adapter);

        ArrayList<BoardMarker> boardModels = new ArrayList<>();
        boardModels.add(new BoardMarker(1, 0,0,"BLANK",true));
        boardModels.add(new BoardMarker(2, 0,1,"X",true));
        boardModels.add(new BoardMarker(3, 0,2,"BLANK",true));
        boardModels.add(new BoardMarker(4, 1,0,"O",true));
        boardModels.add(new BoardMarker(5, 1,1,"BLANK",true));
        boardModels.add(new BoardMarker(6, 1,2,"BLANK",true));
        boardModels.add(new BoardMarker(7, 2,0,"BLANK",true));
        boardModels.add(new BoardMarker(8, 2,1,"BLANK",true));
        boardModels.add(new BoardMarker(9, 2,2,"BLANK",true));
        BoardViewAdapter boardViewAdapter = new BoardViewAdapter(boardModels,this);
        boardgridView.setAdapter(boardViewAdapter);
        boardgridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object boardvie
                ((Button) view).setBackgroundResource(R.drawable.cross);
                ((Button) view).setText("X");
                ((Button) view).setTextColor(80000000);
            }
        });


       /* for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String buttonID = "button_" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id", getOpPackageName());
                markers[i][j] = findViewById(resID);
                markers[i][j].setOnClickListener(this);
            }
        }*/

        Button buttonReset = findViewById(R.id.button_reset);
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetGame();
            }
        });
    }


/*        @Override
    public void onClick(View view) {
        if (!((Button) view).getText().toString().equals("")) {
            return;
        }
        if (player1Turn) {
            ((Button) view).setBackgroundResource(R.drawable.cross);
            ((Button) view).setText("X");
            ((Button) view).setTextColor(getResources().getColor(android.R.color.transparent));
        } else {
            ((Button) view).setBackgroundResource(R.drawable.circle);
            ((Button) view).setText("O");
            ((Button) view).setTextColor(getResources().getColor(android.R.color.transparent));
        }
        roundCount++;
        if (checkWinner()){
            if (player1Turn){
                playerWin();
            }
            else{
                computerWin();
            }

        }
        else if(roundCount==9){
            draw();
        }
        else {
            player1Turn = !player1