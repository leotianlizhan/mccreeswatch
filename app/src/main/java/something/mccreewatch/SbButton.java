package something.mccreewatch;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;

public class SbButton {
    private final int name;
    private final int imageResource;
    private final int soundResource;
    private static SoundPool soundPool;
    private int soundID;
    private boolean loaded = false;

    public SbButton(int name, int imageResource, int soundResource, Context ctx) {
        this.name = name;
        this.imageResource = imageResource;
        this.soundResource = soundResource;
        soundID = soundPool.load(ctx, R.raw.mccree, 1);
        soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                loaded = true;
            }
        });
    }

    public int getName() {
        return name;
    }

    public int getImageResource() {
        return imageResource;
    }

    public int getSoundResource() {
        return soundResource;
    }
    public static final void Builder(){
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
            soundPool = new SoundPool(6, AudioManager.STREAM_MUSIC,0);
        }
    }
}
