package bsnl.bsnl_teevra;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class Report_Availability_Activity extends AppCompatActivity implements View.OnClickListener {
    private ImageView img_bbnl_olt;
    private ImageView img_bng;
    private ImageView img_bsnl_olt;
    private ImageView img_dslam;
    private ImageView img_lmg;
    private ImageView img_mng;
    private ImageView img_oclan;
    private ImageView img_rpr;
    private ImageView img_tip_baf;
    private ImageView img_tip_olt;

    public void onClick(View view) {
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.report_availability_activity);
        this.img_bng = (ImageView) findViewById(R.id.img_bng);
        this.img_rpr = (ImageView) findViewById(R.id.img_rpr);
        this.img_mng = (ImageView) findViewById(R.id.img_mng);
        this.img_oclan = (ImageView) findViewById(R.id.img_oclan);
        this.img_tip_olt = (ImageView) findViewById(R.id.img_tip_olt);
        this.img_tip_baf = (ImageView) findViewById(R.id.img_tip_baf);
        this.img_bsnl_olt = (ImageView) findViewById(R.id.img_bsnl_olt);
        this.img_bbnl_olt = (ImageView) findViewById(R.id.img_bbnl_olt);
        this.img_dslam = (ImageView) findViewById(R.id.img_dslam);
        this.img_lmg = (ImageView) findViewById(R.id.img_lmg);
        this.img_bng.setOnClickListener(this);
        this.img_rpr.setOnClickListener(this);
        this.img_mng.setOnClickListener(this);
        this.img_oclan.setOnClickListener(this);
        this.img_tip_olt.setOnClickListener(this);
        this.img_tip_baf.setOnClickListener(this);
        this.img_bsnl_olt.setOnClickListener(this);
        this.img_bbnl_olt.setOnClickListener(this);
        this.img_dslam.setOnClickListener(this);
        this.img_lmg.setOnClickListener(this);
    }
}
