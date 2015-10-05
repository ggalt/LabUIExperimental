package george.lab.coursera.labuiexperimental;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static george.lab.coursera.labuiexperimental.R.id.colorView;

/**
 * Created by George on 10/4/2015.
 */
public class colorListAdaper extends BaseAdapter{
    private final List<ColorLabel> mColorItems = new ArrayList<>();
    private final Context mContext;

    private static final String TAG = "colorListAdapter";

    public class ColorLabel {
        public String label;
        public int color;
    }

    public colorListAdaper(Context context) {
        mContext = context;
    }

    public void addItem(String l, int c) {
        ColorLabel colorLabel = new ColorLabel();
        colorLabel.label = l;
        colorLabel.color = c;
        mColorItems.add(colorLabel);
    }

    @Override
    public int getCount() {
        return mColorItems.size();
    }

    @Override
    public Object getItem(int position) {
        return mColorItems.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        RelativeLayout relativeLayout = (RelativeLayout) convertView;

        if(relativeLayout==null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(mContext.LAYOUT_INFLATER_SERVICE);
            relativeLayout = (RelativeLayout) inflater.inflate(R.layout.color_list_adapter, null);
        }

        final TextView colorText = (TextView) relativeLayout.findViewById(colorView);
        colorText.setText(mColorItems.get(position).label);
        colorText.setTextColor(mColorItems.get(position).color);
        return relativeLayout;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
