package com.example.alfitra_uas_mobile2.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.alfitra_uas_mobile2.R
import com.example.alfitra_uas_mobile2.TVAdapter
import com.example.alfitra_uas_mobile2.models.TV
import com.example.alfitra_uas_mobile2.models.TVResponse
import com.example.alfitra_uas_mobile2.services.TVApiInterface
import com.example.alfitra_uas_mobile2.services.TVApiService
import kotlinx.android.synthetic.main.fragment_explore.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ExploreFragment : Fragment() {
    private val tv = arrayListOf<TV>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_explore, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv_tv_list.layoutManager = LinearLayoutManager(this.context)
        rv_tv_list.setHasFixedSize(true)
        getTVData { tv : List<TV> ->
            rv_tv_list.adapter = TVAdapter(tv)
        }
        showRecyclerView()
    }

    private fun getTVData(callback: (List<TV>) -> Unit){
        val apiService = TVApiService.getInstance().create(TVApiInterface::class.java)
        apiService.getTVList().enqueue(object : Callback<TVResponse> {
            override fun onFailure(call: Call<TVResponse>, t: Throwable) {

            }

            override fun onResponse(call: Call<TVResponse>, response: Response<TVResponse>) {
                return callback(response.body()!!.tv)
            }

        })
    }

    private fun showRecyclerView() {
        rv_tv_list.layoutManager = LinearLayoutManager(this.context)
        rv_tv_list.adapter = TVAdapter(tv)
    }

}