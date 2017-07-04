package com.axelspringer.upday.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.axelspringer.upday.R;
import com.axelspringer.upday.UpdayApplication;
import com.axelspringer.upday.model.ArticleDTO;
import com.axelspringer.upday.services.network.api.UpdayApi;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * A login screen that offers login via email/password.
 */
public class NewArticleActivity extends AppCompatActivity {

    // Tag
    public static final String TAG = NewArticleActivity.class.getSimpleName();
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    // UI references.
    @BindView(R.id.new_article_title) protected EditText mTitleEditText;
    @BindView(R.id.new_article_description) protected EditText mDescriptionEditText;
    @BindView(R.id.new_article_text) protected EditText mTextEditText;
    @BindView(R.id.new_article_add_button) protected Button mAddNewArticle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_article);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.new_article_add_button)
    public void onClickAddButton(View view) {
        // TODO: check form before sending a new object
        final UpdayApi api = UpdayApplication.getClient().getRetrofit().create(UpdayApi.class);
        final ArticleDTO dto = new ArticleDTO();
        dto.setHeader(mTitleEditText.getText().toString());
        dto.setDescription(mDescriptionEditText.getText().toString());
        dto.setText(mTextEditText.getText().toString());
//        dto.setPublicationDate(new Date());  //TODO: manage date format
        compositeDisposable.add(
                api.createArticle(dto)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                response -> finish(),
                                throwable -> Log.e(TAG, throwable.toString())
                        ));
    }


}