package com.ik.umeh.francisumeanozie.carowner;


import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ik.umeh.francisumeanozie.R;
import com.ik.umeh.francisumeanozie.adapters.CarOwnerAdapter;
import com.ik.umeh.francisumeanozie.models.Car;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class CarOwnerFragment extends Fragment {


    private TextView emptyText, total, date_range;

    private ListView listView;
    private CarOwnerAdapter listAdapter;
    private List<Car> listItems;
    private ProgressBar mProgressView;

    Map<String,Integer> mapColor =new HashMap<String,Integer>();
    Map<String,Integer> mapCountry=new HashMap<String,Integer>();
    private static final String ARG_CARS = "cars";

    Car mCar;

    public CarOwnerFragment() {
        // Required empty public constructor
    }

    public static CarOwnerFragment newInstance(Car car) {
        CarOwnerFragment fragment = new CarOwnerFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_CARS, car);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mCar = getArguments().getParcelable(ARG_CARS);
        }
        for(int i = 0; i < mCar.getColors().size(); i++){

            String str1 = mCar.getColors().get(i);
            String cap1 = str1.substring(0, 1).toUpperCase() + str1.substring(1);

            mapColor.put(cap1, i);

        }

        for(int i = 0; i < mCar.getCountries().size(); i++){

            String str1 = mCar.getCountries().get(i);
            String cap1 = str1.substring(0, 1).toUpperCase() + str1.substring(1);

            mapCountry.put(cap1, i);

        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_car_owner, container, false);

        listView = (ListView) view.findViewById(R.id.list);
        mProgressView = view.findViewById(R.id.loading);
        total = view.findViewById(R.id.total);
        date_range = view.findViewById(R.id.date_range);

        emptyText = view.findViewById(R.id.empty);

        listItems = new ArrayList<>();

        listAdapter = new CarOwnerAdapter(getContext(), listItems);


        listView.setAdapter(listAdapter);

        readData();


        return view;
    }



    /**
     * reads the excel file line by line and handles the filtration
     * first checks if gender, country and color is not null,
     * second checks if gender and color is not null
     * third checks if gender and country is not null
     * fourth checks if color and gender is not null
     * fifth checks if gender is not null
     * sixth checks if country is not null
     * lastly checks if color is not null
     *
     */
    public void readData(){

        InputStream is = getResources().openRawResource(R.raw.data);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));

        String line = "";

        mProgressView.setVisibility(View.VISIBLE);

        Log.e("color", mCar.getGender());
        Log.e("color", mCar.getColors().toString());
        String cap = "";
        String str = "";

        //capitalizing the first letter
        if(mCar.getGender() != null && !(mCar.getGender().equals(""))) {
            str = mCar.getGender();
            cap = str.substring(0, 1).toUpperCase() + str.substring(1);
        }

        try {
            reader.readLine();

            while((line = reader.readLine()) != null){


                String tokens[] = line.split(",");

                if(tokens[8].equals(cap) && mapColor.get(tokens[7]) != null && mapCountry.get(tokens[4]) != null){

                    Car car = new Car();
                    car.setFirst_name(tokens[1]);
                    car.setLast_name(tokens[2]);
                    car.setEmail(tokens[3]);
                    car.setCountry(tokens[4]);
                    car.setCar_model(tokens[5]);
                    //car.setCar_model_year(tokens[6]);
                    car.setCar_color(tokens[7]);
                    car.setGender(tokens[8]);
                    car.setJob_title(tokens[9]);

                    listItems.add(car);

                }else if(tokens[8].equals(cap) && mapColor.get(tokens[7]) != null){

                    Car car = new Car();
                    car.setFirst_name(tokens[1]);
                    car.setLast_name(tokens[2]);
                    car.setEmail(tokens[3]);
                    car.setCountry(tokens[4]);
                    car.setCar_model(tokens[5]);
                    //car.setCar_model_year(tokens[6]);
                    car.setCar_color(tokens[7]);
                    car.setGender(tokens[8]);
                    car.setJob_title(tokens[9]);

                    listItems.add(car);

                }else if(tokens[8].equals(cap) && mapCountry.get(tokens[4]) != null){

                    Car car = new Car();
                    car.setFirst_name(tokens[1]);
                    car.setLast_name(tokens[2]);
                    car.setEmail(tokens[3]);
                    car.setCountry(tokens[4]);
                    car.setCar_model(tokens[5]);
                    //car.setCar_model_year(tokens[6]);
                    car.setCar_color(tokens[7]);
                    car.setGender(tokens[8]);
                    car.setJob_title(tokens[9]);

                    listItems.add(car);

                }else if(mapColor.get(tokens[7]) != null && mapCountry.get(tokens[4]) != null){

                    Car car = new Car();
                    car.setFirst_name(tokens[1]);
                    car.setLast_name(tokens[2]);
                    car.setEmail(tokens[3]);
                    car.setCountry(tokens[4]);
                    car.setCar_model(tokens[5]);
                    //car.setCar_model_year(tokens[6]);
                    car.setCar_color(tokens[7]);
                    car.setGender(tokens[8]);
                    car.setJob_title(tokens[9]);

                    listItems.add(car);

                }else if(mapCountry.get(tokens[4]) != null){

                    Car car = new Car();
                    car.setFirst_name(tokens[1]);
                    car.setLast_name(tokens[2]);
                    car.setEmail(tokens[3]);
                    car.setCountry(tokens[4]);
                    car.setCar_model(tokens[5]);
                    //car.setCar_model_year(tokens[6]);
                    car.setCar_color(tokens[7]);
                    car.setGender(tokens[8]);
                    car.setJob_title(tokens[9]);

                    listItems.add(car);

                }else if(mapColor.get(tokens[7]) != null){

                    Car car = new Car();
                    car.setFirst_name(tokens[1]);
                    car.setLast_name(tokens[2]);
                    car.setEmail(tokens[3]);
                    car.setCountry(tokens[4]);
                    car.setCar_model(tokens[5]);
                    //car.setCar_model_year(tokens[6]);
                    car.setCar_color(tokens[7]);
                    car.setGender(tokens[8]);
                    car.setJob_title(tokens[9]);

                    listItems.add(car);

                }else if(tokens[8].equals(cap)){

                    Car car = new Car();
                    car.setFirst_name(tokens[1]);
                    car.setLast_name(tokens[2]);
                    car.setEmail(tokens[3]);
                    car.setCountry(tokens[4]);
                    car.setCar_model(tokens[5]);
                    //car.setCar_model_year(tokens[6]);
                    car.setCar_color(tokens[7]);
                    car.setGender(tokens[8]);
                    car.setJob_title(tokens[9]);

                    listItems.add(car);

                }

            }
            listAdapter.notifyDataSetChanged();
            if(listAdapter.getCount() == 0){
                total.setText("0");
                date_range.setText(mCar.getStart_year()+" - "+mCar.getEnd_year());
                emptyText.setVisibility(View.VISIBLE);
            }else {
                total.setText(listAdapter.getCount()+"");
                date_range.setText(mCar.getStart_year()+" - "+mCar.getEnd_year());
                emptyText.setVisibility(View.GONE);
            }

            reader.close();
            mProgressView.setVisibility(View.GONE);

        } catch (IOException e) {
            e.printStackTrace();
            mProgressView.setVisibility(View.VISIBLE);
        }
    }


}


