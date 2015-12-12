package kr.ac.korea.fridge.fragment;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import kr.ac.korea.fridge.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CostFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CostFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CostFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CostFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CostFragment newInstance(String param1, String param2) {
        CostFragment fragment = new CostFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public CostFragment() {
        // Required empty public constructor
    }

    private TextView tvCostMonth;
    private Button btnCostMonthBefore;
    private Button btnCostMonthAfter;
//    layout_cost_trash_high_money
    //layout_cost_high
    private RelativeLayout layoutCostLow, layoutCostModerate, layoutCostHigh;
    private LinearLayout layoutCostTrashLowAmount, layoutCostTrashLowMoney,
            layoutCostTrashModerateAmount, layoutCostTrashModerateMoney,
            layoutCostTrashHighAmount, layoutCostTrashHighMoney;

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
        View view = inflater.inflate(R.layout.fragment_cost, container, false);
        tvCostMonth = (TextView) view.findViewById(R.id.tv_cost_month);
        btnCostMonthBefore = (Button) view.findViewById(R.id.btn_cost_month_before);
        btnCostMonthBefore.setOnClickListener(this);
        btnCostMonthAfter = (Button) view.findViewById(R.id.btn_cost_month_after);
        btnCostMonthAfter.setOnClickListener(this);


        layoutCostLow = (RelativeLayout) view.findViewById(R.id.layout_cost_low);
        layoutCostLow.setOnClickListener(this);
        layoutCostModerate = (RelativeLayout) view.findViewById(R.id.layout_cost_moderate);
        layoutCostModerate.setOnClickListener(this);
        layoutCostHigh = (RelativeLayout) view.findViewById(R.id.layout_cost_high);
        layoutCostHigh.setOnClickListener(this);

        changeLayout();

        layoutCostTrashLowAmount = (LinearLayout) view.findViewById(R.id.layout_cost_trash_low_amount);
        layoutCostTrashLowMoney = (LinearLayout) view.findViewById(R.id.layout_cost_trash_low_money);
        layoutCostTrashModerateAmount = (LinearLayout) view.findViewById(R.id.layout_cost_trash_moderate_amount);
        layoutCostTrashModerateMoney = (LinearLayout) view.findViewById(R.id.layout_cost_trash_moderate_money);
        layoutCostTrashHighAmount = (LinearLayout) view.findViewById(R.id.layout_cost_trash_high_amount);
        layoutCostTrashHighMoney = (LinearLayout) view.findViewById(R.id.layout_cost_trash_high_money);
        return view;
    }
    private void changeLayout(){
        String month = tvCostMonth.getText().toString();
        int m = Integer.parseInt(month);
        layoutCostLow.setVisibility(View.INVISIBLE);
        layoutCostModerate.setVisibility(View.INVISIBLE);
        layoutCostHigh.setVisibility(View.INVISIBLE);
        switch(m) {
            case 10 :
                layoutCostHigh.setVisibility(View.VISIBLE);
                break;
            case 11 :
                layoutCostModerate.setVisibility(View.VISIBLE);
                break;
            case 12 :
                layoutCostLow.setVisibility(View.VISIBLE);
                break;
            default :
                layoutCostLow.setVisibility(View.VISIBLE);
                tvCostMonth.setText("12");
                break;
        }
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

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btn_cost_month_after :
                increaseMonth();
                break;
            case R.id.btn_cost_month_before :
                decreaseMonth();
                break;
            case R.id.layout_cost_low :
                changeLowLayout();
                break;
            case R.id.layout_cost_moderate :
                changeModerateLayout();
                break;
            case R.id.layout_cost_high :
                changeHighLayout();
                break;
        }
    }

    private void increaseMonth(){
        String month = tvCostMonth.getText().toString();
        int m = Integer.parseInt(month);
        if(m >= 12){
            Toast.makeText(getActivity(), "마지막 달입니다.", Toast.LENGTH_SHORT).show();
        } else {
            tvCostMonth.setText(String.valueOf(++m));
            changeLayout();
        }
    }
    private void decreaseMonth(){
        String month = tvCostMonth.getText().toString();
        int m = Integer.parseInt(month);
        if(m <= 10){
            Toast.makeText(getActivity(), "이전 자료가 없습니다.", Toast.LENGTH_SHORT).show();
        } else {
            tvCostMonth.setText(String.valueOf(--m));
            changeLayout();
        }
    }
    private void changeLowLayout(){
        if(layoutCostTrashLowAmount.getVisibility() == View.VISIBLE) {
            layoutCostTrashLowAmount.setVisibility(View.INVISIBLE);
            layoutCostTrashLowMoney.setVisibility(View.VISIBLE);
        } else {
            layoutCostTrashLowAmount.setVisibility(View.VISIBLE);
            layoutCostTrashLowMoney.setVisibility(View.INVISIBLE);
        }
    }
    private void changeModerateLayout(){
        if(layoutCostTrashModerateAmount.getVisibility() == View.VISIBLE) {
            layoutCostTrashModerateAmount.setVisibility(View.INVISIBLE);
            layoutCostTrashModerateMoney.setVisibility(View.VISIBLE);
        } else {
            layoutCostTrashModerateAmount.setVisibility(View.VISIBLE);
            layoutCostTrashModerateMoney.setVisibility(View.INVISIBLE);
        }
    }
    private void changeHighLayout(){
        if(layoutCostTrashHighAmount.getVisibility() == View.VISIBLE) {
            layoutCostTrashHighAmount.setVisibility(View.INVISIBLE);
            layoutCostTrashHighMoney.setVisibility(View.VISIBLE);
        } else {
            layoutCostTrashHighAmount.setVisibility(View.VISIBLE);
            layoutCostTrashHighMoney.setVisibility(View.INVISIBLE);
        }
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

}
