package com.ik.umeh.francisumeanozie.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.ik.umeh.francisumeanozie.R;
import com.ik.umeh.francisumeanozie.carowner.CarOwnerFragment;
import com.ik.umeh.francisumeanozie.models.Car;

import java.util.List;

public class CarOwnerAdapter extends BaseAdapter {

    private final Context mContext;
    private final List<Car> car;
    private CarOwnerFragment carFragment = null;


    public CarOwnerAdapter(Context context, List<Car> car) {
        this.mContext = context;
        this.car = car;
    }

    public CarOwnerAdapter(CarOwnerFragment fragment, List<Car> car) {
        this.carFragment = fragment;
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
            convertView = layoutInflater.inflate(R.layout.car_list, null);
        }

        // 3
        final TextView name = convertView.findViewById(R.id.date_range);
        final TextView gender = convertView.findViewById(R.id.gender);
        final TextView country = convertView.findViewById(R.id.country);
        final TextView color = convertView.findViewById(R.id.color);
        final ImageView imageView = convertView.findViewById(R.id.img_view);


        name.setText(cars.getFirst_name()+" "+cars.getLast_name());
        gender.setText(cars.getGender());

        country.setText(cars.getCountry());
        color.setText(cars.getCar_color());


        //get first letter of each String item
        String firstLetter = String.valueOf(cars.getFirst_name().charAt(0));

        ColorGenerator generator = ColorGenerator.MATERIAL; // or use DEFAULT
        // generate random color
        int col = generator.getColor(cars.getFirst_name());


        TextDrawable drawable = TextDrawable.builder()
                .buildRound(firstLetter, col); // radius in px

        imageView.setImageDrawable(drawable);


        return convertView;
    }
}
