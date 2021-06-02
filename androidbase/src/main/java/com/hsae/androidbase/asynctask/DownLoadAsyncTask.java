package com.hsae.androidbase.asynctask;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.PowerManager;
import android.widget.Toast;

import com.hsae.androidbase.logutils.logutils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownLoadAsyncTask extends AsyncTask<String, Integer, String> {

    private PowerManager.WakeLock mWakeLock;
    private int ValueProgress=100;
    private Context context;

    public DownLoadAsyncTask(Context context){
        this.context=context;
    }
    /**
     * onPreExecute是可以选择性覆写的方法
     * 在主线程中执行,在异步任务执行之前,该方法将会被调用
     * 一般用来在执行后台任务前对UI做一些标记和准备工作，
     * 如在界面上显示一个进度条。
     */
    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        // take CPU lock to prevent CPU from going off if the user
        // presses the power button during download
        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        mWakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK,
                getClass().getName());
        mWakeLock.acquire();
        //Display progressBar
//        progressBar.setVisibility(View.VISIBLE);
    }

    /**
     * 抽象方法必须覆写,执行异步任务的方法
     * @param params
     * @return
     */
    @Override
    protected String doInBackground(String... params) {

        InputStream input = null;
        OutputStream output = null;
        HttpURLConnection connection = null;
        try {
            URL url = new URL(params[0]);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            // expect HTTP 200 OK, so we don't mistakenly save error report
            // instead of the file
            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                return "Server returned HTTP " + connection.getResponseCode()
                        + " " + connection.getResponseMessage();
            }
            // this will be useful to display download percentage
            // might be -1: server did not report the length
            int fileLength = connection.getContentLength();
            // download the file
            input = connection.getInputStream();
            //create output
            output = new FileOutputStream(getSDCardDir());
            byte data[] = new byte[4096];
            long total = 0;
            int count;
            while ((count = input.read(data)) != -1) {
                // allow canceling with back button
                if (isCancelled()) {
                    input.close();
                    return null;
                }
                total += count;
                // publishing the progress....
                if (fileLength > 0) // only if total length is known
                    publishProgress((int) (total * 100 / fileLength));
                //
                Thread.sleep(100);
                output.write(data, 0, count);
            }
        } catch (Exception e) {
            return e.toString();
        } finally {
            try {
                if (output != null)
                    output.close();
                if (input != null)
                    input.close();
            } catch (IOException ignored) {
            }
            if (connection != null)
                connection.disconnect();
        }

        return null;
    }

    /**
     * onProgressUpdate是可以选择性覆写的方法
     * 在主线程中执行,当后台任务的执行进度发生改变时,
     * 当然我们必须在doInBackground方法中调用publishProgress()
     * 来设置进度变化的值
     * @param values
     */
    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        //        progressBar.setmProgress(values[0]);
        //update progressBar
        if(updateUI!=null){
            updateUI.UpdateProgressBar(values[0]);
        }
    }

    /**
     * onPostExecute是可以选择性覆写的方法
     * 在主线程中执行,在异步任务执行完成后,此方法会被调用
     * 一般用于更新UI或其他必须在主线程执行的操作,传递参数bitmap为
     * doInBackground方法中的返回值
     * @param values
     */
    @Override
    protected void onPostExecute(String values) {
        super.onPostExecute(values);
        mWakeLock.release();
        if (values != null)
            logutils.d("Download error: "+values);
        else {
            Toast.makeText(context, "File downloaded", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * onCancelled是可以选择性覆写的方法
     * 在主线程中,当异步任务被取消时,该方法将被调用,
     * 要注意的是这个时onPostExecute将不会被执行
     */
    @Override
    protected void onCancelled() {
        super.onCancelled();
    }

    /**
     * get SD card path
     * @return
     */
    public File getSDCardDir(){
        if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())){
            // 创建一个文件夹对象，赋值为外部存储器的目录
            String dirName = Environment.getExternalStorageDirectory()+"/MyDownload/";
            File f = new File(dirName);
            if(!f.exists()){
                f.mkdir();
            }
            File downloadFile=new File(f,"new.jpg");
            return downloadFile;
        }
        else{
            logutils.d("NO SD Card!");
            return null;
        }

    }

    public UpdateUI updateUI;


    public interface UpdateUI{
        void UpdateProgressBar(Integer values);
    }

    public void setUpdateUIInterface(UpdateUI updateUI){
        this.updateUI=updateUI;
    }
}
