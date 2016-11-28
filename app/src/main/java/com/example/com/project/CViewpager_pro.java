package com.example.com.project;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

public class CViewpager_pro extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */

    static class CItem
    {
        String name = null;
        String ex = null;
        String url = null;
    }

    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    Toolbar toolbar;
    NavigationView navigationView;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewpager);

        Intent intent = getIntent();

        textView = (TextView)findViewById(R.id.tlbr_title);

        /*Log.d("ITPANGPANG",""+intent.getExtras().getString("1"));*/

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

/*        SetNav();*/
    }

    private void SetNav()
    {
        /**/
        toolbar = (Toolbar)findViewById(R.id.toolbar);


        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);

        DrawerLayout drawer = (DrawerLayout)findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        /*navigationView.setNavigationItemSelectedListener(onNavigationItemSelected());*/
        navigationView.setNavigationItemSelectedListener(this);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        String nav_char = "";
        IntentFilter filter = new IntentFilter();

        if(id == R.id.nav_listview_pro)
        {
            Intent intent = new Intent(CViewpager_pro.this, CViewpager_pro.class);
            nav_char = "정보처리";
            intent.putExtra("name",nav_char);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.listview, container, false);

            final CListViewAdapter adapter;

            ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getContext())
                    .threadPriority(Thread.NORM_PRIORITY - 2)
                    .denyCacheImageMultipleSizesInMemory()
                    .discCacheFileNameGenerator(new Md5FileNameGenerator())
                    .tasksProcessingOrder(QueueProcessingType.LIFO)
                    .writeDebugLogs() // Remove for release app
                    .build();

            ImageLoader.getInstance().init(config);


            final ScrollView sv_scrollview = (ScrollView)rootView.findViewById(R.id.scrollview);
            final Button btn_more = (Button)rootView.findViewById(R.id.btn_more);

            ImageView imageView = (ImageView)rootView.findViewById(R.id.imageView1);

            // Adapter 생성
            adapter = new CListViewAdapter() ;

            ListView listview = (ListView) rootView.findViewById(R.id.listview);

            listview.setAdapter(adapter);

            switch (getArguments().getInt(ARG_SECTION_NUMBER))
            {
                case 1:
                    // 첫 번째 아이템 추가.00
                    adapter.addItem("처리", "설명","STL2X74DGNA") ;
                    adapter.addItem("이름", "설명","pDGcglPDs-w") ;
                    adapter.addItem("이름", "설명","PTP5AdtWg_U") ;
                    adapter.addItem("이름", "설명","9xJRjo9VZUg") ;
                    adapter.addItem("이름", "설명","PJlBVzkJj78") ;
                    adapter.addItem("이름", "설명","PJlBVzkJj78") ;
                    break;
                case 2:
                    break;
            }

            listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView parent, View v, int position, long id) {
                // get item
                CListViewItem item = (CListViewItem) parent.getItemAtPosition(position) ;

/*                String titleStr = item.getTitle() ;
                String descStr = item.getDesc() ;
                Drawable iconDrawable = item.getIcon() ;*/

                // TODO : use item data.

                    Intent intent = new Intent(getContext(), CYoutubePlayer.class);
                    intent.putExtra("url", item.getUrl());
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

            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "필기";
                case 1:
                    return "실기";
            }
            return null;
        }
    }
}
