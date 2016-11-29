package com.example.com.project;

import android.graphics.drawable.Drawable;

/**
 * Created by com on 2016-11-16.
 */

public class CListViewItem
{
    private String m_name = null;
    private String m_subtitle = null;
    private String m_imgUrl1 = null;
    private String m_imgUrl2 = null;

    public String getName()
    {
        return m_name;
    }

    public void setName(String m_name)
    {
        this.m_name = m_name;
    }

    public String getSubtitle()
    {
        return m_subtitle;
    }

    public void setSubtitle(String m_subtitle)
    {
        this.m_subtitle = m_subtitle;
    }

    public String getImgUrl1()
    {
        return m_imgUrl1;
    }

    public void setImgUrl1(String m_imgUrl1)
    {
        this.m_imgUrl1 = m_imgUrl1;
    }

    public String getImgUrl2()
    {
        return m_imgUrl2;
    }

    public void setImgUrl2(String m_imgUrl2)
    {
        this.m_imgUrl2 = m_imgUrl2;
    }
}
