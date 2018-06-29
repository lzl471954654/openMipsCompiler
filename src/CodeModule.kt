import java.io.BufferedOutputStream
import java.io.BufferedWriter
import java.io.DataOutputStream
import java.io.FileWriter
import java.util.*
import javax.print.attribute.IntegerSyntax
import kotlin.reflect.jvm.internal.impl.renderer.ClassifierNamePolicy
import kotlin.system.exitProcess

var line = 1
var codeAddress = 0
var codeLine = 0

val addressMap = HashMap<String,Int>()

fun dealCode() {
    outStream = DataOutputStream(targetFile.outputStream())
    txtOut = BufferedWriter(FileWriter(txtFile))
    markAddress()
    println(addressMap)

    while (srcScanner.hasNext()) {
        val lineData = srcScanner.nextLine().trimStart(' ')
        if (lineData.startsWith('#') || lineData.endsWith(':')){
            line++
            continue
        }
        val datas = lineData.split(" ")
        if (datas.size > 2 || datas.isEmpty()) {
            println("invalid line : $line")
            closeStream()
            exitError()
        }
        val instruction = datas[0]
        val arguments = datas[1]
        val binary = convertInsToByte(instruction, arguments)
        outStream!!.writeInt(binary)
        txtOut!!.write(IntToBinaryString(binary))
        txtOut!!.newLine()
        line++
    }

    outStream?.close()
    txtOut?.close()
}

fun IntToBinaryString(int: Int):String{
    val builder = StringBuilder()
    val data = Integer.toBinaryString(int)
    var i = data.length
    while (i<32){
        builder.append('0')
        i++
    }
    builder.append(data)
    return builder.toString()
}

fun markAddress(){
    while (srcScanner.hasNext()){
        val lineData = srcScanner.nextLine().trimStart(' ')
        if (lineData.endsWith(':')){
            addressMap.put(lineData.substring(0 until lineData.length-1),codeAddress)
        }else if(lineData.startsWith('#'))
            continue
        else{
            val datas = lineData.split(' ')
            if (datas.isEmpty()){

            }else{
                val ins = datas[0]
                if ( ins in jMap || ins in rMap || ins in iMap){
                    codeLine ++
                    codeAddress = codeLine * 4
                }
            }
        }
    }
    srcScanner.close()
    srcScanner = Scanner(srcFile.inputStream())
}

fun exitError(){
    closeStream()
    exitProcess(-1)
}


fun convertInsToByte(ins: String, args: String): Int {
    var datas: Int = 0X00000000
    when {
        rMap.containsKey(ins) -> datas = dealRType(ins, args)
        iMap.containsKey(ins) -> datas = dealIType(ins, args)
        jMap.containsKey(ins) -> datas = dealJType(ins, args)
        else -> {
            println("Unknown instruction : $ins ; line:$line")
            exitError()
        }
    }
    return datas
}

fun getNumberOfRegister(arg: String): String {
    if (!arg.startsWith("$")) {
        println("argument is wrong , register must start with $ ; line:$line")
        exitError()
    }
    val number = arg.substring(1 until arg.length).toInt()
    val byteNumber = number.toString(2)
    val builder = StringBuilder()
    var i = byteNumber.length
    while (i < 5) {
        builder.append('0')
        i++
    }
    builder.append(byteNumber)
    return builder.toString()
}

fun getImmediate(arg: String, size: Int): String {
    val byteNumber = if (arg.isOct()){
        if (size<=16){
            val s = arg.toShort(10)
            println(s)
            Integer.toBinaryString(NumberFormatUtil.toUnsigedInt(s))
        }else{
            Integer.toBinaryString(Integer.parseInt(arg,10))
        }
        //arg.toInt(10)
    }else if (arg.isHex()){
        val n = arg.toLowerCase().replace("0x","")
        Integer.toBinaryString(Integer.parseInt(n,16))
    }else{
        if (!addressMap.containsKey(arg)){
            println("Not found address mark : $arg ; line:$line")
            exitError()
            "0"
        }else{
            Integer.toBinaryString(addressMap[arg]!!)
        }
    }

    val builder = StringBuilder()
    var i = byteNumber.length
    while (i < size) {
        builder.append('0')
        i++
    }
    builder.append(byteNumber)
    return builder.toString()
}

