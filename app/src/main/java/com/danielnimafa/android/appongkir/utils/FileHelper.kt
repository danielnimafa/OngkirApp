package com.danielnimafa.android.appongkir.utils

import android.app.Activity
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Environment
import android.webkit.MimeTypeMap
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Androidev on 6/22/2016.
 *
 *
 * ini class helper untuk hal tentang pembuatan file
 */
object FileHelper {

    var extStorageDirectory = Environment.getExternalStorageDirectory().toString()
    var dirPic = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString()
    var standardSize: Long = 600

    // Create the storage directory if it does not exist
    // Create a media file name
    val outputFilePickGallery: File
        get() {
            val mainDir = File(extStorageDirectory
                    + File.separator + Const.app_name + File.separator)
            val mediaStorageDir = File(mainDir, Const.IMAGE_DIRECTORY_NAME)
            println("Main Dir: " + mediaStorageDir.path)
            if (!mediaStorageDir.exists()) {
                if (!mediaStorageDir.mkdirs()) {
                    System.out.println("ADAPTER: Oops! Failed create ${Const.IMAGE_DIRECTORY_NAME} directory")
                }
            }
            val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss",
                    Locale.getDefault()).format(Date())

            val mediaFile: File
            mediaFile = File(mediaStorageDir.path + File.separator
                    + "IMG_PICKGALLERY_" + timeStamp + ".jpg")
            return mediaFile
        }

    val outputMediaFileCapture: File
        get() {
            val location = extStorageDirectory + File.separator + Const.app_name + File.separator
            val mediaStorageDir = File(location, Const.IMAGE_DIRECTORY_NAME)
            val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
            val fileName = "IMG_CAPTURE_$timeStamp.jpg"

            if (!mediaStorageDir.exists()) {
                if (!mediaStorageDir.mkdirs()) {
                    System.out.println("ADAPTER: Oops! Failed create ${Const.IMAGE_DIRECTORY_NAME} directory")
                }
            }

            return File(mediaStorageDir.path + File.separator + fileName)
        }

    fun makeImgCacheFile(): File {
        val mainDir = File(extStorageDirectory
                + File.separator + Const.app_name + File.separator)
        val mediaStorageDir = File(mainDir, Const.IMAGE_DIRECTORY_NAME)
        println("Main Dir: " + mediaStorageDir.path)

        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                System.out.println("ADAPTER: Oops! Failed create ${Const.IMAGE_DIRECTORY_NAME} directory")
            }
        }

        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss",
                Locale.getDefault()).format(Date())

        return File("${mediaStorageDir.path}${File.separator}_IMG_CACHE_$timeStamp.jpg")
    }

    fun makeImgCacheFileWithPosition(pos: Int): File {
        val mainDir = File(extStorageDirectory
                + File.separator + Const.app_name + File.separator)
        val mediaStorageDir = File(mainDir, Const.IMAGE_DIRECTORY_NAME + File.separator
                + Const.IMAGECACHE_DIRECTORY_NAME)
        println("Main Dir: " + mediaStorageDir.path)

        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                System.out.println("ADAPTER: Oops! Failed create ${Const.IMAGE_DIRECTORY_NAME} directory")
            }
        }

        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss",
                Locale.getDefault()).format(Date())

        return File("${mediaStorageDir.path}${File.separator}_IMG_CACHE_${timeStamp}_$pos.jpg")
    }

    @Throws(IOException::class)
    fun createImageFile(activity: Activity): File {

        val mCurrentPhotoPath: String
        val imageFile: File
        val state = Environment.getExternalStorageState()
        val timeStamp = SimpleDateFormat("dd-MMM-yyyy").format(Date())
        val albumName = Const.app_name
        val fileName = "IMG_" + albumName + "_capture_" + timeStamp

        if (Environment.MEDIA_MOUNTED == state) {
            val storageDir = Environment.getExternalStorageDirectory()
            imageFile = File(storageDir, fileName + ".jpg")
            mCurrentPhotoPath = imageFile.absolutePath
            Sout.log("Media Mounted", mCurrentPhotoPath)

        } else {
            val storageDir = File(activity.filesDir, albumName)
            imageFile = File(storageDir, fileName + ".jpg")
            mCurrentPhotoPath = imageFile.absolutePath
            Sout.log("Media NOT Mounted", mCurrentPhotoPath)
        }

        return imageFile
    }

    fun makeNoMediaFile(): File {
        val mediaFile: File
        val location = extStorageDirectory + File.separator + Const.app_name + File.separator
        val mediaStorageDir = File(location, Const.IMAGE_DIRECTORY_NAME)
        val fileName = Const.NO_MEDIA_FILENAME

        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                System.out.println("ADAPTER: Oops! Failed create ${Const.IMAGE_DIRECTORY_NAME} directory")
            }
        }

        mediaFile = File(mediaStorageDir.path + File.separator + fileName)
        Sout.log("nomedia file", mediaFile.absolutePath)
        return mediaFile
    }

    fun getFileSize(path: String): Long {
        var length: Long = 0
        try {
            val pickedImage = File(path)
            length = pickedImage.length()
            length = length / 1024
        } catch (e: Exception) {
            Sout.trace(e)
        }
        return length
    }

    /*@Throws(IOException::class)
    fun copy(src: File, dst: File) {
        val `in` = FileInputStream(src)
        val out = FileOutputStream(dst)

        // Transfer bytes from in to out
        val buf = ByteArray(1024)
        var len: Int
        while ((len = `in`.read(buf)) > 0) {
            out.write(buf, 0, len)
        }
        `in`.close()
        out.close()
    }*/

    fun createAssetsFolder() {
        val mainDir = File(extStorageDirectory
                + File.separator + Const.app_name + File.separator)
        val assetFolder = File(mainDir.toString())

        if (!assetFolder.exists()) {
            assetFolder.mkdirs()
        }
    }

    fun getMimeType(url: String): String? {
        var type: String? = null
        val extension = MimeTypeMap.getFileExtensionFromUrl(url)
        if (extension != null) {
            type = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension)
        }
        return type
    }

    fun delImage(path: String) {
        val fdelete = File(path)
        if (fdelete.exists()) {
            if (fdelete.delete()) {
                Sout.log("deleted file image", path)
            } else {
                Sout.log("failed deleting file image", path)
            }
        }
    }
}
