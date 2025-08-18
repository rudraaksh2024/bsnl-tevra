package bsnl.bsnl_teevra.wizards;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.core.widgets.analyzer.BasicMeasure;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import bsnl.bsnl_teevra.AlertHelperclass;
import bsnl.bsnl_teevra.R;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.ByteOrder;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

public class Ont_Configure_Activity extends AppCompatActivity implements View.OnClickListener {
    public static final int LOCATION_PERMISSION_REQ = 1001;
    /* access modifiers changed from: private */
    public String IMPORT_CLASS;
    private String Ont_Password;
    private String Ont_Type;
    private String Ont_Username;
    private String Ont_model;
    /* access modifiers changed from: private */
    public String PACKAGE_NAME;
    private ArrayAdapter<String> adapter_make;
    private ArrayAdapter<String> adapter_type;
    private ArrayList<String> array_make;
    private ArrayList<String> array_type;
    private Button btn_submit;
    /* access modifiers changed from: private */
    public AlertDialog counter_dialog;
    /* access modifiers changed from: private */
    public AlertDialog error_dialog;
    private EditText et_ont_password;
    private EditText et_ont_username;
    /* access modifiers changed from: private */
    public AlertDialog info_dialog;
    private AlertDialog logout_dialog;
    /* access modifiers changed from: private */
    public AlertDialog progress_dialog;
    /* access modifiers changed from: private */
    public TextView txt_alert;
    private AutoCompleteTextView txt_model1;
    private AutoCompleteTextView txt_model2;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.ont_configure_activity);
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
        this.txt_model1 = (AutoCompleteTextView) findViewById(R.id.txt_model1);
        this.txt_model2 = (AutoCompleteTextView) findViewById(R.id.txt_model2);
        ArrayList<String> arrayList = new ArrayList<>();
        this.array_type = arrayList;
        arrayList.add("-- ONT TYPE --");
        this.array_type.add("SINGLE BAND");
        this.array_type.add("DUAL BAND");
        this.adapter_type = new ArrayAdapter<>(this, R.layout.spinner_basic, this.array_type);
        this.txt_model1.setText(this.array_type.get(0).toString());
        this.txt_model1.setAdapter(this.adapter_type);
        ArrayList<String> arrayList2 = new ArrayList<>();
        this.array_make = arrayList2;
        arrayList2.add("-- ONT MAKE --");
        this.array_make.add("SYROTECH");
        this.array_make.add("OPTILINK");
        this.array_make.add("SECUREYE");
        this.array_make.add("DIGISOL");
        this.array_make.add("TECHNXT");
        this.array_make.add("NETLINK");
        this.array_make.add("UNIWAY");
        this.array_make.add("SHARPVISION");
        this.array_make.add("DBC");
        this.adapter_make = new ArrayAdapter<>(this, R.layout.spinner_basic, this.array_make);
        this.txt_model2.setText(this.array_make.get(0).toString());
        this.txt_model2.setAdapter(this.adapter_make);
        this.et_ont_username = (EditText) findViewById(R.id.et_ont_username);
        this.et_ont_password = (EditText) findViewById(R.id.et_ont_password);
        Button button = (Button) findViewById(R.id.btn_submit);
        this.btn_submit = button;
        button.setOnClickListener(this);
    }

    public void onClick(View view) {
        if (view.getId() == R.id.btn_submit) {
            this.Ont_Type = this.txt_model1.getText().toString().trim();
            this.Ont_model = this.txt_model2.getText().toString().trim();
            this.Ont_Username = this.et_ont_username.getText().toString().trim();
            this.Ont_Password = this.et_ont_password.getText().toString().trim();
            if (this.Ont_Type.equals("-- ONT TYPE --")) {
                new AlertHelperclass().ntoast("PLease Select ONT Type From DropDown", this);
            } else if (this.Ont_model.equals("-- ONT MAKE --")) {
                new AlertHelperclass().ntoast("PLease Select ONT Make From DropDown", this);
            } else if (this.Ont_Username.isEmpty()) {
                new AlertHelperclass().ntoast("PLease Enter Correct ONT Username", this);
            } else if (this.Ont_Password.isEmpty()) {
                new AlertHelperclass().ntoast("PLease Enter Correct ONT Password", this);
            } else if (ContextCompat.checkSelfPermission(this, "android.permission.ACCESS_FINE_LOCATION") != 0) {
                if (shouldShowRequestPermissionRationale("android.permission.ACCESS_FINE_LOCATION")) {
                    ActivityCompat.requestPermissions(this, new String[]{"android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_NETWORK_STATE"}, 1001);
                    return;
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                View inflate = getLayoutInflater().inflate(R.layout.custom_info, (ViewGroup) null);
                TextView textView = (TextView) inflate.findViewById(R.id.txt_update);
                textView.setText("SETTINGS");
                builder.setCancelable(false);
                builder.setView(inflate);
                AlertDialog create = builder.create();
                this.info_dialog = create;
                create.show();
                ((TextView) inflate.findViewById(R.id.txt_error)).setText("Action Needs Access Of Device GPS");
                ((TextView) inflate.findViewById(R.id.txt_cancel)).setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        Ont_Configure_Activity.this.info_dialog.cancel();
                    }
                });
                textView.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        Intent intent = new Intent();
                        intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
                        intent.addCategory("android.intent.category.DEFAULT");
                        intent.setData(Uri.parse("package:" + Ont_Configure_Activity.this.getPackageName()));
                        intent.addFlags(268435456);
                        intent.addFlags(BasicMeasure.EXACTLY);
                        intent.addFlags(8388608);
                        Ont_Configure_Activity.this.startActivity(intent);
                        Ont_Configure_Activity.this.info_dialog.cancel();
                    }
                });
            } else if (((LocationManager) getSystemService("location")).isProviderEnabled("gps")) {
                Ont_Login();
            } else {
                this.txt_alert.setText("GPS is disabled\nPlease Turn On GPS to Proceed Further");
                this.error_dialog.show();
            }
        }
    }

    private void Ont_Login() {
        WifiManager wifiManager = (WifiManager) getSystemService("wifi");
        if (wifiManager.isWifiEnabled()) {
            char c = 65535;
            if (wifiManager.getConnectionInfo().getNetworkId() == -1) {
                this.txt_alert.setText("WIFI Adapter Is Not Connected To Any Access Point");
                this.error_dialog.show();
                return;
            }
            try {
                int ipAddress = wifiManager.getConnectionInfo().getIpAddress();
                if (ByteOrder.nativeOrder().equals(ByteOrder.LITTLE_ENDIAN)) {
                    ipAddress = Integer.reverseBytes(ipAddress);
                }
                String[] split = InetAddress.getByAddress(BigInteger.valueOf((long) ipAddress).toByteArray()).getHostAddress().split("\\.");
                wifiManager.getConnectionInfo().getSSID();
                String str = "http://" + (split[0] + "." + split[1] + "." + split[2] + ".1");
                this.IMPORT_CLASS = new String();
                String str2 = this.Ont_Type;
                int hashCode = str2.hashCode();
                if (hashCode != -772232359) {
                    if (hashCode == 392061709) {
                        if (str2.equals("SINGLE BAND")) {
                            c = 0;
                        }
                    }
                } else if (str2.equals("DUAL BAND")) {
                    c = 1;
                }
                if (c == 0) {
                    this.IMPORT_CLASS = "vsol1band";
                    this.PACKAGE_NAME = get_package_name("vsol1band");
                } else if (c == 1) {
                    this.IMPORT_CLASS = "vsol2band";
                    this.PACKAGE_NAME = get_package_name("vsol2band");
                }
                Ont_Login_meathod(str);
            } catch (UnknownHostException e) {
                Log.v("ERROR", e.toString());
                Toast.makeText(getApplicationContext(), "Cannot Resolve The IP", 1).show();
            }
        } else {
            this.txt_alert.setText("WIFI Adapter Is Off\nPlease Turn On WIFI to Proceed Further");
            this.error_dialog.show();
        }
    }

    private String get_package_name(String str) {
        if (str.equals("vsol1band")) {
            return getString(R.string.wizard_vsol1);
        }
        return str.equals("vsol2band") ? getString(R.string.wizard_vsol2) : "";
    }

    private void Ont_Login_meathod(String str) {
        this.progress_dialog.show();
        try {
            final Class<?> cls = Class.forName(this.PACKAGE_NAME + "Ont_Configure_Class");
            final Object newInstance = cls.getDeclaredConstructor(new Class[]{String.class}).newInstance(new Object[]{str});
            final Class[] clsArr = {String.class, String.class};
            final Object[] objArr = {this.Ont_Username, this.Ont_Password};
            final String str2 = str;
            new Thread(new Runnable() {
                public void run() {
                    try {
                        final JSONObject jSONObject = (JSONObject) cls.getMethod("login", clsArr).invoke(newInstance, objArr);
                        Ont_Configure_Activity.this.runOnUiThread(new Runnable() {
                            public void run() {
                                Ont_Configure_Activity.this.progress_dialog.cancel();
                                Log.v("LOGIN", jSONObject.toString());
                                try {
                                    if (Boolean.valueOf(jSONObject.getBoolean(NotificationCompat.CATEGORY_STATUS)).booleanValue()) {
                                        Intent intent = new Intent(Ont_Configure_Activity.this, Ont_Configure_Status_Activity.class);
                                        intent.putExtra("MYOBJECT", (Serializable) newInstance);
                                        intent.putExtra("IMPORT_CLASS", Ont_Configure_Activity.this.IMPORT_CLASS);
                                        intent.putExtra("PACKAGE_NAME", Ont_Configure_Activity.this.PACKAGE_NAME);
                                        Ont_Configure_Activity.this.startActivity(intent);
                                        return;
                                    }
                                    String trim = jSONObject.getString("message").toString().trim();
                                    if (trim.contains("Please wait for 1 min")) {
                                        AlertDialog.Builder builder = new AlertDialog.Builder(Ont_Configure_Activity.this);
                                        View inflate = Ont_Configure_Activity.this.getLayoutInflater().inflate(R.layout.custom_counter, (ViewGroup) null);
                                        final TextView textView = (TextView) inflate.findViewById(R.id.txt_counter);
                                        builder.setCancelable(false);
                                        builder.setView(inflate);
                                        AlertDialog unused = Ont_Configure_Activity.this.counter_dialog = builder.create();
                                        Ont_Configure_Activity.this.counter_dialog.show();
                                        new CountDownTimer(60000, 1000) {
                                            public void onTick(long j) {
                                                textView.setText(((j / 1000) % 60) + "");
                                            }

                                            public void onFinish() {
                                                Ont_Configure_Activity.this.counter_dialog.cancel();
                                            }
                                        }.start();
                                    } else if (trim.toLowerCase().contains("password")) {
                                        Ont_Configure_Activity.this.txt_alert.setText(trim);
                                        Ont_Configure_Activity.this.error_dialog.show();
                                    } else if (trim.toLowerCase().contains("another user have logined")) {
                                        Ont_Configure_Activity.this.txt_alert.setText(trim);
                                        Ont_Configure_Activity.this.error_dialog.show();
                                    } else {
                                        Intent intent2 = new Intent(Ont_Configure_Activity.this, Ont_Configure_web_Activity.class);
                                        intent2.putExtra("URL", str2);
                                        Ont_Configure_Activity.this.startActivity(intent2);
                                    }
                                } catch (JSONException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
            Toast.makeText(getApplicationContext(), str, 1).show();
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e2) {
            throw new RuntimeException(e2);
        } catch (NoSuchMethodException e3) {
            throw new RuntimeException(e3);
        } catch (ClassNotFoundException e4) {
            throw new RuntimeException(e4);
        } catch (InstantiationException e5) {
            throw new RuntimeException(e5);
        }
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        if (i != 1001) {
            return;
        }
        if (iArr.length <= 0 || iArr[0] != 0) {
            Toast.makeText(this, "Location Permission Denied", 0).show();
        } else {
            Ont_Login();
        }
    }
}