fun dealRType(ins: String, args: String): Int {
    val builder = StringBuilder()
    builder.append("000000")
    if (!moveMap.containsKey(ins)) {
        val argsList = args.split(",")
        if (argsList.size < 3) {
            println("argument is not enough for $ins ; line:$line")
            exitError()
        }
        val resultRegister = getNumberOfRegister(argsList[0])
        val firstRegister = getNumberOfRegister(argsList[1])
        val secondRegister = getNumberOfRegister(argsList[2])
        builder.append(firstRegister)
                .append(resultRegister)
                .append(secondRegister)
                .append("00000")
                .append(rMap[ins])

    } else {
        val argsList = args.split(",")
        if (argsList.size < 3) {
            println("argument is not enough for $ins ; line:$line")
            exitError()
        }
        val resultRegister = getNumberOfRegister(argsList[0])
        val firstRegister = getNumberOfRegister(argsList[1])
        val immediate = getImmediate(argsList[2], 5)
        builder.append("00000")
                .append(firstRegister)
                .append(resultRegister)
                .append(immediate)
                .append(moveMap[ins])
    }
    println("R : $builder")
    return builder.toString().toMyInt()
}

fun dealIType(ins: String, args: String): Int {
    val builder = StringBuilder()
    val argsList = args.split(",")
    if (ins == "lui") {
        if (argsList.size < 2) {
            println("argument is not enough for instruction : $ins ; line:$line")
            exitError()
        }
        val resultRegister = getNumberOfRegister(argsList[0])
        val immediate = getImmediate(argsList[1], 16)
        builder.append(iMap[ins])
                .append("00000")
                .append(resultRegister)
                .append(immediate)
    } else {
        val resultRegister = getNumberOfRegister(argsList[0])
        val firstRegister = getNumberOfRegister(argsList[1])
        val immediate = getImmediate(argsList[2], 16)
        //println(immediate)
        builder.append(iMap[ins])
                .append(firstRegister)
                .append(resultRegister)
                .append(immediate)
    }
    println("I : $builder")
    return builder.toString().toMyInt()
}

fun dealJType(ins: String, args: String): Int {
    val builder = StringBuilder()
    if (ins == "jr") {
        val resultRegister = getNumberOfRegister(args)
        builder.append("000000")
                .append(resultRegister)
                .append("00000")
                .append("00000")
                .append("00000")
                .append(jMap[ins])
    } else {
        val immediate = getImmediate(args, 26)
        builder.append(jMap[ins])
                .append(immediate)
    }
    println("J : $builder")
    return builder.toString().toMyInt()
}

fun String.isHex():Boolean{
    return this.matches("\\b(0[xX])?[A-Fa-f0-9]+\\b".toRegex())
}

fun String.isOct():Boolean{
    return if (this[0] == '-' || this[0] == '+'){
        this.substring(1 until this.length).matches("[0-9]+".toRegex())
    }else if (this[0] in '0'..'9'){
        this.matches("[0-9]+".toRegex())
    }else
        false
}

fun String.toMyInt():Int{
    return NumberFormatUtil.toInt(this)
}

val rMap = hashMapOf<String, String>(
        "add" to "100000",
        "sub" to "100010",
        "and" to "100100",
        "or" to "100101",
        "xor" to "100110",
        "sll" to "000000",
        "srl" to "000010",
        "sra" to "000011"
)


val moveMap = hashMapOf<String, String>(
        "sll" to "000000",
        "srl" to "000010",
        "sra" to "000011"
)


val iMap = hashMapOf<String, String>(
        "addi" to "001000",
        "andi" to "001100",
        "ori" to "001101",
        "xori" to "001110",
        "lw" to "100011",
        "sw" to "101011",
        "beq" to "000100",
        "bne" to "000101",
        "lui" to "001111"
)


val jMap = hashMapOf(
        "jr" to "001000",
        "j" to "000010",
        "jal" to "000011"
)
