package george.lab.coursera.labuiexperimental;

import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Hashtable;

public class Main2Activity extends AppCompatActivity {
    static final private int RADIAL = 1;
    static final private int LINEAR = 2;

    private static final String TAG = "Main2Activity";

    int[] valColors = {Color.BLACK, Color.BLUE, Color.CYAN, Color.DKGRAY, Color.GRAY, Color.GREEN, Color.LTGRAY, Color.MAGENTA, Color.RED, Color.TRANSPARENT, Color.WHITE, Color.YELLOW};
    String[] valLabels = {"BLACK", "BLUE", "CYAN", "DKGRAY", "GRAY", "GREEN", "LTGRAY", "MAGENTA", "RED", "TRANSPARENT", "WHITE", "YELLOW" };

    private RadioGroup radioGroup;
    private RadioButton rad;
    private RadioButton lin;
    private LinearLayout vertLO;
    private LinearLayout listviewLO;
    private ListView colorListOne;
    private ListView colorListTwo;
    private ListView colorListThree;
    private ListView colorListFour;

    private colorListAdaper listOne;
    private colorListAdaper listTwo;
    private colorListAdaper listThree;
    private colorListAdaper listFour;

    private int c1 = Color.BLUE;
    private int c2 = Color.YELLOW;
    private int c3 = Color.RED;
    private int c4 = Color.GREEN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        radioGroup = (RadioGroup) findViewById(R.id.radiogroupGradient);
        rad = (RadioButton) findViewById(R.id.rbRadial);
        lin = (RadioButton) findViewById(R.id.rbLinear);

        rad.setChecked(false);
        lin.setChecked(true);

        vertLO = (LinearLayout) findViewById(R.id.verticalLayout);
        listviewLO = (LinearLayout) vertLO.findViewById(R.id.listViewLayout);

        colorListOne = (ListView) listviewLO.findViewById(R.id.lvOne);
        colorListTwo = (ListView) listviewLO.findViewById(R.id.lvTwo);
        colorListThree = (ListView) listviewLO.findViewById(R.id.lvThree);
        colorListFour = (ListView) listviewLO.findViewById(R.id.lvFour);
        setupColorListViews();
        colorListOne.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                colorListAdaper.ColorLabel colorLabel = (colorListAdaper.ColorLabel) parent.getItemAtPosition(position);
                c1 = colorLabel.color;
                Log.i(TAG, "COLOR1="+colorLabel.label+c1);
            }
        });
        colorListTwo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                colorListAdaper.ColorLabel colorLabel = (colorListAdaper.ColorLabel) parent.getItemAtPosition(position);
                c2 = colorLabel.color;
                Log.i(TAG, "COLOR2="+colorLabel.label+c2);
            }
        });
        colorListThree.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                colorListAdaper.ColorLabel colorLabel = (colorListAdaper.ColorLabel) parent.getItemAtPosition(position);
                c3 = colorLabel.color;
                Log.i(TAG, "COLOR3="+colorLabel.label+c3);
            }
        });
        colorListFour.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                colorListAdaper.ColorLabel colorLabel = (colorListAdaper.ColorLabel) parent.getItemAtPosition(position);
                c4 = colorLabel.color;
                Log.i(TAG, "COLOR4="+colorLabel.label+c4);
            }
        });

        Button okButton = (Button) findViewById(R.id.btnOK);
//        View.OnClickListener okBtnClick = new View.OnClickListener() {
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(view.getContext(), GradientActivity.class);
                intent.putExtra("COLOR_ONE", c1);
                intent.putExtra("COLOR_TWO", c2);
                intent.putExtra("COLOR_THREE", c3);
                intent.putExtra("COLOR_FOUR", c4);
                Log.i(TAG,"c1:"+c1+"c2"+c2+"c3"+c3+"c4"+c4);
                if(lin.isChecked()){
                    intent.putExtra("GRADIENT", LINEAR);
                }
                else {
                    intent.putExtra("GRADIENT", RADIAL);
                }
                startActivity(intent);
            }
        });
    }

    private void setupColorListViews() {
        listOne = new colorListAdaper(getApplicationContext());
        colorListOne.setAdapter(listOne);
        listTwo = new colorListAdaper(getApplicationContext());
        colorListTwo.setAdapter(listTwo);
        listThree = new colorListAdaper(getApplicationContext());
        colorListThree.setAdapter(listThree);
        listFour = new colorListAdaper(getApplicationContext());
        colorListFour.setAdapter(listFour);

        for(int i = 0; i < 12; i++ ) {
            listOne.addItem(valLabels[i],valColors[i]);
            listTwo.addItem(valLabels[i],valColors[i]);
            listThree.addItem(valLabels[i],valColors[i]);
            listFour.addItem(valLabels[i],valColors[i]);
        }
    }


}
