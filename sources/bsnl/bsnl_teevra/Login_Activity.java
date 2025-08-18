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
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.messaging.Constants;
import com.google.firebase.messaging.FirebaseMessaging;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class Login_Activity extends AppCompatActivity implements View.OnClickListener {
    private static final String Access_level_login_preference = "access_level_Key";
    private static final String Account_status_login_preference = "Account_status_Key";
    private static final String Circle_login_preference = "circle_Key";
    private static final String Demo_videoID = "DemoVideoID_key";
    private static final String Designation_login_preference = "designation_Key";
    private static final String Detail_bb_privilege_login_preference = "detail_bb_Key";
    private static final String Detail_ftth_privilege_login_preference = "detail_ftth_Key";
    private static final String Detail_tip_privilege_login_preference = "detail_tip_Key";
    private static final String Email_login_preference = "email_Key";
    private static final String FMS_preference = "fms_Key";
    private static final String Fault_Rectify_privilege_login_preference = "fault_rectify_Key";
    private static final String Fms_Role_preference = "fms_role_key";
    private static final String Fms_firm_preference = "fms_firm_Key";
    private static final String Fms_loginid_preference = "fms_username_Key";
    private static final String Fms_teamid_preference = "fms_teamid_key";
    private static final String Fms_userid_preference = "fms_userid_key";
    private static final String Is_Demo_viewed_preference = "isDemoViewed_key";
    private static final String Is_Loggedin_preference = "isloggedin_key";
    private static final String Mobile_login_preference = "mobile_Key";
    private static final String NMS_dslam_privilege_login_preference = "nms_dslam_Key";
    private static final String NMS_olt_privilege_login_preference = "nms_olt_Key";
    private static final String NMS_tpartyolt_privilege_login_preference = "nms_tpartyolt_Key";
    private static final String Name_login_preference = "name_key";
    private static final String Nw_Glance_preference = "nw_glance_Key";
    private static final String PMS_privilege_login_preference = "pms_Key";
    private static final String Password_login_preference = "password_Key";
    private static final String Portverify_privilege_login_preference = "portverify_Key";
    private static final String Rand_login_preference = "rand_Key";
    private static final String Report_login_preference = "report_Key";
    private static final String Role_login_preference = "role_Key";
    private static final String Setting_privilege_login_preference = "setting_Key";
    private static final String Ssa_login_preference = "ssa_Key";
    private static final String Unit_login_preference = "unit_Key";
    private static final String Username_login_preference = "username_Key";
    private static final String mypreference = "myloginpreference";
    /* access modifiers changed from: private */
    public String DemoVideoId;
    /* access modifiers changed from: private */
    public Boolean Is_Demo_Viewed;
    /* access modifiers changed from: private */
    public String Pref_fcmtoken;
    private Button btn_Login;
    private Button btn_Register;
    /* access modifiers changed from: private */
    public CheckBox chk_rememberme;
    /* access modifiers changed from: private */
    public SharedPreferences.Editor editor;
    /* access modifiers changed from: private */
    public AlertDialog error_dialog;
    private EditText et_password;
    private EditText et_username;
    /* access modifiers changed from: private */
    public String fcm_token = "";
    /* access modifiers changed from: private */
    public AlertDialog info_dialog;
    /* access modifiers changed from: private */
    public AlertDialog progress_dialog;
    private SharedPreferences sharedPreferences;
    private TextView tv_forgotpassword;
    /* access modifiers changed from: private */
    public TextView txt_alert;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.login_activity);
        FirebaseApp.initializeApp(this);
        SharedPreferences sharedPreferences2 = getSharedPreferences(mypreference, 0);
        this.sharedPreferences = sharedPreferences2;
        this.Is_Demo_Viewed = Boolean.valueOf(sharedPreferences2.getBoolean(Is_Demo_viewed_preference, false));
        this.DemoVideoId = this.sharedPreferences.getString(Demo_videoID, "");
        String string = this.sharedPreferences.getString("Pref_fcmtoken", (String) null);
        this.Pref_fcmtoken = string;
        if (string == null) {
            this.Pref_fcmtoken = this.fcm_token;
            FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<String>() {
                public void onComplete(Task<String> task) {
                    if (task.isSuccessful()) {
                        String unused = Login_Activity.this.fcm_token = task.getResult();
                        Login_Activity.this.getSharedPreferences(Login_Activity.mypreference, 0).edit().putString("fcmtoken", Login_Activity.this.fcm_token).commit();
                        Login_Activity login_Activity = Login_Activity.this;
                        String unused2 = login_Activity.Pref_fcmtoken = login_Activity.fcm_token;
                    }
                }
            });
        }
        this.et_username = (EditText) findViewById(R.id.et_username);
        this.et_password = (EditText) findViewById(R.id.et_password);
        this.btn_Login = (Button) findViewById(R.id.btn_Login);
        this.btn_Register = (Button) findViewById(R.id.btn_Register);
        this.chk_rememberme = (CheckBox) findViewById(R.id.chk_rememberme);
        TextView textView = (TextView) findViewById(R.id.tv_forgotpassword);
        this.tv_forgotpassword = textView;
        textView.setPaintFlags(8);
        this.btn_Login.setOnClickListener(this);
        this.btn_Register.setOnClickListener(this);
        this.tv_forgotpassword.setOnClickListener(this);
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
    }

    public void onClick(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService("input_method");
        if (view.getId() == R.id.btn_Register) {
            startActivity(new Intent(this, Register_Activity.class));
        } else if (view.getId() == R.id.btn_Login) {
            final String string = Settings.Secure.getString(getContentResolver(), "android_id");
            String str = getString(R.string.serverip) + "login.php";
            final String trim = this.et_username.getText().toString().trim();
            final String trim2 = this.et_password.getText().toString().trim();
            if (trim.equals("") || trim2.equals("")) {
                new AlertHelperclass().ntoast("PLEASE ENTER A VALID USERNAME & PASSWORD", getApplicationContext());
                return;
            }
            this.editor = this.sharedPreferences.edit();
            this.progress_dialog.show();
            RequestQueue newRequestQueue = Volley.newRequestQueue(this);
            AnonymousClass4 r1 = new StringRequest(1, str, new Response.Listener<String>() {
                public void onResponse(String str) {
                    Login_Activity.this.progress_dialog.cancel();
                    try {
                        JSONObject jSONObject = new JSONObject(str);
                        if (!jSONObject.getBoolean("version_check")) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(Login_Activity.this);
                            View inflate = Login_Activity.this.getLayoutInflater().inflate(R.layout.custom_info, (ViewGroup) null);
                            TextView textView = (TextView) inflate.findViewById(R.id.txt_update);
                            textView.setText("GO");
                            builder.setCancelable(false);
                            builder.setView(inflate);
                            AlertDialog unused = Login_Activity.this.info_dialog = builder.create();
                            Login_Activity.this.info_dialog.show();
                            ((TextView) inflate.findViewById(R.id.txt_alert)).setText("NEWER VERSION IS AVAILABLE");
                            ((TextView) inflate.findViewById(R.id.txt_error)).setText(Html.fromHtml("Please Update Your Application With Newer Version <br><b>https://tinyurl.com/teevra-final</b>"));
                            ((TextView) inflate.findViewById(R.id.txt_cancel)).setOnClickListener(new View.OnClickListener() {
                                public void onClick(View view) {
                                    Login_Activity.this.info_dialog.cancel();
                                }
                            });
                            textView.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View view) {
                                    Login_Activity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://tinyurl.com/teevra-final")));
                                    Login_Activity.this.info_dialog.cancel();
                                }
                            });
                        } else if (jSONObject.getBoolean("login_check")) {
                            if (Login_Activity.this.chk_rememberme.isChecked()) {
                                Login_Activity.this.editor.putBoolean(Login_Activity.Is_Loggedin_preference, true);
                            }
                            Login_Activity.this.editor.putString(Login_Activity.Username_login_preference, trim);
                            Login_Activity.this.editor.putString(Login_Activity.Password_login_preference, trim2);
                            Login_Activity.this.editor.putString(Login_Activity.Rand_login_preference, jSONObject.getString("key_value").trim());
                            Login_Activity.this.editor.putString(Login_Activity.Name_login_preference, jSONObject.getString("name").trim());
                            Login_Activity.this.editor.putString(Login_Activity.Mobile_login_preference, jSONObject.getString("mobile").trim());
                            Login_Activity.this.editor.putString(Login_Activity.Email_login_preference, jSONObject.getString("email").trim());
                            Login_Activity.this.editor.putString(Login_Activity.Designation_login_preference, jSONObject.getString("designation").trim());
                            Login_Activity.this.editor.putString(Login_Activity.Unit_login_preference, jSONObject.getString("unit").trim());
                            Login_Activity.this.editor.putString(Login_Activity.Circle_login_preference, jSONObject.getString("circle").trim());
                            Login_Activity.this.editor.putString(Login_Activity.Ssa_login_preference, jSONObject.getString("ssa").trim());
                            Login_Activity.this.editor.putString(Login_Activity.Role_login_preference, jSONObject.getString("role").trim());
                            Login_Activity.this.editor.putString(Login_Activity.Access_level_login_preference, jSONObject.getString("access_level").trim());
                            Login_Activity.this.editor.putBoolean(Login_Activity.Detail_bb_privilege_login_preference, jSONObject.getBoolean("detail_bb"));
                            Login_Activity.this.editor.putBoolean(Login_Activity.Detail_ftth_privilege_login_preference, jSONObject.getBoolean("detail_ftth"));
                            Login_Activity.this.editor.putBoolean(Login_Activity.Detail_tip_privilege_login_preference, jSONObject.getBoolean("detail_tip"));
                            Login_Activity.this.editor.putBoolean(Login_Activity.FMS_preference, jSONObject.getBoolean("fms"));
                            Login_Activity.this.editor.putBoolean(Login_Activity.Fault_Rectify_privilege_login_preference, jSONObject.getBoolean("rectify_bb"));
                            Login_Activity.this.editor.putBoolean(Login_Activity.NMS_dslam_privilege_login_preference, jSONObject.getBoolean("nms_dslam"));
                            Login_Activity.this.editor.putBoolean(Login_Activity.NMS_olt_privilege_login_preference, jSONObject.getBoolean("nms_olt"));
                            Login_Activity.this.editor.putBoolean(Login_Activity.NMS_tpartyolt_privilege_login_preference, jSONObject.getBoolean("nms_tpartyolt"));
                            Login_Activity.this.editor.putBoolean(Login_Activity.Portverify_privilege_login_preference, jSONObject.getBoolean("portverify"));
                            Login_Activity.this.editor.putBoolean(Login_Activity.PMS_privilege_login_preference, jSONObject.getBoolean("pms"));
                            Login_Activity.this.editor.putBoolean(Login_Activity.Nw_Glance_preference, jSONObject.getBoolean("nw_glance"));
                            Login_Activity.this.editor.putBoolean(Login_Activity.Report_login_preference, jSONObject.getBoolean("report"));
                            Login_Activity.this.editor.putBoolean(Login_Activity.Account_status_login_preference, jSONObject.getBoolean("account_status"));
                            Login_Activity.this.editor.putBoolean(Login_Activity.Setting_privilege_login_preference, jSONObject.getBoolean("settings"));
                            Login_Activity.this.editor.putBoolean(Login_Activity.FMS_preference, jSONObject.getBoolean("fms"));
                            Login_Activity.this.editor.putString(Login_Activity.Fms_loginid_preference, jSONObject.getString("FMS_USERNAME"));
                            Login_Activity.this.editor.putString(Login_Activity.Fms_firm_preference, jSONObject.getString("FMS_FIRM"));
                            Login_Activity.this.editor.putString(Login_Activity.Fms_teamid_preference, jSONObject.getString("FMS_TEAMID"));
                            Login_Activity.this.editor.putString(Login_Activity.Fms_userid_preference, jSONObject.getString("FMS_USERID"));
                            Login_Activity.this.editor.putString(Login_Activity.Fms_Role_preference, jSONObject.getString("FMS_ROLE").trim());
                            Login_Activity.this.editor.commit();
                            new AlertHelperclass().ptoast("LoggedIn Successfully", Login_Activity.this.getApplicationContext());
                            if (!Login_Activity.this.Is_Demo_Viewed.booleanValue() || !Login_Activity.this.DemoVideoId.equals(Login_Activity.this.getString(R.string.Demo_VideoID))) {
                                Login_Activity.this.startActivity(new Intent(Login_Activity.this, Whats_New_Activity.class));
                                Login_Activity.this.finish();
                                return;
                            }
                            Login_Activity.this.startActivity(new Intent(Login_Activity.this, DashBoard_New.class));
                            Login_Activity.this.finish();
                        } else {
                            new AlertHelperclass().ntoast(jSONObject.getString("errorlog"), Login_Activity.this.getApplicationContext());
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                public void onErrorResponse(VolleyError volleyError) {
                    String onErrorResponse = new VolleyErrorHelper().onErrorResponse(volleyError, Login_Activity.this.getApplicationContext());
                    Login_Activity.this.progress_dialog.cancel();
                    Login_Activity.this.txt_alert.setText(onErrorResponse);
                    Login_Activity.this.error_dialog.show();
                }
            }) {
                /* access modifiers changed from: protected */
                public Map<String, String> getParams() throws AuthFailureError {
                    HashMap hashMap = new HashMap();
                    hashMap.put("username", trim);
                    hashMap.put("password", trim2);
                    hashMap.put("deviceid", string);
                    hashMap.put("name", BuildConfig.VERSION_NAME);
                    hashMap.put("code", String.valueOf(BuildConfig.VERSION_CODE));
                    hashMap.put(Constants.ScionAnalytics.ORIGIN_FCM, Login_Activity.this.Pref_fcmtoken);
                    return hashMap;
                }
            };
            r1.setRetryPolicy(new DefaultRetryPolicy(BZip2Constants.BASEBLOCKSIZE, 1, 1.0f));
            newRequestQueue.add(r1);
        } else if (view.getId() == R.id.tv_forgotpassword) {
            startActivity(new Intent(this, Password_Reset_Activity.class));
        }
    }
}
