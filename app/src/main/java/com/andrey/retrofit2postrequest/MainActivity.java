package com.andrey.retrofit2postrequest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.andrey.retrofit2postrequest.data.model.Post;
import com.andrey.retrofit2postrequest.data.remote.ApiService;
import com.andrey.retrofit2postrequest.data.remote.ApiUtils;
import com.jakewharton.rxbinding2.view.RxView;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.body)
    EditText body;

    @BindView(R.id.postRequest)
    TextView postRequest;

    @BindView(R.id.sendRequest)
    Button sendRequest;

    @BindView(R.id.title)
    EditText title;

    private ApiService apiService;
    private final String TAG = "mLog";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        apiService = ApiUtils.getApiService();

        RxView.clicks(sendRequest)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        if(!title.getText().toString().isEmpty() && !body.getText().toString().isEmpty())
                            sendPost();
                    }
                });
    }

    public void sendPost()
    {
        apiService.savePost(title.getText().toString(), body.getText().toString(), 666)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Post>() {
                    @Override
                    public void accept(Post post) throws Exception {
                        postRequest.setText(post.showRequest());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }
}
