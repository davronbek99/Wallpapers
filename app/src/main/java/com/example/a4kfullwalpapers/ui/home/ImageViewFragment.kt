package com.example.a4kfullwalpapers.ui.home

import android.Manifest
import android.app.ProgressDialog
import android.app.WallpaperManager
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.AsyncTask
import android.os.Build
import android.os.Bundle
import android.os.Environment
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.downloader.Error
import com.downloader.OnDownloadListener
import com.downloader.OnStartOrResumeListener
import com.downloader.PRDownloader
import com.downloader.PRDownloaderConfig
import com.example.a4kfullwalpapers.MainActivity
import com.example.a4kfullwalpapers.R
import com.example.a4kfullwalpapers.adapters.FilterImageAdapter
import com.example.a4kfullwalpapers.db.AppDatabase
import com.example.a4kfullwalpapers.db.ImageEntity
import com.example.a4kfullwalpapers.models.Photo
import com.example.a4kfullwalpapers.ui.home.filter.GPUImageFilterTools
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import com.like.LikeButton
import com.like.OnLikeListener
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import jp.co.cyberagent.android.gpuimage.GPUImageView
import jp.co.cyberagent.android.gpuimage.filter.GPUImageFilter
import kotlinx.android.synthetic.main.bottom_sheet.view.*
import kotlinx.android.synthetic.main.fragment_image_view.view.*
import retrofit2.http.Url
import zlc.season.rxdownload4.manager.Completed
import zlc.season.rxdownload4.recorder.RxDownloadRecorder
import java.io.BufferedInputStream
import java.io.File
import java.io.FileOutputStream
import java.lang.Exception
import java.net.URL

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class ImageViewFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null

    var progressDialog: ProgressDialog? = null
    private var filterAdjuster: GPUImageFilterTools.FilterAdjuster? = null
    private lateinit var gpuImageView: GPUImageView
    private lateinit var seekBar: SeekBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as MainActivity).hideBottomMenu()
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    private lateinit var root: View
    var isWallpaper = false
    lateinit var loadBitmap: Bitmap
    private lateinit var filterImageAdapter: FilterImageAdapter
    var type: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.fragment_image_view, container, false)

        val imageDao = AppDatabase.getDatabase(root.context).imageDao()

        progressDialog = ProgressDialog(root.context)
        progressDialog?.setMessage("Loading...")
        progressDialog?.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL)
        progressDialog?.isIndeterminate = true

        val photo = arguments?.getSerializable("photo") as Photo
        type = arguments?.getInt("type", 0)!!
        gpuImageView = root.image
        seekBar = root.seekbar

