package com.example.pop_libs_kotlin.ui.image

import android.graphics.Bitmap
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.pop_libs_kotlin.mvp.model.cache.IImageCache
import com.example.pop_libs_kotlin.mvp.model.image.IImageLoader
import com.example.pop_libs_kotlin.mvp.model.network.INetworkStatus
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import java.io.ByteArrayOutputStream
import java.io.IOException

class GlideImageLoader(
    private val networkStatus: INetworkStatus,
    private val imageCache: IImageCache
): IImageLoader<ImageView> {

    override fun load(url: String, container: ImageView) {
        networkStatus.isOnlineSingle().subscribe { isOnline ->
            if (isOnline) {
                Glide.with(container.context)
                        .asBitmap()
                        .load(url)
                        .listener(object : RequestListener<Bitmap> {
                            override fun onLoadFailed(
                                    e: GlideException?,
                                    model: Any?,
                                    target: Target<Bitmap>?,
                                    isFirstResource: Boolean
                            ): Boolean {
                                e?.printStackTrace()
                                return false
                            }

                            override fun onResourceReady(
                                    resource: Bitmap?,
                                    model: Any?,
                                    target: Target<Bitmap>?,
                                    dataSource: DataSource?,
                                    isFirstResource: Boolean
                            ): Boolean {
                                imageCache.putImage(url, resource?.let { convertBitmapToByteArray(it) })
                                        .observeOn(Schedulers.io())
                                        .subscribe()
                                return false
                            }
                        })
                        .into(container)
            } else {
                imageCache.getImage(url).observeOn(AndroidSchedulers.mainThread())
                        .subscribe { byteArray ->
                            Glide.with(container.context)
                                    .asBitmap()
                                    .load(byteArray)
                                    .into(container)
                        }
            }
        }
    }

    fun convertBitmapToByteArray(bmp: Bitmap): ByteArray {
        val out = ByteArrayOutputStream()
        try {
            bmp.compress(Bitmap.CompressFormat.PNG, 100, out)
            out.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return out.toByteArray()
    }

}