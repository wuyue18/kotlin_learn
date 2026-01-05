class day1 {
    //1.变量声明
    fun fun1(){
        //var 可变变量
        //val 常量
        var count : Int =10
        count = 20
        val str : String = "Hello World"
        //声明时可以不带数据类型，编译时会自己进行判断
        var name = "五月"
        //但是声明时不能为空
        //val languageName: String = null
        //如果需要声明成null那么需要声明为可以为空的类型
        var a:String?=null
        //同样，变量必须初始化
        // var b; 这是编译不通过的
        var b:Int//应该是不允许的但是不知道为什么，没有报错
        //补充：方法内的局部变量允许先声明，但是使用前必须进行初始化，否则会报错



    }

    //2.条件判断
    fun fun2(a:Int){
        var b = 0
        //常规判断
        if(a==1){
            b = 1
        }else if (a==2){

            b =2
        }else{
            b =3
        }
        //条件表达式，kotlin习惯用的写法
        b=if (a==1){
            1
        }else if(a==2) {
            2
        }else{
            3
        }
        //这里有警告是因为 多条件下kotlin推荐使用when,类似于switch但是比switch更强大
        b = when(a){
            1->1
            2->2
            else->3
        }
        //或者
        b = when(a){
            1,2->a
            else->3
        }
        //不管是when 还是if 必须保证表达式必须要有结果，也就是满足所有分支都有返回值，就像方法一样
        // b = when(a){
        //            1,2->a这种是会报错的
    }
    //3.空安全
    private fun getString():String?{
        return if (System.currentTimeMillis() % 2 == 0L) "Hello" else null
    }

    fun fun3(){
        val a:String = "test"
        val b:String? = getString()
        //空安全旨在尽量在编译时消除掉空指针异常（NPE）的可能性
        //做法是将数据类型区分为可空和不可空，例如 String?和String
        // 可为空的变量可以赋值为null，但是不能直接调用方法
        //不可为空的变量不可以赋值为null但是可以直接调用方法

        println(a.length)    // ✅ 安全
        //println(b.length)    // ❌ 编译错误
        println(b?.length)   // ✅ 安全调用，如果为空会输出null
        println(b?.length?:0)   /* ✅ 安全调用，如果为空会输出0 */
        println(b!!.length)  // ❌ 如果 b 为 null → 运行时异常  !!就是告诉编译器我保证他不是空但是实际是不确定的，只是主观上的保证
        //智能类型转换
        if (b!=null){
            println(b.length)
            //这里可以通过是因为在判断非空之后b自动升级为了一个非空变量，所以可以直接调用了,但是只限在判断内
        }
        //这里应该报错的，重点应该在上边判断的警告了，不知道为什么b被认定为一定非空了，需要再研究
        println(b.length)

        //补充：这里的现象是因为  println(b!!.length)
        //kotlin认为如果b是空的那么当场就异常了，走不到下边的代码，他能走到下边的代码就证明他一定不为空。叫做控制流分析
        //会自己分析代码，有点牛逼奥



    }

    //4.控制流分析
    //不是简单地逐行检查语法，而是通过追踪程序所有可能的执行路径（Path），来推断变量在某一时刻的状态（如：是否为空、是什么类型、是否已初始化）。
    //核心工具： 控制流图（Control Flow Graph, CFG）。
    //Kotlin CFA 的三大核心能力
    //A. 智能转换 (Smart Casts)
    //这是最直观的表现。编译器根据之前的判断条件，自动转换变量的类型。
    //空安全转换： 经过 if (x != null) 或 x!! 后，变量从 String? 自动变为 String。
    //类型检查转换： 经过 if (x is String) 后，变量自动具备 String 的所有方法。
    //B. 确定性赋值检查 (Definite Assignment)
    //编译器会确保你在读取一个变量之前，它在所有可能的执行路径中都已经被初始化了。
    //C. 不可达代码与尽头分析 (Unreachable Code & Dead Ends)
    //编译器能识别出哪些代码永远不会执行，或者哪些路径已经到了尽头。
    //
    //抛出异常： 如果一个函数调用了 throw 或 return，CFA 知道后面的代码不会执行。
    //
    //提前终止： 像 b!! 这种操作，CFA 认为它是一个“过滤网”，通过了它的数据一定是非空的。
    //总结：
    //可以把 CFA 想象成一个水流系统：
    //入口： 变量带着模糊的类型进入（如 Any?）。
    //过滤器： if、is、!! 就像滤网，过滤掉了 null 或错误的类型。
    //出口： 在滤网下方的代码块中，变量已经变成了纯净的、确定的类型（如 String）。

}