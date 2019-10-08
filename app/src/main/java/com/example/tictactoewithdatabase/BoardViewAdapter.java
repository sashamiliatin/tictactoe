package com.example.tictactoewithdatabase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import java.util.List;

public class BoardViewAdapter extends ArrayAdapter<Marker> {
     private Context context;
     private static LayoutInflater inflater;
     private List<Marker> markers;

     class VieHolder{
        public ImageView boardItem;
    }

    public BoardViewAdapter(Context context, List<Marker> markers ){
        super(context,0,markers);
        this.context = context;
        this.markers = markers;
    }

    @Override
    public int getCount() {
        return markers.size();
    }

    @Override
    public Marker getItem(int position) {
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
        View boardView =inflater.inflate(R.layout.board_view,null);
        ImageView imageView =boardView.findViewById(R.id.board);

        switch (markers.get(position)) {
            case BLANK:
                imageView.setImageResource(R.drawable.blank);
                break;
            case X:
                imageView.setImageResource(R.drawable.cross);
                break;
            case O:
                imageView.setImageResource(R.drawable.circle);
                break;
        }

        vieHolder.boardItem = imageView;
        boardView.setTag(vieHolder);
        return boardView;
    }
}
