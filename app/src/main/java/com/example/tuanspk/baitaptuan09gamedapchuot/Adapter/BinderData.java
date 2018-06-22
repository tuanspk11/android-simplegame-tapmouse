package com.example.tuanspk.baitaptuan09gamedapchuot.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.tuanspk.baitaptuan09gamedapchuot.R;

import java.util.HashMap;
import java.util.List;

public class BinderData extends BaseAdapter {

    // XML node keys
    static final String KEY_TAG = "rankdata";                                                       // parent node
    static final String KEY_ID = "id";
    static final String KEY_NAME = "name";
    static final String KEY_SCORE = "score";

    LayoutInflater inflater;
    List<HashMap<String, String>> rankDataCollection;
    ViewRank rank;

    public BinderData() {
        // TODO Auto-generated constructor stub
    }

    public BinderData(Activity activity, List<HashMap<String, String>> map) {
        this.rankDataCollection = map;

        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return 0;
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

        View view = convertView;

        if (convertView == null) {
            view = inflater.inflate(R.layout.listrow_layout, null);

            rank = new ViewRank();
            rank.textViewName = (TextView) view.findViewById(R.id.textview_name);                   // Name
            rank.textViewScore = (TextView) view.findViewById(R.id.textview_score);                 // Score

            view.setTag(rank);
        } else {
            rank = (ViewRank) view.getTag();
        }

        // Setting all values in listview
        rank.textViewName.setText(rankDataCollection.get(position).get(KEY_NAME));
        rank.textViewScore.setText(rankDataCollection.get(position).get(KEY_SCORE));

        return view;
    }

    static class ViewRank {
        TextView textViewName;
        TextView textViewScore;
    }
}
