package bsnl.bsnl_teevra;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.text.Html;
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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FMS_Reports_Activity extends AppCompatActivity implements View.OnClickListener {
    /* access modifiers changed from: private */
    public String Pref_Circle;
    private String Pref_Fms_Role;
    private String Pref_Fms_TeamId;
    private String Pref_Fms_UserId;
    /* access modifiers changed from: private */
    public String Pref_Fms_Username;
    private String Pref_Fms_Zone;
    private String Pref_Fms_role;
    /* access modifiers changed from: private */
    public String Pref_Randkey;
    /* access modifiers changed from: private */
    public String Pref_SSA;
    /* access modifiers changed from: private */
    public String Pref_Username;
    /* access modifiers changed from: private */
    public String androidId;
    SharedPreferences.Editor editor;
    /* access modifiers changed from: private */
    public AlertDialog error_dialog;
    private AlertDialog info_dialog;
    private LinearLayout lay_olt_reboot;
    private LinearLayout lay_peak_traffic;
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
        setContentView((int) R.layout.fms_reports_activity);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayShowTitleEnabled(false);
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
        this.lay_peak_traffic = (LinearLayout) findViewById(R.id.lay_peak_traffic);
        this.lay_olt_reboot = (LinearLayout) findViewById(R.id.lay_olt_reboot);
        this.lay_peak_traffic.setOnClickListener(this);
        this.lay_olt_reboot.setOnClickListener(this);
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
                    FMS_Reports_Activity.this.logout_dialog.cancel();
                }
            });
            ((TextView) inflate.findViewById(R.id.txt_submit)).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    FMS_Reports_Activity fMS_Reports_Activity = FMS_Reports_Activity.this;
                    fMS_Reports_Activity.editor = fMS_Reports_Activity.sharedPreferences.edit();
                    FMS_Reports_Activity.this.editor.remove("isfmsloggedin_key");
                    FMS_Reports_Activity.this.editor.commit();
                    Intent intent = new Intent(FMS_Reports_Activity.this, FMS_Login_Activity.class);
                    intent.setFlags(67108864);
                    FMS_Reports_Activity.this.startActivity(intent);
                    FMS_Reports_Activity.this.finish();
                    FMS_Reports_Activity.this.logout_dialog.cancel();
                }
            });
        }
        return super.onOptionsItemSelected(menuItem);
    }

    public void onClick(View view) {
        if (view.getId() == R.id.lay_peak_traffic) {
            peak_traffic();
        } else if (view.getId() == R.id.lay_olt_reboot) {
            startActivity(new Intent(this, FMS_Report_Reboot_Activity.class));
        }
    }

    private void peak_traffic() {
        this.progress_dialog.show();
        RequestQueue newRequestQueue = Volley.newRequestQueue(this);
        AnonymousClass5 r1 = new StringRequest(1, getString(R.string.serverip) + "report/peak_traffic_utilization1.php", new Response.Listener<String>() {
            public void onResponse(String str) {
                FMS_Reports_Activity.this.progress_dialog.cancel();
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    if (jSONObject.getBoolean("SUCCESS")) {
                        JSONArray jSONArray = jSONObject.getJSONArray("ROWSET");
                        Intent intent = new Intent(FMS_Reports_Activity.this, FMS_Report_Traffic_Activity.class);
                        intent.putExtra("RESPONSE", jSONArray.toString());
                        FMS_Reports_Activity.this.startActivity(intent);
                        return;
                    }
                    FMS_Reports_Activity.this.txt_alert.setText(Html.fromHtml(jSONObject.getString("ERROR")));
                    FMS_Reports_Activity.this.error_dialog.show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError volleyError) {
                FMS_Reports_Activity.this.progress_dialog.cancel();
                FMS_Reports_Activity.this.txt_alert.setText(new VolleyErrorHelper().onErrorResponse(volleyError, FMS_Reports_Activity.this.getApplicationContext()));
                FMS_Reports_Activity.this.error_dialog.show();
            }
        }) {
            /* access modifiers changed from: protected */
            public Map<String, String> getParams() throws AuthFailureError {
                HashMap hashMap = new HashMap();
                hashMap.put("fms_userid", FMS_Reports_Activity.this.Pref_Fms_Username);
                hashMap.put("circle", FMS_Reports_Activity.this.Pref_Circle);
                hashMap.put("ssa", FMS_Reports_Activity.this.Pref_SSA);
                hashMap.put("username", FMS_Reports_Activity.this.Pref_Username);
                hashMap.put("random_key", FMS_Reports_Activity.this.Pref_Randkey);
                hashMap.put("device_id", FMS_Reports_Activity.this.androidId);
                return hashMap;
            }
        };
        r1.setRetryPolicy(new DefaultRetryPolicy(BZip2Constants.BASEBLOCKSIZE, 1, 1.0f));
        newRequestQueue.add(r1);
    }
}
