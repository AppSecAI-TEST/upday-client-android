package com.axelspringer.upday.ui.list;

import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.axelspringer.upday.R;
import com.axelspringer.upday.UpdayApplication;
import com.axelspringer.upday.infrastructure.di.factories.UpdayFactory;
import com.axelspringer.upday.ui.add.NewArticleActivity;
import com.axelspringer.upday.ui.list.adapters.ItemTouchHelperAdapter;
import com.axelspringer.upday.ui.list.adapters.MainRecyclerAdapter;
import com.axelspringer.upday.ui.list.adapters.SimpleItemTouchHelperCallback;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements LifecycleRegistryOwner {

    public static final String TAG = MainActivity.class.getSimpleName();

    // Android - Arch. components
    private LifecycleRegistry lifecycleRegistry = new LifecycleRegistry(this);
    // Views
    @BindView(R.id.toolbar)
    protected Toolbar toolbar;
    @BindView(R.id.main_recycler_view)
    protected RecyclerView mRecyclerView;
    @BindView(R.id.fab)
    protected FloatingActionButton mFab;
    // Data
    private ArticleListViewModel articleListViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        this.mFab.setOnClickListener(view -> {
            final Intent intent = new Intent(this, NewArticleActivity.class);
            startActivity(intent);
        });
        UpdayApplication application = (UpdayApplication) getApplication();
        this.articleListViewModel = ViewModelProviders.of(this, new UpdayFactory(application)).get(ArticleListViewModel.class);
    }

    @Override
    protected void onStart() {
        super.onStart();
        this.articleListViewModel.getArticles().observe(this, articles -> {
            Log.d(TAG, "Articles Changed:" + articles);
            final MainRecyclerAdapter adapter = new MainRecyclerAdapter(articles);
            this.setupDragAndSwipe(adapter);
            mRecyclerView.setAdapter(new MainRecyclerAdapter(articles));
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_refresh) {
            this.articleListViewModel.queryAllArticles();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.articleListViewModel.queryAllArticles();
    }


    private void setupDragAndSwipe(ItemTouchHelperAdapter itemTouchHelperAdapter) {
        final SimpleItemTouchHelperCallback itemTouchHelperCallback = new SimpleItemTouchHelperCallback(itemTouchHelperAdapter);
        final ItemTouchHelper itemTouchHelper = new ItemTouchHelper(itemTouchHelperCallback);
        itemTouchHelper.attachToRecyclerView(mRecyclerView);
    }

    @Override
    public LifecycleRegistry getLifecycle() {
        return this.lifecycleRegistry;
    }

}
