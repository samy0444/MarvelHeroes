package com.oyelabs.marvelapp.ui

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.AutoCompleteTextView
import android.widget.ProgressBar
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.oyelabs.marvelapp.R
import com.oyelabs.marvelapp.ui.adapters.CharacterSearchAdapter
import com.oyelabs.marvelapp.ui.adapters.CharactersAdapter
import com.oyelabs.marvelapp.api.APIService
import com.oyelabs.marvelapp.databinding.ActivityMainBinding
import com.oyelabs.marvelapp.models.characters.Result
import com.oyelabs.marvelapp.models.characters.characters
import com.oyelabs.marvelapp.utils.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var characterSearchAdapter: CharacterSearchAdapter
    private lateinit var productSearchBar: AutoCompleteTextView
   var dataList = ArrayList<Result>()
    var ArrayList = ArrayList<Result>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       setContentView(R.layout.activity_main)
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)

        recyclerView = findViewById(R.id.recyclerView)
        progressBar = findViewById(R.id.progressBar)
        progressBar.visibility = View.VISIBLE
        recyclerView.layoutManager = GridLayoutManager(this,2)





        APIService.instance.getData(Constants.ts, Constants.APIKEY, Constants.hash)
            .enqueue(object : Callback<characters> {
                override fun onFailure(call: Call<characters>, t: Throwable) {
                    progressBar.visibility = View.GONE
                }

                override fun onResponse(call: Call<characters>, response: Response<characters>) {


                    // in search adapter
//                   // with(CharSearchBar) { this?.setInputType(InputType.TYPE_TEXT_FLAG_AUTO_COMPLETE) }
//
                    dataList = response.body()!!.data.results as ArrayList<Result>
                    //ArrayList= response.body()!!.data.results as ArrayList<Result>
                    ArrayList.addAll(dataList)

                    recyclerView.adapter =
                        CharactersAdapter(ArrayList, this@MainActivity)
                    progressBar.visibility = View.GONE






                }

            })



    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        val menuItem = menu!!.findItem(R.id.search)


        if(menuItem!=null){
            val searchView = menuItem.actionView as SearchView
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return true;
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    if(newText!!.isNotEmpty()){
                        Log.d("queryyyyyyy",newText)
                        //binding.searchView.clearFocus()
                        ArrayList.clear()
                        val search = newText.toLowerCase(Locale.getDefault())
                        Log.d("searchhhhhh",search)
                        dataList.forEach {
                            if(it.name.toLowerCase(Locale.getDefault()).contains(search)){
                                //ArrayList.clear()
                                Log.d("showwwwwwww",it.name.toLowerCase(Locale.getDefault()))
                                //charAdapter.filter.filter(search)
                                ArrayList.add(it)
                                Log.d("arrayList", ArrayList.toString())
                           }
                        }
                        recyclerView.adapter!!.notifyDataSetChanged()
                    }
                    else{
                        ArrayList.clear()
                        ArrayList.addAll(dataList)
                        recyclerView.adapter!!.notifyDataSetChanged()
                    }
                    return true
                }
            })
        }


        return super.onCreateOptionsMenu(menu)
    }
}