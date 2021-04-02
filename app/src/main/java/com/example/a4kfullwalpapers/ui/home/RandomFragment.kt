package com.example.a4kfullwalpapers.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.example.a4kfullwalpapers.MainActivity
import com.example.a4kfullwalpapers.R
import com.example.a4kfullwalpapers.adapters.ImageListPagingAdapter
import com.example.a4kfullwalpapers.models.Photo
import com.example.a4kfullwalpapers.viewModel.ImageViewModel
import kotlinx.android.synthetic.main.fragment_random.view.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class RandomFragment : Fragment() {
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
    private lateinit var imageViewModel: ImageViewModel
    private lateinit var imageListPagingAdapter: ImageListPagingAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.fragment_random, container, false)
        imageViewModel = ViewModelProviders.of(this)[ImageViewModel::class.java]

        imageListPagingAdapter =
            ImageListPagingAdapter(object : ImageListPagingAdapter.OnImageItemClickListener {
                override fun onImageItemClick(photo: Photo) {
                    val bundle = Bundle()
                    bundle.putSerializable("photo", photo)
                    Navigation.findNavController(root)
                        .navigate(R.id.image_view_fragment, bundle)
                }
            })
        imageViewModel.getImagePagedList(3).observe(viewLifecycleOwner,
            {
                imageListPagingAdapter.submitList(it)
            })
        root.rv_random.adapter = imageListPagingAdapter
        return root
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RandomFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
    override fun onResume() {
        super.onResume()
        (activity as MainActivity).showCircle(3)
    }
}