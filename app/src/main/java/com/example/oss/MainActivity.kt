package com.example.oss

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.view.Window
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import java.lang.Exception
import java.lang.reflect.Type
import kotlin.math.pow

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    val VERSION = "1.1"

    var mode = 0
    var ans: Long = 0

    private fun factorial(x: Long): Double{
        var f: Double = 1.0
        if (x <= 1) return 1.0

        for (i in 2..x){
            f *= i
        }
        return f
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val nav: NavigationView = findViewById(R.id.nav_view)
        nav.setNavigationItemSelectedListener(this)


        findViewById<TextView>(R.id.editN).inputType = InputType.TYPE_NULL
        findViewById<TextView>(R.id.editK).inputType = InputType.TYPE_NULL
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val drawer = findViewById<DrawerLayout>(R.id.drawler_layout)

        when (item.itemId){
            R.id.item1 -> {
                findViewById<TextView>(R.id.description).setText(R.string.text_cs)
                findViewById<TextView>(R.id.formula).setText(R.string.cs_f)
                mode = 1
            }
            R.id.item2 -> {
                findViewById<TextView>(R.id.description).setText(R.string.text_cr)
                findViewById<TextView>(R.id.formula).setText(R.string.cr_f)
                mode = 2
            }
            R.id.item3 -> {
                findViewById<TextView>(R.id.description).setText(R.string.text_crp)
                findViewById<TextView>(R.id.formula).setText(R.string.crp_f)
                mode = 3
            }
            R.id.item4 -> {
                findViewById<TextView>(R.id.description).setText(R.string.text_csp)
                findViewById<TextView>(R.id.formula).setText(R.string.csp_f)
                mode = 4
            }
            R.id.info -> {
                Toast.makeText(this, "Author:\nKirill Kramarskiy IST-91\nrelease $VERSION", Toast.LENGTH_LONG).show()
                findViewById<TextView>(R.id.description).text = ""
                findViewById<TextView>(R.id.formula).setText(R.string.info)
            }
        }
        findViewById<TextView>(R.id.editN).inputType = InputType.TYPE_CLASS_NUMBER
        findViewById<TextView>(R.id.editK).inputType = InputType.TYPE_CLASS_NUMBER
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onResume() {
        super.onResume()

        val drawer = findViewById<DrawerLayout>(R.id.drawler_layout)
        drawer.openDrawer(GravityCompat.START)

        val ansV: View = findViewById(R.id.ansView)
        val ansTV: TextView = findViewById(R.id.ansTV)
        val clear_image: ImageView = findViewById(R.id.clear_image)

        ansV.setOnClickListener {
            when (mode){
                0 -> Log.d("myLog", "mode: $mode")
                1 -> {
                    try {
                        Log.d("myLog", "mode: $mode")
                        val k = findViewById<TextView>(R.id.editK).text.toString().toLong()
                        val n = findViewById<TextView>(R.id.editN).text.toString().toLong()

                        if (n < k){
                            Toast.makeText(this, "n должна быть больше k", Toast.LENGTH_LONG).show()
                        } else{
                            ans = (factorial(n) / ( factorial(k) * factorial(n - k) )).toLong()

                            ansTV.text = "${n}! / ($k * ( $n - $k )!) \n  -> $ans"}
                    } catch (Ex: Exception) {Log.d("myLog", "$Ex")}
                }
                2 -> {
                    try {
                        Log.d("myLog", "mode: $mode")
                        val k = findViewById<TextView>(R.id.editK).text.toString().toLong()
                        val n = findViewById<TextView>(R.id.editN).text.toString().toLong()

                        if (n < k){
                            Toast.makeText(this, "n должна быть больше k", Toast.LENGTH_LONG).show()
                        } else{
                            ans = (factorial(n) / factorial(n - k)).toLong()

                            ansTV.text = "${n}! / ( $n - $k )! \n -> $ans"}
                    } catch (Ex: Exception) {Log.d("myLog", "$Ex")}
                }
                3 -> {
                    try {
                        Log.d("myLog", "mode: $mode")
                        val k = findViewById<TextView>(R.id.editK).text.toString().toInt()
                        val n = findViewById<TextView>(R.id.editN).text.toString().toDouble()

                        if (n < k){
                            Toast.makeText(this, "n должна быть больше k", Toast.LENGTH_LONG).show()
                        } else{
                            ans = n.pow(k).toLong()

                            ansTV.text = "Решение -> ${n.toLong()} ^ $k \n Ответ -> $ans"}
                    } catch (Ex: Exception) {Log.d("myLog", "$Ex")}
                }
                4 -> {
                    try {
                        Log.d("myLog", "mode: $mode")
                        val k = findViewById<TextView>(R.id.editK).text.toString().toLong()
                        val n = findViewById<TextView>(R.id.editN).text.toString().toLong()

                        if (n < k){
                            Toast.makeText(this, "n должна быть больше k", Toast.LENGTH_LONG).show()
                        } else{
                            ans = ( factorial((n + k - 1) ) / ( factorial(k) * factorial((n - 1)) ) ).toLong()

                            ansTV.text = "Решение -> ($n + $k - 1)! / $k! * ($n - $k)! \n Ответ -> $ans"}
                    } catch (Ex: Exception) {Log.d("myLog", "$Ex")}
                }
            }
        }
        clear_image.setOnClickListener {
            ansTV.text = "ОТВЕТ"
            this.findViewById<TextView>(R.id.editK).text = ""
            this.findViewById<TextView>(R.id.editN).text = ""
        }
    }
}

