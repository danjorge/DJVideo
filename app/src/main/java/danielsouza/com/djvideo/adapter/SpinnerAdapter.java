package danielsouza.com.djvideo.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import danielsouza.com.djvideo.R;

/**
 * Created by daniel.souza on 31/01/2017.
 */

public class SpinnerAdapter extends BaseAdapter {

    private Context mContext;
    private List<String> listItemSpinner;

    public SpinnerAdapter(Context mContext, List<String> listItemSpinner) {
        this.mContext = mContext;
        this.listItemSpinner = listItemSpinner;
    }

    @Override
    public int getCount() {
        return listItemSpinner.size();
    }

    @Override
    public Object getItem(int position) {
        return listItemSpinner.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.spinner_item, null);
        }

        TextView textView = (TextView) convertView.findViewById(R.id.item_spinner);
        textView.setText(listItemSpinner.get(position));

        return convertView;
    }
}
