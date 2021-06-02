package com.hsae.kotlindemo

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

//空安全设计 kotlin独有的
class MainActivity : AppCompatActivity() {
    /**
     * val只读变量。它只能赋值一次，不能修改
     * var是一种可读可写变量。
     */

    //kotlin里面变量默认是不允许为空的，除非在类型后面加个？解除非空限制
    val languageName: String = "reai_Kotlin"

    val somebody : String  = "s"

    val name : Int? = null

    var location = "航盛"  //Kotlin 有个很方便的地方是，如果你在声明的时候就赋值，那不写变量类型也行：

    //还是有些场景，变量的值真的无法保证空与否，比如你要从服务器取一个 JSON 数据，并把它解析成一个 User 对象
    //myname就是可空类型
    var myname : String? = "热爱"

    //这么写影响代码可读性
    var view : View? = null

    //延迟初始化
    lateinit var view1 : View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        //它的作用就是让 IDE 不要对这个变量检查初始化和报错。
        // 换句话说，加了这个 lateinit 关键字，这个变量的初始化就全靠你自己了，编译器不帮你检查了。
       // view1 = findViewById(R.id.staticLayout)


        //这里需要判空
//        if(view != null){
//            view.setBackgroundColor(Color.RED)
        // 👆这样写会报错，Smart cast to 'View' is impossible, because 'view' is a mutable property that could have been changed by this time
//            //这个报错的意思是即使你检查了非空也不能保证下面调用的时候就是非空，因为在多线程情况下，其他线程可能把它再改成空的。
//        }
       // view?.setBackgroundColor(Color.RED)
        //这个写法同样会对变量做一次非空确认之后再调用方法，这是 Kotlin 的写法，并且它可以做到线程安全，因此这种写法叫做「safe call」

       // view!!.setBackgroundColor(Color.RED)
        //意思是告诉编译器，我保证这里的 view 一定是非空的，编译器你不要帮我做检查了，有什么后果我自己承担。
        // 这种「肯定不会为空」的断言式的调用叫做 「non-null asserted call」。
        // 一旦用了非空断言，实际上和 Java 就没什么两样了，但也就享受不到 Kotlin 的空安全设计带来的好处（在编译时做检查，而不是运行时抛异常）了。

        //Java 里面的 @Nullable 和 @NonNull 注解，在转换成 Kotlin 后对应的就是可空变量和不可空变量

        println("reai_我重启了")
        speak()

        println(cook("平菇"))
    }

    fun speak (){
        myname = null
        println(languageName)
//        launch (Dispatchers.Main){
//
//        }
    }

    fun cook (name : String) : String{
        return name
    }


    /**
     * suspend提醒 ，我是一个被自动放在后台运行的耗时函数
     */
    suspend fun suspendingGetImage(imageId : String ){
      //  println("hello!")

        withContext(Dispatchers.IO){

        }
    }




}