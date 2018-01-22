package com.dazhentech.faithchallengea.challenge.succeedfragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dazhentech.faithchallengea.R;

import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SucceedFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SucceedFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SucceedFragment extends Fragment implements View.OnClickListener {

    private TextView score,body,heart,profession;
    private Button submitGain;
    private EditText gain;
    private SharedPreferences config;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public SucceedFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SucceedFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SucceedFragment newInstance(String param1, String param2) {
        SucceedFragment fragment = new SucceedFragment();
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
        View view = inflater.inflate(R.layout.fragment_succeed, container, false);
        score = view.findViewById(R.id.succeed_score_added);
        body = view.findViewById(R.id.succeed_body_added);
        heart = view.findViewById(R.id.succeed_heart_added);
        profession = view.findViewById(R.id.succeed_profession_added);
        submitGain = view.findViewById(R.id.succeed_submit);
        submitGain.setOnClickListener(this);
        gain = view.findViewById(R.id.succeed_real_gain);
        try{
            config = getContext().getApplicationContext().getSharedPreferences("Mytrials",Context.MODE_PRIVATE);
            String str = String.format(Locale.US,"毅力点+%d",config.getInt("thistrialsum",0));
            score.setText(str);
            String str2 = String.format(Locale.US,"体质+%d",config.getInt("thistrialbody",0));
            body.setText(str2);
            String str3 = String.format(Locale.US,"心灵+%d",config.getInt("thistrialheart",0));
            heart.setText(str3);
            String str4 = String.format(Locale.US,"专业+%d",config.getInt("thistrialprofession",0));
            profession.setText(str4);
        }catch (Exception e){
            e.printStackTrace();
        }

        return view;
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
            case R.id.succeed_submit:
                if(checkEmptyEditText()){
                    Toast.makeText(getActivity(),"请输入你的收获！",Toast.LENGTH_LONG).show();
                }else {
                    mListener.onSucceedSubmitButtonClick(gain.getText().toString());
                }
                break;
        }
    }
    public boolean checkEmptyEditText (){
        if (gain.getText()!=null && gain.getText().length()>0){
            return false;
        }else {
            return true;
        }
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
        void onSucceedSubmitButtonClick(String gain);
    }
}
