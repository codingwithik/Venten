package com.ik.umeh.francisumeanozie.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

import lombok.Data;

@Data
public class Car implements Parcelable{

    private String first_name;
    private String last_name;
    private String email;
    private String phone;
    private String country;
    private List<String> countries;
    private List<String> colors;
    private String color;
    private String car_model;
    private String car_color;
    private String gender;
    private String job_title;
    private Long start_year;
    private Long end_year;

    public Car(){}

    protected Car(Parcel in) {
        first_name = in.readString();
        phone = in.readString();
        last_name = in.readString();
        country = in.readString();
        email = in.readString();
        color = in.readString();
        car_color = in.readString();
        car_model = in.readString();
        gender = in.readString();
        job_title = in.readString();
        start_year = in.readLong();
        end_year = in.readLong();

    }

    public static final Parcelable.Creator<Car> CREATOR = new Parcelable.Creator<Car>() {
        @Override
        public Car createFromParcel(Parcel in) {
            return new Car(in);
        }

        @Override
        public Car[] newArray(int size) {
            return new Car[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(first_name);
        parcel.writeString(phone);
        parcel.writeString(last_name);
        parcel.writeString(gender);
        parcel.writeString(color);
        parcel.writeString(car_color);
        parcel.writeString(car_model);
        parcel.writeString(country);
        parcel.writeString(email);
        parcel.writeString(job_title);
        parcel.writeLong(start_year);
        parcel.writeLong(end_year);

    }
}
