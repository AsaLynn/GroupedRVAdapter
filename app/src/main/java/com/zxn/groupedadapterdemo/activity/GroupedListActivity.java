package com.zxn.groupedadapterdemo.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.zxn.groupedadapter.adapter.GroupedRecyclerViewAdapter;
import com.zxn.groupedadapter.decoration.GroupedLinearItemDecoration;
import com.zxn.groupedadapter.holder.BaseViewHolder;
import com.zxn.groupedadapterdemo.R;
import com.zxn.groupedadapterdemo.adapter.GroupedListAdapter;
import com.zxn.groupedadapterdemo.decoration.CustomLinearItemDecoration;
import com.zxn.groupedadapterdemo.model.GroupModel;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 分组的列表
 */
public class GroupedListActivity extends AppCompatActivity {

    private RecyclerView rvList;
    private RecyclerView.ItemDecoration itemDecoration;
    private GroupedListAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_list);

        rvList = (RecyclerView) findViewById(R.id.rv_list);

        rvList.setLayoutManager(new LinearLayoutManager(this));
        adapter = new GroupedListAdapter(this, GroupModel.getGroups(10, 5));
        adapter.setOnHeaderClickListener(new GroupedRecyclerViewAdapter.OnHeaderClickListener() {
            @Override
            public void onHeaderClick(GroupedRecyclerViewAdapter adapter, BaseViewHolder holder,
                                      int groupPosition) {
                Toast.makeText(GroupedListActivity.this, "组头：groupPosition = " + groupPosition,
                        Toast.LENGTH_LONG).show();
            }
        });
        adapter.setOnFooterClickListener(new GroupedRecyclerViewAdapter.OnFooterClickListener() {
            @Override
            public void onFooterClick(GroupedRecyclerViewAdapter adapter, BaseViewHolder holder,
                                      int groupPosition) {
                Toast.makeText(GroupedListActivity.this, "组尾：groupPosition = " + groupPosition,
                        Toast.LENGTH_LONG).show();
            }
        });
        adapter.setOnChildClickListener(new GroupedRecyclerViewAdapter.OnChildClickListener() {
            @Override
            public void onChildClick(GroupedRecyclerViewAdapter adapter, BaseViewHolder holder,
                                     int groupPosition, int childPosition) {
                Toast.makeText(GroupedListActivity.this, "子项：groupPosition = " + groupPosition
                                + ", childPosition = " + childPosition,
                        Toast.LENGTH_LONG).show();
            }
        });
        rvList.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_ment, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // 移除前面的ItemDecoration
        removeItemDecoration();

        switch (item.getItemId()) {
            case R.id.none:

                itemDecoration = null;
                return true;

            case R.id.space:

                // 空白分割线，只需要设置分割线大小，不需要设置样式
                itemDecoration = new GroupedLinearItemDecoration(adapter,
                        20, null,20,null,20,null);
                rvList.addItemDecoration(itemDecoration);
                return true;

            case R.id.ordinary:

                // 普通分割线，设置分割线大小和头、尾、子项的分割线样式
                itemDecoration = new GroupedLinearItemDecoration(adapter,
                        20, getResources().getDrawable(R.drawable.green_divider),
                        20,getResources().getDrawable(R.drawable.purple_divider),
                        20,getResources().getDrawable(R.drawable.pink_divider));
                rvList.addItemDecoration(itemDecoration);
                return true;

            case R.id.custom:

                // 自定义分割线，可以根据需要设置每个item的分割线大小和样式
                itemDecoration = new CustomLinearItemDecoration(this,adapter);
                rvList.addItemDecoration(itemDecoration);

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void removeItemDecoration() {
        if (itemDecoration != null) {
            rvList.removeItemDecoration(itemDecoration);
        }
    }

    public static void openActivity(Context context) {
        Intent intent = new Intent(context, GroupedListActivity.class);
        context.startActivity(intent);
    }
}
