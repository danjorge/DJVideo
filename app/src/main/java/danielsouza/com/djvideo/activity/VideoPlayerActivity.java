package danielsouza.com.djvideo.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.MediaController;
import android.widget.VideoView;

import danielsouza.com.djvideo.permissions.Permissions;
import danielsouza.com.djvideo.R;

public class VideoPlayerActivity extends AppCompatActivity {

    private VideoView video;
    private int position = 0;
    private ProgressDialog progressDialog;
    private MediaController mediaController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);

        if (mediaController == null) {
            mediaController = new MediaController(this);
        }

        Permissions.requestRWPermission(this);

        Intent intent = getIntent();
        String filePath = "";
        filePath = (String) intent.getExtras().get("filepath");

        video = (VideoView) findViewById(R.id.video_view);

        progressDialog = new ProgressDialog(VideoPlayerActivity.this);
        progressDialog.setTitle("Video Player");
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        try {
            video.setMediaController(mediaController);
            if (filePath != null) {
                video.setVideoURI(Uri.parse(filePath));
            }
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }

        video.requestFocus();
        video.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                progressDialog.dismiss();
                video.seekTo(position);
                if (position == 0) {
                    video.start();
                } else {
                    video.pause();
                }
            }
        });

        video.setKeepScreenOn(true);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mediaController.show();
        return super.onTouchEvent(event);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("Position", video.getCurrentPosition());
        video.pause();
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        position = savedInstanceState.getInt("Position");
        video.seekTo(position);
    }

    @Override
    protected void onDestroy() {
        video.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if (mp != null && mp.isPlaying()) {
                    mp.stop();
                    mp.release();
                }
            }
        });
        super.onDestroy();

    }
}
