package bsnl.bsnl_teevra.wizards.fragments;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import bsnl.bsnl_teevra.AlertHelperclass;
import bsnl.bsnl_teevra.R;
import bsnl.bsnl_teevra.wizards.Ont_Configure_Activity;
import com.google.android.material.textfield.TextInputLayout;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Ont_Configure_Wlan_Fragment extends Fragment implements View.OnClickListener {
    private static final int WIFI_PICKER_REQUEST_CODE = 1;
    /* access modifiers changed from: private */
    public Class<?> OntSettingClass;
    private String Ont_Url;
    private String PACKAGE_NAME;
    private String Token;
    /* access modifiers changed from: private */
    public ArrayList<String> action;
    /* access modifiers changed from: private */
    public ArrayAdapter<String> adp_action;
    /* access modifiers changed from: private */
    public ArrayAdapter<String> adp_ssid;
    /* access modifiers changed from: private */
    public ArrayList<String> band;
    private TextView btn_submit;
    /* access modifiers changed from: private */
    public AutoCompleteTextView et_action;
    /* access modifiers changed from: private */
    public EditText et_wifiband;
    /* access modifiers changed from: private */
    public EditText et_wifipwd;
    /* access modifiers changed from: private */
    public AutoCompleteTextView et_wifissid;
    /* access modifiers changed from: private */
    public EditText et_wifissid_new;
    /* access modifiers changed from: private */
    public AlertDialog info_dialog;
    private TextInputLayout lay_action;
    private TextInputLayout lay_band;
    /* access modifiers changed from: private */
    public TextInputLayout lay_pwd;
    /* access modifiers changed from: private */
    public SwipeRefreshLayout lay_refresh;
    private TextInputLayout lay_ssid;
    /* access modifiers changed from: private */
    public TextInputLayout lay_ssid_new;
    /* access modifiers changed from: private */
    public AlertDialog progress_dialog;
    /* access modifiers changed from: private */
    public ArrayList<String> pwd;
    /* access modifiers changed from: private */
    public ArrayList<String> ssid;
    private JSONObject wan_response;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.ont_configure_wlan_fragment, viewGroup, false);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View inflate2 = getLayoutInflater().inflate(R.layout.custom_progress, (ViewGroup) null);
        builder.setCancelable(false);
        builder.setView(inflate2);
        this.progress_dialog = builder.create();
        this.lay_refresh = (SwipeRefreshLayout) inflate.findViewById(R.id.lay_refresh);
        this.lay_action = (TextInputLayout) inflate.findViewById(R.id.lay_action);
        this.lay_band = (TextInputLayout) inflate.findViewById(R.id.lay_band);
        this.lay_ssid = (TextInputLayout) inflate.findViewById(R.id.lay_ssid);
        this.lay_ssid_new = (TextInputLayout) inflate.findViewById(R.id.lay_ssid_new);
        this.lay_pwd = (TextInputLayout) inflate.findViewById(R.id.lay_pwd);
        this.et_action = (AutoCompleteTextView) inflate.findViewById(R.id.et_action);
        this.et_wifiband = (EditText) inflate.findViewById(R.id.et_wifiband);
        this.et_wifissid = (AutoCompleteTextView) inflate.findViewById(R.id.et_wifissid);
        this.et_wifissid_new = (EditText) inflate.findViewById(R.id.et_wifissid_new);
        this.et_wifipwd = (EditText) inflate.findViewById(R.id.et_wifipwd);
        this.btn_submit = (TextView) inflate.findViewById(R.id.btn_submit);
        get_wlan_info();
        this.btn_submit.setOnClickListener(this);
        this.lay_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            public void onRefresh() {
                Ont_Configure_Wlan_Fragment.this.get_wlan_info();
                Ont_Configure_Wlan_Fragment.this.lay_refresh.setRefreshing(false);
            }
        });
        return inflate;
    }

    /* access modifiers changed from: private */
    public void get_wlan_info() {
        this.progress_dialog.show();
        Serializable serializable = getArguments().getSerializable("LOGIN_OBJECT");
        getArguments().getString("IMPORT_CLASS");
        this.PACKAGE_NAME = getArguments().getString("PACKAGE_NAME");
        try {
            Class<?> cls = serializable.getClass();
            this.Token = (String) cls.getMethod("getToken", new Class[0]).invoke(serializable, new Object[0]);
            this.Ont_Url = (String) cls.getMethod("fetchUrl", new Class[0]).invoke(serializable, new Object[0]);
            final Class<?> cls2 = Class.forName(this.PACKAGE_NAME + "Ont_Configure_Wlan_Class");
            final Object newInstance = cls2.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
            final Class[] clsArr = {String.class};
            final Object[] objArr = {this.Ont_Url};
            this.OntSettingClass = Class.forName(this.PACKAGE_NAME + "Ont_Configure_Setting_Class");
            new Thread(new Runnable() {
                public void run() {
                    try {
                        final JSONObject jSONObject = (JSONObject) cls2.getMethod("extractWlanParameter", clsArr).invoke(newInstance, objArr);
                        Ont_Configure_Wlan_Fragment.this.requireActivity().runOnUiThread(new Runnable() {
                            public void run() {
                                Ont_Configure_Wlan_Fragment.this.progress_dialog.cancel();
                                try {
                                    if (Boolean.valueOf(jSONObject.getBoolean(NotificationCompat.CATEGORY_STATUS)).booleanValue()) {
                                        JSONArray jSONArray = jSONObject.getJSONArray("message");
                                        ArrayList unused = Ont_Configure_Wlan_Fragment.this.action = new ArrayList();
                                        ArrayList unused2 = Ont_Configure_Wlan_Fragment.this.band = new ArrayList();
                                        ArrayList unused3 = Ont_Configure_Wlan_Fragment.this.ssid = new ArrayList();
                                        ArrayList unused4 = Ont_Configure_Wlan_Fragment.this.pwd = new ArrayList();
                                        Ont_Configure_Wlan_Fragment.this.action.add("--  SELECT ACTION  --");
                                        Ont_Configure_Wlan_Fragment.this.action.add("SSID CHANGE");
                                        Ont_Configure_Wlan_Fragment.this.action.add("PASSWORD CHANGE");
                                        for (int i = 0; i < jSONArray.length(); i++) {
                                            JSONObject jSONObject = jSONArray.getJSONObject(i);
                                            Ont_Configure_Wlan_Fragment.this.band.add(jSONObject.getString("band"));
                                            Ont_Configure_Wlan_Fragment.this.ssid.add(jSONObject.getString("ssid"));
                                            Ont_Configure_Wlan_Fragment.this.pwd.add(jSONObject.getString("password"));
                                        }
                                        ArrayAdapter unused5 = Ont_Configure_Wlan_Fragment.this.adp_action = new ArrayAdapter(Ont_Configure_Wlan_Fragment.this.getActivity(), R.layout.spinner_basic, Ont_Configure_Wlan_Fragment.this.action);
                                        ArrayAdapter unused6 = Ont_Configure_Wlan_Fragment.this.adp_ssid = new ArrayAdapter(Ont_Configure_Wlan_Fragment.this.getActivity(), R.layout.spinner_basic, Ont_Configure_Wlan_Fragment.this.ssid);
                                        Ont_Configure_Wlan_Fragment.this.et_action.setText(((String) Ont_Configure_Wlan_Fragment.this.action.get(0)).toString());
                                        Ont_Configure_Wlan_Fragment.this.et_action.setAdapter(Ont_Configure_Wlan_Fragment.this.adp_action);
                                        Ont_Configure_Wlan_Fragment.this.et_wifissid.addTextChangedListener(new TextWatcher() {
                                            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                                            }

                                            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                                            }

                                            public void afterTextChanged(Editable editable) {
                                                int indexOf = Ont_Configure_Wlan_Fragment.this.ssid.indexOf(Ont_Configure_Wlan_Fragment.this.et_wifissid.getText().toString());
                                                Ont_Configure_Wlan_Fragment.this.et_wifiband.setText(((String) Ont_Configure_Wlan_Fragment.this.band.get(indexOf)).toString());
                                                Ont_Configure_Wlan_Fragment.this.et_wifipwd.setText(((String) Ont_Configure_Wlan_Fragment.this.pwd.get(indexOf)).toString());
                                            }
                                        });
                                        Ont_Configure_Wlan_Fragment.this.et_wifissid.setText(((String) Ont_Configure_Wlan_Fragment.this.ssid.get(0)).toString());
                                        Ont_Configure_Wlan_Fragment.this.et_wifissid.setAdapter(Ont_Configure_Wlan_Fragment.this.adp_ssid);
                                        Ont_Configure_Wlan_Fragment.this.et_action.addTextChangedListener(new TextWatcher() {
                                            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                                            }

                                            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                                            }

                                            public void afterTextChanged(Editable editable) {
                                                if (Ont_Configure_Wlan_Fragment.this.et_action.getText().toString().equals("SSID CHANGE")) {
                                                    Ont_Configure_Wlan_Fragment.this.lay_pwd.setVisibility(8);
                                                    Ont_Configure_Wlan_Fragment.this.lay_ssid_new.setVisibility(0);
                                                    Ont_Configure_Wlan_Fragment.this.et_wifissid_new.requestFocus();
                                                    return;
                                                }
                                                Ont_Configure_Wlan_Fragment.this.lay_pwd.setVisibility(0);
                                                Ont_Configure_Wlan_Fragment.this.lay_ssid_new.setVisibility(8);
                                            }
                                        });
                                        return;
                                    }
                                    new AlertHelperclass().ntoast("Sorry!! Cant Fetch WLAN Information", Ont_Configure_Wlan_Fragment.this.getActivity());
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

    public void onClick(View view) {
        ((InputMethodManager) getContext().getSystemService("input_method")).hideSoftInputFromWindow(view.getWindowToken(), 0);
        if (view.getId() == R.id.btn_submit) {
            String ssid2 = ((WifiManager) getActivity().getSystemService("wifi")).getConnectionInfo().getSSID();
            String trim = this.et_action.getText().toString().trim();
            if (trim.equals("--  SELECT ACTION  --")) {
                Toast.makeText(getActivity(), "Please Select Action Type", 1).show();
            } else if (trim.equals("SSID CHANGE")) {
                String trim2 = this.et_wifissid.getText().toString().trim();
                final String trim3 = this.et_wifissid_new.getText().toString().trim();
                final String trim4 = this.et_wifiband.getText().toString().trim();
                if (trim3.equals("")) {
                    new AlertHelperclass().ntoast("Please Enter A Valid SSID Name", getActivity());
                } else if (trim2.equals(trim3)) {
                    new AlertHelperclass().ntoast("Old SSID Name And New SSID Name Cant Be Identical", getActivity());
                } else if (trim2.equals(ssid2.replaceAll("\"", ""))) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    View inflate = getLayoutInflater().inflate(R.layout.custom_info, (ViewGroup) null);
                    TextView textView = (TextView) inflate.findViewById(R.id.txt_alert);
                    TextView textView2 = (TextView) inflate.findViewById(R.id.txt_update);
                    textView.setText("ALERT");
                    textView.setTypeface(Typeface.defaultFromStyle(1));
                    textView.setTextColor(getResources().getColor(R.color.colorRed));
                    ((TextView) inflate.findViewById(R.id.txt_error)).setText(Html.fromHtml("You Are Connected To The SSID <b>(" + trim2 + ").</b><br>An Attempt To Change The <b>SSID NAME</b> Will Disconnect You From The ONT.<br><br><b>Do You Really Want To Proceed ?</b>", 63));
                    textView2.setText("PROCEED");
                    builder.setCancelable(false);
                    builder.setView(inflate);
                    AlertDialog create = builder.create();
                    this.info_dialog = create;
                    create.show();
                    ((TextView) inflate.findViewById(R.id.txt_cancel)).setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                            Ont_Configure_Wlan_Fragment.this.info_dialog.cancel();
                        }
                    });
                    textView2.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                            Ont_Configure_Wlan_Fragment.this.ssid_change(trim3, trim4, "same");
                            Ont_Configure_Wlan_Fragment.this.info_dialog.cancel();
                        }
                    });
                } else {
                    ssid_change(trim3, trim4, "different");
                }
            } else if (trim.equals("PASSWORD CHANGE")) {
                final String trim5 = this.et_wifissid.getText().toString().trim();
                final String trim6 = this.et_wifiband.getText().toString().trim();
                final String trim7 = this.et_wifipwd.getText().toString().trim();
                if (trim5.equals(ssid2.replaceAll("\"", ""))) {
                    AlertDialog.Builder builder2 = new AlertDialog.Builder(getActivity());
                    View inflate2 = getLayoutInflater().inflate(R.layout.custom_info, (ViewGroup) null);
                    TextView textView3 = (TextView) inflate2.findViewById(R.id.txt_alert);
                    TextView textView4 = (TextView) inflate2.findViewById(R.id.txt_update);
                    textView3.setText("ALERT");
                    textView3.setTypeface(Typeface.defaultFromStyle(1));
                    textView3.setTextColor(getResources().getColor(R.color.colorRed));
                    ((TextView) inflate2.findViewById(R.id.txt_error)).setText(Html.fromHtml("You Are Connected To The SSID <b>(" + trim5 + ").</b><br>An Attempt To Change The <b>SSID PASSWORD</b> Will Disconnect You From The ONT.<br><br><b>Do You Really Want To Proceed ?</b>", 63));
                    textView4.setText("PROCEED");
                    builder2.setCancelable(false);
                    builder2.setView(inflate2);
                    AlertDialog create2 = builder2.create();
                    this.info_dialog = create2;
                    create2.show();
                    ((TextView) inflate2.findViewById(R.id.txt_cancel)).setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                            Ont_Configure_Wlan_Fragment.this.info_dialog.cancel();
                        }
                    });
                    textView4.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                            Ont_Configure_Wlan_Fragment.this.pwd_change(trim5, trim6, trim7, "same");
                            Ont_Configure_Wlan_Fragment.this.info_dialog.cancel();
                        }
                    });
                    return;
                }
                pwd_change(trim5, trim6, trim7, "different");
            }
        }
    }

    /* access modifiers changed from: private */
    public void pwd_change(String str, String str2, String str3, String str4) {
        this.progress_dialog.show();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("ssid", str);
            jSONObject.put("band", str2);
            jSONObject.put("password", str3);
            jSONObject.put("token", this.Token);
            final Object newInstance = this.OntSettingClass.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
            final Class[] clsArr = {String.class, JSONObject.class};
            final Object[] objArr = {this.Ont_Url, jSONObject};
            final String str5 = str4;
            new Thread(new Runnable() {
                public void run() {
                    try {
                        final JSONObject jSONObject = (JSONObject) Ont_Configure_Wlan_Fragment.this.OntSettingClass.getMethod("configureWifiPassword", clsArr).invoke(newInstance, objArr);
                        Ont_Configure_Wlan_Fragment.this.requireActivity().runOnUiThread(new Runnable() {
                            public void run() {
                                Ont_Configure_Wlan_Fragment.this.progress_dialog.cancel();
                                Log.v("SSID-PWD-CHANGE", jSONObject.toString());
                                if (str5.equals("same")) {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(Ont_Configure_Wlan_Fragment.this.getActivity());
                                    View inflate = Ont_Configure_Wlan_Fragment.this.getLayoutInflater().inflate(R.layout.custom_info, (ViewGroup) null);
                                    TextView textView = (TextView) inflate.findViewById(R.id.txt_alert);
                                    TextView textView2 = (TextView) inflate.findViewById(R.id.txt_update);
                                    textView.setText("WIFI PASSWORD CHANGED");
                                    textView.setTypeface(Typeface.defaultFromStyle(1));
                                    textView.setTextColor(Ont_Configure_Wlan_Fragment.this.getResources().getColor(R.color.colorRed));
                                    ((TextView) inflate.findViewById(R.id.txt_error)).setText("\nYou Are Not Connected To ONT Anymore\n\nBefore Proceeding Further,\nPlease Connect The WIFI With New Password");
                                    textView2.setText("CHANGE PASSWORD");
                                    builder.setCancelable(false);
                                    builder.setView(inflate);
                                    AlertDialog unused = Ont_Configure_Wlan_Fragment.this.info_dialog = builder.create();
                                    Ont_Configure_Wlan_Fragment.this.info_dialog.show();
                                    ((TextView) inflate.findViewById(R.id.txt_cancel)).setOnClickListener(new View.OnClickListener() {
                                        public void onClick(View view) {
                                            Ont_Configure_Wlan_Fragment.this.info_dialog.cancel();
                                            Intent intent = new Intent(Ont_Configure_Wlan_Fragment.this.getContext(), Ont_Configure_Activity.class);
                                            intent.setFlags(67108864);
                                            Ont_Configure_Wlan_Fragment.this.startActivity(intent);
                                            Ont_Configure_Wlan_Fragment.this.getActivity().finish();
                                        }
                                    });
                                    textView2.setOnClickListener(new View.OnClickListener() {
                                        public void onClick(View view) {
                                            Ont_Configure_Wlan_Fragment.this.info_dialog.cancel();
                                            Ont_Configure_Wlan_Fragment.this.startActivity(new Intent("android.net.wifi.PICK_WIFI_NETWORK"));
                                        }
                                    });
                                    return;
                                }
                                Ont_Configure_Wlan_Fragment.this.get_wlan_info();
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e2) {
            throw new RuntimeException(e2);
        } catch (IllegalAccessException e3) {
            throw new RuntimeException(e3);
        } catch (InstantiationException e4) {
            throw new RuntimeException(e4);
        } catch (NoSuchMethodException e5) {
            throw new RuntimeException(e5);
        }
    }

    /* access modifiers changed from: private */
    public void ssid_change(String str, String str2, String str3) {
        this.progress_dialog.show();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("ssid", str);
            jSONObject.put("band", str2);
            jSONObject.put("token", this.Token);
            final Object newInstance = this.OntSettingClass.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
            final Class[] clsArr = {String.class, JSONObject.class};
            final Object[] objArr = {this.Ont_Url, jSONObject};
            final String str4 = str3;
            new Thread(new Runnable() {
                public void run() {
                    try {
                        Ont_Configure_Wlan_Fragment.this.OntSettingClass.getMethod("configureWifiSsid", clsArr).invoke(newInstance, objArr);
                        Ont_Configure_Wlan_Fragment.this.requireActivity().runOnUiThread(new Runnable() {
                            public void run() {
                                Ont_Configure_Wlan_Fragment.this.progress_dialog.cancel();
                                if (str4.equals("same")) {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(Ont_Configure_Wlan_Fragment.this.getActivity());
                                    View inflate = Ont_Configure_Wlan_Fragment.this.getLayoutInflater().inflate(R.layout.custom_info, (ViewGroup) null);
                                    TextView textView = (TextView) inflate.findViewById(R.id.txt_alert);
                                    TextView textView2 = (TextView) inflate.findViewById(R.id.txt_update);
                                    textView.setText("WIFI SSID CHANGED");
                                    textView.setTypeface(Typeface.defaultFromStyle(1));
                                    textView.setTextColor(Ont_Configure_Wlan_Fragment.this.getResources().getColor(R.color.colorRed));
                                    ((TextView) inflate.findViewById(R.id.txt_error)).setText("You Are Not Connected To ONT Anymore\n\nBefore Proceeding,\nPlease Connect The New SSID ");
                                    textView2.setText("CHANGE SSID");
                                    builder.setCancelable(false);
                                    builder.setView(inflate);
                                    AlertDialog unused = Ont_Configure_Wlan_Fragment.this.info_dialog = builder.create();
                                    Ont_Configure_Wlan_Fragment.this.info_dialog.show();
                                    ((TextView) inflate.findViewById(R.id.txt_cancel)).setOnClickListener(new View.OnClickListener() {
                                        public void onClick(View view) {
                                            Ont_Configure_Wlan_Fragment.this.info_dialog.cancel();
                                            Intent intent = new Intent(Ont_Configure_Wlan_Fragment.this.getContext(), Ont_Configure_Activity.class);
                                            intent.setFlags(67108864);
                                            Ont_Configure_Wlan_Fragment.this.startActivity(intent);
                                            Ont_Configure_Wlan_Fragment.this.getActivity().finish();
                                        }
                                    });
                                    textView2.setOnClickListener(new View.OnClickListener() {
                                        public void onClick(View view) {
                                            Ont_Configure_Wlan_Fragment.this.info_dialog.cancel();
                                            Ont_Configure_Wlan_Fragment.this.startActivityForResult(new Intent("android.net.wifi.PICK_WIFI_NETWORK"), 1);
                                        }
                                    });
                                    return;
                                }
                                Ont_Configure_Wlan_Fragment.this.get_wlan_info();
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e2) {
            throw new RuntimeException(e2);
        } catch (IllegalAccessException e3) {
            throw new RuntimeException(e3);
        } catch (InstantiationException e4) {
            throw new RuntimeException(e4);
        } catch (NoSuchMethodException e5) {
            throw new RuntimeException(e5);
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 1) {
            Intent intent2 = new Intent(getContext(), Ont_Configure_Activity.class);
            intent2.setFlags(67108864);
            startActivity(intent2);
            getActivity().finish();
        }
    }
}
