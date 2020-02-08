package com.ik.umeh.francisumeanozie.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ik.umeh.francisumeanozie.R;
import com.ik.umeh.francisumeanozie.carfilter.CarFilterFragment;
import com.ik.umeh.francisumeanozie.models.Car;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class CarFilterAdapter extends BaseAdapter {

    private final Context mContext;
    private final List<Car> car;
    private CarFilterFragment filterFragment = null;


    public CarFilterAdapter(Context context, List<Car> car) {
        this.mContext = context;
        this.car = car;
    }

    public CarFilterAdapter(CarFilterFragment fragment, List<Car> car) {
        this.filterFragment = fragment;
        this.mContext = fragment.getContext();
        this.car = car;
    }

    @Override
    public int getCount() {
        return car.size();
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
    public View getView(int position, View convertView, final ViewGroup parent) {
        // 1
        final Car cars = car.get(position);

        // 2
        if (convertView == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            convertView = layoutInflater.inflate(R.layout.filter_list, null);
        }

        // 3
        final TextView date_range = convertView.findViewById(R.id.date_range);
        final TextView gender = convertView.findViewById(R.id.gender);
        final TextView country = convertView.findViewById(R.id.country);
        final TextView color = convertView.findViewById(R.id.color);
        final CircleImageView img = convertView.findViewById(R.id.img);


        date_range.setText(cars.getStart_year()+" - "+cars.getEnd_year());
        gender.setText(cars.getGender());

        List<String> countries = new ArrayList<>();
        List<String> colors = new ArrayList<>();

        String listCountries = "";
        String listColors = "";

        if(cars.getCountries().size() > 1){
            for(int i = 0; i < 2; i++)
                countries.add(cars.getCountries().get(i));
        }


        if(cars.getColors().size() > 1){
            for(int i = 0; i < 2; i++)
                colors.add(cars.getColors().get(i));
        }


        for(String s : countries)
            listCountries += s + ",\t";

        for(String s : colors)
            listColors += s + ",\t";

        String colorList ="";
        String countryList = "";

        if(!listColors.equals(""))
             colorList = listColors.substring(0, listColors.length()-2);

        if(!listCountries.equals(""))
            countryList = listCountries.substring(0, listCountries.length()-2);


        country.setText(countryList);
        color.setText(colorList);


        if(cars.getColors().contains("Green"))
            img.setBackgroundTintList(mContext.getResources().getColorStateList(R.color.colorAccent));

        return convertView;
    }
}
