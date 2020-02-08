package com.ik.umeh.francisumeanozie.carfilter;


import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ik.umeh.francisumeanozie.R;
import com.ik.umeh.francisumeanozie.adapters.CarFilterAdapter;
import com.ik.umeh.francisumeanozie.models.Car;
import com.ik.umeh.francisumeanozie.volley.VolleyErrorHandler;
import com.ik.umeh.francisumeanozie.volley.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class CarFilterFragment extends Fragment {


    public static String TAG = "CarFragment";

    private TextView  emptyText;

    private ListView listView;
    private CarFilterAdapter listAdapter;
    private List<Car> listItems;

    private final String API = "https://ven10.co/assessment/filter.json";

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    private ProgressBar mProgressView;

    private OnCarFilterClickListener mListener;

    public CarFilterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_car_filter, container, false);

        listView = (ListView) view.findViewById(R.id.list);
        mProgressView = view.findViewById(R.id.loading);


        listItems = new ArrayList<>();

        listAdapter = new CarFilterAdapter(getContext(), listItems);


        listView.setAdapter(listAdapter);
        carFilterList();


        emptyText = view.findViewById(R.id.empty);



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Car car = listItems.get(i);

                if (mListener != null){
                    mListener.OnCarFilterClick(car);
                }

            }
        });

        return view;
    }

    private void carFilterList(){
        mProgressView.setVisibility(View.VISIBLE);
        JsonArrayRequest jsArrayRequest = new JsonArrayRequest(Request.Method.GET, API, null,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {

                        try{
                            Log.e(TAG, "onResponse"+ response.toString());

                            for(int i = 0; i < response.length(); i++){

                                JSONObject obj = response.getJSONObject(i);
                                JSONArray countries = obj.getJSONArray("countries");
                                ObjectMapper objectMapper = new ObjectMapper();
                                //FAIL_ON_UNKNOWN_PROPERTIES prevents crash if reponse has field the object dont
                                objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
                                Car item = objectMapper.readValue(obj.toString(),Car.class);

                                listItems.add(item);

                            }
                            listAdapter.notifyDataSetChanged();

                            if(listAdapter.getCount() == 0){
                                emptyText.setVisibility(View.VISIBLE);
                                listView.setEmptyView(emptyText);
                            }


                            mProgressView.setVisibility(View.GONE);

                        }catch (Exception e){
                            mProgressView.setVisibility(View.GONE);
                            try {
                                Toast.makeText(getContext(), getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
                                Log.e(TAG, "onResponse: ",e );

                            }catch (Exception ex){

                            }

                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
//                    utils.dismiss();
                mProgressView.setVisibility(View.GONE);
                VolleyErrorHandler.getVolleyError(error,getContext(), TAG);
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");

                return headers;

            }
        };

        // Access the RequestQueue through your singleton class.
        VolleySingleton.getInstance(getContext()).addToRequestQueue(jsArrayRequest);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof CarFilterFragment.OnCarFilterClickListener) {
            mListener = (CarFilterFragment.OnCarFilterClickListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListDependantlick");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnCarFilterClickListener {
        // TODO: Update argument type and name
        void OnCarFilterClick(Car car);
    }


}
