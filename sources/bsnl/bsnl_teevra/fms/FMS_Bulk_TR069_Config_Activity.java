package bsnl.bsnl_teevra.fms;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.core.view.ViewCompat;
import bsnl.bsnl_teevra.R;
import bsnl.bsnl_teevra.Storage_Permission;
import bsnl.bsnl_teevra.VolleyErrorHelper;
import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FMS_Bulk_TR069_Config_Activity extends AppCompatActivity implements View.OnClickListener {
    public int NUM_ITEMS_PAGE = 50;
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
    public int TOTAL_LIST_ITEMS = 0;
    private BaseAdapter_TR069 adapter;
    /* access modifiers changed from: private */
    public String androidId;
    private LinearLayout btnLay;
    private TextView[] btns;
    /* access modifiers changed from: private */
    public int countt = 0;
    private BaseAdapter_TR069 customBaseAdapter;
    SharedPreferences.Editor editor;
    /* access modifiers changed from: private */
    public AlertDialog error_dialog;
    private ImageView img_download;
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
    private Storage_Permission storagePermission;
    private TextView title;
    private JSONArray total_array;
    /* access modifiers changed from: private */
    public TextView txt_alert;
    private TextView txt_element;
    private TextView txt_header;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.fms_bulk_tr069_config_activity);
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
        this.img_download = (ImageView) findViewById(R.id.img_download);
        this.txt_element = (TextView) findViewById(R.id.txt_element);
        this.btnLay = (LinearLayout) findViewById(R.id.btnLay);
        this.lay_btn = (LinearLayout) findViewById(R.id.lay_btn);
        this.prev = (TextView) findViewById(R.id.prev);
        this.next = (TextView) findViewById(R.id.next);
        this.title = (TextView) findViewById(R.id.title);
        this.txt_header = (TextView) findViewById(R.id.txt_header);
        this.listview = (ListView) findViewById(R.id.listView);
        this.txt_element.setText(" (" + getIntent().getStringExtra("IP") + ")");
        try {
            this.Row_Array = new JSONArray();
            JSONArray jSONArray = new JSONObject(getIntent().getStringExtra("RESPONSE")).getJSONArray("ROWSET");
            this.total_array = jSONArray;
            this.Row_Array = jSONArray;
            this.TOTAL_LIST_ITEMS = jSONArray.length();
            Btnfooter();
            loadList(0);
            CheckBtnBackGroud(0);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.img_download.setOnClickListener(this);
    }

    private void Btnfooter() {
        this.lay_btn.removeAllViews();
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
                    if (FMS_Bulk_TR069_Config_Activity.this.countt + 1 >= i3) {
                        Toast.makeText(FMS_Bulk_TR069_Config_Activity.this.getApplicationContext(), "Last Page", 1).show();
                        return;
                    }
                    FMS_Bulk_TR069_Config_Activity fMS_Bulk_TR069_Config_Activity = FMS_Bulk_TR069_Config_Activity.this;
                    fMS_Bulk_TR069_Config_Activity.loadList(fMS_Bulk_TR069_Config_Activity.countt + 1);
                    FMS_Bulk_TR069_Config_Activity fMS_Bulk_TR069_Config_Activity2 = FMS_Bulk_TR069_Config_Activity.this;
                    fMS_Bulk_TR069_Config_Activity2.CheckBtnBackGroud(fMS_Bulk_TR069_Config_Activity2.countt + 1);
                    FMS_Bulk_TR069_Config_Activity fMS_Bulk_TR069_Config_Activity3 = FMS_Bulk_TR069_Config_Activity.this;
                    int unused = fMS_Bulk_TR069_Config_Activity3.countt = fMS_Bulk_TR069_Config_Activity3.countt + 1;
                }
            });
            this.prev.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (FMS_Bulk_TR069_Config_Activity.this.countt == 0) {
                        Toast.makeText(FMS_Bulk_TR069_Config_Activity.this.getApplicationContext(), "First Page", 1).show();
                        return;
                    }
                    FMS_Bulk_TR069_Config_Activity fMS_Bulk_TR069_Config_Activity = FMS_Bulk_TR069_Config_Activity.this;
                    fMS_Bulk_TR069_Config_Activity.loadList(fMS_Bulk_TR069_Config_Activity.countt - 1);
                    FMS_Bulk_TR069_Config_Activity fMS_Bulk_TR069_Config_Activity2 = FMS_Bulk_TR069_Config_Activity.this;
                    fMS_Bulk_TR069_Config_Activity2.CheckBtnBackGroud(fMS_Bulk_TR069_Config_Activity2.countt - 1);
                    FMS_Bulk_TR069_Config_Activity fMS_Bulk_TR069_Config_Activity3 = FMS_Bulk_TR069_Config_Activity.this;
                    int unused = fMS_Bulk_TR069_Config_Activity3.countt = fMS_Bulk_TR069_Config_Activity3.countt - 1;
                }
            });
            this.btns[i4].setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    FMS_Bulk_TR069_Config_Activity.this.loadList(i4);
                    FMS_Bulk_TR069_Config_Activity.this.CheckBtnBackGroud(i4);
                    int unused = FMS_Bulk_TR069_Config_Activity.this.countt = i4;
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
                this.btns[i].setBackgroundDrawable(getResources().getDrawable(R.drawable.button02));
                this.btns[i5].setTextColor(getResources().getColor(R.color.colorblack));
            } else {
                this.btns[i5].setBackgroundColor(getResources().getColor(17170445));
                this.btns[i5].setTextColor(getResources().getColor(R.color.colorwhite));
            }
        }
    }

    private void save_file() {
        this.progress_dialog.show();
        final String stringExtra = getIntent().getStringExtra("IP");
        final String stringExtra2 = getIntent().getStringExtra("IDENTIFIER");
        RequestQueue newRequestQueue = Volley.newRequestQueue(this);
        AnonymousClass6 r2 = new StringRequest(1, getString(R.string.serverip) + "bbnw/download.php", new Response.Listener<String>() {
            public void onResponse(String str) {
                FMS_Bulk_TR069_Config_Activity.this.progress_dialog.cancel();
                try {
                    final File file = new File(FMS_Bulk_TR069_Config_Activity.this.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS), "Bulk_TR069_Config(" + stringExtra + ").csv");
                    if (file.exists()) {
                        file.delete();
                    }
                    BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
                    bufferedOutputStream.write(str.getBytes());
                    bufferedOutputStream.flush();
                    bufferedOutputStream.close();
                    AlertDialog.Builder builder = new AlertDialog.Builder(FMS_Bulk_TR069_Config_Activity.this);
                    View inflate = FMS_Bulk_TR069_Config_Activity.this.getLayoutInflater().inflate(R.layout.custom_info, (ViewGroup) null);
                    TextView textView = (TextView) inflate.findViewById(R.id.txt_update);
                    textView.setText("OPEN");
                    builder.setCancelable(true);
                    builder.setView(inflate);
                    AlertDialog unused = FMS_Bulk_TR069_Config_Activity.this.info_dialog = builder.create();
                    FMS_Bulk_TR069_Config_Activity.this.info_dialog.show();
                    ((TextView) inflate.findViewById(R.id.txt_error)).setText(Html.fromHtml("The File Is Downloaded and Saved at <br><b>" + Environment.DIRECTORY_DOWNLOADS + "/BSNL-TEEVRA/Bulk_TR069_Config(" + stringExtra + ").csv</b><br><br>Do You Want To Open The File ", 63));
                    ((TextView) inflate.findViewById(R.id.txt_cancel)).setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                            FMS_Bulk_TR069_Config_Activity.this.info_dialog.cancel();
                        }
                    });
                    textView.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                            FMS_Bulk_TR069_Config_Activity.this.info_dialog.cancel();
                            Uri uriForFile = FileProvider.getUriForFile(FMS_Bulk_TR069_Config_Activity.this.getApplicationContext(), FMS_Bulk_TR069_Config_Activity.this.getApplicationContext().getPackageName() + ".provider", file);
                            Intent intent = new Intent("android.intent.action.VIEW");
                            intent.setFlags(67108864);
                            intent.setDataAndType(uriForFile, "application/vnd.ms-excel");
                            intent.setFlags(1);
                            FMS_Bulk_TR069_Config_Activity.this.startActivity(intent);
                        }
                    });
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError volleyError) {
                FMS_Bulk_TR069_Config_Activity.this.progress_dialog.cancel();
                FMS_Bulk_TR069_Config_Activity.this.txt_alert.setText(new VolleyErrorHelper().onErrorResponse(volleyError, FMS_Bulk_TR069_Config_Activity.this.getApplicationContext()));
                FMS_Bulk_TR069_Config_Activity.this.error_dialog.show();
            }
        }) {
            /* access modifiers changed from: protected */
            public Map<String, String> getParams() throws AuthFailureError {
                HashMap hashMap = new HashMap();
                hashMap.put("OLT_IP", stringExtra);
                hashMap.put("IDENTIFIER", stringExtra2);
                hashMap.put("username", FMS_Bulk_TR069_Config_Activity.this.Pref_Username);
                hashMap.put("random_key", FMS_Bulk_TR069_Config_Activity.this.Pref_Randkey);
                hashMap.put("device_id", FMS_Bulk_TR069_Config_Activity.this.androidId);
                Log.v("PARAMS", hashMap.toString());
                return hashMap;
            }
        };
        r2.setRetryPolicy(new DefaultRetryPolicy(600000, 1, 1.0f));
        newRequestQueue.add(r2);
    }

    public void onClick(View view) {
        save_file();
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        if (this.storagePermission.handlePermissionResult(i, iArr).booleanValue()) {
            save_file();
        } else {
            Toast.makeText(this, "Storage Permission Denied.", 1).show();
        }
    }
}
