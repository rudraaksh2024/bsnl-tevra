package bsnl.bsnl_teevra.wizards;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import bsnl.bsnl_teevra.R;
import bsnl.bsnl_teevra.SectionPageAdapter;
import bsnl.bsnl_teevra.wizards.fragments.Ont_Configure_Quick_Fragment;
import bsnl.bsnl_teevra.wizards.fragments.Ont_Configure_Status_Fragment;
import bsnl.bsnl_teevra.wizards.fragments.Ont_Configure_TR069_Fragment;
import bsnl.bsnl_teevra.wizards.fragments.Ont_Configure_Wan_Fragment;
import bsnl.bsnl_teevra.wizards.fragments.Ont_Configure_Wlan_Fragment;
import com.google.android.material.tabs.TabLayout;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import org.json.JSONException;
import org.json.JSONObject;

public class Ont_Configure_Status_Activity extends AppCompatActivity implements View.OnClickListener {
    private String IMPORT_CLASS;
    private String PACKAGE_NAME;
    /* access modifiers changed from: private */
    public AlertDialog counter_dialog;
    private AlertDialog error_dialog;
    /* access modifiers changed from: private */
    public AlertDialog info_dialog;
    /* access modifiers changed from: private */
    public Object loginOnt;
    private AlertDialog logout_dialog;
    private AlertDialog progress_dialog;
    private TabLayout tab_features;
    private TextView txt_reboot;
    private ViewPager viewpager;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.ont_configure_status_activity);
        this.loginOnt = getIntent().getSerializableExtra("MYOBJECT");
        this.IMPORT_CLASS = getIntent().getStringExtra("IMPORT_CLASS");
        this.PACKAGE_NAME = getIntent().getStringExtra("PACKAGE_NAME");
        this.txt_reboot = (TextView) findViewById(R.id.txt_reboot);
        this.tab_features = (TabLayout) findViewById(R.id.tab_features);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        this.viewpager = viewPager;
        viewPager.setOffscreenPageLimit(5);
        setupViewPager(this.viewpager);
        this.tab_features.setupWithViewPager(this.viewpager);
        this.txt_reboot.setOnClickListener(this);
    }

    private void setupViewPager(ViewPager viewPager) {
        SectionPageAdapter sectionPageAdapter = new SectionPageAdapter(getSupportFragmentManager());
        Ont_Configure_Quick_Fragment ont_Configure_Quick_Fragment = new Ont_Configure_Quick_Fragment();
        Ont_Configure_Status_Fragment ont_Configure_Status_Fragment = new Ont_Configure_Status_Fragment();
        Ont_Configure_Wan_Fragment ont_Configure_Wan_Fragment = new Ont_Configure_Wan_Fragment();
        Ont_Configure_Wlan_Fragment ont_Configure_Wlan_Fragment = new Ont_Configure_Wlan_Fragment();
        Ont_Configure_TR069_Fragment ont_Configure_TR069_Fragment = new Ont_Configure_TR069_Fragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("LOGIN_OBJECT", (Serializable) this.loginOnt);
        bundle.putString("IMPORT_CLASS", this.IMPORT_CLASS);
        bundle.putString("PACKAGE_NAME", this.PACKAGE_NAME);
        ont_Configure_Status_Fragment.setArguments(bundle);
        ont_Configure_Wlan_Fragment.setArguments(bundle);
        ont_Configure_TR069_Fragment.setArguments(bundle);
        ont_Configure_Quick_Fragment.setArguments(bundle);
        ont_Configure_Wan_Fragment.setArguments(bundle);
        sectionPageAdapter.addFragments(ont_Configure_Quick_Fragment, "QUICK");
        sectionPageAdapter.addFragments(ont_Configure_Status_Fragment, "STATUS");
        sectionPageAdapter.addFragments(ont_Configure_Wan_Fragment, "WAN");
        sectionPageAdapter.addFragments(ont_Configure_Wlan_Fragment, "WLAN");
        sectionPageAdapter.addFragments(ont_Configure_TR069_Fragment, "TR069");
        viewPager.setAdapter(sectionPageAdapter);
        viewPager.setCurrentItem(1);
    }

    public void onClick(View view) {
        if (view.getId() == R.id.txt_reboot) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            View inflate = getLayoutInflater().inflate(R.layout.custom_info, (ViewGroup) null);
            TextView textView = (TextView) inflate.findViewById(R.id.txt_alert);
            TextView textView2 = (TextView) inflate.findViewById(R.id.txt_update);
            textView.setText("COMMIT AND REBOOT");
            textView.setTypeface(Typeface.defaultFromStyle(1));
            textView.setTextColor(getResources().getColor(R.color.colorRed));
            ((TextView) inflate.findViewById(R.id.txt_error)).setText("Are You Sure,\nYou Want To Commit Changes And Reboot Your ONT ?");
            textView2.setText("REBOOT");
            builder.setCancelable(false);
            builder.setView(inflate);
            AlertDialog create = builder.create();
            this.info_dialog = create;
            create.show();
            ((TextView) inflate.findViewById(R.id.txt_cancel)).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Ont_Configure_Status_Activity.this.info_dialog.cancel();
                }
            });
            textView2.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    new Thread(new Runnable() {
                        public void run() {
                            try {
                                JSONObject jSONObject = (JSONObject) Ont_Configure_Status_Activity.this.loginOnt.getClass().getMethod("reboot", new Class[0]).invoke(Ont_Configure_Status_Activity.this.loginOnt, new Object[0]);
                                Ont_Configure_Status_Activity.this.runOnUiThread(new Runnable() {
                                    public void run() {
                                        Ont_Configure_Status_Activity.this.info_dialog.cancel();
                                        AlertDialog.Builder builder = new AlertDialog.Builder(Ont_Configure_Status_Activity.this);
                                        View inflate = Ont_Configure_Status_Activity.this.getLayoutInflater().inflate(R.layout.custom_counter, (ViewGroup) null);
                                        TextView textView = (TextView) inflate.findViewById(R.id.txt_error);
                                        final TextView textView2 = (TextView) inflate.findViewById(R.id.txt_counter);
                                        builder.setCancelable(false);
                                        builder.setView(inflate);
                                        AlertDialog unused = Ont_Configure_Status_Activity.this.counter_dialog = builder.create();
                                        Ont_Configure_Status_Activity.this.counter_dialog.show();
                                        textView.setText("Commit And Reboot Initiated\nKindly Wait For 1 Min Before Reloging");
                                        textView.setTypeface(Typeface.defaultFromStyle(1));
                                        textView.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                                        textView.setTextColor(Ont_Configure_Status_Activity.this.getResources().getColor(R.color.colorRed));
                                        new CountDownTimer(60000, 1000) {
                                            public void onTick(long j) {
                                                textView2.setText(((j / 1000) % 60) + "");
                                            }

                                            public void onFinish() {
                                                Ont_Configure_Status_Activity.this.counter_dialog.cancel();
                                                Intent intent = new Intent(Ont_Configure_Status_Activity.this.getApplicationContext(), Ont_Configure_Activity.class);
                                                intent.setFlags(67108864);
                                                Ont_Configure_Status_Activity.this.startActivity(intent);
                                                Ont_Configure_Status_Activity.this.finish();
                                            }
                                        }.start();
                                    }
                                });
                            } catch (IllegalAccessException e) {
                                throw new RuntimeException(e);
                            } catch (InvocationTargetException e2) {
                                throw new RuntimeException(e2);
                            } catch (NoSuchMethodException e3) {
                                throw new RuntimeException(e3);
                            }
                        }
                    }).start();
                }
            });
        }
    }

    public void onBackPressed() {
        Ont_Logout();
    }

    private void Ont_Logout() {
        new Thread(new Runnable() {
            public void run() {
                try {
                    final JSONObject jSONObject = (JSONObject) Ont_Configure_Status_Activity.this.loginOnt.getClass().getMethod("logout", new Class[0]).invoke(Ont_Configure_Status_Activity.this.loginOnt, new Object[0]);
                    Ont_Configure_Status_Activity.this.runOnUiThread(new Runnable() {
                        public void run() {
                            try {
                                Toast.makeText(Ont_Configure_Status_Activity.this.getBaseContext(), jSONObject.getString("message").toString(), 1).show();
                                Intent intent = new Intent(Ont_Configure_Status_Activity.this.getApplicationContext(), Ont_Configure_Activity.class);
                                intent.setFlags(67108864);
                                Ont_Configure_Status_Activity.this.startActivity(intent);
                                Ont_Configure_Status_Activity.this.finish();
                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    });
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                } catch (InvocationTargetException e2) {
                    throw new RuntimeException(e2);
                } catch (NoSuchMethodException e3) {
                    throw new RuntimeException(e3);
                }
            }
        }).start();
    }
}
