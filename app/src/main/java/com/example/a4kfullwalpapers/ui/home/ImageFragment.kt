package com.example.a4kfullwalpapers.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.example.a4kfullwalpapers.R
import com.example.a4kfullwalpapers.adapters.ImageListPagingAdapter
import com.example.a4kfullwalpapers.models.Photo
import com.example.a4kfullwalpapers.viewModel.ImageViewModel
import kotlinx.android.synthetic.main.fragment_image.view.*

private const val ARG_PARAM1 = "param1"

class ImageFragment : Fragment() {

    private var param1: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
        }
    }

    private lateinit var imageViewModel: ImageViewModel
    private lateinit var root: View
    private lateinit var imageListPagingAdapter: ImageListPagingAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.fragment_image, container, false)
        imageViewModel = ViewModelProviders.of(this)[ImageViewModel::class.java]
        imageListPagingAdapter = ImageListPagingAdapter(object :
            ImageListPagingAdapter.OnImageItemClickListener {
            override fun onImageItemClick(photo: Photo) {
                val bundle = Bundle()
                bundle.putSerializable("photo", photo)
                Navigation.findNavController(root).navigate(R.id.image_view_fragment, bundle)
            }
        })
        imageViewModel.getImagePagedList(1, param1!!).observe(viewLifecycleOwner, Observer {
            imageListPagingAdapter.submitList(it)
        })
        root.rv.adapter = imageListPagingAdapter
        return root
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String) =
            ImageFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                }
            }
    }
}