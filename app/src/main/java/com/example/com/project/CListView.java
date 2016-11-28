package com.example.com.project;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ScrollView;

/**
 * Created by com on 2016-11-16.
 */

public class CListView extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview);

        ListView listview ;
        final CListViewAdapter adapter;

        // Adapter 생성
        adapter = new CListViewAdapter() ;

        //
        final ScrollView sv_scrollview = (ScrollView)findViewById(R.id.scrollview);
        final Button btn_more = (Button)findViewById(R.id.btn_more);

        // 리스트뷰 참조 및 Adapter달기
        listview = (ListView) findViewById(R.id.listview);
        listview.setAdapter(adapter);


        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                /*// get item
                CListViewItem item = (CListViewItem) parent.getItemAtPosition(position) ;

                String titleStr = item.getTitle() ;
                String descStr = item.getDesc() ;
                Drawable iconDrawable = item.getIcon() ;

                // TODO : use item data.*/

                Intent intent = new Intent(getApplicationContext(), CYoutubePlayer.class);
                startActivity(intent);
            }
        }) ;

        listview.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            if((firstVisibleItem + visibleItemCount) == totalItemCount)
            {
                btn_more.setVisibility(View.VISIBLE);
            }
                else
            {
                btn_more.setVisibility(View.INVISIBLE);
            }
            }
        });
    }
}
