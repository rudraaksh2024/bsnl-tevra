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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
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

public class Fault_Rectify_Port_Reset_Fragment extends Fragment implements View.OnClickListener {
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
    private TextView btn_get_port_detail;
    /* access modifiers changed from: private */
    public TextView btn_port_reset;
    /* access modifiers changed from: private */
    public AlertDialog error_dialog;
    private EditText et_port_reset;
    private ImageView imageView;
    /* access modifiers changed from: private */
    public LinearLayout lay_port_reset;
    /* access modifiers changed from: private */
    public AlertDialog progress_dialog;
    private SharedPreferences sharedPreferences;
    /* access modifiers changed from: private */
    public TextView txt_alert;
    /* access modifiers changed from: private */
    public TextView txt_bb_port;
    /* access modifiers changed from: private */
    public TextView txt_bb_telephone;
    /* access modifiers changed from: private */
    public TextView txt_bb_userid;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fault_rectify_port_reset_fragment, viewGroup, false);
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
        this.et_port_reset = (EditText) inflate.findViewById(R.id.et_port_reset);
        this.btn_get_port_detail = (TextView) inflate.findViewById(R.id.btn_get_port_detail);
        this.lay_port_reset = (LinearLayout) inflate.findViewById(R.id.lay_port_reset);
        this.txt_bb_telephone = (TextView) inflate.findViewById(R.id.txt_bb_telephone);
        this.txt_bb_userid = (TextView) inflate.findViewById(R.id.txt_bb_userid);
        this.txt_bb_port = (TextView) inflate.findViewById(R.id.txt_bb_port);
        this.btn_port_reset = (TextView) inflate.findViewById(R.id.btn_port_reset);
        this.btn_get_port_detail.setOnClickListener(this);
        this.btn_port_reset.setOnClickListener(this);
        return inflate;
    }

    public void onClick(View view) {
        ((InputMethodManager) getContext().getSystemService("input_method")).hideSoftInputFromWindow(view.getWindowToken(), 0);
        if (view.getId() == R.id.btn_get_port_detail) {
            final String trim = this.et_port_reset.getText().toString().trim();
            if (trim.equals("")) {
                new AlertHelperclass().ntoast("PLEASE ENTER A VALID TELEPHONE NO/USER-ID!!", getContext());
                return;
            }
            this.progress_dialog.show();
            this.btn_port_reset.setVisibility(8);
            this.lay_port_reset.setVisibility(8);
            RequestQueue newRequestQueue = Volley.newRequestQueue(getActivity());
            AnonymousClass3 r6 = new StringRequest(1, getString(R.string.serverip) + "bb_fault_rectify.php", new Response.Listener<String>() {
                public void onResponse(String str) {
                    Fault_Rectify_Port_Reset_Fragment.this.progress_dialog.cancel();
                    try {
                        JSONObject jSONObject = new JSONObject(str);
                        if (!jSONObject.getBoolean("is_connect")) {
                            Fault_Rectify_Port_Reset_Fragment.this.txt_alert.setText(jSONObject.getString("error_log"));
                            Fault_Rectify_Port_Reset_Fragment.this.error_dialog.show();
                        } else if (jSONObject.getBoolean("success")) {
                            Fault_Rectify_Port_Reset_Fragment.this.txt_bb_telephone.setText(jSONObject.getString("telepnone"));
                            Fault_Rectify_Port_Reset_Fragment.this.txt_bb_userid.setText(jSONObject.getString("userid"));
                            Fault_Rectify_Port_Reset_Fragment.this.txt_bb_port.setText(jSONObject.getString("port"));
                            String unused = Fault_Rectify_Port_Reset_Fragment.this.Dslam_ip = jSONObject.getString("dslamip");
                            String unused2 = Fault_Rectify_Port_Reset_Fragment.this.Port = jSONObject.getString("port");
                            Fault_Rectify_Port_Reset_Fragment.this.lay_port_reset.setVisibility(0);
                            boolean z = jSONObject.getBoolean("db_error");
                            boolean z2 = jSONObject.getBoolean("authorization_error");
                            if (!z) {
                                if (!z2) {
                                    Fault_Rectify_Port_Reset_Fragment.this.btn_port_reset.setVisibility(0);
                                    Fault_Rectify_Port_Reset_Fragment.this.btn_port_reset.setEnabled(true);
                                    return;
                                }
                            }
                            Fault_Rectify_Port_Reset_Fragment.this.btn_port_reset.setVisibility(8);
                            Fault_Rectify_Port_Reset_Fragment.this.txt_alert.setText(jSONObject.getString("error_log"));
                            Fault_Rectify_Port_Reset_Fragment.this.error_dialog.show();
                        } else {
                            Fault_Rectify_Port_Reset_Fragment.this.txt_alert.setText(jSONObject.getString("error_log"));
                            Fault_Rectify_Port_Reset_Fragment.this.error_dialog.show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                public void onErrorResponse(VolleyError volleyError) {
                    Fault_Rectify_Port_Reset_Fragment.this.progress_dialog.cancel();
                    new AlertDialog.Builder(Fault_Rectify_Port_Reset_Fragment.this.getActivity()).setTitle("Error").setIcon(R.drawable.ic_action_error).setCancelable(false).setMessage(new VolleyErrorHelper().onErrorResponse(volleyError, Fault_Rectify_Port_Reset_Fragment.this.getContext())).setNegativeButton("Retry", (DialogInterface.OnClickListener) null).create().show();
                }
            }) {
                /* access modifiers changed from: protected */
                public Map<String, String> getParams() throws AuthFailureError {
                    HashMap hashMap = new HashMap();
                    hashMap.put("userid", trim);
                    hashMap.put("access_level", Fault_Rectify_Port_Reset_Fragment.this.Pref_Access_Level);
                    hashMap.put("circle", Fault_Rectify_Port_Reset_Fragment.this.Pref_Circle);
                    hashMap.put("ssa", Fault_Rectify_Port_Reset_Fragment.this.Pref_SSA);
                    hashMap.put("username", Fault_Rectify_Port_Reset_Fragment.this.Pref_Username);
                    hashMap.put("random_key", Fault_Rectify_Port_Reset_Fragment.this.Pref_Randkey);
                    hashMap.put("device_id", Fault_Rectify_Port_Reset_Fragment.this.androidId);
                    return hashMap;
                }
            };
            r6.setRetryPolicy(new DefaultRetryPolicy(BZip2Constants.BASEBLOCKSIZE, 1, 1.0f));
            newRequestQueue.add(r6);
        } else if (view.getId() == R.id.btn_port_reset) {
            this.progress_dialog.show();
            RequestQueue newRequestQueue2 = Volley.newRequestQueue(getActivity());
            AnonymousClass6 r5 = new StringRequest(1, getString(R.string.serverip) + "port_reset.php", new Response.Listener<String>() {
                public void onResponse(String str) {
                    Fault_Rectify_Port_Reset_Fragment.this.progress_dialog.cancel();
                    try {
                        JSONObject jSONObject = new JSONObject(str);
                        if (jSONObject.getBoolean("success")) {
                            new AlertDialog.Builder(Fault_Rectify_Port_Reset_Fragment.this.getActivity()).setTitle("SUCCESS").setIcon(R.drawable.ic_action_ok).setCancelable(false).setMessage("Port Reset Is Done SuccessFully\nPlease Wait For 5 Mins For DSL to Get Stable.").setNegativeButton("ok", (DialogInterface.OnClickListener) null).create().show();
                            return;
                        }
                        Fault_Rectify_Port_Reset_Fragment.this.txt_alert.setText(jSONObject.getString("error_log"));
                        Fault_Rectify_Port_Reset_Fragment.this.error_dialog.show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                public void onErrorResponse(VolleyError volleyError) {
                    Fault_Rectify_Port_Reset_Fragment.this.progress_dialog.cancel();
                    new AlertDialog.Builder(Fault_Rectify_Port_Reset_Fragment.this.getActivity()).setTitle("Error").setIcon(R.drawable.ic_action_error).setCancelable(false).setMessage(new VolleyErrorHelper().onErrorResponse(volleyError, Fault_Rectify_Port_Reset_Fragment.this.getContext())).setNegativeButton("Retry", (DialogInterface.OnClickListener) null).create().show();
                }
            }) {
                /* access modifiers changed from: protected */
                public Map<String, String> getParams() throws AuthFailureError {
                    HashMap hashMap = new HashMap();
                    hashMap.put("dslampip", Fault_Rectify_Port_Reset_Fragment.this.Dslam_ip);
                    hashMap.put("port", Fault_Rectify_Port_Reset_Fragment.this.Port);
                    hashMap.put("username", Fault_Rectify_Port_Reset_Fragment.this.Pref_Username);
                    hashMap.put("random_key", Fault_Rectify_Port_Reset_Fragment.this.Pref_Randkey);
                    hashMap.put("device_id", Fault_Rectify_Port_Reset_Fragment.this.androidId);
                    return hashMap;
                }
            };
            r5.setRetryPolicy(new DefaultRetryPolicy(BZip2Constants.BASEBLOCKSIZE, 1, 1.0f));
            newRequestQueue2.add(r5);
        }
    }
}
