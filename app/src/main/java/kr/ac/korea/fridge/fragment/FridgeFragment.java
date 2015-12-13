package kr.ac.korea.fridge.fragment;

import android.app.Activity;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import kr.ac.korea.fridge.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FridgeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FridgeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */


public class FridgeFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private SharedPreferences pref = null;

    private LinearLayout porkLayout = null;
    private LinearLayout cheeseLayout = null;
    private LinearLayout firstRow = null;
    private LinearLayout secondRow = null;
    private LinearLayout line = null;
    private boolean detailOpen = false;
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FridgeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FridgeFragment newInstance(String param1, String param2) {
        FridgeFragment fragment = new FridgeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public FridgeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fridge, container, false);
        firstRow = (LinearLayout) view.findViewById(R.id.firstRowOfFirstSelf);
        firstRow.setOnClickListener(this);
        secondRow = (LinearLayout) view.findViewById(R.id.secondRowOfFirstSelf);
        secondRow.setOnClickListener(this);
        line = (LinearLayout) view.findViewById(R.id.ylinTilanjakaja);
        line.setOnClickListener(this);
        porkLayout = (LinearLayout) view.findViewById(R.id.layout_fridge_detail);

        cheeseLayout = (LinearLayout) view.findViewById(R.id.layout_fridge_detail2);

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
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
        public void onFragmentInteraction(Uri uri);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.firstRowOfFirstSelf :
                hideBack();
                showDetail1();
                break;
            case R.id.secondRowOfFirstSelf :
                hideBack();
                showDetail2();
                break;
            case R.id.ylinTilanjakaja :
                hideDetail();
                showBack();
                break;
        }
    }

    private void hideBack(){
        firstRow.setVisibility(View.INVISIBLE);
        secondRow.setVisibility(View.INVISIBLE);

    }
    private void showBack(){
        firstRow.setVisibility(View.VISIBLE);
        secondRow.setVisibility(View.VISIBLE);
    }

    private void hideDetail(){
        porkLayout.setVisibility(View.INVISIBLE);
        cheeseLayout.setVisibility(View.INVISIBLE);
    }
    private void showDetail1(){
        porkLayout.setVisibility(View.VISIBLE);
        cheeseLayout.setVisibility(View.INVISIBLE);
    }

    private void showDetail2(){
        porkLayout.setVisibility(View.INVISIBLE);
        cheeseLayout.setVisibility(View.VISIBLE);
    }

}
