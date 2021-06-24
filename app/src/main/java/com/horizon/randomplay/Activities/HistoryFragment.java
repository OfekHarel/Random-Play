package com.horizon.randomplay.Activities;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.horizon.randomplay.R;
import com.horizon.randomplay.util.SharedData;

import java.util.ArrayList;

public class HistoryFragment extends BaseFragment {

    private ListView historyList;
    private ArrayAdapter<String> adapter;

    private ImageButton clear;
    private TextView txt;

    public HistoryFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_history, container, false);

        this.historyList = rootView.findViewById(R.id.hist_list);
        this.adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, SharedData.getInstance().getHistory());

        this.adapter = new ArrayAdapter<String>
                (getContext(), android.R.layout.simple_list_item_1, SharedData.getInstance().getHistory()){
            @Override
            public View getView(int position, View convertView, ViewGroup parent){
                View view = super.getView(position, convertView, parent);
                TextView tv = (TextView) view.findViewById(android.R.id.text1);
                tv.setTextColor(getResources().getColor(R.color.app_black));
                tv.setTextSize(20);
                tv.setPadding(0, 2,0 ,2 );
                return view;
            }
        };

        this.historyList.setAdapter(adapter);

        this.clear = rootView.findViewById(R.id.clear_hist);
        this.clear.setOnClickListener(v -> {
            preformVibration(getContext(), 2);
            this.adapter.clear();
            SharedData.getInstance().setHistory(new ArrayList<>());
            whenVoid();
        });

        this.txt = rootView.findViewById(R.id.hist_txt);


        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        whenVoid();
    }

    private void whenVoid() {
        if (this.adapter.getCount() <= 0) {
            this.txt.setVisibility(View.VISIBLE);
            this.clear.setVisibility(View.INVISIBLE);
        } else {
            this.txt.setVisibility(View.INVISIBLE);
            this.clear.setVisibility(View.VISIBLE);
        }
    }
}