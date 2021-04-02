package com.example.a4kfullwalpapers.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.a4kfullwalpapers.MainActivity
import com.example.a4kfullwalpapers.R
import com.example.a4kfullwalpapers.adapters.SavedImagesAdapter
import com.example.a4kfullwalpapers.db.AppDatabase
import com.example.a4kfullwalpapers.db.ImageEntity
import com.example.a4kfullwalpapers.models.Photo
import com.example.a4kfullwalpapers.models.Src
import kotlinx.android.synthetic.main.fragment_favorite.view.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class FavoriteFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    private lateinit var root: View
    private lateinit var savedImagesAdapter: SavedImagesAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.fragment_favorite, container, false)
        val imageDao = AppDatabase.getDatabase(root.context).imageDao()
        val allImage = imageDao.getAllImage()
        savedImagesAdapter = SavedImagesAdapter(object : SavedImagesAdapter.OnItemClickListener {
            override fun onItemClick(imageEntity: ImageEntity) {
                var photo = Photo()
                photo.width = imageEntity.width
                 photo.id = imageEntity.id
                photo.height = imageEntity.height
                photo.photographer = imageEntity.author
                var src = Src()
                src.medium = imageEntity.imgUrl!!
                photo.src = src
                val bundle = Bundle()
                bundle.putSerializable("photo", photo)
                Navigation.findNavController(root).navigate(R.id.image_view_fragment, bundle)

            }
        })
        allImage.observe(viewLifecycleOwner, {
            savedImagesAdapter.submitList(it)
        })

        root.rv_favorite.adapter = savedImagesAdapter
        return root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FavoriteFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).showCircle(4)
    }
}