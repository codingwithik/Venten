package com.ik.umeh.francisumeanozie.carfilter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.ik.umeh.francisumeanozie.R;
import com.ik.umeh.francisumeanozie.carowner.CarOwnerFragment;
import com.ik.umeh.francisumeanozie.models.Car;

public class CarFilterActivity extends AppCompatActivity implements
        CarFilterFragment.OnCarFilterClickListener{

    private static final String TAG = "CarFIlterActivity";

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_filter);

//        try
//        {
//            this.getSupportActionBar().hide();
//        }
//        catch (NullPointerException e){}
//
//        Window window = this.getWindow();
//
//        // clear FLAG_TRANSLUCENT_STATUS flag:
//        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//
//        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
//        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//
//        // finally change the color
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            window.getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
//
//            window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorWhite));
//        }


        fragmentManager = getSupportFragmentManager();
        if(savedInstanceState== null){
            if (findViewById(R.id.fragment_holder) != null) {
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_holder, new CarFilterFragment());
                fragmentTransaction.commit();
            }
        }
    }

    public void goBack(View view){
        onBackPressed();
    }

    @Override
    public void OnCarFilterClick(Car cars) {
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        //add a bit of sliding animations

        //add to backstack to close the fragment and not the activity
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.replace(R.id.fragment_holder, CarOwnerFragment.newInstance(cars));
        fragmentTransaction.commit();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        fragmentManager = null;
        fragmentTransaction = null;
    }
}
