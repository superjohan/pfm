package com.aerodeko.pfm

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import com.aerodeko.pfm.model.BudgetInteractor
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), BudgetInteractor.Listener {
    private lateinit var budgetInteractor: BudgetInteractor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        budgetInteractor = BudgetInteractor(this, this)

        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        incomeButton.setOnClickListener { view ->
            budgetInteractor.addIncome()
        }

        expenseButton.setOnClickListener { view ->
            budgetInteractor.addExpense()
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
        Snackbar.make(incomeButton, "Income TODO", Snackbar.LENGTH_SHORT).show()
    }

    override fun onOpenAddExpense() {
        Snackbar.make(expenseButton, "Expense TODO", Snackbar.LENGTH_SHORT).show()
    }
}
