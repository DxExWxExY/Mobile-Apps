package dxexwxexy.pricefinder.Activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.LinkedList;

import dxexwxexy.pricefinder.Data.Item;
import dxexwxexy.pricefinder.R;

public class ItemsView extends AppCompatActivity {

    private LinkedList<Item> items;
    private SwipeRefreshLayout refreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items_view);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initItems();
        initRecyclerView();
        initUI();
    }

    private void initItems() {
        items = new LinkedList<>();
        items.add(new Item("GTX 1080 Ti", "https://images.nvidia.com/" +
                "geforce-com/international/images/nvidia-geforce-gtx-1080-ti/" +
                "GeForce_GTX_1080ti_3qtr_top_left.png",499.99));
    }

    /**
     * Initializes the RecyclerViewer for the items of operations.
     */
    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.items_list);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(items, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initUI() {
        FloatingActionButton addItem = findViewById(R.id.add_fab);
        addItem.setOnClickListener(view -> {
            Snackbar.make(view, "Cannot Add Items... Yet", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        });
        refreshLayout = findViewById(R.id.menu_refresh);
        refreshLayout.setOnRefreshListener(() -> {
           //
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_items_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_refresh:
                refreshLayout.setRefreshing(true);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        // TODO: 7/16/2018 save instance
    }

    /**
     * Class required to use a RecyclerViewer.
     *
     */
    public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        /**
         * Fields used by the RecyclerViewer.
         */
        private LinkedList<Item> items;
        private Context mContext;

        RecyclerViewAdapter(LinkedList<Item> items, Context mContext) {
            this.items = items;
            this.mContext = mContext;
        }

        /**
         * {@inheritDoc}
         * @param parent
         * @param viewType
         * @return
         */
        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_container, parent, false);
            return new ViewHolder(view);
        }

        /**
         * {@inheritDoc}
         * @param holder
         * @param position
         */
        @SuppressLint("NewApi")
        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            ViewHolder content = (ViewHolder) holder;
            content.name.setText(items.get(position).getName());
            content.initialPrice.setText(items.get(position).getInitialPrice());
            content.currentPrice.setText(items.get(position).getCurrentPrice());
            content.difference.setText(items.get(position).getDifference());
            Picasso.get().load(items.get(position).getURL()).into(content.itemIcon);
            content.refresh.setOnClickListener(e -> {
                items.get(position).updateCurrentPrice();
                content.currentPrice.setText(items.get(position).getCurrentPrice());
                content.difference.setText(items.get(position).getDifference());
                if (Integer.parseInt(items.get(position).getDifference()) <= 0) {
                    content.difference.setTextColor(getColor(R.color.green));
                } else if (Integer.parseInt(items.get(position).getDifference()) <= 20) {
                    content.difference.setTextColor(getColor(R.color.yellow));
                } else if (Integer.parseInt(items.get(position).getDifference()) <= 40) {
                    content.difference.setTextColor(getColor(R.color.orange));
                } else  {
                    content.difference.setTextColor(getColor(R.color.red));
                }
            });
        }

        /**
         * {@inheritDoc}
         * @return
         */
        @Override
        public int getItemCount() {
            return items == null ? 0 : items.size();
        }

        /**
         * Instance used by RecyclerViewer.
         */
        class ViewHolder extends RecyclerView.ViewHolder {

            /**
             * Fields used by the item_container layout.
             */
            TextView name, initialPrice, currentPrice, difference;
            ImageView itemIcon;
            ImageButton refresh;
            ConstraintLayout parentLayout;

            ViewHolder(View itemView) {
                super(itemView);
                name = itemView.findViewById(R.id.item_name);
                initialPrice = itemView.findViewById(R.id.initial_price);
                currentPrice = itemView.findViewById(R.id.current_price);
                difference = itemView.findViewById(R.id.difference);
                itemIcon = itemView.findViewById(R.id.item_icon);
                refresh = itemView.findViewById(R.id.refresh_item);
                parentLayout = itemView.findViewById(R.id.item_holder);
            }
        }
    }
}
