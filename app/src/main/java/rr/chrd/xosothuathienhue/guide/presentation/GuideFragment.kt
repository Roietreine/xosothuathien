package rr.chrd.xosothuathienhue.guide.presentation

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import rr.chrd.xosothuathienhue.R
import rr.chrd.xosothuathienhue.databinding.FragmentGuideBinding
import rr.chrd.xosothuathienhue.guide.adapter.GuideAdapter
import rr.chrd.xosothuathienhue.guide.model.GuideInterface
import rr.chrd.xosothuathienhue.guide.model.GuideModel

class GuideFragment : Fragment(), GuideInterface {

    private var _binding : FragmentGuideBinding? = null
    private  val binding get() = _binding!!
    private var guideData = GuideViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGuideBinding.inflate(inflater,container,false)
        val rootView = (binding.root)
        guideData = ViewModelProvider(this)[GuideViewModel::class.java]
        guideData.guideFun()
        val adpts = GuideAdapter(this)
        guideData.gddtls.observe(viewLifecycleOwner){
            if (it != null){
                adpts.setAdapter(it)
                binding.guideRecycler.apply {
                    setHasFixedSize(true)
                    layoutManager = LinearLayoutManager(context)
                    adapter = adpts
                }
            }
        }
        onBackClick()
        return rootView
    }

    private fun onBackClick() {
        binding.bckButton.setOnClickListener {
            findNavController().navigate(R.id.action_guideFragment_to_mainFragment)
        }
    }

    override fun onItemClick(data: GuideModel) {
        var dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.guide_show_all)
        dialog.setCancelable(true)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val title = dialog.findViewById<TextView>(R.id.guide_title_view_all)
        val desc = dialog.findViewById<TextView>(R.id.guide_desc_view_all)
        title.text = data.guideT
        desc.text = data.guideD
        dialog.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}