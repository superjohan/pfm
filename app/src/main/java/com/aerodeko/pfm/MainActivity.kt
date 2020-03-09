package com.aerodeko.pfm

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import com.aerodeko.pfm.model.BudgetInteractor
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), BudgetInteractor.Listener {
    private lateinit var budgetInteractor: BudgetInteractor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        this.budgetInteractor = BudgetInteractor(this, this)

        setSupportActionBar(this.toolbar)

        incomeButton.setOnClickListener {
            this.budgetInteractor.addIncome()
        }

        expenseButton.setOnClickListener {
            this.budgetInteractor.addExpense()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        this.menuInflater.inflate(R.menu.menu_main, menu)

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
