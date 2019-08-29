package com.lyl.recyclerviewitemdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rv;
    private TextView title;
    private int mSuspensionHeight;
    private LinearLayoutManager layoutManager;
    private int mCurrentPos;
    private ImageView iv;
    private LinearLayout ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        rv = (RecyclerView) findViewById(R.id.rv);
        title = (TextView) findViewById(R.id.title);
        iv = (ImageView) findViewById(R.id.iv);
        ll = (LinearLayout) findViewById(R.id.ll);

        Glide.with(MainActivity.this)
                .load("http://img3.imgtn.bdimg.com/it/u=3583433020,118316633&fm=26&gp=0.jpg")
                .into(iv);

        layoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(layoutManager);
        RvAdapter rvAdapter = new RvAdapter();
        rv.setAdapter(rvAdapter);

        title.setText("title: " + 0);

        rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                //获取悬浮条的高度
                mSuspensionHeight = ll.getHeight();

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //对悬浮条的位置进行调整
                //找到下一个itemView
                View view = layoutManager.findViewByPosition(mCurrentPos + 1);
                if (view != null) {
                    if (view.getTop() <= mSuspensionHeight) {
                        //需要对悬浮条进行移动
                        //移动的距离就是悬浮栏的高度减去top
                        ll.setY(-(mSuspensionHeight - view.getTop()));
                    } else {
                        //保持在原来的位置
                        ll.setY(0);
                    }
                }

                if (mCurrentPos != layoutManager.findFirstVisibleItemPosition()) {
                    mCurrentPos = layoutManager.findFirstVisibleItemPosition();
                    title.setText("title: " + mCurrentPos);

                    if (mCurrentPos % 2 == 0){
                        Glide.with(MainActivity.this)
                                .load("http://img3.imgtn.bdimg.com/it/u=3583433020,118316633&fm=26&gp=0.jpg")
                                .into(iv);
                    }else {
                        Glide.with(MainActivity.this)
                                .load("http://img5.imgtn.bdimg.com/it/u=1043977011,2291928950&fm=26&gp=0.jpg")
                                .into(iv);
                    }
                }

            }
        });

    }
}
