package danielsouza.com.djvideo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import danielsouza.com.djvideo.R;

/**
 * Created by daniel.souza on 06/02/2017.
 */

public class ListViewFragmentAdapter extends BaseAdapter {

    private Context mContext;
    private List<String> listFragmentItens;

    public ListViewFragmentAdapter(Context mContext, List<String> listFragmentItens) {
        this.mContext = mContext;
        this.listFragmentItens = listFragmentItens;
    }

    @Override
    public int getCount() {
        return listFragmentItens.size();
    }

    @Override
    public Object getItem(int position) {
        return listFragmentItens.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.layout_row_fragment, null);
        }

        TextView textViewFragment = (TextView) convertView.findViewById(R.id.textListViewFragmentId);
        textViewFragment.setText(listFragmentItens.get(position));

        return convertView;
    }
}
