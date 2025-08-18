package bsnl.bsnl_teevra;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
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

public class Manage_Password_Activity extends AppCompatActivity implements View.OnClickListener {
    private String Pref_Circle;
    private String Pref_Password;
    /* access modifiers changed from: private */
    public String Pref_Randkey;
    private String Pref_Role;
    private String Pref_SSA;
    /* access modifiers changed from: private */
    public String Pref_Username;
    /* access modifiers changed from: private */
    public String androidId;
    /* access modifiers changed from: private */
    public Button btn_change;
    /* access modifiers changed from: private */
    public SharedPreferences.Editor editor;
    /* access modifiers changed from: private */
    public EditText et_current_pwd;
    /* access modifiers changed from: private */
    public EditText et_new_pwd;
    /* access modifiers changed from: private */
    public ImageView imageView;
    /* access modifiers changed from: private */
    public RelativeLayout relativeLayout;
    /* access modifiers changed from: private */
    public SharedPreferences sharedPreferences;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.manage_password_activity);
        SharedPreferences sharedPreferences2 = getSharedPreferences("myloginpreference", 0);
        this.sharedPreferences = sharedPreferences2;
        this.Pref_Circle = sharedPreferences2.getString("circle_Key", (String) null);
        this.Pref_SSA = this.sharedPreferences.getString("ssa_Key", (String) null);
        this.Pref_Username = this.sharedPreferences.getString("username_Key", (String) null);
        this.Pref_Password = this.sharedPreferences.getString("password_Key", (String) null);
        this.Pref_Randkey = this.sharedPreferences.getString("rand_Key", (String) null);
        this.androidId = Settings.Secure.getString(getContentResolver(), "android_id");
        this.imageView = (ImageView) findViewById(R.id.img_header);
        this.et_current_pwd = (EditText) findViewById(R.id.et_current_pwd);
        this.et_new_pwd = (EditText) findViewById(R.id.et_new_pwd);
        this.btn_change = (Button) findViewById(R.id.btn_change);
        RelativeLayout relativeLayout2 = (RelativeLayout) findViewById(R.id.progressbar_managepassword);
        this.relativeLayout = relativeLayout2;
        relativeLayout2.setVisibility(8);
        this.imageView.setOnClickListener(this);
        this.btn_change.setOnClickListener(this);
    }

    public void onClick(View view) {
        if (view.getId() == R.id.img_header) {
            startActivity(new Intent(this, DashBoard_New.class));
            finish();
        } else if (view.getId() == R.id.btn_change) {
            final String trim = this.et_current_pwd.getText().toString().trim();
            final String trim2 = this.et_new_pwd.getText().toString().trim();
            if (trim.equals("") || trim2.equals("")) {
                new AlertHelperclass().ntoast("ERROR !! ALL FIELDS ARE MANDATORY", getApplicationContext());
            } else if (!trim.equals(this.Pref_Password)) {
                new AlertHelperclass().ntoast("ERROR !! SEEMS LIKE YOU FORGOT YOUR PASSWORD", getApplicationContext());
            } else if (trim2.equals(trim)) {
                new AlertHelperclass().ntoast("ERROR !! NEW PASSWORD AND OLD PASSWORD CAN'T BE SAME", getApplicationContext());
            } else {
                ((InputMethodManager) getSystemService("input_method")).hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 2);
                this.relativeLayout.setVisibility(0);
                this.imageView.setEnabled(false);
                this.et_current_pwd.setEnabled(false);
                this.et_new_pwd.setEnabled(false);
                this.btn_change.setEnabled(false);
                RequestQueue newRequestQueue = Volley.newRequestQueue(this);
                AnonymousClass3 r0 = new StringRequest(1, getString(R.string.serverip) + "manage_password.php", new Response.Listener<String>() {
                    public void onResponse(String str) {
                        Manage_Password_Activity.this.relativeLayout.setVisibility(8);
                        Manage_Password_Activity.this.imageView.setEnabled(true);
                        Manage_Password_Activity.this.et_current_pwd.setEnabled(true);
                        Manage_Password_Activity.this.et_new_pwd.setEnabled(true);
                        Manage_Password_Activity.this.btn_change.setEnabled(true);
                        try {
                            if (new JSONObject(str).getBoolean("pwd_change")) {
                                new AlertHelperclass().ptoast("PASSWORD CHANGED SUCCESSFULLY\nPLEASE LOGIN WITH NEW-PASSWORD", Manage_Password_Activity.this.getApplicationContext());
                                Manage_Password_Activity manage_Password_Activity = Manage_Password_Activity.this;
                                SharedPreferences.Editor unused = manage_Password_Activity.editor = manage_Password_Activity.sharedPreferences.edit();
                                Manage_Password_Activity.this.editor.clear();
                                Manage_Password_Activity.this.editor.commit();
                                Intent intent = new Intent(Manage_Password_Activity.this, Login_Activity.class);
                                intent.addFlags(335577088);
                                Manage_Password_Activity.this.startActivity(intent);
                                return;
                            }
                            new AlertHelperclass().ntoast("PASSWORD CHANGED FAILED", Manage_Password_Activity.this.getApplicationContext());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError volleyError) {
                        Manage_Password_Activity.this.relativeLayout.setVisibility(8);
                        Manage_Password_Activity.this.imageView.setEnabled(true);
                        Manage_Password_Activity.this.et_current_pwd.setEnabled(true);
                        Manage_Password_Activity.this.et_new_pwd.setEnabled(true);
                        Manage_Password_Activity.this.btn_change.setEnabled(true);
                        new AlertDialog.Builder(Manage_Password_Activity.this).setTitle("Error").setIcon(R.drawable.ic_action_error).setCancelable(false).setMessage(new VolleyErrorHelper().onErrorResponse(volleyError, Manage_Password_Activity.this.getApplicationContext())).setNegativeButton("Retry", (DialogInterface.OnClickListener) null).create().show();
                    }
                }) {
                    /* access modifiers changed from: protected */
                    public Map<String, String> getParams() throws AuthFailureError {
                        HashMap hashMap = new HashMap();
                        hashMap.put("new_pwd", trim2);
                        hashMap.put("password", trim);
                        hashMap.put("username", Manage_Password_Activity.this.Pref_Username);
                        hashMap.put("random_key", Manage_Password_Activity.this.Pref_Randkey);
                        hashMap.put("device_id", Manage_Password_Activity.this.androidId);
                        return hashMap;
                    }
                };
                r0.setRetryPolicy(new DefaultRetryPolicy(BZip2Constants.BASEBLOCKSIZE, 1, 1.0f));
                newRequestQueue.add(r0);
            }
        }
    }
}
