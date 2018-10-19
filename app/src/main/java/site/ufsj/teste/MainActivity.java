package site.ufsj.teste;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View v){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url("https://www.101apps.co.za/images/headers/101_logo_very_small.jpg").get().build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

                Log.i("Tag","error"+e.getMessage());
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        ImageView imageView = (ImageView)findViewById(R.id.imageView);

                        ResponseBody in = response.body();
                        InputStream inputStream = in.byteStream();
                        // convert inputstram to bufferinoutstream
                        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
                        Bitmap bitmap= BitmapFactory.decodeStream(bufferedInputStream);
                        imageView.setImageBitmap(bitmap);

                    }
                });

            }
        });
    }

}
