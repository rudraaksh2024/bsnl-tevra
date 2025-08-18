package bsnl.bsnl_teevra;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.stats.CodePackage;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class Manage_Inventory_Delete_Switch_Activity extends AppCompatActivity implements View.OnClickListener {
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
    private TextView btn_inventory_submit;
    /* access modifiers changed from: private */
    public AlertDialog error_dialog;
    private EditText et_inventory_comment;
    private ImageView imageView;
    /* access modifiers changed from: private */
    public AlertDialog progress_dialog;
    private SharedPreferences sharedPreferences;
    /* access modifiers changed from: private */
    public TextView txt_alert;
    private TextView txt_inventory_bngip;
    private TextView txt_inventory_bngname;
    private TextView txt_inventory_circle;
    /* access modifiers changed from: private */
    public TextView txt_inventory_comment;
    private TextView txt_inventory_element;
    private TextView txt_inventory_exgcode;
    /* access modifiers changed from: private */
    public TextView txt_inventory_ip_detail;
    private TextView txt_inventory_loc;
    private TextView txt_inventory_make_detail;
    private TextView txt_inventory_ssa;
    private TextView txt_inventory_vendor;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.manage_inventory_delete_switch_activity);
        SharedPreferences sharedPreferences2 = getSharedPreferences("myloginpreference", 0);
        this.sharedPreferences = sharedPreferences2;
        this.Pref_Role = sharedPreferences2.getString("role_Key", (String) null);
        this.Pref_Circle = this.sharedPreferences.getString("circle_Key", (String) null);
        this.Pref_SSA = this.sharedPreferences.getString("ssa_Key", (String) null);
        this.Pref_Username = this.sharedPreferences.getString("username_Key", (String) null);
        this.Pref_Randkey = this.sharedPreferences.getString("rand_Key", (String) null);
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
        Intent intent = getIntent();
        this.imageView = (ImageView) findViewById(R.id.img_header);
        TextView textView = (TextView) findViewById(R.id.txt_inventory_vendor);
        this.txt_inventory_vendor = textView;
        textView.setText(intent.getStringExtra("VENDOR").trim());
        TextView textView2 = (TextView) findViewById(R.id.txt_inventory_element);
        this.txt_inventory_element = textView2;
        textView2.setText(intent.getStringExtra("N/W_ELEMENT").trim());
        TextView textView3 = (TextView) findViewById(R.id.txt_inventory_circle);
        this.txt_inventory_circle = textView3;
        textView3.setText(intent.getStringExtra("CIRCLE").trim());
        TextView textView4 = (TextView) findViewById(R.id.txt_inventory_ssa);
        this.txt_inventory_ssa = textView4;
        textView4.setText(intent.getStringExtra("SSA").trim());
        TextView textView5 = (TextView) findViewById(R.id.txt_inventory_exgcode);
        this.txt_inventory_exgcode = textView5;
        textView5.setText(intent.getStringExtra("EXGCODE").trim());
        TextView textView6 = (TextView) findViewById(R.id.txt_inventory_loc);
        this.txt_inventory_loc = textView6;
        textView6.setText(intent.getStringExtra(CodePackage.LOCATION).trim());
        TextView textView7 = (TextView) findViewById(R.id.txt_inventory_ip_detail);
        this.txt_inventory_ip_detail = textView7;
        textView7.setText(intent.getStringExtra("IP").trim());
        TextView textView8 = (TextView) findViewById(R.id.txt_inventory_make);
        this.txt_inventory_make_detail = textView8;
        textView8.setText(intent.getStringExtra("MAKE").trim());
        TextView textView9 = (TextView) findViewById(R.id.txt_inventory_bngname);
        this.txt_inventory_bngname = textView9;
        textView9.setText(intent.getStringExtra("BNGNAME"));
        TextView textView10 = (TextView) findViewById(R.id.txt_inventory_bngip);
        this.txt_inventory_bngip = textView10;
        textView10.setText(intent.getStringExtra("BNGIP"));
        TextView textView11 = (TextView) findViewById(R.id.btn_inventory_submit);
        this.btn_inventory_submit = textView11;
        textView11.setText("DELETE AGGR-SWITCH");
        this.txt_inventory_comment = (TextView) findViewById(R.id.txt_inventory_comment);
        EditText editText = (EditText) findViewById(R.id.et_inventory_comment);
        this.et_inventory_comment = editText;
        editText.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable editable) {
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                String str = charSequence.length() + "/100";
                if (charSequence.length() < 90) {
                    Manage_Inventory_Delete_Switch_Activity.this.txt_inventory_comment.setTextColor(Manage_Inventory_Delete_Switch_Activity.this.getResources().getColor(R.color.colorblack));
                } else {
                    Manage_Inventory_Delete_Switch_Activity.this.txt_inventory_comment.setTextColor(Manage_Inventory_Delete_Switch_Activity.this.getResources().getColor(R.color.colorRed));
                }
                Manage_Inventory_Delete_Switch_Activity.this.txt_inventory_comment.setText(str);
            }
        });
        this.imageView.setOnClickListener(this);
        this.btn_inventory_submit.setOnClickListener(this);
    }

    public void onClick(View view) {
        if (view.getId() == R.id.img_header) {
            startActivity(new Intent(this, DashBoard_New.class));
            finish();
        } else if (view.getId() == R.id.btn_inventory_submit) {
            final String upperCase = this.et_inventory_comment.getText().toString().trim().toUpperCase();
            if (upperCase.equals("")) {
                new AlertHelperclass().ntoast("PLEASE MENTION A VALID REASON FOR DELETING THE INVENTORY", getApplicationContext());
                return;
            }
            this.progress_dialog.show();
            RequestQueue newRequestQueue = Volley.newRequestQueue(this);
            AnonymousClass4 r0 = new StringRequest(1, getString(R.string.serverip) + "manage_inventory_delete1.php", new Response.Listener<String>() {
                public void onResponse(String str) {
                    Manage_Inventory_Delete_Switch_Activity.this.progress_dialog.cancel();
                    try {
                        JSONObject jSONObject = new JSONObject(str);
                        if (Boolean.valueOf(jSONObject.getBoolean("success")).booleanValue()) {
                            new AlertHelperclass().ptoast("AGGR-SWITCH INVENTORY IS DELETED SUCCESSFULLY !!", Manage_Inventory_Delete_Switch_Activity.this.getApplicationContext());
                            Manage_Inventory_Delete_Switch_Activity.this.startActivity(new Intent(Manage_Inventory_Delete_Switch_Activity.this, Manage_Inventory_Activity.class));
                            Manage_Inventory_Delete_Switch_Activity.this.finish();
                            return;
                        }
                        Manage_Inventory_Delete_Switch_Activity.this.txt_alert.setText(jSONObject.getString("error_log"));
                        Manage_Inventory_Delete_Switch_Activity.this.error_dialog.show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                public void onErrorResponse(VolleyError volleyError) {
                    String onErrorResponse = new VolleyErrorHelper().onErrorResponse(volleyError, Manage_Inventory_Delete_Switch_Activity.this.getApplicationContext());
                    Manage_Inventory_Delete_Switch_Activity.this.progress_dialog.cancel();
                    Manage_Inventory_Delete_Switch_Activity.this.txt_alert.setText(onErrorResponse);
                    Manage_Inventory_Delete_Switch_Activity.this.error_dialog.show();
                }
            }) {
                /* access modifiers changed from: protected */
                public Map<String, String> getParams() throws AuthFailureError {
                    HashMap hashMap = new HashMap();
                    hashMap.put("username", Manage_Inventory_Delete_Switch_Activity.this.Pref_Username);
                    hashMap.put("random_key", Manage_Inventory_Delete_Switch_Activity.this.Pref_Randkey);
                    hashMap.put("device_id", Manage_Inventory_Delete_Switch_Activity.this.androidId);
                    hashMap.put("network_element", "AGGR-SWITCH");
                    hashMap.put("ip", Manage_Inventory_Delete_Switch_Activity.this.txt_inventory_ip_detail.getText().toString());
                    hashMap.put("circle", Manage_Inventory_Delete_Switch_Activity.this.Pref_Circle);
                    hashMap.put("ssa", Manage_Inventory_Delete_Switch_Activity.this.Pref_SSA);
                    hashMap.put("comment", upperCase);
                    return hashMap;
                }
            };
            r0.setRetryPolicy(new DefaultRetryPolicy(BZip2Constants.BASEBLOCKSIZE, 1, 1.0f));
            newRequestQueue.add(r0);
        }
    }

    public void onBackPressed() {
        Intent intent = new Intent(this, Manage_Inventory_Activity.class);
        intent.setFlags(67108864);
        startActivity(intent);
        finish();
    }
}
