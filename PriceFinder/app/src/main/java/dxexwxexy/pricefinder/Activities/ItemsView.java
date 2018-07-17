package dxexwxexy.pricefinder.Activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
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

import java.util.ArrayList;
import java.util.LinkedList;

import dxexwxexy.pricefinder.Data.Item;
import dxexwxexy.pricefinder.R;

public class ItemsView extends AppCompatActivity {

    private static ArrayList<Item> items;
    private SwipeRefreshLayout refreshLayout;
    private RecyclerViewAdapter adapter;

    /***
     * {@inheritDoc}
     * @param savedInstanceState
     */
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

    /***
     * Method to simulate data read.
     */
    private void initItems() {
        items = new ArrayList<>();
        //Sample
        items.add(new Item("GTX 1080 Ti", "https://images.nvidia.com/" +
                "geforce-com/international/images/nvidia-geforce-gtx-1080-ti/" +
                "GeForce_GTX_1080ti_3qtr_top_left.png",499.99));
    }

    /**
     * Initializes the RecyclerViewer for the items of operations.
     */
    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.items_list);
        adapter = new RecyclerViewAdapter(items, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    /***
     * Initializes the UI Components.
     */
    private void initUI() {
        FloatingActionButton addItem = findViewById(R.id.add_fab);
        addItem.setOnClickListener(view -> {
            Snackbar.make(view, "Cannot Add Items... Yet", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        });
        refreshLayout = findViewById(R.id.swipe_refresh);
        refreshLayout.setOnRefreshListener(() -> {
            refreshLayout.setRefreshing(true);
            for (Item item : items) {
                item.updateCurrentPrice();
            }
            adapter.notifyDataSetChanged();
            refreshLayout.setRefreshing(false);
        });
    }

    /***
     * {@inheritDoc}
     * @param outState
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("items", items);
    }

    /***
     * {@inheritDoc}
     * @param savedInstanceState
     */
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        items = savedInstanceState.getParcelableArrayList("items");
    }

    /**
     * Class required to use a RecyclerViewer.
     *
     */
    public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        /**
         * Fields used by the RecyclerViewer.
         */
        private ArrayList<Item> items;
        private Context mContext;

        /***
         * Default Constructor
         * @param items ArrayList containing instances of Item.
         * @param mContext App context.
         */
        RecyclerViewAdapter(ArrayList<Item> items, Context mContext) {
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
            if (Integer.parseInt(items.get(position).getDifference()) <= 0) {
                content.difference.setTextColor(getColor(R.color.green));
            } else if (Integer.parseInt(items.get(position).getDifference()) <= 20) {
                content.difference.setTextColor(getColor(R.color.yellow));
            } else if (Integer.parseInt(items.get(position).getDifference()) <= 40) {
                content.difference.setTextColor(getColor(R.color.orange));
            } else  {
                content.difference.setTextColor(getColor(R.color.red));
            }
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
            ConstraintLayout parentLayout;

            /***
             * Default Constructor
             * @param itemView View Containing the holder views.
             */
            ViewHolder(View itemView) {
                super(itemView);
                name = itemView.findViewById(R.id.item_name);
                initialPrice = itemView.findViewById(R.id.initial_price);
                currentPrice = itemView.findViewById(R.id.current_price);
                difference = itemView.findViewById(R.id.difference);
                itemIcon = itemView.findViewById(R.id.item_icon);
                parentLayout = itemView.findViewById(R.id.item_holder);
            }
        }
    }
}
