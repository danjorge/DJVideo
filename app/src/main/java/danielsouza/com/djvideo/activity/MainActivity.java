package danielsouza.com.djvideo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;

import com.jaredrummler.materialspinner.MaterialSpinner;

import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import danielsouza.com.djvideo.R;
import danielsouza.com.djvideo.adapter.ListViewSideMenuAdapter;
import danielsouza.com.djvideo.entity.MenuItem;
import danielsouza.com.djvideo.fragment.ListDirectoryFragment;
import danielsouza.com.djvideo.permissions.Permissions;

public class MainActivity extends NavigationDrawer {

    private ArrayList<MenuItem> listMenuItens = new ArrayList<>();

    private File[] files;
    private List<String> listForSpinner = new ArrayList<>();
    private File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        super.onCreateDrawer();

        Permissions.requestRWPermission(this);

        final String path = Environment.getExternalStorageDirectory() + "/";

        file = new File(path);

        files = new File[0];

        if(file.isDirectory()){
            files = file.listFiles();
            for(File f : files){
                listForSpinner.add(f.getName());
                spinner.setText(f.getName());
            }
        }
        spinner.setItems(files);


        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {

                File fil = (File) item;
                String toPath = FilenameUtils.getFullPath(fil.getAbsolutePath());
                File fileDirectory = new File(toPath);
                if(fileDirectory.isDirectory()){
                   for(File f : fileDirectory.listFiles()){
                       listMenuItens.add(new MenuItem(f.getName()));
                   }
                }
                /*if(files[position].isDirectory()) {
                    for (File f : file.listFiles()) {
                        listMenuItens.add(new MenuItem(f.getName()));
                    }
                }*/
            }
        });




        frameLayout = (FrameLayout) findViewById(R.id.changeable);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(files != null && files[position].isDirectory()){
                    ListDirectoryFragment fragment = new ListDirectoryFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("directyPath", files[position].getAbsolutePath());
                    fragment.setArguments(bundle);
                    toolbar.setTitle(path.substring(path.lastIndexOf("/")).replace("/", ""));
                    toolbar.setTitleTextColor(ContextCompat.getColor(view.getContext(), R.color.color_white));
                    getSupportFragmentManager().beginTransaction().replace(R.id.changeable, fragment).commit();
                    toggleMenu();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        createAdapterMenuItens(listMenuItens);
        super.onResume();
    }

    public void createAdapterMenuItens(ArrayList<MenuItem> listMenuItens){
        adapter = new ListViewSideMenuAdapter(getApplicationContext(), listMenuItens);
        listView.setAdapter(adapter);
    }
}
