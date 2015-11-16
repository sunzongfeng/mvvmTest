package com.example.ht2015.test.main;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.example.ht2015.test.R;
import com.example.ht2015.test.databinding.FragmentMainBinding;
import com.example.ht2015.test.login.activity.LoginActivity;

public class MainActivityFragment extends Fragment {
    private FragmentMainBinding mBinding;
    private MainModel mViewModel;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        mBinding = FragmentMainBinding.bind(view);
        mViewModel = new MainModel(this, getResources());
        mBinding.setData(mViewModel);
        attachButtonListener();
        return view;
    }

    private void attachButtonListener() {
        mBinding.loginOrCreateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mViewModel.logInClicked();
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);

            }
        });
        // Workaround for missing impl
        mBinding.returningUserRb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mViewModel.updateDependentViews(isChecked);
            }
        });
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        ensureModelDataIsLodaded();
    }

    private void ensureModelDataIsLodaded() {
        if (!mViewModel.isLoaded()) {
            mViewModel.loadAsync();
        }
    }

    public void showShortToast(String text) {
        Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
    }
}
