package danielsouza.com.djvideo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;

import java.io.File;
import java.util.ArrayList;

import danielsouza.com.djvideo.R;
import danielsouza.com.djvideo.adapter.ListViewSideMenuAdapter;
import danielsouza.com.djvideo.entity.MenuItem;
import danielsouza.com.djvideo.fragment.ListDirectoryFragment;
import danielsouza.com.djvideo.permissions.Permissions;

public class MainActivity extends NavigationDrawer {

    private ArrayList<MenuItem> listMenuItens = new ArrayList<>();

    private File[] files;
    private File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_layout);
        super.onCreateDrawer();

        Permissions.requestRWPermission(this);

        String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM) + "/";
        toolbar.setTitle(path.substring(path.lastIndexOf("/")).replace("/", ""));
        toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.color_white));
        file = new File(path);

        files = new File[0];

        if(file.isDirectory()){
            files = file.listFiles();
            for (File f : files) {
                listMenuItens.add( new MenuItem( f.getName() ) );
            }
        }

        frameLayout = (FrameLayout) findViewById(R.id.changeable);

        adapter = new ListViewSideMenuAdapter(this, listMenuItens);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(files != null && files[position].isDirectory()){
                    ListDirectoryFragment fragment = new ListDirectoryFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("directyPath", files[position].getAbsolutePath());
                    fragment.setArguments(bundle);
                    getSupportFragmentManager().beginTransaction().replace(R.id.changeable, fragment).commit();
                    toggleMenu();
                }
            }
        });
    }
}
