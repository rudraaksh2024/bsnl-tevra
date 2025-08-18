package bsnl.bsnl_teevra;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Bundle;
import android.provider.Settings;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.codec.language.bm.Rule;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class NMS_TIP_Fragment extends Fragment implements View.OnClickListener {
    /* access modifiers changed from: private */
    public String Pref_Access_Level;
    /* access modifiers changed from: private */
    public String Pref_Circle;
    /* access modifiers changed from: private */
    public String Pref_Designation;
    /* access modifiers changed from: private */
    public String Pref_Randkey;
    /* access modifiers changed from: private */
    public String Pref_Role;
    /* access modifiers changed from: private */
    public String Pref_SSA;
    /* access modifiers changed from: private */
    public String Pref_Username;
    private String Pref_fms_firmname;
    /* access modifiers changed from: private */
    public String Pref_fms_username;
    /* access modifiers changed from: private */
    public ArrayAdapter<String> adapter_circle;
    /* access modifiers changed from: private */
    public ArrayAdapter<String> adapter_fms_username;
    /* access modifiers changed from: private */
    public ArrayAdapter<String> adapter_ssa;
    /* access modifiers changed from: private */
    public ArrayAdapter<String> adapter_vendor;
    /* access modifiers changed from: private */
    public String androidId;
    private TextView btn_nms_tip;
    /* access modifiers changed from: private */
    public ArrayList<String> circle_element;
    /* access modifiers changed from: private */
    public AlertDialog error_dialog;
    /* access modifiers changed from: private */
    public ArrayList<String> fms_firmaname;
    /* access modifiers changed from: private */
    public ArrayList<String> fms_username;
    private ImageView imageView;
    /* access modifiers changed from: private */
    public LinearLayout lay_nms_tip;
    /* access modifiers changed from: private */
    public AlertDialog progress_dialog;
    private SharedPreferences sharedPreferences;
    /* access modifiers changed from: private */
    public Spinner sp_circle;
    /* access modifiers changed from: private */
    public Spinner sp_nms_vendor;
    /* access modifiers changed from: private */
    public Spinner sp_ssa;
    /* access modifiers changed from: private */
    public ArrayList<String> ssa_element;
    /* access modifiers changed from: private */
    public TableLayout tbl_nms_tip;
    /* access modifiers changed from: private */
    public TextView txt_alert;
    private TextView txt_nms_header;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.nms_tip_fragment, viewGroup, false);
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
        final String str = getString(R.string.serverip) + "tip_vendor_populate_new.php";
        SharedPreferences sharedPreferences2 = getActivity().getSharedPreferences("myloginpreference", 0);
        this.sharedPreferences = sharedPreferences2;
        this.Pref_Role = sharedPreferences2.getString("role_Key", (String) null);
        this.Pref_Access_Level = this.sharedPreferences.getString("access_level_Key", (String) null);
        this.Pref_Circle = this.sharedPreferences.getString("circle_Key", (String) null);
        this.Pref_SSA = this.sharedPreferences.getString("ssa_Key", (String) null);
        this.Pref_Username = this.sharedPreferences.getString("username_Key", (String) null);
        this.Pref_Randkey = this.sharedPreferences.getString("rand_Key", (String) null);
        this.Pref_Designation = this.sharedPreferences.getString("designation_Key", (String) null);
        this.Pref_fms_username = this.sharedPreferences.getString("fms_username_Key", (String) null);
        this.Pref_fms_firmname = this.sharedPreferences.getString("fms_firm_Key", (String) null).toUpperCase();
        this.androidId = Settings.Secure.getString(getActivity().getContentResolver(), "android_id");
        this.imageView = (ImageView) getActivity().findViewById(R.id.img_header);
        this.sp_circle = (Spinner) inflate.findViewById(R.id.sp_circle);
        this.sp_ssa = (Spinner) inflate.findViewById(R.id.sp_ssa);
        this.sp_nms_vendor = (Spinner) inflate.findViewById(R.id.sp_nms_vendor);
        this.btn_nms_tip = (TextView) inflate.findViewById(R.id.btn_nms_tip);
        this.lay_nms_tip = (LinearLayout) inflate.findViewById(R.id.lay_nms_tip);
        this.txt_nms_header = (TextView) inflate.findViewById(R.id.txt_nms_header);
        this.tbl_nms_tip = (TableLayout) inflate.findViewById(R.id.tbl_nms_tip);
        this.progress_dialog.show();
        this.fms_firmaname = new ArrayList<>();
        this.fms_username = new ArrayList<>();
        this.fms_firmaname.add("-- VENDOR --");
        this.fms_username.add("-- FMS-USERNAME --");
        ArrayList<String> arrayList = new ArrayList<>();
        this.circle_element = arrayList;
        arrayList.add("-- CIRCLE --");
        RequestQueue newRequestQueue = Volley.newRequestQueue(getActivity());
        AnonymousClass3 r0 = new StringRequest(1, getString(R.string.serverip) + "circle_populate.php", new Response.Listener<String>() {
            public void onResponse(String str) {
                NMS_TIP_Fragment.this.progress_dialog.cancel();
                try {
                    JSONArray jSONArray = new JSONArray(str);
                    for (int i = 0; i < jSONArray.length(); i++) {
                        NMS_TIP_Fragment.this.circle_element.add(jSONArray.getJSONObject(i).getString("CIRCLE"));
                    }
                    ArrayAdapter unused = NMS_TIP_Fragment.this.adapter_circle = new ArrayAdapter(NMS_TIP_Fragment.this.getActivity(), R.layout.spinner_item, NMS_TIP_Fragment.this.circle_element);
                    NMS_TIP_Fragment.this.sp_circle.setAdapter(NMS_TIP_Fragment.this.adapter_circle);
                    NMS_TIP_Fragment.this.sp_circle.setSelection(NMS_TIP_Fragment.this.adapter_circle.getPosition(NMS_TIP_Fragment.this.Pref_Circle));
                    if (NMS_TIP_Fragment.this.Pref_Access_Level.equals("SSA") || NMS_TIP_Fragment.this.Pref_Access_Level.equals("BA") || NMS_TIP_Fragment.this.Pref_Access_Level.equals("CIRCLE")) {
                        NMS_TIP_Fragment.this.sp_circle.setEnabled(false);
                        NMS_TIP_Fragment.this.sp_circle.setAlpha(0.5f);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError volleyError) {
                ArrayAdapter unused = NMS_TIP_Fragment.this.adapter_circle = new ArrayAdapter(NMS_TIP_Fragment.this.getActivity(), R.layout.spinner_item, NMS_TIP_Fragment.this.circle_element);
                NMS_TIP_Fragment.this.sp_circle.setAdapter(NMS_TIP_Fragment.this.adapter_circle);
                NMS_TIP_Fragment.this.sp_circle.setSelection(NMS_TIP_Fragment.this.adapter_circle.getPosition(NMS_TIP_Fragment.this.Pref_Circle));
                if (NMS_TIP_Fragment.this.Pref_Access_Level.equals("SSA") || NMS_TIP_Fragment.this.Pref_Access_Level.equals("BA") || NMS_TIP_Fragment.this.Pref_Access_Level.equals("CIRCLE")) {
                    NMS_TIP_Fragment.this.sp_circle.setEnabled(false);
                    NMS_TIP_Fragment.this.sp_circle.setAlpha(0.5f);
                }
                String onErrorResponse = new VolleyErrorHelper().onErrorResponse(volleyError, NMS_TIP_Fragment.this.getContext());
                NMS_TIP_Fragment.this.progress_dialog.cancel();
                NMS_TIP_Fragment.this.txt_alert.setText(onErrorResponse);
                NMS_TIP_Fragment.this.error_dialog.show();
            }
        }) {
            /* access modifiers changed from: protected */
            public Map<String, String> getParams() throws AuthFailureError {
                HashMap hashMap = new HashMap();
                hashMap.put("registered_circle", NMS_TIP_Fragment.this.Pref_Circle);
                hashMap.put("access", NMS_TIP_Fragment.this.Pref_Access_Level);
                hashMap.put("username", NMS_TIP_Fragment.this.Pref_Username);
                hashMap.put("random_key", NMS_TIP_Fragment.this.Pref_Randkey);
                hashMap.put("device_id", NMS_TIP_Fragment.this.androidId);
                return hashMap;
            }
        };
        r0.setRetryPolicy(new DefaultRetryPolicy(BZip2Constants.BASEBLOCKSIZE, 1, 1.0f));
        newRequestQueue.add(r0);
        this.sp_circle.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                ArrayList unused = NMS_TIP_Fragment.this.ssa_element = new ArrayList();
                NMS_TIP_Fragment.this.ssa_element.add("-- SSA --");
                final String obj = NMS_TIP_Fragment.this.sp_circle.getSelectedItem().toString();
                NMS_TIP_Fragment.this.progress_dialog.show();
                RequestQueue newRequestQueue = Volley.newRequestQueue(NMS_TIP_Fragment.this.getActivity());
                AnonymousClass3 r0 = new StringRequest(1, NMS_TIP_Fragment.this.getString(R.string.serverip) + "ssa_populate.php", new Response.Listener<String>() {
                    public void onResponse(String str) {
                        NMS_TIP_Fragment.this.progress_dialog.cancel();
                        try {
                            JSONArray jSONArray = new JSONArray(str);
                            for (int i = 0; i < jSONArray.length(); i++) {
                                NMS_TIP_Fragment.this.ssa_element.add(jSONArray.getJSONObject(i).getString("SSA"));
                            }
                            ArrayAdapter unused = NMS_TIP_Fragment.this.adapter_ssa = new ArrayAdapter(NMS_TIP_Fragment.this.getActivity(), R.layout.spinner_item, NMS_TIP_Fragment.this.ssa_element);
                            NMS_TIP_Fragment.this.sp_ssa.setAdapter(NMS_TIP_Fragment.this.adapter_ssa);
                            if (obj.equals(NMS_TIP_Fragment.this.Pref_Circle)) {
                                NMS_TIP_Fragment.this.sp_ssa.setSelection(NMS_TIP_Fragment.this.adapter_ssa.getPosition(NMS_TIP_Fragment.this.Pref_SSA));
                            }
                            if (NMS_TIP_Fragment.this.Pref_Access_Level.equals("SSA")) {
                                NMS_TIP_Fragment.this.sp_ssa.setEnabled(false);
                                NMS_TIP_Fragment.this.sp_ssa.setAlpha(0.5f);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError volleyError) {
                        ArrayAdapter unused = NMS_TIP_Fragment.this.adapter_ssa = new ArrayAdapter(NMS_TIP_Fragment.this.getActivity(), R.layout.spinner_item, NMS_TIP_Fragment.this.ssa_element);
                        NMS_TIP_Fragment.this.sp_ssa.setAdapter(NMS_TIP_Fragment.this.adapter_ssa);
                        if (obj.equals(NMS_TIP_Fragment.this.Pref_Circle)) {
                            NMS_TIP_Fragment.this.sp_ssa.setSelection(NMS_TIP_Fragment.this.adapter_ssa.getPosition(NMS_TIP_Fragment.this.Pref_SSA));
                        }
                        if (NMS_TIP_Fragment.this.Pref_Role.equals("NODAL")) {
                            NMS_TIP_Fragment.this.sp_ssa.setEnabled(false);
                            NMS_TIP_Fragment.this.sp_ssa.setAlpha(0.5f);
                        }
                        String onErrorResponse = new VolleyErrorHelper().onErrorResponse(volleyError, NMS_TIP_Fragment.this.getContext());
                        NMS_TIP_Fragment.this.progress_dialog.cancel();
                        NMS_TIP_Fragment.this.txt_alert.setText(onErrorResponse);
                        NMS_TIP_Fragment.this.error_dialog.show();
                    }
                }) {
                    /* access modifiers changed from: protected */
                    public Map<String, String> getParams() throws AuthFailureError {
                        HashMap hashMap = new HashMap();
                        hashMap.put("circle", obj);
                        hashMap.put("access", NMS_TIP_Fragment.this.Pref_Access_Level);
                        hashMap.put("registered_ssa", NMS_TIP_Fragment.this.Pref_SSA);
                        hashMap.put("username", NMS_TIP_Fragment.this.Pref_Username);
                        hashMap.put("random_key", NMS_TIP_Fragment.this.Pref_Randkey);
                        hashMap.put("device_id", NMS_TIP_Fragment.this.androidId);
                        return hashMap;
                    }
                };
                r0.setRetryPolicy(new DefaultRetryPolicy(BZip2Constants.BASEBLOCKSIZE, 1, 1.0f));
                newRequestQueue.add(r0);
            }
        });
        this.sp_ssa.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                final String obj = NMS_TIP_Fragment.this.sp_circle.getSelectedItem().toString();
                final String obj2 = NMS_TIP_Fragment.this.sp_ssa.getSelectedItem().toString();
                ArrayList unused = NMS_TIP_Fragment.this.fms_firmaname = new ArrayList();
                ArrayList unused2 = NMS_TIP_Fragment.this.fms_username = new ArrayList();
                NMS_TIP_Fragment.this.fms_firmaname.add("-- VENDOR --");
                NMS_TIP_Fragment.this.fms_username.add("-- FMS-USERNAME --");
                NMS_TIP_Fragment.this.progress_dialog.show();
                RequestQueue newRequestQueue = Volley.newRequestQueue(NMS_TIP_Fragment.this.getActivity());
                AnonymousClass3 r0 = new StringRequest(1, str, new Response.Listener<String>() {
                    public void onResponse(String str) {
                        NMS_TIP_Fragment.this.progress_dialog.cancel();
                        NMS_TIP_Fragment.this.fms_firmaname.add(Rule.ALL);
                        NMS_TIP_Fragment.this.fms_username.add(Rule.ALL);
                        try {
                            JSONArray jSONArray = new JSONArray(str);
                            for (int i = 0; i < jSONArray.length(); i++) {
                                JSONObject jSONObject = jSONArray.getJSONObject(i);
                                NMS_TIP_Fragment.this.fms_firmaname.add(jSONObject.getString("FMS_FIRM_NAME"));
                                NMS_TIP_Fragment.this.fms_username.add(jSONObject.getString("FMS_USERNAME"));
                            }
                            ArrayAdapter unused = NMS_TIP_Fragment.this.adapter_vendor = new ArrayAdapter(NMS_TIP_Fragment.this.getActivity(), R.layout.spinner_item, NMS_TIP_Fragment.this.fms_firmaname);
                            ArrayAdapter unused2 = NMS_TIP_Fragment.this.adapter_fms_username = new ArrayAdapter(NMS_TIP_Fragment.this.getActivity(), R.layout.spinner_item, NMS_TIP_Fragment.this.fms_username);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        NMS_TIP_Fragment.this.sp_nms_vendor.setAdapter(NMS_TIP_Fragment.this.adapter_vendor);
                        if (NMS_TIP_Fragment.this.Pref_Designation.contains("FRANCHISEE")) {
                            NMS_TIP_Fragment.this.sp_nms_vendor.setSelection(NMS_TIP_Fragment.this.adapter_fms_username.getPosition(NMS_TIP_Fragment.this.Pref_fms_username));
                            NMS_TIP_Fragment.this.sp_nms_vendor.setEnabled(false);
                            NMS_TIP_Fragment.this.sp_nms_vendor.setAlpha(0.6f);
                        }
                    }
                }, new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError volleyError) {
                        ArrayAdapter unused = NMS_TIP_Fragment.this.adapter_vendor = new ArrayAdapter(NMS_TIP_Fragment.this.getActivity(), R.layout.spinner_item, NMS_TIP_Fragment.this.fms_firmaname);
                        ArrayAdapter unused2 = NMS_TIP_Fragment.this.adapter_fms_username = new ArrayAdapter(NMS_TIP_Fragment.this.getActivity(), R.layout.spinner_item, NMS_TIP_Fragment.this.fms_username);
                        NMS_TIP_Fragment.this.sp_nms_vendor.setAdapter(NMS_TIP_Fragment.this.adapter_vendor);
                        NMS_TIP_Fragment.this.progress_dialog.cancel();
                        NMS_TIP_Fragment.this.txt_alert.setText(new VolleyErrorHelper().onErrorResponse(volleyError, NMS_TIP_Fragment.this.getContext()));
                        NMS_TIP_Fragment.this.error_dialog.show();
                    }
                }) {
                    /* access modifiers changed from: protected */
                    public Map<String, String> getParams() throws AuthFailureError {
                        HashMap hashMap = new HashMap();
                        hashMap.put("circle", obj);
                        hashMap.put("ssa", obj2);
                        hashMap.put("username", NMS_TIP_Fragment.this.Pref_Username);
                        hashMap.put("random_key", NMS_TIP_Fragment.this.Pref_Randkey);
                        hashMap.put("device_id", NMS_TIP_Fragment.this.androidId);
                        return hashMap;
                    }
                };
                r0.setRetryPolicy(new DefaultRetryPolicy(BZip2Constants.BASEBLOCKSIZE, 1, 1.0f));
                newRequestQueue.add(r0);
            }
        });
        this.btn_nms_tip.setOnClickListener(this);
        return inflate;
    }

    public void onClick(View view) {
        if (view.getId() == R.id.btn_nms_tip) {
            String str = getString(R.string.serverip) + "nms_tip_new.php";
            this.lay_nms_tip.setVisibility(8);
            final String obj = this.sp_circle.getSelectedItem().toString();
            final String obj2 = this.sp_ssa.getSelectedItem().toString();
            final String obj3 = this.sp_nms_vendor.getSelectedItem().toString();
            if (obj.equals("-- CIRCLE --")) {
                new AlertHelperclass().ntoast("Please Select A Valid Circle", getContext());
            } else if (obj2.equals("-- SSA --")) {
                new AlertHelperclass().ntoast("Please Select A Valid SSA", getContext());
            } else if (obj3.equals("-- VENDOR --")) {
                new AlertHelperclass().ntoast("Please Select A Valid Vendor", getContext());
            } else {
                String str2 = new String();
                if (obj3.equals(Rule.ALL)) {
                    this.txt_nms_header.setText("CIRCLE: " + obj + " | SSA: " + obj2);
                } else {
                    this.txt_nms_header.setText(obj3);
                    str2 = this.fms_username.get(this.sp_nms_vendor.getSelectedItemPosition());
                }
                final String str3 = str2;
                this.progress_dialog.show();
                this.tbl_nms_tip.removeAllViewsInLayout();
                this.txt_nms_header.setPaintFlags(8);
                RequestQueue newRequestQueue = Volley.newRequestQueue(getActivity());
                AnonymousClass8 r0 = new StringRequest(1, str, new Response.Listener<String>() {
                    public void onResponse(String str) {
                        TableRow tableRow = new TableRow(NMS_TIP_Fragment.this.getActivity());
                        int i = -1;
                        tableRow.setLayoutParams(new TableRow.LayoutParams(-1, -1));
                        TextView textView = new TextView(NMS_TIP_Fragment.this.getActivity());
                        textView.setText("SR");
                        textView.setTextColor(-1);
                        textView.setGravity(17);
                        int i2 = 20;
                        textView.setPadding(20, 20, 20, 20);
                        textView.setTypeface((Typeface) null, 1);
                        Resources resources = NMS_TIP_Fragment.this.getResources();
                        int i3 = R.dimen.mediumtext;
                        textView.setTextSize(0, resources.getDimension(R.dimen.mediumtext));
                        textView.setBackgroundResource(R.drawable.new_style1);
                        TextView textView2 = new TextView(NMS_TIP_Fragment.this.getActivity());
                        textView2.setText("VLAN");
                        textView2.setTextColor(-1);
                        textView2.setGravity(17);
                        textView2.setPadding(20, 20, 20, 20);
                        textView2.setTypeface((Typeface) null, 1);
                        textView2.setTextSize(0, NMS_TIP_Fragment.this.getResources().getDimension(R.dimen.mediumtext));
                        textView2.setBackgroundResource(R.drawable.new_style1);
                        TextView textView3 = new TextView(NMS_TIP_Fragment.this.getActivity());
                        textView3.setText(CodePackage.LOCATION);
                        textView3.setTextColor(-1);
                        textView3.setGravity(17);
                        textView3.setPadding(20, 20, 20, 20);
                        textView3.setTypeface((Typeface) null, 1);
                        textView3.setTextSize(0, NMS_TIP_Fragment.this.getResources().getDimension(R.dimen.mediumtext));
                        textView3.setBackgroundResource(R.drawable.new_style1);
                        TextView textView4 = new TextView(NMS_TIP_Fragment.this.getActivity());
                        textView4.setText("OLT IP");
                        textView4.setTextColor(-1);
                        textView4.setGravity(17);
                        textView4.setPadding(20, 20, 20, 20);
                        textView4.setTypeface((Typeface) null, 1);
                        textView4.setTextSize(0, NMS_TIP_Fragment.this.getResources().getDimension(R.dimen.mediumtext));
                        textView4.setBackgroundResource(R.drawable.new_style1);
                        TextView textView5 = new TextView(NMS_TIP_Fragment.this.getActivity());
                        textView5.setText("STATUS");
                        textView5.setTextColor(-1);
                        textView5.setGravity(17);
                        textView5.setPadding(20, 20, 20, 20);
                        textView5.setTypeface((Typeface) null, 1);
                        textView5.setTextSize(0, NMS_TIP_Fragment.this.getResources().getDimension(R.dimen.mediumtext));
                        textView5.setBackgroundResource(R.drawable.new_style1);
                        TextView textView6 = new TextView(NMS_TIP_Fragment.this.getActivity());
                        textView6.setText("OLT MAKE");
                        textView6.setTextColor(-1);
                        textView6.setGravity(17);
                        textView6.setPadding(20, 20, 20, 20);
                        textView6.setTypeface((Typeface) null, 1);
                        textView6.setTextSize(0, NMS_TIP_Fragment.this.getResources().getDimension(R.dimen.mediumtext));
                        textView6.setBackgroundResource(R.drawable.new_style1);
                        TextView textView7 = new TextView(NMS_TIP_Fragment.this.getActivity());
                        textView7.setText("FRANCHISEE");
                        textView7.setTextColor(-1);
                        textView7.setGravity(17);
                        textView7.setPadding(20, 20, 20, 20);
                        textView7.setTypeface((Typeface) null, 1);
                        textView7.setTextSize(0, NMS_TIP_Fragment.this.getResources().getDimension(R.dimen.mediumtext));
                        textView7.setBackgroundResource(R.drawable.new_style1);
                        tableRow.addView(textView);
                        if (obj3.equals(Rule.ALL)) {
                            tableRow.addView(textView7);
                        }
                        tableRow.addView(textView3);
                        tableRow.addView(textView2);
                        tableRow.addView(textView4);
                        tableRow.addView(textView6);
                        tableRow.addView(textView5);
                        NMS_TIP_Fragment.this.tbl_nms_tip.addView(tableRow);
                        try {
                            JSONArray jSONArray = new JSONArray(str);
                            int i4 = 0;
                            while (i4 < jSONArray.length()) {
                                int i5 = i4 + 1;
                                JSONObject jSONObject = jSONArray.getJSONObject(i4);
                                TableRow tableRow2 = new TableRow(NMS_TIP_Fragment.this.getActivity());
                                tableRow2.setLayoutParams(new TableRow.LayoutParams(i, i));
                                TextView textView8 = new TextView(NMS_TIP_Fragment.this.getActivity());
                                textView8.setText(Integer.toString(i5));
                                textView8.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                                textView8.setGravity(17);
                                textView8.setPadding(i2, i2, i2, i2);
                                textView8.setTextSize(0, NMS_TIP_Fragment.this.getResources().getDimension(i3));
                                textView8.setBackgroundResource(R.drawable.style17);
                                TextView textView9 = new TextView(NMS_TIP_Fragment.this.getActivity());
                                textView9.setText(jSONObject.getString("vlan"));
                                textView9.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                                textView9.setGravity(17);
                                textView9.setPadding(i2, i2, i2, i2);
                                textView9.setTextSize(0, NMS_TIP_Fragment.this.getResources().getDimension(i3));
                                textView9.setBackgroundResource(R.drawable.style17);
                                TextView textView10 = new TextView(NMS_TIP_Fragment.this.getActivity());
                                textView10.setText(jSONObject.getString("location"));
                                textView10.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                                textView10.setGravity(17);
                                textView10.setPadding(i2, i2, i2, i2);
                                textView10.setTextSize(0, NMS_TIP_Fragment.this.getResources().getDimension(i3));
                                textView10.setBackgroundResource(R.drawable.style17);
                                TextView textView11 = new TextView(NMS_TIP_Fragment.this.getActivity());
                                textView11.setText(jSONObject.getString("ip"));
                                textView11.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                                textView11.setGravity(17);
                                textView11.setPadding(i2, i2, i2, i2);
                                textView11.setTextSize(0, NMS_TIP_Fragment.this.getResources().getDimension(i3));
                                textView11.setBackgroundResource(R.drawable.style17);
                                TextView textView12 = new TextView(NMS_TIP_Fragment.this.getActivity());
                                textView12.setText(Html.fromHtml(jSONObject.getString("stat")));
                                textView12.setGravity(17);
                                textView12.setPadding(i2, i2, i2, i2);
                                textView12.setTypeface((Typeface) null, 1);
                                textView12.setTextSize(0, NMS_TIP_Fragment.this.getResources().getDimension(i3));
                                textView12.setBackgroundResource(R.drawable.style17);
                                TextView textView13 = new TextView(NMS_TIP_Fragment.this.getActivity());
                                textView13.setText(Html.fromHtml(jSONObject.getString("make")));
                                textView13.setGravity(17);
                                textView13.setPadding(20, 20, 20, 20);
                                textView13.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                                textView13.setTextSize(0, NMS_TIP_Fragment.this.getResources().getDimension(i3));
                                textView13.setBackgroundResource(R.drawable.style17);
                                TextView textView14 = new TextView(NMS_TIP_Fragment.this.getActivity());
                                textView14.setText(Html.fromHtml(jSONObject.getString("firm")));
                                textView14.setGravity(17);
                                textView14.setPadding(20, 20, 20, 20);
                                textView14.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                                textView14.setTextSize(0, NMS_TIP_Fragment.this.getResources().getDimension(R.dimen.mediumtext));
                                textView14.setBackgroundResource(R.drawable.style17);
                                tableRow2.addView(textView8);
                                if (obj3.equals(Rule.ALL)) {
                                    tableRow2.addView(textView14);
                                }
                                tableRow2.addView(textView10);
                                tableRow2.addView(textView9);
                                tableRow2.addView(textView11);
                                tableRow2.addView(textView13);
                                tableRow2.addView(textView12);
                                NMS_TIP_Fragment.this.tbl_nms_tip.addView(tableRow2);
                                i4 = i5;
                                i = -1;
                                i2 = 20;
                                i3 = R.dimen.mediumtext;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        NMS_TIP_Fragment.this.lay_nms_tip.setVisibility(0);
                        NMS_TIP_Fragment.this.progress_dialog.cancel();
                    }
                }, new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError volleyError) {
                        NMS_TIP_Fragment.this.progress_dialog.cancel();
                        NMS_TIP_Fragment.this.txt_alert.setText(new VolleyErrorHelper().onErrorResponse(volleyError, NMS_TIP_Fragment.this.getContext()));
                        NMS_TIP_Fragment.this.error_dialog.show();
                    }
                }) {
                    /* access modifiers changed from: protected */
                    public Map<String, String> getParams() throws AuthFailureError {
                        HashMap hashMap = new HashMap();
                        hashMap.put("circle", obj);
                        hashMap.put("ssa", obj2);
                        hashMap.put("fms_username", str3);
                        hashMap.put("vendor", obj3);
                        hashMap.put("username", NMS_TIP_Fragment.this.Pref_Username);
                        hashMap.put("random_key", NMS_TIP_Fragment.this.Pref_Randkey);
                        hashMap.put("device_id", NMS_TIP_Fragment.this.androidId);
                        return hashMap;
                    }
                };
                r0.setRetryPolicy(new DefaultRetryPolicy(BZip2Constants.BASEBLOCKSIZE, 1, 1.0f));
                newRequestQueue.add(r0);
            }
        }
    }
}
