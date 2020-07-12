package com.e.ecospendbeerlist.Adapters;

import android.content.Context;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.e.ecospendbeerlist.Interfaces.ICustomItemClickListener;
import com.e.ecospendbeerlist.Models.Beer;
import com.e.ecospendbeerlist.R;

import java.util.List;

import static android.content.ContentValues.TAG;

public class BeerRecyclerAdapter extends RecyclerView.Adapter<BeerRecyclerAdapter.Define> {

    Context context;
    List<Beer> list; // adapte edilecek liste.
    ICustomItemClickListener customItemClickListener;  // Listener nesnesi. Bu nesne sayesinde itemler tıklanabilir oluyor.
    public static int sizeAdder = 10; // ilk açılışta recyclerView'da gösterilecek eleman sayısı.

    // Listener nesnesi.

    public BeerRecyclerAdapter(Context context, List<Beer> list, ICustomItemClickListener customItemClickListener) {
        this.context = context;
        this.list = list;
        this.customItemClickListener = customItemClickListener;

    }


    @NonNull
    @Override
    public Define onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate((R.layout.beer_list_recycler), parent, false);
        final Define mViewHolder = new Define(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // tıklanan rectclerView itemi için clickListener oluşturdum.
                customItemClickListener.onItemClick(v, mViewHolder.getLayoutPosition());
            }
        });
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Define holder, int position) {
        try {
            final Beer item = list.get(position);

            /*
            Uygulamada url den fotoğrafları göstermek için glide'ı tercih ettim. Tercih etme sebeplerim
            daha önce kullanmam, kullanımının basit olması ve performans açısından verimli bir kütüphane olmasıdır.
             */
            Glide.with(context).clear(holder.beersRecyclerImage);
            //noinspection deprecation
            RequestOptions requestOptions = new RequestOptions();
            //noinspection deprecation
            requestOptions = requestOptions.transforms(new CenterCrop(), new RoundedCorners(30));
            Glide.with(context)
                    .load(item.getImageURL())
                    .into(holder.beersRecyclerImage);
            String title = "<b>Title:</b> " + item.getTitle();
            String tagline = "<b>Tagline:</b> " + item.getTagLine();
            // Etiket isimleri bold yazıldı.

            holder.beersRecyclerTitleText.setText(Html.fromHtml(title));
            holder.beersRecyclerTagLine.setText(Html.fromHtml(tagline));


        } catch (IndexOutOfBoundsException e) {
            Log.e(TAG, "IndexOutOfBoundsException: " + e.getMessage());

        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class Define extends RecyclerView.ViewHolder {
        RecyclerView beersRecycler;
        ImageView beersRecyclerImage; // View itemlerini tanımladım.
        TextView beersRecyclerTitleText;
        TextView beersRecyclerTagLine;


        public Define(@NonNull View itemView) {
            super(itemView);
            beersRecycler = itemView.findViewById(R.id.beersRecycler);
            beersRecyclerTitleText = itemView.findViewById(R.id.beersRecyclerTitleText);
            beersRecyclerTagLine = itemView.findViewById(R.id.beersRecyclerTagLine);
            beersRecyclerImage = itemView.findViewById(R.id.beersRecyclerImage);
            // Tanımlanan itemleri layoutla eşledim.

        }
    }


}
