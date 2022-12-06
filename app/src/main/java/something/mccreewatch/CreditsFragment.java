package something.mccreewatch;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class CreditsFragment extends Fragment {


    public CreditsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_credits, container, false);
        TextView txtCredits = (TextView) v.findViewById(R.id.txt_credits);
        txtCredits.setMovementMethod(LinkMovementMethod.getInstance());
        getActivity().setTitle("Credits");
        return v;
    }

}
