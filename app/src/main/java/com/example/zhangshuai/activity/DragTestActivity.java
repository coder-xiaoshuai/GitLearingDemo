package com.example.zhangshuai.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zhangshuai.adapter.TabPagerAdapter;
import com.example.zhangshuai.fragment.TabFragment;
import com.example.zhangshuai.gitlearingdemo.R;
import com.example.zhangshuai.interfaces.StartDragListener;
import com.example.zhangshuai.views.ForbidScrollViewPager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

public class DragTestActivity extends AppCompatActivity implements StartDragListener {
    private RecyclerView recyclerView;
    private ForbidScrollViewPager mViewPager;
    private TabPagerAdapter tabPagerAdapter;
    private List<String> tabTitles;
    private List<Fragment> fragments;
    private String[] tabTitlesArray = new String[] { "推荐", "校园", "摄影", "二次元", "直播", "搞笑", "宠物", "收藏" };
    private ItemTouchHelper itemTouchHelper;
    private TabAdapter tabAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drag_test_rv);
        recyclerView = findViewById(R.id.tab_layout);
        recyclerView.setItemAnimator(null);
        mViewPager = findViewById(R.id.viewpager);
        mViewPager.setOffscreenPageLimit(5);
        tabTitles = Arrays.asList(tabTitlesArray);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        tabAdapter = new TabAdapter(this, tabTitles, mViewPager);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(tabAdapter);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
        tabAdapter.setItemTouchHelper(itemTouchHelper);

        initViewPager();
    }

    private void initViewPager() {
        fragments = new ArrayList<>();
        for (int i = 0; i < tabTitles.size(); i++) {
            TabFragment tabFragment = new TabFragment();
            Bundle bundle = new Bundle();
            bundle.putString(TabFragment.ARGUMENTS_KEY_TAB_TITLE, tabTitles.get(i));
            tabFragment.setArguments(bundle);
            fragments.add(tabFragment);
        }
        tabPagerAdapter = new TabPagerAdapter(getSupportFragmentManager(), fragments);
        mViewPager.setAdapter(tabPagerAdapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    static class TabAdapter extends RecyclerView.Adapter<TabViewHolder> {
        private List<String> tabTitles;
        private Context context;
        private StartDragListener startDragListener;
        private ViewPager viewPager;

        public void setItemTouchHelper(ItemTouchHelper itemTouchHelper) {
            this.itemTouchHelper = itemTouchHelper;
        }

        private ItemTouchHelper itemTouchHelper;

        public void setStartDragListener(StartDragListener startDragListener) {
            this.startDragListener = startDragListener;
        }

        public TabAdapter(Context context, List<String> tabTitles) {
            this.tabTitles = tabTitles;
            this.context = context;
        }

        public TabAdapter(Context context, List<String> tabTitles, ViewPager viewPager) {
            this.tabTitles = tabTitles;
            this.context = context;
            this.viewPager = viewPager;
        }

        @NonNull
        @Override
        public TabViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new TabViewHolder(LayoutInflater.from(context).inflate(R.layout.item_tab, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull TabViewHolder holder, int position) {
            Log.e("zs","onBindViewHolder"+position);
            holder.tabTitle.setBackgroundColor(position % 2 == 0 ? Color.YELLOW : Color.BLUE);
            holder.tabTitle.setText(tabTitles.get(position));
            //holder.itemView.setOnClickListener(v -> viewPager.setCurrentItem(position));
            //holder.itemView.setOnTouchListener(new View.OnTouchListener() {
            //    @Override
            //    public boolean onTouch(View v, MotionEvent event) {
            //        if (position < 1) {
            //            return true;
            //        }
            //        return false;
            //    }
            //});

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tabTitles.set(position, "修改item" + (position + 1));
                    notifyDataSetChanged();
                }
            });
        }

        @Override
        public int getItemCount() {
            return tabTitles.size();
        }
    }

    static class TabViewHolder extends RecyclerView.ViewHolder {
        ImageView tabIcon;
        TextView tabTitle;

        public TabViewHolder(@NonNull View itemView) {
            super(itemView);
            tabTitle = itemView.findViewById(R.id.tab_title);
        }
    }

    //itemHelper的回调
    ItemTouchHelper.Callback callback = new ItemTouchHelper.Callback() {

        /**
         * 官方文档的说明如下：
         * o control which actions user can take on each view, you should override getMovementFlags(RecyclerView, ViewHolder)
         * and return appropriate set of direction flags. (LEFT, RIGHT, START, END, UP, DOWN).
         * 返回我们要监控的方向，上下左右，我们做的是上下拖动，要返回都是UP和DOWN
         * 关键坑爹的是下面方法返回值只有1个，也就是说只能监控一个方向。
         * 不过点入到源码里面有惊喜。源码标记方向如下：
         *  public static final int UP = 1     0001
         *  public static final int DOWN = 1 << 1; （位运算：值其实就是2）0010
         *  public static final int LEFT = 1 << 2   左 值是3
         *  public static final int RIGHT = 1 << 3  右 值是8
         */
        @Override
        public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
            Log.e("zs", "getMovementFlags");
            int dragFlags = 0;
            int swipeFlags = 0;
            if (layoutManager instanceof GridLayoutManager) {
                // 如果是Grid布局，则不能滑动，只能上下左右拖动
                dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
                swipeFlags = 0;
            } else if (layoutManager instanceof LinearLayoutManager) {
                // 如果是纵向Linear布局，则能上下拖动，左右滑动
                if (((LinearLayoutManager) layoutManager).getOrientation() == RecyclerView.VERTICAL) {
                    dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
                    swipeFlags = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
                } else {
                    // 如果是横向Linear布局，则能左右拖动，上下滑动
                    swipeFlags = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
                    dragFlags = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
                }
            }
            return makeMovementFlags(dragFlags, swipeFlags); //该方法指定可进行的操作

            /**
             * 备注：由getMovementFlags可以联想到setMovementFlags，不过文档么有这个方法，但是：
             * 有 makeMovementFlags (int dragFlags, int swipeFlags)
             * Convenience method to create movement flags.便捷方法创建moveMentFlag
             * For instance, if you want to let your items be drag & dropped vertically and swiped left to be dismissed,
             * you can call this method with: makeMovementFlags(UP | DOWN, LEFT);
             * 这个recyclerview的文档写的简直完美，示例代码都弄好了！！！
             * 如果你想让item上下拖动和左边滑动删除，应该这样用： makeMovementFlags(UP | DOWN, LEFT)
             */

            //拓展一下：如果只想上下的话：makeMovementFlags（UP | DOWN, 0）,标记方向的最小值1
        }

        /**
         * 官方文档的说明如下
         * If user drags an item, ItemTouchHelper will call onMove(recyclerView, dragged, target). Upon receiving this callback,
         * you should move the item from the old position (dragged.getAdapterPosition()) to new position (target.getAdapterPosition())
         * in your adapter and also call notifyItemMoved(int, int).
         * 拖动某个item的回调，在return前要更新item位置，调用notifyItemMoved（draggedPosition，targetPosition）
         * viewHolde:正在拖动item
         * target：要拖到的目标
         * @return true 表示消费事件
         */
        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
                              RecyclerView.ViewHolder target) {
            Log.e("zs", "onMove");
            //得到当拖拽的viewHolder的Position
            int fromPosition = viewHolder.getAdapterPosition();

            //拿到当前拖拽到的item的viewHolder
            int toPosition = target.getAdapterPosition();
            if (fromPosition < 2 && toPosition == 0) {
                return false;
            }
            if (toPosition < 2) {
                return false;
            }
            if (fromPosition < toPosition) {
                for (int i = fromPosition; i < toPosition; i++) {
                    Collections.swap(tabTitles, i, i + 1);
                    Collections.swap(fragments, i, i + 1);
                }
            } else {
                for (int i = fromPosition; i > toPosition; i--) {
                    Collections.swap(tabTitles, i, i - 1);
                    Collections.swap(fragments, i, i - 1);
                }
            }
            tabAdapter.notifyItemMoved(fromPosition, toPosition);

            return true;
        }

        /**
         * 谷歌官方文档说明如下：
         * 这个看了一下主要是做左右拖动的回调
         * When a View is swiped, ItemTouchHelper animates it until it goes out of bounds, then calls onSwiped(ViewHolder, int).
         * At this point, you should update your adapter (e.g. remove the item) and call related Adapter#notify event.
         * @param viewHolder
         * @param direction
         */
        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
            //暂不处理
            Log.e("zs", "onSwiped");
        }

        @Override
        public void onSelectedChanged(@Nullable RecyclerView.ViewHolder viewHolder, int actionState) {
            Log.e("zs", "onSelectedChanged");
            if (viewHolder.getAdapterPosition() < 2) {
                return;
            }
            super.onSelectedChanged(viewHolder, actionState);
            viewHolder.itemView.findViewById(R.id.tab_title).setScaleX(1.5f);
            viewHolder.itemView.findViewById(R.id.tab_title).setScaleY(1.5f);
        }

        @Override
        public void clearView(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
            super.clearView(recyclerView, viewHolder);
            viewHolder.itemView.findViewById(R.id.tab_title).setScaleX(1.0f);
            viewHolder.itemView.findViewById(R.id.tab_title).setScaleY(1.0f);
            Log.e("zs", "clearView  position" + viewHolder.getAdapterPosition());
            tabPagerAdapter.notifyDataSetChanged();
            mViewPager.setCurrentItem(viewHolder.getLayoutPosition());
        }

        /**
         * 官方文档如下：返回true 当前tiem可以被拖动到目标位置后，直接”落“在target上，其他的上面的tiem跟着“落”，
         * 所以要重写这个方法，不然只是拖动的tiem在动，target tiem不动，静止的
         * Return true if the current ViewHolder can be dropped over the the target ViewHolder.
         * @param recyclerView
         * @param current
         * @param target
         * @return
         */
        @Override
        public boolean canDropOver(RecyclerView recyclerView, RecyclerView.ViewHolder current,
                                   RecyclerView.ViewHolder target) {
            //拿到当前拖拽到的item的viewHolder
            int toPosition = target.getAdapterPosition();
            //if (toPosition < 2) {
            //    return false;
            //}

            return true;
        }

        /**
         * 官方文档说明如下：
         * Returns whether ItemTouchHelper should start a drag and drop operation if an item is long pressed.
         * 是否开启长按 拖动
         * @return
         */
        @Override
        public boolean isLongPressDragEnabled() {
            Log.e("zs", "isLongPressDragEnabled");
            //return true后，可以实现长按拖动排序和拖动动画了
            return true;
        }

        @Override
        public boolean isItemViewSwipeEnabled() {
            Log.e("zs", "isItemViewSwipeEnabled");
            return super.isItemViewSwipeEnabled();
        }
    };

    @Override
    public void startDragItem(RecyclerView.ViewHolder holder) {
        //谷歌官方文档如下：
        //开启拖动我们给他的holder，但是默认
        // 是长按拖动，直接代码调用拖动要先禁止长按拖动
        //Starts dragging the provided ViewHolder. By default,
        // ItemTouchHelper starts a drag when a View is long pressed.
        // You can disable that behavior by overriding isLongPressDragEnabled().
        itemTouchHelper.startDrag(holder);
    }

    public abstract class OnRecyclerItemClickListener implements RecyclerView.OnItemTouchListener {
        private GestureDetectorCompat mGestureDetectorCompat;
        private RecyclerView mRecyclerView;

        public OnRecyclerItemClickListener(RecyclerView recyclerView) {
            mRecyclerView = recyclerView;
            mGestureDetectorCompat =
                new GestureDetectorCompat(mRecyclerView.getContext(), new ItemTouchHelperGestureListener());
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            mGestureDetectorCompat.onTouchEvent(e);
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
            mGestureDetectorCompat.onTouchEvent(e);
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }

        public abstract void onItemClick(RecyclerView.ViewHolder viewHolder);

        public abstract void onLongClick(RecyclerView.ViewHolder viewHolder);

        private class ItemTouchHelperGestureListener extends GestureDetector.SimpleOnGestureListener {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                View childViewUnder = mRecyclerView.findChildViewUnder(e.getX(), e.getY());
                if (childViewUnder != null) {
                    RecyclerView.ViewHolder childViewHolder = mRecyclerView.getChildViewHolder(childViewUnder);
                    onItemClick(childViewHolder);
                }
                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                View childViewUnder = mRecyclerView.findChildViewUnder(e.getX(), e.getY());
                if (childViewUnder != null) {
                    RecyclerView.ViewHolder childViewHolder = mRecyclerView.getChildViewHolder(childViewUnder);
                    onLongClick(childViewHolder);
                }
            }
        }
    }
}
