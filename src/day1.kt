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
    fun fun3_0():String?{
        return null
    }

    fun fun3(){
        val a:String = "test"
        val b:String? = fun3_0()
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



    }



}