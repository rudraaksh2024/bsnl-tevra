package bsnl.bsnl_teevra.wizards.fragments;

import android.app.AlertDialog;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import bsnl.bsnl_teevra.R;
import java.lang.reflect.InvocationTargetException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Ont_Configure_Status_Fragment extends Fragment {
    private String Ont_Url;
    private String Token;
    /* access modifiers changed from: private */
    public SwipeRefreshLayout lay_refresh;
    /* access modifiers changed from: private */
    public LinearLayout lay_status;
    private LinearLayout lay_voip;
    /* access modifiers changed from: private */
    public LinearLayout lay_wan;
    private Object loginOnt1;
    /* access modifiers changed from: private */
    public Class<?> objNetworkClass;
    /* access modifiers changed from: private */
    public AlertDialog progress_dialog;
    /* access modifiers changed from: private */
    public TextView txt_voip_phn;
    /* access modifiers changed from: private */
    public TextView txt_voip_status;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.ont_configure_status_fragment, viewGroup, false);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View inflate2 = getLayoutInflater().inflate(R.layout.custom_progress, (ViewGroup) null);
        builder.setCancelable(false);
        builder.setView(inflate2);
        this.progress_dialog = builder.create();
        this.lay_refresh = (SwipeRefreshLayout) inflate.findViewById(R.id.lay_refresh);
        this.lay_status = (LinearLayout) inflate.findViewById(R.id.lay_status);
        this.lay_wan = (LinearLayout) inflate.findViewById(R.id.lay_wan);
        this.lay_voip = (LinearLayout) inflate.findViewById(R.id.lay_voip);
        this.txt_voip_phn = (TextView) inflate.findViewById(R.id.txt_voip_phn);
        this.txt_voip_status = (TextView) inflate.findViewById(R.id.txt_voip_status);
        this.loginOnt1 = getArguments().getSerializable("LOGIN_OBJECT");
        getArguments().getString("IMPORT_CLASS");
        String string = getArguments().getString("PACKAGE_NAME");
        try {
            Class<?> cls = this.loginOnt1.getClass();
            this.Token = (String) cls.getMethod("getToken", new Class[0]).invoke(this.loginOnt1, new Object[0]);
            this.Ont_Url = (String) cls.getMethod("fetchUrl", new Class[0]).invoke(this.loginOnt1, new Object[0]);
            Class<?> cls2 = Class.forName(string + "Ont_Configure_Network_Status_Class");
            this.objNetworkClass = cls2;
            get_wan_info(cls2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.lay_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            public void onRefresh() {
                Ont_Configure_Status_Fragment.this.lay_wan.removeAllViews();
                Ont_Configure_Status_Fragment ont_Configure_Status_Fragment = Ont_Configure_Status_Fragment.this;
                ont_Configure_Status_Fragment.get_wan_info(ont_Configure_Status_Fragment.objNetworkClass);
                Ont_Configure_Status_Fragment.this.lay_refresh.setRefreshing(false);
            }
        });
        return inflate;
    }

    /* access modifiers changed from: private */
    public void get_wan_info(Class<?> cls) {
        this.progress_dialog.show();
        this.lay_status.setVisibility(8);
        try {
            final Object newInstance = cls.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
            final Class[] clsArr = {String.class};
            final Object[] objArr = {this.Ont_Url};
            final Class<?> cls2 = cls;
            new Thread(new Runnable() {
                public void run() {
                    try {
                        final JSONObject jSONObject = (JSONObject) cls2.getMethod("extractWanInfo", clsArr).invoke(newInstance, objArr);
                        final JSONObject jSONObject2 = (JSONObject) cls2.getMethod("extractVoipInfo", clsArr).invoke(newInstance, objArr);
                        Ont_Configure_Status_Fragment.this.getActivity().runOnUiThread(new Runnable() {
                            public void run() {
                                Ont_Configure_Status_Fragment.this.progress_dialog.cancel();
                                int i = 0;
                                Ont_Configure_Status_Fragment.this.lay_status.setVisibility(0);
                                try {
                                    if (Boolean.valueOf(jSONObject2.getBoolean(NotificationCompat.CATEGORY_STATUS)).booleanValue()) {
                                        JSONObject jSONObject = jSONObject2.getJSONObject("message");
                                        if (jSONObject.getString(NotificationCompat.CATEGORY_STATUS).equals("Registered")) {
                                            Ont_Configure_Status_Fragment.this.txt_voip_status.setText("Registered");
                                            Ont_Configure_Status_Fragment.this.txt_voip_status.setTextColor(Ont_Configure_Status_Fragment.this.getResources().getColor(R.color.colorGreen));
                                        } else {
                                            Ont_Configure_Status_Fragment.this.txt_voip_status.setText(jSONObject.getString(NotificationCompat.CATEGORY_STATUS));
                                            Ont_Configure_Status_Fragment.this.txt_voip_status.setTextColor(Ont_Configure_Status_Fragment.this.getResources().getColor(R.color.colorRed));
                                        }
                                        Ont_Configure_Status_Fragment.this.txt_voip_phn.setText(jSONObject.getString("number"));
                                    } else {
                                        Ont_Configure_Status_Fragment.this.txt_voip_status.setText("Failed To Fetch Voip Detail");
                                        Ont_Configure_Status_Fragment.this.txt_voip_status.setTextColor(Ont_Configure_Status_Fragment.this.getResources().getColor(R.color.colorRed));
                                        Ont_Configure_Status_Fragment.this.txt_voip_phn.setText("Failed To Fetch Voip Detail");
                                        Ont_Configure_Status_Fragment.this.txt_voip_phn.setTextColor(Ont_Configure_Status_Fragment.this.getResources().getColor(R.color.colorRed));
                                    }
                                    int i2 = -1;
                                    if (Boolean.valueOf(jSONObject.getBoolean(NotificationCompat.CATEGORY_STATUS)).booleanValue()) {
                                        JSONArray jSONArray = jSONObject.getJSONArray("message");
                                        if (jSONArray.length() == 0) {
                                            TextView textView = new TextView(Ont_Configure_Status_Fragment.this.getActivity());
                                            textView.setLayoutParams(new TableRow.LayoutParams(-1, -1));
                                            textView.setText("Sorry!! No Wan Interface Is Created So Far");
                                            textView.setTextColor(Ont_Configure_Status_Fragment.this.getResources().getColor(R.color.colorRed));
                                            textView.setGravity(17);
                                            textView.setPadding(15, 15, 15, 15);
                                            textView.setTextSize(0, Ont_Configure_Status_Fragment.this.getResources().getDimension(R.dimen.smalltext));
                                            textView.setTypeface(Typeface.DEFAULT_BOLD);
                                            Ont_Configure_Status_Fragment.this.lay_wan.addView(textView);
                                            return;
                                        }
                                        int i3 = 0;
                                        while (i3 < jSONArray.length()) {
                                            JSONObject jSONObject2 = jSONArray.getJSONObject(i3);
                                            new LinearLayout(Ont_Configure_Status_Fragment.this.getActivity());
                                            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(i2, -2);
                                            layoutParams.setMargins(i, 10, i, i);
                                            View inflate = Ont_Configure_Status_Fragment.this.getLayoutInflater().inflate(R.layout.custom_layout, (ViewGroup) null);
                                            inflate.setLayoutParams(layoutParams);
                                            TextView textView2 = (TextView) inflate.findViewById(R.id.txt_status);
                                            TextView textView3 = (TextView) inflate.findViewById(R.id.txt_mac);
                                            TextView textView4 = (TextView) inflate.findViewById(R.id.txt_ip);
                                            TextView textView5 = (TextView) inflate.findViewById(R.id.txt_gw);
                                            i3++;
                                            ((TextView) inflate.findViewById(R.id.txt_srno)).setText(Integer.toString(i3));
                                            ((TextView) inflate.findViewById(R.id.txt_interface)).setText(jSONObject2.getString("interface").toUpperCase());
                                            ((TextView) inflate.findViewById(R.id.txt_vlan)).setText(jSONObject2.getString("vlan"));
                                            ((TextView) inflate.findViewById(R.id.txt_mode)).setText(jSONObject2.getString("protocol"));
                                            if (jSONObject2.getString(NotificationCompat.CATEGORY_STATUS).toLowerCase().equals("up")) {
                                                textView2.setTextColor(Ont_Configure_Status_Fragment.this.getResources().getColor(R.color.colorGreen));
                                            } else {
                                                textView2.setTextColor(Ont_Configure_Status_Fragment.this.getResources().getColor(R.color.colorRed));
                                            }
                                            textView2.setText(jSONObject2.getString(NotificationCompat.CATEGORY_STATUS).toUpperCase());
                                            textView3.setText(jSONObject2.getString("mac"));
                                            textView4.setText(jSONObject2.getString("ip"));
                                            textView5.setText(jSONObject2.getString("gateway"));
                                            Ont_Configure_Status_Fragment.this.lay_wan.addView(inflate);
                                            i = 0;
                                            i2 = -1;
                                        }
                                        return;
                                    }
                                    TextView textView6 = new TextView(Ont_Configure_Status_Fragment.this.getActivity());
                                    textView6.setLayoutParams(new TableRow.LayoutParams(-1, -1));
                                    textView6.setText("Sorry!! Failed To Fetch Interface Detail");
                                    textView6.setTextColor(Ont_Configure_Status_Fragment.this.getResources().getColor(R.color.colorRed));
                                    textView6.setGravity(17);
                                    textView6.setPadding(15, 15, 15, 15);
                                    textView6.setTextSize(0, Ont_Configure_Status_Fragment.this.getResources().getDimension(R.dimen.smalltext));
                                    textView6.setTypeface(Typeface.DEFAULT_BOLD);
                                    Ont_Configure_Status_Fragment.this.lay_wan.addView(textView6);
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
