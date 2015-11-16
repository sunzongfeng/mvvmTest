package com.example.ht2015.test.login.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.example.ht2015.test.R;
import com.example.ht2015.test.group.activity.ClassManagerActivity;
import com.example.ht2015.test.databinding.LoginMainBinding;
import com.example.ht2015.test.login.model.LoginModel;

public class LoginActivity extends AppCompatActivity {
    private LoginMainBinding mBinding;
    private LoginModel mLoginModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main);

        mBinding = DataBindingUtil.setContentView(this, R.layout.login_main);
        mLoginModel = new LoginModel(this,getResources());
        mBinding.setData(mLoginModel);

        attachButtonListener();

        //也可以 使用这种方式：
//        ActivityLoginBinding mBinding  = ActivityLoginBinding.inflate(getLayoutInflater());

//        如果您在 ListView 或者 RecyclerView 的 adapter 中使用数据绑定，则推荐使用如下的方式：
//        ActivityLoginBinding sBinding = ActivityLoginBinding.inflate(getLayoutInflater(), viewGroup, false);
//        ActivityLoginBinding logininding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.list_item, viewGroup, false);


        //onCreateViewHolder 中 初始化:
//        MovieItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
//                R.layout.movie_item,  parent,  false);

        //onBindViewHolder 中 赋值
//        Movie movie = mMovies.get(position);
//        holder.binding.setVariable(com.aswifter.databinding.BR.movie, movie);
//        holder.binding.executePendingBindings();
    }

    private void attachButtonListener() {
        mBinding.loginOrCreateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(LoginActivity.this,ClassManagerActivity.class);
                startActivity(intent);

            }
        });
        // Workaround for missing impl
        mBinding.returningUserRb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mLoginModel.updateDependentViews(isChecked);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        ensureModelDataIsLodaded();
    }

    private void ensureModelDataIsLodaded() {
        if (!mLoginModel.isLoaded()) {
            mLoginModel.loadAsync();
        }
    }

    public void showShortToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }
}
