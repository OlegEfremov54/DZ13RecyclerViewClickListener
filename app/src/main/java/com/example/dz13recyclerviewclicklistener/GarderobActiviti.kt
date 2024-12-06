package com.example.dz13recyclerviewclicklistener

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class GarderobActiviti : AppCompatActivity() {
    private lateinit var toolbarGA:Toolbar
    private lateinit var recyclerViewRV: RecyclerView

    private val garderobVal = mutableListOf(
        Garderob("Боты", "Классические, унисекс, коричневые, из натуральной кожи", R.drawable.boti),
        Garderob("Брюки", "Класические, синие, с карманами", R.drawable.bruki),
        Garderob("Кросовки", "Тканевые, серого цвета ", R.drawable.crosovki),
        Garderob("Костюм", "Шерстяная ткань, стального цвета, с галстуком синим", R.drawable.kostum),
        Garderob("Носки", "Набор носков белого, черого и черного цвета", R.drawable.noski),
        Garderob("Плащ", "Кремовый плащ, с поясом", R.drawable.plasch),
        Garderob("Ремень", "Кожаный ремень с металической пряжкой", R.drawable.remen),
        Garderob("Кросовки", "Спортивные, лёгкие, летние, Nike", R.drawable.crosovki),
        Garderob("Шапка", "Вязаная шапочка разноцветная", R.drawable.shapka),
        Garderob("Брюки", "Бежевые, классические, мужские", R.drawable.bruki),
        Garderob("Шарф", "Вязаный шарф, шерсть", R.drawable.sharf),
        Garderob("Шляпа", "Гангстерская шляпа а ля Алькапоне", R.drawable.shlapa),
        Garderob("Шуба", "Светлый мех, норка, спинки", R.drawable.shuba),
        Garderob("Рубашка", "Ночная сорочка трикотажная", R.drawable.sorochka),
        Garderob("Рубашка", "Белая в чёрную полосочку", R.drawable.rubw),
        Garderob("Юбка", "Плисерованная юбка темного цвета с белой каемочкой", R.drawable.ubka),
        Garderob("Шорты", "Чёрные, спортивные, подходят для занятия спортом", R.drawable.shortblec),
        Garderob("Шорты", "Красные, быстросохнущие, подходят для плавания", R.drawable.shortred),
        Garderob("Шорты", "Голубые, мягкие для домашнего ношения", R.drawable.shortblo),
        Garderob("Футболка", "Зелёная, однотонная, х/б 100%", R.drawable.futgreen),
        Garderob("Футболка", "Красная, однотонная, унисекс х/б 80%, латекс 20%", R.drawable.futred),
        Garderob("Футболка", "Белая, однотонная, х/б 100%", R.drawable.futwit)
    )




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_garderob_activiti)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //Тулбар
        toolbarGA = findViewById(R.id.toolbarGA)
        setSupportActionBar(toolbarGA)
        title = "  Мой гардероб"
        toolbarGA.subtitle = "Версия 1."
        toolbarGA.setLogo(R.drawable.baseline_dry_cleaning_24)


        //Запускае менеджер
        recyclerViewRV = findViewById(R.id.recyclerViewRV)
        recyclerViewRV.layoutManager = LinearLayoutManager(this)
        recyclerViewRV.adapter = MyAdapter(garderobVal)

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
                    applicationContext, "Автор Ефремов О.В. Создан 5.12.2024",
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