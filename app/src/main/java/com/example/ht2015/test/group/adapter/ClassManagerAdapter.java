package com.example.ht2015.test.group.adapter;


import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.ht2015.test.R;
import com.example.ht2015.test.databinding.ItemClassBinding;
import com.example.ht2015.test.group.model.ClassModel;

import java.util.ArrayList;
import java.util.List;

public class ClassManagerAdapter extends RecyclerView.Adapter<ClassManagerAdapter.BindingHolder> {

    private Context mContext;
    private LayoutInflater mInflater;
    private List<String> mItems;
    private ItemClickListener mItemClickListener;

    public ClassManagerAdapter(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mItems = new ArrayList<>();
    }

    public void setData(List<String> items) {
        this.mItems = items;
    }

    public void removeItem(int position) {
        mItems.remove(position);
        notifyItemRemoved(position);
    }


    public void setItemClickListener(ItemClickListener itemClickListener) {

        this.mItemClickListener = itemClickListener;
    }


    @Override
    public BindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemClassBinding mItemBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.item_class_manager_layout, parent, false);
        BindingHolder mHolder = new BindingHolder(mItemBinding.getRoot());//得到根布局View设置给ViewHolder
        mHolder.setBinding(mItemBinding);//把mItemBinding设置给ViewHolder
        return mHolder;
    }

    @Override
    public void onBindViewHolder(BindingHolder holder, final int position) {

        //通过holder.getBinding()得到Binding Class
        final String name = mItems.get(position);
        ClassModel model = new ClassModel(mContext.getResources());
        model.setmGroupName(name + "刘德华");
        holder.binding.setData(model);

//        model.setmGroupName(name + "张学友");

        holder.binding.tvName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, name + "who", Toast.LENGTH_SHORT).show();
                if (mItemClickListener != null) {
                    mItemClickListener.onItemClick(view, position);

                }
            }
        });

        //在xml中设置 {data.mGroupName} 之后，下面代码的无效
        holder.binding.tvClass.setText("施瓦辛格");

        //todo 立即更新UI，测试 是否需要
//        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

//    public class ViewHolder extends RecyclerView.ViewHolder {
//        @Bind(R.id.tv_name)
//        TextView mTvName;
//
//        public ViewHolder(View view) {
//            super(view);
//            ButterKnife.bind(this, view);
//        }
//
//        @OnClick({R.id.item_layout})
//        public void onClick(View view) {
//            if (null != mItemClickListener) {
//                mItemClickListener.onItemClick(view, getLayoutPosition());
//            }
//
//        }
//
//        @OnLongClick({R.id.item_layout})
//        public boolean onLongClick(View view) {
//            if (null != mItemClickListener) {
//                mItemClickListener.onLongClick(view, getLayoutPosition());
//            }
//            return true;
//        }
//    }

    public class BindingHolder extends RecyclerView.ViewHolder {
        private ItemClassBinding binding;

        public BindingHolder(View itemView) {
            super(itemView);
        }

        public ItemClassBinding getBinding() {
            return binding;
        }

        public void setBinding(ItemClassBinding binding) {
            this.binding = binding;
        }
    }

    public interface ItemClickListener {

        void onItemClick(View view, int position);

        void onLongClick(View view, int position);

    }

}
