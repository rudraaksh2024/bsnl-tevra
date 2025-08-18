package bsnl.bsnl_teevra;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.internal.view.SupportMenu;
import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PMS_Tip_Uplink_Activity extends AppCompatActivity {
    private String Pref_Circle;
    private String Pref_Designation;
    /* access modifiers changed from: private */
    public String Pref_Randkey;
    private String Pref_SSA;
    /* access modifiers changed from: private */
    public String Pref_Username;
    private String Pref_fms_firmname;
    private String Pref_fms_username;
    /* access modifiers changed from: private */
    public String androidId;
    /* access modifiers changed from: private */
    public AlertDialog error_dialog;
    /* access modifiers changed from: private */
    public LinearLayout lay_pms_tip_uplink;
    /* access modifiers changed from: private */
    public AlertDialog progress_dialog;
    private SharedPreferences sharedPreferences;
    /* access modifiers changed from: private */
    public TextView txt_alert;
    private TextView txt_header;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.pms_tip_uplink_activity);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        SharedPreferences sharedPreferences2 = getSharedPreferences("myloginpreference", 0);
        this.sharedPreferences = sharedPreferences2;
        this.Pref_Circle = sharedPreferences2.getString("circle_Key", (String) null);
        this.Pref_SSA = this.sharedPreferences.getString("ssa_Key", (String) null);
        this.Pref_Username = this.sharedPreferences.getString("username_Key", (String) null);
        this.Pref_Randkey = this.sharedPreferences.getString("rand_Key", (String) null);
        this.Pref_Designation = this.sharedPreferences.getString("designation_Key", (String) null);
        this.Pref_fms_username = this.sharedPreferences.getString("fms_username_Key", (String) null);
        this.Pref_fms_firmname = this.sharedPreferences.getString("fms_firm_Key", (String) null).toUpperCase();
        this.androidId = Settings.Secure.getString(getContentResolver(), "android_id");
        this.lay_pms_tip_uplink = (LinearLayout) findViewById(R.id.lay_pms_tip_uplink);
        this.txt_header = (TextView) findViewById(R.id.txt_header);
        String stringExtra = getIntent().getStringExtra("circle");
        String stringExtra2 = getIntent().getStringExtra("ssa");
        String stringExtra3 = getIntent().getStringExtra("olt_ip");
        String stringExtra4 = getIntent().getStringExtra("olt_vlan");
        String stringExtra5 = getIntent().getStringExtra("nas_ip");
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
        get_uplink_Detail(stringExtra, stringExtra2, stringExtra3, stringExtra4, stringExtra5, "6hr");
        this.txt_header.setText("  (UPLINK TRAFFIC - 6Hr)");
    }

    private void get_uplink_Detail(String str, String str2, String str3, String str4, String str5, String str6) {
        final int layoutHeight = getLayoutHeight();
        this.lay_pms_tip_uplink.removeAllViews();
        this.progress_dialog.show();
        RequestQueue newRequestQueue = Volley.newRequestQueue(this);
        final String str7 = str3;
        final String str8 = str4;
        final String str9 = str5;
        final String str10 = str6;
        final String str11 = str;
        final String str12 = str2;
        AnonymousClass3 r0 = new StringRequest(1, getString(R.string.serverip) + "PMS-TIP/pms_tip_uplink_traffic.php", new Response.Listener<String>() {
            public void onResponse(String str) {
                AnonymousClass1 r0 = this;
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    Iterator<String> keys = jSONObject.keys();
                    while (keys.hasNext()) {
                        JSONObject jSONObject2 = jSONObject.getJSONObject(keys.next());
                        ArrayList arrayList = new ArrayList();
                        new ArrayList();
                        ArrayList arrayList2 = new ArrayList();
                        new ArrayList();
                        ArrayList arrayList3 = new ArrayList();
                        View inflate = PMS_Tip_Uplink_Activity.this.getLayoutInflater().inflate(R.layout.custom_pms_uplink_traffic, (ViewGroup) null);
                        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, layoutHeight);
                        layoutParams.setMargins(10, 10, 10, 10);
                        inflate.setLayoutParams(layoutParams);
                        LineChart lineChart = (LineChart) inflate.findViewById(R.id.mychart);
                        LinearLayout linearLayout = (LinearLayout) inflate.findViewById(R.id.lay_traffic);
                        linearLayout.removeAllViews();
                        ((TextView) inflate.findViewById(R.id.txt_interface)).setText("UPLINK INTERFACE : " + jSONObject2.getString("DESC").toUpperCase());
                        JSONArray jSONArray = jSONObject2.getJSONArray("ROWSET");
                        int i = 0;
                        while (i < jSONArray.length()) {
                            JSONObject jSONObject3 = jSONArray.getJSONObject(i);
                            int i2 = jSONObject3.getInt("IN_TRAFFIC");
                            JSONObject jSONObject4 = jSONObject;
                            int i3 = jSONObject3.getInt("OUT_TRAFFIC");
                            Iterator<String> it = keys;
                            JSONArray jSONArray2 = jSONArray;
                            Date parse = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(jSONObject3.getString("TIMESTAMP"));
                            arrayList3.add(new SimpleDateFormat("dd-MMM, HH:mm").format(parse));
                            float f = (float) i;
                            View view = inflate;
                            arrayList.add(new Entry(f, (float) i2));
                            arrayList2.add(new Entry(f, (float) i3));
                            View inflate2 = PMS_Tip_Uplink_Activity.this.getLayoutInflater().inflate(R.layout.custom_pms_uplink_traffic1, (ViewGroup) null);
                            TableRow tableRow = (TableRow) inflate2.findViewById(R.id.tr_rows);
                            ArrayList arrayList4 = arrayList;
                            DecimalFormat decimalFormat = new DecimalFormat("#,###,###,###.##");
                            i++;
                            ((TextView) inflate2.findViewById(R.id.txt_sr)).setText("" + i);
                            ((TextView) inflate2.findViewById(R.id.txt_uplinkInterface)).setText(jSONObject2.getString("DESC").toUpperCase());
                            ((TextView) inflate2.findViewById(R.id.txt_inTraffic)).setText(decimalFormat.format((long) i2) + " Kbps");
                            ((TextView) inflate2.findViewById(R.id.txt_outTraffic)).setText(decimalFormat.format((long) i3) + " Kbps");
                            ((TextView) inflate2.findViewById(R.id.txt_timeStamp)).setText(new SimpleDateFormat("dd-MMM, HH:mm:ss").format(parse));
                            linearLayout.addView(inflate2);
                            r0 = this;
                            jSONObject = jSONObject4;
                            keys = it;
                            jSONArray = jSONArray2;
                            inflate = view;
                            lineChart = lineChart;
                            arrayList3 = arrayList3;
                            arrayList2 = arrayList2;
                            arrayList = arrayList4;
                        }
                        Iterator<String> it2 = keys;
                        View view2 = inflate;
                        LineDataSet lineDataSet = new LineDataSet(arrayList, "IN-TRAFFIC");
                        lineDataSet.setColor(-16776961);
                        lineDataSet.setDrawFilled(true);
                        lineDataSet.setFillColor(Color.parseColor("#cce6ff"));
                        lineDataSet.setDrawValues(false);
                        lineDataSet.setDrawCircles(false);
                        LineDataSet lineDataSet2 = new LineDataSet(arrayList2, "OUT-TRAFFIC");
                        lineDataSet2.setColor(SupportMenu.CATEGORY_MASK);
                        lineDataSet2.setDrawFilled(true);
                        lineDataSet2.setFillColor(Color.parseColor("#e6ccff"));
                        lineDataSet2.setDrawValues(false);
                        lineDataSet2.setDrawCircles(false);
                        LineData lineData = new LineData(lineDataSet, lineDataSet2);
                        ArrayList arrayList5 = arrayList3;
                        MyMarkerView myMarkerView = new MyMarkerView(PMS_Tip_Uplink_Activity.this, R.layout.custom_marker_view, "Kbps", arrayList5);
                        LineChart lineChart2 = lineChart;
                        myMarkerView.setChartView(lineChart2);
                        lineChart2.setMarker(myMarkerView);
                        lineChart2.getAxisRight().setDrawLabels(false);
                        lineChart2.getDescription().setEnabled(false);
                        lineChart2.getLegend().setEnabled(false);
                        lineChart2.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
                        lineChart2.getXAxis().setDrawGridLines(false);
                        lineChart2.getXAxis().setAxisMinimum(0.0f);
                        lineChart2.getXAxis().setLabelCount(arrayList5.size(), false);
                        XAxis xAxis = lineChart2.getXAxis();
                        xAxis.setValueFormatter(new IndexAxisValueFormatter((Collection<String>) arrayList5));
                        xAxis.setLabelRotationAngle(-90.0f);
                        lineChart2.getAxisLeft().setAxisMinimum(0.0f);
                        lineChart2.getAxisLeft().setGranularityEnabled(true);
                        lineChart2.getAxisLeft().setGranularity(10000.0f);
                        lineChart2.getAxisLeft().setDrawGridLines(false);
                        lineChart2.getAxisLeft().setLabelCount(arrayList5.size(), false);
                        lineChart2.getAxisRight().setDrawGridLines(false);
                        lineChart2.animateX(1000);
                        lineChart2.setData(lineData);
                        lineChart2.invalidate();
                        PMS_Tip_Uplink_Activity.this.lay_pms_tip_uplink.addView(view2);
                        r0 = this;
                        jSONObject = jSONObject;
                        keys = it2;
                    }
                    PMS_Tip_Uplink_Activity.this.progress_dialog.cancel();
                } catch (ParseException | JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError volleyError) {
                PMS_Tip_Uplink_Activity.this.progress_dialog.cancel();
                PMS_Tip_Uplink_Activity.this.txt_alert.setText(new VolleyErrorHelper().onErrorResponse(volleyError, PMS_Tip_Uplink_Activity.this.getApplicationContext()));
                PMS_Tip_Uplink_Activity.this.error_dialog.show();
            }
        }) {
            /* access modifiers changed from: protected */
            public Map<String, String> getParams() throws AuthFailureError {
                HashMap hashMap = new HashMap();
                hashMap.put("olt_ip", str7);
                hashMap.put("olt_vlan", str8);
                hashMap.put("nas_ip", str9);
                hashMap.put("duration", str10);
                hashMap.put("circle", str11);
                hashMap.put("ssa", str12);
                hashMap.put("username", PMS_Tip_Uplink_Activity.this.Pref_Username);
                hashMap.put("random_key", PMS_Tip_Uplink_Activity.this.Pref_Randkey);
                hashMap.put("device_id", PMS_Tip_Uplink_Activity.this.androidId);
                return hashMap;
            }
        };
        r0.setRetryPolicy(new DefaultRetryPolicy(BZip2Constants.BASEBLOCKSIZE, 1, 1.0f));
        newRequestQueue.add(r0);
    }

    public int getLayoutHeight() {
        int identifier = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (identifier > 0) {
            getResources().getDimensionPixelSize(identifier);
        }
        TypedValue typedValue = new TypedValue();
        getTheme().resolveAttribute(16843499, typedValue, true);
        int dimensionPixelSize = getResources().getDimensionPixelSize(typedValue.resourceId);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels - dimensionPixelSize;
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        String stringExtra = getIntent().getStringExtra("olt_ip");
        getMenuInflater().inflate(R.menu.snmp_menu, menu);
        menu.findItem(R.id.olt_ip).setTitle(stringExtra);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        String stringExtra = getIntent().getStringExtra("circle");
        String stringExtra2 = getIntent().getStringExtra("ssa");
        String stringExtra3 = getIntent().getStringExtra("olt_ip");
        String stringExtra4 = getIntent().getStringExtra("olt_vlan");
        String stringExtra5 = getIntent().getStringExtra("nas_ip");
        if (itemId == R.id.snmp_6) {
            this.txt_header.setText("  (UPLINK TRAFFIC - 6Hr)");
            get_uplink_Detail(stringExtra, stringExtra2, stringExtra3, stringExtra4, stringExtra5, "6hr");
        } else if (itemId == R.id.snmp_12) {
            this.txt_header.setText("  (UPLINK TRAFFIC - 12Hr)");
            get_uplink_Detail(stringExtra, stringExtra2, stringExtra3, stringExtra4, stringExtra5, "12hr");
        } else if (itemId == R.id.snmp_18) {
            this.txt_header.setText("  (UPLINK TRAFFIC - 18Hr)");
            get_uplink_Detail(stringExtra, stringExtra2, stringExtra3, stringExtra4, stringExtra5, "18hr");
        } else if (itemId == R.id.snmp_24) {
            this.txt_header.setText("  (UPLINK TRAFFIC - 24Hr)");
            get_uplink_Detail(stringExtra, stringExtra2, stringExtra3, stringExtra4, stringExtra5, "24hr");
        }
        return super.onOptionsItemSelected(menuItem);
    }
}
