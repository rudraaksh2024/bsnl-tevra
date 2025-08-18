package bsnl.bsnl_teevra;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.Settings;
import android.text.Editable;
import android.text.Html;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.apache.logging.log4j.message.ParameterizedMessage;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Register_Activity extends AppCompatActivity implements View.OnClickListener {
    /* access modifiers changed from: private */
    public String Final_Unit;
    private Button btn_fms_validate_user;
    private Button btn_otp_submit;
    private Button btn_validate_user;
    private CountDownTimer cTimer = null;
    /* access modifiers changed from: private */
    public ArrayList<String> circle_element;
    /* access modifiers changed from: private */
    public String circle_name;
    /* access modifiers changed from: private */
    public AlertDialog error_dialog;
    /* access modifiers changed from: private */
    public EditText et_email;
    /* access modifiers changed from: private */
    public EditText et_fms_mobile;
    /* access modifiers changed from: private */
    public EditText et_fms_name;
    /* access modifiers changed from: private */
    public EditText et_fms_password;
    /* access modifiers changed from: private */
    public EditText et_fms_username;
    /* access modifiers changed from: private */
    public EditText et_hrms;
    /* access modifiers changed from: private */
    public EditText et_mobile;
    /* access modifiers changed from: private */
    public EditText et_name;
    /* access modifiers changed from: private */
    public EditText et_otp1;
    /* access modifiers changed from: private */
    public EditText et_otp2;
    /* access modifiers changed from: private */
    public EditText et_otp3;
    /* access modifiers changed from: private */
    public EditText et_otp4;
    /* access modifiers changed from: private */
    public EditText et_otp5;
    /* access modifiers changed from: private */
    public EditText et_otp6;
    /* access modifiers changed from: private */
    public EditText et_password;
    /* access modifiers changed from: private */
    public EditText et_surname;
    /* access modifiers changed from: private */
    public EditText et_unit;
    /* access modifiers changed from: private */
    public String final_account_status;
    /* access modifiers changed from: private */
    public String final_bbdetail_privilege;
    /* access modifiers changed from: private */
    public String final_circle;
    /* access modifiers changed from: private */
    public String final_designation;
    /* access modifiers changed from: private */
    public String final_email;
    private boolean final_email_validation;
    /* access modifiers changed from: private */
    public String final_fms_firm;
    /* access modifiers changed from: private */
    public String final_fms_role;
    /* access modifiers changed from: private */
    public String final_fms_status;
    /* access modifiers changed from: private */
    public String final_fms_teamid;
    /* access modifiers changed from: private */
    public String final_fms_userid;
    /* access modifiers changed from: private */
    public String final_fms_username;
    /* access modifiers changed from: private */
    public String final_fullname;
    private String final_lastname;
    /* access modifiers changed from: private */
    public String final_mobile;
    private String final_name;
    /* access modifiers changed from: private */
    public String final_password;
    /* access modifiers changed from: private */
    public String final_ssa;
    /* access modifiers changed from: private */
    public String final_tipdetail_privilege;
    /* access modifiers changed from: private */
    public String final_tipnms_privilege;
    /* access modifiers changed from: private */
    public String final_username;
    /* access modifiers changed from: private */
    public ArrayList<String> franchisee_list;
    /* access modifiers changed from: private */
    public String franchisee_type;
    /* access modifiers changed from: private */
    public AlertDialog info_dialog;
    /* access modifiers changed from: private */
    public LinearLayout lay_bsnl_register;
    /* access modifiers changed from: private */
    public LinearLayout lay_fms_register;
    /* access modifiers changed from: private */
    public LinearLayout lay_fms_register01;
    /* access modifiers changed from: private */
    public LinearLayout lay_otp;
    /* access modifiers changed from: private */
    public LinearLayout lay_register;
    /* access modifiers changed from: private */
    public String mobile;
    /* access modifiers changed from: private */
    public AlertDialog progress_dialog;
    /* access modifiers changed from: private */
    public RadioGroup radio;
    /* access modifiers changed from: private */
    public RadioButton radio_usertype;
    /* access modifiers changed from: private */
    public Spinner sp_circle;
    /* access modifiers changed from: private */
    public Spinner sp_designation;
    /* access modifiers changed from: private */
    public Spinner sp_fmscircle;
    /* access modifiers changed from: private */
    public Spinner sp_fmsssa;
    /* access modifiers changed from: private */
    public Spinner sp_franchisee_type;
    /* access modifiers changed from: private */
    public Spinner sp_ssa;
    /* access modifiers changed from: private */
    public ArrayList<String> ssa_element;
    /* access modifiers changed from: private */
    public String ssa_name;
    private TextView tv_resendotp;
    /* access modifiers changed from: private */
    public TextView tv_timer;
    /* access modifiers changed from: private */
    public TextView txt_alert;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.register_activity);
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
        this.lay_register = (LinearLayout) findViewById(R.id.lay_register);
        this.lay_bsnl_register = (LinearLayout) findViewById(R.id.lay_bsnl_register);
        this.lay_fms_register = (LinearLayout) findViewById(R.id.lay_fms_register);
        this.lay_fms_register01 = (LinearLayout) findViewById(R.id.lay_fms_register01);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.lay_otp);
        this.lay_otp = linearLayout;
        linearLayout.setVisibility(8);
        final String[] strArr = {"-- DESIGNATION --", "DIR", "CGM", "PGM", "GM", "DGM", "AGM", "SDE", "JTO", "JE", "LM", "TM"};
        this.et_fms_mobile = (EditText) findViewById(R.id.et_fms_mobile);
        this.et_fms_name = (EditText) findViewById(R.id.et_fms_name);
        this.et_fms_username = (EditText) findViewById(R.id.et_fms_username);
        this.et_fms_password = (EditText) findViewById(R.id.et_fms_password);
        this.sp_fmscircle = (Spinner) findViewById(R.id.sp_fmscircle);
        this.sp_fmsssa = (Spinner) findViewById(R.id.sp_fmsssa);
        this.sp_franchisee_type = (Spinner) findViewById(R.id.sp_fms_user_type);
        this.btn_fms_validate_user = (Button) findViewById(R.id.btn_fms_validate_user);
        this.et_name = (EditText) findViewById(R.id.et_name);
        this.et_surname = (EditText) findViewById(R.id.et_surname);
        this.et_hrms = (EditText) findViewById(R.id.et_hrms);
        this.et_password = (EditText) findViewById(R.id.et_password);
        this.et_mobile = (EditText) findViewById(R.id.et_mobile);
        this.et_email = (EditText) findViewById(R.id.et_email);
        this.et_unit = (EditText) findViewById(R.id.et_unit);
        this.sp_designation = (Spinner) findViewById(R.id.sp_designation);
        this.sp_circle = (Spinner) findViewById(R.id.sp_circle);
        this.sp_ssa = (Spinner) findViewById(R.id.sp_ssa);
        this.btn_validate_user = (Button) findViewById(R.id.btn_validate_user);
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radio);
        this.radio = radioGroup;
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                Register_Activity register_Activity = Register_Activity.this;
                RadioButton unused = register_Activity.radio_usertype = (RadioButton) register_Activity.findViewById(i);
                int indexOfChild = Register_Activity.this.radio.indexOfChild(Register_Activity.this.radio_usertype);
                if (indexOfChild == 0) {
                    Register_Activity.this.lay_bsnl_register.setVisibility(0);
                    Register_Activity.this.lay_fms_register.setVisibility(8);
                    Register_Activity.this.et_name.setText("");
                    Register_Activity.this.et_surname.setText("");
                    Register_Activity.this.et_hrms.setText("");
                    Register_Activity.this.et_password.setText("");
                    Register_Activity.this.et_mobile.setText("");
                    Register_Activity.this.et_email.setText("");
                    Register_Activity.this.et_unit.setText("");
                    Register_Activity.this.sp_designation.setAdapter((SpinnerAdapter) null);
                    Register_Activity.this.sp_circle.setAdapter((SpinnerAdapter) null);
                    Register_Activity.this.sp_ssa.setAdapter((SpinnerAdapter) null);
                    Register_Activity.this.sp_designation.setAdapter(new ArrayAdapter(Register_Activity.this, R.layout.spinner_item, strArr));
                    Register_Activity.this.progress_dialog.show();
                    ArrayList unused2 = Register_Activity.this.circle_element = new ArrayList();
                    Register_Activity.this.circle_element.clear();
                    Register_Activity.this.circle_element.add("-- CIRCLE --");
                    RequestQueue newRequestQueue = Volley.newRequestQueue(Register_Activity.this);
                    AnonymousClass3 r0 = new StringRequest(1, Register_Activity.this.getString(R.string.serverip) + "register_circle_populate.php", new Response.Listener<String>() {
                        public void onResponse(String str) {
                            Register_Activity.this.progress_dialog.cancel();
                            try {
                                JSONArray jSONArray = new JSONArray(str);
                                for (int i = 0; i < jSONArray.length(); i++) {
                                    Register_Activity.this.circle_element.add(jSONArray.getJSONObject(i).getString("CIRCLE"));
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                        public void onErrorResponse(VolleyError volleyError) {
                            Register_Activity.this.progress_dialog.cancel();
                            Register_Activity.this.txt_alert.setText(new VolleyErrorHelper().onErrorResponse(volleyError, Register_Activity.this.getApplicationContext()));
                            Register_Activity.this.error_dialog.show();
                        }
                    }) {
                        /* access modifiers changed from: protected */
                        public Map<String, String> getParams() throws AuthFailureError {
                            return new HashMap();
                        }
                    };
                    r0.setRetryPolicy(new DefaultRetryPolicy(BZip2Constants.BASEBLOCKSIZE, 1, 1.0f));
                    newRequestQueue.add(r0);
                    Spinner access$1200 = Register_Activity.this.sp_circle;
                    Register_Activity register_Activity2 = Register_Activity.this;
                    access$1200.setAdapter(new ArrayAdapter(register_Activity2, R.layout.spinner_item, register_Activity2.circle_element));
                    Register_Activity.this.sp_circle.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }

                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                            ArrayList unused = Register_Activity.this.ssa_element = new ArrayList();
                            Register_Activity.this.ssa_element.add("-- SSA --");
                            String unused2 = Register_Activity.this.circle_name = Register_Activity.this.sp_circle.getSelectedItem().toString();
                            if (!Register_Activity.this.circle_name.equals("-- CIRCLE --")) {
                                Register_Activity.this.progress_dialog.show();
                                RequestQueue newRequestQueue = Volley.newRequestQueue(Register_Activity.this);
                                AnonymousClass3 r0 = new StringRequest(1, Register_Activity.this.getString(R.string.serverip) + "register_ssa_populate.php", new Response.Listener<String>() {
                                    public void onResponse(String str) {
                                        Register_Activity.this.progress_dialog.cancel();
                                        try {
                                            JSONArray jSONArray = new JSONArray(str);
                                            for (int i = 0; i < jSONArray.length(); i++) {
                                                Register_Activity.this.ssa_element.add(jSONArray.getJSONObject(i).getString("SSA"));
                                            }
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }, new Response.ErrorListener() {
                                    public void onErrorResponse(VolleyError volleyError) {
                                        Register_Activity.this.progress_dialog.cancel();
                                        Register_Activity.this.txt_alert.setText(new VolleyErrorHelper().onErrorResponse(volleyError, Register_Activity.this.getApplicationContext()));
                                        Register_Activity.this.error_dialog.show();
                                    }
                                }) {
                                    /* access modifiers changed from: protected */
                                    public Map<String, String> getParams() throws AuthFailureError {
                                        HashMap hashMap = new HashMap();
                                        hashMap.put("circle", Register_Activity.this.circle_name);
                                        return hashMap;
                                    }
                                };
                                r0.setRetryPolicy(new DefaultRetryPolicy(BZip2Constants.BASEBLOCKSIZE, 1, 1.0f));
                                newRequestQueue.add(r0);
                            }
                            Register_Activity.this.sp_ssa.setAdapter(new ArrayAdapter(Register_Activity.this, R.layout.spinner_item, Register_Activity.this.ssa_element));
                        }
                    });
                } else if (indexOfChild == 1) {
                    Register_Activity.this.lay_bsnl_register.setVisibility(8);
                    Register_Activity.this.lay_fms_register.setVisibility(0);
                    Register_Activity.this.et_fms_mobile.setEnabled(false);
                    Register_Activity.this.et_fms_mobile.setText("");
                    Register_Activity.this.et_fms_name.setText("");
                    Register_Activity.this.et_fms_username.setText("");
                    Register_Activity.this.et_fms_password.setText("");
                    Register_Activity.this.sp_fmscircle.setAdapter((SpinnerAdapter) null);
                    Register_Activity.this.sp_fmsssa.setAdapter((SpinnerAdapter) null);
                    Register_Activity.this.sp_franchisee_type.setAdapter((SpinnerAdapter) null);
                    Register_Activity.this.progress_dialog.show();
                    ArrayList unused3 = Register_Activity.this.circle_element = new ArrayList();
                    Register_Activity.this.circle_element.clear();
                    Register_Activity.this.circle_element.add("-- CIRCLE --");
                    RequestQueue newRequestQueue2 = Volley.newRequestQueue(Register_Activity.this);
                    AnonymousClass7 r02 = new StringRequest(1, Register_Activity.this.getString(R.string.serverip) + "register_circle_populate.php", new Response.Listener<String>() {
                        public void onResponse(String str) {
                            Register_Activity.this.progress_dialog.cancel();
                            try {
                                JSONArray jSONArray = new JSONArray(str);
                                for (int i = 0; i < jSONArray.length(); i++) {
                                    Register_Activity.this.circle_element.add(jSONArray.getJSONObject(i).getString("CIRCLE"));
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                        public void onErrorResponse(VolleyError volleyError) {
                            Register_Activity.this.progress_dialog.cancel();
                            Register_Activity.this.txt_alert.setText(new VolleyErrorHelper().onErrorResponse(volleyError, Register_Activity.this.getApplicationContext()));
                            Register_Activity.this.error_dialog.show();
                        }
                    }) {
                        /* access modifiers changed from: protected */
                        public Map<String, String> getParams() throws AuthFailureError {
                            return new HashMap();
                        }
                    };
                    r02.setRetryPolicy(new DefaultRetryPolicy(BZip2Constants.BASEBLOCKSIZE, 1, 1.0f));
                    newRequestQueue2.add(r02);
                    Spinner access$2400 = Register_Activity.this.sp_fmscircle;
                    Register_Activity register_Activity3 = Register_Activity.this;
                    access$2400.setAdapter(new ArrayAdapter(register_Activity3, R.layout.spinner_item, register_Activity3.circle_element));
                    Register_Activity.this.sp_fmscircle.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }

                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                            ArrayList unused = Register_Activity.this.ssa_element = new ArrayList();
                            Register_Activity.this.ssa_element.add("-- SSA --");
                            String unused2 = Register_Activity.this.circle_name = Register_Activity.this.sp_fmscircle.getSelectedItem().toString();
                            if (!Register_Activity.this.circle_name.equals("-- CIRCLE --")) {
                                Register_Activity.this.progress_dialog.show();
                                RequestQueue newRequestQueue = Volley.newRequestQueue(Register_Activity.this);
                                AnonymousClass3 r0 = new StringRequest(1, Register_Activity.this.getString(R.string.serverip) + "register_ssa_populate.php", new Response.Listener<String>() {
                                    public void onResponse(String str) {
                                        Register_Activity.this.progress_dialog.cancel();
                                        try {
                                            JSONArray jSONArray = new JSONArray(str);
                                            for (int i = 0; i < jSONArray.length(); i++) {
                                                Register_Activity.this.ssa_element.add(jSONArray.getJSONObject(i).getString("SSA"));
                                            }
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }, new Response.ErrorListener() {
                                    public void onErrorResponse(VolleyError volleyError) {
                                        Register_Activity.this.progress_dialog.cancel();
                                        Register_Activity.this.txt_alert.setText(new VolleyErrorHelper().onErrorResponse(volleyError, Register_Activity.this.getApplicationContext()));
                                        Register_Activity.this.error_dialog.show();
                                    }
                                }) {
                                    /* access modifiers changed from: protected */
                                    public Map<String, String> getParams() throws AuthFailureError {
                                        HashMap hashMap = new HashMap();
                                        hashMap.put("circle", Register_Activity.this.circle_name);
                                        return hashMap;
                                    }
                                };
                                r0.setRetryPolicy(new DefaultRetryPolicy(BZip2Constants.BASEBLOCKSIZE, 1, 1.0f));
                                newRequestQueue.add(r0);
                            }
                            Register_Activity.this.sp_fmsssa.setAdapter(new ArrayAdapter(Register_Activity.this, R.layout.spinner_item, Register_Activity.this.ssa_element));
                            Register_Activity.this.sp_fmsssa.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                public void onNothingSelected(AdapterView<?> adapterView) {
                                }

                                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                                    ArrayList unused = Register_Activity.this.franchisee_list = new ArrayList();
                                    Register_Activity.this.franchisee_list.add("-- USER-TYPE --");
                                    String unused2 = Register_Activity.this.ssa_name = Register_Activity.this.sp_fmsssa.getSelectedItem().toString();
                                    if (!Register_Activity.this.ssa_name.equals("-- USER-TYPE --")) {
                                        Register_Activity.this.franchisee_list.add("FRANCHISEE OWNER");
                                        Register_Activity.this.franchisee_list.add("FRANCHISEE MEMBER");
                                    }
                                    Register_Activity.this.sp_franchisee_type.setAdapter(new ArrayAdapter(Register_Activity.this, R.layout.spinner_item, Register_Activity.this.franchisee_list));
                                    Register_Activity.this.sp_franchisee_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                        public void onNothingSelected(AdapterView<?> adapterView) {
                                        }

                                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                                            String unused = Register_Activity.this.franchisee_type = Register_Activity.this.sp_franchisee_type.getSelectedItem().toString();
                                            Register_Activity.this.et_fms_mobile.setText("");
                                            Register_Activity.this.et_fms_name.setText("");
                                            Register_Activity.this.et_fms_username.setText("");
                                            Register_Activity.this.et_fms_password.setText("");
                                            Register_Activity.this.lay_fms_register01.setVisibility(8);
                                            Register_Activity.this.et_fms_mobile.setEnabled(!Register_Activity.this.franchisee_type.equals("-- USER-TYPE --"));
                                        }
                                    });
                                }
                            });
                        }
                    });
                    Register_Activity.this.et_fms_mobile.addTextChangedListener(new TextWatcher() {
                        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                        }

                        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                        }

                        public void afterTextChanged(Editable editable) {
                            if (Register_Activity.this.et_fms_mobile.getText().length() == 10) {
                                String unused = Register_Activity.this.mobile = Register_Activity.this.et_fms_mobile.getText().toString();
                                Register_Activity.this.et_fms_mobile.setEnabled(false);
                                Register_Activity.this.progress_dialog.show();
                                Register_Activity.this.progress_dialog.show();
                                RequestQueue newRequestQueue = Volley.newRequestQueue(Register_Activity.this);
                                AnonymousClass3 r0 = new StringRequest(1, Register_Activity.this.getString(R.string.serverip) + "fms/fms_register.php", new Response.Listener<String>() {
                                    public void onResponse(String str) {
                                        Register_Activity.this.progress_dialog.cancel();
                                        try {
                                            JSONObject jSONObject = new JSONObject(str);
                                            String unused = Register_Activity.this.final_bbdetail_privilege = jSONObject.getString("bb_detail_privilege");
                                            String unused2 = Register_Activity.this.final_tipdetail_privilege = jSONObject.getString("tip_detail_privilege");
                                            String unused3 = Register_Activity.this.final_tipnms_privilege = jSONObject.getString("nms_tip_privilege");
                                            if (jSONObject.getBoolean("STATUS")) {
                                                Register_Activity.this.lay_fms_register01.setVisibility(0);
                                                JSONObject jSONObject2 = jSONObject.getJSONArray("ROWSET").getJSONObject(0);
                                                Register_Activity.this.et_fms_name.setText(jSONObject2.getString("NAME"));
                                                Register_Activity.this.et_fms_username.setText(jSONObject2.getString("TEEVRA_USERNAME"));
                                                String unused4 = Register_Activity.this.final_fms_username = jSONObject2.getString("USERNAME");
                                                String unused5 = Register_Activity.this.final_fms_firm = jSONObject2.getString("FIRM_NAME");
                                                String unused6 = Register_Activity.this.final_fms_teamid = jSONObject2.getString("TEAM_ID");
                                                if (Register_Activity.this.sp_franchisee_type.getSelectedItem().toString().equals("FRANCHISEE OWNER")) {
                                                    String unused7 = Register_Activity.this.final_fms_role = "FMS-ADMIN";
                                                    String unused8 = Register_Activity.this.final_fms_userid = jSONObject2.getString("TEAM_ID");
                                                } else {
                                                    String unused9 = Register_Activity.this.final_fms_role = "FMS-USER";
                                                    String unused10 = Register_Activity.this.final_fms_userid = jSONObject2.getString("USERID");
                                                }
                                                String unused11 = Register_Activity.this.final_email = jSONObject2.getString("EMAIL").toLowerCase();
                                                return;
                                            }
                                            Register_Activity.this.txt_alert.setText("ERROR\n\n" + jSONObject.getString("ERROR_LOG"));
                                            Register_Activity.this.error_dialog.show();
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }, new Response.ErrorListener() {
                                    public void onErrorResponse(VolleyError volleyError) {
                                        Register_Activity.this.progress_dialog.cancel();
                                        Register_Activity.this.txt_alert.setText(new VolleyErrorHelper().onErrorResponse(volleyError, Register_Activity.this.getApplicationContext()));
                                        Register_Activity.this.error_dialog.show();
                                    }
                                }) {
                                    /* access modifiers changed from: protected */
                                    public Map<String, String> getParams() throws AuthFailureError {
                                        HashMap hashMap = new HashMap();
                                        hashMap.put("circle", Register_Activity.this.circle_name);
                                        hashMap.put("ssa", Register_Activity.this.ssa_name);
                                        hashMap.put("user_type", Register_Activity.this.franchisee_type);
                                        hashMap.put("mobile", Register_Activity.this.mobile);
                                        return hashMap;
                                    }
                                };
                                r0.setRetryPolicy(new DefaultRetryPolicy(BZip2Constants.BASEBLOCKSIZE, 1, 1.0f));
                                newRequestQueue.add(r0);
                            }
                        }
                    });
                }
            }
        });
        this.et_otp1 = (EditText) findViewById(R.id.et_otp1);
        this.et_otp2 = (EditText) findViewById(R.id.et_otp2);
        this.et_otp3 = (EditText) findViewById(R.id.et_otp3);
        this.et_otp4 = (EditText) findViewById(R.id.et_otp4);
        this.et_otp5 = (EditText) findViewById(R.id.et_otp5);
        this.et_otp6 = (EditText) findViewById(R.id.et_otp6);
        this.btn_otp_submit = (Button) findViewById(R.id.btn_otp_submit);
        TextView textView = (TextView) findViewById(R.id.tv_resendotp);
        this.tv_resendotp = textView;
        textView.setPaintFlags(8);
        this.tv_timer = (TextView) findViewById(R.id.tv_timer);
        this.btn_validate_user.setOnClickListener(this);
        this.btn_fms_validate_user.setOnClickListener(this);
        this.btn_otp_submit.setOnClickListener(this);
        this.tv_resendotp.setOnClickListener(this);
        this.et_otp1.addTextChangedListener(new GenericTextWatcher(this.et_otp1));
        this.et_otp2.addTextChangedListener(new GenericTextWatcher(this.et_otp2));
        this.et_otp3.addTextChangedListener(new GenericTextWatcher(this.et_otp3));
        this.et_otp4.addTextChangedListener(new GenericTextWatcher(this.et_otp4));
        this.et_otp5.addTextChangedListener(new GenericTextWatcher(this.et_otp5));
        this.et_otp6.addTextChangedListener(new GenericTextWatcher(this.et_otp6));
    }

    private void Toast_Volley_Error(VolleyError volleyError) {
        new AlertDialog.Builder(this).setTitle("Error").setIcon(R.drawable.ic_action_error).setCancelable(false).setMessage(new VolleyErrorHelper().onErrorResponse(volleyError, getApplicationContext())).setNegativeButton("Retry", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                Register_Activity.this.startActivity(new Intent(Register_Activity.this, Login_Activity.class));
                Register_Activity.this.finish();
                dialogInterface.cancel();
            }
        }).create().show();
    }

    public void onClick(View view) {
        ((InputMethodManager) getSystemService("input_method")).hideSoftInputFromWindow(this.lay_register.getWindowToken(), 2);
        if (this.lay_bsnl_register.getVisibility() == 0) {
            this.final_name = this.et_name.getText().toString().trim();
            this.final_lastname = this.et_surname.getText().toString().trim();
            this.final_fullname = this.final_name.toUpperCase() + " " + this.final_lastname.toUpperCase();
            this.final_username = this.et_hrms.getText().toString().trim();
            this.final_password = this.et_password.getText().toString().trim();
            this.final_mobile = this.et_mobile.getText().toString().trim();
            String trim = this.et_email.getText().toString().trim();
            this.final_email = trim;
            this.final_email_validation = isValidEmail(trim);
            this.Final_Unit = this.et_unit.getText().toString().trim().toUpperCase();
            this.final_designation = this.sp_designation.getSelectedItem().toString();
            this.final_circle = this.sp_circle.getSelectedItem().toString();
            this.final_ssa = this.sp_ssa.getSelectedItem().toString();
            this.final_fms_username = "";
            this.final_fms_firm = "";
            this.final_fms_teamid = "";
            this.final_fms_userid = "";
            this.final_account_status = "FALSE";
            this.final_fms_status = "FALSE";
            this.final_bbdetail_privilege = "FALSE";
            this.final_tipdetail_privilege = "FALSE";
            this.final_tipnms_privilege = "FALSE";
            this.final_fms_role = "FMS-USER";
        } else if (this.lay_fms_register.getVisibility() == 0) {
            this.final_username = this.et_fms_username.getText().toString().trim();
            this.final_password = this.et_fms_password.getText().toString().trim();
            this.final_mobile = this.et_fms_mobile.getText().toString().trim();
            this.final_email_validation = isValidEmail(this.final_email);
            this.final_designation = this.sp_franchisee_type.getSelectedItem().toString();
            this.final_circle = this.sp_fmscircle.getSelectedItem().toString();
            this.final_ssa = this.sp_fmsssa.getSelectedItem().toString();
            this.final_fullname = this.et_fms_name.getText().toString().trim().toUpperCase();
            this.Final_Unit = this.sp_fmsssa.getSelectedItem().toString();
            this.final_account_status = "TRUE";
            this.final_fms_status = "TRUE";
        }
        if (view.getId() == R.id.btn_validate_user) {
            if (this.final_name.equals("")) {
                Toast makeText = Toast.makeText(this, "First Name can't be blank", 1);
                makeText.setGravity(1, 0, 0);
                makeText.show();
            } else if (this.final_lastname.isEmpty()) {
                Toast makeText2 = Toast.makeText(this, "Surname can't be blank", 1);
                makeText2.setGravity(1, 0, 0);
                makeText2.show();
            } else if (this.final_username.isEmpty()) {
                Toast makeText3 = Toast.makeText(this, "Username can't be blank", 1);
                makeText3.setGravity(1, 0, 0);
                makeText3.show();
            } else if (this.final_password.isEmpty()) {
                Toast makeText4 = Toast.makeText(this, "Password can't be blank", 1);
                makeText4.setGravity(1, 0, 0);
                makeText4.show();
            } else if (this.final_mobile.isEmpty()) {
                Toast makeText5 = Toast.makeText(this, "Mobile can't be blank", 1);
                makeText5.setGravity(1, 0, 0);
                makeText5.show();
            } else if (!this.final_email_validation) {
                Toast makeText6 = Toast.makeText(this, "Please Enter A Valid Email-Address", 1);
                makeText6.setGravity(1, 0, 0);
                makeText6.show();
            } else if (this.final_designation.equals("-- DESIGNATION --")) {
                Toast makeText7 = Toast.makeText(this, "Please Select Your Designation", 1);
                makeText7.setGravity(1, 0, 0);
                makeText7.show();
            } else if (this.Final_Unit.isEmpty()) {
                Toast makeText8 = Toast.makeText(this, "Unit can't be blank", 1);
                makeText8.setGravity(1, 0, 0);
                makeText8.show();
            } else if (this.final_circle.equals("-- CIRCLE --") || this.final_circle.equals("")) {
                Toast makeText9 = Toast.makeText(this, "Please Select Your Circle", 1);
                makeText9.setGravity(1, 0, 0);
                makeText9.show();
            } else if (this.final_ssa.equals("-- SSA --") || this.final_ssa.equals("")) {
                Toast makeText10 = Toast.makeText(this, "Please Select Your SSA", 1);
                makeText10.setGravity(1, 0, 0);
                makeText10.show();
            } else {
                User_Validate();
            }
        } else if (view.getId() == R.id.btn_fms_validate_user) {
            if (this.final_fullname.equals("")) {
                Toast makeText11 = Toast.makeText(this, "NAME FIELD CAN'T BE BLANK", 1);
                makeText11.setGravity(1, 0, 0);
                makeText11.show();
            } else if (this.final_username.isEmpty()) {
                Toast makeText12 = Toast.makeText(this, "USERNAME FIELD CAN'T BE BLANK", 1);
                makeText12.setGravity(1, 0, 0);
                makeText12.show();
            } else if (this.final_password.isEmpty()) {
                Toast makeText13 = Toast.makeText(this, "PASSWORD FIELD CAN'T BE BLANK", 1);
                makeText13.setGravity(1, 0, 0);
                makeText13.show();
            } else if (this.final_mobile.isEmpty()) {
                Toast makeText14 = Toast.makeText(this, "MOBILE FIELD CAN'T BE BLANK", 1);
                makeText14.setGravity(1, 0, 0);
                makeText14.show();
            } else if (this.final_designation.equals("-- USER-TYPE --")) {
                Toast makeText15 = Toast.makeText(this, "PLEASE SELECT YOUR USER TYPE", 1);
                makeText15.setGravity(1, 0, 0);
                makeText15.show();
            } else if (this.final_circle.equals("-- CIRCLE --") || this.final_circle.equals("")) {
                Toast makeText16 = Toast.makeText(this, "PLEASE SELECT YOUR CIRCLE", 1);
                makeText16.setGravity(1, 0, 0);
                makeText16.show();
            } else if (this.final_ssa.equals("-- SSA --") || this.final_ssa.equals("")) {
                Toast makeText17 = Toast.makeText(this, "PLEASE SELECT YOUR SSA", 1);
                makeText17.setGravity(1, 0, 0);
                makeText17.show();
            } else {
                User_Validate();
            }
        } else if (view.getId() == R.id.tv_resendotp) {
            this.tv_resendotp.setVisibility(8);
            cancelTimer();
            User_Validate();
        } else if (view.getId() == R.id.btn_otp_submit) {
            cancelTimer();
            String trim2 = this.et_otp1.getText().toString().trim();
            String trim3 = this.et_otp2.getText().toString().trim();
            String trim4 = this.et_otp3.getText().toString().trim();
            String trim5 = this.et_otp4.getText().toString().trim();
            String trim6 = this.et_otp5.getText().toString().trim();
            String trim7 = this.et_otp6.getText().toString().trim();
            if (trim2.isEmpty() || trim3.isEmpty() || trim4.isEmpty() || trim5.isEmpty() || trim6.isEmpty() || trim7.isEmpty()) {
                Toast toast = new Toast(this);
                TextView textView = new TextView(this);
                textView.setText("PLEASE ENTER A VALID 6 DIGIT OTP");
                textView.setTextSize(0, getResources().getDimension(R.dimen.smalltext));
                textView.setTypeface((Typeface) null, 1);
                textView.setGravity(17);
                textView.setTextColor(getResources().getColor(R.color.colorRed));
                textView.setBackgroundResource(R.drawable.style10);
                textView.setPadding(15, 15, 15, 15);
                textView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_action_cross, 0, 0);
                toast.setView(textView);
                toast.show();
                return;
            }
            User_Register(trim2 + trim3 + trim4 + trim5 + trim6 + trim7);
        }
    }

    private void User_Register(String str) {
        final String string = Settings.Secure.getString(getContentResolver(), "android_id");
        this.progress_dialog.show();
        RequestQueue newRequestQueue = Volley.newRequestQueue(this);
        final String str2 = str;
        AnonymousClass5 r1 = new StringRequest(1, getString(R.string.serverip) + "register.php", new Response.Listener<String>() {
            public void onResponse(String str) {
                Register_Activity.this.progress_dialog.cancel();
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    if (jSONObject.getBoolean("success")) {
                        new AlertHelperclass().ptoast("USER IS SUCCESSFULLY REGISTERED\nREDIRECTING TO LOGIN", Register_Activity.this.getApplicationContext());
                        Register_Activity.this.startActivity(new Intent(Register_Activity.this, Login_Activity.class));
                        Register_Activity.this.finish();
                        return;
                    }
                    Register_Activity.this.txt_alert.setText("ERROR\n\n" + jSONObject.getString("errorlog"));
                    Register_Activity.this.error_dialog.show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError volleyError) {
                String onErrorResponse = new VolleyErrorHelper().onErrorResponse(volleyError, Register_Activity.this.getApplicationContext());
                Register_Activity.this.progress_dialog.cancel();
                Register_Activity.this.txt_alert.setText(onErrorResponse);
                Register_Activity.this.error_dialog.show();
                Register_Activity.this.error_dialog.getButton(-2).setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        Register_Activity.this.startActivity(new Intent(Register_Activity.this, Login_Activity.class));
                        Register_Activity.this.finish();
                        Register_Activity.this.error_dialog.cancel();
                    }
                });
            }
        }) {
            /* access modifiers changed from: protected */
            public Map<String, String> getParams() throws AuthFailureError {
                HashMap hashMap = new HashMap();
                hashMap.put("name", Register_Activity.this.final_fullname);
                hashMap.put("username", Register_Activity.this.final_username);
                hashMap.put("password", Register_Activity.this.final_password);
                hashMap.put("mobile", Register_Activity.this.final_mobile);
                hashMap.put("email", Register_Activity.this.final_email);
                hashMap.put("designation", Register_Activity.this.final_designation);
                hashMap.put("unit", Register_Activity.this.Final_Unit);
                hashMap.put("ssa", Register_Activity.this.final_ssa);
                hashMap.put("circle", Register_Activity.this.final_circle);
                hashMap.put("fms_username", Register_Activity.this.final_fms_username);
                hashMap.put("fms_firm", Register_Activity.this.final_fms_firm);
                hashMap.put("fms_teamid", Register_Activity.this.final_fms_teamid);
                hashMap.put("fms_userid", "" + Register_Activity.this.final_fms_userid);
                hashMap.put("deviceid", string);
                hashMap.put("account_status", Register_Activity.this.final_account_status);
                hashMap.put("fms_status", Register_Activity.this.final_fms_status);
                hashMap.put("bb_detail_privilege", Register_Activity.this.final_bbdetail_privilege);
                hashMap.put("tip_detail_privilege", Register_Activity.this.final_tipdetail_privilege);
                hashMap.put("nms_tip_privilege", Register_Activity.this.final_tipnms_privilege);
                hashMap.put("fms_role", Register_Activity.this.final_fms_role);
                hashMap.put("otp", str2);
                return hashMap;
            }
        };
        r1.setRetryPolicy(new DefaultRetryPolicy(BZip2Constants.BASEBLOCKSIZE, 1, 1.0f));
        newRequestQueue.add(r1);
    }

    private void User_Validate() {
        final String string = Settings.Secure.getString(getContentResolver(), "android_id");
        this.progress_dialog.show();
        RequestQueue newRequestQueue = Volley.newRequestQueue(this);
        AnonymousClass8 r1 = new StringRequest(1, getString(R.string.serverip) + "register_user_validation.php", new Response.Listener<String>() {
            public void onResponse(String str) {
                Register_Activity.this.progress_dialog.cancel();
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    if (jSONObject.getBoolean("success")) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(Register_Activity.this);
                        View inflate = Register_Activity.this.getLayoutInflater().inflate(R.layout.custom_info, (ViewGroup) null);
                        TextView textView = (TextView) inflate.findViewById(R.id.txt_update);
                        textView.setText("VERIFY-OTP");
                        builder.setCancelable(false);
                        builder.setView(inflate);
                        AlertDialog unused = Register_Activity.this.info_dialog = builder.create();
                        Register_Activity.this.info_dialog.show();
                        ((TextView) inflate.findViewById(R.id.txt_alert)).setText("INFO");
                        ((TextView) inflate.findViewById(R.id.txt_error)).setText(Html.fromHtml("An 6-Digit OTP Is Sent To Your Registered Mobile"));
                        ((TextView) inflate.findViewById(R.id.txt_cancel)).setOnClickListener(new View.OnClickListener() {
                            public void onClick(View view) {
                                Register_Activity.this.info_dialog.cancel();
                            }
                        });
                        textView.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View view) {
                                Register_Activity.this.lay_register.setVisibility(8);
                                Register_Activity.this.lay_otp.setVisibility(0);
                                Register_Activity.this.startTimer();
                                Register_Activity.this.info_dialog.cancel();
                            }
                        });
                        return;
                    }
                    Register_Activity.this.txt_alert.setText("ERROR\n\n" + jSONObject.getString("errorlog"));
                    Register_Activity.this.error_dialog.show();
                    Register_Activity.this.error_dialog.getButton(-2).setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                            Register_Activity.this.startActivity(new Intent(Register_Activity.this, Login_Activity.class));
                            Register_Activity.this.finish();
                            Register_Activity.this.error_dialog.cancel();
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError volleyError) {
                Register_Activity.this.progress_dialog.cancel();
                Register_Activity.this.txt_alert.setText(new VolleyErrorHelper().onErrorResponse(volleyError, Register_Activity.this.getApplicationContext()));
                Register_Activity.this.error_dialog.show();
                Register_Activity.this.error_dialog.getButton(-2).setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        Register_Activity.this.startActivity(new Intent(Register_Activity.this, Login_Activity.class));
                        Register_Activity.this.finish();
                        Register_Activity.this.error_dialog.cancel();
                    }
                });
            }
        }) {
            /* access modifiers changed from: protected */
            public Map<String, String> getParams() throws AuthFailureError {
                HashMap hashMap = new HashMap();
                hashMap.put("username", Register_Activity.this.final_username);
                hashMap.put("mobile", Register_Activity.this.final_mobile);
                hashMap.put("deviceid", string);
                hashMap.put("name", BuildConfig.VERSION_NAME);
                hashMap.put("code", String.valueOf(BuildConfig.VERSION_CODE));
                return hashMap;
            }
        };
        r1.setRetryPolicy(new DefaultRetryPolicy(BZip2Constants.BASEBLOCKSIZE, 1, 1.0f));
        newRequestQueue.add(r1);
    }

    /* access modifiers changed from: private */
    public void startTimer() {
        AnonymousClass9 r0 = new CountDownTimer(150000, 1000) {
            public void onTick(long j) {
                long j2 = j / 1000;
                Register_Activity.this.tv_timer.setText(String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, new Object[]{Long.valueOf(j2 / 60)}) + ParameterizedMessage.ERROR_MSG_SEPARATOR + String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, new Object[]{Long.valueOf(j2 % 60)}));
            }

            public void onFinish() {
                Register_Activity.this.txt_alert.setText("ERROR\n\nSession Expired, Please Try Again");
                Register_Activity.this.error_dialog.show();
                Register_Activity.this.error_dialog.getButton(-2).setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        Register_Activity.this.finish();
                        Register_Activity.this.overridePendingTransition(0, 0);
                        Register_Activity.this.startActivity(Register_Activity.this.getIntent());
                        Register_Activity.this.overridePendingTransition(0, 0);
                    }
                });
            }
        };
        this.cTimer = r0;
        r0.start();
    }

    public void cancelTimer() {
        CountDownTimer countDownTimer = this.cTimer;
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }

    private boolean isValidEmail(CharSequence charSequence) {
        return !TextUtils.isEmpty(charSequence) && Patterns.EMAIL_ADDRESS.matcher(charSequence).matches();
    }

    private class GenericTextWatcher implements TextWatcher {
        private final View view;

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public GenericTextWatcher(View view2) {
            this.view = view2;
        }

        public void afterTextChanged(Editable editable) {
            EditText editText;
            EditText editText2;
            boolean z = false;
            EditText editText3 = null;
            switch (this.view.getId()) {
                case R.id.et_otp1 /*2131231037*/:
                    if (Register_Activity.this.et_otp2.getText().length() == 1 && Register_Activity.this.et_otp3.getText().length() == 1 && Register_Activity.this.et_otp4.getText().length() == 1 && Register_Activity.this.et_otp5.getText().length() == 1 && Register_Activity.this.et_otp6.getText().length() == 1) {
                        z = true;
                    }
                    editText = Register_Activity.this.et_otp2;
                    break;
                case R.id.et_otp2 /*2131231038*/:
                    if (Register_Activity.this.et_otp1.getText().length() == 1 && Register_Activity.this.et_otp3.getText().length() == 1 && Register_Activity.this.et_otp4.getText().length() == 1 && Register_Activity.this.et_otp5.getText().length() == 1 && Register_Activity.this.et_otp6.getText().length() == 1) {
                        z = true;
                    }
                    editText3 = Register_Activity.this.et_otp3;
                    editText2 = Register_Activity.this.et_otp1;
                    break;
                case R.id.et_otp3 /*2131231039*/:
                    if (Register_Activity.this.et_otp1.getText().length() == 1 && Register_Activity.this.et_otp2.getText().length() == 1 && Register_Activity.this.et_otp4.getText().length() == 1 && Register_Activity.this.et_otp5.getText().length() == 1 && Register_Activity.this.et_otp6.getText().length() == 1) {
                        z = true;
                    }
                    editText3 = Register_Activity.this.et_otp4;
                    editText2 = Register_Activity.this.et_otp2;
                    break;
                case R.id.et_otp4 /*2131231040*/:
                    if (Register_Activity.this.et_otp1.getText().length() == 1 && Register_Activity.this.et_otp2.getText().length() == 1 && Register_Activity.this.et_otp3.getText().length() == 1 && Register_Activity.this.et_otp5.getText().length() == 1 && Register_Activity.this.et_otp6.getText().length() == 1) {
                        z = true;
                    }
                    editText3 = Register_Activity.this.et_otp5;
                    editText2 = Register_Activity.this.et_otp3;
                    break;
                case R.id.et_otp5 /*2131231041*/:
                    if (Register_Activity.this.et_otp1.getText().length() == 1 && Register_Activity.this.et_otp2.getText().length() == 1 && Register_Activity.this.et_otp3.getText().length() == 1 && Register_Activity.this.et_otp4.getText().length() == 1 && Register_Activity.this.et_otp6.getText().length() == 1) {
                        z = true;
                    }
                    editText3 = Register_Activity.this.et_otp6;
                    editText2 = Register_Activity.this.et_otp4;
                    break;
                case R.id.et_otp6 /*2131231042*/:
                    if (Register_Activity.this.et_otp1.getText().length() == 1 && Register_Activity.this.et_otp2.getText().length() == 1 && Register_Activity.this.et_otp3.getText().length() == 1 && Register_Activity.this.et_otp4.getText().length() == 1 && Register_Activity.this.et_otp5.getText().length() == 1) {
                        z = true;
                    }
                    editText2 = Register_Activity.this.et_otp5;
                    break;
                default:
                    editText = null;
                    break;
            }
            EditText editText4 = editText3;
            editText3 = editText2;
            editText = editText4;
            if (editable.length() > 1) {
                if (z) {
                    if (editText != null) {
                        editText.getText().clear();
                        moveToNextEdit(editText, (EditText) this.view);
                        return;
                    }
                    stayOnCurrentEdit((EditText) this.view);
                } else if (editText == null) {
                } else {
                    if (editText.getText().length() == 0) {
                        moveToNextEdit(editText, (EditText) this.view);
                    } else {
                        stayOnCurrentEdit((EditText) this.view);
                    }
                }
            } else if (editable.length() < 1 && editText3 != null) {
                moveToPreviousEdit(editText3);
            }
        }

        private void stayOnCurrentEdit(EditText editText) {
            editText.setText(editText.getText().toString().substring(0, 1));
            editText.setSelection(editText.getText().length());
        }

        private void moveToPreviousEdit(EditText editText) {
            editText.setSelection(editText.getText().length());
            editText.requestFocus();
        }

        private void moveToNextEdit(EditText editText, EditText editText2) {
            editText.setText(editText2.getText().toString().substring(1, 2));
            editText.requestFocus();
            editText.setSelection(editText.getText().length());
            editText2.setText(editText2.getText().toString().substring(0, 1));
        }
    }
}
