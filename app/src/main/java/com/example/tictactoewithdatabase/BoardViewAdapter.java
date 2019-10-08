package com.example.tictactoewithdatabase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.List;

public class BoardViewAdapter extends BaseAdapter implements View.OnClickListener {
    Context context;
    private static LayoutInflater inflater;

    @Override
    public void onClick(View v) {
        int position = (int) v.getTag();
        Object object =getItem(position);
        Marker marker = (Marker) object;
    }


     private List<Marker> markers;
    public  Integer[] boardPosition = {0,1,2,10,11,12,20,21,22};

    public BoardViewAdapter(Context context, List<Marker> markers ){
        this.context = context;
        this.markers = markers;
    }

    class VieHolder{
        public ImageView boardItem;
/*        public VieHolder(View view){
            boardItem = view.findViewById(R.id.BoardGridView);
        }*/
    }
    @Override
    public int getCount() {
        return 9;
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
        View boardView =inflater.inflate(R.layout.board_view,null);
        ImageView imageView =boardView.findViewById(R.id.board);
        if (markers.get(position).equals(Marker.BLANK)){
           //imageView.setBackgroundColor(Color.TRANSPARENT);
           imageView.setImageResource(R.drawable.blank);
        }
        if (markers.get(position).equals(Marker.X)){
            imageView.setImageResource(R.drawable.cross);
        }
        if (markers.get(position).equals(Marker.O)){
           imageView.setImageResource(R.drawable.circle);
        }
        vieHolder.boardItem = imageView;
        imageView.setId(boardPosition[position]);
        boardView.setTag(vieHolder);
        //imageView.setBackgroundColor(Color.TRANSPARENT);
        return boardView;
    }
}
