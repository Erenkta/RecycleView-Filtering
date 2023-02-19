package com.example.recycleviewfiltering

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView.OnQueryTextListener
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recycleviewfiltering.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    lateinit var adapter:RvAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val arrList = arrayListOf<String>()
        adapter = RvAdapter(arrList)
        binding.rcView.adapter = adapter

        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.rcView.layoutManager = layoutManager


        binding.rcView.addItemDecoration(DividerItemDecoration(this,layoutManager.orientation))

        ListeEkle(arrList)

        //Search View İşlemleri
        binding.searchView.setOnQueryTextListener(object :OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean { //Klavyede submite basılınca bir şey olsun istersen buna yaz
                return false //İşlem yok demek
            }

            override fun onQueryTextChange(newText: String?): Boolean { //Bu da text Her değiştiğinde
                adapter.filter.filter(newText)
                return false //İşlem tamamlandı
            }

        })

    }
    fun ListeEkle(list:ArrayList<String>){
        val addList = arrayListOf<String>("Turkey","Japan","Belgian","Russian","Ukraine","Egypt","Canada","Germany")
        list.addAll(addList)
        adapter.filter.filter(binding.searchView.query)
    }

}