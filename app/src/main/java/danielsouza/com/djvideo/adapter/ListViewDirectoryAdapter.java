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
 * Created by daniel.souza on 27/01/2017.
 */

public class ListViewDirectoryAdapter extends BaseAdapter {

    private Context mContext;
    private List<String> listFilesDirectory;

    public ListViewDirectoryAdapter(Context mContext, List<String> listFilesDirectory) {
        this.mContext = mContext;
        this.listFilesDirectory = listFilesDirectory;
    }

    @Override
    public int getCount() {
        return listFilesDirectory.size();
    }

    @Override
    public Object getItem(int position) {
        return listFilesDirectory.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.layout_row_directory_files, null);
        }

        TextView textView = (TextView) convertView.findViewById(R.id.text_list_view_directory);
        textView.setText(listFilesDirectory.get(position));

        return convertView;
    }
}
