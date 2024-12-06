package com.example.dz13recyclerviewclicklistener

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DetailActivity : AppCompatActivity() {
    private var garderobs: Garderob? = null
    private var position: Int = 0
    private lateinit var layoutLL: LinearLayout
    private lateinit var toolbarGA: Toolbar
    private lateinit var imageGarderobIV: ImageView
    private lateinit var editNameTV: TextView
    private lateinit var editDescriptionTV: TextView


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        layoutLL = findViewById(R.id.mainGA)

        imageGarderobIV = findViewById(R.id.imageGarderobIV)
        editNameTV = findViewById(R.id.editNameTV)
        editDescriptionTV = findViewById(R.id.editDescriptionTV)

        //Тулбар
        toolbarGA = findViewById(R.id.toolbarGA)
        setSupportActionBar(toolbarGA)
        title = "  Мой гардероб"
        toolbarGA.subtitle = "Страница элемента"

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        toolbarGA.setNavigationOnClickListener { onBackPressed() }


        if (intent.hasExtra("garderob")) {
            garderobs = intent.getSerializableExtra("garderob") as Garderob
        }
        if (intent.hasExtra("position")) {
            position = intent.getIntExtra("position", 0)
        }

        if (garderobs != null) {
            editNameTV.text = garderobs!!.name
            editDescriptionTV.text = garderobs!!.description
            imageGarderobIV.setImageResource(garderobs!!.image)
        }

        layoutLL.setOnLongClickListener( View.OnLongClickListener {
            val dialog = AlertDialog.Builder(this)
            val inflater = this.layoutInflater
            val dialogView = inflater.inflate(R.layout.update_dialog, null)
            dialog.setView(dialogView)

            val editName = dialogView.findViewById(R.id.updateNameET) as EditText
            val editDescription = dialogView.findViewById(R.id.updateDescriptionET) as EditText

            dialog.setTitle("Внести изменения в запись")
            dialog.setMessage("Отредактируйте данные: ")
            dialog.setPositiveButton("Обновить") { _, _ ->
                editNameTV.text = editName.text.toString()
                editDescriptionTV.text = editDescription.text.toString()
                garderobs = Garderob(
                    editNameTV.text.toString(),
                    editDescriptionTV.text.toString(),
                    garderobs!!.image
                )
            }
            dialog.setNegativeButton("Отмена") { _, _ -> }
            dialog.create().show()
            false
        })


    }

    override fun onBackPressed() {
        super.onBackPressed()

        val intent = Intent(this, GarderobActiviti::class.java)
        intent.putExtra("garderob", garderobs)
        intent.putExtra("position", position)
        startActivity(intent)
        finish()
    }

    //Инициализация Меню
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.infoMenuMain -> {
                Toast.makeText(
                    applicationContext, "Автор Ефремов О.В. Создан 6.12.2024",
                    Toast.LENGTH_LONG
                ).show()
            }

            R.id.exitMenuMain -> {
                Toast.makeText(
                    applicationContext, "Работа приложения завершена",
                    Toast.LENGTH_LONG
                ).show()
                finishAffinity()
            }
        }
        return super.onOptionsItemSelected(item)
    }

}