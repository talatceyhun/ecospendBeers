package com.e.ecospendbeerlist.Interfaces;

import android.view.View;

public interface ICustomItemClickListener {// recycler view dahili olarak onClick metodu içermediği için
    // bu inteface'i tanımladım ve bu sayede recyclerView'a onClick verebildik.
    void onItemClick(View v, int position); // metodu tanımladım.

}
