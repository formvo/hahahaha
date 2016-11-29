package com.example.com.project.WrittenList.Client;

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

import com.example.com.project.CListViewAdapter;
import com.example.com.project.CListViewItem;
import com.example.com.project.CYoutubePlayer;
import com.example.com.project.FileManager.CFileManager;
import com.example.com.project.R;
import com.example.com.project.WrittenList.Interface.CPracticeListData;
import com.example.com.project.WrittenList.Interface.CWrittenListData;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import static com.example.com.project.R.id.container;

public class CListClient extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
{
    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */

    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    Toolbar toolbar;
    NavigationView navigationView;
    TextView textView;
    public static String jsonData;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewpager);

        Intent intent = getIntent();

        textView = (TextView)findViewById(R.id.tlbr_title);

        /*Log.d("ITPANGPANG",""+intent.getExtras().getString("1"));*/

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager)findViewById(container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout)findViewById(R.id.tabs);
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
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView)findViewById(R.id.nav_view);
        /*navigationView.setNavigationItemSelectedListener(onNavigationItemSelected());*/
        navigationView.setNavigationItemSelectedListener(this);
    }

    @SuppressWarnings( "StatementWithEmptyBody" )
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item)
    {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        String nav_char = "";
        IntentFilter filter = new IntentFilter();

        if(id == R.id.nav_listview_pro)
        {
            Intent intent = new Intent(CListClient.this, CListClient.class);
            nav_char = "정보처리";
            intent.putExtra("name", nav_char);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout)findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment
    {
        public class CItem
        {
            String m_name = null;
            String m_subtitle = null;
            String m_imgUrl1 = null;
            String m_imgUrl2 = null;
        }
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment()
        {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber)
        {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
        {
            View rootView = inflater.inflate(R.layout.listview, container, false);

            final CListViewAdapter adapter;


            ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getContext()).threadPriority(Thread.NORM_PRIORITY - 2)
                    .denyCacheImageMultipleSizesInMemory()
                    .discCacheFileNameGenerator(new Md5FileNameGenerator())
                    .tasksProcessingOrder(QueueProcessingType.LIFO)
                    .writeDebugLogs() // Remove for release app
                    .build();

            ImageLoader.getInstance().init(config);

            final ScrollView sv_scrollview = (ScrollView)rootView.findViewById(R.id.scrollview);
            final Button btn_more = (Button)rootView.findViewById(R.id.btn_more);

            ImageView imageView = (ImageView)rootView.findViewById(R.id.imageView1);

            /////////////////////////////////////////////////////////////////////////////////////////
            //

            /* JSON Parsing */
            CFileManager cFileManager = new CFileManager(getContext(), "json.txt");
            jsonData = cFileManager.rawLoad();

            // Adapter 생성
            adapter = new CListViewAdapter();

            ListView listview = (ListView)rootView.findViewById(R.id.listview);

            int size;
            int i=0;

            switch (getArguments().getInt(ARG_SECTION_NUMBER))
            {
                case 1:
                    CWrittenListData.GetInstance().Init(jsonData);

                    //////////////////////////////////////////////////////////////////////////////////////////
                    //

                    CWrittenListData.CData wData;
                    size = CWrittenListData.GetInstance().Size();
                    while(i < size)
                    {
                        wData = CWrittenListData.GetInstance().Get(i);

                        CItem cItem = new CItem();

                        cItem.m_name = wData.m_name;
                        cItem.m_subtitle = wData.m_subtitle;
                        cItem.m_imgUrl1 = wData.m_imgUrl1;
                        cItem.m_imgUrl2 = wData.m_imgUrl2;

                        adapter.addItem(cItem.m_name, cItem.m_subtitle, cItem.m_imgUrl1, cItem.m_imgUrl2);

                        i++;
                    }

                    break;

                case 2:
                    CPracticeListData.GetInstance().Init(jsonData);

                    //////////////////////////////////////////////////////////////////////////////////////////
                    //

                    CPracticeListData.CData pData;
                    size = CPracticeListData.GetInstance().Size();
                    while(i < size)
                    {
                        pData = CPracticeListData.GetInstance().Get(i);

                        CItem cItem = new CItem();

                        cItem.m_name = pData.m_name;
                        cItem.m_subtitle = pData.m_subtitle;
                        cItem.m_imgUrl1 = pData.m_imgUrl1;
                        cItem.m_imgUrl2 = pData.m_imgUrl2;

                        adapter.addItem(cItem.m_name, cItem.m_subtitle, cItem.m_imgUrl1, cItem.m_imgUrl2);

                        i++;
                    }
                    break;
            }


            listview.setAdapter(adapter);

            //////////////////////////////////////////////////////////////////////////////////////////
            //

            listview.setOnItemClickListener(new AdapterView.OnItemClickListener()
            {
                @Override
                public void onItemClick(AdapterView parent, View v, int position, long id)
                {
                    // get item
                    CListViewItem item = (CListViewItem)parent.getItemAtPosition(position);

/*                String titleStr = item.getTitle() ;
                String descStr = item.getDesc() ;
                Drawable iconDrawable = item.getIcon() ;*/

                    // TODO : use item data.

                    Intent intent = new Intent(getContext(), CYoutubePlayer.class);
                    intent.putExtra("url", item.getImgUrl1());
                    startActivity(intent);
                }
            });

            listview.setOnScrollListener(new AbsListView.OnScrollListener()
            {
                @Override
                public void onScrollStateChanged(AbsListView view, int scrollState)
                {

                }

                @Override
                public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount)
                {
                    if((firstVisibleItem + visibleItemCount) == totalItemCount)
                    {
                        btn_more.setVisibility(View.VISIBLE);
                    }
                    else
                    {
                        btn_more.setVisibility(View.GONE);
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
    public class SectionsPagerAdapter extends FragmentPagerAdapter
    {

        public SectionsPagerAdapter(FragmentManager fm)
        {
            super(fm);
        }

        @Override
        public Fragment getItem(int position)
        {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount()
        {
            // Show 3 total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position)
        {
            switch(position)
            {
                case 0:
                    return "필기";
                case 1:
                    return "실기";
            }
            return null;
        }
    }
}
