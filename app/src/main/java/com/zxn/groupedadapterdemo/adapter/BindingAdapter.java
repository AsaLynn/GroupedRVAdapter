package com.zxn.groupedadapterdemo.adapter;

import android.content.Context;

import com.zxn.groupedadapter.adapter.GroupedRecyclerViewAdapter;
import com.zxn.groupedadapter.holder.BaseViewHolder;
import com.zxn.groupedadapterdemo.R;
import com.zxn.groupedadapterdemo.databinding.AdapterBindingChildBinding;
import com.zxn.groupedadapterdemo.databinding.AdapterBindingFooterBinding;
import com.zxn.groupedadapterdemo.databinding.AdapterBindingHeaderBinding;
import com.zxn.groupedadapterdemo.entity.ChildEntity;
import com.zxn.groupedadapterdemo.entity.GroupEntity;

import java.util.ArrayList;

/**
 * 这是使用DataBinding的adapter。
 */
public class BindingAdapter extends GroupedRecyclerViewAdapter {

    private ArrayList<GroupEntity> mGroups;

    public BindingAdapter(Context context, ArrayList<GroupEntity> groups) {
        super(context, true);
        mGroups = groups;
    }

    @Override
    public int getGroupCount() {
        return mGroups == null ? 0 : mGroups.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        ArrayList<ChildEntity> children = mGroups.get(groupPosition).getChildren();
        return children == null ? 0 : children.size();
    }

    @Override
    public boolean hasHeader(int groupPosition) {
        return true;
    }

    @Override
    public boolean hasFooter(int groupPosition) {
        return true;
    }

    @Override
    public int getHeaderLayout(int viewType) {
        return R.layout.adapter_binding_header;
    }

    @Override
    public int getFooterLayout(int viewType) {
        return R.layout.adapter_binding_footer;
    }

    @Override
    public int getChildLayout(int viewType) {
        return R.layout.adapter_binding_child;
    }

    @Override
    public void onBindHeaderViewHolder(BaseViewHolder holder, int groupPosition) {
        GroupEntity entity = mGroups.get(groupPosition);
        AdapterBindingHeaderBinding binding = holder.getBinding();
        binding.setEntity(entity);
    }

    @Override
    public void onBindFooterViewHolder(BaseViewHolder holder, int groupPosition) {
        GroupEntity entity = mGroups.get(groupPosition);
        AdapterBindingFooterBinding binding = holder.getBinding();
        binding.setEntity(entity);
    }

    @Override
    public void onBindChildViewHolder(BaseViewHolder holder, int groupPosition, int childPosition) {
        ChildEntity entity = mGroups.get(groupPosition).getChildren().get(childPosition);
        AdapterBindingChildBinding binding = holder.getBinding();
        binding.setEntity(entity);
    }
}
