package com.example.com.project;

import java.util.ArrayList;

/**
 * Created by USER on 2016-11-27.
 */

public class CDataArray {

    public static class CData{
        public String name = null;
        public String ex;
        public String url = null;
    }

    private ArrayList<CData> list = null;

    public void init(){

        list = new ArrayList<CData>();

        CData cData = null;

        cData.name = "오리엔테이션";
        cData.ex = "▶ 오리엔테이션";
        cData.url = "STL2X74DGNA";

        list.add( cData );
    }
}