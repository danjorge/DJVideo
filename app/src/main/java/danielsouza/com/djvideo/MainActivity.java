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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listViewVideos = (ListView) findViewById(R.id.listViewVideosId);
        List<String> listTextVideos = new ArrayList<>();

        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("DJVideos");
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();

//        String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM) + "/KumaComico/Harry Potter Aula Fuleragi (Kuma Cômico).mp4";
        String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM) + "/";
        final File file = new File(path);
        File files[] = null;
        if(file.isDirectory()){
            files = file.listFiles();
        }
        if (files != null) {
            for (File f : files) {
                listTextVideos.add(f.getName());
            }
        }

        ListViewVideosAdapter listViewVideosAdapter = new ListViewVideosAdapter(this, listTextVideos);
        listViewVideos.setAdapter(listViewVideosAdapter);
        progressDialog.dismiss();

        listViewVideos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(view.getContext(), VideoPlayerActivity.class);
                intent.putExtra("filepath", file.getAbsolutePath());
                startActivity(intent);
            }
        });
    }
}
