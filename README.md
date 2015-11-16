# mvvmTest
一 . 工作环境

	你的app要使用Data Binding，需要添加Data Binding到gradle构建文件里，如下：

		   dependencies {
		       classpath "com.android.tools.build:gradle:1.2.3"
		       classpath "com.android.databinding:dataBinder:1.0-rc0"
		   }
		}
	然后确保jcenter在repositories列表里，如下：

		allprojects {
		   repositories {
		       jcenter()
		   }
		}
	在每一个你想要使用Data Binding的module，添加如下的插件：

		apply plugin: ‘com.android.application'
		apply plugin: 'com.android.databinding'
	Data Binding插件将会在你的项目内添加必需提供的以及编译配置依赖。

二. xml
	1. 最外层是 layout
	2. 多了一个 data 节点
	3. 可以重命名 自动生成的 Binding文件 ：  <data class = ".UserBinding">
	4. 可以import 类 ：<import type="android.view.View"/>  
		冲突时，可以重命名 <import type="com.example.real.estate.View" alias="Vista"/>
	5. 支持运算符：
   			android:text="@{String.valueOf(index + 1)}"
			android:visibility="@{age < 13 ? View.GONE : View.VISIBLE}"
			android:transitionName='@{"image_" + id}'
	7. 支持JAVA一些语法：
		<data>
		    <import type="com.example.MyStringUtils"/>
		    <variable name="user" type="com.example.User"/>
		</data>
		<TextView
		   android:text="@{MyStringUtils.capitalize(user.lastName)}"
		   android:layout_width="wrap_content"
		   android:layout_height="wrap_content"/>
	8. 代码：
		<?xml version="1.0" encoding="utf-8"?>
		<layout xmlns:android="http://schemas.android.com/apk/res/android">
		   <data>
		       <variable name="user" type="com.example.User"/>
		   </data>
		   <LinearLayout
		       android:orientation="vertical"
		       android:layout_width="match_parent"
		       android:layout_height="match_parent">
		       <TextView android:layout_width="wrap_content"
		           android:layout_height="wrap_content"
		           android:text="@{user.firstName}"/>
		       <TextView android:layout_width="wrap_content"
		           android:layout_height="wrap_content"
		           android:text="@{user.lastName}"/>
		   </LinearLayout>
		</layout>
三。model
	1. 变量 初始化，及使用
		private static class User extends BaseObservable {
		   public final ObservableField<String> firstName =
		       new ObservableField<>();
		   public final ObservableField<String> lastName =
		       new ObservableField<>();
		   public final ObservableInt age = new ObservableInt();
		}
		就是这样，要访问该值，使用set和get方法：

		user.firstName.set("Google");
		int age = user.age.get();


	2. 

四。activity
	1. 绑定
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	   super.onCreate(savedInstanceState);
	   ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.main_activity);
	   User user = new User("Test", "User");
	   binding.setUser(user);

	}

五。fragment
 	View view = inflater.inflate(R.layout.fragment_main, container, false);
    mBinding = FragmentMainBinding.bind(view);
    mViewModel = new MainModel(this, getResources());
    mBinding.setData(mViewModel);

六。 RecyclerView

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
		//holder.binding.executePendingBindings();

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
    }







