package id.sch.smktelkom_mlg.privateassignment.xirpl434.movietimes;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetilActivity extends AppCompatActivity {

    public String url = "https://api.nytimes.com/svc/movies/v2/reviews/search.json?api-key=96d37398f7b846979bcb91e5f84dbc5c";
    String image, judul, oleh, gambar, detildeskripsi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detil);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent intent = getIntent();
        image = intent.getStringExtra("image");
        judul = intent.getStringExtra("judul");
        oleh = intent.getStringExtra("oleh");

        setTitle(judul);

        gambar = url + image;
        ImageView Imagedetil = (ImageView) findViewById(R.id.imagedetil);
        TextView textjudul = (TextView) findViewById(R.id.detiljudul);


        Glide.with(this).load(image)
                .crossFade()
                .centerCrop()
                .placeholder(R.drawable.ic_menu_gallery)
                .error(R.mipmap.ic_launcher)
                .into(Imagedetil);
        textjudul.setText(oleh);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}
