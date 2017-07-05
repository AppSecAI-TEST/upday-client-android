package com.axelspringer.upday.ui.list.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.axelspringer.upday.R;
import com.axelspringer.upday.model.ArticleDTO;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by damien on 7/4/17.
 */

public class ArticleViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.article_item_title)
    protected TextView title;
    @BindView(R.id.article_item_description)
    protected TextView shortDescription;
    @BindView(R.id.article_item_text)
    protected TextView text;
    @BindView(R.id.article_item_date)
    protected TextView date;


    public ArticleViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bindData(ArticleDTO data) {
        this.title.setText(data.getHeader());
        this.shortDescription.setText(data.getDescription());
        this.text.setText(data.getText());
        if (data.getPublicationDate() != null) {
            this.date.setText(data.getPublicationDate().toString());
        }
    }
}
