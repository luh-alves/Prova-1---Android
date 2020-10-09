package com.example.prova01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_resultado_calculo_imc.*
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var altura =  findViewById<EditText>(R.id.inputAltura)
        var peso = findViewById<EditText>(R.id.inputPeso)
        val sh = getSharedPreferences("CalcIMG", Context.MODE_PRIVATE)

      btCalcularIMC.setOnClickListener {
          var mensagem =""
            if (peso.text.isNotEmpty() && altura.text.isNotEmpty()) {
                var imc = peso.text.toString().toDouble() / (altura.text.toString().toDouble() * altura.text.toString().toDouble())
                if (imc <17) {
                    mensagem =  imc.toString() + " Muito Abaixo do Peso! "
                } else if (imc >=17 && imc <18.49) {
                    mensagem = imc.toString() + " Abaixo do Peso"
                } else if (imc >=18.50 && imc<24.99){
                    mensagem = imc.toString() + " Peso Ideal"
                } else if (imc >=25.0 && imc<29.99){
                    mensagem = imc.toString() + " Acima do Peso"
                } else if (imc >=30.0 && imc<34.99){
                    mensagem = imc.toString() + " Obesidade I"
                } else if (imc >=35.0 && imc<39.99){
                    mensagem = imc.toString() + " Obesidade II"
                }
                else {
                    mensagem = imc.toString() + "Obesidade III(Mórbida)"
                }
            } else {
                mensagem = "Por favor, preencha todos os campos!"
            }

          val intent= Intent(this, ResultadoCalculoImc::class.java)
          intent.putExtra("mensagem", mensagem)
          startActivity(intent)


        }

        btCadastrarIMC.setOnClickListener { v:View ->
            if (inputPeso.text.isNotEmpty() && inputAltura.text.isNotEmpty()){
                var chave = inputPeso.text.toString()
                var valor = inputAltura.text.toString()
                sh.edit().putString(chave,valor).apply()
                Toast.makeText(this," Salvos Com Sucesso!",Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(this,"Por favor, preencha todos os campos!",Toast.LENGTH_SHORT).show()
            }

        }

        btConsultarIMC.setOnClickListener { v: View ->
            if (inputPeso.text.isNotEmpty()){
                var chave = inputPeso.text.toString()
                var valor = sh.getString(chave, "")


                if(valor.isNullOrEmpty()){
                    Toast.makeText(this," Valores não encontrados!",Toast.LENGTH_SHORT).show()

                }
                else{

                    inputAltura.setText(valor)
                    Toast.makeText(this," Valores encontrados!",Toast.LENGTH_SHORT).show()



                }

            }


        }

        btLimpar.setOnClickListener { v: View ->
            inputAltura.text.clear()
            inputPeso.text.clear()

        }

    }
}