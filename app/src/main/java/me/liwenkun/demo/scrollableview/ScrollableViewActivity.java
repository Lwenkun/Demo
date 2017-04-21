package me.liwenkun.demo.scrollableview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import me.liwenkun.demo.R;

/**
 * Created by lwenkun on 2017/4/15.
 */

public class ScrollableViewActivity extends AppCompatActivity {

    private static String TAG = "ScrollableViewActivity";

    private RecyclerView rvScroll;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrollable_view);

        rvScroll = (RecyclerView) findViewById(R.id.rv_scroll);
        rvScroll.setLayoutManager(new LinearLayoutManager(this));
        rvScroll.setAdapter(new MyAdapter(Cheeses.CHEESES));

        rvScroll.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (newState == RecyclerView.SCROLL_STATE_SETTLING) {
                    Log.d(TAG, "state setting");
                } else if (newState == RecyclerView.SCROLL_STATE_DRAGGING) {
                    Log.d(TAG, "state dragging");
                } else if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    Log.d(TAG, "state idle");
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            //    Log.d(TAG, "onScrolled called");
               if (!ViewCompat.canScrollVertically(recyclerView, -1)) {
                   Toast.makeText(ScrollableViewActivity.this, "cannot scroll up", Toast.LENGTH_SHORT).show();
               }
            }
        });



    }
}

class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private String[] dataList;

    public MyAdapter(String[] text) {
        dataList = text;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_view, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder local = (ViewHolder) holder;
        local.textView.setText(dataList[position]);
    }

    @Override
    public int getItemCount() {
        return dataList == null ? 0 : dataList.length;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.tv_text);
        }
    }
}
