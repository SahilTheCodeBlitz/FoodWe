package com.example.foodwe

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest

class MainActivity : AppCompatActivity(), Adapter.onClicked, MyAdapter.onItemClickedHor {


    val mList = mutableListOf<FoodData>( )

    val nlist = mutableListOf<FoodData1>()

    lateinit var adapter:MyAdapter

    lateinit var adapVer:Adapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val searchView = findViewById<SearchView>(R.id.searchView)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                fetchDataForQuery(query)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                // Handle query text change (e.g., filter search results)
                return true
            }
        })

        fetch()
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewHorizontal)
        val layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        adapter = MyAdapter(this)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter=adapter

        fetchDataVer()
        val recyclerViewVer =findViewById<RecyclerView>(R.id.recyclerViewVertical)
        val layoutManagers = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        adapVer=Adapter(this)
        recyclerViewVer.layoutManager = layoutManagers
        recyclerViewVer.adapter = adapVer
    }

        fun fetch(){
            val api ="68870e431bf547e28f92b5d1e1e7769b"
            val url ="https://api.spoonacular.com/recipes/random?apiKey=$api"

            val u  = "https://api.spoonacular.com/recipes/informationBulk?ids=715538,716429&limit=10&apiKey=$api"

            val l = "https://api.spoonacular.com/recipes/random?number=10&apiKey=$api"

            val jsonObjectRequest = JsonObjectRequest(
                Request.Method.GET, l, null,
                Response.Listener { response ->

                    val arr = response.getJSONArray("recipes")

                    for (i in  0 until arr.length()){

                        val obj = arr.getJSONObject(i)

                        val id = obj.getInt("id")
                        val img = obj.getString("image")
                        val source  = obj.getString("spoonacularSourceUrl")

                        val dataA = FoodData(id,img,source)

                        mList.add(dataA)

                        Log.d("dataadd","$id")
                    }
                    adapter.updateNews(mList)


                },
                Response.ErrorListener { error ->
                    Log.d("errorV","error in loading the data")

                }
            )
            // Access the RequestQueue through your singleton class.
            MySingelton.getInstance(this).addToRequestQueue(jsonObjectRequest)
    }

    fun fetchDataVer(){
        val api ="68870e431bf547e28f92b5d1e1e7769b"
        val url ="https://api.spoonacular.com/recipes/random?apiKey=$api"

        val u  = "https://api.spoonacular.com/recipes/informationBulk?ids=715538,716429&limit=10&apiKey=$api"

        val l = "https://api.spoonacular.com/recipes/random?number=11&apiKey=$api"

        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, l, null,
            Response.Listener { response ->

                val arr = response.getJSONArray("recipes")

                for (i in  0 until arr.length()){

                    val obj = arr.getJSONObject(i)

                    val id = obj.getInt("id")
                    val img = obj.getString("image")
                    val title = obj.getString("title")
                    val source  = obj.getString("spoonacularSourceUrl")

                    val dataA = FoodData1(title,img,source,id)

                    nlist.add(dataA)

                    Log.d("dataver","$id")
                }
                adapVer.updateData(nlist)


            },
            Response.ErrorListener { error ->
                Log.d("errorV","error in loading the data")

            }
        )
        // Access the RequestQueue through your singleton class.
        MySingelton.getInstance(this).addToRequestQueue(jsonObjectRequest)
    }

    override fun onItemClicked(item:FoodData1) {
        val url = item.source // content
        val intent = CustomTabsIntent.Builder()
            .build()
        intent.launchUrl(this@MainActivity, Uri.parse(url))
    }

    override fun onItemClickKelyavar(item: FoodData) {
            val url = item.source
            val intent = CustomTabsIntent.Builder().build()
            intent.launchUrl(this@MainActivity,Uri.parse(url))
        }

    fun fetchDataForQuery(quer:String){
        val url = "https://api.spoonacular.com/recipes/complexSearch?query=$quer"
    }

}