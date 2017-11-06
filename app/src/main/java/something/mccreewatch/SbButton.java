package something.mccreewatch;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

public class SbButton {
    private final String name;
    private final int imageResource;
    private final int soundResource;
    private int soundID;
    private SoundPool soundPool;
    private boolean loaded = false;

    public SbButton(String name, int imageResource, int soundResource, Context ctx, SoundPool soundPool) {
        this.name = name;
        this.imageResource = imageResource;
        this.soundResource = soundResource;
        this.soundPool = soundPool;
        soundID = soundPool.load(ctx, soundResource, 1);
        soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                loaded = true;
            }
        });
    }

    public void playSound(){
        // Log.d("Soundboard", "playSound() called");
//        if(!loaded){
//            Toast.makeText(temp, "Please wait for sounds to load", Toast.LENGTH_SHORT).show();
//            return;
//        }
        soundPool.play(soundID, 1, 1, 1, 0, 1f);
        // Log.d("Soundboard", "Sound played successfully");
    }

    public String getName() {
        return name;
    }

    public int getImageResource() {
        return imageResource;
    }

    public int getSoundResource() {
        return soundResource;
    }
}
