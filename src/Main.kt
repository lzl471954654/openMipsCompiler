import java.io.BufferedOutputStream
import java.io.BufferedWriter
import java.io.DataOutputStream
import java.io.File
import java.util.*

lateinit var srcScanner: Scanner
lateinit var targetFile: File
lateinit var srcFile: File
lateinit var txtFile: File
var outStream : DataOutputStream? = null
var txtOut : BufferedWriter? = null
fun main(args: Array<String>) {
    val srcFilePath = args[0]
    if (srcFilePath.isNullOrEmpty()){
        println("src file path not found!")
        return
    }
    srcFile = File(srcFilePath)
    if (!srcFile.exists()){
        println("src file not exists!")
        return
    }
    val targetFilePath = srcFile.parent + srcFile.name + ".data"
    val txtFilePath = srcFile.path + ".out" + ".txt"
    srcScanner = Scanner(srcFile.inputStream())
    targetFile = File(targetFilePath)
    if (targetFile.exists()){
        targetFile.delete()
    }
    targetFile.createNewFile()
    txtFile = File(txtFilePath)
    if (txtFile.exists()){
        txtFile.delete()
    }
    txtFile.createNewFile()
    dealCode()
    closeStream()
    println("Compiler Success!")
}

fun closeStream(){
    outStream?.close()
    txtOut?.close()
}