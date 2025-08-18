package bsnl.bsnl_teevra;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import bsnl.bsnl_teevra.wizards.Ont_Configure_Activity;

public class Wizards_Activity extends AppCompatActivity implements View.OnClickListener {
    private LinearLayout card_quicksetup;
    private LinearLayout card_tr069;
    private ImageView imageView;
    private LinearLayout lay1;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.wizards_activity);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int i = displayMetrics.widthPixels;
        this.imageView = (ImageView) findViewById(R.id.img_header);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.lay1);
        this.lay1 = linearLayout;
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, (int) (((double) i) / 2.5d)));
        this.card_quicksetup = (LinearLayout) findViewById(R.id.card_quicksetup);
        this.card_tr069 = (LinearLayout) findViewById(R.id.card_tr069);
        this.card_quicksetup.setOnClickListener(this);
        this.card_tr069.setOnClickListener(this);
        this.imageView.setOnClickListener(this);
    }

    public void onClick(View view) {
        if (view.getId() == R.id.card_quicksetup) {
            startActivity(new Intent(this, Ont_Configure_Activity.class));
        } else if (view.getId() == R.id.card_tr069) {
            startActivity(new Intent(this, Tr069_Activity.class));
        } else if (view.getId() == R.id.img_header) {
            Intent intent = new Intent(this, DashBoard_New.class);
            intent.setFlags(67108864);
            startActivity(intent);
            finish();
        }
    }
}
