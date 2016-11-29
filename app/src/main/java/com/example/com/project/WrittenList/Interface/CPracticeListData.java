package com.example.com.project.WrittenList.Interface;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by 장호 on 2016-11-28.
 */
public class CPracticeListData
{
    public class CData
    {
        public String m_name = null;
        public String m_subtitle = null;
        public String m_imgUrl1 = null;
        public String m_imgUrl2 = null;
    }

    private static CPracticeListData instance = null;

    ArrayList<CData> m_list = null;

    /*
     * *
     */

    public static CPracticeListData GetInstance()
    {
        if(instance != null)
        {
            return instance;
        }

        instance = new CPracticeListData();

        return instance;
    }

    public void Init(String strJson)
    {
        m_list = new ArrayList<CData>();

        JSONObject jsonObject = null;
        JSONArray jsonArray = null;

        CData cData = null;
        try
        {
            jsonObject = new JSONObject(strJson);
            jsonArray = jsonObject.getJSONArray("Pro_Practice");

            /**/
            JSONObject json = null;
            int size = jsonArray.length();
            int i = 0;
            while(i < size)
            {
                json = jsonArray.getJSONObject(i);

                /**/
                cData = new CData();

                cData.m_name = json.getString("name");
                cData.m_subtitle = json.getString("subtitle");

                cData.m_imgUrl1 = json.getString("img_id1");
                cData.m_imgUrl2 = json.getString("img_id2");

                m_list.add(cData);

                i++;
            }
        }
        catch(JSONException e)
        {
            e.printStackTrace();
            Log.i ( "json", "json err!! " + e );

            return;
        }
    }

    public int Size()
    {
        return m_list.size();
    }

    public CData Get(int idx)
    {
        return m_list.get(idx);
    }
}
