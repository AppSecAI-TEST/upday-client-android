package com.axelspringer.upday.ui.list.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.axelspringer.upday.R;
import com.axelspringer.upday.UpdayApplication;
import com.axelspringer.upday.model.ArticleDTO;
import com.axelspringer.upday.services.network.api.UpdayApi;
import com.axelspringer.upday.ui.list.viewholders.ArticleViewHolder;

import java.util.Collections;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by damien on 7/4/17.
 */

public class MainRecyclerAdapter extends RecyclerView.Adapter<ArticleViewHolder> implements ItemTouchHelperAdapter {

    public static final String TAG = MainRecyclerAdapter.class.getSimpleName();

    private List<ArticleDTO> mData;

    public MainRecyclerAdapter(List<ArticleDTO> articles) {
        this.mData =  articles;
    }

    @Override
    public ArticleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        final View view = inflater.inflate(R.layout.article_layout_item, parent, false);

        return new ArticleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ArticleViewHolder holder, int position) {
        holder.bindData(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                if (i >= mData.size()) return;
                Collections.swap(mData, i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                if (i >= mData.size()) return;
                Collections.swap(mData, i, i - 1);
            }
        }
        notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public void onItemDismiss(int position) {
        final UpdayApi api = UpdayApplication.getClient().getRetrofit().create(UpdayApi.class);
        api.deleteArticle(mData.get(position).getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        aVoid -> {
                            mData.remove(position);
                            notifyItemRemoved(position);
                        },
                        throwable -> Log.e(TAG, throwable.toString())
                );
    }
}
