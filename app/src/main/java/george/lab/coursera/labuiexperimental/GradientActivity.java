package george.lab.coursera.labuiexperimental;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SeekBar;

public class GradientActivity extends AppCompatActivity {

    private LinearLayout linearLayout;
    private GradientView gv;
    private SeekBar seekBar;
    private SeekBar.OnSeekBarChangeListener seekBarChangeListener;

    private int colorOne = Color.BLUE;
    private int colorTwo = Color.YELLOW;
    private int colorThree = Color.RED;
    private int colorFour = Color.GREEN;
    private int[] gColors = new int[]{Color.BLUE, Color.YELLOW, Color.RED, Color.GREEN};
    private static int RADIAL = 1;
    private static int LINEAR = 2;
    private int gradientType = LINEAR;

    private static final String TAG = "Gradient Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gradient);
        linearLayout = (LinearLayout) findViewById(R.id.MainLayout);
        seekBar = (SeekBar)findViewById(R.id.radiusMeasure);
        seekBar.setMax(31);

        Bundle extras = getIntent().getExtras();
        colorOne = extras.getInt("COLOR_ONE");
        colorTwo = extras.getInt("COLOR_TWO");
        colorThree = extras.getInt("COLOR_THREE");
        colorFour = extras.getInt("COLOR_FOUR");
        gradientType = extras.getInt("GRADIENT");
        Log.i(TAG, "Gradient is: " + gradientType);

        seekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                shiftColors(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        };
        seekBar.setOnSeekBarChangeListener(seekBarChangeListener);

        DisplayMetrics metrics = getBaseContext().getResources().getDisplayMetrics();

        gv = new GradientView(this, metrics.widthPixels, metrics.heightPixels-seekBar.getHeight());
        gv.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        linearLayout.addView(gv);
        gv.SetGradient();
    }
    private int circularShift( int bits, int k ){
        return (bits >>> k) | (bits << (Integer.SIZE - k));
    }

    public void shiftColors(int shift){
        gColors[0] = circularShift(colorOne, shift);
        gColors[1] = circularShift(colorTwo, shift);
        gColors[2] = circularShift(colorThree, shift);
        gColors[3] = circularShift(colorFour, shift);
        gv.SetGradient();
    }

    public void setColors(){
        gColors[0] = colorOne;
        gColors[1] = colorTwo;
        gColors[2] = colorThree;
        gColors[3] = colorFour;

    }

    public class GradientView extends View {

        private Paint mPaint;
        private float centerX;
        private float centerY;
        private float radius;
        private int width;
        private int height;
        private boolean mSetShader = false;
        Bitmap bitmap;
        LinearGradient linearGradient;
        RadialGradient radialGradient;

        OnTouchListener touchListener;

        public GradientView(Context c, int w, int h) {
            super(c);
            mPaint = new Paint();
            mPaint.setAntiAlias(false);
            mPaint.setStyle(Paint.Style.FILL);
            width = w;
            height = h;
            bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            setClickable(true);
            if(gradientType == RADIAL) {
                centerX = (float) width / 2F;
                centerY = (float) height / 2F;
            }
            else {
                centerX = (float) width;
                centerY = (float) height;
            }
            radius = height > width ? (float)height/2F : (float)width/2F;

            touchListener = new OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    centerX = event.getX();
                    centerY = event.getY();
                    SetGradient();
                    invalidate();
                    return false;
                }
            };
            setOnTouchListener(touchListener);
            SetGradient();
        }



        public void SetGradient(){
//            Log.i(TAG,"Gradient now is type: "+gradientType);
            if(gradientType == LINEAR) {
                linearGradient =
                        new LinearGradient(0,0,centerX,centerY,gColors,null, Shader.TileMode.CLAMP);
                if (!mSetShader) {
                    mPaint.setShader(linearGradient);
                }
            }
            else {
                radialGradient =
                        new RadialGradient(centerX, centerY, radius, gColors, null, Shader.TileMode.CLAMP);
                if (!mSetShader) {
                    mPaint.setShader(radialGradient);
                }
            }
            invalidate();
        }

        @Override
        public void setOnTouchListener(OnTouchListener l) {
            super.setOnTouchListener(l);
        }


        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            canvas.drawPaint(mPaint);
            invalidate();
        }

    }
}
