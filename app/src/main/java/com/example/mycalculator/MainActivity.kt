package com.example.mycalculator

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mycalculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    //onCreate()에서 초기화 하기 위해 lateinit 사용

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        //바인딩 객체를 초기화 함, inflate()는 xml에 있는 view를 객체화 해주는 역할, 객체화 해주는 과정에서 layoutInflater가 필요함
        val view = binding.root
        setContentView(binding.root)
        //setContentView()는 레이아웃을 inflating 해주는 역할. 기존에는 아래와 같이 R.layout.activity_main으로 넘겨줬다면, viewBinding에서는 root뷰를 넘겨줌
        //setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

    }
}