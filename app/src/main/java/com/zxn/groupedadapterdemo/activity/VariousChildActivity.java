package com.zxn.groupedadapterdemo.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.zxn.groupedadapter.adapter.GroupedRecyclerViewAdapter;
import com.zxn.groupedadapter.holder.BaseViewHolder;
import com.zxn.groupedadapterdemo.R;
import com.zxn.groupedadapterdemo.adapter.VariousChildAdapter;
import com.zxn.groupedadapterdemo.model.GroupModel;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 多种子项类型的分组列表
 */
public class VariousChildActivity extends AppCompatActivity {

    private RecyclerView rvList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_list);

        rvList = (RecyclerView) findViewById(R.id.rv_list);

        rvList.setLayoutManager(new LinearLayoutManager(this));
        VariousChildAdapter adapter = new VariousChildAdapter(this, GroupModel.getGroups(10, 5));
        adapter.setOnChildClickListener(new GroupedRecyclerViewAdapter.OnChildClickListener() {
            @Override
            public void onChildClick(GroupedRecyclerViewAdapter adapter, BaseViewHolder holder,
                                     int groupPosition, int childPosition) {
                Toast.makeText(VariousChildActivity.this, "子项：groupPosition = " + groupPosition
                                + ", childPosition = " + childPosition,
                        Toast.LENGTH_LONG).show();
            }
        });
        rvList.setAdapter(adapter);

    }

    public static void openActivity(Context context) {
        Intent intent = new Intent(context, VariousChildActivity.class);
        context.startActivity(intent);
    }
}
