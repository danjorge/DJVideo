package danielsouza.com.djvideo;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by daniel.souza on 25/01/2017.
 */
public class ListViewVideosAdapter extends BaseAdapter{

    private Context mContext;
    private List<String> listTextVideos;

    public ListViewVideosAdapter(Context mContext, List<String> listTextVideos) {
        this.mContext = mContext;
        this.listTextVideos = listTextVideos;
    }

    @Override
    public int getCount() {
        return listTextVideos.size();
    }

    @Override
    public Object getItem(int position) {
        return listTextVideos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.layout_row_videos, null);
        }

        TextView textView = (TextView) convertView.findViewById(R.id.text_list_view);
        textView.setText(listTextVideos.get(position));

        return convertView;
    }
}
