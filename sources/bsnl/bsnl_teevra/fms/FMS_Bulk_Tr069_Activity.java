package bsnl.bsnl_teevra.fms;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.provider.Settings;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import bsnl.bsnl_teevra.R;
import bsnl.bsnl_teevra.ResizeData;
import bsnl.bsnl_teevra.VolleyErrorHelper;
import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FMS_Bulk_Tr069_Activity extends AppCompatActivity implements OnChartValueSelectedListener, View.OnClickListener {
    private static final int BATCH_SIZE = 75;
    private JSONArray Hetro_array;
    private JSONArray Homo_config_array;
    private JSONArray Homo_nonconfig_array;
    public int NUM_ITEMS_PAGE = 50;
    private JSONArray Offline_array;
    private JSONArray Ont_array;
    private String Pref_Access_Level;
    private String Pref_Circle;
    private String Pref_Designation;
    private String Pref_Fms_Username;
    private String Pref_Nw_Glance;
    /* access modifiers changed from: private */
    public String Pref_Randkey;
    private String Pref_Role;
    private String Pref_SSA;
    /* access modifiers changed from: private */
    public String Pref_Username;
    private String Pref_fms_firmname;
    private String Pref_fms_username;
    private JSONArray Row_Array;
    /* access modifiers changed from: private */
    public JSONArray Split_Ont_Array;
    public int TOTAL_LIST_ITEMS = 0;
    private BaseAdapter_TR069 adapter;
    /* access modifiers changed from: private */
    public String androidId;
    private LinearLayout btnLay;
    private TextView[] btns;
    private PieChart chart;
    /* access modifiers changed from: private */
    public String consent;
    /* access modifiers changed from: private */
    public AlertDialog consent_dialog;
    /* access modifiers changed from: private */
    public int countt = 0;
    private BaseAdapter_TR069 customBaseAdapter;
    SharedPreferences.Editor editor;
    /* access modifiers changed from: private */
    public AlertDialog error_dialog;
    private EditText et_search;
    /* access modifiers changed from: private */
    public AlertDialog info_dialog;
    private LinearLayout lay_btn;
    private ListView listview;
    private AlertDialog logout_dialog;
    private TextView next;
    private TextView prev;
    /* access modifiers changed from: private */
    public AlertDialog progress_dialog;
    private int search_count = 0;
    SharedPreferences sharedPreferences;
    private TextView title;
    private JSONArray total_array;
    /* access modifiers changed from: private */
    public TextView txt_alert;
    private TextView txt_element;
    private TextView txt_header;
    /* access modifiers changed from: private */
    public TextView txt_submit;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.fms_bulk_tr069_activity);
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
        this.txt_element = (TextView) findViewById(R.id.txt_element);
        this.txt_submit = (TextView) findViewById(R.id.txt_submit);
        this.chart = (PieChart) findViewById(R.id.piechart);
        this.et_search = (EditText) findViewById(R.id.et_search);
        this.btnLay = (LinearLayout) findViewById(R.id.btnLay);
        this.lay_btn = (LinearLayout) findViewById(R.id.lay_btn);
        this.prev = (TextView) findViewById(R.id.prev);
        this.next = (TextView) findViewById(R.id.next);
        this.title = (TextView) findViewById(R.id.title);
        this.txt_header = (TextView) findViewById(R.id.txt_header);
        this.listview = (ListView) findViewById(R.id.listView);
        this.txt_element.setText(" (" + getIntent().getStringExtra("IP") + ")");
        try {
            this.total_array = new JSONArray();
            this.Offline_array = new JSONArray();
            this.Homo_config_array = new JSONArray();
            this.Homo_nonconfig_array = new JSONArray();
            this.Hetro_array = new JSONArray();
            this.Ont_array = new JSONArray();
            this.Row_Array = new JSONArray();
            JSONArray jSONArray = new JSONObject(ResizeData.decompressData(getIntent().getByteArrayExtra("RESPONSE"))).getJSONArray("ROWSET");
            this.total_array = jSONArray;
            this.Row_Array = jSONArray;
            this.TOTAL_LIST_ITEMS = jSONArray.length();
            Btnfooter();
            loadList(0);
            CheckBtnBackGroud(0);
            for (int i = 0; i < this.total_array.length(); i++) {
                JSONObject jSONObject = this.total_array.getJSONObject(i);
                String string = jSONObject.getString("ONLINE");
                String string2 = jSONObject.getString("HOMOGENEOUS");
                String string3 = jSONObject.getString("CONFIGURABLE");
                if (string.equals("N")) {
                    this.Offline_array.put(jSONObject);
                } else if (!string2.equals("Y")) {
                    this.Hetro_array.put(jSONObject);
                } else if (string3.equals("Y")) {
                    this.Homo_config_array.put(jSONObject);
                    this.Ont_array.put(jSONObject.getString("PON_PORT"));
                } else {
                    this.Homo_nonconfig_array.put(jSONObject);
                }
            }
            int length = this.Offline_array.length();
            int length2 = this.Homo_config_array.length();
            int length3 = this.Homo_nonconfig_array.length();
            int length4 = this.Hetro_array.length();
            ArrayList arrayList = new ArrayList();
            arrayList.add(new PieEntry((float) length, "OFFLINE"));
            arrayList.add(new PieEntry((float) length2, "CONFIGURABLE"));
            arrayList.add(new PieEntry((float) length3, "NO ACTION NEEDED"));
            arrayList.add(new PieEntry((float) length4, "NON HOMOGENEOUS"));
            ArrayList arrayList2 = new ArrayList();
            for (int valueOf : ColorTemplate.VORDIPLOM_COLORS) {
                arrayList2.add(Integer.valueOf(valueOf));
            }
            for (int valueOf2 : ColorTemplate.JOYFUL_COLORS) {
                arrayList2.add(Integer.valueOf(valueOf2));
            }
            for (int valueOf3 : ColorTemplate.COLORFUL_COLORS) {
                arrayList2.add(Integer.valueOf(valueOf3));
            }
            for (int valueOf4 : ColorTemplate.LIBERTY_COLORS) {
                arrayList2.add(Integer.valueOf(valueOf4));
            }
            Collections.shuffle(arrayList2);
            PieDataSet pieDataSet = new PieDataSet(arrayList, "");
            pieDataSet.setValueFormatter(new ValueFormatter() {
                public String getFormattedValue(float f) {
                    return "" + ((int) f);
                }
            });
            AnonymousClass2 r2 = new ValueFormatter() {
                public String getFormattedValue(float f) {
                    return "" + ((int) f);
                }
            };
            pieDataSet.setColors((List<Integer>) arrayList2);
            PieData pieData = new PieData(pieDataSet);
            pieData.setDrawValues(true);
            pieData.setValueFormatter(r2);
            pieData.setValueTextSize(15.0f);
            pieData.setValueTextColor(ViewCompat.MEASURED_STATE_MASK);
            pieData.setValueTypeface(Typeface.DEFAULT_BOLD);
            pieDataSet.setSliceSpace(2.0f);
            pieDataSet.setSelectionShift(3.0f);
            this.chart.setExtraOffsets(0.0f, 0.0f, 0.0f, -5.0f);
            this.chart.setMinOffset(0.0f);
            this.chart.setData(pieData);
            this.chart.spin(1400, 0.0f, 450.0f, Easing.EaseInOutQuad);
            this.chart.setRotationAngle(0.0f);
            this.chart.setRotationEnabled(true);
            this.chart.setDrawHoleEnabled(true);
            this.chart.setEntryLabelTextSize(12.0f);
            this.chart.setEntryLabelColor(ViewCompat.MEASURED_STATE_MASK);
            this.chart.setCenterTextSize(20.0f);
            this.chart.getDescription().setEnabled(false);
            this.chart.setOnChartValueSelectedListener(this);
            this.chart.setDrawEntryLabels(false);
            this.chart.invalidate();
            Legend legend = this.chart.getLegend();
            legend.setTextSize(10.0f);
            legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
            legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
            legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
            legend.setDrawInside(false);
            legend.setEnabled(true);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e2) {
            throw new RuntimeException(e2);
        }
        this.txt_submit.setOnClickListener(this);
    }

    private String decompress(byte[] bArr) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream;
        GZIPInputStream gZIPInputStream = new GZIPInputStream(new ByteArrayInputStream(bArr));
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr2 = new byte[1024];
            while (true) {
                int read = gZIPInputStream.read(bArr2);
                if (read != -1) {
                    byteArrayOutputStream.write(bArr2, 0, read);
                } else {
                    String byteArrayOutputStream2 = byteArrayOutputStream.toString();
                    byteArrayOutputStream.close();
                    gZIPInputStream.close();
                    return byteArrayOutputStream2;
                }
            }
        } catch (Throwable th) {
            try {
                gZIPInputStream.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
        throw th;
    }

    private void Btnfooter() {
        this.lay_btn.removeAllViews();
        this.countt = 0;
        int i = this.TOTAL_LIST_ITEMS;
        int i2 = this.NUM_ITEMS_PAGE;
        final int i3 = (i / i2) + (i % i2 == 0 ? 0 : 1);
        this.prev.setPadding(20, 15, 20, 15);
        this.next.setPadding(20, 15, 20, 15);
        this.btns = new TextView[i3];
        final int i4 = 0;
        while (i4 < i3) {
            this.btns[i4] = new TextView(this);
            this.btns[i4].setBackgroundColor(getResources().getColor(17170445));
            int i5 = i4 + 1;
            this.btns[i4].setText("" + i5);
            this.btns[i4].setTextColor(ViewCompat.MEASURED_STATE_MASK);
            this.btns[i4].setTypeface(Typeface.DEFAULT_BOLD);
            this.btns[i4].setGravity(17);
            this.btns[i4].setPadding(20, 15, 20, 15);
            this.btns[i4].setTextSize(0, getResources().getDimension(R.dimen.smalltext));
            this.btns[i4].setBackgroundResource(R.drawable.button01);
            this.lay_btn.addView(this.btns[i4], new LinearLayout.LayoutParams(-2, -1));
            this.next.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (FMS_Bulk_Tr069_Activity.this.countt + 1 >= i3) {
                        Toast.makeText(FMS_Bulk_Tr069_Activity.this.getApplicationContext(), "Last Page", 1).show();
                        return;
                    }
                    FMS_Bulk_Tr069_Activity fMS_Bulk_Tr069_Activity = FMS_Bulk_Tr069_Activity.this;
                    fMS_Bulk_Tr069_Activity.loadList(fMS_Bulk_Tr069_Activity.countt + 1);
                    FMS_Bulk_Tr069_Activity fMS_Bulk_Tr069_Activity2 = FMS_Bulk_Tr069_Activity.this;
                    fMS_Bulk_Tr069_Activity2.CheckBtnBackGroud(fMS_Bulk_Tr069_Activity2.countt + 1);
                    FMS_Bulk_Tr069_Activity fMS_Bulk_Tr069_Activity3 = FMS_Bulk_Tr069_Activity.this;
                    int unused = fMS_Bulk_Tr069_Activity3.countt = fMS_Bulk_Tr069_Activity3.countt + 1;
                }
            });
            this.prev.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (FMS_Bulk_Tr069_Activity.this.countt == 0) {
                        Toast.makeText(FMS_Bulk_Tr069_Activity.this.getApplicationContext(), "First Page", 1).show();
                        return;
                    }
                    FMS_Bulk_Tr069_Activity fMS_Bulk_Tr069_Activity = FMS_Bulk_Tr069_Activity.this;
                    fMS_Bulk_Tr069_Activity.loadList(fMS_Bulk_Tr069_Activity.countt - 1);
                    FMS_Bulk_Tr069_Activity fMS_Bulk_Tr069_Activity2 = FMS_Bulk_Tr069_Activity.this;
                    fMS_Bulk_Tr069_Activity2.CheckBtnBackGroud(fMS_Bulk_Tr069_Activity2.countt - 1);
                    FMS_Bulk_Tr069_Activity fMS_Bulk_Tr069_Activity3 = FMS_Bulk_Tr069_Activity.this;
                    int unused = fMS_Bulk_Tr069_Activity3.countt = fMS_Bulk_Tr069_Activity3.countt - 1;
                }
            });
            this.btns[i4].setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    FMS_Bulk_Tr069_Activity.this.loadList(i4);
                    FMS_Bulk_Tr069_Activity.this.CheckBtnBackGroud(i4);
                    int unused = FMS_Bulk_Tr069_Activity.this.countt = i4;
                }
            });
            i4 = i5;
        }
    }

    /* access modifiers changed from: private */
    public void loadList(int i) {
        JSONArray jSONArray = new JSONArray();
        int i2 = i * this.NUM_ITEMS_PAGE;
        int i3 = i2;
        while (i3 < this.NUM_ITEMS_PAGE + i2 && i3 < this.Row_Array.length()) {
            try {
                jSONArray.put(this.Row_Array.getJSONObject(i3));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            i3++;
        }
        BaseAdapter_TR069 baseAdapter_TR069 = new BaseAdapter_TR069(getApplicationContext(), jSONArray, i2);
        this.adapter = baseAdapter_TR069;
        this.listview.setAdapter(baseAdapter_TR069);
    }

    /* access modifiers changed from: private */
    public void CheckBtnBackGroud(int i) {
        int i2 = this.TOTAL_LIST_ITEMS;
        int i3 = this.NUM_ITEMS_PAGE;
        int i4 = (i2 / i3) + (i2 % i3 == 0 ? 0 : 1);
        this.title.setText("Page " + (i + 1) + " of " + i4 + " Pages");
        for (int i5 = 0; i5 < i4; i5++) {
            if (i5 == i) {
                this.btns[i].setBackgroundDrawable(getResources().getDrawable(R.drawable.button));
                this.btns[i5].setTextColor(getResources().getColor(R.color.colorwhite));
            } else {
                this.btns[i5].setBackgroundColor(getResources().getColor(17170445));
                this.btns[i5].setTextColor(getResources().getColor(R.color.colorblack));
            }
        }
    }

    public void onValueSelected(Entry entry, Highlight highlight) {
        entry.getY();
        int x = (int) highlight.getX();
        this.Row_Array = new JSONArray();
        if (x == 0) {
            this.Row_Array = this.Offline_array;
            this.txt_header.setText("OFFLINE ONT INFORMATION");
        } else if (x == 1) {
            this.Row_Array = this.Homo_config_array;
            this.txt_header.setText("CONFIGURABLE ONT WAN INFORMATION");
        } else if (x == 2) {
            this.Row_Array = this.Homo_nonconfig_array;
            this.txt_header.setText("ONT WAN INFORMATION (NO ACTION NEEDED)");
        } else if (x == 3) {
            this.Row_Array = this.Hetro_array;
            this.txt_header.setText("NON HOMOGENEOUS ONT WAN INFORMATION");
        }
        this.TOTAL_LIST_ITEMS = this.Row_Array.length();
        Btnfooter();
        loadList(0);
        CheckBtnBackGroud(0);
    }

    public void onNothingSelected() {
        new JSONArray();
        JSONArray jSONArray = this.total_array;
        this.Row_Array = jSONArray;
        this.TOTAL_LIST_ITEMS = jSONArray.length();
        Btnfooter();
        loadList(0);
        CheckBtnBackGroud(0);
        this.txt_header.setText("ONT WAN INTERFACE INFORMATION");
    }

    public void onClick(View view) {
        if (this.Ont_array.length() == 0) {
            this.txt_alert.setText("Zero Configurable ONT Is There In OLT\nHence No Action Is Required");
            this.error_dialog.show();
            return;
        }
        this.Split_Ont_Array = new JSONArray();
        if (this.Ont_array.length() >= 75) {
            int i = 0;
            while (i < 75) {
                try {
                    this.Split_Ont_Array.put(this.Ont_array.get(i));
                    i++;
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            View inflate = getLayoutInflater().inflate(R.layout.custom_info, (ViewGroup) null);
            TextView textView = (TextView) inflate.findViewById(R.id.txt_update);
            textView.setText("OK");
            builder.setCancelable(true);
            builder.setView(inflate);
            this.info_dialog = builder.create();
            ((TextView) inflate.findViewById(R.id.txt_error)).setText(Html.fromHtml("Number Of <b><i>Configurable ONTs (" + this.Ont_array.length() + ")</i></b> Is High.<br>To Ensure Seamless Execution, The Activity Will Be Performed In <b><i>Batches Of " + 75 + " ONTs</i></b><br><br>Do You Want To Proceed", 63));
            ((TextView) inflate.findViewById(R.id.txt_cancel)).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    FMS_Bulk_Tr069_Activity.this.info_dialog.cancel();
                }
            });
            textView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    FMS_Bulk_Tr069_Activity.this.info_dialog.cancel();
                    FMS_Bulk_Tr069_Activity.this.xyz();
                }
            });
            this.info_dialog.show();
            return;
        }
        this.Split_Ont_Array = this.Ont_array;
        xyz();
    }

    /* access modifiers changed from: private */
    public void xyz() {
        Log.v("SPLIT ONT ARRAY2", this.Split_Ont_Array.toString());
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View inflate = getLayoutInflater().inflate(R.layout.custom_consent, (ViewGroup) null);
        final CheckBox checkBox = (CheckBox) inflate.findViewById(R.id.chk_consent);
        builder.setCancelable(false);
        builder.setView(inflate);
        this.consent_dialog = builder.create();
        ((TextView) inflate.findViewById(R.id.txt_cancel)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                FMS_Bulk_Tr069_Activity.this.consent_dialog.cancel();
            }
        });
        ((TextView) inflate.findViewById(R.id.txt_update)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (checkBox.isChecked()) {
                    String unused = FMS_Bulk_Tr069_Activity.this.consent = "Y";
                } else {
                    String unused2 = FMS_Bulk_Tr069_Activity.this.consent = "N";
                }
                String stringExtra = FMS_Bulk_Tr069_Activity.this.getIntent().getStringExtra("IP");
                String stringExtra2 = FMS_Bulk_Tr069_Activity.this.getIntent().getStringExtra("OLT_VLAN");
                String stringExtra3 = FMS_Bulk_Tr069_Activity.this.getIntent().getStringExtra("NAS_IP");
                FMS_Bulk_Tr069_Activity.this.consent_dialog.cancel();
                FMS_Bulk_Tr069_Activity fMS_Bulk_Tr069_Activity = FMS_Bulk_Tr069_Activity.this;
                fMS_Bulk_Tr069_Activity.tr069_config(stringExtra, stringExtra2, stringExtra3, fMS_Bulk_Tr069_Activity.consent);
            }
        });
        this.consent_dialog.show();
    }

    /* access modifiers changed from: private */
    public void tr069_config(final String str, String str2, String str3, String str4) {
        this.progress_dialog.show();
        RequestQueue newRequestQueue = Volley.newRequestQueue(this);
        final String str5 = str;
        final String str6 = str2;
        final String str7 = str3;
        final String str8 = str4;
        AnonymousClass12 r0 = new StringRequest(1, getString(R.string.serverip) + "bbnw/onu_tr069_config.php", new Response.Listener<String>() {
            public void onResponse(String str) {
                FMS_Bulk_Tr069_Activity.this.progress_dialog.cancel();
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    Boolean valueOf = Boolean.valueOf(jSONObject.getBoolean("LOGIN_SUCCESS"));
                    String string = jSONObject.getString("LOGIN_ERROR");
                    if (valueOf.booleanValue()) {
                        FMS_Bulk_Tr069_Activity.this.txt_submit.setEnabled(false);
                        String string2 = jSONObject.getString("IDENTIFIER");
                        Intent intent = new Intent(FMS_Bulk_Tr069_Activity.this, FMS_Bulk_TR069_Config_Activity.class);
                        intent.putExtra("RESPONSE", jSONObject.toString());
                        intent.putExtra("IP", str);
                        intent.putExtra("IDENTIFIER", string2);
                        FMS_Bulk_Tr069_Activity.this.startActivity(intent);
                        return;
                    }
                    FMS_Bulk_Tr069_Activity.this.txt_alert.setText(string);
                    FMS_Bulk_Tr069_Activity.this.error_dialog.show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError volleyError) {
                FMS_Bulk_Tr069_Activity.this.progress_dialog.cancel();
                FMS_Bulk_Tr069_Activity.this.txt_alert.setText(new VolleyErrorHelper().onErrorResponse(volleyError, FMS_Bulk_Tr069_Activity.this.getApplicationContext()));
                FMS_Bulk_Tr069_Activity.this.error_dialog.show();
            }
        }) {
            public Request.Priority getPriority() {
                return Request.Priority.HIGH;
            }

            /* access modifiers changed from: protected */
            public Map<String, String> getParams() throws AuthFailureError {
                HashMap hashMap = new HashMap();
                hashMap.put("olt_ip", str5);
                hashMap.put("olt_vlan", str6);
                hashMap.put("nas_ip", str7);
                hashMap.put("consent", str8);
                hashMap.put("onts", FMS_Bulk_Tr069_Activity.this.Split_Ont_Array.toString());
                hashMap.put("username", FMS_Bulk_Tr069_Activity.this.Pref_Username);
                hashMap.put("random_key", FMS_Bulk_Tr069_Activity.this.Pref_Randkey);
                hashMap.put("device_id", FMS_Bulk_Tr069_Activity.this.androidId);
                return hashMap;
            }
        };
        r0.setRetryPolicy(new DefaultRetryPolicy(900000, 1, 1.0f));
        newRequestQueue.add(r0);
    }
}