//        loadBitmap = gpuImageView.gpuImage.bitmapWithFilterApplied
        var uri = Uri.parse(photo.src?.medium)
        gpuImageView.setImage(uri)

        root.back_item_id.setOnClickListener {
            if (isWallpaper) {
                root.layout2.visibility = View.VISIBLE
                root.layout3.visibility = View.GONE
                isWallpaper = false
            } else {
                Navigation.findNavController(root).popBackStack()
            }
        }

        root.share_item_id.setOnClickListener {
            loadBitmap = gpuImageView.gpuImage.bitmapWithFilterApplied
            val file = File(
                activity?.externalCacheDir,
                System.currentTimeMillis().toString() + ".jpg"
            )
            val out = FileOutputStream(file)
            loadBitmap.compress(Bitmap.CompressFormat.JPEG, 100, out)
            out.close()
            val bmpUri = Uri.fromFile(file)
            val shareIntent = Intent()
            shareIntent.action = Intent.ACTION_SEND
            shareIntent.putExtra(Intent.EXTRA_STREAM, bmpUri)
            shareIntent.type = "image/jpeg"
            startActivity(Intent.createChooser(shareIntent, "Share"))
        }

        root.info_item_id.setOnClickListener {
            val bottomSheet = BottomSheetDialog(root.context, R.style.BottomSheetDialogTheme)
            val view = layoutInflater.inflate(R.layout.bottom_sheet, null, false)
            view.tv_author.text = photo.photographer
            view.img_data.text = "${photo.width}x${photo.height}"
            bottomSheet.setContentView(view)

            bottomSheet.setOnShowListener {
                root.layout2.visibility = View.INVISIBLE
            }

            bottomSheet.setOnDismissListener {
                root.layout2.visibility = View.VISIBLE
            }
            bottomSheet.show()
        }

        root.download_btn.setOnClickListener {

            Dexter.withContext(root.context)
                .withPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .withListener(object : PermissionListener {
                    override fun onPermissionGranted(p0: PermissionGrantedResponse?) {
                        //   AsyncTask
//                        FileAsyncTask().execute(photo.src.medium)
                        progressDialog?.show()

                        //  PRDownload
                        val config: PRDownloaderConfig = PRDownloaderConfig.newBuilder()
                            .setReadTimeout(30000)
                            .setConnectTimeout(30000)
                            .build()
                        PRDownloader.initialize(root.context, config)

                        PRDownloader.download(
                            photo.src?.original,
                            Environment.getExternalStorageDirectory().absolutePath,
                            "${System.currentTimeMillis()}.jpg"
                        )
                            .build().setOnStartOrResumeListener(object : OnStartOrResumeListener {
                                override fun onStartOrResume() {

                                }
                            }).start(object : OnDownloadListener {
                                override fun onDownloadComplete() {
                                    progressDialog?.hide()
                                }

                                override fun onError(error: Error?) {
                                    Toast.makeText(
                                        root.context,
                                        "${error?.serverErrorMessage}",
                                        Toast.LENGTH_SHORT
                                    )
                                        .show()
                                }

                            })

//                          RxDownloader
//                        RxDownloadRecorder.getTask(photo.src.medium)
//                            .observeOn(AndroidSchedulers.mainThread())
//                            .subscribe {
//                                val status = it.status
//                                when (status) {
//                                    is Completed -> {
//                                        Toast.makeText(root.context, "Success", Toast.LENGTH_SHORT)
//                                            .show()
//                                        progressDialog?.hide()
//                                    }
//                                }
//                            }
//                        RxDownloadRecorder?.startAll()
                    }

                    override fun onPermissionDenied(p0: PermissionDeniedResponse?) {

                    }

                    override fun onPermissionRationaleShouldBeShown(
                        p0: PermissionRequest?,
                        p1: PermissionToken?
                    ) {
                        p1?.continuePermissionRequest()
                    }

                }).check()

        }

        root.wallpaper_btn.setOnClickListener {
            isWallpaper = true
            root.layout2.visibility = View.GONE
            root.layout3.visibility = View.VISIBLE

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                root.lock_btn.visibility = View.VISIBLE
                root.home_lock_btn.visibility = View.VISIBLE
            } else {
                root.lock_btn.visibility = View.GONE
                root.home_lock_btn.visibility = View.GONE
            }
        }

        val manager = WallpaperManager.getInstance(root.context)

        root.home_w_btn.setOnClickListener {
            loadBitmap = gpuImageView.gpuImage.bitmapWithFilterApplied
            manager.setBitmap(loadBitmap)
        }

        root.lock_btn.setOnClickListener {
            loadBitmap = gpuImageView.gpuImage.bitmapWithFilterApplied
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                manager.setBitmap(loadBitmap, null, true, WallpaperManager.FLAG_LOCK)
            }
        }

        root.home_lock_btn.setOnClickListener {
            loadBitmap = gpuImageView.gpuImage.bitmapWithFilterApplied
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                manager.setBitmap(loadBitmap, null, true, WallpaperManager.FLAG_LOCK)
                manager.setBitmap(loadBitmap)
            }
        }

        var imageEntity = imageDao.getImageById(photo.id!!)
        if (imageEntity != null) {
            root.like.isLiked = true
        }

        root.like.setOnLikeListener(object : OnLikeListener {
            override fun liked(likeButton: LikeButton?) {
                imageEntity = ImageEntity()
                imageEntity.id = photo.id
                imageEntity.imgUrl = photo.src?.medium
                imageEntity.author = photo.photographer
                imageDao.insertImage(imageEntity)
            }

            override fun unLiked(likeButton: LikeButton?) {
                imageDao.deleteImage(imageEntity)
            }

        })
        filterImageAdapter = FilterImageAdapter(object : FilterImageAdapter.OnItemClickListener {
            override fun onItemClick(gpuImageFilter: GPUImageFilter) {
                switchFilterTo(gpuImageFilter)
                gpuImageView.requestRender()
            }

        })

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                filterAdjuster?.adjust(p1)
                gpuImageView.requestRender()
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {

            }

        })
        root.filter_rv.layoutManager =
            LinearLayoutManager(root.context, LinearLayoutManager.HORIZONTAL, false)
        root.filter_rv.adapter = filterImageAdapter


        root.filter_btn.setOnClickListener {
            root.layout1.visibility = View.GONE
            root.layout2.visibility = View.GONE
            root.layout4.visibility = View.VISIBLE
            root.layout5.visibility = View.VISIBLE
        }

        root.close_filter_btn.setOnClickListener {
            root.layout1.visibility = View.VISIBLE
            root.layout2.visibility = View.VISIBLE
            root.layout4.visibility = View.GONE
            root.layout5.visibility = View.GONE
        }

        root.checked_filter_btn.setOnClickListener {
            root.layout1.visibility = View.VISIBLE
            root.layout2.visibility = View.VISIBLE
            root.layout4.visibility = View.GONE
            root.layout5.visibility = View.GONE
        }

        return root
    }

    inner class FileAsyncTask : AsyncTask<String, Void, Void>() {

        override fun onPreExecute() {
            super.onPreExecute()
            progressDialog?.show()

        }

        override fun doInBackground(vararg p0: String?): Void? {
            try {
                val filePath = Environment.getExternalStorageDirectory().absolutePath
                val url = URL(p0[0])
                val openConnection = url.openConnection()
                openConnection.connect()
                val inputStream = openConnection.getInputStream()

                val fileOutputStream =
                    FileOutputStream(filePath + "/" + System.currentTimeMillis() + ".jpg")
                var byteArray = ByteArray(8192)

                while (true) {
                    val read = inputStream.read(byteArray)
                    if (read == -1) {
                        break
                    }
                    fileOutputStream.write(byteArray, 0, read)
                }
                fileOutputStream.flush()
                fileOutputStream.close()
                inputStream.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return null
        }

        override fun onPostExecute(result: Void?) {
            super.onPostExecute(result)
            progressDialog?.hide()
        }
    }

    private fun switchFilterTo(filter: GPUImageFilter) {
        if (gpuImageView.filter == null || gpuImageView.filter.javaClass != filter.javaClass) {
            gpuImageView.filter = filter
            filterAdjuster = GPUImageFilterTools.FilterAdjuster(filter)
            if (filterAdjuster!!.canAdjust()) {
                seekBar.visibility = View.VISIBLE
                filterAdjuster!!.adjust(seekBar.progress)
            } else {
                seekBar.visibility = View.INVISIBLE
            }
        }
    }


    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ImageViewFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).hideBottomMenu()
        (activity as MainActivity).supportActionBar?.hide()
    }

    override fun onStop() {
        super.onStop()
        if (type == 0) {
            (activity as MainActivity).showBottomMenu()
            (activity as MainActivity).supportActionBar?.show()
        } else {
            (activity as MainActivity).hideBottomMenu()
            (activity as MainActivity).supportActionBar?.hide()
        }
    }
}