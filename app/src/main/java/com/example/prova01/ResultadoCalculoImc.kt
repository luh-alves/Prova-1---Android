package com.example.prova01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_resultado_calculo_imc.*

class ResultadoCalculoImc : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultado_calculo_imc)
        voltar.setOnClickListener {
           finish()
        }

        var resposta = findViewById<EditText>(R.id.resultado)
        var mensagem = intent.getStringExtra("mensagem")
        resposta.setText(mensagem)


    }
}