package com.example.mycalculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mycalculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding //onCreate()에서 초기화 하기 위해 lateinit 사용

    //변수 선언 및 초기화
    private var numInput: TextView? = null //입력값 변수 초기화, ?를 붙여서 null을 허용
    private var numResult: TextView? = null //결과값 변수 초기화
    private var numInputCheck = false //입력값이 설정 되었는지 여부를 변수로, 초기값 false
    private var numResultCheck = false //결과값이 설정 되었는지 여부를 변수로, 초기값 false
    private var FirstNum : Double = 0.0 //사용자가 연산 버튼을 클릭 하기 전, 첫 번째로 클릭하는 숫자 십진수 초기화
    private var SecondNum : Double = 0.0 //사용자가 연산 버튼을 클릭 후, 두 번째로 클릭하는 숫자 십진수 초기화
    private var ResultNum : Double = 0.0 //계산 결과 저장하는 변수, 십진수 초기화
    private var Arithmethic : String? = null //사칙 연산하는 연산자 저장 변수, 초기값 null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater) //바인딩 객체를 초기화 함, inflate()는 xml에 있는 view를 객체화 해주는 역할, 객체화 해주는 과정에서 layoutInflater가 필요함
        val view = binding.root
        setContentView(binding.root) //setContentView()는 레이아웃을 inflating 해주는 역할. 기존에는 아래와 같이 R.layout.activity_main으로 넘겨줬다면, viewBinding에서는 root뷰를 넘겨줌
        //setContentView(R.layout.activity_main)

        //숫자 버튼 클릭 이벤트 리스너
        binding.numSeven.setOnClickListener {  }
        binding.numEight.setOnClickListener {  }
        binding.numNine.setOnClickListener {  }
        binding.numFore.setOnClickListener {  }
        binding.numFive.setOnClickListener {  }
        binding.numSix.setOnClickListener {  }
        binding.numOne.setOnClickListener {  }
        binding.numTwo.setOnClickListener {  }
        binding.numThree.setOnClickListener {  }
        binding.numZero.setOnClickListener {  }

        //연산 버튼 클릭 이벤트 리스너
        binding.allDelete.setOnClickListener { //AC 버튼 클릭 시 호출
            numInput?.text = ""
            numResult?.text = ""
            numInputCheck = false
            numResultCheck = false
            FirstNum = 0.0
            SecondNum = 0.0
            Arithmethic = ""
        }
        binding.delete.setOnClickListener {  }
        binding.oneDelete.setOnClickListener {  }
        binding.division.setOnClickListener {  }
        binding.multiply.setOnClickListener {  }
        binding.subtract.setOnClickListener {  }
        binding.plus.setOnClickListener {  }
        binding.dot.setOnClickListener {  }
        binding.equal.setOnClickListener {  }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}