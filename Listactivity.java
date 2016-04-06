package com.example.tato.turkishlira;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.widget.ImageView;
import android.widget.TextView;

public class Listactivity  extends Activity {

    // XML node degerleri
    static final String KEY_NAME = "description";

    static final String KEY_DESC = "category";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.singlelist);

        // intent datayi alma
        Intent in = getIntent();

        // daha intent onceki intentden verileri alma
        String name = in.getStringExtra(KEY_NAME);

        String description = in.getStringExtra(KEY_DESC);

        long CountryID = in.getLongExtra("CountryID", -1);

        // degerleri bastirma
        TextView lblName = (TextView) findViewById(R.id.name_label);

        TextView lblDesc = (TextView) findViewById(R.id.description_label);


        int[] drawables = new int[] { R.drawable.arap, R.drawable.argentina, R.drawable.australia,R.drawable.aruba,R.drawable.bosnia_and_herzegovina,
                R.drawable.barbados,R.drawable.banglades,R.drawable.bulgaria,R.drawable.bahrain,R.drawable.bermuda,R.drawable.bolivia,R.drawable.brazil,R.drawable.bahamas,
                R.drawable.canada, R.drawable.croatia,R.drawable.chile,R.drawable.china,R.drawable.colombia,R.drawable.cyprus,R.drawable.czechrepublic
                , R.drawable.denmark, R.drawable.dominicanrepublic , R.drawable.estonia , R.drawable.egypt,
                R.drawable.euro, R.drawable.fiji,R.drawable.french,R.drawable.ghana,R.drawable.gambian
                , R.drawable.guatemala, R.drawable.hongkong, R.drawable.hungary, R.drawable.iceland, R.drawable.india, R.drawable.indonesia
                , R.drawable.iran , R.drawable.israel, R.drawable.jamaica, R.drawable.japan, R.drawable.jordanian, R.drawable.kenya
                , R.drawable.kuveyt, R.drawable.laos, R.drawable.lebanon, R.drawable.lithuania, R.drawable.macedonia, R.drawable.malagasy
                , R.drawable.malaysia, R.drawable.maldivs, R.drawable.mexico, R.drawable.moldova, R.drawable.namibia, R.drawable.nepal, R.drawable.newzealand
                , R.drawable.nigeria, R.drawable.norway, R.drawable.oman, R.drawable.pakistan, R.drawable.panama, R.drawable.paraguay
                , R.drawable.peru, R.drawable.philippines, R.drawable.poland, R.drawable.qatar, R.drawable.romania, R.drawable.russia
                , R.drawable.saudiarabia, R.drawable.serbia, R.drawable.seychelles, R.drawable.singapore, R.drawable.southafrica, R.drawable.southkorea
                , R.drawable.srilanka, R.drawable.sweden, R.drawable.switzerland, R.drawable.syria, R.drawable.taiwan, R.drawable.thaibaht, R.drawable.tunus
                , R.drawable.turkey, R.drawable.uganda, R.drawable.ukraine, R.drawable.unitedkingdom, R.drawable.unitedstates, R.drawable.uruguay, R.drawable.venezuela
                , R.drawable.vietnam, R.drawable.westafrica};


        ImageView imgView = (ImageView)findViewById(R.id.flag);

        if( drawables.length <= CountryID)
        {
            //
        }
        else {
            imgView.setImageDrawable(ResourcesCompat.getDrawable(getResources(),drawables[(int) CountryID],null));
        }

        lblName.setText(name);

        lblDesc.setText(description);
    }
}

