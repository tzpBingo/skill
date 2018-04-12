object Base {

  def main(args: Array[String]): Unit = {

    val x = if("hello"=="hello") "ok" else "no"
    println(x)

    var a=10
    while(a<20){
      println(a)
      a = a+1
    }


    val numArr = new Array[Int](10)
    val strArray = new Array[String](10)
    val strArray2 = Array("asdf")
    numArr(1) = 99
    println(numArr)
  }


  class Base{

  }

}
