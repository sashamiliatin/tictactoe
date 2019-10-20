package com.example.tictactoewithdatabase.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.example.tictactoewithdatabase.R;
import com.example.tictactoewithdatabase.model.Marker;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class BoardViewAdapter extends ArrayAdapter<Marker> {
    private Context context;
    private List<Marker> markers;

    class ViewHolder {
        private ImageView boardItem;

        ViewHolder(View view) {
            boardItem = view.findViewById(R.id.board);
        }

        public ImageView getBoardItem() {
            return boardItem;
        }

        public void setBoardItem(ImageView boardItem) {
            this.boardItem = boardItem;
        }
    }

    public BoardViewAdapter(Context context, List<Marker> markers) {
        super(context, 0, markers);
        this.context = context;
        this.markers = markers;
    }

    @Override
    public int getCount() {
        return markers.size();
    }

    @Override
    public Marker getItem(int position) {
        return markers.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NotNull
    @Override
    public View getView(int position, View convertView, @NotNull ViewGroup parent) {
        View boardView = getView(convertView);

        ViewHolder viewHolder = (ViewHolder) boardView.getTag();

        setItemView(position, viewHolder);

        return boardView;
    }

    private void setItemView(int position, ViewHolder viewHolder) {
        switch (markers.get(position)) {
            case BLANK:
                viewHolder.getBoardItem().setImageResource(R.drawable.blank);
                break;
            case X:
                viewHolder.getBoardItem().setImageResource(R.drawable.cross);
                break;
            case O:
                viewHolder.getBoardItem().setImageResource(R.drawable.circle);
                break;
        }
    }

    @NotNull
    private View getView(View convertView) {
        if (convertView != null) {
            return convertView;
        }

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View boardView = inflater.inflate(R.layout.board_view, null);
        ViewHolder viewHolder = new ViewHolder(boardView);
        boardView.setTag(viewHolder);

        return boardView;
    }
}
