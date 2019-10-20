package com.example.tictactoewithdatabase.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.tictactoewithdatabase.R;
import com.example.tictactoewithdatabase.model.User;

import java.util.List;

public class PlayerViewAdapter extends BaseAdapter  {
    private Context context;
    private List<User> users;

    public PlayerViewAdapter(Context context){
        this.context = context;
    }

    public void setUsers(List<User> users) {
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
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View playerView = inflater.inflate(R.layout.players_view,null);

        TextView playerName = playerView.findViewById(R.id.UserTextView);
        playerName.setText(users.get(position).getName());

        TextView playerPoints = playerView.findViewById(R.id.PointsTextView);
        playerPoints.setText(String.valueOf(users.get(position).getWins()));

        setViewHolder(playerView, playerName, playerPoints);

        return playerView;
    }

    private void setViewHolder(View playerView, TextView playerName, TextView playerPoints) {
        VieHolder vieHolder = new VieHolder();
        vieHolder.userNameText = playerName;
        vieHolder.userPointsText = playerPoints;
        playerView.setTag(vieHolder);
    }
}