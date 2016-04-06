package com.example.tato.turkishlira;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends Activity {

    // All static variables
    static final String URL = "http://themoneyconverter.com/rss-feed/TRY/rss.xml";
    // XML node keys
    static final String KEY_ITEM = "item"; // parent node
    static final String KEY_ID = "title";
    static final String KEY_NAME = "description";
    static final String KEY_DESC = "category";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        ArrayList<HashMap<String, String>> menuItems = new ArrayList<HashMap<String, String>>();

        parser parser = new parser();
        String xml = null;
        try {
            xml = parser.getXmlFromUrl(URL);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Document doc = parser.getDomElement(xml);


        NodeList nl = doc.getElementsByTagName(KEY_ITEM);
        // butun elemanlar icin loop <item> icindeki 
        Flag Flags[] = new Flag[nl.getLength()];
        for (int i = 0; i < nl.getLength(); i++) {
            //  HashMap yaratma
            HashMap<String, String> map = new HashMap<String, String>();
            Element e = (Element) nl.item(i);
            // her node u  HashMap key => value e ekleme
            map.put(KEY_ID, parser.getValue(e, KEY_ID));
            map.put(KEY_NAME, parser.getValue(e, KEY_NAME));
            map.put(KEY_DESC, parser.getValue(e, KEY_DESC));
            //  HashListeyi  ArrayListeye aktarma
            menuItems.add(map);

            String Text = parser.getValue(e,KEY_NAME);
            //bazi parsinglere devam 
            int indexOfEqualSign = Text.indexOf("=");
            Text = Text.substring(indexOfEqualSign+1,Text.length());
            //parsing son 
            
            if( drawables.length <= i)
            {
                
                Flags[i] = new Flag(R.drawable.turkey, Text );
            }
            else {
                Flags[i] = new Flag(drawables[i], Text );
            }

        }
        FlagAdapter adapter = new FlagAdapter(this,R.layout.newlayoutlist,Flags);

        ListView listView1 = (ListView)findViewById(R.id.listView1);

        View header = (View)getLayoutInflater().inflate(R.layout.listview_item_row, null);
        listView1.addHeaderView(header);

        listView1.setAdapter(adapter);


        listView1.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // secili itemlerden degeri alma
                String name = ((TextView) view.findViewById(R.id.txtTitle)).getText().toString();
/*                String description = ((TextView) view.findViewById(R.id.description)).getText().toString();*/

                // yeni intent
                Intent in = new Intent(getApplicationContext(), Listactivity.class);
                in.putExtra("CountryID", id);
                in.putExtra(KEY_NAME, name);

                startActivity(in);

            }

        });

        Button buton = (Button ) findViewById(R.id.button1);


        buton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,CurrencyConverterActivity.class);
                startActivity(intent);
            }
        });


        Button buton2 = (Button ) findViewById(R.id.button2);

        buton2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Chart.class);
                startActivity(intent);
            }
        });
    }





}
