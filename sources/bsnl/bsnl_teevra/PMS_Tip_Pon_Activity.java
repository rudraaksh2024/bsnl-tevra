package bsnl.bsnl_teevra;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.internal.view.SupportMenu;
import androidx.core.view.ViewCompat;
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
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PMS_Tip_Pon_Activity extends AppCompatActivity {
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
    public LinearLayout lay_pms_tip_pon;
    /* access modifiers changed from: private */
    public AlertDialog progress_dialog;
    private SharedPreferences sharedPreferences;
    /* access modifiers changed from: private */
    public TextView txt_alert;
    private TextView txt_header;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.pms_tip_pon_activity);
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
        this.lay_pms_tip_pon = (LinearLayout) findViewById(R.id.lay_pms_tip_pon);
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
        get_pon_Detail(stringExtra, stringExtra2, stringExtra3, stringExtra4, stringExtra5, "6hr");
        this.txt_header.setText("  (PON TRAFFIC - 6Hr)");
    }

    private void get_pon_Detail(String str, String str2, String str3, String str4, String str5, String str6) {
        final int layoutHeight = getLayoutHeight();
        this.lay_pms_tip_pon.removeAllViews();
        this.progress_dialog.show();
        RequestQueue newRequestQueue = Volley.newRequestQueue(this);
        final String str7 = str3;
        final String str8 = str4;
        final String str9 = str5;
        final String str10 = str6;
        final String str11 = str;
        final String str12 = str2;
        AnonymousClass3 r0 = new StringRequest(1, getString(R.string.serverip) + "PMS-TIP/pms_tip_pon_traffic.php", new Response.Listener<String>() {
            public void onResponse(String str) {
                PMS_Tip_Pon_Activity.this.progress_dialog.cancel();
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
                        final LinearLayout linearLayout = new LinearLayout(PMS_Tip_Pon_Activity.this);
                        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, layoutHeight / 2);
                        layoutParams.setMargins(10, 5, 10, 5);
                        linearLayout.setLayoutParams(layoutParams);
                        linearLayout.setPadding(0, 0, 0, 25);
                        linearLayout.setOrientation(1);
                        linearLayout.setBackgroundResource(R.drawable.new_style);
                        TextView textView = new TextView(PMS_Tip_Pon_Activity.this);
                        textView.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
                        textView.setText("PON INTERFACE : " + jSONObject2.getString("DESC").toUpperCase());
                        textView.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                        textView.setPaintFlags(8);
                        textView.setGravity(17);
                        textView.setPadding(15, 15, 15, 0);
                        textView.setTypeface((Typeface) null, 1);
                        textView.setTextSize(0, PMS_Tip_Pon_Activity.this.getResources().getDimension(R.dimen.mediumtext));
                        LinearLayout linearLayout2 = new LinearLayout(PMS_Tip_Pon_Activity.this);
                        linearLayout2.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
                        linearLayout2.setOrientation(0);
                        linearLayout2.setGravity(16);
                        linearLayout2.setPadding(25, 0, 25, 0);
                        ImageView imageView = new ImageView(PMS_Tip_Pon_Activity.this);
                        imageView.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
                        imageView.setPadding(10, 10, 10, 10);
                        imageView.setBackgroundResource(R.drawable.ic_new_circle_purple);
                        imageView.setClickable(true);
                        imageView.setImageResource(R.drawable.ic_new_share_white);
                        LinearLayout linearLayout3 = new LinearLayout(PMS_Tip_Pon_Activity.this);
                        linearLayout3.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
                        linearLayout3.setOrientation(0);
                        linearLayout3.setGravity(5);
                        linearLayout3.setPadding(0, 0, 15, 0);
                        TextView textView2 = new TextView(PMS_Tip_Pon_Activity.this);
                        textView2.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
                        textView2.setCompoundDrawablesWithIntrinsicBounds(R.drawable.circle_blue, 0, 0, 0);
                        textView2.setText("IN-TRAFFIC (Kbps)");
                        textView2.setGravity(17);
                        textView2.setTextColor(-16776961);
                        textView2.setPaintFlags(8);
                        textView2.setTypeface((Typeface) null, 1);
                        textView2.setTextSize(0, PMS_Tip_Pon_Activity.this.getResources().getDimension(R.dimen.smalltext));
                        TextView textView3 = new TextView(PMS_Tip_Pon_Activity.this);
                        textView3.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
                        textView3.setCompoundDrawablesWithIntrinsicBounds(R.drawable.circle_red, 0, 0, 0);
                        textView3.setText("OUT-TRAFFIC (Kbps)");
                        textView3.setGravity(17);
                        textView3.setTextColor(SupportMenu.CATEGORY_MASK);
                        textView3.setPaintFlags(8);
                        textView3.setTypeface((Typeface) null, 1);
                        textView3.setTextSize(0, PMS_Tip_Pon_Activity.this.getResources().getDimension(R.dimen.smalltext));
                        linearLayout3.addView(textView3);
                        linearLayout3.addView(textView2);
                        linearLayout2.addView(imageView);
                        linearLayout2.addView(linearLayout3);
                        JSONArray jSONArray = jSONObject2.getJSONArray("ROWSET");
                        int i = 0;
                        while (i < jSONArray.length()) {
                            JSONObject jSONObject3 = jSONArray.getJSONObject(i);
                            int i2 = jSONObject3.getInt("IN_TRAFFIC");
                            int i3 = jSONObject3.getInt("OUT_TRAFFIC");
                            JSONObject jSONObject4 = jSONObject;
                            arrayList3.add(new SimpleDateFormat("dd-MMM, HH:mm").format(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(jSONObject3.getString("TIMESTAMP"))));
                            float f = (float) i;
                            arrayList.add(new Entry(f, (float) i2));
                            arrayList2.add(new Entry(f, (float) i3));
                            i++;
                            jSONObject = jSONObject4;
                        }
                        JSONObject jSONObject5 = jSONObject;
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
                        LineChart lineChart = new LineChart(PMS_Tip_Pon_Activity.this);
                        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, -1);
                        layoutParams2.setMargins(5, 5, 5, 5);
                        lineChart.setLayoutParams(layoutParams2);
                        lineChart.setPadding(5, 5, 5, 5);
                        MyMarkerView myMarkerView = new MyMarkerView(PMS_Tip_Pon_Activity.this, R.layout.custom_marker_view, "Kbps", arrayList3);
                        myMarkerView.setChartView(lineChart);
                        lineChart.setMarker(myMarkerView);
                        lineChart.getAxisRight().setDrawLabels(false);
                        lineChart.getDescription().setEnabled(false);
                        lineChart.getLegend().setEnabled(false);
                        lineChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
                        lineChart.getXAxis().setDrawGridLines(false);
                        lineChart.getXAxis().setAxisMinimum(0.0f);
                        lineChart.getXAxis().setLabelCount(arrayList3.size(), false);
                        XAxis xAxis = lineChart.getXAxis();
                        xAxis.setValueFormatter(new IndexAxisValueFormatter((Collection<String>) arrayList3));
                        xAxis.setLabelRotationAngle(-90.0f);
                        lineChart.getAxisLeft().setAxisMinimum(0.0f);
                        lineChart.getAxisLeft().setGranularityEnabled(true);
                        lineChart.getAxisLeft().setGranularity(10000.0f);
                        lineChart.getAxisLeft().setDrawGridLines(false);
                        lineChart.getAxisLeft().setLabelCount(arrayList3.size(), false);
                        lineChart.getAxisRight().setDrawGridLines(false);
                        lineChart.animateX(1000);
                        lineChart.setData(lineData);
                        lineChart.invalidate();
                        linearLayout.addView(textView);
                        linearLayout.addView(linearLayout2);
                        linearLayout.addView(lineChart);
                        imageView.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View view) {
                                Bitmap createBitmap = Bitmap.createBitmap(linearLayout.getWidth(), linearLayout.getHeight(), Bitmap.Config.ARGB_8888);
                                linearLayout.draw(new Canvas(createBitmap));
                                new ShareImage().share(createBitmap, new File(PMS_Tip_Pon_Activity.this.getApplicationContext().getExternalCacheDir(), File.separator + "PMS_PON(" + str7 + ").jpg"), PMS_Tip_Pon_Activity.this);
                            }
                        });
                        PMS_Tip_Pon_Activity.this.lay_pms_tip_pon.addView(linearLayout);
                        jSONObject = jSONObject5;
                    }
                } catch (ParseException | JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError volleyError) {
                PMS_Tip_Pon_Activity.this.progress_dialog.cancel();
                PMS_Tip_Pon_Activity.this.txt_alert.setText(new VolleyErrorHelper().onErrorResponse(volleyError, PMS_Tip_Pon_Activity.this.getApplicationContext()));
                PMS_Tip_Pon_Activity.this.error_dialog.show();
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
                hashMap.put("username", PMS_Tip_Pon_Activity.this.Pref_Username);
                hashMap.put("random_key", PMS_Tip_Pon_Activity.this.Pref_Randkey);
                hashMap.put("device_id", PMS_Tip_Pon_Activity.this.androidId);
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
            this.txt_header.setText("  (PON TRAFFIC - 6Hr)");
            get_pon_Detail(stringExtra, stringExtra2, stringExtra3, stringExtra4, stringExtra5, "6hr");
        } else if (itemId == R.id.snmp_12) {
            this.txt_header.setText("  (PON TRAFFIC - 12Hr)");
            get_pon_Detail(stringExtra, stringExtra2, stringExtra3, stringExtra4, stringExtra5, "12hr");
        } else if (itemId == R.id.snmp_18) {
            this.txt_header.setText("  (PON TRAFFIC - 18Hr)");
            get_pon_Detail(stringExtra, stringExtra2, stringExtra3, stringExtra4, stringExtra5, "18hr");
        } else if (itemId == R.id.snmp_24) {
            this.txt_header.setText("  (PON TRAFFIC - 24Hr)");
            get_pon_Detail(stringExtra, stringExtra2, stringExtra3, stringExtra4, stringExtra5, "24hr");
        }
        return super.onOptionsItemSelected(menuItem);
    }
}
