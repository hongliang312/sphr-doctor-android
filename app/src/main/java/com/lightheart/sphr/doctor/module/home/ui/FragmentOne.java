package com.lightheart.sphr.doctor.module.home.ui;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Transition;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lightheart.sphr.doctor.R;
import com.lightheart.sphr.doctor.base.BaseFragment;
import com.lightheart.sphr.doctor.bean.TextBean;
import java.util.ArrayList;
import butterknife.BindView;

/**
 * Created by admin  2019/11/29/15:38
 * Describe
 * 作者 洪亮 admin
 */
public class FragmentOne extends BaseFragment implements BaseQuickAdapter.OnItemClickListener {

    @BindView(R.id.rvText)
    RecyclerView rvText;
    @BindView(R.id.toggleButton)
    Button toggleButton;
    @BindView(R.id.textView)
    TextView textView;
    private static final String[] TITLE = {"Animation", "MultipleItem", "Header/Footer", "PullToRefresh", "Section", "EmptyView", "DragAndSwipe", "ItemClick", "ExpandableItem", "DataBinding", "UpFetchData", "SectionMultipleItem", "DiffUtil"};
    private static final String[] TIME = {"2019-12-2", "2019-12-3", "2019-12-4", "2019-12-5", "2019-12-6", "2019-12-7", "2019-12-8", "2019-12-9", "2020-1-1", "2020-1-2", "2020-1-3", "2020-1-4", "2020-1-5"};
    //    private static final String[] PRICE = {"55", "56", "57", "58", "59", "60", "66", "88", "52", "30", "66", "13", "14"};
    private static final String[] TEXT = {"若不是你突然闯进我生活、我怎会把死守的寂寞放任了、如果我真的没那么爱过、爱着一个没有灵魂的人、世界都是黑色"};
    private ArrayList<TextBean> mDataList = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_one;
    }

    @Override
    protected void initInjector() {
    }

    public static FragmentOne newInstance(int itimeCategory, int mPage) {
        FragmentOne fragment = new FragmentOne();
        Bundle bundle = new Bundle();
        bundle.putInt("timeCategory", itimeCategory);
        bundle.putInt("mPage", mPage);
        fragment.setArguments(bundle);
        return fragment;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void initView(View view) {
        initData();
//        rvText.setLayoutManager(new LinearLayoutManager(getActivity()));
//        MineWalletAdapter adapter = new MineWalletAdapter(R.layout.mine_wallet_item, mDataList);
//        adapter.setOnItemClickListener(this);
//        rvText.setAdapter(adapter);
//        final TextViewSuffixWrapper textViewSuffixWrapper = new TextViewSuffixWrapper(textView);
//        textViewSuffixWrapper.getMainContent();
//        textViewSuffixWrapper.setSuffix("查看更多");
//        CharSequence suffix = textViewSuffixWrapper.getSuffix();
//        assert suffix != null;
//        textViewSuffixWrapper.suffixColor("...".length(), suffix.length(), R.color.bottom_color, new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ToastUtils.showShort("click");
//                if (textViewSuffixWrapper.isCollapsed()) {
//                    textViewSuffixWrapper.expand();
//                }
//            }
//        });
//        Transition transition = textViewSuffixWrapper.getTransition();
//        if (transition != null) {
//            transition.setDuration(2000);
//        }
//
//        ViewParent parent = textViewSuffixWrapper.getTextView().getParent();
//        if (parent == null) {
//            throw new TypeCastException("null cannot be cast to non-null type android.view.ViewGroup");
//        }else {
//            textViewSuffixWrapper.setSceneRoot((ViewGroup) parent);
//            textViewSuffixWrapper.collapse(false);
//            textViewSuffixWrapper.getTextView().setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    ToastUtils.showShort("click view");
//                }
//            });
//        }
//        toggleButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                textViewSuffixWrapper.toggle();
//            }
//        });
    }

    private void initData() {
        mDataList = new ArrayList<>();
        for (int i = 0; i < TITLE.length; i++) {
            TextBean item = new TextBean();
            item.setTitle(TITLE[i]);
            item.setName(TIME[i]);
//            item.setPrice(TEXT[i]);
//            item.setText(TEXT[i]);
            mDataList.add(item);
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
    }
}
