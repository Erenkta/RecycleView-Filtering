package com.example.recycleviewfiltering

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList

class RvAdapter(var arrList:ArrayList<String>):RecyclerView.Adapter<RvHolder>(),Filterable{
    val filteredArrList = ArrayList<String>()
    init{ //Bu hepsinden önce çağırılıyor
        filteredArrList.addAll(arrList)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RvHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false)
        return RvHolder(view)
    }

    override fun getItemCount(): Int {
        return filteredArrList.size
    }

    override fun onBindViewHolder(holder: RvHolder, position: Int) {
        holder.myText.text = filteredArrList.get(position).toString()
    }

    override fun getFilter(): Filter {
        return object : Filter(){
            override fun performFiltering(constraint: CharSequence?): FilterResults { //Filtreleme
                val aramaMetni = constraint.toString() //Bu parametre filtre metnini aldi
                filteredArrList.clear() //Boş gözüktürdük
                if(aramaMetni.isEmpty()){
                    filteredArrList.addAll(arrList) //Arama temizlenmiş ya da bir şey aratmamış ondan hepsi gösteriliyor
                }
                else{
                    for(text in arrList){
                        if(text.toString().toLowerCase(Locale.ROOT).contains(aramaMetni.toString().toLowerCase(Locale.ROOT))) //Eğer ikisi aynı ise demek Locale.Root ise cihazın dilini kullan
                        {
                            filteredArrList.add(text) //Bunu göster
                        }
                    }

                }
                val filterResult = FilterResults()
                filterResult.values = filteredArrList
                return filterResult //perform filtering bir FilterResult döndürüyordu
                //Biz de bir filter result oluşturduk bunun değerini listeye eşitleyip döndürdük
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) { //Sonuç
                notifyDataSetChanged() //Değişiklik var mı farket dedik
            }

        }
    }
}