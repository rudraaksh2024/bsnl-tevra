package bsnl.bsnl_teevra;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.text.Html;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class FMS_DashBoard_Activity extends AppCompatActivity implements View.OnClickListener {
    private String Pref_Circle;
    private String Pref_Fms_Role;
    private String Pref_Fms_TeamId;
    private String Pref_Fms_UserId;
    /* access modifiers changed from: private */
    public String Pref_Fms_Username;
    private String Pref_Fms_Zone;
    private String Pref_Fms_role;
    /* access modifiers changed from: private */
    public String Pref_Randkey;
    private String Pref_SSA;
    /* access modifiers changed from: private */
    public String Pref_Username;
    private int Pref_fms_actual_teamid;
    private int Pref_fms_loggedin_teamid;
    /* access modifiers changed from: private */
    public String androidId;
    private LinearLayout card_fms_fault;
    private LinearLayout card_fms_provision;
    private LinearLayout card_fms_report;
    private LinearLayout card_fms_settings;
    SharedPreferences.Editor editor;
    /* access modifiers changed from: private */
    public AlertDialog error_dialog;
    private AlertDialog info_dialog;
    private Intent intent;
    private LinearLayout lay1;
    private LinearLayout lay2;
    /* access modifiers changed from: private */
    public AlertDialog logout_dialog;
    /* access modifiers changed from: private */
    public AlertDialog progress_dialog;
    SharedPreferences sharedPreferences;
    /* access modifiers changed from: private */
    public TextView txt_alert;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.fms_dashboard_activity);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int i = displayMetrics.widthPixels;
        SharedPreferences sharedPreferences2 = getSharedPreferences("myloginpreference", 0);
        this.sharedPreferences = sharedPreferences2;
        this.Pref_Fms_Username = sharedPreferences2.getString("fms_username_Key", (String) null);
        this.Pref_Fms_role = this.sharedPreferences.getString("fms_role_key", (String) null);
        SharedPreferences sharedPreferences3 = getSharedPreferences("myloginpreference", 0);
        this.sharedPreferences = sharedPreferences3;
        this.Pref_Fms_Username = sharedPreferences3.getString("fms_username_Key", (String) null);
        this.Pref_Fms_UserId = this.sharedPreferences.getString("fms_userid_key", (String) null);
        this.Pref_Username = this.sharedPreferences.getString("username_Key", (String) null);
        this.Pref_Randkey = this.sharedPreferences.getString("rand_Key", (String) null);
        this.Pref_Fms_Zone = this.sharedPreferences.getString("fms_user_zone_Key", (String) null);
        this.Pref_Fms_TeamId = this.sharedPreferences.getString("fms_teamid_key", (String) null);
        this.Pref_Circle = this.sharedPreferences.getString("circle_Key", (String) null);
        this.Pref_SSA = this.sharedPreferences.getString("ssa_Key", (String) null);
        this.androidId = Settings.Secure.getString(getContentResolver(), "android_id");
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View inflate = getLayoutInflater().inflate(R.layout.custom_progress, (ViewGroup) null);
        builder.setCancelable(false);
        builder.setView(inflate);
        this.progress_dialog = builder.create();
        AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
        View inflate2 = getLayoutInflater().inflate(R.layout.custom_error, (ViewGroup) null);
        this.txt_alert = (TextView) inflate2.findViewById(R.id.txt_error);
        builder2.setCancelable(false);
        builder2.setNegativeButton("Retry", (DialogInterface.OnClickListener) null);
        builder2.setView(inflate2);
        this.error_dialog = builder2.create();
        getIntent().getStringExtra("platform");
        this.lay1 = (LinearLayout) findViewById(R.id.lay1);
        this.lay2 = (LinearLayout) findViewById(R.id.lay2);
        int i2 = (int) (((double) i) / 2.5d);
        this.lay1.setLayoutParams(new LinearLayout.LayoutParams(-1, i2));
        this.lay2.setLayoutParams(new LinearLayout.LayoutParams(-1, i2));
        this.card_fms_provision = (LinearLayout) findViewById(R.id.card_fms_provision);
        this.card_fms_fault = (LinearLayout) findViewById(R.id.card_fms_fault);
        this.card_fms_report = (LinearLayout) findViewById(R.id.card_fms_report);
        this.card_fms_settings = (LinearLayout) findViewById(R.id.card_fms_settings);
        this.card_fms_provision.setOnClickListener(this);
        this.card_fms_fault.setOnClickListener(this);
        this.card_fms_report.setOnClickListener(this);
        this.card_fms_settings.setOnClickListener(this);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        Intent intent2 = getIntent();
        this.intent = intent2;
        intent2.getStringExtra("fms_username");
        getMenuInflater().inflate(R.menu.fmsmenu, menu);
        menu.findItem(R.id.fmsuser).setTitle(this.Pref_Fms_Username);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        if (itemId == R.id.teevrahome) {
            Intent intent2 = new Intent(this, DashBoard_New.class);
            intent2.setFlags(67108864);
            startActivity(intent2);
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
                    FMS_DashBoard_Activity.this.logout_dialog.cancel();
                }
            });
            ((TextView) inflate.findViewById(R.id.txt_submit)).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    FMS_DashBoard_Activity fMS_DashBoard_Activity = FMS_DashBoard_Activity.this;
                    fMS_DashBoard_Activity.editor = fMS_DashBoard_Activity.sharedPreferences.edit();
                    FMS_DashBoard_Activity.this.editor.remove("isfmsloggedin_key");
                    FMS_DashBoard_Activity.this.editor.commit();
                    Intent intent = new Intent(FMS_DashBoard_Activity.this, FMS_Login_Activity.class);
                    intent.setFlags(67108864);
                    FMS_DashBoard_Activity.this.startActivity(intent);
                    FMS_DashBoard_Activity.this.finish();
                    FMS_DashBoard_Activity.this.logout_dialog.cancel();
                }
            });
        }
        return super.onOptionsItemSelected(menuItem);
    }

    public void onClick(View view) {
        String stringExtra = getIntent().getStringExtra("platform");
        if (view.getId() == R.id.card_fms_provision) {
            if (stringExtra.equals("DSCM")) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                View inflate = getLayoutInflater().inflate(R.layout.custom_logout, (ViewGroup) null);
                TextView textView = (TextView) inflate.findViewById(R.id.txt_submit);
                ((TextView) inflate.findViewById(R.id.txt_info)).setText("INFORMATION\n");
                ((TextView) inflate.findViewById(R.id.txt_error)).setText(Html.fromHtml("<b>" + this.Pref_SSA + "</b> SSA Is Migrated To CDR3<br>For Provision Orders Kindly Refer FOA"));
                ((TextView) inflate.findViewById(R.id.txt_cancel)).setVisibility(4);
                textView.setText("OK");
                builder.setCancelable(false);
                builder.setView(inflate);
                AlertDialog create = builder.create();
                this.logout_dialog = create;
                create.show();
                textView.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        FMS_DashBoard_Activity.this.logout_dialog.cancel();
                    }
                });
                return;
            }
            fms_provision();
        } else if (view.getId() == R.id.card_fms_fault) {
            if (stringExtra.equals("DSCM")) {
                AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
                View inflate2 = getLayoutInflater().inflate(R.layout.custom_logout, (ViewGroup) null);
                TextView textView2 = (TextView) inflate2.findViewById(R.id.txt_submit);
                ((TextView) inflate2.findViewById(R.id.txt_info)).setText("INFORMATION\n");
                ((TextView) inflate2.findViewById(R.id.txt_error)).setText(Html.fromHtml("<b>" + this.Pref_SSA + "</b> SSA Is Migrated To CDR3<br>For Fault Orders Kindly Refer FOA"));
                ((TextView) inflate2.findViewById(R.id.txt_cancel)).setVisibility(4);
                textView2.setText("OK");
                builder2.setCancelable(false);
                builder2.setView(inflate2);
                AlertDialog create2 = builder2.create();
                this.logout_dialog = create2;
                create2.show();
                textView2.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        FMS_DashBoard_Activity.this.logout_dialog.cancel();
                    }
                });
                return;
            }
            fms_fault();
        } else if (view.getId() == R.id.card_fms_report) {
            startActivity(new Intent(this, FMS_Reports_Activity.class));
        } else if (view.getId() != R.id.card_fms_settings) {
        } else {
            if (this.Pref_Fms_role.equals("FMS-ADMIN")) {
                startActivity(new Intent(this, Fms_Admin_Activity.class));
                return;
            }
            AlertDialog.Builder builder3 = new AlertDialog.Builder(this);
            View inflate3 = getLayoutInflater().inflate(R.layout.custom_logout, (ViewGroup) null);
            TextView textView3 = (TextView) inflate3.findViewById(R.id.txt_submit);
            ((TextView) inflate3.findViewById(R.id.txt_info)).setText("ACCESS RESTRICTED");
            ((TextView) inflate3.findViewById(R.id.txt_error)).setText("Only Franchisee Owner Can Access");
            ((TextView) inflate3.findViewById(R.id.txt_cancel)).setVisibility(4);
            textView3.setText("OK");
            builder3.setCancelable(false);
            builder3.setView(inflate3);
            AlertDialog create3 = builder3.create();
            this.logout_dialog = create3;
            create3.show();
            textView3.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    FMS_DashBoard_Activity.this.logout_dialog.cancel();
                }
            });
        }
    }

    private void fms_provision() {
        this.progress_dialog.show();
        RequestQueue newRequestQueue = Volley.newRequestQueue(this);
        AnonymousClass8 r1 = new StringRequest(1, getString(R.string.serverip) + "fms/fms_provision_count.php", new Response.Listener<String>() {
            public void onResponse(String str) {
                FMS_DashBoard_Activity.this.progress_dialog.cancel();
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    Intent intent = new Intent(FMS_DashBoard_Activity.this, Fms_Provison_MainActivity.class);
                    intent.putExtra("RESPONSE", jSONObject.toString());
                    FMS_DashBoard_Activity.this.startActivity(intent);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError volleyError) {
                FMS_DashBoard_Activity.this.progress_dialog.cancel();
                FMS_DashBoard_Activity.this.txt_alert.setText(new VolleyErrorHelper().onErrorResponse(volleyError, FMS_DashBoard_Activity.this.getApplicationContext()));
                FMS_DashBoard_Activity.this.error_dialog.show();
            }
        }) {
            /* access modifiers changed from: protected */
            public Map<String, String> getParams() throws AuthFailureError {
                HashMap hashMap = new HashMap();
                hashMap.put("fms_userid", FMS_DashBoard_Activity.this.Pref_Fms_Username);
                hashMap.put("username", FMS_DashBoard_Activity.this.Pref_Username);
                hashMap.put("random_key", FMS_DashBoard_Activity.this.Pref_Randkey);
                hashMap.put("device_id", FMS_DashBoard_Activity.this.androidId);
                return hashMap;
            }
        };
        r1.setRetryPolicy(new DefaultRetryPolicy(BZip2Constants.BASEBLOCKSIZE, 1, 1.0f));
        newRequestQueue.add(r1);
    }

    private void fms_fault() {
        this.progress_dialog.show();
        RequestQueue newRequestQueue = Volley.newRequestQueue(this);
        AnonymousClass11 r1 = new StringRequest(1, getString(R.string.serverip) + "fms/fms_fault_count.php", new Response.Listener<String>() {
            public void onResponse(String str) {
                FMS_DashBoard_Activity.this.progress_dialog.cancel();
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    Intent intent = new Intent(FMS_DashBoard_Activity.this, Fms_Fault_MainActivity.class);
                    intent.putExtra("RESPONSE", jSONObject.toString());
                    FMS_DashBoard_Activity.this.startActivity(intent);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError volleyError) {
                FMS_DashBoard_Activity.this.progress_dialog.cancel();
                FMS_DashBoard_Activity.this.txt_alert.setText(new VolleyErrorHelper().onErrorResponse(volleyError, FMS_DashBoard_Activity.this.getApplicationContext()));
                FMS_DashBoard_Activity.this.error_dialog.show();
            }
        }) {
            /* access modifiers changed from: protected */
            public Map<String, String> getParams() throws AuthFailureError {
                HashMap hashMap = new HashMap();
                hashMap.put("fms_userid", FMS_DashBoard_Activity.this.Pref_Fms_Username);
                hashMap.put("username", FMS_DashBoard_Activity.this.Pref_Username);
                hashMap.put("random_key", FMS_DashBoard_Activity.this.Pref_Randkey);
                hashMap.put("device_id", FMS_DashBoard_Activity.this.androidId);
                return hashMap;
            }
        };
        r1.setRetryPolicy(new DefaultRetryPolicy(BZip2Constants.BASEBLOCKSIZE, 1, 1.0f));
        newRequestQueue.add(r1);
    }
}
