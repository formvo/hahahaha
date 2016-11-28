package com.example.com.project;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by USER on 2016-11-27.
 */

public class CTest {

    public class CData{
        public String name = null;
        public String ex = null;
        public String url = null;
    }

    ArrayList<CData> list = null;

    public void init(String strJsonArray){
        list = new ArrayList<CData>();

        JSONArray jsonArray = null;

        CData cData = null;
        try
        {
            jsonArray = new JSONArray ( strJsonArray );

            /**/
            JSONObject json = null;
            String strImg_url1 = null;
            String strImg_url2 = null;

            int i = jsonArray.length ();

            while ( --i >= 0 )
            {
                json = jsonArray.getJSONObject ( i );

                /**/
                cData = new CData ();

                cData.name = json.getString ( "name" );
                cData.ex = json.getString ( "subtitle" );
                cData.url = json.getString ( "url" );
                strImg_url1 = json.getString ( "img_id1" );
                strImg_url2 = json.getString ( "img_id1" );

                list.add ( cData );

                Log.i ( "Data:", json.getString ( "name" ) );

            }
        }
        catch ( JSONException e )
        {
            e.printStackTrace ();
            Log.i ( "json", "json err!! " + e );

            return;
        }
    }
}
