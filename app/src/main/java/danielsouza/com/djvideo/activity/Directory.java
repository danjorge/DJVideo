package danielsouza.com.djvideo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import danielsouza.com.djvideo.permissions.Permissions;
import danielsouza.com.djvideo.R;
import danielsouza.com.djvideo.adapter.ListViewDirectoryAdapter;

public class Directory extends AppCompatActivity {

    private File file;
    private File[] files;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_directory);

        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        Permissions.requestRWPermission(this);

        ListView listViewFilesDirectory = (ListView) findViewById(R.id.listViewDirectoryId);
        List<String> listFilesDirectory = new ArrayList<>();

        Intent intent = getIntent();
        String directoryPath = (String) intent.getExtras().get("directoryPath");

        if (directoryPath != null) {
            setTitle(directoryPath.substring(directoryPath.lastIndexOf("/")).replace("/", ""));
        }

        file = null;
        if (directoryPath != null) {
            file = new File(directoryPath);
        }

        files = new File[0];
        if (file != null) {
            files = file.listFiles();
        }

        for (File f : files){
            listFilesDirectory.add(f.getName());
        }

        ListViewDirectoryAdapter listViewDirectoryAdapter = new ListViewDirectoryAdapter(this, listFilesDirectory);
        listViewFilesDirectory.setAdapter(listViewDirectoryAdapter);

        listViewFilesDirectory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(view.getContext(), VideoPlayerActivity.class);
                intent.putExtra("filepath", files[position].getAbsolutePath());
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.right_out, R.anim.left_in);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return false;
        }
        return super.onOptionsItemSelected(item);
    }
}
