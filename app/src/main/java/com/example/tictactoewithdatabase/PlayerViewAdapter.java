package com.example.tictactoewithdatabase;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.zip.Inflater;

public class PlayerViewAdapter extends BaseAdapter  {
    Context context;
    List<User> users;
    private static LayoutInflater inflater;


    public PlayerViewAdapter(Context context, List<User> users){
        this.context = context;
        this.users = users;
    }
    class VieHolder {
        public TextView userNameText;
        public TextView userPointsText;

    }

    @Override
    public int getCount() {
        return users.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        VieHolder vieHolder = new VieHolder();
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View playerView =inflater.inflate(R.layout.players_view,null);
        ImageView imageView =playerView.findViewById(R.id.PlayersGridView);
        TextView playerName = playerView.findViewById(R.id.UserTextView);
        playerName.setText(users.get(position).name);
        TextView playerPoints = playerView.findViewById(R.id.PointsTextView);
        playerPoints.setText(String.valueOf(users.get(position).getWins()));
        vieHolder.userNameText =playerName;
        vieHolder.userPointsText = playerPoints;
        playerView.setTag(vieHolder);
        //playerPoints.setText(users.get(position).getWins());
        return playerView;
    }
}