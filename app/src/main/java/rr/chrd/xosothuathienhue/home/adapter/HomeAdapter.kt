package rr.chrd.xosothuathienhue.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import rr.chrd.xosothuathienhue.databinding.HomeviewBinding
import rr.chrd.xosothuathienhue.home.model.HomeModel

class HomeAdapter : RecyclerView.Adapter<HomeAdapter.AdapterHolder>() {

    class AdapterHolder (val adapts: HomeviewBinding): RecyclerView.ViewHolder(adapts.root)
    private var homeList = emptyList<HomeModel>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterHolder = AdapterHolder(
        HomeviewBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    override fun onBindViewHolder(holder: AdapterHolder, position: Int) {
        with(holder){
            with(homeList[position]){
                adapts.homeTitle.text = this.homeT
                adapts.homeDescription.text = this.homeD
            }
        }
    }
    override fun getItemCount(): Int {
        return homeList.size
    }
    fun setAdapter(setAdapt : List<HomeModel>){
        this.homeList = setAdapt
    }
}