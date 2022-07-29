package com.example.cepretrofit

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cepretrofit.data.models.CEP
import com.example.cepretrofit.data.remote.PostmonAPI
import com.example.cepretrofit.data.remote.Rede
import com.example.cepretrofit.databinding.FragmentHomeBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val retrofit = Rede.getRetrofitInstance("https://api.postmon.com.br")
        val endpointPostmon = retrofit.create(PostmonAPI::class.java)

        binding.button.setOnClickListener{
            if(binding.editTextTextPersonName.text.isNotEmpty()){

                val chamada =
                endpointPostmon.getCEPInfo(binding.editTextTextPersonName.text.toString())

                chamada.enqueue(object : Callback<CEP> {
                    override fun onResponse(call: Call<CEP>, response: Response<CEP>) {
                        response.body()?.let { cep ->
                            binding.textView.text = cep.toString()
                        }
                    }

                    override fun onFailure(call: Call<CEP>, t: Throwable) {
                        Log.e("IMPACTA", t.printStackTrace().toString())
                    }

                })



            }
        }
    }

}