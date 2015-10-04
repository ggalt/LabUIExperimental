package george.lab.coursera.labuiexperimental;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Main2Activity extends AppCompatActivity {
    private int RADIAL = 1;
    private int LINEAR = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Button okButton = (Button) findViewById(R.id.btnOK);
//        View.OnClickListener okBtnClick = new View.OnClickListener() {
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle gradientElements = new Bundle();
                gradientElements.putInt("COLOR_ONE", Color.BLUE);
                gradientElements.putInt("COLOR_TWO",Color.YELLOW);
                gradientElements.putInt("COLOR_THREE",Color.RED);
                gradientElements.putInt("COLOR_FOUR",Color.GREEN);
                gradientElements.putInt("GRADIENT", RADIAL);
            }
        });
    }


}
