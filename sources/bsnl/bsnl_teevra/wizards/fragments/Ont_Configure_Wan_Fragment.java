package bsnl.bsnl_teevra.wizards.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import bsnl.bsnl_teevra.AlertHelperclass;
import bsnl.bsnl_teevra.R;
import bsnl.bsnl_teevra.wizards.vsol2_classes.Ont_Configure_Class;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Ont_Configure_Wan_Fragment extends Fragment implements View.OnClickListener {
    /* access modifiers changed from: private */
    public Class<?> OntSettingClass;
    private String Ont_Url;
    private String PACKAGE_NAME;
    private String Token;
    /* access modifiers changed from: private */
    public ArrayAdapter<String> adapter_interface;
    /* access modifiers changed from: private */
    public ArrayAdapter<String> adapter_service;
    /* access modifiers changed from: private */
    public ArrayList<String> array_domain;
    /* access modifiers changed from: private */
    public ArrayList<String> array_interface;
    /* access modifiers changed from: private */
    public ArrayList<String> array_password;
    /* access modifiers changed from: private */
    public ArrayList<String> array_protocol;
    /* access modifiers changed from: private */
    public ArrayList<String> array_service;
    /* access modifiers changed from: private */
    public ArrayList<String> array_username;
    /* access modifiers changed from: private */
    public ArrayList<String> array_vlan;
    private AlertDialog error_dialog;
    /* access modifiers changed from: private */
    public EditText et_bridge_vlan;
    /* access modifiers changed from: private */
    public EditText et_pppoe_pwd;
    /* access modifiers changed from: private */
    public EditText et_pppoe_userid;
    /* access modifiers changed from: private */
    public EditText et_pppoe_vlan;
    /* access modifiers changed from: private */
    public EditText et_voip_domain;
    /* access modifiers changed from: private */
    public EditText et_voip_pwd;
    /* access modifiers changed from: private */
    public EditText et_voip_tele;
    /* access modifiers changed from: private */
    public EditText et_voip_vlan;
    /* access modifiers changed from: private */
    public LinearLayout lay_bridge;
    /* access modifiers changed from: private */
    public LinearLayout lay_pppoe;
    /* access modifiers changed from: private */
    public SwipeRefreshLayout lay_refresh;
    /* access modifiers changed from: private */
    public LinearLayout lay_voip;
    private Ont_Configure_Class loginOnt;
    /* access modifiers changed from: private */
    public AlertDialog progress_dialog;
    /* access modifiers changed from: private */
    public TextView txt_add;
    private TextView txt_alert;
    /* access modifiers changed from: private */
    public TextView txt_delete;
    /* access modifiers changed from: private */
    public AutoCompleteTextView txt_interface;
    /* access modifiers changed from: private */
    public AutoCompleteTextView txt_service;
    /* access modifiers changed from: private */
    public JSONObject wan_response;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.ont_configure_wan_fragment, viewGroup, false);
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
        this.lay_refresh = (SwipeRefreshLayout) inflate.findViewById(R.id.lay_refresh);
        this.txt_interface = (AutoCompleteTextView) inflate.findViewById(R.id.txt_connection);
        this.txt_service = (AutoCompleteTextView) inflate.findViewById(R.id.txt_protocol);
        this.lay_pppoe = (LinearLayout) inflate.findViewById(R.id.lay_pppoe);
        this.et_pppoe_vlan = (EditText) inflate.findViewById(R.id.et_pppoe_vlan);
        this.et_pppoe_userid = (EditText) inflate.findViewById(R.id.et_pppoe_userid);
        this.et_pppoe_pwd = (EditText) inflate.findViewById(R.id.et_pppoe_pwd);
        this.lay_voip = (LinearLayout) inflate.findViewById(R.id.lay_voip);
        this.et_voip_vlan = (EditText) inflate.findViewById(R.id.et_voip_vlan);
        this.et_voip_tele = (EditText) inflate.findViewById(R.id.et_voip_tele);
        this.et_voip_pwd = (EditText) inflate.findViewById(R.id.et_voip_pwd);
        this.et_voip_domain = (EditText) inflate.findViewById(R.id.et_voip_domain);
        this.lay_bridge = (LinearLayout) inflate.findViewById(R.id.lay_bridge);
        this.et_bridge_vlan = (EditText) inflate.findViewById(R.id.et_bridge_vlan);
        this.txt_add = (TextView) inflate.findViewById(R.id.txt_add);
        this.txt_delete = (TextView) inflate.findViewById(R.id.txt_delete);
        this.txt_add.setEnabled(false);
        this.txt_delete.setEnabled(false);
        this.txt_add.setOnClickListener(this);
        this.txt_delete.setOnClickListener(this);
        get_wan_info();
        this.lay_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            public void onRefresh() {
                Ont_Configure_Wan_Fragment.this.get_wan_info();
                Ont_Configure_Wan_Fragment.this.lay_refresh.setRefreshing(false);
            }
        });
        return inflate;
    }

    /* access modifiers changed from: private */
    public void get_wan_info() {
        this.progress_dialog.show();
        Serializable serializable = getArguments().getSerializable("LOGIN_OBJECT");
        getArguments().getString("IMPORT_CLASS");
        this.PACKAGE_NAME = getArguments().getString("PACKAGE_NAME");
        try {
            Class<?> cls = serializable.getClass();
            this.Token = (String) cls.getMethod("getToken", new Class[0]).invoke(serializable, new Object[0]);
            this.Ont_Url = (String) cls.getMethod("fetchUrl", new Class[0]).invoke(serializable, new Object[0]);
            this.OntSettingClass = Class.forName(this.PACKAGE_NAME + "Ont_Configure_Setting_Class");
            final Class<?> cls2 = Class.forName(this.PACKAGE_NAME + "Ont_Configure_Wan_Class");
            final Object newInstance = cls2.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
            final Class[] clsArr = {String.class};
            final Object[] objArr = {this.Ont_Url};
            this.wan_response = new JSONObject();
            new Thread(new Runnable() {
                public void run() {
                    try {
                        JSONObject unused = Ont_Configure_Wan_Fragment.this.wan_response = (JSONObject) cls2.getMethod("extractWanParameter", clsArr).invoke(newInstance, objArr);
                        Ont_Configure_Wan_Fragment.this.getActivity().runOnUiThread(new Runnable() {
                            public void run() {
                                Ont_Configure_Wan_Fragment.this.progress_dialog.cancel();
                                Ont_Configure_Wan_Fragment.this.txt_add.setEnabled(true);
                                Ont_Configure_Wan_Fragment.this.txt_delete.setEnabled(true);
                                try {
                                    Boolean.valueOf(Ont_Configure_Wan_Fragment.this.wan_response.getBoolean(NotificationCompat.CATEGORY_STATUS));
                                    JSONArray jSONArray = Ont_Configure_Wan_Fragment.this.wan_response.getJSONArray("message");
                                    ArrayList unused = Ont_Configure_Wan_Fragment.this.array_interface = new ArrayList();
                                    ArrayList unused2 = Ont_Configure_Wan_Fragment.this.array_service = new ArrayList();
                                    ArrayList unused3 = Ont_Configure_Wan_Fragment.this.array_vlan = new ArrayList();
                                    ArrayList unused4 = Ont_Configure_Wan_Fragment.this.array_username = new ArrayList();
                                    ArrayList unused5 = Ont_Configure_Wan_Fragment.this.array_password = new ArrayList();
                                    ArrayList unused6 = Ont_Configure_Wan_Fragment.this.array_domain = new ArrayList();
                                    ArrayList unused7 = Ont_Configure_Wan_Fragment.this.array_protocol = new ArrayList();
                                    Ont_Configure_Wan_Fragment.this.array_protocol.add("-- SELECT PROTOCOL --");
                                    Ont_Configure_Wan_Fragment.this.array_protocol.add("PPPOE");
                                    Ont_Configure_Wan_Fragment.this.array_protocol.add("IPOE");
                                    Ont_Configure_Wan_Fragment.this.array_protocol.add("BRIDGE");
                                    for (int i = 0; i < jSONArray.length(); i++) {
                                        JSONObject jSONObject = jSONArray.getJSONObject(i);
                                        Ont_Configure_Wan_Fragment.this.array_interface.add(jSONObject.getString("interface"));
                                        Ont_Configure_Wan_Fragment.this.array_service.add(jSONObject.getString("protocol"));
                                        Ont_Configure_Wan_Fragment.this.array_vlan.add(jSONObject.getString("vlan"));
                                        Ont_Configure_Wan_Fragment.this.array_username.add(jSONObject.getString("username"));
                                        Ont_Configure_Wan_Fragment.this.array_password.add(jSONObject.getString("password"));
                                        Ont_Configure_Wan_Fragment.this.array_domain.add(jSONObject.getString("domain"));
                                    }
                                    Ont_Configure_Wan_Fragment.this.array_interface.add("-- ADD NEW --");
                                    ArrayAdapter unused8 = Ont_Configure_Wan_Fragment.this.adapter_interface = new ArrayAdapter(Ont_Configure_Wan_Fragment.this.getActivity(), R.layout.spinner_basic, Ont_Configure_Wan_Fragment.this.array_interface);
                                    Ont_Configure_Wan_Fragment.this.txt_service.addTextChangedListener(new TextWatcher() {
                                        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                                        }

                                        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                                        }

                                        public void afterTextChanged(Editable editable) {
                                            String trim = Ont_Configure_Wan_Fragment.this.txt_service.getText().toString().trim();
                                            if (trim.equals("PPPOE")) {
                                                Ont_Configure_Wan_Fragment.this.lay_pppoe.setVisibility(0);
                                                Ont_Configure_Wan_Fragment.this.lay_bridge.setVisibility(8);
                                                Ont_Configure_Wan_Fragment.this.lay_voip.setVisibility(8);
                                            } else if (trim.equals("IPOE")) {
                                                Ont_Configure_Wan_Fragment.this.lay_pppoe.setVisibility(8);
                                                Ont_Configure_Wan_Fragment.this.lay_bridge.setVisibility(8);
                                                Ont_Configure_Wan_Fragment.this.lay_voip.setVisibility(0);
                                            } else if (trim.equals("BRIDGE")) {
                                                Ont_Configure_Wan_Fragment.this.lay_pppoe.setVisibility(8);
                                                Ont_Configure_Wan_Fragment.this.lay_bridge.setVisibility(0);
                                                Ont_Configure_Wan_Fragment.this.lay_voip.setVisibility(8);
                                            } else {
                                                Ont_Configure_Wan_Fragment.this.lay_pppoe.setVisibility(8);
                                                Ont_Configure_Wan_Fragment.this.lay_bridge.setVisibility(8);
                                                Ont_Configure_Wan_Fragment.this.lay_voip.setVisibility(8);
                                            }
                                        }
                                    });
                                    Ont_Configure_Wan_Fragment.this.txt_interface.addTextChangedListener(new TextWatcher() {
                                        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                                        }

                                        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                                        }

                                        public void afterTextChanged(Editable editable) {
                                            String trim = Ont_Configure_Wan_Fragment.this.txt_interface.getText().toString().trim();
                                            int indexOf = Ont_Configure_Wan_Fragment.this.array_interface.indexOf(trim);
                                            if (trim.equals("-- ADD NEW --")) {
                                                ArrayAdapter unused = Ont_Configure_Wan_Fragment.this.adapter_service = new ArrayAdapter(Ont_Configure_Wan_Fragment.this.getActivity(), R.layout.spinner_basic, Ont_Configure_Wan_Fragment.this.array_protocol);
                                                Ont_Configure_Wan_Fragment.this.txt_service.setText(((String) Ont_Configure_Wan_Fragment.this.array_protocol.get(0)).toString());
                                                Ont_Configure_Wan_Fragment.this.txt_service.setAdapter(Ont_Configure_Wan_Fragment.this.adapter_service);
                                                Ont_Configure_Wan_Fragment.this.et_pppoe_vlan.setText("");
                                                Ont_Configure_Wan_Fragment.this.et_pppoe_userid.setText("");
                                                Ont_Configure_Wan_Fragment.this.et_pppoe_pwd.setText("");
                                                Ont_Configure_Wan_Fragment.this.et_voip_vlan.setText("");
                                                Ont_Configure_Wan_Fragment.this.et_voip_tele.setText("");
                                                Ont_Configure_Wan_Fragment.this.et_voip_pwd.setText("");
                                                Ont_Configure_Wan_Fragment.this.et_voip_domain.setText("");
                                                Ont_Configure_Wan_Fragment.this.et_bridge_vlan.setText("");
                                                Ont_Configure_Wan_Fragment.this.txt_add.setEnabled(true);
                                                Ont_Configure_Wan_Fragment.this.txt_delete.setEnabled(false);
                                                Ont_Configure_Wan_Fragment.this.txt_add.setVisibility(0);
                                                Ont_Configure_Wan_Fragment.this.txt_delete.setVisibility(8);
                                                return;
                                            }
                                            Ont_Configure_Wan_Fragment.this.txt_add.setEnabled(false);
                                            Ont_Configure_Wan_Fragment.this.txt_delete.setEnabled(true);
                                            Ont_Configure_Wan_Fragment.this.txt_add.setVisibility(8);
                                            Ont_Configure_Wan_Fragment.this.txt_delete.setVisibility(0);
                                            Ont_Configure_Wan_Fragment.this.txt_service.setText(((String) Ont_Configure_Wan_Fragment.this.array_service.get(indexOf)).toString());
                                            String trim2 = Ont_Configure_Wan_Fragment.this.txt_service.getText().toString().trim();
                                            if (trim2.equals("PPPOE")) {
                                                Ont_Configure_Wan_Fragment.this.et_pppoe_vlan.setText(((String) Ont_Configure_Wan_Fragment.this.array_vlan.get(indexOf)).toString());
                                                Ont_Configure_Wan_Fragment.this.et_pppoe_userid.setText(((String) Ont_Configure_Wan_Fragment.this.array_username.get(indexOf)).toString());
                                                Ont_Configure_Wan_Fragment.this.et_pppoe_pwd.setText(((String) Ont_Configure_Wan_Fragment.this.array_password.get(indexOf)).toString());
                                            } else if (trim2.equals("IPOE")) {
                                                Ont_Configure_Wan_Fragment.this.et_voip_vlan.setText(((String) Ont_Configure_Wan_Fragment.this.array_vlan.get(indexOf)).toString());
                                                Ont_Configure_Wan_Fragment.this.et_voip_tele.setText(((String) Ont_Configure_Wan_Fragment.this.array_username.get(indexOf)).toString());
                                                Ont_Configure_Wan_Fragment.this.et_voip_pwd.setText(((String) Ont_Configure_Wan_Fragment.this.array_password.get(indexOf)).toString());
                                                Ont_Configure_Wan_Fragment.this.et_voip_domain.setText(((String) Ont_Configure_Wan_Fragment.this.array_domain.get(indexOf)).toString());
                                            } else {
                                                Ont_Configure_Wan_Fragment.this.et_bridge_vlan.setText(((String) Ont_Configure_Wan_Fragment.this.array_vlan.get(indexOf)).toString());
                                            }
                                        }
                                    });
                                    Ont_Configure_Wan_Fragment.this.txt_interface.setText(((String) Ont_Configure_Wan_Fragment.this.array_interface.get(0)).toString());
                                    Ont_Configure_Wan_Fragment.this.txt_interface.setAdapter(Ont_Configure_Wan_Fragment.this.adapter_interface);
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
        if (view.getId() == R.id.txt_add) {
            String trim = this.txt_service.getText().toString().trim();
            String str = "domain";
            if (trim.equals("PPPOE")) {
                String obj = this.et_pppoe_vlan.getText().toString();
                String obj2 = this.et_pppoe_userid.getText().toString();
                String obj3 = this.et_pppoe_pwd.getText().toString();
                if (obj.isEmpty() || obj2.isEmpty() || obj3.isEmpty()) {
                    new AlertHelperclass().ntoast("ALl Fields Are Mandatory", getActivity());
                    return;
                }
                String check_services = check_services(obj);
                if (check_services.equals("free")) {
                    JSONObject jSONObject = new JSONObject();
                    try {
                        jSONObject.put("action", "sv");
                        jSONObject.put("interface", "Add");
                        jSONObject.put("vlan", obj);
                        jSONObject.put("username", obj2);
                        jSONObject.put("password", obj3);
                        jSONObject.put("token", this.Token);
                        pppoe_configure(jSONObject);
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    this.txt_alert.setText(Html.fromHtml("<b>PPPOE WAN CREATION FAILED</b><br><br>A Wan Interface <b>(" + check_services.toUpperCase() + ")</b> With Vlan <b>" + obj + "</b> is Already Present<br><br> Please Delete It and try again", 63));
                    this.error_dialog.show();
                }
            } else if (trim.equals("IPOE")) {
                String obj4 = this.et_voip_vlan.getText().toString();
                String obj5 = this.et_voip_tele.getText().toString();
                String obj6 = this.et_voip_pwd.getText().toString();
                String str2 = "ALl Fields Are Mandatory";
                String obj7 = this.et_voip_domain.getText().toString();
                if (obj4.isEmpty() || obj5.isEmpty() || obj6.isEmpty() || obj7.isEmpty()) {
                    new AlertHelperclass().ntoast(str2, getActivity());
                    return;
                }
                String str3 = "</b> is Already Present<br><br> Please Delete It and try again";
                String check_services2 = check_services(obj4);
                if (check_services2.equals("free")) {
                    JSONObject jSONObject2 = new JSONObject();
                    try {
                        jSONObject2.put("action", "sv");
                        jSONObject2.put("interface", "Add");
                        jSONObject2.put("vlan", obj4);
                        jSONObject2.put("username", obj5);
                        jSONObject2.put("password", obj6);
                        jSONObject2.put(str, obj7);
                        jSONObject2.put("token", this.Token);
                        voip_configure(jSONObject2);
                    } catch (JSONException e2) {
                        throw new RuntimeException(e2);
                    }
                } else {
                    this.progress_dialog.cancel();
                    this.txt_alert.setText(Html.fromHtml("<b>VOIP WAN CREATION FAILED</b><br><br>A Wan Interface <b>(" + check_services2.toUpperCase() + ")</b> With Vlan <b>" + obj4 + str3, 63));
                    this.error_dialog.show();
                }
            } else {
                String str4 = "ALl Fields Are Mandatory";
                String str5 = "</b> is Already Present<br><br> Please Delete It and try again";
                if (trim.equals("BRIDGE")) {
                    String obj8 = this.et_bridge_vlan.getText().toString();
                    if (obj8.isEmpty()) {
                        new AlertHelperclass().ntoast(str4, getActivity());
                        return;
                    }
                    String check_services3 = check_services(obj8);
                    if (check_services3.equals("free")) {
                        JSONObject jSONObject3 = new JSONObject();
                        try {
                            jSONObject3.put("action", "sv");
                            jSONObject3.put("interface", "Add");
                            jSONObject3.put("vlan", obj8);
                            jSONObject3.put("token", this.Token);
                            bridge_configure(jSONObject3);
                        } catch (JSONException e3) {
                            throw new RuntimeException(e3);
                        }
                    } else {
                        this.progress_dialog.cancel();
                        this.txt_alert.setText(Html.fromHtml("<b>BRIDGE WAN CREATION FAILED</b><br><br>A Wan Interface <b>(" + check_services3.toUpperCase() + ")</b> With Vlan <b>" + obj8 + str5, 63));
                        this.error_dialog.show();
                    }
                } else {
                    new AlertHelperclass().ntoast("Please Select A Valid Connection Protocol", getActivity());
                }
            }
        } else {
            String str6 = "domain";
            if (view.getId() == R.id.txt_delete) {
                String trim2 = this.txt_service.getText().toString().trim();
                if (trim2.equals("PPPOE")) {
                    String obj9 = this.txt_interface.getText().toString();
                    String trim3 = this.et_pppoe_vlan.getText().toString().trim();
                    String trim4 = this.et_pppoe_userid.getText().toString().trim();
                    String trim5 = this.et_pppoe_pwd.getText().toString().trim();
                    JSONObject jSONObject4 = new JSONObject();
                    try {
                        jSONObject4.put("action", "rm");
                        jSONObject4.put("interface", obj9);
                        jSONObject4.put("vlan", trim3);
                        jSONObject4.put("username", trim4);
                        jSONObject4.put("password", trim5);
                        jSONObject4.put("token", this.Token);
                        pppoe_configure(jSONObject4);
                    } catch (JSONException e4) {
                        throw new RuntimeException(e4);
                    }
                } else if (trim2.equals("IPOE")) {
                    String obj10 = this.txt_interface.getText().toString();
                    String trim6 = this.et_voip_vlan.getText().toString().trim();
                    String trim7 = this.et_voip_tele.getText().toString().trim();
                    String trim8 = this.et_voip_pwd.getText().toString().trim();
                    String trim9 = this.et_voip_domain.getText().toString().trim();
                    JSONObject jSONObject5 = new JSONObject();
                    try {
                        jSONObject5.put("action", "rm");
                        jSONObject5.put("interface", obj10);
                        jSONObject5.put("vlan", trim6);
                        jSONObject5.put("username", trim7);
                        jSONObject5.put("password", trim8);
                        jSONObject5.put(str6, trim9);
                        jSONObject5.put("token", this.Token);
                        voip_configure(jSONObject5);
                    } catch (JSONException e5) {
                        throw new RuntimeException(e5);
                    }
                } else {
                    String obj11 = this.txt_interface.getText().toString();
                    String trim10 = this.et_pppoe_vlan.getText().toString().trim();
                    JSONObject jSONObject6 = new JSONObject();
                    try {
                        jSONObject6.put("action", "rm");
                        jSONObject6.put("interface", obj11);
                        jSONObject6.put("vlan", trim10);
                        jSONObject6.put("token", this.Token);
                        bridge_configure(jSONObject6);
                    } catch (JSONException e6) {
                        throw new RuntimeException(e6);
                    }
                }
            }
        }
    }

    private String check_services(String str) {
        try {
            Boolean.valueOf(this.wan_response.getBoolean(NotificationCompat.CATEGORY_STATUS));
            JSONArray jSONArray = this.wan_response.getJSONArray("message");
            String str2 = "free";
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                if (str.equals(jSONObject.getString("vlan").trim())) {
                    str2 = jSONObject.getString("interface").trim();
                }
            }
            return str2;
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    private void pppoe_configure(JSONObject jSONObject) {
        this.progress_dialog.show();
        try {
            final Object newInstance = this.OntSettingClass.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
            final Class[] clsArr = {String.class, JSONObject.class};
            final Object[] objArr = {this.Ont_Url, jSONObject};
            new Thread(new Runnable() {
                public void run() {
                    try {
                        final JSONObject jSONObject = (JSONObject) Ont_Configure_Wan_Fragment.this.OntSettingClass.getMethod("configureInternet", clsArr).invoke(newInstance, objArr);
                        Ont_Configure_Wan_Fragment.this.requireActivity().runOnUiThread(new Runnable() {
                            public void run() {
                                Ont_Configure_Wan_Fragment.this.progress_dialog.cancel();
                                try {
                                    if (Boolean.valueOf(jSONObject.getBoolean(NotificationCompat.CATEGORY_STATUS)).booleanValue()) {
                                        new AlertHelperclass().ptoast("Success", Ont_Configure_Wan_Fragment.this.getActivity());
                                    } else {
                                        new AlertHelperclass().ntoast("Failed", Ont_Configure_Wan_Fragment.this.getActivity());
                                    }
                                    Ont_Configure_Wan_Fragment.this.get_wan_info();
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
        } catch (InstantiationException e2) {
            throw new RuntimeException(e2);
        } catch (InvocationTargetException e3) {
            throw new RuntimeException(e3);
        } catch (NoSuchMethodException e4) {
            throw new RuntimeException(e4);
        }
    }

    private void voip_configure(JSONObject jSONObject) {
        this.progress_dialog.show();
        try {
            final Object newInstance = this.OntSettingClass.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
            final Class[] clsArr = {String.class, JSONObject.class};
            final Object[] objArr = {this.Ont_Url, jSONObject};
            new Thread(new Runnable() {
                public void run() {
                    try {
                        final JSONObject jSONObject = (JSONObject) Ont_Configure_Wan_Fragment.this.OntSettingClass.getMethod("configureVoipInterface", clsArr).invoke(newInstance, objArr);
                        final JSONObject jSONObject2 = (JSONObject) Ont_Configure_Wan_Fragment.this.OntSettingClass.getMethod("configureVoipSetting", clsArr).invoke(newInstance, objArr);
                        Ont_Configure_Wan_Fragment.this.requireActivity().runOnUiThread(new Runnable() {
                            public void run() {
                                Ont_Configure_Wan_Fragment.this.progress_dialog.cancel();
                                try {
                                    Boolean valueOf = Boolean.valueOf(jSONObject.getBoolean(NotificationCompat.CATEGORY_STATUS));
                                    Boolean valueOf2 = Boolean.valueOf(jSONObject2.getBoolean(NotificationCompat.CATEGORY_STATUS));
                                    if (!valueOf.booleanValue() || !valueOf2.booleanValue()) {
                                        new AlertHelperclass().ntoast("Failed", Ont_Configure_Wan_Fragment.this.getActivity());
                                    } else {
                                        new AlertHelperclass().ptoast("Success", Ont_Configure_Wan_Fragment.this.getActivity());
                                    }
                                    Ont_Configure_Wan_Fragment.this.get_wan_info();
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
        } catch (InstantiationException e2) {
            throw new RuntimeException(e2);
        } catch (InvocationTargetException e3) {
            throw new RuntimeException(e3);
        } catch (NoSuchMethodException e4) {
            throw new RuntimeException(e4);
        }
    }

    private void bridge_configure(JSONObject jSONObject) {
        this.progress_dialog.show();
        try {
            final Object newInstance = this.OntSettingClass.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
            final Class[] clsArr = {String.class, JSONObject.class};
            final Object[] objArr = {this.Ont_Url, jSONObject};
            new Thread(new Runnable() {
                public void run() {
                    try {
                        final JSONObject jSONObject = (JSONObject) Ont_Configure_Wan_Fragment.this.OntSettingClass.getMethod("configureBridge", clsArr).invoke(newInstance, objArr);
                        Ont_Configure_Wan_Fragment.this.requireActivity().runOnUiThread(new Runnable() {
                            public void run() {
                                Ont_Configure_Wan_Fragment.this.progress_dialog.cancel();
                                try {
                                    if (Boolean.valueOf(jSONObject.getBoolean(NotificationCompat.CATEGORY_STATUS)).booleanValue()) {
                                        new AlertHelperclass().ptoast("Success", Ont_Configure_Wan_Fragment.this.getActivity());
                                    } else {
                                        new AlertHelperclass().ntoast("Failed", Ont_Configure_Wan_Fragment.this.getActivity());
                                    }
                                    Ont_Configure_Wan_Fragment.this.get_wan_info();
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
        } catch (InstantiationException e2) {
            throw new RuntimeException(e2);
        } catch (InvocationTargetException e3) {
            throw new RuntimeException(e3);
        } catch (NoSuchMethodException e4) {
            throw new RuntimeException(e4);
        }
    }
}
