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

public class Manage_User_Delete_Activity extends AppCompatActivity implements View.OnClickListener {
    private String Pref_Circle;
    /* access modifiers changed from: private */
    public String Pref_Randkey;
    private String Pref_Role;
    private String Pref_SSA;
    /* access modifiers changed from: private */
    public String Pref_Username;
    /* access modifiers changed from: private */
    public String androidId;
    private TextView btn_delete;
    /* access modifiers changed from: private */
    public AlertDialog error_dialog;
    /* access modifiers changed from: private */
    public EditText et_acct_comment;
    private ImageView img_header;
    /* access modifiers changed from: private */
    public LinearLayout lay_delete_user;
    /* access modifiers changed from: private */
    public AlertDialog progress_dialog;
    private SharedPreferences sharedPreferences;
    /* access modifiers changed from: private */
    public TextView txt_access_level;
    /* access modifiers changed from: private */
    public TextView txt_account_status;
    /* access modifiers changed from: private */
    public TextView txt_acct_comment;
    /* access modifiers changed from: private */
    public TextView txt_acct_creationdate;
    /* access modifiers changed from: private */
    public TextView txt_alert;
    /* access modifiers changed from: private */
    public TextView txt_designation;
    /* access modifiers changed from: private */
    public TextView txt_email;
    /* access modifiers changed from: private */
    public TextView txt_last_login;
    /* access modifiers changed from: private */
    public TextView txt_mobile;
    /* access modifiers changed from: private */
    public TextView txt_name;
    /* access modifiers changed from: private */
    public TextView txt_role;
    /* access modifiers changed from: private */
    public TextView txt_unit;
    /* access modifiers changed from: private */
    public TextView txt_username;
    /* access modifiers changed from: private */
    public String user_circle;
    /* access modifiers changed from: private */
    public String user_ssa;
    /* access modifiers changed from: private */
    public String username;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.manage_user_delete_activity);
        Intent intent = getIntent();
        this.username = intent.getStringExtra("username");
        this.user_circle = intent.getStringExtra("user_circle");
        this.user_ssa = intent.getStringExtra("user_ssa");
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
        this.Pref_Role = sharedPreferences2.getString("role_Key", (String) null);
        this.Pref_Circle = this.sharedPreferences.getString("circle_Key", (String) null);
        this.Pref_SSA = this.sharedPreferences.getString("ssa_Key", (String) null);
        this.Pref_Username = this.sharedPreferences.getString("username_Key", (String) null);
        this.Pref_Randkey = this.sharedPreferences.getString("rand_Key", (String) null);
        this.androidId = Settings.Secure.getString(getContentResolver(), "android_id");
        this.img_header = (ImageView) findViewById(R.id.img_header);
        this.btn_delete = (TextView) findViewById(R.id.btn_delete);
        this.txt_username = (TextView) findViewById(R.id.txt_username);
        this.txt_name = (TextView) findViewById(R.id.txt_name);
        this.txt_mobile = (TextView) findViewById(R.id.txt_mobile);
        this.txt_email = (TextView) findViewById(R.id.txt_email);
        this.txt_designation = (TextView) findViewById(R.id.txt_designation);
        this.txt_unit = (TextView) findViewById(R.id.txt_unit);
        this.txt_role = (TextView) findViewById(R.id.txt_role);
        this.txt_access_level = (TextView) findViewById(R.id.txt_access_level);
        this.txt_account_status = (TextView) findViewById(R.id.txt_account_status);
        this.txt_last_login = (TextView) findViewById(R.id.txt_last_login);
        this.txt_acct_creationdate = (TextView) findViewById(R.id.txt_acct_creationdate);
        this.txt_acct_comment = (TextView) findViewById(R.id.txt_acct_comment);
        EditText editText = (EditText) findViewById(R.id.et_acct_comment);
        this.et_acct_comment = editText;
        editText.setEnabled(false);
        this.lay_delete_user = (LinearLayout) findViewById(R.id.lay_delete_user);
        this.et_acct_comment.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable editable) {
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                String str = charSequence.length() + "/100";
                if (charSequence.length() < 90) {
                    Manage_User_Delete_Activity.this.txt_acct_comment.setTextColor(Manage_User_Delete_Activity.this.getResources().getColor(R.color.colorblack));
                } else {
                    Manage_User_Delete_Activity.this.txt_acct_comment.setTextColor(Manage_User_Delete_Activity.this.getResources().getColor(R.color.colorRed));
                }
                Manage_User_Delete_Activity.this.txt_acct_comment.setText(str);
            }
        });
        RequestQueue newRequestQueue = Volley.newRequestQueue(this);
        AnonymousClass4 r3 = new StringRequest(1, getString(R.string.serverip) + "manage_user_getdetail.php", new Response.Listener<String>() {
            public void onResponse(String str) {
                Manage_User_Delete_Activity.this.progress_dialog.cancel();
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    if (Boolean.valueOf(jSONObject.getBoolean("success")).booleanValue()) {
                        Manage_User_Delete_Activity.this.txt_username.setText(jSONObject.getString("USERNAME"));
                        Manage_User_Delete_Activity.this.txt_name.setText(jSONObject.getString("NAME"));
                        Manage_User_Delete_Activity.this.txt_mobile.setText(jSONObject.getString("MOBILE"));
                        Manage_User_Delete_Activity.this.txt_email.setText(jSONObject.getString("EMAIL"));
                        Manage_User_Delete_Activity.this.txt_designation.setText(jSONObject.getString("DESIGNATION"));
                        Manage_User_Delete_Activity.this.txt_unit.setText(jSONObject.getString("UNIT") + ", " + jSONObject.getString("SSA") + ", " + jSONObject.getString("CIRCLE"));
                        Manage_User_Delete_Activity.this.txt_role.setText(jSONObject.getString("ROLE"));
                        Manage_User_Delete_Activity.this.txt_access_level.setText(jSONObject.getString("ACCESS_LEVEL"));
                        if (jSONObject.getBoolean("ACCOUNT_STATUS")) {
                            Manage_User_Delete_Activity.this.txt_account_status.setText("APPROVED");
                            Manage_User_Delete_Activity.this.txt_account_status.setTextColor(Manage_User_Delete_Activity.this.getResources().getColor(R.color.colorGreen));
                        } else {
                            Manage_User_Delete_Activity.this.txt_account_status.setText("PENDING FOR APPROVAL");
                            Manage_User_Delete_Activity.this.txt_account_status.setTextColor(Manage_User_Delete_Activity.this.getResources().getColor(R.color.colorRed));
                        }
                        Manage_User_Delete_Activity.this.txt_acct_creationdate.setText(jSONObject.getString("CREATED_ON"));
                        Manage_User_Delete_Activity.this.txt_last_login.setText(jSONObject.getString("LAST_LOGIN"));
                        Manage_User_Delete_Activity.this.et_acct_comment.setEnabled(true);
                        Manage_User_Delete_Activity.this.lay_delete_user.setVisibility(0);
                        return;
                    }
                    String string = jSONObject.getString("error_log");
                    Manage_User_Delete_Activity.this.progress_dialog.cancel();
                    Manage_User_Delete_Activity.this.txt_alert.setText(string);
                    Manage_User_Delete_Activity.this.error_dialog.show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError volleyError) {
                String onErrorResponse = new VolleyErrorHelper().onErrorResponse(volleyError, Manage_User_Delete_Activity.this.getApplicationContext());
                Manage_User_Delete_Activity.this.progress_dialog.cancel();
                Manage_User_Delete_Activity.this.txt_alert.setText(onErrorResponse);
                Manage_User_Delete_Activity.this.error_dialog.show();
            }
        }) {
            /* access modifiers changed from: protected */
            public Map<String, String> getParams() throws AuthFailureError {
                HashMap hashMap = new HashMap();
                hashMap.put("uid", Manage_User_Delete_Activity.this.username);
                hashMap.put("Circle", Manage_User_Delete_Activity.this.user_circle);
                hashMap.put("SSA", Manage_User_Delete_Activity.this.user_ssa);
                hashMap.put("username", Manage_User_Delete_Activity.this.Pref_Username);
                hashMap.put("random_key", Manage_User_Delete_Activity.this.Pref_Randkey);
                hashMap.put("device_id", Manage_User_Delete_Activity.this.androidId);
                return hashMap;
            }
        };
        r3.setRetryPolicy(new DefaultRetryPolicy(BZip2Constants.BASEBLOCKSIZE, 1, 1.0f));
        newRequestQueue.add(r3);
        this.img_header.setOnClickListener(this);
        this.btn_delete.setOnClickListener(this);
    }

    public void onClick(View view) {
        if (view.getId() == R.id.img_header) {
            startActivity(new Intent(this, DashBoard_New.class));
            finish();
        } else if (view.getId() == R.id.btn_delete) {
            final String upperCase = this.et_acct_comment.getText().toString().trim().toUpperCase();
            if (upperCase.equals("")) {
                new AlertHelperclass().ntoast("PLEASE MENTION A VALID REASON FOR DELETING THE USER", getApplicationContext());
                return;
            }
            this.progress_dialog.show();
            RequestQueue newRequestQueue = Volley.newRequestQueue(this);
            AnonymousClass7 r0 = new StringRequest(1, getString(R.string.serverip) + "manage_user_delete.php", new Response.Listener<String>() {
                public void onResponse(String str) {
                    Manage_User_Delete_Activity.this.progress_dialog.cancel();
                    try {
                        JSONObject jSONObject = new JSONObject(str);
                        if (Boolean.valueOf(jSONObject.getBoolean("success")).booleanValue()) {
                            new AlertHelperclass().ptoast(Manage_User_Delete_Activity.this.username + "USER IS DELETED SUCCESSFULLY !!", Manage_User_Delete_Activity.this.getApplicationContext());
                            Manage_User_Delete_Activity.this.startActivity(new Intent(Manage_User_Delete_Activity.this, Manage_User_Activity.class));
                            Manage_User_Delete_Activity.this.finish();
                            return;
                        }
                        String string = jSONObject.getString("error_log");
                        Manage_User_Delete_Activity.this.progress_dialog.cancel();
                        Manage_User_Delete_Activity.this.txt_alert.setText(string);
                        Manage_User_Delete_Activity.this.error_dialog.show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                public void onErrorResponse(VolleyError volleyError) {
                    String onErrorResponse = new VolleyErrorHelper().onErrorResponse(volleyError, Manage_User_Delete_Activity.this.getApplicationContext());
                    Manage_User_Delete_Activity.this.progress_dialog.cancel();
                    Manage_User_Delete_Activity.this.txt_alert.setText(onErrorResponse);
                    Manage_User_Delete_Activity.this.error_dialog.show();
                }
            }) {
                /* access modifiers changed from: protected */
                public Map<String, String> getParams() throws AuthFailureError {
                    HashMap hashMap = new HashMap();
                    hashMap.put("uid", Manage_User_Delete_Activity.this.username);
                    hashMap.put("Circle", Manage_User_Delete_Activity.this.user_circle);
                    hashMap.put("SSA", Manage_User_Delete_Activity.this.user_ssa);
                    hashMap.put("username", Manage_User_Delete_Activity.this.Pref_Username);
                    hashMap.put("random_key", Manage_User_Delete_Activity.this.Pref_Randkey);
                    hashMap.put("device_id", Manage_User_Delete_Activity.this.androidId);
                    hashMap.put("comment", upperCase);
                    return hashMap;
                }
            };
            r0.setRetryPolicy(new DefaultRetryPolicy(BZip2Constants.BASEBLOCKSIZE, 1, 1.0f));
            newRequestQueue.add(r0);
        }
    }
}
