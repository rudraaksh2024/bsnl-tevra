package bsnl.bsnl_teevra;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
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

public class PMS_Setting_Activity extends AppCompatActivity implements View.OnClickListener {
    private String Pref_Access_Level;
    /* access modifiers changed from: private */
    public String Pref_Circle;
    private String Pref_Nw_Glance;
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
    public AlertDialog error_dialog;
    private ImageView imageView;
    private LinearLayout lay1;
    private LinearLayout lay_pms_tip;
    /* access modifiers changed from: private */
    public AlertDialog progress_dialog;
    private SharedPreferences sharedPreferences;
    private Spannable span_tip;
    /* access modifiers changed from: private */
    public TextView txt_alert;
    /* access modifiers changed from: private */
    public TextView txt_pms_disabled;
    /* access modifiers changed from: private */
    public TextView txt_pms_enabled;
    /* access modifiers changed from: private */
    public TextView txt_pms_total;
    private TextView txt_tip;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.pms_setting_activity);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int i = displayMetrics.widthPixels;
        SharedPreferences sharedPreferences2 = getSharedPreferences("myloginpreference", 0);
        this.sharedPreferences = sharedPreferences2;
        this.Pref_Role = sharedPreferences2.getString("role_Key", (String) null);
        this.Pref_Access_Level = this.sharedPreferences.getString("access_level_Key", (String) null);
        this.Pref_Circle = this.sharedPreferences.getString("circle_Key", (String) null);
        this.Pref_SSA = this.sharedPreferences.getString("ssa_Key", (String) null);
        this.Pref_Username = this.sharedPreferences.getString("username_Key", (String) null);
        this.Pref_Randkey = this.sharedPreferences.getString("rand_Key", (String) null);
        this.androidId = Settings.Secure.getString(getContentResolver(), "android_id");
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.lay1);
        this.lay1 = linearLayout;
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, (int) (((double) i) / 2.5d)));
        this.lay_pms_tip = (LinearLayout) findViewById(R.id.lay_pms_tip);
        this.txt_pms_enabled = (TextView) findViewById(R.id.txt_pms_enabled);
        this.txt_pms_disabled = (TextView) findViewById(R.id.txt_pms_disabled);
        this.txt_pms_total = (TextView) findViewById(R.id.txt_pms_total);
        this.txt_tip = (TextView) findViewById(R.id.txt_tip);
        this.imageView = (ImageView) findViewById(R.id.img_header);
        SpannableString spannableString = new SpannableString(this.txt_tip.getText());
        this.span_tip = spannableString;
        spannableString.setSpan(new RelativeSizeSpan(3.0f), 0, 1, 33);
        this.txt_tip.setText(this.span_tip);
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
        RequestQueue newRequestQueue = Volley.newRequestQueue(this);
        AnonymousClass3 r5 = new StringRequest(1, getString(R.string.serverip) + "report_misc_pms_index.php", new Response.Listener<String>() {
            public void onResponse(String str) {
                PMS_Setting_Activity.this.progress_dialog.cancel();
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    String string = jSONObject.getString("ONLINE");
                    String string2 = jSONObject.getString("OFFLINE");
                    String string3 = jSONObject.getString("TOTAL");
                    if (jSONObject.getInt("ONLINE") > 0) {
                        PMS_Setting_Activity.this.txt_pms_enabled.setTextColor(PMS_Setting_Activity.this.getResources().getColor(R.color.colorGreen));
                    } else {
                        PMS_Setting_Activity.this.txt_pms_enabled.setTextColor(PMS_Setting_Activity.this.getResources().getColor(R.color.colorRed));
                    }
                    if (jSONObject.getInt("OFFLINE") > 0) {
                        PMS_Setting_Activity.this.txt_pms_disabled.setTextColor(PMS_Setting_Activity.this.getResources().getColor(R.color.colorRed));
                    } else {
                        PMS_Setting_Activity.this.txt_pms_disabled.setTextColor(PMS_Setting_Activity.this.getResources().getColor(R.color.colorGreen));
                    }
                    PMS_Setting_Activity.this.txt_pms_enabled.setText(string);
                    PMS_Setting_Activity.this.txt_pms_disabled.setText(string2);
                    PMS_Setting_Activity.this.txt_pms_total.setText(string3);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError volleyError) {
                PMS_Setting_Activity.this.progress_dialog.cancel();
                PMS_Setting_Activity.this.txt_alert.setText(new VolleyErrorHelper().onErrorResponse(volleyError, PMS_Setting_Activity.this.getApplicationContext()));
                PMS_Setting_Activity.this.error_dialog.show();
            }
        }) {
            /* access modifiers changed from: protected */
            public Map<String, String> getParams() throws AuthFailureError {
                HashMap hashMap = new HashMap();
                hashMap.put("circle", PMS_Setting_Activity.this.Pref_Circle);
                hashMap.put("ssa", PMS_Setting_Activity.this.Pref_SSA);
                hashMap.put("username", PMS_Setting_Activity.this.Pref_Username);
                hashMap.put("random_key", PMS_Setting_Activity.this.Pref_Randkey);
                hashMap.put("device_id", PMS_Setting_Activity.this.androidId);
                return hashMap;
            }
        };
        r5.setRetryPolicy(new DefaultRetryPolicy(BZip2Constants.BASEBLOCKSIZE, 1, 1.0f));
        newRequestQueue.add(r5);
        this.lay_pms_tip.setOnClickListener(this);
        this.imageView.setOnClickListener(this);
    }

    public void onClick(View view) {
        if (view.getId() == R.id.img_header) {
            Intent intent = new Intent(this, DashBoard_New.class);
            intent.setFlags(67108864);
            startActivity(intent);
            finish();
        } else if (view.getId() == R.id.lay_pms_tip) {
            startActivity(new Intent(this, PMS_Setting_Tip_Activity.class));
        }
    }
}
