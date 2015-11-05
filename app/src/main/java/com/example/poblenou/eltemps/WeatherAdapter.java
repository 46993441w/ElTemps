package com.example.poblenou.eltemps;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.poblenou.eltemps.json.List;


public class WeatherAdapter extends ArrayAdapter<List> {
    public WeatherAdapter(Context context,int resource, java.util.List<List> objects){
        super(context,resource,objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Obtenim l'objecte en la posició corresponent
        List item = getItem(position);

        // Mirem a veure si la View s'està reusant, si no es així "inflem" la View
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.listview_row, parent, false);
        }

        // Unim el codi en les Views del Layout
        TextView tvForecast = (TextView) convertView.findViewById(R.id.tvDetail);

        // Fiquem les dades dels objectes (provinents del JSON) en el layout
        tvForecast.setText(item.getForecastString());

        // Retornem la View replena per a mostrarla
        return convertView;
    }
}
