package rr.chrd.xosothuathienhue.tips.adapter

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import rr.chrd.xosothuathienhue.databinding.TipsviewBinding
import rr.chrd.xosothuathienhue.tips.model.TipsInterface
import rr.chrd.xosothuathienhue.tips.model.TipsModel

class TipsAdapter(val listener : TipsInterface): RecyclerView.Adapter<TipsAdapter.AdapterHolder>() {
    class AdapterHolder (val adpts : TipsviewBinding): RecyclerView.ViewHolder(adpts.root)
    private var tipsList = emptyList<TipsModel>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterHolder = AdapterHolder(
        TipsviewBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    override fun onBindViewHolder(holder: AdapterHolder, position: Int) {
        with(holder){
            with(tipsList[position]){
                adpts.tipsTitle.text = this.tipsT
                adpts.tipsDesc.text = this.tipsD
                adpts.tipsDesc.ellipsize = TextUtils.TruncateAt.MARQUEE
                adpts.tipsDesc.isSelected = true
                adpts.tipsCardView.setOnClickListener {
                    listener.onClickitem(this)
                }
            }
        }
    }
    override fun getItemCount(): Int {
     return tipsList.size
    }

    fun setAdapter (setAdapt : List<TipsModel>){
        this.tipsList = setAdapt
    }
}