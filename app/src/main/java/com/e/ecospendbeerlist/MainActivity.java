package com.e.ecospendbeerlist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.e.ecospendbeerlist.Adapters.BeerRecyclerAdapter;
import com.e.ecospendbeerlist.Interfaces.ICustomItemClickListener;
import com.e.ecospendbeerlist.Interfaces.VolleyCallBack;
import com.e.ecospendbeerlist.Models.Beer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Beer> beerList = new ArrayList<>();
    private RecyclerView beersRecyclerView;
    private BeerRecyclerAdapter beerRecyclerAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Beers");
        beersRecyclerView = findViewById(R.id.beersRecycler);
        layoutManager = new LinearLayoutManager(this);
        beersRecyclerView.setLayoutManager(layoutManager);

        beersGet(new VolleyCallBack() {
            @Override
            public void onSuccess() {
                beerRecyclerAdapter = new BeerRecyclerAdapter(getApplicationContext(), beerList, new ICustomItemClickListener() {
                    @Override
                    public void onItemClick(View v, int position) {
                        Intent intent = new Intent(getApplicationContext(),BeerDetailActivity.class);
                        Beer tmpBeer = beerList.get(position);
                        intent.putExtra("imageURL",tmpBeer.getImageURL());
                        intent.putExtra("description",tmpBeer.getDescription());
                        intent.putExtra("brewersTips",tmpBeer.getBrewersTips());
                        intent.putExtra("name",tmpBeer.getTitle());
                        startActivity(intent);
                        // başlangıçta putExtra ile id'yi yollayıp detail aktivitesinde tekrar get isteği yapmayı düşündüm
                        // ama iki get isteğinden gelen responseları karşılaştırdığımda id ile yapılacak get isteğinin gereksiz olduğunu gördüm. İlk sorguya göre
                        // gelen ekstra bir bilgi yok o yüzden sadece bu aktivitede http Get isteği yaptım.


                    }
                });
                beersRecyclerView.setAdapter(beerRecyclerAdapter);
            }
        });

    }

    /*
    Kullanımının basit olması, google'ın bir kütüphanesi olması ve İstenilen API'da zorlu json parse işlemlerinin olmamasından
    dolayı volley kütüphanesini seçtim ve kullandım.
    Gelen JSON verisi oldukça basit ve parse etmesi kolay. Daha karmaşık bir yapı olsaydı volley yerine
    retrofit de kullanabilirdim.
     */

    public  void beersGet(final VolleyCallBack callBack){
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://api.punkapi.com/v2/beers/";

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //JSONObject beersObject = new JSONObject(response);
                            JSONArray beersArray = new JSONArray(response);
                            for(int i = 0; i<beersArray.length();i++){
                                Beer beerTmp = new Beer();
                                JSONObject beerJsonTmp = beersArray.getJSONObject(i);
                                beerTmp.setTitle(beerJsonTmp.getString("name"));
                                beerTmp.setTagLine(beerJsonTmp.getString("tagline"));
                                beerTmp.setImageURL(beerJsonTmp.getString("image_url"));
                                beerTmp.setDescription(beerJsonTmp.getString("description"));
                                beerTmp.setBrewersTips(beerJsonTmp.getString("brewers_tips"));

                                beerList.add(beerTmp);


                            }
                            callBack.onSuccess();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("BeerApiCallError","Error");
            }
        });

// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
}