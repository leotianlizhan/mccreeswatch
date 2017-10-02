package something.mccreewatch;


import android.content.res.Resources;
import android.graphics.drawable.AnimationDrawable;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

public class MainFragment extends Fragment {

    private int durationMccree;
    private int durationMccreeVoiceOnly;
    private SoundPool soundPool;
    private int soundID;
    private int soundIDVoiceOnly;
    private boolean loaded = false;
    private GifDrawable gifDrawable = null;
    private Handler handler = new Handler();
    private Runnable timerRunnable;

    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);

        Button btnMccree = (Button)v.findViewById(R.id.btn_mccree);
        btnMccree.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                playSoundMccree(v);
            }
        });

        Button btnMccreeVoiceOnly = (Button)v.findViewById(R.id.btn_mccree_spam);
        btnMccreeVoiceOnly.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                playSoundMccreeVoiceOnly(v);
            }
        });
        Button btnSoundboard = (Button)v.findViewById(R.id.btn_soundboard);
        btnSoundboard.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                ((MainActivity)getActivity()).navigate(R.id.nav_soundboard);
            }
        });
        getActivity().setTitle("McCree's Watch");

        // set up sound pool
        createSoundPool();
        durationMccree = getSoundDuration(R.raw.mccree);
        durationMccreeVoiceOnly = getSoundDuration(R.raw.mccree_voiceonly);
        soundID = soundPool.load(getContext(), R.raw.mccree, 1);
        Log.d("R.raw.mccree", Integer.toString(R.raw.mccree));
        Log.d("soundID", Integer.toString(soundID));
        soundIDVoiceOnly = soundPool.load(getContext(), R.raw.mccree_voiceonly, 1);
        soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                loaded = true;
            }
        });

        // set up gif
        try {
            gifDrawable = new GifDrawable(getResources(), R.drawable.mccree_meme);
        }catch (Exception e){
            Log.e("Gif", "Declaring new gifDrawable failed");
        };
        GifImageView gifImageView = (GifImageView)v.findViewById(R.id.imgv_gif);
        gifDrawable.stop();
        gifImageView.setImageDrawable(gifDrawable);

        timerRunnable = new Runnable() {
            @Override
            public void run() {
                gifDrawable.stop();
            }
        };

        return v;
    }

    public void playSoundMccree(View v){
        playSoundID(soundID);
    }

    public void playSoundMccreeVoiceOnly(View v){
        playSoundID(soundIDVoiceOnly);
    }
    private void playSoundID(int ID){
        if(!loaded) return;
        soundPool.play(ID, 1, 1, 1, 0, 1f);
        gifDrawable.start();
        handler.removeCallbacks(timerRunnable);
        if(ID == soundID) handler.postDelayed(timerRunnable, durationMccree);
        else handler.postDelayed(timerRunnable, durationMccreeVoiceOnly);
    }

    private void createSoundPool(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            AudioAttributes attributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();
            soundPool = new SoundPool.Builder()
                    .setAudioAttributes(attributes)
                    .setMaxStreams(12)
                    .build();
        }else{
            soundPool = new SoundPool(6,AudioManager.STREAM_MUSIC,0);
        }
    }

    private int getSoundDuration(int rawID){
        MediaPlayer player = MediaPlayer.create(getContext(), rawID);
        return player.getDuration();
    }

}
