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


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SbFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SbFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SbFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private SoundPool soundPool;
    private SbButton[] sbButtons;

    private OnFragmentInteractionListener mListener;

    public SbFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SbFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SbFragment newInstance(String param1, String param2) {
        SbFragment fragment = new SbFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        // Note: onCreate is called before onCreateView
        soundPoolBuilder();
    }

    public void soundPoolBuilder(){
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
        // add all the sounds
        sbButtons = new SbButton[25];
        Context ctx = getContext();
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_sb, container, false);
        Log.d("SbFragment", "Infated");
        GridView gridView = (GridView)view.findViewById(R.id.gridview);
        final SbAdapter sbAdapter = new SbAdapter(getContext(), sbButtons);
        gridView.setAdapter(sbAdapter);
        Log.d("Soundboard", "GridView initialized in SbFragment");
        gridView.setOnItemClickListener(new GridView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("Soundboard", "onItemClick is called");
                SbButton sound = sbButtons[position];
                sound.playSound();
            }
        });
        getActivity().setTitle("Soundboard");
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
