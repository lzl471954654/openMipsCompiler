import java.io.DataOutputStream
import java.io.File
import java.util.*

lateinit var srcScanner: Scanner
lateinit var targetFile: File
lateinit var srcFile: File
var outStream : DataOutputStream? = null
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
    srcScanner = Scanner(srcFile.inputStream())
    targetFile = File(targetFilePath)
    if (targetFile.exists()){
        targetFile.delete()
    }
    targetFile.createNewFile()
    dealCode()
    closeStream()
}

fun closeStream(){
    outStream?.close()
}