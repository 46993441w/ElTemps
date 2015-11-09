package com.example.poblenou.eltemps;

import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.poblenou.eltemps.json.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetailsActivityFragment extends Fragment {

    public DetailsActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        //connexió que recull l'informació de l'altre activity
        Intent i = getActivity().getIntent();
        List item = (List) i.getSerializableExtra("item");

        TextView tvDetail = (TextView) view.findViewById(R.id.tvDetail);
        tvDetail.setText(item.getForecastString());

        return view;
    }
}
