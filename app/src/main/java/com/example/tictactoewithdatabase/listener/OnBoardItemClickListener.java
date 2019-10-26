package com.example.tictactoewithdatabase.listener;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.example.tictactoewithdatabase.model.Marker;
import com.example.tictactoewithdatabase.model.User;
import com.example.tictactoewithdatabase.repository.UserRepository;
import com.example.tictactoewithdatabase.service.GameService;
import com.example.tictactoewithdatabase.service.JsonPlaceHolderApi;
import com.example.tictactoewithdatabase.service.RetrofitClientInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OnBoardItemClickListener implements AdapterView.OnItemClickListener {
    private GameService gameService;
    private ArrayAdapter boardViewAdapter;
    private UserRepository userRepository;
    private String androidId;
    private Context context;

    public OnBoardItemClickListener(GameService gameService, ArrayAdapter boardViewAdapter, UserRepository userRepository, String id, Context context) {
        this.gameService = gameService;
        this.boardViewAdapter = boardViewAdapter;
        this.userRepository = userRepository;
        this.androidId = id;
        this.context = context;

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (!gameService.boardItems.get(position).equals(Marker.BLANK)) {
            return;
        }

        gameService.markAtBoard(position, Marker.X);
        boardViewAdapter.notifyDataSetChanged();

        if (gameService.isWinner()) {
            alertDialog();
            //gameService.resetBoard();
            User user = userRepository.getUsers().get(0);
            user.setWins(user.getWins() + 1);
            userRepository.update(user);
            updateServerDb();
        } else if (gameService.isDraw()) {
            User user = userRepository.getUsers().get(0);
            user.setWins(user.getGames() + 1);
            userRepository.update(user);
            alertDialog();
            //gameService.resetBoard();
            // TODO update user
        } else {
            gameService.bestMoveDetect();
            boardViewAdapter.notifyDataSetChanged();

            if (gameService.isWinner()) {
                User user = userRepository.getUsers().get(0);
                user.setWins(user.getGames() + 1);
                userRepository.update(user);
                alertDialog();
                //gameService.resetBoard();
            } else if (gameService.isDraw()) {
                User user = userRepository.getUsers().get(0);
                user.setWins(user.getGames() + 1);
                userRepository.update(user);
                alertDialog();
                gameService.resetBoard();
            }
        }
    }
    public void updateServerDb(){
        final UserRepository userRepository = new UserRepository(context);
        userRepository.getUsers();
        List<User> androidUser = userRepository.getUsers();
        JsonPlaceHolderApi jsonPlaceHolderApi = RetrofitClientInstance.getRetrofitInstance().create(JsonPlaceHolderApi.class);
        Call<List<User>> call = jsonPlaceHolderApi.updateUser(androidUser, androidId);
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {

            }
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                /*List<User> userFromDb = response.body();
                User user = new User(userFromDb.get(0).getName(),userFromDb.get(0).getWins(),userFromDb.get(0).getWins());
                userRepository.insert(user);
                //startActivity(new Intent(getApplicationContext(), GameActivity.class));*/


            }
        });
    }

    public void alertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Start new Game?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                gameService.resetBoard();
                dialog.dismiss();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                gameService.resetBoard();
                dialog.dismiss();
            }
        });
    }

}
