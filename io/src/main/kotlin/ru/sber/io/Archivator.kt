package main.kotlin.ru.sber.io

import java.io.File
import java.util.zip.ZipEntry
import java.util.zip.ZipInputStream
import java.util.zip.ZipOutputStream

/**
 * Реализовать методы архивации и разархивации файла.
 * Для реализиации использовать ZipInputStream и ZipOutputStream.
 */
class Archivator {

    /**
     * Метод, который архивирует файл logfile.log в архив logfile.zip.
     * Архив должен располагаться в том же каталоге, что и исходной файл.
     */
    fun zipLogfile(fileNameIn: String = "logfile.log", fileNameOut: String = "logfile.zip",
                   pathIn: String = "io/", pathOut: String = pathIn ) {
        val inputFile = File(pathIn + fileNameIn)
        val outputFile = File(pathOut + fileNameOut)
        var inputBuffer: ByteArray

        inputFile.inputStream().use {
            inputBuffer = it.readBytes()
        }

        ZipOutputStream(outputFile.outputStream()).use {
            it.putNextEntry(ZipEntry(fileNameIn))
            it.write(inputBuffer)
        }
    }

    /**
     * Метод, который извлекает файл из архива.
     * Извлечь из архива logfile.zip файл и сохарнить его в том же каталоге с именем unzippedLogfile.log
     */
    fun unzipLogfile(fileNameIn: String = "logfile.zip", fileNameOut: String = "unzippedLogfile.log",
                     pathIn: String = "io/", pathOut: String = pathIn) {
        val inputFile = File(pathIn + fileNameIn)
        val outputFile = File(pathOut + fileNameOut)
        var inputBuffer: ByteArray

        ZipInputStream(inputFile.inputStream()).use {
            println(it.nextEntry)
            inputBuffer = it.readBytes()
        }

        outputFile.outputStream().use {
            it.write(inputBuffer)
        }
    }
}
