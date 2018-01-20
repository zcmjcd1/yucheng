package com.dazhentech.faithchallengea.momentsfragment;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dazhentech.faithchallengea.R;
import com.dazhentech.faithchallengea.adapters.FragmentAdapter;
import com.dazhentech.faithchallengea.momentsfragment.listfragment.MomentsListFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MomentsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MomentsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MomentsFragment extends Fragment {
    private TabLayout mTablayout;
    private ViewPager mContentVp;

    private List<String> tabIndicators;
    private List<Fragment> tabFragments;
    private FragmentAdapter mFragmentAdapterAdapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public MomentsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment WorldFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MomentsFragment newInstance(String param1, String param2) {
        MomentsFragment fragment = new MomentsFragment();
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
        return inflater.inflate(R.layout.fragment_moments, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mTablayout = view.findViewById(R.id.world_tab_layout);
        mContentVp = view.findViewById(R.id.world_viewpager);
        mContentVp.setOffscreenPageLimit(1);

        initContent();
        initTab();
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
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private void initContent() {
        tabIndicators = new ArrayList<>();
        tabIndicators.add("过去");
        tabIndicators.add("现在");
        tabFragments = new ArrayList<>();
        for ( String s:tabIndicators){
            mTablayout.addTab(mTablayout.newTab().setText(s));
            tabFragments.add(new MomentsListFragment());
        }
        mFragmentAdapterAdapter = new FragmentAdapter(getActivity().getSupportFragmentManager(), tabFragments, tabIndicators);
        mContentVp.setAdapter(mFragmentAdapterAdapter);

    }
    private void initTab() {
        mTablayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        mTablayout.setTabTextColors(Color.parseColor("#FFE49E91"), ContextCompat.getColor(getContext(), R.color.white));
//        mTablayout.setSelectedTabIndicatorColor(ContextCompat.getColor(getContext(), R.color.white));
        ViewCompat.setElevation(mTablayout,10);
        mTablayout.setupWithViewPager(mContentVp);
        mTablayout.setTabsFromPagerAdapter(mFragmentAdapterAdapter);
    }
}