package com.dazhentech.faithchallengea.challenge.finishfragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.dazhentech.faithchallengea.R;

import cn.iwgang.countdownview.CountdownView;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FinishFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FinishFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FinishFragment extends Fragment implements View.OnClickListener{
    SharedPreferences config;
    CountdownView mCvCountdownView;
    Button finishAndRecord, finishAndContinue;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public FinishFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FinishFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FinishFragment newInstance(String param1, String param2) {
        FinishFragment fragment = new FinishFragment();
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
        View view = inflater.inflate(R.layout.fragment_finish, container, false);
        mCvCountdownView = view.findViewById(R.id.countdown_view_finish);
        //倒计时1分钟-60秒*1000=60000millisecond
        mCvCountdownView.start(9000); // Millisecond
        mCvCountdownView.setOnCountdownEndListener(new CountdownView.OnCountdownEndListener() {
            @Override
            public void onEnd(CountdownView cv) {
//                Toast.makeText(getContext(),"计时结束，记录为失败或不做处理", Toast.LENGTH_LONG).show();
                mListener.onTimeout();
            }
        });
        finishAndRecord = view.findViewById(R.id.finish_and_record);
        finishAndContinue = view.findViewById(R.id.finish_and_continue);
        finishAndRecord.setOnClickListener(this);
        finishAndContinue.setOnClickListener(this);
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

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.finish_and_continue:
                //需要左上角一个view显示当前毅力点总和，在点击完成后，根据剩余秒数加毅力点，跳出一个加毅力点的view在总和view右侧，一闪即逝，跳转到倒计时页。（缩小所有fragment的container）
                //activity容器留出左上角一小块给总和view，和叠加毅力点的view。
                finishOneCount();
                mListener.onRepeatTrial();
                break;
            case R.id.finish_and_record:
                finishOneCount();
                submit();
                SharedPreferences.Editor editor = config.edit();
                editor.putBoolean("lastcombo",false);
                editor.putInt("lastadd",0);
                editor.putInt("thistrialsum",0);
                Toast.makeText(getContext(),"current time:"+config.getInt("thistrialsum",0)+"today: "+config.getInt("lastadd",0),Toast.LENGTH_LONG).show();
                editor.commit();
                mListener.onTrialFinish();
                break;
        }

    }
    public void submit(){

    }
    public void finishOneCount (){
        //finish当前activity，根据剩余秒数加毅力点，提交数据库
        long remaintime = mCvCountdownView.getRemainTime();
        config = getContext().getSharedPreferences("Mytrials",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = config.edit();
        boolean lastcombo = config.getBoolean("lastcombo",false);
        System.out.println(lastcombo);
        int lastadd = config.getInt("lastadd",0);
        int thistrialsum = config.getInt("thistrialsum",0);
        System.out.println(remaintime);
        if(remaintime>=8000L){
            updateThisTrialRecord(thistrialsum,editor,2,2,false);
        } else if((remaintime<8000L && remaintime>=6000)||(remaintime<4000L&& remaintime>=0L)){
            updateThisTrialRecord(thistrialsum,editor,1,1,false);
        } else if ((remaintime<6000L && remaintime>=5500L) ||(remaintime<4500L&& remaintime>=4000L)){
            if(!lastcombo){
                if(lastadd==2){
                    updateThisTrialRecord(thistrialsum,editor,4,4,true);
                }else {
                    updateThisTrialRecord(thistrialsum,editor,2,2,true);
                }
            }else {
                updateThisTrialRecord(thistrialsum,editor,2+lastadd,2+lastadd,true);
            }

        } else if (remaintime<5500L && remaintime>=4500L){
            if(!lastcombo){
                System.out.println("222222222222");
                updateThisTrialRecord(thistrialsum,editor,4,4,true);
            }else {
                System.out.println("33333333333");
                updateThisTrialRecord(thistrialsum,editor,2+lastadd,2+lastadd,true);
            }
        } else {
            updateThisTrialRecord(thistrialsum,editor,1,1,false);
        }

    }
    public void updateThisTrialRecord (int thistrialsum, SharedPreferences.Editor mEditor,int add,int lastadd,boolean lastcombo){
        if (thistrialsum ==0){
            mEditor.putInt("thistrialsum",add);
        } else {
            mEditor.putInt("thistrialsum",thistrialsum+add);
        }
        mEditor.putInt("lastadd",lastadd);
        mEditor.putBoolean("lastcombo",lastcombo);
        mEditor.commit();
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
        void onFragmentInteraction(Uri uri);
        void onTimeout();
        void onTrialFinish();
        void onRepeatTrial();
    }
}
