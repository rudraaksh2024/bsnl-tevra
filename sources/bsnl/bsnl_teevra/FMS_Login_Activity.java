package bsnl.bsnl_teevra;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
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

public class FMS_Login_Activity extends AppCompatActivity implements View.OnClickListener {
    private static final String FMS_Password_login_preference = "fms_password_Key";
    private static final String FMS_User_Circle_preference = "fms_user_circle_Key";
    private static final String FMS_User_SSA_preference = "fms_user_ssa_Key";
    private static final String FMS_User_Zone_preference = "fms_user_zone_Key";
    private static final String FMS_User_team_preference = "fms_user_team_Key";
    private static final String FMS_Username_login_preference = "fms_username_Key";
    private static final String Is_FMS_Loggedin_preference = "isfmsloggedin_key";
    private static final String mypreference = "myloginpreference";
    /* access modifiers changed from: private */
    public String Pref_Circle;
    private String Pref_Designation;
    private String Pref_Fms_Password;
    private String Pref_Fms_Username;
    /* access modifiers changed from: private */
    public String Pref_Randkey;
    /* access modifiers changed from: private */
    public String Pref_SSA;
    /* access modifiers changed from: private */
    public String Pref_Username;
    /* access modifiers changed from: private */
    public String androidId;
    private Button btn_fms_Login;
    /* access modifiers changed from: private */
    public CheckBox chk_fms_rememberme;
    /* access modifiers changed from: private */
    public SharedPreferences.Editor editor;
    /* access modifiers changed from: private */
    public AlertDialog error_dialog;
    private EditText et_fms_password;
    private EditText et_fms_username;
    private ImageView imageView;
    /* access modifiers changed from: private */
    public AlertDialog info_dialog;
    private LinearLayout lay_login;
    /* access modifiers changed from: private */
    public LinearLayout lay_progressbar;
    /* access modifiers changed from: private */
    public AlertDialog progress_dialog;
    /* access modifiers changed from: private */
    public SharedPreferences sharedPreferences;
    private TextView tv_fms_forgotpassword;
    /* access modifiers changed from: private */
    public TextView txt_alert;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.fms_login_activity);
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
        getString(R.string.serverip) + "fms/fms_login.php";
        ImageView imageView2 = (ImageView) findViewById(R.id.img_header);
        this.imageView = imageView2;
        imageView2.setOnClickListener(this);
        this.et_fms_username = (EditText) findViewById(R.id.et_fms_username);
        this.et_fms_password = (EditText) findViewById(R.id.et_fms_password);
        this.btn_fms_Login = (Button) findViewById(R.id.btn_fms_Login);
        this.chk_fms_rememberme = (CheckBox) findViewById(R.id.chk_fms_rememberme);
        TextView textView = (TextView) findViewById(R.id.tv_fms_forgotpassword);
        this.tv_fms_forgotpassword = textView;
        textView.setPaintFlags(8);
        this.lay_login = (LinearLayout) findViewById(R.id.lay_fms_login);
        this.lay_progressbar = (LinearLayout) findViewById(R.id.progressbar_fms_login);
        this.btn_fms_Login.setOnClickListener(this);
        this.tv_fms_forgotpassword.setOnClickListener(this);
        SharedPreferences sharedPreferences2 = getSharedPreferences(mypreference, 0);
        this.sharedPreferences = sharedPreferences2;
        Boolean.valueOf(sharedPreferences2.getBoolean(Is_FMS_Loggedin_preference, false));
        this.Pref_Fms_Username = this.sharedPreferences.getString(FMS_Username_login_preference, (String) null);
        this.Pref_Fms_Password = this.sharedPreferences.getString(FMS_Password_login_preference, (String) null);
        this.Pref_Username = this.sharedPreferences.getString("username_Key", (String) null);
        this.Pref_Circle = this.sharedPreferences.getString("circle_Key", (String) null);
        this.Pref_SSA = this.sharedPreferences.getString("ssa_Key", (String) null);
        this.Pref_Randkey = this.sharedPreferences.getString("rand_Key", (String) null);
        this.Pref_Designation = this.sharedPreferences.getString("designation_Key", (String) null);
        this.androidId = Settings.Secure.getString(getContentResolver(), "android_id");
        if (this.Pref_Designation.equals("FRANCHISEE OWNER")) {
            this.et_fms_username.setText(this.Pref_Fms_Username);
            this.et_fms_password.setText(this.Pref_Fms_Password);
            this.et_fms_username.setEnabled(false);
        } else if (this.Pref_Designation.equals("FRANCHISEE MEMBER")) {
            this.et_fms_username.setText(this.Pref_Fms_Username);
            this.et_fms_password.setText(this.Pref_Fms_Password);
            this.et_fms_username.setEnabled(false);
        } else {
            this.et_fms_username.setText(this.Pref_Fms_Username);
            this.et_fms_password.setText(this.Pref_Fms_Password);
        }
    }

    public void onClick(View view) {
        ((InputMethodManager) getSystemService("input_method")).hideSoftInputFromWindow(getWindow().getDecorView().getRootView().getWindowToken(), 0);
        if (view.getId() == R.id.img_header) {
            Intent intent = new Intent(this, DashBoard_New.class);
            intent.setFlags(67108864);
            startActivity(intent);
            finish();
        } else if (view.getId() == R.id.tv_fms_forgotpassword) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            View inflate = getLayoutInflater().inflate(R.layout.custom_info, (ViewGroup) null);
            TextView textView = (TextView) inflate.findViewById(R.id.txt_update);
            textView.setText("GO");
            builder.setCancelable(false);
            builder.setView(inflate);
            AlertDialog create = builder.create();
            this.info_dialog = create;
            create.show();
            ((TextView) inflate.findViewById(R.id.txt_alert)).setText("INFO");
            ((TextView) inflate.findViewById(R.id.txt_error)).setText(Html.fromHtml("Please Reset Your FMS Password From <br><b>https://fms.bsnl.in</b>"));
            ((TextView) inflate.findViewById(R.id.txt_cancel)).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    FMS_Login_Activity.this.info_dialog.cancel();
                }
            });
            textView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    FMS_Login_Activity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://fms.bsnl.in")));
                    FMS_Login_Activity.this.info_dialog.cancel();
                }
            });
        } else if (view.getId() == R.id.btn_fms_Login) {
            final String trim = this.et_fms_username.getText().toString().trim();
            final String trim2 = this.et_fms_password.getText().toString().trim();
            if (trim.equals("") || trim2.equals("")) {
                new AlertHelperclass().ntoast("PLEASE ENTER A VALID FMS USERNAME & PASSWORD", getApplicationContext());
                return;
            }
            this.progress_dialog.show();
            RequestQueue newRequestQueue = Volley.newRequestQueue(this);
            AnonymousClass5 r0 = new StringRequest(1, getString(R.string.serverip) + "fms/fms_login.php", new Response.Listener<String>() {
                public void onResponse(String str) {
                    FMS_Login_Activity.this.lay_progressbar.setVisibility(8);
                    FMS_Login_Activity.this.progress_dialog.cancel();
                    try {
                        JSONObject jSONObject = new JSONObject(str);
                        if (jSONObject.getString("STATUS").equals("SUCCESS")) {
                            FMS_Login_Activity fMS_Login_Activity = FMS_Login_Activity.this;
                            SharedPreferences.Editor unused = fMS_Login_Activity.editor = fMS_Login_Activity.sharedPreferences.edit();
                            if (FMS_Login_Activity.this.chk_fms_rememberme.isChecked()) {
                                FMS_Login_Activity.this.editor.putBoolean(FMS_Login_Activity.Is_FMS_Loggedin_preference, true);
                                FMS_Login_Activity.this.editor.commit();
                            }
                            FMS_Login_Activity.this.editor.putString(FMS_Login_Activity.FMS_Username_login_preference, trim);
                            FMS_Login_Activity.this.editor.putString(FMS_Login_Activity.FMS_Password_login_preference, trim2);
                            FMS_Login_Activity.this.editor.commit();
                            String string = jSONObject.getString("PLATFORM");
                            String string2 = jSONObject.getString("USER_TYPE");
                            if (string2.equals("FRANCHISEE")) {
                                FMS_Login_Activity.this.editor.putString(FMS_Login_Activity.FMS_User_Zone_preference, jSONObject.getJSONObject("ROWSET").getString("ZONE"));
                                FMS_Login_Activity.this.editor.commit();
                                new AlertHelperclass().ptoast("FMS LOGIN SUCCESS", FMS_Login_Activity.this.getApplicationContext());
                                Intent intent = new Intent(FMS_Login_Activity.this, FMS_DashBoard_Activity.class);
                                intent.putExtra("platform", string);
                                FMS_Login_Activity.this.startActivity(intent);
                                FMS_Login_Activity.this.finish();
                            } else if (string2.equals("BBC")) {
                                jSONObject.getJSONObject("ROWSET");
                                new AlertHelperclass().ptoast("FMS LOGIN SUCCESS", FMS_Login_Activity.this.getApplicationContext());
                                Intent intent2 = new Intent(FMS_Login_Activity.this, FMS_BBC_Dashboard_Activity.class);
                                intent2.putExtra("platform", string);
                                FMS_Login_Activity.this.startActivity(intent2);
                                FMS_Login_Activity.this.finish();
                            } else if (string2.equals("FRANCHISEE-MANAGER")) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(FMS_Login_Activity.this);
                                View inflate = FMS_Login_Activity.this.getLayoutInflater().inflate(R.layout.custom_info, (ViewGroup) null);
                                TextView textView = (TextView) inflate.findViewById(R.id.txt_update);
                                textView.setText("Ok");
                                ((TextView) inflate.findViewById(R.id.txt_cancel)).setVisibility(4);
                                builder.setCancelable(false);
                                builder.setView(inflate);
                                AlertDialog unused2 = FMS_Login_Activity.this.info_dialog = builder.create();
                                FMS_Login_Activity.this.info_dialog.show();
                                ((TextView) inflate.findViewById(R.id.txt_alert)).setText("INFO");
                                ((TextView) inflate.findViewById(R.id.txt_error)).setText("Sorry !! The Module Is Not Available");
                                textView.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View view) {
                                        FMS_Login_Activity.this.info_dialog.cancel();
                                    }
                                });
                            }
                        } else {
                            FMS_Login_Activity.this.txt_alert.setText("FMS LOGIN FAILED!!\nPlease SIGN-IN With Correct FMS Username And Password");
                            FMS_Login_Activity.this.error_dialog.show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                public void onErrorResponse(VolleyError volleyError) {
                    FMS_Login_Activity.this.progress_dialog.cancel();
                    FMS_Login_Activity.this.txt_alert.setText(new VolleyErrorHelper().onErrorResponse(volleyError, FMS_Login_Activity.this.getApplicationContext()));
                    FMS_Login_Activity.this.error_dialog.show();
                }
            }) {
                /* access modifiers changed from: protected */
                public Map<String, String> getParams() throws AuthFailureError {
                    HashMap hashMap = new HashMap();
                    hashMap.put("fms_userid", trim);
                    hashMap.put("fms_password", trim2);
                    hashMap.put("circle", FMS_Login_Activity.this.Pref_Circle);
                    hashMap.put("ssa", FMS_Login_Activity.this.Pref_SSA);
                    hashMap.put("username", FMS_Login_Activity.this.Pref_Username);
                    hashMap.put("random_key", FMS_Login_Activity.this.Pref_Randkey);
                    hashMap.put("device_id", FMS_Login_Activity.this.androidId);
                    return hashMap;
                }
            };
            r0.setRetryPolicy(new DefaultRetryPolicy(BZip2Constants.BASEBLOCKSIZE, 1, 1.0f));
            newRequestQueue.add(r0);
        }
    }
}
