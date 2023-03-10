package com.example.kalkulator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import java.math.RoundingMode
import kotlin.math.exp
import kotlin.math.pow
import kotlin.math.sqrt

class MainActivity : AppCompatActivity() {

    var liczba1: String = "" // pierwsza liczba
    var liczba2: String = "" // druga liczba
    var wynik: String = "" // wynik dzialania
    var operator: String = "" // wybrane działanie
    var isOpJustPressed: Boolean = false // informacja czy wlasnie nacisnelismy
                                         // przycisk operatora
                                         // jesli tak to zapisujemy pierwsza liczbe i
                                         // zaczynamy czytac druga liczbe





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClickDigit(view: View) {
        val ow = findViewById<TextView>(R.id.outputWindow)
        if (isOpJustPressed) {
            ow.text = ""
            isOpJustPressed = false
        }
        var tekst:String = ""+ow.text
        if (tekst=="0") tekst="";
        if (tekst=="-0") tekst="-";
        ow.text = tekst+(view as Button).text
    }

    fun onClickOperator(view: View) {
        val ow = findViewById<TextView>(R.id.outputWindow)
        if(operator!=""){
            val liczbatmp = ow.text.toString()
            when (operator) {
                "+" -> wynik = add(liczba1, liczbatmp)
                "-" -> wynik = sub(liczba1, liczbatmp)
                "*" -> wynik = mul(liczba1, liczbatmp)
                "/" -> wynik = div(liczba1, liczbatmp)
                "xʸ" -> wynik = potega(liczba1, liczbatmp)
            }
            liczba1 = wynik

        }
        else{
            liczba1 = ow.text.toString()
        }
        when (view.id) {
            R.id.buttonPlus -> operator = "+"
            R.id.buttonMinus -> operator = "-"
            R.id.buttonMul -> operator = "*"
            R.id.buttonDiv -> operator = "/"
            R.id.buttonPotega -> operator ="xʸ"
        }
        isOpJustPressed = true
    }


    fun onClickEqual(view: View) {
        if(operator!="") {
            val ow = findViewById<TextView>(R.id.outputWindow)
            liczba2 = ow.text.toString()
            when (operator) {
                "+" -> wynik = add(liczba1, liczba2)
                "-" -> wynik = sub(liczba1, liczba2)
                "*" -> wynik = mul(liczba1, liczba2)
                "/" -> wynik = div(liczba1, liczba2)
                "xʸ" -> wynik = potega(liczba1, liczba2)

            }
            ow.text = wynik
            liczba1 = ""
            liczba2 = ""
            operator = ""
            isOpJustPressed = true //zeby 'wyczyscic' ekran po dzialaniu
        }
    }

    fun onClickDot(view: View) {
        val ow = findViewById<TextView>(R.id.outputWindow)
        val tekstbezkropki = ow.text.toString()
        //tekstbezkropki + "."
        if(!tekstbezkropki.contains(".", true)){
            ow.text = tekstbezkropki + "."
        }

    }

    fun onClickPlusMinus(view: View) {
        val ow = findViewById<TextView>(R.id.outputWindow)
        val tekstbezminusa = ow.text.toString()
        //"-" + tekstbezminusa
        if(!tekstbezminusa.contains("-", true)) {
            ow.text = "-" + tekstbezminusa
        }
        if(tekstbezminusa.contains("-", true)) {
            ow.text = tekstbezminusa.substring(1,tekstbezminusa.length)
        }



    }
    //to nie tak ma dzialac
    //juz lepiej ale problem z zerem na poczatku

    fun onClickC(view: View) {
        val ow = findViewById<TextView>(R.id.outputWindow)
        liczba1 = ""
        liczba2 = ""
        operator = ""
        wynik = ""
        ow.text = ""
    }

    fun onClickCE(view: View) {
        val ow = findViewById<TextView>(R.id.outputWindow)
        ow.text = ""
    }



    fun onClickBack(view: View) {
        //ow.text.toString() zczytuje
        val ow = findViewById<TextView>(R.id.outputWindow)
        val stara = ow.text.toString()
        //stara.substring(0,stara.length-1)
        ow.text = stara.substring(0,stara.length-1)
    }


    fun add(l1: String, l2: String):String {
        return (l1.toBigDecimal()+l2.toBigDecimal()).toPlainString()
    }
    fun sub(l1: String, l2: String):String {
        return (l1.toBigDecimal()-l2.toBigDecimal()).toPlainString()
    }
    fun mul(l1: String, l2: String):String {
        return (l1.toBigDecimal()*l2.toBigDecimal()).toPlainString()
    }
    fun div(l1: String, l2: String):String {
        return l1.toBigDecimal().divide(l2.toBigDecimal(),10, RoundingMode.HALF_UP).toPlainString()
    }
    fun potega(l1: String, l2: String):String {
        return (l1.toDouble().pow(l2.toDouble())).toString()
    }

    fun onClickSqrt(view: View) {
        val ow = findViewById<TextView>(R.id.outputWindow)
        val pierw = ow.text.toString()
        ow.text = squareroot(pierw)
    }

    fun squareroot(l1: String):String {
        return sqrt(l1.toDouble()).toString()
    }

    fun onClickExp(view: View) {
        val ow = findViewById<TextView>(R.id.outputWindow)
        val wykladnik = ow.text.toString()
        ow.text = edox(wykladnik)
    }

    fun edox(l1: String):String {
        return exp(l1.toDouble()).toString()
    }

}