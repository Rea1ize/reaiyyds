package com.hsae.javabase.reflex;
import android.content.Context;
import android.util.Log;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;
public class Demo {
    final static String TAG = "4444_demo";
    private static Context mContext;

    static void setContext(Context context){
        mContext = context;
    }

    public static void main() throws Exception{

        //通过反射获取class对象
        Class stuClass = Class.forName(getValue("className"));
        //获取show()方法
        Method m= stuClass.getMethod(getValue("methodName"));
        //调用show()方法
        m.invoke(stuClass.getConstructor().newInstance());

    }

    public static String getValue(String key) throws IOException {

        Properties pro = new Properties();//获取配置文件的对象

        pro.load(mContext.getAssets().open("pro"));

      String className =   pro.getProperty("className");

        Log.d(TAG, "className: "+className);

        //FileReader in = new FileReader("pro.txt");//获取输入流

        //pro.load(in);//将流加载到配置文件对象中

       // in.close();

        return pro.getProperty(key);

    }


}
