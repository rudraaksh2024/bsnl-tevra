package bsnl.bsnl_teevra;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.provider.Settings;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.stats.CodePackage;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.apache.poi.ss.usermodel.DateUtil;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Detail_BB_Fragment extends Fragment implements View.OnClickListener {
    /* access modifiers changed from: private */
    public Date F_Date;
    private DatePickerDialog.OnDateSetListener Fr_Date_Listener;
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
    private String Pref_designation;
    /* access modifiers changed from: private */
    public String Pref_fms_username;
    /* access modifiers changed from: private */
    public Date T_Date;
    /* access modifiers changed from: private */
    public String Telephone_number;
    private DatePickerDialog.OnDateSetListener To_Date_Listener;
    /* access modifiers changed from: private */
    public int X1;
    /* access modifiers changed from: private */
    public int X2;
    /* access modifiers changed from: private */
    public int Y1;
    /* access modifiers changed from: private */
    public int Y2;
    /* access modifiers changed from: private */
    public String androidId;
    /* access modifiers changed from: private */
    public TextView btn_detail_bb;
    /* access modifiers changed from: private */
    public float dX;
    /* access modifiers changed from: private */
    public float dY;
    /* access modifiers changed from: private */
    public String dup_ip;
    /* access modifiers changed from: private */
    public AlertDialog error_dialog;
    private EditText et_detail_bb;
    /* access modifiers changed from: private */
    public ImageView imageView;
    private ImageView img_usage_bb;
    /* access modifiers changed from: private */
    public int lastAction;
    /* access modifiers changed from: private */
    public LinearLayout lay_bbdetail_display;
    /* access modifiers changed from: private */
    public RelativeLayout lay_usage_bb;
    /* access modifiers changed from: private */
    public float newX;
    /* access modifiers changed from: private */
    public float newY;
    /* access modifiers changed from: private */
    public AlertDialog progress_dialog;
    private SharedPreferences sharedPreferences;
    /* access modifiers changed from: private */
    public TextView txt_alert;
    /* access modifiers changed from: private */
    public TextView txt_bbdetail_accountstatus;
    /* access modifiers changed from: private */
    public TextView txt_bbdetail_address;
    /* access modifiers changed from: private */
    public TextView txt_bbdetail_adslstatus;
    /* access modifiers changed from: private */
    public TextView txt_bbdetail_attainablerate;
    /* access modifiers changed from: private */
    public TextView txt_bbdetail_attenuation;
    /* access modifiers changed from: private */
    public TextView txt_bbdetail_bandwidth;
    /* access modifiers changed from: private */
    public TextView txt_bbdetail_currentrate;
    /* access modifiers changed from: private */
    public TextView txt_bbdetail_dslamport;
    /* access modifiers changed from: private */
    public TextView txt_bbdetail_mobile;
    /* access modifiers changed from: private */
    public TextView txt_bbdetail_name;
    /* access modifiers changed from: private */
    public TextView txt_bbdetail_out_amt;
    /* access modifiers changed from: private */
    public TextView txt_bbdetail_planname;
    /* access modifiers changed from: private */
    public TextView txt_bbdetail_portprofile;
    /* access modifiers changed from: private */
    public TextView txt_bbdetail_snr;
    /* access modifiers changed from: private */
    public TextView txt_bbdetail_speed;
    /* access modifiers changed from: private */
    public TextView txt_bbdetail_status;
    /* access modifiers changed from: private */
    public TextView txt_bbdetail_telephone;
    /* access modifiers changed from: private */
    public TextView txt_bbdetail_userid;
    /* access modifiers changed from: private */
    public TextView txt_bbsdetail_staicip;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.detail_bb_fragment, viewGroup, false);
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
        SharedPreferences sharedPreferences2 = getActivity().getSharedPreferences("myloginpreference", 0);
        this.sharedPreferences = sharedPreferences2;
        this.Pref_Access_Level = sharedPreferences2.getString("access_level_Key", (String) null);
        this.Pref_Username = this.sharedPreferences.getString("username_Key", (String) null);
        this.Pref_designation = this.sharedPreferences.getString("designation_Key", (String) null);
        this.Pref_fms_username = this.sharedPreferences.getString("fms_username_Key", (String) null);
        this.Pref_Randkey = this.sharedPreferences.getString("rand_Key", (String) null);
        this.Pref_Circle = this.sharedPreferences.getString("circle_Key", (String) null);
        this.Pref_SSA = this.sharedPreferences.getString("ssa_Key", (String) null);
        this.androidId = Settings.Secure.getString(getActivity().getContentResolver(), "android_id");
        this.et_detail_bb = (EditText) inflate.findViewById(R.id.et_detail_bb);
        this.btn_detail_bb = (TextView) inflate.findViewById(R.id.btn_detail_bb);
        LinearLayout linearLayout = (LinearLayout) inflate.findViewById(R.id.lay_bbdetail_display);
        this.lay_bbdetail_display = linearLayout;
        linearLayout.setVisibility(4);
        this.lay_usage_bb = (RelativeLayout) inflate.findViewById(R.id.lay_usage_bb);
        this.img_usage_bb = (ImageView) inflate.findViewById(R.id.img_usage_bb);
        this.imageView = (ImageView) getActivity().findViewById(R.id.img_header);
        this.txt_bbdetail_name = (TextView) inflate.findViewById(R.id.txt_bbdetail_name);
        this.txt_bbdetail_address = (TextView) inflate.findViewById(R.id.txt_bbdetail_address);
        this.txt_bbdetail_mobile = (TextView) inflate.findViewById(R.id.txt_bbdetail_mobile);
        this.txt_bbdetail_telephone = (TextView) inflate.findViewById(R.id.txt_bbdetail_telephone);
        this.txt_bbdetail_userid = (TextView) inflate.findViewById(R.id.txt_bbdetail_userid);
        this.txt_bbdetail_accountstatus = (TextView) inflate.findViewById(R.id.txt_bbdetail_accountstatus);
        this.txt_bbdetail_out_amt = (TextView) inflate.findViewById(R.id.txt_bbdetail_out_amt);
        this.txt_bbsdetail_staicip = (TextView) inflate.findViewById(R.id.txt_bbdetail_staicip);
        this.txt_bbdetail_planname = (TextView) inflate.findViewById(R.id.txt_bbdetail_planname);
        this.txt_bbdetail_bandwidth = (TextView) inflate.findViewById(R.id.txt_bbdetail_bandwidth);
        this.txt_bbdetail_dslamport = (TextView) inflate.findViewById(R.id.txt_bbdetail_dslamport);
        this.txt_bbdetail_adslstatus = (TextView) inflate.findViewById(R.id.txt_bbdetail_adslstatus);
        this.txt_bbdetail_portprofile = (TextView) inflate.findViewById(R.id.txt_bbdetail_portprofile);
        this.txt_bbdetail_snr = (TextView) inflate.findViewById(R.id.txt_bbdetail_snr);
        this.txt_bbdetail_attenuation = (TextView) inflate.findViewById(R.id.txt_bbdetail_attenuation);
        this.txt_bbdetail_attainablerate = (TextView) inflate.findViewById(R.id.txt_bbdetail_attainablerate);
        this.txt_bbdetail_currentrate = (TextView) inflate.findViewById(R.id.txt_bbdetail_currentrate);
        this.txt_bbdetail_status = (TextView) inflate.findViewById(R.id.txt_bbdetail_status);
        this.txt_bbdetail_speed = (TextView) inflate.findViewById(R.id.txt_bbdetail_speed);
        this.btn_detail_bb.setOnClickListener(this);
        this.img_usage_bb.setOnClickListener(this);
        this.img_usage_bb.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                float measuredHeight = (float) Detail_BB_Fragment.this.lay_usage_bb.getMeasuredHeight();
                float measuredWidth = (float) Detail_BB_Fragment.this.lay_usage_bb.getMeasuredWidth();
                int actionMasked = motionEvent.getActionMasked();
                if (actionMasked == 0) {
                    float unused = Detail_BB_Fragment.this.dX = view.getX() - motionEvent.getRawX();
                    float unused2 = Detail_BB_Fragment.this.dY = view.getY() - motionEvent.getRawY();
                    int unused3 = Detail_BB_Fragment.this.X1 = (int) motionEvent.getRawX();
                    int unused4 = Detail_BB_Fragment.this.Y1 = (int) motionEvent.getRawY();
                    int unused5 = Detail_BB_Fragment.this.lastAction = 0;
                } else if (actionMasked == 1) {
                    int unused6 = Detail_BB_Fragment.this.X2 = (int) motionEvent.getRawX();
                    int unused7 = Detail_BB_Fragment.this.Y2 = (int) motionEvent.getRawY();
                    int access$300 = Detail_BB_Fragment.this.X1 - Detail_BB_Fragment.this.X2;
                    int access$400 = Detail_BB_Fragment.this.Y1 - Detail_BB_Fragment.this.Y2;
                    if (Math.abs(access$300) < 5 || Math.abs(access$400) < 5) {
                        view.performClick();
                        return false;
                    }
                } else if (actionMasked != 2) {
                    return false;
                } else {
                    float unused8 = Detail_BB_Fragment.this.newX = motionEvent.getRawX() + Detail_BB_Fragment.this.dX;
                    float unused9 = Detail_BB_Fragment.this.newY = motionEvent.getRawY() + Detail_BB_Fragment.this.dY;
                    if (Detail_BB_Fragment.this.newX <= 0.0f || Detail_BB_Fragment.this.newX >= measuredWidth - ((float) view.getWidth()) || Detail_BB_Fragment.this.newY <= 0.0f || Detail_BB_Fragment.this.newY >= measuredHeight - ((float) view.getHeight())) {
                        int unused10 = Detail_BB_Fragment.this.lastAction = 1;
                    } else {
                        view.animate().x(Detail_BB_Fragment.this.newX).y(Detail_BB_Fragment.this.newY).setDuration(0).start();
                        int unused11 = Detail_BB_Fragment.this.lastAction = 2;
                    }
                }
                return true;
            }
        });
        return inflate;
    }

    public void onClick(View view) {
        if (view.getId() == R.id.btn_detail_bb) {
            ((InputMethodManager) getContext().getSystemService("input_method")).hideSoftInputFromWindow(view.getWindowToken(), 0);
            final String trim = this.et_detail_bb.getText().toString().trim();
            if (trim.equals("")) {
                new AlertHelperclass().ntoast("PLEASE ENTER A VALID TELEPHONE NO/USER-ID!!", getContext());
                return;
            }
            this.imageView.setEnabled(false);
            this.lay_bbdetail_display.setVisibility(8);
            this.lay_usage_bb.setVisibility(8);
            this.progress_dialog.show();
            if (this.Pref_designation.equals("FRANCHISEE OWNER") || this.Pref_designation.equals("FRANCHISEE MEMBER")) {
                RequestQueue newRequestQueue = Volley.newRequestQueue(getActivity());
                AnonymousClass4 r0 = new StringRequest(1, getString(R.string.serverip) + "fms/fms_phone_validate_new.php", new Response.Listener<String>() {
                    public void onResponse(String str) {
                        try {
                            if (new JSONObject(str).getString("STATUS").equals("TRUE")) {
                                Detail_BB_Fragment.this.bb_number_detail(trim);
                                return;
                            }
                            Detail_BB_Fragment.this.progress_dialog.cancel();
                            Detail_BB_Fragment.this.txt_alert.setText("Access Restricted !!!\nThe Telephone No '" + trim + "' Doesn't Belongs To Your FMS Group");
                            Detail_BB_Fragment.this.error_dialog.show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError volleyError) {
                        Detail_BB_Fragment.this.progress_dialog.cancel();
                        Detail_BB_Fragment.this.txt_alert.setText(new VolleyErrorHelper().onErrorResponse(volleyError, Detail_BB_Fragment.this.getContext()));
                        Detail_BB_Fragment.this.error_dialog.show();
                    }
                }) {
                    /* access modifiers changed from: protected */
                    public Map<String, String> getParams() throws AuthFailureError {
                        HashMap hashMap = new HashMap();
                        hashMap.put("phone_no", trim);
                        hashMap.put("fms_userid", Detail_BB_Fragment.this.Pref_fms_username);
                        hashMap.put("circle", Detail_BB_Fragment.this.Pref_Circle);
                        hashMap.put("ssa", Detail_BB_Fragment.this.Pref_SSA);
                        hashMap.put("username", Detail_BB_Fragment.this.Pref_Username);
                        hashMap.put("random_key", Detail_BB_Fragment.this.Pref_Randkey);
                        hashMap.put("device_id", Detail_BB_Fragment.this.androidId);
                        return hashMap;
                    }
                };
                r0.setRetryPolicy(new DefaultRetryPolicy(BZip2Constants.BASEBLOCKSIZE, 1, 1.0f));
                newRequestQueue.add(r0);
                return;
            }
            bb_number_detail(trim);
        } else if (view.getId() == R.id.img_usage_bb) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            View inflate = getLayoutInflater().inflate(R.layout.custom_alert_usage, (ViewGroup) null);
            builder.setCancelable(false);
            builder.setView(inflate);
            final AlertDialog create = builder.create();
            final LinearLayout linearLayout = (LinearLayout) inflate.findViewById(R.id.lay_custom_alert);
            RelativeLayout relativeLayout = (RelativeLayout) inflate.findViewById(R.id.lay_custom_alert_loading);
            relativeLayout.setVisibility(8);
            final TextView textView = (TextView) inflate.findViewById(R.id.txt_alert);
            final TextView textView2 = (TextView) inflate.findViewById(R.id.txt_fromdate);
            final TextView textView3 = (TextView) inflate.findViewById(R.id.txt_todate);
            TextView textView4 = (TextView) inflate.findViewById(R.id.txt_submit);
            TextView textView5 = (TextView) inflate.findViewById(R.id.txt_cancel);
            textView.setText("UNBILLED USAGE FOR " + this.Telephone_number);
            Calendar instance = Calendar.getInstance();
            instance.get(1);
            instance.get(2);
            instance.get(5);
            textView2.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Calendar instance = Calendar.getInstance();
                    DatePickerDialog datePickerDialog = new DatePickerDialog(Detail_BB_Fragment.this.getActivity(), new DatePickerDialog.OnDateSetListener() {
                        public void onDateSet(DatePicker datePicker, int i, int i2, int i3) {
                            int i4 = i2 + 1;
                            String.format("%d-%02d-%02d", new Object[]{Integer.valueOf(i), Integer.valueOf(i4), Integer.valueOf(i3)});
                            textView2.setText(String.format("%02d/%02d/%d", new Object[]{Integer.valueOf(i3), Integer.valueOf(i4), Integer.valueOf(i)}));
                        }
                    }, instance.get(1), instance.get(2), instance.get(5));
                    datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis() - DateUtil.DAY_MILLISECONDS);
                    datePickerDialog.show();
                }
            });
            textView3.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Calendar instance = Calendar.getInstance();
                    DatePickerDialog datePickerDialog = new DatePickerDialog(Detail_BB_Fragment.this.getActivity(), new DatePickerDialog.OnDateSetListener() {
                        public void onDateSet(DatePicker datePicker, int i, int i2, int i3) {
                            textView3.setText(String.format("%02d/%02d/%d", new Object[]{Integer.valueOf(i3), Integer.valueOf(i2 + 1), Integer.valueOf(i)}));
                        }
                    }, instance.get(1), instance.get(2), instance.get(5));
                    datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis() - DateUtil.DAY_MILLISECONDS);
                    datePickerDialog.show();
                }
            });
            final TextView textView6 = textView4;
            final TextView textView7 = textView5;
            final RelativeLayout relativeLayout2 = relativeLayout;
            final AlertDialog alertDialog = create;
            textView4.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    final String trim = textView2.getText().toString().trim();
                    final String trim2 = textView3.getText().toString().trim();
                    final String access$2000 = Detail_BB_Fragment.this.Telephone_number;
                    if (trim.equals("")) {
                        new AlertHelperclass().ntoast("PLEASE SELECT A VALID FROM DATE", Detail_BB_Fragment.this.getContext());
                    } else if (trim2.equals("")) {
                        new AlertHelperclass().ntoast("PLEASE SELECT A VALID FROM DATE", Detail_BB_Fragment.this.getContext());
                    } else {
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                        try {
                            Date unused = Detail_BB_Fragment.this.F_Date = simpleDateFormat.parse(trim);
                            Date unused2 = Detail_BB_Fragment.this.T_Date = simpleDateFormat.parse(trim2);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        if (Detail_BB_Fragment.this.F_Date.after(Detail_BB_Fragment.this.T_Date)) {
                            new AlertHelperclass().ntoast("FROM-DATE CANNOT BE AFTER TO-DATE", Detail_BB_Fragment.this.getContext());
                            return;
                        }
                        textView2.setEnabled(false);
                        textView3.setEnabled(false);
                        textView6.setEnabled(false);
                        textView7.setEnabled(false);
                        linearLayout.setAlpha(0.5f);
                        relativeLayout2.setVisibility(0);
                        RequestQueue newRequestQueue = Volley.newRequestQueue(Detail_BB_Fragment.this.getActivity());
                        AnonymousClass3 r0 = new StringRequest(1, Detail_BB_Fragment.this.getActivity().getString(R.string.serverip) + "usage.php", new Response.Listener<String>() {
                            public void onResponse(String str) {
                                try {
                                    JSONObject jSONObject = new JSONObject(str);
                                    if (jSONObject.getBoolean("SUCCESS")) {
                                        String string = jSONObject.getString("BA");
                                        String string2 = jSONObject.getString("USERID");
                                        String string3 = jSONObject.getString("TOTAL_VOL");
                                        AlertDialog.Builder builder = new AlertDialog.Builder(Detail_BB_Fragment.this.getActivity());
                                        View inflate = Detail_BB_Fragment.this.getLayoutInflater().inflate(R.layout.custom_alert_usage_display, (ViewGroup) null);
                                        builder.setCancelable(false);
                                        builder.setView(inflate);
                                        final AlertDialog create = builder.create();
                                        TextView textView = (TextView) inflate.findViewById(R.id.txt_alert2);
                                        ((TextView) inflate.findViewById(R.id.txt_ok)).setOnClickListener(new View.OnClickListener() {
                                            public void onClick(View view) {
                                                create.cancel();
                                            }
                                        });
                                        ((TextView) inflate.findViewById(R.id.txt_alert1)).setText("UNBILLED USAGE FOR " + Detail_BB_Fragment.this.Telephone_number);
                                        textView.setText("PERIOD " + trim + " TO " + trim2);
                                        textView.setPaintFlags(8);
                                        ((TextView) inflate.findViewById(R.id.txt_ba)).setText(string);
                                        ((TextView) inflate.findViewById(R.id.txt_bb_uid)).setText(string2);
                                        ((TextView) inflate.findViewById(R.id.txt_total_val)).setText(string3);
                                        create.show();
                                        alertDialog.cancel();
                                        return;
                                    }
                                    new AlertHelperclass().ntoast("PLEASE TRY AFTER SOMETIME", Detail_BB_Fragment.this.getContext());
                                    alertDialog.cancel();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {
                            public void onErrorResponse(VolleyError volleyError) {
                                String onErrorResponse = new VolleyErrorHelper().onErrorResponse(volleyError, Detail_BB_Fragment.this.getContext());
                                alertDialog.cancel();
                                textView.setText(onErrorResponse);
                                Detail_BB_Fragment.this.error_dialog.show();
                            }
                        }) {
                            /* access modifiers changed from: protected */
                            public Map<String, String> getParams() throws AuthFailureError {
                                HashMap hashMap = new HashMap();
                                hashMap.put("TelePhone", access$2000);
                                hashMap.put("From_Date", trim);
                                hashMap.put("To_Date", trim2);
                                hashMap.put("username", Detail_BB_Fragment.this.Pref_Username);
                                hashMap.put("random_key", Detail_BB_Fragment.this.Pref_Randkey);
                                hashMap.put("device_id", Detail_BB_Fragment.this.androidId);
                                return hashMap;
                            }
                        };
                        r0.setRetryPolicy(new DefaultRetryPolicy(BZip2Constants.BASEBLOCKSIZE, 1, 1.0f));
                        newRequestQueue.add(r0);
                    }
                }
            });
            textView5.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    create.cancel();
                }
            });
            create.show();
        }
    }

    /* access modifiers changed from: private */
    public void bb_number_detail(String str) {
        final String trim = str.trim();
        RequestQueue newRequestQueue = Volley.newRequestQueue(getActivity());
        AnonymousClass11 r0 = new StringRequest(1, getString(R.string.serverip) + "detail_bb01.php", new Response.Listener<String>() {
            public void onResponse(String str) {
                String str2 = CodePackage.LOCATION;
                Detail_BB_Fragment.this.progress_dialog.cancel();
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    if (!jSONObject.getBoolean("is_connect")) {
                        Detail_BB_Fragment.this.txt_alert.setText(Html.fromHtml(jSONObject.getString("error_log")));
                        Detail_BB_Fragment.this.error_dialog.show();
                    } else if (jSONObject.getBoolean("success")) {
                        Detail_BB_Fragment.this.txt_bbdetail_name.setText(jSONObject.getString("customer_name"));
                        Detail_BB_Fragment.this.txt_bbdetail_address.setText(jSONObject.getString("customer_address"));
                        Detail_BB_Fragment.this.txt_bbdetail_mobile.setText(jSONObject.getString("customer_mobile"));
                        Detail_BB_Fragment.this.txt_bbdetail_out_amt.setText(Html.fromHtml(jSONObject.getString("customer_outstanding")));
                        Detail_BB_Fragment.this.txt_bbdetail_telephone.setText(jSONObject.getString("bb_tele"));
                        String unused = Detail_BB_Fragment.this.Telephone_number = jSONObject.getString("bb_tele").trim();
                        Detail_BB_Fragment.this.txt_bbdetail_userid.setText(jSONObject.getString("bb_userid"));
                        Detail_BB_Fragment.this.txt_bbdetail_accountstatus.setText(Html.fromHtml(jSONObject.getString("bb_account")));
                        Detail_BB_Fragment.this.txt_bbsdetail_staicip.setText(jSONObject.getString("bb_staticip"));
                        Detail_BB_Fragment.this.txt_bbdetail_planname.setText(jSONObject.getString("bb_planname"));
                        Detail_BB_Fragment.this.txt_bbdetail_bandwidth.setText(jSONObject.getString("bb_bandwidth"));
                        Detail_BB_Fragment.this.txt_bbdetail_dslamport.setText(Html.fromHtml(jSONObject.getString("bb_dslam_port")));
                        Detail_BB_Fragment.this.txt_bbdetail_adslstatus.setText(Html.fromHtml(jSONObject.getString("bb_adsl_status")));
                        Detail_BB_Fragment.this.txt_bbdetail_portprofile.setText(Html.fromHtml(jSONObject.getString("bb_port_profile")));
                        Detail_BB_Fragment.this.txt_bbdetail_snr.setText(Html.fromHtml(jSONObject.getString("bb_port_snr")));
                        Detail_BB_Fragment.this.txt_bbdetail_attenuation.setText(Html.fromHtml(jSONObject.getString("bb_port_atn")));
                        Detail_BB_Fragment.this.txt_bbdetail_attainablerate.setText(Html.fromHtml(jSONObject.getString("bb_port_attrate")));
                        Detail_BB_Fragment.this.txt_bbdetail_currentrate.setText(Html.fromHtml(jSONObject.getString("bb_port_currate")));
                        Detail_BB_Fragment.this.txt_bbdetail_status.setText(Html.fromHtml(jSONObject.getString("bb_bngstatus")));
                        Detail_BB_Fragment.this.txt_bbdetail_speed.setText(Html.fromHtml(jSONObject.getString("bb_bngspeed")));
                        boolean z = jSONObject.getBoolean("db_error");
                        boolean z2 = jSONObject.getBoolean("authorization_error");
                        boolean z3 = jSONObject.getBoolean("duplicate_vlan");
                        if (z) {
                            Detail_BB_Fragment.this.lay_bbdetail_display.setVisibility(0);
                            Detail_BB_Fragment.this.lay_usage_bb.setVisibility(4);
                            Detail_BB_Fragment.this.txt_alert.setText(Html.fromHtml(jSONObject.getString("error_log")));
                            Detail_BB_Fragment.this.error_dialog.show();
                        } else if (z2) {
                            Detail_BB_Fragment.this.lay_bbdetail_display.setVisibility(4);
                            Detail_BB_Fragment.this.lay_usage_bb.setVisibility(4);
                            Detail_BB_Fragment.this.txt_alert.setText(Html.fromHtml(jSONObject.getString("error_log")));
                            Detail_BB_Fragment.this.error_dialog.show();
                        } else if (!z3) {
                            Detail_BB_Fragment.this.lay_bbdetail_display.setVisibility(0);
                            Detail_BB_Fragment.this.lay_usage_bb.setVisibility(0);
                        } else {
                            JSONArray jSONArray = jSONObject.getJSONArray("dslam-info");
                            AlertDialog.Builder builder = new AlertDialog.Builder(Detail_BB_Fragment.this.getActivity());
                            View inflate = Detail_BB_Fragment.this.getLayoutInflater().inflate(R.layout.custom_alert_dialog, (ViewGroup) null);
                            builder.setCancelable(false);
                            builder.setView(inflate);
                            final AlertDialog create = builder.create();
                            TextView textView = (TextView) inflate.findViewById(R.id.txt_alert);
                            TextView textView2 = (TextView) inflate.findViewById(R.id.txt_cancel);
                            final TableLayout tableLayout = (TableLayout) inflate.findViewById(R.id.tbl_dup_vlan);
                            TableRow tableRow = new TableRow(Detail_BB_Fragment.this.getActivity());
                            tableRow.setLayoutParams(new TableRow.LayoutParams(-1, -1));
                            TextView textView3 = new TextView(Detail_BB_Fragment.this.getActivity());
                            textView3.setLayoutParams(new TableRow.LayoutParams(0, -1, 1.0f));
                            textView3.setText("SR");
                            textView3.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                            textView3.setGravity(17);
                            textView3.setPadding(15, 15, 15, 15);
                            textView3.setTypeface((Typeface) null, 1);
                            textView3.setTextSize(0, Detail_BB_Fragment.this.getResources().getDimension(R.dimen.smalltext));
                            textView3.setBackgroundResource(R.drawable.style17);
                            TextView textView4 = new TextView(Detail_BB_Fragment.this.getActivity());
                            textView4.setLayoutParams(new TableRow.LayoutParams(0, -1, 3.0f));
                            textView4.setText("EXCHANGE");
                            textView4.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                            textView4.setGravity(17);
                            textView4.setPadding(15, 15, 15, 15);
                            textView4.setTypeface((Typeface) null, 1);
                            textView4.setTextSize(0, Detail_BB_Fragment.this.getResources().getDimension(R.dimen.smalltext));
                            textView4.setBackgroundResource(R.drawable.style17);
                            TextView textView5 = new TextView(Detail_BB_Fragment.this.getActivity());
                            textView5.setLayoutParams(new TableRow.LayoutParams(0, -1, 3.0f));
                            textView5.setText(str2);
                            textView5.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                            textView5.setGravity(17);
                            textView5.setPadding(15, 15, 15, 15);
                            textView5.setTypeface((Typeface) null, 1);
                            textView5.setTextSize(0, Detail_BB_Fragment.this.getResources().getDimension(R.dimen.smalltext));
                            textView5.setBackgroundResource(R.drawable.style17);
                            TextView textView6 = new TextView(Detail_BB_Fragment.this.getActivity());
                            TextView textView7 = (TextView) inflate.findViewById(R.id.txt_submit);
                            textView6.setLayoutParams(new TableRow.LayoutParams(0, -1, 3.0f));
                            textView6.setText("DSLAM-IP");
                            textView6.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                            textView6.setGravity(17);
                            textView6.setPadding(15, 15, 15, 15);
                            textView6.setTypeface((Typeface) null, 1);
                            textView6.setTextSize(0, Detail_BB_Fragment.this.getResources().getDimension(R.dimen.smalltext));
                            textView6.setBackgroundResource(R.drawable.style17);
                            tableRow.addView(textView3);
                            tableRow.addView(textView4);
                            tableRow.addView(textView5);
                            tableRow.addView(textView6);
                            tableLayout.addView(tableRow);
                            int i = 0;
                            while (i < jSONArray.length()) {
                                int i2 = i + 1;
                                JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                                textView.setText("DUPLICATE DSLAM INVENTORY (VLAN " + jSONObject2.getString("DSLAM_VLAN") + ")");
                                TableRow tableRow2 = new TableRow(Detail_BB_Fragment.this.getActivity());
                                tableRow2.setLayoutParams(new TableRow.LayoutParams(-1, -1));
                                final RadioButton radioButton = new RadioButton(Detail_BB_Fragment.this.getActivity());
                                radioButton.setLayoutParams(new TableRow.LayoutParams(0, -2, 1.0f));
                                radioButton.setPadding(5, 15, 5, 15);
                                radioButton.setBackgroundResource(R.drawable.style17);
                                TextView textView8 = new TextView(Detail_BB_Fragment.this.getActivity());
                                textView8.setLayoutParams(new TableRow.LayoutParams(0, -1, 3.0f));
                                textView8.setText(jSONObject2.getString("EXGCODE"));
                                textView8.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                                textView8.setGravity(17);
                                textView8.setPadding(5, 15, 5, 15);
                                textView8.setTextSize(0, Detail_BB_Fragment.this.getResources().getDimension(R.dimen.smalltext));
                                textView8.setBackgroundResource(R.drawable.style17);
                                TextView textView9 = new TextView(Detail_BB_Fragment.this.getActivity());
                                JSONArray jSONArray2 = jSONArray;
                                textView9.setLayoutParams(new TableRow.LayoutParams(0, -1, 3.0f));
                                textView9.setText(jSONObject2.getString(str2));
                                textView9.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                                textView9.setGravity(17);
                                textView9.setPadding(5, 15, 5, 15);
                                textView9.setTextSize(0, Detail_BB_Fragment.this.getResources().getDimension(R.dimen.smalltext));
                                textView9.setBackgroundResource(R.drawable.style17);
                                final TextView textView10 = new TextView(Detail_BB_Fragment.this.getActivity());
                                textView10.setLayoutParams(new TableRow.LayoutParams(0, -1, 3.0f));
                                textView10.setText(jSONObject2.getString("DSLAM_IP"));
                                textView10.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                                textView10.setGravity(17);
                                textView10.setPadding(5, 15, 5, 15);
                                textView10.setTextSize(0, Detail_BB_Fragment.this.getResources().getDimension(R.dimen.smalltext));
                                textView10.setBackgroundResource(R.drawable.style17);
                                tableRow2.addView(radioButton);
                                tableRow2.addView(textView8);
                                tableRow2.addView(textView9);
                                tableRow2.addView(textView10);
                                tableLayout.addView(tableRow2);
                                String unused2 = Detail_BB_Fragment.this.dup_ip = "NULL";
                                radioButton.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View view) {
                                        for (int i = 1; i < tableLayout.getChildCount(); i++) {
                                            ((RadioButton) ((TableRow) tableLayout.getChildAt(i)).getChildAt(0)).setChecked(false);
                                        }
                                        radioButton.setChecked(true);
                                        String unused = Detail_BB_Fragment.this.dup_ip = textView10.getText().toString().trim();
                                        new AlertHelperclass().ptoast("Selected DSLAM-IP : " + Detail_BB_Fragment.this.dup_ip, Detail_BB_Fragment.this.getContext());
                                    }
                                });
                                textView2.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View view) {
                                        create.cancel();
                                    }
                                });
                                TextView textView11 = textView7;
                                textView11.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View view) {
                                        if (Detail_BB_Fragment.this.dup_ip == "NULL") {
                                            new AlertHelperclass().ntoast("Please Select Correct DSLAM", Detail_BB_Fragment.this.getActivity());
                                            return;
                                        }
                                        Detail_BB_Fragment.this.bb_number_detail01(trim, Detail_BB_Fragment.this.dup_ip);
                                        create.cancel();
                                    }
                                });
                                i = i2;
                                textView7 = textView11;
                                jSONArray = jSONArray2;
                                str2 = str2;
                            }
                            create.show();
                        }
                    } else {
                        Detail_BB_Fragment.this.txt_alert.setText(Html.fromHtml(jSONObject.getString("error_log")));
                        Detail_BB_Fragment.this.error_dialog.show();
                    }
                    Detail_BB_Fragment.this.imageView.setEnabled(true);
                    Detail_BB_Fragment.this.btn_detail_bb.setEnabled(true);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError volleyError) {
                Detail_BB_Fragment.this.progress_dialog.cancel();
                Detail_BB_Fragment.this.txt_alert.setText(new VolleyErrorHelper().onErrorResponse(volleyError, Detail_BB_Fragment.this.getContext()));
                Detail_BB_Fragment.this.error_dialog.show();
            }
        }) {
            /* access modifiers changed from: protected */
            public Map<String, String> getParams() throws AuthFailureError {
                HashMap hashMap = new HashMap();
                hashMap.put("userid", trim);
                hashMap.put("access_level", Detail_BB_Fragment.this.Pref_Access_Level);
                hashMap.put("circle", Detail_BB_Fragment.this.Pref_Circle);
                hashMap.put("ssa", Detail_BB_Fragment.this.Pref_SSA);
                hashMap.put("username", Detail_BB_Fragment.this.Pref_Username);
                hashMap.put("random_key", Detail_BB_Fragment.this.Pref_Randkey);
                hashMap.put("device_id", Detail_BB_Fragment.this.androidId);
                return hashMap;
            }
        };
        r0.setRetryPolicy(new DefaultRetryPolicy(BZip2Constants.BASEBLOCKSIZE, 1, 1.0f));
        newRequestQueue.add(r0);
    }

    /* access modifiers changed from: private */
    public void bb_number_detail01(String str, String str2) {
        this.progress_dialog.show();
        final String trim = str.trim();
        final String trim2 = str2.trim();
        RequestQueue newRequestQueue = Volley.newRequestQueue(getActivity());
        AnonymousClass14 r1 = new StringRequest(1, getString(R.string.serverip) + "detail_bb_ipbased.php", new Response.Listener<String>() {
            public void onResponse(String str) {
                Detail_BB_Fragment.this.progress_dialog.cancel();
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    if (!jSONObject.getBoolean("is_connect")) {
                        Detail_BB_Fragment.this.txt_alert.setText(Html.fromHtml(jSONObject.getString("error_log")));
                        Detail_BB_Fragment.this.error_dialog.show();
                    } else if (jSONObject.getBoolean("success")) {
                        Detail_BB_Fragment.this.txt_bbdetail_name.setText(jSONObject.getString("customer_name"));
                        Detail_BB_Fragment.this.txt_bbdetail_address.setText(jSONObject.getString("customer_address"));
                        Detail_BB_Fragment.this.txt_bbdetail_mobile.setText(jSONObject.getString("customer_mobile"));
                        Detail_BB_Fragment.this.txt_bbdetail_out_amt.setText(Html.fromHtml(jSONObject.getString("customer_outstanding")));
                        Detail_BB_Fragment.this.txt_bbdetail_telephone.setText(jSONObject.getString("bb_tele"));
                        String unused = Detail_BB_Fragment.this.Telephone_number = jSONObject.getString("bb_tele").trim();
                        Detail_BB_Fragment.this.txt_bbdetail_userid.setText(jSONObject.getString("bb_userid"));
                        Detail_BB_Fragment.this.txt_bbdetail_accountstatus.setText(Html.fromHtml(jSONObject.getString("bb_account")));
                        Detail_BB_Fragment.this.txt_bbsdetail_staicip.setText(jSONObject.getString("bb_staticip"));
                        Detail_BB_Fragment.this.txt_bbdetail_planname.setText(jSONObject.getString("bb_planname"));
                        Detail_BB_Fragment.this.txt_bbdetail_bandwidth.setText(jSONObject.getString("bb_bandwidth"));
                        Detail_BB_Fragment.this.txt_bbdetail_dslamport.setText(Html.fromHtml(jSONObject.getString("bb_dslam_port")));
                        Detail_BB_Fragment.this.txt_bbdetail_adslstatus.setText(Html.fromHtml(jSONObject.getString("bb_adsl_status")));
                        Detail_BB_Fragment.this.txt_bbdetail_portprofile.setText(Html.fromHtml(jSONObject.getString("bb_port_profile")));
                        Detail_BB_Fragment.this.txt_bbdetail_snr.setText(Html.fromHtml(jSONObject.getString("bb_port_snr")));
                        Detail_BB_Fragment.this.txt_bbdetail_attenuation.setText(Html.fromHtml(jSONObject.getString("bb_port_atn")));
                        Detail_BB_Fragment.this.txt_bbdetail_attainablerate.setText(Html.fromHtml(jSONObject.getString("bb_port_attrate")));
                        Detail_BB_Fragment.this.txt_bbdetail_currentrate.setText(Html.fromHtml(jSONObject.getString("bb_port_currate")));
                        Detail_BB_Fragment.this.txt_bbdetail_status.setText(Html.fromHtml(jSONObject.getString("bb_bngstatus")));
                        Detail_BB_Fragment.this.txt_bbdetail_speed.setText(Html.fromHtml(jSONObject.getString("bb_bngspeed")));
                        Detail_BB_Fragment.this.lay_bbdetail_display.setVisibility(0);
                        Detail_BB_Fragment.this.lay_usage_bb.setVisibility(0);
                    } else {
                        Detail_BB_Fragment.this.txt_alert.setText(Html.fromHtml(jSONObject.getString("error_log")));
                        Detail_BB_Fragment.this.error_dialog.show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError volleyError) {
                Detail_BB_Fragment.this.progress_dialog.cancel();
                Detail_BB_Fragment.this.txt_alert.setText(new VolleyErrorHelper().onErrorResponse(volleyError, Detail_BB_Fragment.this.getContext()));
                Detail_BB_Fragment.this.error_dialog.show();
            }
        }) {
            /* access modifiers changed from: protected */
            public Map<String, String> getParams() throws AuthFailureError {
                HashMap hashMap = new HashMap();
                hashMap.put("userid", trim);
                hashMap.put("dslamip", trim2);
                hashMap.put("username", Detail_BB_Fragment.this.Pref_Username);
                hashMap.put("random_key", Detail_BB_Fragment.this.Pref_Randkey);
                hashMap.put("device_id", Detail_BB_Fragment.this.androidId);
                return hashMap;
            }
        };
        r1.setRetryPolicy(new DefaultRetryPolicy(BZip2Constants.BASEBLOCKSIZE, 1, 1.0f));
        newRequestQueue.add(r1);
    }

    private void bb_number_detail1(String str) {
        final String trim = str.trim();
        RequestQueue newRequestQueue = Volley.newRequestQueue(getActivity());
        AnonymousClass17 r0 = new StringRequest(1, getString(R.string.serverip) + "detail_bb.php", new Response.Listener<String>() {
            public void onResponse(String str) {
                Detail_BB_Fragment.this.progress_dialog.cancel();
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    if (!jSONObject.getBoolean("is_connect")) {
                        Detail_BB_Fragment.this.txt_alert.setText(jSONObject.getString("error_log"));
                        Detail_BB_Fragment.this.error_dialog.show();
                    } else if (jSONObject.getBoolean("success")) {
                        Detail_BB_Fragment.this.txt_bbdetail_name.setText(jSONObject.getString("customer_name"));
                        Detail_BB_Fragment.this.txt_bbdetail_address.setText(jSONObject.getString("customer_address"));
                        Detail_BB_Fragment.this.txt_bbdetail_mobile.setText(jSONObject.getString("customer_mobile"));
                        Detail_BB_Fragment.this.txt_bbdetail_out_amt.setText(Html.fromHtml(jSONObject.getString("customer_outstanding")));
                        Detail_BB_Fragment.this.txt_bbdetail_telephone.setText(jSONObject.getString("bb_tele"));
                        String unused = Detail_BB_Fragment.this.Telephone_number = jSONObject.getString("bb_tele").trim();
                        Detail_BB_Fragment.this.txt_bbdetail_userid.setText(jSONObject.getString("bb_userid"));
                        Detail_BB_Fragment.this.txt_bbdetail_accountstatus.setText(Html.fromHtml(jSONObject.getString("bb_account")));
                        Detail_BB_Fragment.this.txt_bbsdetail_staicip.setText(jSONObject.getString("bb_staticip"));
                        Detail_BB_Fragment.this.txt_bbdetail_planname.setText(jSONObject.getString("bb_planname"));
                        Detail_BB_Fragment.this.txt_bbdetail_bandwidth.setText(jSONObject.getString("bb_bandwidth"));
                        Detail_BB_Fragment.this.txt_bbdetail_dslamport.setText(Html.fromHtml(jSONObject.getString("bb_dslam_port")));
                        Detail_BB_Fragment.this.txt_bbdetail_adslstatus.setText(Html.fromHtml(jSONObject.getString("bb_adsl_status")));
                        Detail_BB_Fragment.this.txt_bbdetail_portprofile.setText(Html.fromHtml(jSONObject.getString("bb_port_profile")));
                        Detail_BB_Fragment.this.txt_bbdetail_snr.setText(Html.fromHtml(jSONObject.getString("bb_port_snr")));
                        Detail_BB_Fragment.this.txt_bbdetail_attenuation.setText(Html.fromHtml(jSONObject.getString("bb_port_atn")));
                        Detail_BB_Fragment.this.txt_bbdetail_attainablerate.setText(Html.fromHtml(jSONObject.getString("bb_port_attrate")));
                        Detail_BB_Fragment.this.txt_bbdetail_currentrate.setText(Html.fromHtml(jSONObject.getString("bb_port_currate")));
                        Detail_BB_Fragment.this.txt_bbdetail_status.setText(Html.fromHtml(jSONObject.getString("bb_bngstatus")));
                        Detail_BB_Fragment.this.txt_bbdetail_speed.setText(Html.fromHtml(jSONObject.getString("bb_bngspeed")));
                        boolean z = jSONObject.getBoolean("db_error");
                        boolean z2 = jSONObject.getBoolean("authorization_error");
                        if (z) {
                            Detail_BB_Fragment.this.lay_bbdetail_display.setVisibility(0);
                            Detail_BB_Fragment.this.lay_usage_bb.setVisibility(0);
                            Detail_BB_Fragment.this.txt_alert.setText(jSONObject.getString("error_log"));
                            Detail_BB_Fragment.this.error_dialog.show();
                        } else if (!z2) {
                            Detail_BB_Fragment.this.lay_bbdetail_display.setVisibility(0);
                            Detail_BB_Fragment.this.lay_usage_bb.setVisibility(0);
                        } else {
                            Detail_BB_Fragment.this.lay_bbdetail_display.setVisibility(4);
                            Detail_BB_Fragment.this.lay_usage_bb.setVisibility(4);
                            Detail_BB_Fragment.this.txt_alert.setText(jSONObject.getString("error_log"));
                            Detail_BB_Fragment.this.error_dialog.show();
                        }
                    } else {
                        Detail_BB_Fragment.this.txt_alert.setText(jSONObject.getString("error_log"));
                        Detail_BB_Fragment.this.error_dialog.show();
                    }
                    Detail_BB_Fragment.this.imageView.setEnabled(true);
                    Detail_BB_Fragment.this.btn_detail_bb.setEnabled(true);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError volleyError) {
                Detail_BB_Fragment.this.progress_dialog.cancel();
                Detail_BB_Fragment.this.txt_alert.setText(new VolleyErrorHelper().onErrorResponse(volleyError, Detail_BB_Fragment.this.getContext()));
                Detail_BB_Fragment.this.error_dialog.show();
            }
        }) {
            /* access modifiers changed from: protected */
            public Map<String, String> getParams() throws AuthFailureError {
                HashMap hashMap = new HashMap();
                hashMap.put("userid", trim);
                hashMap.put("access_level", Detail_BB_Fragment.this.Pref_Access_Level);
                hashMap.put("circle", Detail_BB_Fragment.this.Pref_Circle);
                hashMap.put("ssa", Detail_BB_Fragment.this.Pref_SSA);
                hashMap.put("username", Detail_BB_Fragment.this.Pref_Username);
                hashMap.put("random_key", Detail_BB_Fragment.this.Pref_Randkey);
                hashMap.put("device_id", Detail_BB_Fragment.this.androidId);
                return hashMap;
            }
        };
        r0.setRetryPolicy(new DefaultRetryPolicy(BZip2Constants.BASEBLOCKSIZE, 1, 1.0f));
        newRequestQueue.add(r0);
    }
}
