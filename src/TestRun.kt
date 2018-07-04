import java.io.File
import java.util.*

val map = hashMapOf(
        "123" to 6
)

fun main(args: Array<String>) {
    println("11111111111111111111111111110000".toMyInt())
    println(Integer.toBinaryString((12-32)))
    println(Integer.toBinaryString((12-32)).substring(14 until  32-2))
    var s = "123456789"
    println(s.substring(s.length-4 until s.length))


    val number3 = "0x80"
    println(number3.isOct())
    println(Integer.toBinaryString(NumberFormatUtil.toUnsigedInt(number3.toShort(10))))

    val number2 = "-111"
    println(number2.toInt(10))
    println(number2.toInt(10).toString(2))

    val oct = "+55111"
    println(oct.substring(1 until oct.length))
    println(oct.isOct())

    val spaceDemo = "    abc"
    println(spaceDemo.trimStart(' '))

    val hex = "0xff"
    println(Integer.parseInt(hex.toLowerCase().replace("0x",""),16))

    val bin = "11101100100000100000000000000000"
    println(bin.toLong(2))
    val l = bin.toLong(2)
    println(l.toInt().toString(2))
    println(bin.toLong().toString(2).toInt(2))
    println(bin.toInt(2))
    println(bin.toInt(2).toString(2).toInt(2))


    val file = File("test.s")
    val scanner = Scanner(file.inputStream())
    val temp = scanner.nextLine()
    println(temp)
    scanner.reset()
    println(scanner.nextLine())
    temp.split(" ").forEach {
        println(it)
    }

    val temp1 = "$3"
    val value = temp1.substring(1 until temp1.length)
    println(value)
    println( 0x03.toByte() == value.toByte() )


    val arg = "$8"
    val number = arg.substring(1 until arg.length).toInt()
    val byteNumber = number.toString(2)
    println(byteNumber)
    val builder = StringBuilder()
    var i = byteNumber.length
    while (i<6){
        builder.append('0')
        i++
    }
    builder.append(byteNumber)
    println(builder.toString())
}
