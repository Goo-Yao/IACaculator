package com.goo.iacaculator.presenter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.goo.iacaculator.base.BasePresenter;
import com.goo.iacaculator.entity.ArithmeticInfo;
import com.goo.iacaculator.model.MainModel;
import com.goo.iacaculator.model.mInterface.MainMInterface;
import com.goo.iacaculator.view.adapter.RVMainAdapter;
import com.goo.iacaculator.view.vinterface.MainVInterface;

/**
 * Created by Goo on 2016-10-26.
 */

public class MainPresenter extends BasePresenter<MainVInterface> {
    private MainMInterface mModel;

    public MainPresenter(MainVInterface viewInterface) {
        super(viewInterface);
        mModel = new MainModel();
    }

    /**
     * 获取已配置的RVAdapter
     *
     * @param context
     * @return
     */
    public RVMainAdapter getRVAdapter(final Context context) {
        RVMainAdapter adapter = new RVMainAdapter(context, mModel.getAMData());
        adapter.setOnRvItemClickListener(new RVMainAdapter.OnRvItemClickListener() {
            @Override
            public void onItemClick(View v, ArithmeticInfo arithmeticInfo) {
                view.startActivityWithAnim(arithmeticInfo.getName());
            }
        });
        return adapter;
    }

    /**
     * 触摸动画
     *
     * @param adapter
     * @return
     */
    public ItemTouchHelper getItemTouchHelper(final RVMainAdapter adapter) {
        return new ItemTouchHelper(new ItemTouchHelper.Callback() {

            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                return makeMovementFlags(ItemTouchHelper.UP |
                        ItemTouchHelper.DOWN, 0);
            }

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                int fromPosition = viewHolder.getAdapterPosition();
                int toPosition = target.getAdapterPosition();
                adapter.swapData(fromPosition, toPosition);
                return true;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
            }

            @Override
            public boolean isLongPressDragEnabled() {
                return true;
            }

        });
    }


}
