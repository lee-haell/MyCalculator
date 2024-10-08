package com.example.mycalculator

import android.os.Bundle
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
        binding = ActivityMainBinding.inflate(layoutInflater) //바인딩 객체를 초기화 함, inflate()는 xml에 있는 view를 객체화 해주는 역할, 객체화 해주는 과정에서 layoutInflater가 필요함
        val view = binding.root
        setContentView(binding.root) //setContentView()는 레이아웃을 inflating 해주는 역할. 기존에는 아래와 같이 R.layout.activity_main으로 넘겨줬다면, viewBinding에서는 root뷰를 넘겨줌

        //TextView 초기화
        numInput = binding.numInput
        numResult = binding.numResult

        //숫자 버튼 클릭 이벤트 리스너
        binding.numOne.setOnClickListener { appendNumber("1") }
        binding.numTwo.setOnClickListener { appendNumber("2") }
        binding.numThree.setOnClickListener { appendNumber("3") }
        binding.numFore.setOnClickListener { appendNumber("4") }
        binding.numFive.setOnClickListener { appendNumber("5") }
        binding.numSix.setOnClickListener { appendNumber("6") }
        binding.numSeven.setOnClickListener { appendNumber("7") }
        binding.numEight.setOnClickListener { appendNumber("8") }
        binding.numNine.setOnClickListener { appendNumber("9") }
        binding.numZero.setOnClickListener { appendNumber("0") }

        //연산 버튼 클릭 이벤트 리스너
        binding.allClear.setOnClickListener { allClearClick() } //AC 버튼 클릭 시 호출
        binding.clear.setOnClickListener { clearClick() } //입력값의 모든 값 삭제
        binding.backClear.setOnClickListener { backClearClick() } //입력값의 값 하나씩 삭제
        binding.division.setOnClickListener { operatorClick("/") } //연산 나누기
        binding.multiply.setOnClickListener { operatorClick("*") } //연산 곱하기
        binding.minus.setOnClickListener { operatorClick("-") } //연산 빼기
        binding.plus.setOnClickListener { operatorClick("+") } //연산 더하기
        binding.dot.setOnClickListener { appendNumber(".") } //소수점
        binding.equal.setOnClickListener { calculateResult() } //등호

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    /**
     * 숫자 버튼 클릭 시 호출 되는 메서드
     * 연산자 설정 여부로 첫 번째 or 두 번째 숫자로 인식
     */
    private fun appendNumber(number: String) {
        if(Arithmethic == null) { //연산자가 설정 안 됐을 때
            if(numResultCheck) {
                numResult?.append(number)
            } else {
                numResult?.text = number
                numResultCheck = true
            }
        } else { //연산자가 설정 됐을 때
            if(numInputCheck) {
                numResult?.append(number)
            } else {
                numResult?.text = number
                numResultCheck = true
            }
        }
    }

    //AC 버튼 클릭 시 호출 되는 메서드
    private fun allClearClick() {
        numInput?.text = "" //입력값 텍스트 비움
        numResult?.text = "" //결과값 텍스트 비움
        numInputCheck = false //입력값이 설정되지 않았음을 표시하는 플래그를 false로 설정
        numResultCheck = false //결과값이 설정되지 않았음을 표시하는 플래그를 false로 설정
        FirstNum = 0.0 //첫 번째 숫자 초기화
        SecondNum = 0.0 //두 번째 숫자 초기화
        Arithmethic = "" //연산자 초기화
    }

    //C 버튼 클릭 시 호출 되는 메서드
    private fun clearClick() {
        numInput?.text = ""
        numInputCheck = false
        SecondNum = 0.0
    }

    //마지막 문자 삭제 버튼('x') 클릭 시 호출 되는 메서드
    //------마지막 문자가 연산자 일 때는?-------
    private fun backClearClick() {
        if(numInput?.text.toString().isNotEmpty()) {
            numInput?.text = numInput?.text?.dropLast(1)
        }
    }

    private fun operatorClick(operator: String) {
        if(numResultCheck) {
            FirstNum = numResult?.text.toString().toDouble()
            Arithmethic = operator
            numResult?.text = FirstNum.toString()
            numInput?.text = FirstNum.toString() + Arithmethic
            numResultCheck = false
        } else if(numInputCheck) {
            FirstNum = numInput?.text.toString().toDouble()
            Arithmethic = operator
            numInput?.text = FirstNum.toString() + Arithmethic
            numResult?.text = ""
            numInputCheck = true
        }
    }

    private fun calculateResult() {
        if(numInputCheck) {
            SecondNum = numInput?.text.toString().toDouble()
            when(Arithmethic) {
                "/" -> ResultNum = FirstNum / SecondNum
                "*" -> ResultNum = FirstNum * SecondNum
                "-" -> ResultNum = FirstNum - SecondNum
                "+" -> ResultNum = FirstNum + SecondNum
            }
            numResult?.text = ResultNum.toString()
            numResultCheck = true
            numInput?.text = ""
            numInputCheck = false
        }
    }
}