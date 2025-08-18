package bsnl.bsnl_teevra;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.view.ViewCompat;
import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Port_Verify_Activity extends AppCompatActivity implements View.OnClickListener {
    /* access modifiers changed from: private */
    public String Pref_Circle;
    /* access modifiers changed from: private */
    public String Pref_Randkey;
    private String Pref_Role;
    /* access modifiers changed from: private */
    public String Pref_SSA;
    /* access modifiers changed from: private */
    public String Pref_Username;
    /* access modifiers changed from: private */
    public String androidId;
    /* access modifiers changed from: private */
    public ArrayList<String> bng_code;
    /* access modifiers changed from: private */
    public TextView btn_portverify;
    /* access modifiers changed from: private */
    public AlertDialog error_dialog;
    /* access modifiers changed from: private */
    public ImageView imageView;
    /* access modifiers changed from: private */
    public LinearLayout lay_portverify;
    /* access modifiers changed from: private */
    public AlertDialog progress_dialog;
    private RadioGroup radio;
    private RadioButton radio_userid;
    private SharedPreferences sharedPreferences;
    /* access modifiers changed from: private */
    public Spinner sp_bngcode;
    /* access modifiers changed from: private */
    public TableLayout tab_portverify;
    /* access modifiers changed from: private */
    public TextView txt_alert;
    private TextView txt_portverify;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.port_verify_activity);
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
        SharedPreferences sharedPreferences2 = getSharedPreferences("myloginpreference", 0);
        this.sharedPreferences = sharedPreferences2;
        this.Pref_Circle = sharedPreferences2.getString("circle_Key", (String) null);
        this.Pref_SSA = this.sharedPreferences.getString("ssa_Key", (String) null);
        this.Pref_Username = this.sharedPreferences.getString("username_Key", (String) null);
        this.Pref_Randkey = this.sharedPreferences.getString("rand_Key", (String) null);
        this.androidId = Settings.Secure.getString(getContentResolver(), "android_id");
        this.imageView = (ImageView) findViewById(R.id.img_header);
        this.sp_bngcode = (Spinner) findViewById(R.id.sp_bngcode);
        this.radio = (RadioGroup) findViewById(R.id.radio);
        this.btn_portverify = (TextView) findViewById(R.id.btn_portverify);
        this.lay_portverify = (LinearLayout) findViewById(R.id.lay_portverify);
        this.tab_portverify = (TableLayout) findViewById(R.id.tab_portverify);
        this.txt_portverify = (TextView) findViewById(R.id.txt_portverify);
        this.btn_portverify.setEnabled(false);
        this.imageView.setEnabled(false);
        this.bng_code = new ArrayList<>();
        RequestQueue newRequestQueue = Volley.newRequestQueue(this);
        AnonymousClass3 r0 = new StringRequest(1, getString(R.string.serverip) + "bng_populate.php", new Response.Listener<String>() {
            public void onResponse(String str) {
                Port_Verify_Activity.this.progress_dialog.cancel();
                Port_Verify_Activity.this.btn_portverify.setEnabled(true);
                Port_Verify_Activity.this.imageView.setEnabled(true);
                Port_Verify_Activity.this.bng_code.add("-- BNGCODE --");
                try {
                    JSONArray jSONArray = new JSONArray(str);
                    for (int i = 0; i < jSONArray.length(); i++) {
                        Port_Verify_Activity.this.bng_code.add(jSONArray.getJSONObject(i).getString("BNG_NAME"));
                    }
                    Spinner access$400 = Port_Verify_Activity.this.sp_bngcode;
                    Port_Verify_Activity port_Verify_Activity = Port_Verify_Activity.this;
                    access$400.setAdapter(new ArrayAdapter(port_Verify_Activity, R.layout.spinner_item, port_Verify_Activity.bng_code));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError volleyError) {
                Port_Verify_Activity.this.progress_dialog.cancel();
                Port_Verify_Activity.this.txt_alert.setText(new VolleyErrorHelper().onErrorResponse(volleyError, Port_Verify_Activity.this.getApplicationContext()));
                Port_Verify_Activity.this.error_dialog.show();
            }
        }) {
            /* access modifiers changed from: protected */
            public Map<String, String> getParams() throws AuthFailureError {
                HashMap hashMap = new HashMap();
                hashMap.put("circle", Port_Verify_Activity.this.Pref_Circle);
                hashMap.put("ssa", Port_Verify_Activity.this.Pref_SSA);
                hashMap.put("username", Port_Verify_Activity.this.Pref_Username);
                hashMap.put("random_key", Port_Verify_Activity.this.Pref_Randkey);
                hashMap.put("device_id", Port_Verify_Activity.this.androidId);
                return hashMap;
            }
        };
        r0.setRetryPolicy(new DefaultRetryPolicy(BZip2Constants.BASEBLOCKSIZE, 1, 1.0f));
        newRequestQueue.add(r0);
        this.imageView.setOnClickListener(this);
        this.btn_portverify.setOnClickListener(this);
    }

    public void onClick(View view) {
        if (view.getId() == R.id.img_header) {
            startActivity(new Intent(this, DashBoard_New.class));
            finish();
        } else if (view.getId() == R.id.btn_portverify) {
            this.lay_portverify.setVisibility(4);
            this.radio_userid = (RadioButton) findViewById(this.radio.getCheckedRadioButtonId());
            final String str = this.radio_userid.getText().toString().toLowerCase() + "@bsnl.in";
            final String obj = this.sp_bngcode.getSelectedItem().toString();
            if (obj.equals("-- BNGCODE --")) {
                new AlertHelperclass().ntoast("PLEASE SELECT BNGCODE CODE !!", getApplicationContext());
                return;
            }
            this.tab_portverify.removeAllViews();
            this.progress_dialog.show();
            this.txt_portverify.setText("BNG : " + obj.toUpperCase() + " | USERID : " + this.radio_userid.getText().toString());
            this.txt_portverify.setPaintFlags(8);
            RequestQueue newRequestQueue = Volley.newRequestQueue(this);
            AnonymousClass6 r0 = new StringRequest(1, getString(R.string.serverip) + "portverify.php", new Response.Listener<String>() {
                public void onResponse(String str) {
                    Port_Verify_Activity.this.progress_dialog.cancel();
                    try {
                        JSONObject jSONObject = new JSONObject(str);
                        if (jSONObject.getBoolean("error")) {
                            Port_Verify_Activity.this.txt_alert.setText(jSONObject.getString("error_log"));
                            Port_Verify_Activity.this.error_dialog.show();
                        } else if (jSONObject.getBoolean("success")) {
                            TableRow tableRow = new TableRow(Port_Verify_Activity.this);
                            tableRow.setLayoutParams(new TableRow.LayoutParams(-1, -1));
                            TextView textView = new TextView(Port_Verify_Activity.this);
                            textView.setText("SR");
                            textView.setLayoutParams(new TableRow.LayoutParams(0, -1, 0.8f));
                            textView.setTextColor(-1);
                            textView.setGravity(17);
                            int i = 15;
                            textView.setPadding(15, 15, 15, 15);
                            textView.setTypeface((Typeface) null, 1);
                            textView.setTextSize(0, Port_Verify_Activity.this.getResources().getDimension(R.dimen.mediumtext));
                            textView.setBackgroundResource(R.drawable.new_style1);
                            TextView textView2 = new TextView(Port_Verify_Activity.this);
                            textView2.setLayoutParams(new TableRow.LayoutParams(0, -1, 1.7f));
                            textView2.setText("EXGCODE");
                            textView2.setTextColor(-1);
                            textView2.setGravity(17);
                            textView2.setPadding(0, 15, 0, 15);
                            textView2.setTypeface((Typeface) null, 1);
                            textView2.setTextSize(0, Port_Verify_Activity.this.getResources().getDimension(R.dimen.mediumtext));
                            textView2.setBackgroundResource(R.drawable.new_style1);
                            TextView textView3 = new TextView(Port_Verify_Activity.this);
                            textView3.setLayoutParams(new TableRow.LayoutParams(0, -1, 3.0f));
                            textView3.setText("SERVICE");
                            textView3.setTextColor(-1);
                            textView3.setGravity(17);
                            textView3.setPadding(0, 15, 0, 15);
                            textView3.setTypeface((Typeface) null, 1);
                            textView3.setTextSize(0, Port_Verify_Activity.this.getResources().getDimension(R.dimen.mediumtext));
                            textView3.setBackgroundResource(R.drawable.new_style1);
                            TextView textView4 = new TextView(Port_Verify_Activity.this);
                            textView4.setLayoutParams(new TableRow.LayoutParams(0, -1, 1.7f));
                            textView4.setText("PORT");
                            textView4.setTextColor(-1);
                            textView4.setGravity(17);
                            textView4.setPadding(0, 15, 0, 15);
                            textView4.setTypeface((Typeface) null, 1);
                            textView4.setTextSize(0, Port_Verify_Activity.this.getResources().getDimension(R.dimen.mediumtext));
                            textView4.setBackgroundResource(R.drawable.new_style1);
                            TextView textView5 = new TextView(Port_Verify_Activity.this);
                            textView5.setLayoutParams(new TableRow.LayoutParams(0, -1, 2.8f));
                            textView5.setText("TIMESTAMP");
                            textView5.setTextColor(-1);
                            textView5.setGravity(17);
                            textView5.setPadding(15, 15, 15, 15);
                            textView5.setTypeface((Typeface) null, 1);
                            textView5.setTextSize(0, Port_Verify_Activity.this.getResources().getDimension(R.dimen.mediumtext));
                            textView5.setBackgroundResource(R.drawable.new_style1);
                            tableRow.addView(textView);
                            tableRow.addView(textView2);
                            tableRow.addView(textView3);
                            tableRow.addView(textView4);
                            tableRow.addView(textView5);
                            Port_Verify_Activity.this.tab_portverify.addView(tableRow);
                            JSONArray jSONArray = jSONObject.getJSONArray("portverify");
                            int i2 = 0;
                            while (i2 < jSONArray.length()) {
                                int i3 = i2 + 1;
                                JSONObject jSONObject2 = jSONArray.getJSONObject(i2);
                                TableRow tableRow2 = new TableRow(Port_Verify_Activity.this);
                                tableRow2.setLayoutParams(new TableRow.LayoutParams(-1, -1));
                                TextView textView6 = new TextView(Port_Verify_Activity.this);
                                textView6.setLayoutParams(new TableRow.LayoutParams(0, -1, 0.8f));
                                textView6.setText(Integer.toString(i3));
                                textView6.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                                textView6.setGravity(17);
                                textView6.setPadding(i, i, i, i);
                                textView6.setTextSize(0, Port_Verify_Activity.this.getResources().getDimension(R.dimen.mediumtext));
                                textView6.setBackgroundResource(R.drawable.style17);
                                TextView textView7 = new TextView(Port_Verify_Activity.this);
                                textView7.setLayoutParams(new TableRow.LayoutParams(0, -1, 1.7f));
                                textView7.setText(jSONObject2.getString("exgcode"));
                                textView7.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                                textView7.setGravity(17);
                                textView7.setPadding(0, i, 0, i);
                                textView7.setTextSize(0, Port_Verify_Activity.this.getResources().getDimension(R.dimen.mediumtext));
                                textView7.setBackgroundResource(R.drawable.style17);
                                TextView textView8 = new TextView(Port_Verify_Activity.this);
                                textView8.setLayoutParams(new TableRow.LayoutParams(0, -1, 3.0f));
                                textView8.setText(jSONObject2.getString(NotificationCompat.CATEGORY_SERVICE));
                                textView8.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                                textView8.setGravity(17);
                                textView8.setPadding(0, i, 0, i);
                                textView8.setTextSize(0, Port_Verify_Activity.this.getResources().getDimension(R.dimen.mediumtext));
                                textView8.setBackgroundResource(R.drawable.style17);
                                TextView textView9 = new TextView(Port_Verify_Activity.this);
                                textView9.setLayoutParams(new TableRow.LayoutParams(0, -1, 1.7f));
                                textView9.setText(jSONObject2.getString("vlan"));
                                textView9.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                                textView9.setGravity(17);
                                textView9.setPadding(0, i, 0, i);
                                textView9.setTextSize(0, Port_Verify_Activity.this.getResources().getDimension(R.dimen.mediumtext));
                                textView9.setBackgroundResource(R.drawable.style17);
                                TextView textView10 = new TextView(Port_Verify_Activity.this);
                                textView10.setLayoutParams(new TableRow.LayoutParams(0, -1, 2.8f));
                                textView10.setText(jSONObject2.getString("time"));
                                textView10.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                                textView10.setGravity(17);
                                textView10.setPadding(0, 15, 0, 15);
                                textView10.setTextSize(0, Port_Verify_Activity.this.getResources().getDimension(R.dimen.mediumtext));
                                textView10.setBackgroundResource(R.drawable.style17);
                                tableRow2.addView(textView6);
                                tableRow2.addView(textView7);
                                tableRow2.addView(textView8);
                                tableRow2.addView(textView9);
                                tableRow2.addView(textView10);
                                Port_Verify_Activity.this.tab_portverify.addView(tableRow2);
                                Port_Verify_Activity.this.lay_portverify.setVisibility(0);
                                i = 15;
                                i2 = i3;
                            }
                        } else {
                            new AlertHelperclass().ntoast(jSONObject.getString("error_log"), Port_Verify_Activity.this.getApplicationContext());
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                public void onErrorResponse(VolleyError volleyError) {
                    Port_Verify_Activity.this.progress_dialog.cancel();
                    Port_Verify_Activity.this.txt_alert.setText(new VolleyErrorHelper().onErrorResponse(volleyError, Port_Verify_Activity.this.getApplicationContext()));
                    Port_Verify_Activity.this.error_dialog.show();
                }
            }) {
                /* access modifiers changed from: protected */
                public Map<String, String> getParams() throws AuthFailureError {
                    HashMap hashMap = new HashMap();
                    hashMap.put("bngname", obj);
                    hashMap.put("userid", str);
                    hashMap.put("username", Port_Verify_Activity.this.Pref_Username);
                    hashMap.put("random_key", Port_Verify_Activity.this.Pref_Randkey);
                    hashMap.put("device_id", Port_Verify_Activity.this.androidId);
                    return hashMap;
                }
            };
            r0.setRetryPolicy(new DefaultRetryPolicy(BZip2Constants.BASEBLOCKSIZE, 1, 1.0f));
            newRequestQueue.add(r0);
        }
    }
}
