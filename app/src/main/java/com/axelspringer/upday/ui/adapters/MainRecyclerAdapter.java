package com.axelspringer.upday.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.axelspringer.upday.R;
import com.axelspringer.upday.model.ArticleDTO;
import com.axelspringer.upday.ui.views.viewholders.ArticleViewHolder;

import java.util.List;

/**
 * Created by damien on 7/4/17.
 */

public class MainRecyclerAdapter extends RecyclerView.Adapter<ArticleViewHolder> {

    private List<ArticleDTO> data;

    public MainRecyclerAdapter(List<ArticleDTO> articles) {
        this.data =  articles;
    }

    @Override
    public ArticleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        final View view = inflater.inflate(R.layout.article_layout_item, parent, false);

        return new ArticleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ArticleViewHolder holder, int position) {
        holder.bindData(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
