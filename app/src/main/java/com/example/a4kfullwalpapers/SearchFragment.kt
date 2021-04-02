package com.example.a4kfullwalpapers

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.example.a4kfullwalpapers.adapters.ImageListPagingAdapter
import com.example.a4kfullwalpapers.models.Photo
import com.example.a4kfullwalpapers.viewModel.ImageViewModel
import kotlinx.android.synthetic.main.fragment_search.view.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class SearchFragment : Fragment() {

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
    lateinit var imageViewModel: ImageViewModel
    lateinit var imageListPagingAdapter: ImageListPagingAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.fragment_search, container, false)
        imageViewModel = ViewModelProviders.of(this)[ImageViewModel::class.java]
        imageListPagingAdapter =
            ImageListPagingAdapter(object : ImageListPagingAdapter.OnImageItemClickListener {
                override fun onImageItemClick(photo: Photo) {
                    val bundle = Bundle()
                    bundle.putInt("type", 1)
                    bundle.putSerializable("photo", photo)
                    Navigation.findNavController(root).navigate(R.id.image_view_fragment, bundle)
                }
            })
        root.search_edit.addTextChangedListener {
            val searchText = it.toString()
            imageViewModel.getImagePagedList(1, searchText)
                .observe(viewLifecycleOwner, Observer { t ->
                    imageListPagingAdapter.submitList(t)
                })
        }
        root.search_rv.adapter = imageListPagingAdapter
        root.close_btn.setOnClickListener {
            if (root.search_edit.text.toString() != "") {
                root.search_edit.setText("")
            } else {
                Navigation.findNavController(root).popBackStack()
                (activity as MainActivity).showBottomMenu()
                (activity as MainActivity).supportActionBar?.show()
            }
        }

        return root
    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).hideBottomMenu()
        (activity as MainActivity).supportActionBar?.hide()
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SearchFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onStop() {
        super.onStop()
        hideSoftKeyboard(root.search_edit)
    }

    override fun onDestroy() {
        super.onDestroy()
        (activity as MainActivity).showBottomMenu()
        (activity as MainActivity).supportActionBar?.show()
    }

    private fun hideSoftKeyboard(searchEdit: EditText) {
        val imm: InputMethodManager? =
            root.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
        imm?.hideSoftInputFromWindow(searchEdit.getWindowToken(), 0)
    }
}