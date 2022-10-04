package com.andrest.loginretrofit

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.andrest.login.Constants
import com.andrest.loginretrofit.databinding.ActivityMainBinding
import com.andrest.loginretrofit.retrofit.LoginService
import com.andrest.loginretrofit.retrofit.UserInfo
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        mBinding.swType.setOnCheckedChangeListener { button, checked ->
            button.text = if (checked) getString(R.string.main_login)
            else getString(R.string.main_register)

            mBinding.btnLogin.text = button.text
        }

        mBinding.btnLogin.setOnClickListener {
            login()
        }
    }

    private fun login() {

        val email = mBinding.etEmail.text.toString().trim()
        val password = mBinding.etPassword.text.toString().trim()

        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(LoginService::class.java)

        lifecycleScope.launch {
            try {
                val result = service.loginUser(UserInfo(email, password))
                updateUI("${Constants.TOKEN_PROPERTY} : ${result}")
            } catch (e: Exception) {
                e.printStackTrace()
                updateUI(getString(R.string.main_error_response))
            }
        }

/*        service.login(UserInfo(email, password)).enqueue(
            object : Callback<LoginResponse>{
                override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                    when(response.code()) {
                        200 -> {
                            val result = response.body()
                            updateUI("${Constants.TOKEN_PROPERTY} : ${result?.token}")
                        }
                        400 -> { updateUI(getString(R.string.main_error_server))}
                        else -> {updateUI(getString(R.string.main_error_response))}
                    }

                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    Log.e("Retrofit","Problemas con el servidor.")
                    updateUI(getString(R.string.main_error_server))
                }
            }
        )*/



    }

    private fun updateUI(result: String) {
        mBinding.tvResult.visibility = View.VISIBLE
        mBinding.tvResult.text = result
    }
}