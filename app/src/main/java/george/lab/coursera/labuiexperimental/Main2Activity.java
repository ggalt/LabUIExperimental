package george.lab.coursera.labuiexperimental;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

public class Main2Activity extends AppCompatActivity {
    private int RADIAL = 1;
    private int LINEAR = 2;

    private Context mainContenxt;
    private RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mainContenxt = this;
        radioGroup = (RadioGroup) findViewById(R.id.radiogroupGradient);


        Button okButton = (Button) findViewById(R.id.btnOK);
//        View.OnClickListener okBtnClick = new View.OnClickListener() {
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(mainContenxt, GradientActivity.class);
                intent.putExtra("COLOR_ONE", Color.BLUE);
                intent.putExtra("COLOR_TWO", Color.YELLOW);
                intent.putExtra("COLOR_THREE", Color.RED);
                intent.putExtra("COLOR_FOUR", Color.GREEN);
                switch(radioGroup.getCheckedRadioButtonId()) {
                    case R.id.rbLinear: {
                        intent.putExtra("GRADIENT", LINEAR);
                    }
                    case R.id.rbRadial: {
                        intent.putExtra("GRADIENT", RADIAL);
                    }
                    default:{
                        intent.putExtra("GRADIENT", RADIAL);
                    }
                }

                startActivity(intent);
            }
        });
    }


}
