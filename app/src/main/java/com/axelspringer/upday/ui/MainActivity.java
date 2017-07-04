package com.axelspringer.upday.ui;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ButtonBarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;

import com.axelspringer.upday.R;
import com.axelspringer.upday.model.ArticleDTO;
import com.axelspringer.upday.services.ApiService;
import com.axelspringer.upday.ui.adapters.MainRecyclerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();
    // Rx
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    // Views
    @BindView(R.id.toolbar)
    protected Toolbar toolbar;
    @BindView(R.id.main_recycler_view)
    protected RecyclerView mRecyclerView;
    @BindView(R.id.fab)
    protected FloatingActionButton mFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mFab.setOnClickListener(view -> Snackbar.make(view, "Add an article", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.compositeDisposable.add(
        ApiService.getArticles().subscribe(
                articles -> {
                    Log.v(MainActivity.TAG, articles.toString());
                    mRecyclerView.setAdapter(new MainRecyclerAdapter(articles));
                },
                throwable -> Log.e(MainActivity.TAG, throwable.toString())));
    }
}
