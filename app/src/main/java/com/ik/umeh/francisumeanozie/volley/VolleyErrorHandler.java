package com.ik.umeh.francisumeanozie.volley;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ik.umeh.francisumeanozie.R;

public class VolleyErrorHandler {
    public static void getVolleyError(com.android.volley.VolleyError error, Context context, String TAG) {
        try {
            if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                Log.e(TAG, error.getMessage());
                Toast.makeText(context, context.getResources().getString(R.string.connection_error),Toast.LENGTH_LONG).show();
                //This indicates that the request has either time out or there is no connection
            } else if (error instanceof AuthFailureError) {
                Log.e(TAG, " Auth error"+error.getMessage());
                //Error indicating that there was an Authentication Failure while performing the request
                String jsonError = new String(error.networkResponse.data);
                Log.e(TAG, "show_error "+jsonError);
                JsonObject errorObj= new JsonParser().parse(jsonError).getAsJsonObject();
                //if(errorObj.get(""))
                Toast.makeText(context, errorObj.get("error_description").toString(),Toast.LENGTH_LONG).show();
            } else if (error instanceof ServerError) {
                Log.e(TAG, " server error");
                //Indicates that the server responded with a error response
                String jsonError = new String(error.networkResponse.data);
                Log.e(TAG, "show_error "+jsonError);
                JsonObject errorObj = null;
                try{
                    errorObj= new JsonParser().parse(jsonError).getAsJsonObject();
                    Toast.makeText(context, errorObj.get("error_description").toString(),Toast.LENGTH_LONG).show();

                }catch (Exception e){
                    Toast.makeText(context, R.string.something_went_wrong,Toast.LENGTH_LONG).show();

                }
            } else if (error instanceof NetworkError) {
                Log.e(TAG, "network error");
                Toast.makeText(context, R.string.network_connection_error,Toast.LENGTH_LONG).show();

                //Indicates that there was network error while performing the request
            } else if (error instanceof ParseError) {
                Log.e(TAG, "parse error");

                // Indicates that the server response could not be parsed
            }
        }
        catch (Exception e){
            Log.e(TAG, e.getMessage());
        }

    }

}


