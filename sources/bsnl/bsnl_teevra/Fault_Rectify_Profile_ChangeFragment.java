package bsnl.bsnl_teevra;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.common.Scopes;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class Fault_Rectify_Profile_ChangeFragment extends Fragment implements View.OnClickListener {
    /* access modifiers changed from: private */
    public String Dslam_ip;
    /* access modifiers changed from: private */
    public String Port;
    /* access modifiers changed from: private */
    public String Pref_Access_Level;
    /* access modifiers changed from: private */
    public String Pref_Circle;
    /* access modifiers changed from: private */
    public String Pref_Randkey;
    /* access modifiers changed from: private */
    public String Pref_SSA;
    /* access modifiers changed from: private */
    public String Pref_Username;
    /* access modifiers changed from: private */
    public String androidId;
    private TextView btn_get_user_detail;
    /* access modifiers changed from: private */
    public TextView btn_profile_change;
    /* access modifiers changed from: private */
    public AlertDialog error_dialog;
    private EditText et_profile_change;
    private ImageView imageView;
    /* access modifiers changed from: private */
    public LinearLayout lay_profile_change;
    /* access modifiers changed from: private */
    public AlertDialog progress_dialog;
    private SharedPreferences sharedPreferences;
    private Spinner spinner_profie;
    /* access modifiers changed from: private */
    public TextView txt_alert;
    /* access modifiers changed from: private */
    public TextView txt_profile_change_bandwidth;
    /* access modifiers changed from: private */
    public TextView txt_profile_change_planname;
    /* access modifiers changed from: private */
    public TextView txt_profile_change_port;
    /* access modifiers changed from: private */
    public TextView txt_profile_change_telephone;
    /* access modifiers changed from: private */
    public TextView txt_profile_change_userid;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fault_rectify_profile_change_fragment, viewGroup, false);
        SharedPreferences sharedPreferences2 = getActivity().getSharedPreferences("myloginpreference", 0);
        this.sharedPreferences = sharedPreferences2;
        this.Pref_Access_Level = sharedPreferences2.getString("access_level_Key", (String) null);
        this.Pref_Username = this.sharedPreferences.getString("username_Key", (String) null);
        this.Pref_Randkey = this.sharedPreferences.getString("rand_Key", (String) null);
        this.Pref_Circle = this.sharedPreferences.getString("circle_Key", (String) null);
        this.Pref_SSA = this.sharedPreferences.getString("ssa_Key", (String) null);
        this.androidId = Settings.Secure.getString(getActivity().getContentResolver(), "android_id");
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View inflate2 = getLayoutInflater().inflate(R.layout.custom_progress, (ViewGroup) null);
        builder.setCancelable(false);
        builder.setView(inflate2);
        this.progress_dialog = builder.create();
        AlertDialog.Builder builder2 = new AlertDialog.Builder(getActivity());
        View inflate3 = getLayoutInflater().inflate(R.layout.custom_error, (ViewGroup) null);
        this.txt_alert = (TextView) inflate3.findViewById(R.id.txt_error);
        builder2.setCancelable(false);
        builder2.setNegativeButton("Retry", (DialogInterface.OnClickListener) null);
        builder2.setView(inflate3);
        this.error_dialog = builder2.create();
        this.imageView = (ImageView) getActivity().findViewById(R.id.img_header);
        this.et_profile_change = (EditText) inflate.findViewById(R.id.et_profile_change);
        this.btn_get_user_detail = (TextView) inflate.findViewById(R.id.btn_get_user_detail);
        this.lay_profile_change = (LinearLayout) inflate.findViewById(R.id.lay_profile_change);
        this.txt_profile_change_telephone = (TextView) inflate.findViewById(R.id.txt_profile_change_telephone);
        this.txt_profile_change_userid = (TextView) inflate.findViewById(R.id.txt_profile_change_userid);
        this.txt_profile_change_port = (TextView) inflate.findViewById(R.id.txt_profile_change_port);
        this.txt_profile_change_planname = (TextView) inflate.findViewById(R.id.txt_profile_change_planname);
        this.txt_profile_change_bandwidth = (TextView) inflate.findViewById(R.id.txt_profile_change_bandwidth);
        this.btn_profile_change = (TextView) inflate.findViewById(R.id.btn_profile_change);
        Spinner spinner = (Spinner) inflate.findViewById(R.id.spinner_profie);
        this.spinner_profie = spinner;
        spinner.setAdapter(new ArrayAdapter(getContext(), R.layout.spinner_item, new String[]{"-- SELECT-PROFILE --", "STD_2MB_PROFILE.PRF", "STD_4MB_PROFILE.PRF", "STD_6MB_PROFILE.PRF", "STD_8MB_PROFILE.PRF", "STD_10MB_PROFILE.PRF", "STD_12MB_PROFILE.PRF", "STD_14MB_PROFILE.PRF", "STD_16MB_PROFILE.PRF", "STD_18MB_PROFILE.PRF", "STD_20MB_PROFILE.PRF", "STD_22MB_PROFILE.PRF", "STD_24MB_PROFILE.PRF"}));
        this.btn_get_user_detail.setOnClickListener(this);
        this.btn_profile_change.setOnClickListener(this);
        return inflate;
    }

    public void onClick(View view) {
        ((InputMethodManager) getContext().getSystemService("input_method")).hideSoftInputFromWindow(view.getWindowToken(), 0);
        if (view.getId() == R.id.btn_get_user_detail) {
            final String trim = this.et_profile_change.getText().toString().trim();
            if (trim.equals("")) {
                new AlertHelperclass().ntoast("PLEASE ENTER A VALID TELEPHONE NO/USER-ID!!", getContext());
                return;
            }
            this.btn_profile_change.setVisibility(8);
            this.lay_profile_change.setVisibility(8);
            this.progress_dialog.show();
            RequestQueue newRequestQueue = Volley.newRequestQueue(getActivity());
            AnonymousClass3 r6 = new StringRequest(1, getString(R.string.serverip) + "bb_fault_rectify.php", new Response.Listener<String>() {
                public void onResponse(String str) {
                    Fault_Rectify_Profile_ChangeFragment.this.progress_dialog.cancel();
                    try {
                        JSONObject jSONObject = new JSONObject(str);
                        if (!jSONObject.getBoolean("is_connect")) {
                            Fault_Rectify_Profile_ChangeFragment.this.txt_alert.setText(jSONObject.getString("error_log"));
                            Fault_Rectify_Profile_ChangeFragment.this.error_dialog.show();
                        } else if (jSONObject.getBoolean("success")) {
                            Fault_Rectify_Profile_ChangeFragment.this.txt_profile_change_telephone.setText(jSONObject.getString("telepnone"));
                            Fault_Rectify_Profile_ChangeFragment.this.txt_profile_change_userid.setText(jSONObject.getString("userid"));
                            Fault_Rectify_Profile_ChangeFragment.this.txt_profile_change_port.setText(jSONObject.getString("port"));
                            Fault_Rectify_Profile_ChangeFragment.this.txt_profile_change_planname.setText(jSONObject.getString("planname"));
                            Fault_Rectify_Profile_ChangeFragment.this.txt_profile_change_bandwidth.setText(jSONObject.getString("bandwidth"));
                            String unused = Fault_Rectify_Profile_ChangeFragment.this.Dslam_ip = jSONObject.getString("dslamip");
                            String unused2 = Fault_Rectify_Profile_ChangeFragment.this.Port = jSONObject.getString("port");
                            Fault_Rectify_Profile_ChangeFragment.this.lay_profile_change.setVisibility(0);
                            boolean z = jSONObject.getBoolean("db_error");
                            boolean z2 = jSONObject.getBoolean("authorization_error");
                            if (!z) {
                                if (!z2) {
                                    Fault_Rectify_Profile_ChangeFragment.this.btn_profile_change.setVisibility(0);
                                    Fault_Rectify_Profile_ChangeFragment.this.btn_profile_change.setEnabled(true);
                                    return;
                                }
                            }
                            Fault_Rectify_Profile_ChangeFragment.this.btn_profile_change.setVisibility(8);
                            Fault_Rectify_Profile_ChangeFragment.this.txt_alert.setText(jSONObject.getString("error_log"));
                            Fault_Rectify_Profile_ChangeFragment.this.error_dialog.show();
                        } else {
                            Fault_Rectify_Profile_ChangeFragment.this.txt_alert.setText(jSONObject.getString("error_log"));
                            Fault_Rectify_Profile_ChangeFragment.this.error_dialog.show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                public void onErrorResponse(VolleyError volleyError) {
                    Fault_Rectify_Profile_ChangeFragment.this.progress_dialog.cancel();
                    Fault_Rectify_Profile_ChangeFragment.this.txt_alert.setText(new VolleyErrorHelper().onErrorResponse(volleyError, Fault_Rectify_Profile_ChangeFragment.this.getContext()));
                    Fault_Rectify_Profile_ChangeFragment.this.error_dialog.show();
                }
            }) {
                /* access modifiers changed from: protected */
                public Map<String, String> getParams() throws AuthFailureError {
                    HashMap hashMap = new HashMap();
                    hashMap.put("userid", trim);
                    hashMap.put("access_level", Fault_Rectify_Profile_ChangeFragment.this.Pref_Access_Level);
                    hashMap.put("circle", Fault_Rectify_Profile_ChangeFragment.this.Pref_Circle);
                    hashMap.put("ssa", Fault_Rectify_Profile_ChangeFragment.this.Pref_SSA);
                    hashMap.put("username", Fault_Rectify_Profile_ChangeFragment.this.Pref_Username);
                    hashMap.put("random_key", Fault_Rectify_Profile_ChangeFragment.this.Pref_Randkey);
                    hashMap.put("device_id", Fault_Rectify_Profile_ChangeFragment.this.androidId);
                    return hashMap;
                }
            };
            r6.setRetryPolicy(new DefaultRetryPolicy(BZip2Constants.BASEBLOCKSIZE, 1, 1.0f));
            newRequestQueue.add(r6);
        } else if (view.getId() == R.id.btn_profile_change) {
            final String trim2 = this.spinner_profie.getSelectedItem().toString().trim();
            if (trim2.equals("-- SELECT-PROFILE --")) {
                new AlertHelperclass().ntoast("PLEASE SELECT PROFILE", getContext());
                return;
            }
            this.progress_dialog.show();
            RequestQueue newRequestQueue2 = Volley.newRequestQueue(getActivity());
            AnonymousClass6 r62 = new StringRequest(1, getString(R.string.serverip) + "profile_change.php", new Response.Listener<String>() {
                public void onResponse(String str) {
                    Fault_Rectify_Profile_ChangeFragment.this.progress_dialog.cancel();
                    try {
                        JSONObject jSONObject = new JSONObject(str);
                        if (jSONObject.getBoolean("success")) {
                            new AlertDialog.Builder(Fault_Rectify_Profile_ChangeFragment.this.getActivity()).setTitle("SUCCESS").setIcon(R.drawable.ic_action_ok).setCancelable(false).setMessage("Profile Is Changed SuccessFully\nPlease Wait For 5 Mins For DSL to Get Stable.").setNegativeButton("ok", (DialogInterface.OnClickListener) null).create().show();
                            return;
                        }
                        Fault_Rectify_Profile_ChangeFragment.this.txt_alert.setText(jSONObject.getString("error_log"));
                        Fault_Rectify_Profile_ChangeFragment.this.error_dialog.show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                public void onErrorResponse(VolleyError volleyError) {
                    Fault_Rectify_Profile_ChangeFragment.this.progress_dialog.cancel();
                    new AlertDialog.Builder(Fault_Rectify_Profile_ChangeFragment.this.getActivity()).setTitle("Error").setIcon(R.drawable.ic_action_error).setCancelable(false).setMessage(new VolleyErrorHelper().onErrorResponse(volleyError, Fault_Rectify_Profile_ChangeFragment.this.getContext())).setNegativeButton("Retry", (DialogInterface.OnClickListener) null).create().show();
                }
            }) {
                /* access modifiers changed from: protected */
                public Map<String, String> getParams() throws AuthFailureError {
                    HashMap hashMap = new HashMap();
                    hashMap.put("dslampip", Fault_Rectify_Profile_ChangeFragment.this.Dslam_ip);
                    hashMap.put("port", Fault_Rectify_Profile_ChangeFragment.this.Port);
                    hashMap.put(Scopes.PROFILE, trim2);
                    hashMap.put("username", Fault_Rectify_Profile_ChangeFragment.this.Pref_Username);
                    hashMap.put("random_key", Fault_Rectify_Profile_ChangeFragment.this.Pref_Randkey);
                    hashMap.put("device_id", Fault_Rectify_Profile_ChangeFragment.this.androidId);
                    return hashMap;
                }
            };
            r62.setRetryPolicy(new DefaultRetryPolicy(BZip2Constants.BASEBLOCKSIZE, 1, 1.0f));
            newRequestQueue2.add(r62);
        }
    }
}
