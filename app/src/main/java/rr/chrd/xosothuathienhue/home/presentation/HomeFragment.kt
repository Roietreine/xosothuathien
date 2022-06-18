package rr.chrd.xosothuathienhue.home.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import rr.chrd.xosothuathienhue.R
import rr.chrd.xosothuathienhue.databinding.FragmentHomeBinding
import rr.chrd.xosothuathienhue.home.adapter.HomeAdapter


class HomeFragment : Fragment() {

    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private var homeData = HomeViewModel()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentHomeBinding.inflate(layoutInflater,container,false)
        val rootView = binding.root
        homeData = ViewModelProvider(this)[HomeViewModel::class.java]

        homeData.homeFun()
        val adpts = HomeAdapter()
        homeData.hmdtls.observe(viewLifecycleOwner){
            if (it != null){
                adpts.setAdapter(it)
                binding.homeRecycler.apply {
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
            findNavController().navigate(R.id.action_homeFragment_to_mainFragment)
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}