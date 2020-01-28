package br.com.guilhermecunha.imc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.renderscript.Script
import androidx.core.content.ContextCompat
import br.com.guilhermecunha.imc.extensions.format
import br.com.guilhermecunha.imc.extensions.valueDouble
import br.com.guilhermecunha.imc.watchers.DecimalTextWatcher
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etPeso.addTextChangedListener(DecimalTextWatcher(etPeso, totalDecimalNumber = 1))
        etAltura.addTextChangedListener(DecimalTextWatcher(etAltura, totalDecimalNumber = 2))

        btCalcular.setOnClickListener {
            calcular()
        }
    }

    private fun calcular() {
        val peso = etPeso.valueDouble()
        val altura= etAltura.valueDouble()

        val imc = peso / (altura * altura)

        when{
            imc <= 18.5 -> configuraIMC(imc, R.drawable.masc_abaixo, R.string.abaixo_peso)
            imc <= 24.9 -> configuraIMC(imc, R.drawable.masc_ideal, R.string.normal_peso)
            imc <= 29.9 -> configuraIMC(imc, R.drawable.masc_sobre, R.string.acima_peso)
            imc <= 34.9 -> configuraIMC(imc, R.drawable.masc_obeso, R.string.obesidade_grau_i)
            imc <= 39.9 -> configuraIMC(imc, R.drawable.masc_obeso, R.string.obesidade_grau_ii)
            else -> configuraIMC(imc, R.drawable.masc_extremo_obeso, R.string.obesidade_grau_iii)

        }
    }

    private fun configuraIMC(imc: Double, drawableID: Int, stringID: Int) {
        tvIMC.text = getString(R.string.seu_imc, imc.format(2))
        ivIMCStatus.setImageDrawable(
            ContextCompat.getDrawable(this, drawableID)
        )

        tvIMCStatus.text = getString(stringID)
    }
}
