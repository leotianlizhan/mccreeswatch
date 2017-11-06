package something.mccreewatch;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

public class SbFragment extends Fragment {

    private SbButton[] sbButtons;

    public SbFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sbButtons = ((MainActivity)getActivity()).getSbButtons();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_sb, container, false);
        // Log.d("SbFragment", "Infated");
        GridView gridView = (GridView)view.findViewById(R.id.gridview);
        final SbAdapter sbAdapter = new SbAdapter(getContext(), sbButtons);
        gridView.setAdapter(sbAdapter);
        // Log.d("Soundboard", "GridView initialized in SbFragment");
        gridView.setOnItemClickListener(new GridView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Log.d("Soundboard", "onItemClick is called");
                SbButton sound = sbButtons[position];
                sound.playSound();
            }
        });
        getActivity().setTitle("Soundboard");
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        sbButtons = null;
    }
}
