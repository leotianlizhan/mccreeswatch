package something.mccreewatch;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private SbButton[] sbButtons;
    private SoundPool soundPool;
    private NavigationView navigationView;
    public static final int numButtons = 26;

    public void soundPoolBuilder(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            AudioAttributes attributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_GAME)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();
            soundPool = new SoundPool.Builder()
                    .setAudioAttributes(attributes)
                    .setMaxStreams(10)
                    .build();
        }else{
            soundPool = new SoundPool(6, AudioManager.STREAM_MUSIC,0);
        }
        // add all the sounds
        sbButtons = new SbButton[numButtons];
        Context ctx = this;
        sbButtons[0] = new SbButton("Tracer", R.drawable.sb_tracer, R.raw.sb_tracer_1, ctx, soundPool);
        sbButtons[1] = new SbButton("Winston", R.drawable.sb_winston, R.raw.sb_winston_1, ctx, soundPool);
        sbButtons[2] = new SbButton("Genji", R.drawable.sb_genji, R.raw.sb_genji_1, ctx, soundPool);
        sbButtons[3] = new SbButton("McCree", R.drawable.sb_mccree, R.raw.sb_mccree_1, ctx, soundPool);
        sbButtons[4] = new SbButton("Pharah", R.drawable.sb_pharah, R.raw.sb_pharah_1, ctx, soundPool);
        sbButtons[5] = new SbButton("Reaper", R.drawable.sb_reaper, R.raw.sb_reaper_1, ctx, soundPool);
        sbButtons[6] = new SbButton("Soldier", R.drawable.sb_soldier, R.raw.sb_soldier_1, ctx, soundPool);
        sbButtons[7] = new SbButton("Sombra", R.drawable.sb_sombra, R.raw.sb_sombra_1, ctx, soundPool);
        sbButtons[8] = new SbButton("Bastion", R.drawable.sb_bastion, R.raw.sb_bastion_1, ctx, soundPool);
        sbButtons[9] = new SbButton("Hanzo", R.drawable.sb_hanzo, R.raw.sb_hanzo_1, ctx, soundPool);
        sbButtons[10] = new SbButton("Junkrat", R.drawable.sb_junkrat, R.raw.sb_junkrat_1, ctx, soundPool);
        sbButtons[11] = new SbButton("Mei", R.drawable.sb_mei, R.raw.sb_mei_1, ctx, soundPool);
        sbButtons[12] = new SbButton("Torb", R.drawable.sb_torb, R.raw.sb_torb_1, ctx, soundPool);
        sbButtons[13] = new SbButton("Widowmaker", R.drawable.sb_widowmaker, R.raw.sb_widowmaker_1, ctx, soundPool);
        sbButtons[14] = new SbButton("D.Va", R.drawable.sb_dva, R.raw.sb_dva_1, ctx, soundPool);
        sbButtons[15] = new SbButton("Orisa", R.drawable.sb_orisa, R.raw.sb_orisa_1, ctx, soundPool);
        sbButtons[16] = new SbButton("Reinhardt", R.drawable.sb_reinhardt, R.raw.sb_reinhardt_1, ctx, soundPool);
        sbButtons[17] = new SbButton("Roadhog", R.drawable.sb_roadhog, R.raw.sb_roadhog_1, ctx, soundPool);
        sbButtons[18] = new SbButton("Zarya", R.drawable.sb_zarya, R.raw.sb_zarya_1, ctx, soundPool);
        sbButtons[19] = new SbButton("Ana", R.drawable.sb_ana, R.raw.sb_ana_1, ctx, soundPool);
        sbButtons[20] = new SbButton("Lucio", R.drawable.sb_lucio, R.raw.sb_lucio_1, ctx, soundPool);
        sbButtons[21] = new SbButton("Mercy", R.drawable.sb_mercy, R.raw.sb_mercy_1, ctx, soundPool);
        sbButtons[22] = new SbButton("Symmetra", R.drawable.sb_symmetra, R.raw.sb_symmetra_1, ctx, soundPool);
        sbButtons[23] = new SbButton("Zenyatta", R.drawable.sb_zenyatta, R.raw.sb_zenyatta_1, ctx, soundPool);
        sbButtons[24] = new SbButton("Doomfist", R.drawable.sb_doomfist, R.raw.sb_doomfist_1, ctx, soundPool);
        sbButtons[25] = new SbButton("Moira", R.drawable.sb_moira, R.raw.sb_moira_1, ctx, soundPool);
    }

    public SbButton[] getSbButtons(){
        return sbButtons;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Set the fragment initially
        MainFragment fragment = new MainFragment();
        android.support.v4.app.FragmentTransaction fragmentTransaction =
                getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_main);

        soundPoolBuilder();

        getSupportFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {

            @Override
            public void onBackStackChanged() {
                Fragment f = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
                if (f != null){
                    updateTitleAndDrawer (f);
                }

            }
        });
    }
    private void updateTitleAndDrawer (Fragment fragment){
        String fragClassName = fragment.getClass().getName();

        if (fragClassName.equals(MainFragment.class.getName())){
            setTitle ("McCree's Watch");
            navigationView.setCheckedItem(R.id.nav_main);
        }
        else if (fragClassName.equals(SbFragment.class.getName())){
            setTitle ("Soundboard");
            navigationView.setCheckedItem(R.id.nav_soundboard);
        }
        else if (fragClassName.equals(WidgetFragment.class.getName())){
            setTitle ("Widget");
            navigationView.setCheckedItem(R.id.nav_widget);
        }
        else if (fragClassName.equals(CreditsFragment.class.getName())){
            setTitle ("Credits");
            navigationView.setCheckedItem(R.id.nav_credits);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        navigate(id);

        // Close nav drawer
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void navigate(int id){
        Fragment fragment = null;
        if (id == R.id.nav_main) {
            fragment = new MainFragment();
        } else if (id == R.id.nav_soundboard) {
            fragment = new SbFragment();
        } else if (id == R.id.nav_widget) {
            fragment = new WidgetFragment();
        } else if (id == R.id.nav_credits) {
            fragment = new CreditsFragment();
        }

        if(fragment != null){
            replaceFragment(fragment);
        }
    }

    private void replaceFragment (Fragment fragment){
        String backStateName =  fragment.getClass().getName();
        String fragmentTag = backStateName;

        FragmentManager manager = getSupportFragmentManager();
        boolean fragmentPopped = manager.popBackStackImmediate (backStateName, 0);

        if (!fragmentPopped && manager.findFragmentByTag(fragmentTag) == null){ //fragment not in back stack, create it.
            FragmentTransaction ft = manager.beginTransaction();
            ft.replace(R.id.fragment_container, fragment, fragmentTag);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.addToBackStack(backStateName);
            ft.commit();
        }
    }
}
