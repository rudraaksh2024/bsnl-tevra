package bsnl.bsnl_teevra;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
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

public class NMS_DSLAM_Fragment extends Fragment implements View.OnClickListener {
    /* access modifiers changed from: private */
    public String Pref_Access_Level;
    /* access modifiers changed from: private */
    public String Pref_Circle;
    /* access modifiers changed from: private */
    public String Pref_Randkey;
    /* access modifiers changed from: private */
    public String Pref_Role;
    /* access modifiers changed from: private */
    public String Pref_SSA;
    /* access modifiers changed from: private */
    public String Pref_Username;
    /* access modifiers changed from: private */
    public ArrayAdapter<String> adapter_circle;
    /* access modifiers changed from: private */
    public ArrayAdapter<String> adapter_ssa;
    /* access modifiers changed from: private */
    public String androidId;
    private TextView btn_nms_dslam;
    /* access modifiers changed from: private */
    public ArrayList<String> circle_element;
    /* access modifiers changed from: private */
    public AlertDialog error_dialog;
    /* access modifiers changed from: private */
    public ArrayList<String> exchangecode_dslam;
    private ImageView imageView;
    /* access modifiers changed from: private */
    public LinearLayout lay_nms_dslam;
    /* access modifiers changed from: private */
    public AlertDialog progress_dialog;
    private SharedPreferences sharedPreferences;
    /* access modifiers changed from: private */
    public Spinner sp_circle;
    /* access modifiers changed from: private */
    public Spinner sp_nms_exchangecode;
    /* access modifiers changed from: private */
    public Spinner sp_ssa;
    /* access modifiers changed from: private */
    public ArrayList<String> ssa_element;
    /* access modifiers changed from: private */
    public TableLayout tbl_nms_dslam;
    /* access modifiers changed from: private */
    public TextView txt_alert;
    private TextView txt_nms_header;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.nms_dslam_fragment, viewGroup, false);
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
        final String str = getString(R.string.serverip) + "exchange_populate.php";
        SharedPreferences sharedPreferences2 = getActivity().getSharedPreferences("myloginpreference", 0);
        this.sharedPreferences = sharedPreferences2;
        this.Pref_Role = sharedPreferences2.getString("role_Key", (String) null);
        this.Pref_Access_Level = this.sharedPreferences.getString("access_level_Key", (String) null);
        this.Pref_Circle = this.sharedPreferences.getString("circle_Key", (String) null);
        this.Pref_SSA = this.sharedPreferences.getString("ssa_Key", (String) null);
        this.Pref_Username = this.sharedPreferences.getString("username_Key", (String) null);
        this.Pref_Randkey = this.sharedPreferences.getString("rand_Key", (String) null);
        this.androidId = Settings.Secure.getString(getActivity().getContentResolver(), "android_id");
        this.imageView = (ImageView) getActivity().findViewById(R.id.img_header);
        this.sp_circle = (Spinner) inflate.findViewById(R.id.sp_circle);
        this.sp_ssa = (Spinner) inflate.findViewById(R.id.sp_ssa);
        this.sp_nms_exchangecode = (Spinner) inflate.findViewById(R.id.sp_nms_exchangecode);
        this.btn_nms_dslam = (TextView) inflate.findViewById(R.id.btn_nms_dslam);
        this.lay_nms_dslam = (LinearLayout) inflate.findViewById(R.id.lay_nms_dslam);
        this.txt_nms_header = (TextView) inflate.findViewById(R.id.txt_nms_header);
        this.tbl_nms_dslam = (TableLayout) inflate.findViewById(R.id.tbl_nms_dslam);
        this.progress_dialog.show();
        ArrayList<String> arrayList = new ArrayList<>();
        this.circle_element = arrayList;
        arrayList.add("-- CIRCLE --");
        RequestQueue newRequestQueue = Volley.newRequestQueue(getActivity());
        AnonymousClass3 r0 = new StringRequest(1, getString(R.string.serverip) + "circle_populate.php", new Response.Listener<String>() {
            public void onResponse(String str) {
                NMS_DSLAM_Fragment.this.progress_dialog.cancel();
                try {
                    JSONArray jSONArray = new JSONArray(str);
                    for (int i = 0; i < jSONArray.length(); i++) {
                        NMS_DSLAM_Fragment.this.circle_element.add(jSONArray.getJSONObject(i).getString("CIRCLE"));
                    }
                    ArrayAdapter unused = NMS_DSLAM_Fragment.this.adapter_circle = new ArrayAdapter(NMS_DSLAM_Fragment.this.getActivity(), R.layout.spinner_item, NMS_DSLAM_Fragment.this.circle_element);
                    NMS_DSLAM_Fragment.this.sp_circle.setAdapter(NMS_DSLAM_Fragment.this.adapter_circle);
                    NMS_DSLAM_Fragment.this.sp_circle.setSelection(NMS_DSLAM_Fragment.this.adapter_circle.getPosition(NMS_DSLAM_Fragment.this.Pref_Circle));
                    if (NMS_DSLAM_Fragment.this.Pref_Access_Level.equals("SSA") || NMS_DSLAM_Fragment.this.Pref_Access_Level.equals("BA") || NMS_DSLAM_Fragment.this.Pref_Access_Level.equals("CIRCLE")) {
                        NMS_DSLAM_Fragment.this.sp_circle.setEnabled(false);
                        NMS_DSLAM_Fragment.this.sp_circle.setAlpha(0.5f);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError volleyError) {
                ArrayAdapter unused = NMS_DSLAM_Fragment.this.adapter_circle = new ArrayAdapter(NMS_DSLAM_Fragment.this.getActivity(), R.layout.spinner_item, NMS_DSLAM_Fragment.this.circle_element);
                NMS_DSLAM_Fragment.this.sp_circle.setAdapter(NMS_DSLAM_Fragment.this.adapter_circle);
                NMS_DSLAM_Fragment.this.sp_circle.setSelection(NMS_DSLAM_Fragment.this.adapter_circle.getPosition(NMS_DSLAM_Fragment.this.Pref_Circle));
                if (NMS_DSLAM_Fragment.this.Pref_Access_Level.equals("SSA") || NMS_DSLAM_Fragment.this.Pref_Access_Level.equals("BA") || NMS_DSLAM_Fragment.this.Pref_Access_Level.equals("CIRCLE")) {
                    NMS_DSLAM_Fragment.this.sp_circle.setEnabled(false);
                    NMS_DSLAM_Fragment.this.sp_circle.setAlpha(0.5f);
                }
                String onErrorResponse = new VolleyErrorHelper().onErrorResponse(volleyError, NMS_DSLAM_Fragment.this.getContext());
                NMS_DSLAM_Fragment.this.progress_dialog.cancel();
                NMS_DSLAM_Fragment.this.txt_alert.setText(onErrorResponse);
                NMS_DSLAM_Fragment.this.error_dialog.show();
            }
        }) {
            /* access modifiers changed from: protected */
            public Map<String, String> getParams() throws AuthFailureError {
                HashMap hashMap = new HashMap();
                hashMap.put("registered_circle", NMS_DSLAM_Fragment.this.Pref_Circle);
                hashMap.put("access", NMS_DSLAM_Fragment.this.Pref_Access_Level);
                hashMap.put("username", NMS_DSLAM_Fragment.this.Pref_Username);
                hashMap.put("random_key", NMS_DSLAM_Fragment.this.Pref_Randkey);
                hashMap.put("device_id", NMS_DSLAM_Fragment.this.androidId);
                return hashMap;
            }
        };
        r0.setRetryPolicy(new DefaultRetryPolicy(BZip2Constants.BASEBLOCKSIZE, 1, 1.0f));
        newRequestQueue.add(r0);
        this.sp_circle.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                ArrayList unused = NMS_DSLAM_Fragment.this.ssa_element = new ArrayList();
                NMS_DSLAM_Fragment.this.ssa_element.add("-- SSA --");
                final String obj = NMS_DSLAM_Fragment.this.sp_circle.getSelectedItem().toString();
                NMS_DSLAM_Fragment.this.progress_dialog.show();
                RequestQueue newRequestQueue = Volley.newRequestQueue(NMS_DSLAM_Fragment.this.getActivity());
                AnonymousClass3 r0 = new StringRequest(1, NMS_DSLAM_Fragment.this.getString(R.string.serverip) + "ssa_populate.php", new Response.Listener<String>() {
                    public void onResponse(String str) {
                        NMS_DSLAM_Fragment.this.progress_dialog.cancel();
                        try {
                            JSONArray jSONArray = new JSONArray(str);
                            for (int i = 0; i < jSONArray.length(); i++) {
                                NMS_DSLAM_Fragment.this.ssa_element.add(jSONArray.getJSONObject(i).getString("SSA"));
                            }
                            ArrayAdapter unused = NMS_DSLAM_Fragment.this.adapter_ssa = new ArrayAdapter(NMS_DSLAM_Fragment.this.getActivity(), R.layout.spinner_item, NMS_DSLAM_Fragment.this.ssa_element);
                            NMS_DSLAM_Fragment.this.sp_ssa.setAdapter(NMS_DSLAM_Fragment.this.adapter_ssa);
                            if (obj.equals(NMS_DSLAM_Fragment.this.Pref_Circle)) {
                                NMS_DSLAM_Fragment.this.sp_ssa.setSelection(NMS_DSLAM_Fragment.this.adapter_ssa.getPosition(NMS_DSLAM_Fragment.this.Pref_SSA));
                            }
                            if (NMS_DSLAM_Fragment.this.Pref_Access_Level.equals("SSA")) {
                                NMS_DSLAM_Fragment.this.sp_ssa.setEnabled(false);
                                NMS_DSLAM_Fragment.this.sp_ssa.setAlpha(0.5f);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError volleyError) {
                        ArrayAdapter unused = NMS_DSLAM_Fragment.this.adapter_ssa = new ArrayAdapter(NMS_DSLAM_Fragment.this.getActivity(), R.layout.spinner_item, NMS_DSLAM_Fragment.this.ssa_element);
                        NMS_DSLAM_Fragment.this.sp_ssa.setAdapter(NMS_DSLAM_Fragment.this.adapter_ssa);
                        if (obj.equals(NMS_DSLAM_Fragment.this.Pref_Circle)) {
                            NMS_DSLAM_Fragment.this.sp_ssa.setSelection(NMS_DSLAM_Fragment.this.adapter_ssa.getPosition(NMS_DSLAM_Fragment.this.Pref_SSA));
                        }
                        if (NMS_DSLAM_Fragment.this.Pref_Role.equals("NODAL")) {
                            NMS_DSLAM_Fragment.this.sp_ssa.setEnabled(false);
                            NMS_DSLAM_Fragment.this.sp_ssa.setAlpha(0.5f);
                        }
                        String onErrorResponse = new VolleyErrorHelper().onErrorResponse(volleyError, NMS_DSLAM_Fragment.this.getContext());
                        NMS_DSLAM_Fragment.this.progress_dialog.cancel();
                        NMS_DSLAM_Fragment.this.txt_alert.setText(onErrorResponse);
                        NMS_DSLAM_Fragment.this.error_dialog.show();
                    }
                }) {
                    /* access modifiers changed from: protected */
                    public Map<String, String> getParams() throws AuthFailureError {
                        HashMap hashMap = new HashMap();
                        hashMap.put("circle", obj);
                        hashMap.put("access", NMS_DSLAM_Fragment.this.Pref_Access_Level);
                        hashMap.put("registered_ssa", NMS_DSLAM_Fragment.this.Pref_SSA);
                        hashMap.put("username", NMS_DSLAM_Fragment.this.Pref_Username);
                        hashMap.put("random_key", NMS_DSLAM_Fragment.this.Pref_Randkey);
                        hashMap.put("device_id", NMS_DSLAM_Fragment.this.androidId);
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
                final String obj = NMS_DSLAM_Fragment.this.sp_circle.getSelectedItem().toString();
                final String obj2 = NMS_DSLAM_Fragment.this.sp_ssa.getSelectedItem().toString();
                NMS_DSLAM_Fragment.this.progress_dialog.show();
                ArrayList unused = NMS_DSLAM_Fragment.this.exchangecode_dslam = new ArrayList();
                NMS_DSLAM_Fragment.this.exchangecode_dslam.add("-- EXGCODE --");
                RequestQueue newRequestQueue = Volley.newRequestQueue(NMS_DSLAM_Fragment.this.getActivity());
                AnonymousClass3 r0 = new StringRequest(1, str, new Response.Listener<String>() {
                    public void onResponse(String str) {
                        NMS_DSLAM_Fragment.this.progress_dialog.cancel();
                        NMS_DSLAM_Fragment.this.exchangecode_dslam.add(Rule.ALL);
                        try {
                            JSONArray jSONArray = new JSONArray(str);
                            for (int i = 0; i < jSONArray.length(); i++) {
                                NMS_DSLAM_Fragment.this.exchangecode_dslam.add(jSONArray.getJSONObject(i).getString("EXGCODE"));
                            }
                            NMS_DSLAM_Fragment.this.sp_nms_exchangecode.setAdapter(new ArrayAdapter(NMS_DSLAM_Fragment.this.getActivity(), R.layout.spinner_item, NMS_DSLAM_Fragment.this.exchangecode_dslam));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        NMS_DSLAM_Fragment.this.progress_dialog.cancel();
                    }
                }, new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError volleyError) {
                        NMS_DSLAM_Fragment.this.sp_nms_exchangecode.setAdapter(new ArrayAdapter(NMS_DSLAM_Fragment.this.getActivity(), R.layout.spinner_item, NMS_DSLAM_Fragment.this.exchangecode_dslam));
                        NMS_DSLAM_Fragment.this.progress_dialog.cancel();
                        NMS_DSLAM_Fragment.this.txt_alert.setText(new VolleyErrorHelper().onErrorResponse(volleyError, NMS_DSLAM_Fragment.this.getContext()));
                        NMS_DSLAM_Fragment.this.error_dialog.show();
                    }
                }) {
                    /* access modifiers changed from: protected */
                    public Map<String, String> getParams() throws AuthFailureError {
                        HashMap hashMap = new HashMap();
                        hashMap.put("circle", obj);
                        hashMap.put("ssa", obj2);
                        hashMap.put("username", NMS_DSLAM_Fragment.this.Pref_Username);
                        hashMap.put("random_key", NMS_DSLAM_Fragment.this.Pref_Randkey);
                        hashMap.put("device_id", NMS_DSLAM_Fragment.this.androidId);
                        return hashMap;
                    }
                };
                r0.setRetryPolicy(new DefaultRetryPolicy(BZip2Constants.BASEBLOCKSIZE, 1, 1.0f));
                newRequestQueue.add(r0);
            }
        });
        this.btn_nms_dslam.setOnClickListener(this);
        return inflate;
    }

    public void onClick(View view) {
        String str = getString(R.string.serverip) + "nms_dslam_new.php";
        this.lay_nms_dslam.setVisibility(8);
        final String obj = this.sp_circle.getSelectedItem().toString();
        final String obj2 = this.sp_ssa.getSelectedItem().toString();
        final String obj3 = this.sp_nms_exchangecode.getSelectedItem().toString();
        if (obj.equals("-- CIRCLE --")) {
            new AlertHelperclass().ntoast("Please Select A Valid Circle", getContext());
        } else if (obj2.equals("-- SSA --")) {
            new AlertHelperclass().ntoast("Please Select A Valid SSA", getContext());
        } else if (obj3.equals("-- EXGCODE --")) {
            new AlertHelperclass().ntoast("Please Select A Valid Exchange code", getContext());
        } else {
            this.progress_dialog.show();
            this.tbl_nms_dslam.removeAllViewsInLayout();
            this.txt_nms_header.setText("CIRCLE: " + obj + " | SSA: " + obj2);
            this.txt_nms_header.setPaintFlags(8);
            RequestQueue newRequestQueue = Volley.newRequestQueue(getActivity());
            AnonymousClass8 r0 = new StringRequest(1, str, new Response.Listener<String>() {
                public void onResponse(String str) {
                    TableRow tableRow = new TableRow(NMS_DSLAM_Fragment.this.getActivity());
                    int i = -1;
                    tableRow.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
                    TextView textView = new TextView(NMS_DSLAM_Fragment.this.getActivity());
                    textView.setText("SR");
                    textView.setTextColor(-1);
                    textView.setGravity(17);
                    int i2 = 20;
                    textView.setPadding(20, 20, 20, 20);
                    textView.setTypeface((Typeface) null, 1);
                    textView.setTextSize(0, NMS_DSLAM_Fragment.this.getResources().getDimension(R.dimen.mediumtext));
                    textView.setBackgroundResource(R.drawable.new_style1);
                    TextView textView2 = new TextView(NMS_DSLAM_Fragment.this.getActivity());
                    textView2.setText("VLAN");
                    textView2.setTextColor(-1);
                    textView2.setGravity(17);
                    textView2.setPadding(20, 20, 20, 20);
                    textView2.setTypeface((Typeface) null, 1);
                    textView2.setTextSize(0, NMS_DSLAM_Fragment.this.getResources().getDimension(R.dimen.mediumtext));
                    textView2.setBackgroundResource(R.drawable.new_style1);
                    TextView textView3 = new TextView(NMS_DSLAM_Fragment.this.getActivity());
                    textView3.setText(CodePackage.LOCATION);
                    textView3.setTextColor(-1);
                    textView3.setGravity(17);
                    textView3.setPadding(20, 20, 20, 20);
                    textView3.setTypeface((Typeface) null, 1);
                    textView3.setTextSize(0, NMS_DSLAM_Fragment.this.getResources().getDimension(R.dimen.mediumtext));
                    textView3.setBackgroundResource(R.drawable.new_style1);
                    TextView textView4 = new TextView(NMS_DSLAM_Fragment.this.getActivity());
                    textView4.setText("DSLAM IP");
                    textView4.setTextColor(-1);
                    textView4.setGravity(17);
                    textView4.setPadding(20, 20, 20, 20);
                    textView4.setTypeface((Typeface) null, 1);
                    textView4.setTextSize(0, NMS_DSLAM_Fragment.this.getResources().getDimension(R.dimen.mediumtext));
                    textView4.setBackgroundResource(R.drawable.new_style1);
                    TextView textView5 = new TextView(NMS_DSLAM_Fragment.this.getActivity());
                    textView5.setText("STATUS");
                    textView5.setTextColor(-1);
                    textView5.setGravity(17);
                    textView5.setPadding(20, 20, 20, 20);
                    textView5.setTypeface((Typeface) null, 1);
                    textView5.setTextSize(0, NMS_DSLAM_Fragment.this.getResources().getDimension(R.dimen.mediumtext));
                    textView5.setBackgroundResource(R.drawable.new_style1);
                    TextView textView6 = new TextView(NMS_DSLAM_Fragment.this.getActivity());
                    textView6.setText("DSLAM MAKE");
                    textView6.setTextColor(-1);
                    textView6.setGravity(17);
                    textView6.setPadding(20, 20, 20, 20);
                    textView6.setTypeface((Typeface) null, 1);
                    textView6.setTextSize(0, NMS_DSLAM_Fragment.this.getResources().getDimension(R.dimen.mediumtext));
                    textView6.setBackgroundResource(R.drawable.new_style1);
                    tableRow.addView(textView);
                    tableRow.addView(textView3);
                    tableRow.addView(textView2);
                    tableRow.addView(textView4);
                    tableRow.addView(textView6);
                    tableRow.addView(textView5);
                    NMS_DSLAM_Fragment.this.tbl_nms_dslam.addView(tableRow);
                    try {
                        JSONArray jSONArray = new JSONArray(str);
                        int i3 = 0;
                        while (i3 < jSONArray.length()) {
                            int i4 = i3 + 1;
                            JSONObject jSONObject = jSONArray.getJSONObject(i3);
                            TableRow tableRow2 = new TableRow(NMS_DSLAM_Fragment.this.getActivity());
                            tableRow2.setLayoutParams(new TableRow.LayoutParams(i, -2));
                            TextView textView7 = new TextView(NMS_DSLAM_Fragment.this.getActivity());
                            textView7.setText(Integer.toString(i4));
                            textView7.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                            textView7.setGravity(17);
                            textView7.setPadding(i2, i2, i2, i2);
                            textView7.setTextSize(0, NMS_DSLAM_Fragment.this.getResources().getDimension(R.dimen.mediumtext));
                            textView7.setBackgroundResource(R.drawable.style17);
                            TextView textView8 = new TextView(NMS_DSLAM_Fragment.this.getActivity());
                            textView8.setText(jSONObject.getString("vlan"));
                            textView8.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                            textView8.setGravity(17);
                            textView8.setPadding(i2, i2, i2, i2);
                            textView8.setTextSize(0, NMS_DSLAM_Fragment.this.getResources().getDimension(R.dimen.mediumtext));
                            textView8.setBackgroundResource(R.drawable.style17);
                            TextView textView9 = new TextView(NMS_DSLAM_Fragment.this.getActivity());
                            textView9.setText(jSONObject.getString("location"));
                            textView9.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                            textView9.setGravity(17);
                            textView9.setPadding(i2, i2, i2, i2);
                            textView9.setTextSize(0, NMS_DSLAM_Fragment.this.getResources().getDimension(R.dimen.mediumtext));
                            textView9.setBackgroundResource(R.drawable.style17);
                            TextView textView10 = new TextView(NMS_DSLAM_Fragment.this.getActivity());
                            textView10.setText(jSONObject.getString("ip"));
                            textView10.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                            textView10.setGravity(17);
                            textView10.setPadding(i2, i2, i2, i2);
                            textView10.setTextSize(0, NMS_DSLAM_Fragment.this.getResources().getDimension(R.dimen.mediumtext));
                            textView10.setBackgroundResource(R.drawable.style17);
                            TextView textView11 = new TextView(NMS_DSLAM_Fragment.this.getActivity());
                            textView11.setText(Html.fromHtml(jSONObject.getString("stat")));
                            textView11.setGravity(17);
                            textView11.setPadding(i2, i2, i2, i2);
                            textView11.setTypeface((Typeface) null, 1);
                            textView11.setTextSize(0, NMS_DSLAM_Fragment.this.getResources().getDimension(R.dimen.mediumtext));
                            textView11.setBackgroundResource(R.drawable.style17);
                            TextView textView12 = new TextView(NMS_DSLAM_Fragment.this.getActivity());
                            textView12.setText(Html.fromHtml(jSONObject.getString("make")));
                            textView12.setGravity(17);
                            textView12.setPadding(20, 20, 20, 20);
                            textView12.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                            textView12.setTextSize(0, NMS_DSLAM_Fragment.this.getResources().getDimension(R.dimen.mediumtext));
                            textView12.setBackgroundResource(R.drawable.style17);
                            tableRow2.addView(textView7);
                            tableRow2.addView(textView9);
                            tableRow2.addView(textView8);
                            tableRow2.addView(textView10);
                            tableRow2.addView(textView12);
                            tableRow2.addView(textView11);
                            NMS_DSLAM_Fragment.this.tbl_nms_dslam.addView(tableRow2);
                            i2 = 20;
                            i3 = i4;
                            i = -1;
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    NMS_DSLAM_Fragment.this.lay_nms_dslam.setVisibility(0);
                    NMS_DSLAM_Fragment.this.progress_dialog.cancel();
                }
            }, new Response.ErrorListener() {
                public void onErrorResponse(VolleyError volleyError) {
                    NMS_DSLAM_Fragment.this.progress_dialog.cancel();
                    NMS_DSLAM_Fragment.this.txt_alert.setText(new VolleyErrorHelper().onErrorResponse(volleyError, NMS_DSLAM_Fragment.this.getContext()));
                    NMS_DSLAM_Fragment.this.error_dialog.show();
                }
            }) {
                /* access modifiers changed from: protected */
                public Map<String, String> getParams() throws AuthFailureError {
                    HashMap hashMap = new HashMap();
                    hashMap.put("circle", obj);
                    hashMap.put("ssa", obj2);
                    hashMap.put("exchangecode", obj3);
                    hashMap.put("username", NMS_DSLAM_Fragment.this.Pref_Username);
                    hashMap.put("random_key", NMS_DSLAM_Fragment.this.Pref_Randkey);
                    hashMap.put("device_id", NMS_DSLAM_Fragment.this.androidId);
                    return hashMap;
                }
            };
            r0.setRetryPolicy(new DefaultRetryPolicy(BZip2Constants.BASEBLOCKSIZE, 1, 1.0f));
            newRequestQueue.add(r0);
        }
    }
}
