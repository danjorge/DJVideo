package danielsouza.com.djvideo.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import danielsouza.com.djvideo.R;
import danielsouza.com.djvideo.activity.VideoPlayerActivity;
import danielsouza.com.djvideo.adapter.ListViewFragmentAdapter;

public class ListDirectoryFragment extends Fragment {

    private ListView listViewFragment;
    private ListViewFragmentAdapter adapter;
    private List<String> listFragmentItens;

    public ListDirectoryFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listFragmentItens = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list_directory, container, false);

        listViewFragment = (ListView) view.findViewById(R.id.listViewFragmentId);

        String path = (String) getArguments().get("directyPath");

        final File file = new File(path);
        File[] files;
        files = file.listFiles();

        for (File f : files){
            listFragmentItens.add(f.getName());
        }

        adapter = new ListViewFragmentAdapter(view.getContext(), listFragmentItens);
        listViewFragment.setAdapter(adapter);


        final File[] finalFiles = files;
        listViewFragment.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(view.getContext(), VideoPlayerActivity.class);
                intent.putExtra("filepath", finalFiles[position].getAbsolutePath());
                startActivity(intent);
            }
        });



        return view;
    }
}
