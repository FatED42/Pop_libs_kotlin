package com.example.pop_libs_kotlin.mvp.model.cache

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.example.pop_libs_kotlin.mvp.model.entity.room.RoomImage
import com.example.pop_libs_kotlin.mvp.model.entity.room.db.Database
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import java.io.*
import java.util.*

class ImageCache(private val context: Context, val db: Database) : IImageCache {

    override fun getImage(url: String) = Single.fromCallable {
        val path = db.imageDao.findByUrl(url)?.localPath
        val bitmap = BitmapFactory.decodeFile(path)
        val out = ByteArrayOutputStream()
        try {
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out)
            out.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        out.toByteArray()
    }.subscribeOn(Schedulers.io())

    override fun putImage(url: String, bytes: ByteArray?) = Completable.fromCallable {
        bytes?.let {
            val file = File(context.getExternalFilesDir("Image"), "${UUID.randomUUID()}.png")
            val fos = FileOutputStream(file)
            fos.write(bytes)
            fos.flush()
            fos.close()
            val image = RoomImage(url, file.absolutePath)
            db.imageDao.insert(image)
        }
    }.subscribeOn(Schedulers.io())

}