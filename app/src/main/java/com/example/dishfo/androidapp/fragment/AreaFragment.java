package com.example.dishfo.androidapp.fragment;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.baoyz.widget.PullRefreshLayout;
import com.baoyz.widget.RefreshDrawable;
import com.baoyz.widget.SmartisanDrawable;
import com.example.dishfo.androidapp.R;
import com.example.dishfo.androidapp.decoration.GridRecyclerViewDecoration;

import java.util.List;

import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AreaFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AreaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AreaFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final int RECOMMEND_COUNT=1;
    private static final int MODULE_COUNT=12;
    private static final String[] MODULE_TITLE=new String[]{"我关注的人",
    "我关注的专区","学习天堂","运动广场","旅游天地",
    "游戏","娱乐","明星","阅读","美食","动漫",""};
    private  static final int[] MODULE_IC=new int[]{R.mipmap.abc_btn_radio_to_on_mtrl_015};


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ListView mListViewRecommend=null;
    private ListAdapter mListAdapter=null;
    private ScrollView mScrollView=null;
    private RecyclerViewAdapter mRecyclerViewAdapter=null;
    private ImageButton mButtonSearch=null;
    private RecyclerView mRecyclerViewModule=null;

    private OnFragmentInteractionListener mListener;

    public AreaFragment() {

        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AreaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AreaFragment newInstance(String param1, String param2) {
        AreaFragment fragment = new AreaFragment();
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
        View view=inflater.inflate(R.layout.fragment_area, container, false);
        initContent(view);
        return view;
    }

    private void initContent(View view){
        mScrollView=view.findViewById(R.id.fragment_area_scrollview_container);
        mListViewRecommend=view.findViewById(R.id.fragment_area_listview_recommend);

        mListAdapter=new ListAdapter(getContext());
        mRecyclerViewAdapter=new RecyclerViewAdapter(getContext());

        mListViewRecommend.setAdapter(mListAdapter);

        mRecyclerViewModule=view.findViewById(R.id.fragment_area_recyclerview_module);
        mButtonSearch=view.findViewById(R.id.fragment_area_imagebutton_search);

        mRecyclerViewModule.setLayoutManager(new GridLayoutManager(getContext(),2){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        mRecyclerViewModule.setItemAnimator(new DefaultItemAnimator());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mRecyclerViewModule.addItemDecoration(
                    new GridRecyclerViewDecoration(R.drawable.recyclerview_divider_dark1,
                            2,getContext()));
        }
        mRecyclerViewModule.setAdapter(mRecyclerViewAdapter);
        mRecyclerViewModule.setScrollContainer(false);
        mScrollView.setVerticalScrollBarEnabled(false);
        OverScrollDecoratorHelper.setUpOverScroll(mScrollView);


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
          //  throw new RuntimeException(context.toString()
            //        + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public static class RecyclerViewAdapter extends  RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>{

        private Context context;

        public RecyclerViewAdapter(Context context){
            this.context=context;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            MyViewHolder holder=new MyViewHolder(LayoutInflater.
                    from(context).
                    inflate(R.layout.recyclerview_item_area_module,parent,false));

            return holder;
        }

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.textView.setText(MODULE_TITLE[position]);
            if(position!=11)
            holder.imageView.setImageDrawable(context.getDrawable(R.mipmap.abc_btn_radio_to_on_mtrl_015));
        }

        @Override
        public int getItemCount() {
            return MODULE_COUNT;
        }

        public class MyViewHolder extends  RecyclerView.ViewHolder{
            TextView textView=null;
            ImageView imageView=null;
            public MyViewHolder(View itemView) {
                super(itemView);
                textView=itemView.findViewById(R.id.fragment_area_textview_module_label);
                imageView=itemView.findViewById(R.id.fragment_area_imageview_module_label);
            }
        }

    }

    public static class ListAdapter extends BaseAdapter{

        private Context context;

        public ListAdapter(Context context){
            this.context=context;
        }

        @Override
        public int getCount() {
            return RECOMMEND_COUNT;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView==null){
                convertView=LayoutInflater.from(context).
                        inflate(R.layout.listview_item_area_recommend,parent,false);
            }
            else{

            }
            return convertView;
        }

        class ViewHolder
        {
            View itemview;
            public ViewHolder(View itemview){
                this.itemview=itemview;
            }
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
        void onFragmentInteraction(Uri uri);
    }
}