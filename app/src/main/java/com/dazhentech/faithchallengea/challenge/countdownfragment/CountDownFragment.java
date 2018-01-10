package com.dazhentech.faithchallengea.challenge.countdownfragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.dazhentech.faithchallengea.R;

import cn.iwgang.countdownview.CountdownView;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CountDownFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CountDownFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CountDownFragment extends Fragment {
    private boolean mHandledPress = false;
    private BackHandledInterface backHandledInterface;
//    private CountdownView countDownView;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    @Override
    public void onStart() {
        super.onStart();
        backHandledInterface.setSelectedFragment(this);
    }

    public CountDownFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CountDownFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CountDownFragment newInstance(String param1, String param2) {
        CountDownFragment fragment = new CountDownFragment();
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_count_down, container, false);
        CountdownView mCvCountdownView = view.findViewById(R.id.countdown_view);
        //倒计时1分钟-60秒*1000=60000millisecond
        mCvCountdownView.start(3000); // Millisecond
        mCvCountdownView.setOnCountdownEndListener(new CountdownView.OnCountdownEndListener() {
            @Override
            public void onEnd(CountdownView cv) {
//                Toast.makeText(getContext(),"计时结束，填收获",Toast.LENGTH_LONG).show();
                mListener.onTimeReached();
            }
        });

// or
//        for (int time = 0; time < 1000; time++) {
//            mCvCountdownView.updateShow(time);
//        }
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onTimeReached();
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
        if (context instanceof BackHandledInterface) {
            backHandledInterface = (BackHandledInterface) context;
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
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onTimeReached();
    }

    public boolean onBackPressed(){
        if(!mHandledPress){
            Log.i("countdown-back","回退处理");
            mHandledPress=true;
            return true;
        }
        return false;

    }

}
