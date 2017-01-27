package danielsouza.com.djvideo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private File[] files;
    private File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("DCIM");

        ListView listViewVideos = (ListView) findViewById(R.id.listViewVideosId);
        List<String> listTextVideos = new ArrayList<>();

        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("DJVideos");
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        Permissions.requestRWPermission(this);

        String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM) + "/";
        file = new File(path);
        files = new File[0];
        if(file.isDirectory()){
            files = file.listFiles();
        }
        for (File f : files) {
            listTextVideos.add(f.getName());
        }

        ListViewVideosAdapter listViewVideosAdapter = new ListViewVideosAdapter(this, listTextVideos);
        listViewVideos.setAdapter(listViewVideosAdapter);
        progressDialog.dismiss();

        listViewVideos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (files != null && files[position].isDirectory()) {
                    Intent intent = new Intent(view.getContext(), Directory.class);
                    intent.putExtra("directoryPath", files[position].getAbsolutePath());
                    startActivity(intent);
                    overridePendingTransition(R.anim.right_in, R.anim.left_out);
                } else {
                    Intent intent = new Intent(view.getContext(), VideoPlayerActivity.class);
                    intent.putExtra("filepath", file.getAbsolutePath());
                    startActivity(intent);
                }
            }
        });
    }
}
