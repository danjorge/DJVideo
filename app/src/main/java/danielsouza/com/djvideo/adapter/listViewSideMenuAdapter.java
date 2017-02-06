package danielsouza.com.djvideo.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import danielsouza.com.djvideo.R;
import danielsouza.com.djvideo.entity.MenuItem;

/**
 * Created by daniel.souza on 06/02/2017.
 */

public class ListViewSideMenuAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<MenuItem> listMenuItens;

    public ListViewSideMenuAdapter(Context mContext, ArrayList<MenuItem> listMenuItens) {
        this.mContext = mContext;
        this.listMenuItens = listMenuItens;
    }

    @Override
    public int getCount() {
        return listMenuItens.size();
    }

    @Override
    public Object getItem(int position) {
        return listMenuItens.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.layout_row_sidemenu, null);
        }

        LinearLayout rowWrapped = (LinearLayout) convertView.findViewById(R.id.rowWrapped);
        TextView textLabel = (TextView) convertView.findViewById(R.id.labelDirectoryId);

        rowWrapped.setBackgroundColor(ContextCompat.getColor(mContext, listMenuItens.get(position).isSelected() ? R.color.blue : android.R.color.transparent));
        textLabel.setText(listMenuItens.get(position).getNome());
        textLabel.setTextColor(ContextCompat.getColor(mContext, listMenuItens.get(position).isSelected() ? android.R.color.white : android.R.color.black));


        return convertView;
    }
}
