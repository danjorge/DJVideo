package danielsouza.com.djvideo;

import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.VideoView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class VideoPlayerActivity extends AppCompatActivity {

    private VideoView video;
    private int position = 0;
    private ProgressDialog progressDialog;
    private MediaController mediaController;
    private Handler handler = new Handler();

    private TextView textProgress;
    private TextView textDuration;
    private SeekBar seekBar;
    private ImageButton buttonPrevious;
    private ImageButton buttonPlay;
    private ImageButton buttonNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);

        if (mediaController == null) {
            new MediaController(this);
        }

        video = (VideoView) findViewById(R.id.video_view);
        textProgress = (TextView) findViewById(R.id.textCountId);
        textDuration = (TextView) findViewById(R.id.textDurationId);
        seekBar = (SeekBar) findViewById(R.id.seekBarId);
        buttonPrevious = (ImageButton) findViewById(R.id.buttonPreviousId);
        buttonPlay = (ImageButton) findViewById(R.id.buttonPlayId);
        buttonNext = (ImageButton) findViewById(R.id.buttonNextId);

        progressDialog = new ProgressDialog(VideoPlayerActivity.this);
        progressDialog.setTitle("Video Player");
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        try {
            video.setMediaController(mediaController);
            video.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.homem_aranha));
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
                    buttonPlay.setImageResource(android.R.drawable.ic_media_pause);
                } else {
                    video.pause();
                    buttonPlay.setImageResource(android.R.drawable.ic_media_play);
                }
            }
        });

        video.setKeepScreenOn(true);
        video.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                buttonPlay.setImageResource(android.R.drawable.ic_media_play);
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textProgress.setText(getTime(progress, "mm:ss"));
                textDuration.setText(getTime(seekBar.getMax() - progress, "mm:ss"));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                handler.removeCallbacks(updateTimeTask);
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                handler.removeCallbacks(updateTimeTask);
                video.seekTo(seekBar.getProgress());
                updateSeekBar();
            }
        });

        buttonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!video.isPlaying()) {
                    video.start();
                    buttonPlay.setImageResource(android.R.drawable.ic_media_pause);
                } else {
                    video.pause();
                    buttonPlay.setImageResource(android.R.drawable.ic_media_play);
                }
            }
        });

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                video.seekTo(seekBar.getProgress() + 10000);
            }
        });

        buttonPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(video.getCurrentPosition() > 0) {
                    video.seekTo(seekBar.getProgress() - 1000);
                }
            }
        });

        updateSeekBar();

    }

    private void updateSeekBar(){
        handler.postDelayed(updateTimeTask, 100);
    }

    private Runnable updateTimeTask = new Runnable() {
        @Override
        public void run() {
            seekBar.setProgress(video.getCurrentPosition());
            seekBar.setMax(video.getDuration());
            handler.postDelayed(this, 100);
        }
    };

    public static String getTime(long milliSeconds, String dateFormat) {
        // Create a DateFormatter object for displaying date in specified format.
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat, Locale.getDefault());

        // Create a calendar object that will convert the time value in milliseconds to date.
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return formatter.format(calendar.getTime());
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
