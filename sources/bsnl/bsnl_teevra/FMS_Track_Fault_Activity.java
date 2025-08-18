package bsnl.bsnl_teevra;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.provider.Settings;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.ViewCompat;
import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FMS_Track_Fault_Activity extends AppCompatActivity implements TextWatcher {
    /* access modifiers changed from: private */
    public String Pref_Fms_Username;
    /* access modifiers changed from: private */
    public String Pref_Randkey;
    private String Pref_SSA;
    /* access modifiers changed from: private */
    public String Pref_Username;
    /* access modifiers changed from: private */
    public String androidId;
    SharedPreferences.Editor editor;
    /* access modifiers changed from: private */
    public AlertDialog error_dialog;
    private EditText et_search;
    /* access modifiers changed from: private */
    public JSONArray fault_array;
    /* access modifiers changed from: private */
    public AlertDialog info_dialog;
    /* access modifiers changed from: private */
    public LinearLayout lay_fms_trackfault;
    /* access modifiers changed from: private */
    public AlertDialog logout_dialog;
    /* access modifiers changed from: private */
    public AlertDialog progress_dialog;
    SharedPreferences sharedPreferences;
    /* access modifiers changed from: private */
    public TextView txt_alert;

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.fms_track_fault_activity);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        SharedPreferences sharedPreferences2 = getSharedPreferences("myloginpreference", 0);
        this.sharedPreferences = sharedPreferences2;
        this.Pref_Fms_Username = sharedPreferences2.getString("fms_username_Key", (String) null);
        this.Pref_Username = this.sharedPreferences.getString("username_Key", (String) null);
        this.Pref_Randkey = this.sharedPreferences.getString("rand_Key", (String) null);
        this.androidId = Settings.Secure.getString(getContentResolver(), "android_id");
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View inflate = getLayoutInflater().inflate(R.layout.custom_progress, (ViewGroup) null);
        builder.setCancelable(false);
        builder.setView(inflate);
        AlertDialog create = builder.create();
        this.progress_dialog = create;
        create.show();
        AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
        View inflate2 = getLayoutInflater().inflate(R.layout.custom_error, (ViewGroup) null);
        this.txt_alert = (TextView) inflate2.findViewById(R.id.txt_error);
        builder2.setCancelable(false);
        builder2.setNegativeButton("Retry", (DialogInterface.OnClickListener) null);
        builder2.setView(inflate2);
        this.error_dialog = builder2.create();
        EditText editText = (EditText) findViewById(R.id.et_search);
        this.et_search = editText;
        editText.addTextChangedListener(this);
        this.lay_fms_trackfault = (LinearLayout) findViewById(R.id.lay_fms_trackfault);
        RequestQueue newRequestQueue = Volley.newRequestQueue(this);
        AnonymousClass3 r0 = new StringRequest(1, getString(R.string.serverip) + "fms/fms_track_faults.php", new Response.Listener<String>() {
            public void onResponse(String str) {
                String str2 = "COMP_BKD_DATE";
                String str3 = "STATUS";
                String str4 = "FAULT_STATUS";
                FMS_Track_Fault_Activity.this.progress_dialog.cancel();
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    int i = 0;
                    if (jSONObject.getString(str3).equals("SUCCESS")) {
                        JSONArray unused = FMS_Track_Fault_Activity.this.fault_array = jSONObject.getJSONArray("ROWSET");
                        int i2 = 0;
                        while (i2 < FMS_Track_Fault_Activity.this.fault_array.length()) {
                            JSONObject jSONObject2 = FMS_Track_Fault_Activity.this.fault_array.getJSONObject(i2);
                            LinearLayout linearLayout = new LinearLayout(FMS_Track_Fault_Activity.this);
                            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
                            layoutParams.setMargins(i, 10, i, 10);
                            linearLayout.setLayoutParams(layoutParams);
                            linearLayout.setOrientation(1);
                            linearLayout.setPadding(10, 10, 10, 10);
                            linearLayout.setBackgroundResource(R.drawable.new_style);
                            LinearLayout linearLayout2 = new LinearLayout(FMS_Track_Fault_Activity.this);
                            linearLayout2.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
                            linearLayout2.setOrientation(i);
                            linearLayout2.setGravity(17);
                            linearLayout2.setPadding(10, 10, 10, 10);
                            linearLayout2.setBackgroundResource(R.drawable.new_style10);
                            TextView textView = new TextView(FMS_Track_Fault_Activity.this);
                            textView.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
                            int i3 = i2 + 1;
                            textView.setText(Integer.toString(i3));
                            textView.setTextColor(-1);
                            textView.setGravity(17);
                            textView.setPadding(5, 5, 5, 5);
                            textView.setTypeface((Typeface) null, 1);
                            textView.setTextSize(0, FMS_Track_Fault_Activity.this.getResources().getDimension(R.dimen.smalltext));
                            textView.setBackgroundResource(R.drawable.ic_new_circle_red);
                            LinearLayout linearLayout3 = new LinearLayout(FMS_Track_Fault_Activity.this);
                            linearLayout3.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 9.0f));
                            linearLayout3.setOrientation(1);
                            linearLayout3.setGravity(17);
                            linearLayout3.setPadding(5, 5, 5, 0);
                            TextView textView2 = new TextView(FMS_Track_Fault_Activity.this);
                            textView2.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
                            textView2.setText(jSONObject2.getString("PHONE_NO").toUpperCase());
                            textView2.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                            textView2.setGravity(17);
                            textView2.setTypeface((Typeface) null, 1);
                            textView2.setTextSize(0, FMS_Track_Fault_Activity.this.getResources().getDimension(R.dimen.mediumtext));
                            TextView textView3 = new TextView(FMS_Track_Fault_Activity.this);
                            int i4 = i3;
                            textView3.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
                            textView3.setText("(DOCKET NO : " + jSONObject2.getString("DOCKET_NO") + ")");
                            textView3.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                            textView3.setGravity(17);
                            textView3.setTextSize(0, FMS_Track_Fault_Activity.this.getResources().getDimension(R.dimen.smalltext));
                            linearLayout3.addView(textView2);
                            linearLayout3.addView(textView3);
                            ImageView imageView = new ImageView(FMS_Track_Fault_Activity.this);
                            imageView.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
                            imageView.setPadding(10, 10, 10, 10);
                            imageView.setClickable(true);
                            imageView.setImageResource(R.drawable.ic_new_share);
                            linearLayout2.addView(textView);
                            linearLayout2.addView(linearLayout3);
                            linearLayout2.addView(imageView);
                            LinearLayout linearLayout4 = new LinearLayout(FMS_Track_Fault_Activity.this);
                            linearLayout4.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
                            linearLayout4.setOrientation(0);
                            linearLayout4.setPadding(5, 10, 5, 5);
                            TextView textView4 = new TextView(FMS_Track_Fault_Activity.this);
                            textView4.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
                            textView4.setText("CONTACT DETAILS : ");
                            textView4.setPaintFlags(8);
                            textView4.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                            textView4.setGravity(1);
                            textView4.setTypeface((Typeface) null, 1);
                            textView4.setTextSize(0, FMS_Track_Fault_Activity.this.getResources().getDimension(R.dimen.smalltext));
                            TextView textView5 = new TextView(FMS_Track_Fault_Activity.this);
                            textView5.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 2.0f));
                            textView5.setText(Html.fromHtml("<b>" + jSONObject2.getString("CUSTOMER_NAME").trim() + "</b><br>" + jSONObject2.getString("ADDRESS").trim() + "<br>" + jSONObject2.getString("MOBILE_NO")));
                            textView5.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                            textView5.setGravity(3);
                            textView5.setTextSize(0, FMS_Track_Fault_Activity.this.getResources().getDimension(R.dimen.smalltext));
                            linearLayout4.addView(textView4);
                            linearLayout4.addView(textView5);
                            LinearLayout linearLayout5 = new LinearLayout(FMS_Track_Fault_Activity.this);
                            linearLayout5.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
                            layoutParams.setMargins(0, 5, 0, 5);
                            linearLayout5.setOrientation(0);
                            linearLayout5.setGravity(17);
                            linearLayout5.setPadding(5, 10, 5, 0);
                            TextView textView6 = new TextView(FMS_Track_Fault_Activity.this);
                            textView6.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
                            textView6.setText("SERVICE TYPE");
                            textView6.setPaintFlags(8);
                            textView6.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                            textView6.setGravity(17);
                            textView6.setTypeface((Typeface) null, 1);
                            textView6.setTextSize(0, FMS_Track_Fault_Activity.this.getResources().getDimension(R.dimen.smalltext));
                            TextView textView7 = new TextView(FMS_Track_Fault_Activity.this);
                            LinearLayout linearLayout6 = linearLayout4;
                            textView7.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
                            textView7.setText("COM SUB TYPE");
                            textView7.setPaintFlags(8);
                            textView7.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                            textView7.setGravity(17);
                            textView7.setTypeface((Typeface) null, 1);
                            textView7.setTextSize(0, FMS_Track_Fault_Activity.this.getResources().getDimension(R.dimen.smalltext));
                            TextView textView8 = new TextView(FMS_Track_Fault_Activity.this);
                            LinearLayout linearLayout7 = linearLayout;
                            textView8.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
                            textView8.setText(str2);
                            textView8.setPaintFlags(8);
                            textView8.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                            textView8.setGravity(17);
                            textView8.setTypeface((Typeface) null, 1);
                            textView8.setTextSize(0, FMS_Track_Fault_Activity.this.getResources().getDimension(R.dimen.smalltext));
                            linearLayout5.addView(textView6);
                            linearLayout5.addView(textView7);
                            linearLayout5.addView(textView8);
                            LinearLayout linearLayout8 = new LinearLayout(FMS_Track_Fault_Activity.this);
                            linearLayout8.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
                            linearLayout8.setOrientation(0);
                            linearLayout8.setGravity(17);
                            linearLayout8.setPadding(5, 5, 5, 10);
                            TextView textView9 = new TextView(FMS_Track_Fault_Activity.this);
                            textView9.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
                            textView9.setText(jSONObject2.getString("SERVICE_TYPE").toUpperCase());
                            textView9.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                            textView9.setGravity(17);
                            textView9.setTextSize(0, FMS_Track_Fault_Activity.this.getResources().getDimension(R.dimen.smalltext));
                            TextView textView10 = new TextView(FMS_Track_Fault_Activity.this);
                            textView10.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
                            textView10.setText(jSONObject2.getString("COMP_SUB_TYPE").toUpperCase());
                            textView10.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                            textView10.setGravity(17);
                            textView10.setTextSize(0, FMS_Track_Fault_Activity.this.getResources().getDimension(R.dimen.smalltext));
                            TextView textView11 = new TextView(FMS_Track_Fault_Activity.this);
                            LinearLayout linearLayout9 = linearLayout5;
                            textView11.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
                            textView11.setText(jSONObject2.getString(str2));
                            textView11.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                            textView11.setGravity(17);
                            textView11.setTextSize(0, FMS_Track_Fault_Activity.this.getResources().getDimension(R.dimen.smalltext));
                            linearLayout8.addView(textView9);
                            linearLayout8.addView(textView10);
                            linearLayout8.addView(textView11);
                            LinearLayout linearLayout10 = new LinearLayout(FMS_Track_Fault_Activity.this);
                            linearLayout10.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
                            layoutParams.setMargins(0, 5, 0, 5);
                            linearLayout10.setOrientation(0);
                            linearLayout10.setGravity(17);
                            linearLayout10.setPadding(5, 10, 5, 0);
                            TextView textView12 = new TextView(FMS_Track_Fault_Activity.this);
                            textView12.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
                            textView12.setText("FMS ASSIGN DT");
                            textView12.setPaintFlags(8);
                            textView12.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                            textView12.setGravity(17);
                            textView12.setTypeface((Typeface) null, 1);
                            textView12.setTextSize(0, FMS_Track_Fault_Activity.this.getResources().getDimension(R.dimen.smalltext));
                            TextView textView13 = new TextView(FMS_Track_Fault_Activity.this);
                            textView13.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
                            textView13.setText("FMS CLOSED DT");
                            textView13.setPaintFlags(8);
                            textView13.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                            textView13.setGravity(17);
                            textView13.setTypeface((Typeface) null, 1);
                            textView13.setTextSize(0, FMS_Track_Fault_Activity.this.getResources().getDimension(R.dimen.smalltext));
                            TextView textView14 = new TextView(FMS_Track_Fault_Activity.this);
                            textView14.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
                            textView14.setText("FAULT CLEARED BY");
                            textView14.setPaintFlags(8);
                            textView14.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                            textView14.setGravity(17);
                            textView14.setTypeface((Typeface) null, 1);
                            textView14.setTextSize(0, FMS_Track_Fault_Activity.this.getResources().getDimension(R.dimen.smalltext));
                            linearLayout10.addView(textView12);
                            linearLayout10.addView(textView13);
                            linearLayout10.addView(textView14);
                            LinearLayout linearLayout11 = new LinearLayout(FMS_Track_Fault_Activity.this);
                            linearLayout11.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
                            linearLayout11.setOrientation(0);
                            linearLayout11.setGravity(17);
                            linearLayout11.setPadding(5, 5, 5, 10);
                            TextView textView15 = new TextView(FMS_Track_Fault_Activity.this);
                            textView15.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
                            textView15.setText(jSONObject2.getString("ASSIGN_TO_FRAN_TASK_DATE"));
                            textView15.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                            textView15.setGravity(17);
                            textView15.setTextSize(0, FMS_Track_Fault_Activity.this.getResources().getDimension(R.dimen.smalltext));
                            TextView textView16 = new TextView(FMS_Track_Fault_Activity.this);
                            textView16.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
                            textView16.setText(jSONObject2.getString("SUBMITTED_DATE"));
                            textView16.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                            textView16.setGravity(17);
                            textView16.setTextSize(0, FMS_Track_Fault_Activity.this.getResources().getDimension(R.dimen.smalltext));
                            TextView textView17 = new TextView(FMS_Track_Fault_Activity.this);
                            String str5 = str2;
                            textView17.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
                            textView17.setText(jSONObject2.getString("SHORT_CODE"));
                            textView17.setBackgroundResource(R.drawable.new_style_circle_green);
                            textView17.setTextColor(-1);
                            textView17.setTypeface((Typeface) null, 1);
                            textView17.setGravity(17);
                            textView17.setTextSize(0, FMS_Track_Fault_Activity.this.getResources().getDimension(R.dimen.smalltext));
                            linearLayout11.addView(textView15);
                            linearLayout11.addView(textView16);
                            linearLayout11.addView(textView17);
                            LinearLayout linearLayout12 = new LinearLayout(FMS_Track_Fault_Activity.this);
                            linearLayout12.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
                            linearLayout12.setOrientation(0);
                            linearLayout12.setGravity(17);
                            linearLayout12.setPadding(5, 5, 5, 10);
                            TextView textView18 = new TextView(FMS_Track_Fault_Activity.this);
                            textView18.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
                            textView18.setText(str3);
                            textView18.setPaintFlags(8);
                            textView18.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                            textView18.setGravity(17);
                            textView18.setTypeface((Typeface) null, 1);
                            textView18.setTextSize(0, FMS_Track_Fault_Activity.this.getResources().getDimension(R.dimen.smalltext));
                            TextView textView19 = new TextView(FMS_Track_Fault_Activity.this);
                            textView19.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
                            textView19.setText("CLARITY RESPONSE");
                            textView19.setPaintFlags(8);
                            textView19.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                            textView19.setGravity(17);
                            textView19.setTypeface((Typeface) null, 1);
                            textView19.setTextSize(0, FMS_Track_Fault_Activity.this.getResources().getDimension(R.dimen.smalltext));
                            TextView textView20 = new TextView(FMS_Track_Fault_Activity.this);
                            String str6 = str3;
                            textView20.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
                            textView20.setText("FRAN. COMMENTS");
                            textView20.setPaintFlags(8);
                            textView20.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                            textView20.setGravity(17);
                            textView20.setTypeface((Typeface) null, 1);
                            textView20.setTextSize(0, FMS_Track_Fault_Activity.this.getResources().getDimension(R.dimen.smalltext));
                            linearLayout12.addView(textView18);
                            linearLayout12.addView(textView19);
                            linearLayout12.addView(textView20);
                            LinearLayout linearLayout13 = new LinearLayout(FMS_Track_Fault_Activity.this);
                            linearLayout13.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
                            linearLayout13.setOrientation(0);
                            linearLayout13.setGravity(17);
                            linearLayout13.setPadding(5, 5, 5, 10);
                            TextView textView21 = new TextView(FMS_Track_Fault_Activity.this);
                            textView21.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
                            textView21.setText(jSONObject2.getString(str4));
                            if (jSONObject2.getString(str4).equals("ASSIGNED")) {
                                textView21.setBackgroundResource(R.drawable.new_style_circle_amber);
                            } else if (jSONObject2.getString(str4).equals("SUBMITTED")) {
                                textView21.setBackgroundResource(R.drawable.new_style_circle_green);
                            } else {
                                textView21.setBackgroundResource(R.drawable.new_style_circle_red);
                            }
                            textView21.setTextColor(-1);
                            textView21.setGravity(17);
                            textView21.setTypeface((Typeface) null, 1);
                            textView21.setTextSize(0, FMS_Track_Fault_Activity.this.getResources().getDimension(R.dimen.smalltext));
                            TextView textView22 = new TextView(FMS_Track_Fault_Activity.this);
                            textView22.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
                            textView22.setText(jSONObject2.getString("CLARITY_RESPONSE_MSG"));
                            textView22.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                            textView22.setGravity(17);
                            textView22.setTextSize(0, FMS_Track_Fault_Activity.this.getResources().getDimension(R.dimen.smalltext));
                            TextView textView23 = new TextView(FMS_Track_Fault_Activity.this);
                            textView23.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
                            textView23.setText(jSONObject2.getString("TASK_COMMENTS"));
                            textView23.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                            textView23.setGravity(17);
                            textView23.setTextSize(0, FMS_Track_Fault_Activity.this.getResources().getDimension(R.dimen.smalltext));
                            linearLayout13.addView(textView21);
                            linearLayout13.addView(textView22);
                            linearLayout13.addView(textView23);
                            LinearLayout linearLayout14 = linearLayout7;
                            linearLayout14.addView(linearLayout2);
                            linearLayout14.addView(linearLayout6);
                            linearLayout14.addView(linearLayout9);
                            linearLayout14.addView(linearLayout8);
                            linearLayout14.addView(linearLayout10);
                            linearLayout14.addView(linearLayout11);
                            linearLayout14.addView(linearLayout12);
                            linearLayout14.addView(linearLayout13);
                            FMS_Track_Fault_Activity.this.lay_fms_trackfault.addView(linearLayout14);
                            str4 = str4;
                            i2 = i4;
                            str2 = str5;
                            str3 = str6;
                            i = 0;
                        }
                        return;
                    }
                    AlertDialog.Builder builder = new AlertDialog.Builder(FMS_Track_Fault_Activity.this);
                    View inflate = FMS_Track_Fault_Activity.this.getLayoutInflater().inflate(R.layout.custom_info, (ViewGroup) null);
                    ((TextView) inflate.findViewById(R.id.txt_cancel)).setVisibility(4);
                    TextView textView24 = (TextView) inflate.findViewById(R.id.txt_update);
                    textView24.setText("OK");
                    builder.setCancelable(false);
                    builder.setView(inflate);
                    AlertDialog unused2 = FMS_Track_Fault_Activity.this.info_dialog = builder.create();
                    FMS_Track_Fault_Activity.this.info_dialog.show();
                    ((TextView) inflate.findViewById(R.id.txt_alert)).setText("INFO");
                    ((TextView) inflate.findViewById(R.id.txt_error)).setText("No Pending Fault Orders");
                    textView24.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                            FMS_Track_Fault_Activity.this.info_dialog.cancel();
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError volleyError) {
                FMS_Track_Fault_Activity.this.progress_dialog.cancel();
                FMS_Track_Fault_Activity.this.txt_alert.setText(new VolleyErrorHelper().onErrorResponse(volleyError, FMS_Track_Fault_Activity.this.getApplicationContext()));
                FMS_Track_Fault_Activity.this.error_dialog.show();
            }
        }) {
            /* access modifiers changed from: protected */
            public Map<String, String> getParams() throws AuthFailureError {
                HashMap hashMap = new HashMap();
                hashMap.put("fms_userid", FMS_Track_Fault_Activity.this.Pref_Fms_Username);
                hashMap.put("username", FMS_Track_Fault_Activity.this.Pref_Username);
                hashMap.put("random_key", FMS_Track_Fault_Activity.this.Pref_Randkey);
                hashMap.put("device_id", FMS_Track_Fault_Activity.this.androidId);
                return hashMap;
            }
        };
        r0.setRetryPolicy(new DefaultRetryPolicy(BZip2Constants.BASEBLOCKSIZE, 1, 1.0f));
        newRequestQueue.add(r0);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.fmsmenu, menu);
        menu.findItem(R.id.fmsuser).setTitle(this.Pref_Fms_Username);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        if (itemId == R.id.teevrahome) {
            Intent intent = new Intent(this, DashBoard_New.class);
            intent.setFlags(67108864);
            startActivity(intent);
            finish();
        } else if (itemId == R.id.fmslogout) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            View inflate = getLayoutInflater().inflate(R.layout.custom_logout, (ViewGroup) null);
            builder.setCancelable(false);
            builder.setView(inflate);
            AlertDialog create = builder.create();
            this.logout_dialog = create;
            create.show();
            ((TextView) inflate.findViewById(R.id.txt_cancel)).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    FMS_Track_Fault_Activity.this.logout_dialog.cancel();
                }
            });
            ((TextView) inflate.findViewById(R.id.txt_submit)).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    FMS_Track_Fault_Activity fMS_Track_Fault_Activity = FMS_Track_Fault_Activity.this;
                    fMS_Track_Fault_Activity.editor = fMS_Track_Fault_Activity.sharedPreferences.edit();
                    FMS_Track_Fault_Activity.this.editor.remove("isfmsloggedin_key");
                    FMS_Track_Fault_Activity.this.editor.commit();
                    Intent intent = new Intent(FMS_Track_Fault_Activity.this, FMS_Login_Activity.class);
                    intent.setFlags(67108864);
                    FMS_Track_Fault_Activity.this.startActivity(intent);
                    FMS_Track_Fault_Activity.this.finish();
                    FMS_Track_Fault_Activity.this.logout_dialog.cancel();
                }
            });
        }
        return super.onOptionsItemSelected(menuItem);
    }

    public void afterTextChanged(Editable editable) {
        String str;
        String str2;
        String str3;
        int i;
        FMS_Track_Fault_Activity fMS_Track_Fault_Activity;
        int i2;
        FMS_Track_Fault_Activity fMS_Track_Fault_Activity2 = this;
        String str4 = "COMP_BKD_DATE";
        String str5 = "PHONE_NO";
        String obj = fMS_Track_Fault_Activity2.et_search.getText().toString();
        fMS_Track_Fault_Activity2.lay_fms_trackfault.removeAllViews();
        int i3 = 0;
        int i4 = 1;
        while (i3 < fMS_Track_Fault_Activity2.fault_array.length()) {
            try {
                JSONObject jSONObject = fMS_Track_Fault_Activity2.fault_array.getJSONObject(i3);
                if (!jSONObject.getString(str5).toUpperCase().contains(obj.toUpperCase())) {
                    i = i3;
                    if (!jSONObject.getString("DOCKET_NO").toUpperCase().contains(obj.toUpperCase()) && !jSONObject.getString("CUSTOMER_NAME").toUpperCase().contains(obj.toUpperCase()) && !jSONObject.getString("MOBILE_NO").toUpperCase().contains(obj.toUpperCase()) && !jSONObject.getString("SERVICE_TYPE").toUpperCase().contains(obj.toUpperCase()) && !jSONObject.getString("COMP_SUB_TYPE").toUpperCase().contains(obj.toUpperCase()) && !jSONObject.getString("SHORT_CODE").toUpperCase().contains(obj.toUpperCase())) {
                        if (!jSONObject.getString("FAULT_STATUS").toUpperCase().contains(obj.toUpperCase())) {
                            str2 = str4;
                            str = str5;
                            str3 = obj;
                            fMS_Track_Fault_Activity = fMS_Track_Fault_Activity2;
                            i2 = i4;
                            i3 = i + 1;
                            i4 = i2;
                            fMS_Track_Fault_Activity2 = fMS_Track_Fault_Activity;
                            obj = str3;
                            str4 = str2;
                            str5 = str;
                        }
                    }
                } else {
                    i = i3;
                }
                LinearLayout linearLayout = new LinearLayout(fMS_Track_Fault_Activity2);
                str3 = obj;
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
                layoutParams.setMargins(0, 10, 0, 10);
                linearLayout.setLayoutParams(layoutParams);
                linearLayout.setOrientation(1);
                linearLayout.setPadding(10, 10, 10, 10);
                linearLayout.setBackgroundResource(R.drawable.new_style);
                LinearLayout linearLayout2 = new LinearLayout(fMS_Track_Fault_Activity2);
                LinearLayout linearLayout3 = linearLayout;
                linearLayout2.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
                linearLayout2.setOrientation(0);
                linearLayout2.setGravity(17);
                linearLayout2.setPadding(10, 10, 10, 10);
                linearLayout2.setBackgroundResource(R.drawable.new_style10);
                TextView textView = new TextView(fMS_Track_Fault_Activity2);
                textView.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
                textView.setText(Integer.toString(i4));
                textView.setTextColor(-1);
                textView.setGravity(17);
                textView.setPadding(5, 5, 5, 5);
                textView.setTypeface((Typeface) null, 1);
                textView.setTextSize(0, getResources().getDimension(R.dimen.smalltext));
                textView.setBackgroundResource(R.drawable.ic_new_circle_red);
                LinearLayout linearLayout4 = new LinearLayout(fMS_Track_Fault_Activity2);
                int i5 = i4;
                linearLayout4.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 9.0f));
                linearLayout4.setOrientation(1);
                linearLayout4.setGravity(17);
                linearLayout4.setPadding(5, 5, 5, 0);
                TextView textView2 = new TextView(fMS_Track_Fault_Activity2);
                textView2.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
                textView2.setText(jSONObject.getString(str5).toUpperCase());
                textView2.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                textView2.setGravity(17);
                textView2.setTypeface((Typeface) null, 1);
                textView2.setTextSize(0, getResources().getDimension(R.dimen.mediumtext));
                TextView textView3 = new TextView(fMS_Track_Fault_Activity2);
                str = str5;
                textView3.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
                textView3.setText("(DOCKET NO : " + jSONObject.getString("DOCKET_NO") + ")");
                textView3.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                textView3.setGravity(17);
                textView3.setTextSize(0, getResources().getDimension(R.dimen.smalltext));
                linearLayout4.addView(textView2);
                linearLayout4.addView(textView3);
                ImageView imageView = new ImageView(fMS_Track_Fault_Activity2);
                imageView.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
                imageView.setPadding(10, 10, 10, 10);
                imageView.setClickable(true);
                imageView.setImageResource(R.drawable.ic_new_share);
                linearLayout2.addView(textView);
                linearLayout2.addView(linearLayout4);
                linearLayout2.addView(imageView);
                LinearLayout linearLayout5 = new LinearLayout(fMS_Track_Fault_Activity2);
                linearLayout5.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
                linearLayout5.setOrientation(0);
                linearLayout5.setPadding(5, 10, 5, 5);
                TextView textView4 = new TextView(fMS_Track_Fault_Activity2);
                textView4.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
                textView4.setText("CONTACT DETAILS : ");
                textView4.setPaintFlags(8);
                textView4.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                textView4.setGravity(1);
                textView4.setTypeface((Typeface) null, 1);
                textView4.setTextSize(0, getResources().getDimension(R.dimen.smalltext));
                TextView textView5 = new TextView(fMS_Track_Fault_Activity2);
                textView5.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 2.0f));
                textView5.setText(Html.fromHtml("<b>" + jSONObject.getString("CUSTOMER_NAME").trim() + "</b><br>" + jSONObject.getString("ADDRESS").trim() + "<br>" + jSONObject.getString("MOBILE_NO")));
                textView5.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                textView5.setGravity(3);
                textView5.setTextSize(0, getResources().getDimension(R.dimen.smalltext));
                linearLayout5.addView(textView4);
                linearLayout5.addView(textView5);
                LinearLayout linearLayout6 = new LinearLayout(fMS_Track_Fault_Activity2);
                linearLayout6.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
                layoutParams.setMargins(0, 5, 0, 5);
                linearLayout6.setOrientation(0);
                linearLayout6.setGravity(17);
                linearLayout6.setPadding(5, 10, 5, 0);
                TextView textView6 = new TextView(fMS_Track_Fault_Activity2);
                textView6.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
                textView6.setText("SERVICE TYPE");
                textView6.setPaintFlags(8);
                textView6.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                textView6.setGravity(17);
                textView6.setTypeface((Typeface) null, 1);
                textView6.setTextSize(0, getResources().getDimension(R.dimen.smalltext));
                TextView textView7 = new TextView(fMS_Track_Fault_Activity2);
                textView7.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
                textView7.setText("COM SUB TYPE");
                textView7.setPaintFlags(8);
                textView7.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                textView7.setGravity(17);
                textView7.setTypeface((Typeface) null, 1);
                textView7.setTextSize(0, getResources().getDimension(R.dimen.smalltext));
                TextView textView8 = new TextView(fMS_Track_Fault_Activity2);
                textView8.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
                textView8.setText(str4);
                textView8.setPaintFlags(8);
                textView8.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                textView8.setGravity(17);
                textView8.setTypeface((Typeface) null, 1);
                textView8.setTextSize(0, getResources().getDimension(R.dimen.smalltext));
                linearLayout6.addView(textView6);
                linearLayout6.addView(textView7);
                linearLayout6.addView(textView8);
                LinearLayout linearLayout7 = new LinearLayout(fMS_Track_Fault_Activity2);
                linearLayout7.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
                linearLayout7.setOrientation(0);
                linearLayout7.setGravity(17);
                linearLayout7.setPadding(5, 5, 5, 10);
                TextView textView9 = new TextView(fMS_Track_Fault_Activity2);
                textView9.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
                textView9.setText(jSONObject.getString("SERVICE_TYPE").toUpperCase());
                textView9.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                textView9.setGravity(17);
                textView9.setTextSize(0, getResources().getDimension(R.dimen.smalltext));
                TextView textView10 = new TextView(fMS_Track_Fault_Activity2);
                textView10.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
                textView10.setText(jSONObject.getString("COMP_SUB_TYPE").toUpperCase());
                textView10.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                textView10.setGravity(17);
                textView10.setTextSize(0, getResources().getDimension(R.dimen.smalltext));
                TextView textView11 = new TextView(fMS_Track_Fault_Activity2);
                textView11.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
                textView11.setText(jSONObject.getString(str4));
                textView11.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                textView11.setGravity(17);
                textView11.setTextSize(0, getResources().getDimension(R.dimen.smalltext));
                linearLayout7.addView(textView9);
                linearLayout7.addView(textView10);
                linearLayout7.addView(textView11);
                LinearLayout linearLayout8 = new LinearLayout(fMS_Track_Fault_Activity2);
                linearLayout8.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
                layoutParams.setMargins(0, 5, 0, 5);
                linearLayout8.setOrientation(0);
                linearLayout8.setGravity(17);
                linearLayout8.setPadding(5, 10, 5, 0);
                TextView textView12 = new TextView(fMS_Track_Fault_Activity2);
                textView12.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
                textView12.setText("FMS ASSIGN DT");
                textView12.setPaintFlags(8);
                textView12.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                textView12.setGravity(17);
                textView12.setTypeface((Typeface) null, 1);
                textView12.setTextSize(0, getResources().getDimension(R.dimen.smalltext));
                TextView textView13 = new TextView(fMS_Track_Fault_Activity2);
                textView13.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
                textView13.setText("FMS CLOSED DT");
                textView13.setPaintFlags(8);
                textView13.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                textView13.setGravity(17);
                textView13.setTypeface((Typeface) null, 1);
                textView13.setTextSize(0, getResources().getDimension(R.dimen.smalltext));
                TextView textView14 = new TextView(fMS_Track_Fault_Activity2);
                textView14.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
                textView14.setText("FAULT CLEARED BY");
                textView14.setPaintFlags(8);
                textView14.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                textView14.setGravity(17);
                textView14.setTypeface((Typeface) null, 1);
                textView14.setTextSize(0, getResources().getDimension(R.dimen.smalltext));
                linearLayout8.addView(textView12);
                linearLayout8.addView(textView13);
                linearLayout8.addView(textView14);
                LinearLayout linearLayout9 = new LinearLayout(fMS_Track_Fault_Activity2);
                linearLayout9.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
                linearLayout9.setOrientation(0);
                linearLayout9.setGravity(17);
                linearLayout9.setPadding(5, 5, 5, 10);
                TextView textView15 = new TextView(fMS_Track_Fault_Activity2);
                textView15.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
                textView15.setText(jSONObject.getString("ASSIGN_TO_FRAN_TASK_DATE"));
                textView15.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                textView15.setGravity(17);
                textView15.setTextSize(0, getResources().getDimension(R.dimen.smalltext));
                TextView textView16 = new TextView(fMS_Track_Fault_Activity2);
                textView16.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
                textView16.setText(jSONObject.getString("SUBMITTED_DATE"));
                textView16.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                textView16.setGravity(17);
                textView16.setTextSize(0, getResources().getDimension(R.dimen.smalltext));
                TextView textView17 = new TextView(fMS_Track_Fault_Activity2);
                str2 = str4;
                textView17.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
                textView17.setText(jSONObject.getString("SHORT_CODE"));
                textView17.setBackgroundResource(R.drawable.new_style_circle_green);
                textView17.setTextColor(-1);
                textView17.setTypeface((Typeface) null, 1);
                textView17.setGravity(17);
                textView17.setTextSize(0, getResources().getDimension(R.dimen.smalltext));
                linearLayout9.addView(textView15);
                linearLayout9.addView(textView16);
                linearLayout9.addView(textView17);
                LinearLayout linearLayout10 = new LinearLayout(fMS_Track_Fault_Activity2);
                linearLayout10.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
                linearLayout10.setOrientation(0);
                linearLayout10.setGravity(17);
                linearLayout10.setPadding(5, 5, 5, 10);
                TextView textView18 = new TextView(fMS_Track_Fault_Activity2);
                textView18.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
                textView18.setText("STATUS");
                textView18.setPaintFlags(8);
                textView18.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                textView18.setGravity(17);
                textView18.setTypeface((Typeface) null, 1);
                textView18.setTextSize(0, getResources().getDimension(R.dimen.smalltext));
                TextView textView19 = new TextView(fMS_Track_Fault_Activity2);
                textView19.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
                textView19.setText("CLARITY RESPONSE");
                textView19.setPaintFlags(8);
                textView19.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                textView19.setGravity(17);
                textView19.setTypeface((Typeface) null, 1);
                textView19.setTextSize(0, getResources().getDimension(R.dimen.smalltext));
                TextView textView20 = new TextView(fMS_Track_Fault_Activity2);
                LinearLayout linearLayout11 = linearLayout9;
                textView20.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
                textView20.setText("FRAN. COMMENTS");
                textView20.setPaintFlags(8);
                textView20.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                textView20.setGravity(17);
                textView20.setTypeface((Typeface) null, 1);
                textView20.setTextSize(0, getResources().getDimension(R.dimen.smalltext));
                linearLayout10.addView(textView18);
                linearLayout10.addView(textView19);
                linearLayout10.addView(textView20);
                LinearLayout linearLayout12 = new LinearLayout(fMS_Track_Fault_Activity2);
                linearLayout12.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
                linearLayout12.setOrientation(0);
                linearLayout12.setGravity(17);
                linearLayout12.setPadding(5, 5, 5, 10);
                TextView textView21 = new TextView(fMS_Track_Fault_Activity2);
                textView21.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
                String str6 = "FAULT_STATUS";
                textView21.setText(jSONObject.getString(str6));
                if (jSONObject.getString(str6).equals("ASSIGNED")) {
                    textView21.setBackgroundResource(R.drawable.new_style_circle_amber);
                } else if (jSONObject.getString(str6).equals("SUBMITTED")) {
                    textView21.setBackgroundResource(R.drawable.new_style_circle_green);
                } else {
                    textView21.setBackgroundResource(R.drawable.new_style_circle_red);
                }
                textView21.setTextColor(-1);
                textView21.setGravity(17);
                textView21.setTypeface((Typeface) null, 1);
                textView21.setTextSize(0, getResources().getDimension(R.dimen.smalltext));
                TextView textView22 = new TextView(fMS_Track_Fault_Activity2);
                textView22.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
                textView22.setText(jSONObject.getString("CLARITY_RESPONSE_MSG"));
                textView22.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                textView22.setGravity(17);
                textView22.setTextSize(0, getResources().getDimension(R.dimen.smalltext));
                TextView textView23 = new TextView(fMS_Track_Fault_Activity2);
                textView23.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
                textView23.setText(jSONObject.getString("TASK_COMMENTS"));
                textView23.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                textView23.setGravity(17);
                textView23.setTextSize(0, getResources().getDimension(R.dimen.smalltext));
                linearLayout12.addView(textView21);
                linearLayout12.addView(textView22);
                linearLayout12.addView(textView23);
                LinearLayout linearLayout13 = linearLayout3;
                linearLayout13.addView(linearLayout2);
                linearLayout13.addView(linearLayout5);
                linearLayout13.addView(linearLayout6);
                linearLayout13.addView(linearLayout7);
                linearLayout13.addView(linearLayout8);
                linearLayout13.addView(linearLayout11);
                linearLayout13.addView(linearLayout10);
                linearLayout13.addView(linearLayout12);
                fMS_Track_Fault_Activity = this;
                fMS_Track_Fault_Activity.lay_fms_trackfault.addView(linearLayout13);
                i2 = i5 + 1;
                i3 = i + 1;
                i4 = i2;
                fMS_Track_Fault_Activity2 = fMS_Track_Fault_Activity;
                obj = str3;
                str4 = str2;
                str5 = str;
            } catch (JSONException e) {
                e.printStackTrace();
                return;
            }
        }
    }
}
