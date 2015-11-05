package com.example.poblenou.eltemps;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.poblenou.eltemps.json.Forecast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.security.auth.callback.Callback;



/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private List items;
    private WeatherAdapter adapter;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        String[] data = {
          "Lun 19/10 - Lluvioso - 31/17",
          "Mar 20/10 - Soleado - 31/17",
          "Mie 21/10 - Nublado - 31/17",
          "Jue 22/10 - Niebla - 31/17",
          "Vie 23/10 - Soleado - 31/17",
          "Sab 24/10 - Soleado - 31/17",
          "Dom 25/10 - Lluvioso - 31/17",
        };
        //String data = refresh("Barcelona,es");
        items = new ArrayList<>(Arrays.asList(data));
        adapter = new WeatherAdapter(getContext(), R.layout.listview_row, items);
        //adapter = new ArrayAdapter<>(getContext(), R.layout.listview_row, R.id.txtRow, items);
        ListView lvRow = (ListView) rootView.findViewById(R.id.llista);
        lvRow.setAdapter(adapter);
        lvRow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                com.example.poblenou.eltemps.json.List item = (com.example.poblenou.eltemps.json.List) parent.getItemAtPosition(position);

                Intent i = new Intent(getContext(), DetailActivity.class);
                i.putExtra("item", item);
                startActivity(i);
            }
        });

        refresh();
        return rootView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.weather_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.action_refresh) {
            refresh();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void refresh() {
        OwnApiClient apiClient = new OwnApiClient();
        apiClient.updateForecasts(adapter,getContext());
    }
}
