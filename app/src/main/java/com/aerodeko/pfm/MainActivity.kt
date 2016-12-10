package com.aerodeko.pfm

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import com.aerodeko.pfm.model.EventInteractor

class MainActivity : AppCompatActivity(), EventInteractor.Listener {
    private lateinit var eventInteractor: EventInteractor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        eventInteractor = EventInteractor(this, this)

        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        val incomeButton = findViewById(R.id.income_button) as FloatingActionButton
        incomeButton.setOnClickListener { view ->
            eventInteractor.addIncome()
        }

        val expenseButton = findViewById(R.id.expense_button) as FloatingActionButton
        expenseButton.setOnClickListener { view ->
            eventInteractor.addExpense()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId

        if (id == R.id.action_settings) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onOpenAddIncome() {
        Snackbar.make(findViewById(R.id.income_button), "Income TODO", Snackbar.LENGTH_SHORT).show()
    }

    override fun onOpenAddExpense() {
        Snackbar.make(findViewById(R.id.expense_button), "Expense TODO", Snackbar.LENGTH_SHORT).show()
    }
}
