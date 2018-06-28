import java.io.File
import java.util.*

lateinit var srcScanner: Scanner
lateinit var targetFile: File

fun main(args: Array<String>) {
    val srcFilePath = args[0]
    if (srcFilePath.isNullOrEmpty()){
        println("src file path not found!")
        return
    }
    val srcFile = File(srcFilePath)
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
}