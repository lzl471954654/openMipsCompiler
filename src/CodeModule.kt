import kotlin.system.exitProcess

var line = 1
fun dealCode(){
    while (srcScanner.hasNext()){
        val lineData = srcScanner.nextLine()
        val datas = lineData.split(" ")
        if (datas.size>2 || datas.isEmpty()){
            println("invalid line : $line")
            exitProcess(-1)
        }
        val instruction = datas[0]
        val arguments = datas[1]

    }
}

fun convertInsToByte(ins : String, args : String):Int{
    val datas : Int
    when {
        rSet.contains(ins) -> datas = dealRType(ins,args)
        iSet.contains(ins) -> datas = dealIType(ins,args)
        jSet.contains(ins) -> datas = dealJType(ins, args)
        else -> {
            println("Unknown instruction : $ins ; line:$line")
            exitProcess(-1)
        }
    }
    return datas
}

fun dealRType(ins: String,args: String):Int{
    val baseTemplate = 0x00000000
    if (!moveSet.contains(ins)){

    }
}

fun dealIType(ins: String,args: String):Int{

}

fun dealJType(ins: String,args: String):Int{

}

val rMap = hashMapOf<String,Byte>(
        "add" to 0x20,
        "sub" to 0x22,
        "and" to 0x24,
        "or"  to 0x25,
        "xor" to 0x26,
        "sll" to 0x00,
        "srl" to 0x02,
        "sra" to 0x03
)

val rSet = hashSetOf(
        "add",
        "sub",
        "and",
        "or",
        "xor",
        "sll",
        "srl",
        "sra"
)

val moveMap = hashMapOf<String,Byte>(
        "sll" to 0x00,
        "srl" to 0x02,
        "sra" to 0x03
)

val moveSet = hashSetOf(
        "sll",
        "sra",
        "srl"
)

val iMap = hashMapOf<String,Byte>(
        "addi" to 0x20,
        "andi" to 0x30,
        "ori"  to 0x34,
        "xori" to 0x38,
        "lw"   to 0x00
)

val iSet = hashSetOf(
        "addi",
        "andi",
        "ori",
        "xori",
        "lw",
        "sw",
        "beq",
        "bne",
        "lui"
)

val jSet = hashSetOf(
        "jr",
        "j",
        "jal"
)