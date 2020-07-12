package com.e.ecospendbeerlist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.e.ecospendbeerlist.Interfaces.VolleyCallBack;
import com.e.ecospendbeerlist.Models.Beer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class BeerDetailActivity extends AppCompatActivity {

    ImageView beerDetailImageView;
    TextView description;
    TextView brewers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beer_detail);
        Intent intent = getIntent();

        //project attachmentında da belirtildiği gibi biranın adını Action Bar'a yazdım.
        getSupportActionBar().setTitle(intent.getStringExtra("name"));

        beerDetailImageView = findViewById(R.id.beerDetailImage);
        description = findViewById(R.id.beerDescription);
        brewers = findViewById(R.id.beerBrewes);
        String imageUrl = intent.getStringExtra("imageURL");
        String descriptionString = intent.getStringExtra("description");
        String brewersTips = intent.getStringExtra("brewersTips");

        Glide.with(getApplicationContext()).clear(beerDetailImageView);
        //noinspection deprecation
        RequestOptions requestOptions = new RequestOptions();
        //noinspection deprecation
        requestOptions = requestOptions.transforms(new CenterCrop(), new RoundedCorners(30));
        Glide.with(getApplicationContext())
                .load(imageUrl)
                .into(beerDetailImageView);

        description.setText("Description: " + descriptionString);
        brewers.setText("Brewers Tips: " + brewersTips);


    }


}