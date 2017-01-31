package bts.uta.com.mystreamingapplication;

import android.annotation.TargetApi;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    private static final int DISCOVER_DURATION = 300;
    private static final int REQUEST_BLU = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void streamViaBT(View v){
        //Karthy can be removed
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            System.out.println("karthy --"+ Environment.getExternalStorageState());
        }
        //getting SDcard root path
        //File root = Environment.getExternalStorageDirectory();
        //System.out.println("karthy Calling getFile");
        //getfile(root);

        File file  = new File(Environment.getExternalStorageDirectory()+ "/Download/soodhu_kavvum.mp3");
        
        streamFile(file);
    }


   

    @TargetApi(Build.VERSION_CODES.M)
    public void streamFile(File file){
        try {
            InputStream  fis = new FileInputStream(file);
            AudioTrack audioTrack = new AudioTrack(AudioManager.STREAM_MUSIC, 44100, AudioFormat.CHANNEL_OUT_MONO,
                    AudioFormat.ENCODING_PCM_16BIT, 20000, AudioTrack.MODE_STREAM);

            audioTrack.play();

            byte[] data = new byte[200];
            int n = 0;
            try {
                while ((n = fis.read(data)) != -1)
                    audioTrack.write(data, 0, data.length);
            }
            catch (IOException e) {
                return;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void getfile(File dir) {
        Log.i("Sd Card1 Path", dir.getAbsolutePath());
        File listFile[] = dir.listFiles();
        if(listFile==null || listFile.length==0){
            System.out.println("No files");
        }
        if (listFile != null && listFile.length > 0) {
            for (int i = 0; i < listFile.length; i++) {

                if (listFile[i].isDirectory()) {
                    System.out.println("directory " + listFile[i]);
                    getfile(listFile[i]);

                } else {
                    System.out.println("file "+ listFile[i]);
                }

            }
        }
    }

}
