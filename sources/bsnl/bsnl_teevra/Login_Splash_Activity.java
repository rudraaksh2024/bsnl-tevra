package bsnl.bsnl_teevra;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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

public class Login_Splash_Activity extends AppCompatActivity {
    private static final String Access_level_login_preference = "access_level_Key";
    private static final String Account_status_login_preference = "Account_status_Key";
    private static final String Circle_login_preference = "circle_Key";
    /* access modifiers changed from: private */
    public static String DemoVideoId = null;
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
    /* access modifiers changed from: private */
    public static Boolean Is_Demo_Viewed = null;
    /* access modifiers changed from: private */
    public static Boolean Is_Logged_In = null;
    private static final String Mobile_login_preference = "mobile_Key";
    private static final String NMS_dslam_privilege_login_preference = "nms_dslam_Key";
    private static final String NMS_olt_privilege_login_preference = "nms_olt_Key";
    private static final String NMS_tpartyolt_privilege_login_preference = "nms_tpartyolt_Key";
    private static final String Name_login_preference = "name_key";
    private static final String Nw_Glance_preference = "nw_glance_Key";
    private static final String PMS_privilege_login_preference = "pms_Key";
    private static final String Portverify_privilege_login_preference = "portverify_Key";
    /* access modifiers changed from: private */
    public static String Pref_Password = null;
    /* access modifiers changed from: private */
    public static String Pref_Username = null;
    /* access modifiers changed from: private */
    public static String Pref_fcmtoken = null;
    private static final String Rand_login_preference = "rand_Key";
    private static final String Report_login_preference = "report_Key";
    private static final String Role_login_preference = "role_Key";
    private static final String Setting_privilege_login_preference = "setting_Key";
    private static final String Ssa_login_preference = "ssa_Key";
    private static final String Unit_login_preference = "unit_Key";
    /* access modifiers changed from: private */
    public static String fcm_token = "";
    private static final String mypreference = "myloginpreference";
    private static final int splash_timeout = 4000;
    /* access modifiers changed from: private */
    public SharedPreferences.Editor editor;
    private AlertDialog error_dialog;
    /* access modifiers changed from: private */
    public AlertDialog info_dialog;
    /* access modifiers changed from: private */
    public RelativeLayout progressBar;
    private AlertDialog progress_dialog;
    /* access modifiers changed from: private */
    public SharedPreferences sharedPreferences;
    private ImageView splashimage;
    private LinearLayout splashlayout;
    private TextView splashtext;
    private TextView txt_alert;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.login_splash_activity);
        FirebaseApp.initializeApp(this);
        SharedPreferences sharedPreferences2 = getSharedPreferences(mypreference, 0);
        this.sharedPreferences = sharedPreferences2;
        Is_Logged_In = Boolean.valueOf(sharedPreferences2.getBoolean("isloggedin_key", false));
        Is_Demo_Viewed = Boolean.valueOf(this.sharedPreferences.getBoolean("isDemoViewed_key", false));
        DemoVideoId = this.sharedPreferences.getString("DemoVideoID_key", "");
        Pref_Username = this.sharedPreferences.getString("username_Key", (String) null);
        Pref_Password = this.sharedPreferences.getString("password_Key", (String) null);
        String string = this.sharedPreferences.getString("Pref_fcmtoken", (String) null);
        Pref_fcmtoken = string;
        if (string == null) {
            Pref_fcmtoken = fcm_token;
            FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<String>() {
                public void onComplete(Task<String> task) {
                    if (task.isSuccessful()) {
                        String unused = Login_Splash_Activity.fcm_token = task.getResult();
                        Login_Splash_Activity.this.getSharedPreferences(Login_Splash_Activity.mypreference, 0).edit().putString("Pref_fcmtoken", Login_Splash_Activity.fcm_token).commit();
                        String unused2 = Login_Splash_Activity.Pref_fcmtoken = Login_Splash_Activity.fcm_token;
                    }
                }
            });
        }
        this.splashlayout = (LinearLayout) findViewById(R.id.lay_splash);
        Animation loadAnimation = AnimationUtils.loadAnimation(this, R.anim.splashanimation);
        loadAnimation.reset();
        this.splashlayout.clearAnimation();
        this.splashlayout.startAnimation(loadAnimation);
        this.splashimage = (ImageView) findViewById(R.id.img_splash);
        this.splashtext = (TextView) findViewById(R.id.txt_splash);
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.progressbar_splash);
        this.progressBar = relativeLayout;
        relativeLayout.setVisibility(4);
        Animation loadAnimation2 = AnimationUtils.loadAnimation(this, R.anim.splashtransition);
        loadAnimation2.reset();
        this.splashimage.clearAnimation();
        this.splashtext.clearAnimation();
        this.splashimage.startAnimation(loadAnimation2);
        this.splashtext.startAnimation(loadAnimation2);
        final String str = getString(R.string.serverip) + "login.php";
        final String string2 = Settings.Secure.getString(getContentResolver(), "android_id");
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
        new Handler().postDelayed(new Runnable() {
            public void run() {
                if (Login_Splash_Activity.Is_Logged_In.booleanValue()) {
                    Login_Splash_Activity.this.progressBar.setVisibility(0);
                    RequestQueue newRequestQueue = Volley.newRequestQueue(Login_Splash_Activity.this);
                    AnonymousClass3 r1 = new StringRequest(1, str, new Response.Listener<String>() {
                        public void onResponse(String str) {
                            Login_Splash_Activity.this.progressBar.setVisibility(4);
                            try {
                                JSONObject jSONObject = new JSONObject(str);
                                if (!jSONObject.getBoolean("version_check")) {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(Login_Splash_Activity.this);
                                    View inflate = Login_Splash_Activity.this.getLayoutInflater().inflate(R.layout.custom_info, (ViewGroup) null);
                                    TextView textView = (TextView) inflate.findViewById(R.id.txt_update);
                                    textView.setText("GO");
                                    builder.setCancelable(false);
                                    builder.setView(inflate);
                                    AlertDialog unused = Login_Splash_Activity.this.info_dialog = builder.create();
                                    Login_Splash_Activity.this.info_dialog.show();
                                    ((TextView) inflate.findViewById(R.id.txt_alert)).setText("NEWER VERSION IS AVAILABLE");
                                    ((TextView) inflate.findViewById(R.id.txt_error)).setText(Html.fromHtml("Please Update Your Application With Newer Version <br><b>https://tinyurl.com/teevra-final</b>"));
                                    ((TextView) inflate.findViewById(R.id.txt_cancel)).setOnClickListener(new View.OnClickListener() {
                                        public void onClick(View view) {
                                            Login_Splash_Activity.this.info_dialog.cancel();
                                        }
                                    });
                                    textView.setOnClickListener(new View.OnClickListener() {
                                        public void onClick(View view) {
                                            Login_Splash_Activity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://tinyurl.com/teevra-final")));
                                            Login_Splash_Activity.this.info_dialog.cancel();
                                        }
                                    });
                                } else if (jSONObject.getBoolean("login_check")) {
                                    SharedPreferences.Editor unused2 = Login_Splash_Activity.this.editor = Login_Splash_Activity.this.sharedPreferences.edit();
                                    Login_Splash_Activity.this.editor.putString(Login_Splash_Activity.Rand_login_preference, jSONObject.getString("key_value").trim());
                                    Login_Splash_Activity.this.editor.putString(Login_Splash_Activity.Name_login_preference, jSONObject.getString("name").trim());
                                    Login_Splash_Activity.this.editor.putString(Login_Splash_Activity.Mobile_login_preference, jSONObject.getString("mobile").trim());
                                    Login_Splash_Activity.this.editor.putString(Login_Splash_Activity.Email_login_preference, jSONObject.getString("email").trim());
                                    Login_Splash_Activity.this.editor.putString(Login_Splash_Activity.Designation_login_preference, jSONObject.getString("designation").trim());
                                    Login_Splash_Activity.this.editor.putString(Login_Splash_Activity.Unit_login_preference, jSONObject.getString("unit").trim());
                                    Login_Splash_Activity.this.editor.putString(Login_Splash_Activity.Circle_login_preference, jSONObject.getString("circle").trim());
                                    Login_Splash_Activity.this.editor.putString(Login_Splash_Activity.Ssa_login_preference, jSONObject.getString("ssa").trim());
                                    Login_Splash_Activity.this.editor.putString(Login_Splash_Activity.Role_login_preference, jSONObject.getString("role").trim());
                                    Login_Splash_Activity.this.editor.putString(Login_Splash_Activity.Access_level_login_preference, jSONObject.getString("access_level").trim());
                                    Login_Splash_Activity.this.editor.putBoolean(Login_Splash_Activity.Detail_bb_privilege_login_preference, jSONObject.getBoolean("detail_bb"));
                                    Login_Splash_Activity.this.editor.putBoolean(Login_Splash_Activity.Detail_ftth_privilege_login_preference, jSONObject.getBoolean("detail_ftth"));
                                    Login_Splash_Activity.this.editor.putBoolean(Login_Splash_Activity.Detail_tip_privilege_login_preference, jSONObject.getBoolean("detail_tip"));
                                    Login_Splash_Activity.this.editor.putBoolean(Login_Splash_Activity.Fault_Rectify_privilege_login_preference, jSONObject.getBoolean("rectify_bb"));
                                    Login_Splash_Activity.this.editor.putBoolean(Login_Splash_Activity.FMS_preference, jSONObject.getBoolean("fms"));
                                    Login_Splash_Activity.this.editor.putBoolean(Login_Splash_Activity.NMS_dslam_privilege_login_preference, jSONObject.getBoolean("nms_dslam"));
                                    Login_Splash_Activity.this.editor.putBoolean(Login_Splash_Activity.NMS_olt_privilege_login_preference, jSONObject.getBoolean("nms_olt"));
                                    Login_Splash_Activity.this.editor.putBoolean(Login_Splash_Activity.NMS_tpartyolt_privilege_login_preference, jSONObject.getBoolean("nms_tpartyolt"));
                                    Login_Splash_Activity.this.editor.putBoolean(Login_Splash_Activity.Portverify_privilege_login_preference, jSONObject.getBoolean("portverify"));
                                    Login_Splash_Activity.this.editor.putBoolean(Login_Splash_Activity.PMS_privilege_login_preference, jSONObject.getBoolean("pms"));
                                    Login_Splash_Activity.this.editor.putBoolean(Login_Splash_Activity.Nw_Glance_preference, jSONObject.getBoolean("nw_glance"));
                                    Login_Splash_Activity.this.editor.putBoolean(Login_Splash_Activity.Report_login_preference, jSONObject.getBoolean("report"));
                                    Login_Splash_Activity.this.editor.putBoolean(Login_Splash_Activity.Account_status_login_preference, jSONObject.getBoolean("account_status"));
                                    Login_Splash_Activity.this.editor.putBoolean(Login_Splash_Activity.Setting_privilege_login_preference, jSONObject.getBoolean("settings"));
                                    Login_Splash_Activity.this.editor.putString(Login_Splash_Activity.Fms_loginid_preference, jSONObject.getString("FMS_USERNAME"));
                                    Login_Splash_Activity.this.editor.putString(Login_Splash_Activity.Fms_firm_preference, jSONObject.getString("FMS_FIRM"));
                                    Login_Splash_Activity.this.editor.putString(Login_Splash_Activity.Fms_teamid_preference, jSONObject.getString("FMS_TEAMID"));
                                    Login_Splash_Activity.this.editor.putString(Login_Splash_Activity.Fms_userid_preference, jSONObject.getString("FMS_USERID"));
                                    Login_Splash_Activity.this.editor.putString(Login_Splash_Activity.Fms_Role_preference, jSONObject.getString("FMS_ROLE").trim());
                                    Login_Splash_Activity.this.editor.commit();
                                    new AlertHelperclass().ptoast("LoggedIn Successfully", Login_Splash_Activity.this.getApplicationContext());
                                    if (!Login_Splash_Activity.Is_Demo_Viewed.booleanValue() || !Login_Splash_Activity.DemoVideoId.equals(Login_Splash_Activity.this.getString(R.string.Demo_VideoID))) {
                                        Login_Splash_Activity.this.startActivity(new Intent(Login_Splash_Activity.this, Whats_New_Activity.class));
                                        Login_Splash_Activity.this.finish();
                                        return;
                                    }
                                    Login_Splash_Activity.this.startActivity(new Intent(Login_Splash_Activity.this, DashBoard_New.class));
                                    Login_Splash_Activity.this.finish();
                                } else {
                                    new AlertHelperclass().ntoast(jSONObject.getString("errorlog"), Login_Splash_Activity.this.getApplicationContext());
                                    Login_Splash_Activity.this.startActivity(new Intent(Login_Splash_Activity.this, Login_Activity.class));
                                    Login_Splash_Activity.this.finish();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                        public void onErrorResponse(VolleyError volleyError) {
                            Login_Splash_Activity.this.progressBar.setVisibility(4);
                            new AlertDialog.Builder(Login_Splash_Activity.this).setMessage(new VolleyErrorHelper().onErrorResponse(volleyError, Login_Splash_Activity.this.getApplicationContext())).setTitle("ERROR!!").setIcon(R.drawable.ic_action_error).setCancelable(false).setNegativeButton("Retry", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Login_Splash_Activity.this.finish();
                                    System.exit(0);
                                }
                            }).create().show();
                        }
                    }) {
                        /* access modifiers changed from: protected */
                        public Map<String, String> getParams() throws AuthFailureError {
                            HashMap hashMap = new HashMap();
                            hashMap.put("username", Login_Splash_Activity.Pref_Username);
                            hashMap.put("password", Login_Splash_Activity.Pref_Password);
                            hashMap.put("deviceid", string2);
                            hashMap.put("name", BuildConfig.VERSION_NAME);
                            hashMap.put("code", String.valueOf(BuildConfig.VERSION_CODE));
                            hashMap.put(Constants.ScionAnalytics.ORIGIN_FCM, Login_Splash_Activity.Pref_fcmtoken);
                            return hashMap;
                        }
                    };
                    r1.setRetryPolicy(new DefaultRetryPolicy(BZip2Constants.BASEBLOCKSIZE, 1, 1.0f));
                    newRequestQueue.add(r1);
                    return;
                }
                Login_Splash_Activity.this.startActivity(new Intent(Login_Splash_Activity.this, Login_Activity.class));
                Login_Splash_Activity.this.finish();
            }
        }, 4000);
    }
}
