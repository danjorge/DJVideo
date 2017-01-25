package danielsouza.com.djvideo;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.content.ContextCompat;
import android.support.v4.os.EnvironmentCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import java.io.File;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listViewVideos;
    private ListViewVideosAdapter listViewVideosAdapter;
    private ProgressDialog progressDialog;

    private List<String> listTextVideos;

    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = getApplicationContext();

        listViewVideos = (ListView) findViewById(R.id.listViewVideosId);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("DJVideos");
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        String path = Environment.getExternalStorageDirectory().toString();
        Log.d("Files", "Path: " +path);
        File video = new File(path);
        listTextVideos.add(video.getName());

        listViewVideosAdapter = new ListViewVideosAdapter(this, listTextVideos);
        listViewVideos.setAdapter(listViewVideosAdapter);
        progressDialog.dismiss();
    }
}
