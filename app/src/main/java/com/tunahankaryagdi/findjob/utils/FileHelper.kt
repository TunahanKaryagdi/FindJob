package com.tunahankaryagdi.findjob.utils

import android.content.Context
import android.net.Uri
import java.io.File

object FileHelper {
     fun Uri.toFile(context: Context) : File?{

        val inputStream = context.contentResolver.openInputStream(this)
        val tempFile = File.createTempFile("temp", ".png")
        return try {
            tempFile.outputStream().use { fileOut ->
                inputStream?.copyTo(fileOut)
            }
            tempFile.deleteOnExit()
            inputStream?.close()
            tempFile
        } catch (e: Exception) {
            null
        }
    }
}