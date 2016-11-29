package com.example.com.project.FileManager;

import android.content.Context;

import com.example.com.project.R;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by 장호 on 2016-11-28.
 */
public class CFileManager
{
    private static String FILE_NAME = "";

    private Context m_Context = null;

    public CFileManager(Context context, String fileName)
    {
        m_Context = context;
        FILE_NAME = fileName;
    }

    /* File Input Output*/
    public void fileSave(String strData)
    {
        if(strData == null || strData.equals(""))
        {
            return;
        }

        FileOutputStream fos = null;

        try
        {
            fos = m_Context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
            fos.write(strData.getBytes());
            fos.close();
        }
        catch (IOException e)
        {
            //Toast.makeText(m_Context, "파일 저장 실패", Toast.LENGTH_SHORT).show();
        }
    }

    public String fileLoad()
    {
        try
        {
            FileInputStream fis = m_Context.openFileInput(FILE_NAME);
            byte[] data = new byte[fis.available()];

            while(fis.read(data) != -1){}

            fis.close();

            return new String(data);
        }
        catch (IOException e)
        {
            return "empty";
        }
    }

    public void delete()
    {
        m_Context.deleteFile(FILE_NAME);
    }

    /* Raw Input Output */
    public String rawLoad()
    {
        try
        {
            InputStream is = m_Context.getResources().openRawResource(R.raw.json);
            byte[] data = new byte[is.available()];

            while(is.read(data) != -1){}

            is.close();

            return new String(data);
        }
        catch(IOException e)
        {
            return "empty";
        }
    }
}
