package bsnl.bsnl_teevra;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;

public class VolleyErrorHelper {
    private String errormsg;

    public String onErrorResponse(VolleyError volleyError, Context context) {
        if (volleyError instanceof TimeoutError) {
            this.errormsg = "Connection Timed-Out!!\nPlease Try After SomeTime.";
        } else if (volleyError instanceof AuthFailureError) {
            this.errormsg = "Server Couldn't Find The Authenticated Request.";
        } else if (volleyError instanceof ServerError) {
            this.errormsg = "Server Timed-Out!!\nServer Is Not Responding.";
        } else if (volleyError instanceof NoConnectionError) {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            NetworkInfo activeNetworkInfo = connectivityManager != null ? connectivityManager.getActiveNetworkInfo() : null;
            if (activeNetworkInfo == null || !activeNetworkInfo.isConnectedOrConnecting()) {
                this.errormsg = "Can't Connect To Internet!!\nPlease Check Your Internet Connection.";
            } else {
                this.errormsg = "Connection Timed-Out!!\nServer Is Not Responding.";
            }
        } else if (volleyError instanceof NetworkError) {
            this.errormsg = "Network Error!!\nPlease Try Again.";
        } else if (volleyError instanceof ParseError) {
            this.errormsg = "Parse Error\nServer Failed To Process Your Request!!.";
        } else {
            this.errormsg = "An Unknown Error Occurred!!";
        }
        return this.errormsg;
    }
}
