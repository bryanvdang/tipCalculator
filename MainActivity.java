package com.bryanvdang.tipcalculator;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;
import android.view.inputmethod.InputMethodManager;
import android.content.Context;

public class MainActivity extends AppCompatActivity {

    //declare objects
    EditText txtTipPercentage, txtMealCost;
    TextView textTipPercent, textTotalMealCost;
    Button btnCalculate, btnClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCalculate = (Button)findViewById(R.id.calculateButton);
        btnClear = (Button)findViewById(R.id.clearButton);


        btnCalculate.setOnClickListener(new View.OnClickListener() { //for btn interaction
            @Override
            public void onClick(View v) {

                calculateTip();
                dismissKeyboard();

            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                clearApp();
                dismissKeyboard();
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void calculateTip(){

        txtTipPercentage = (EditText)findViewById(R.id.tipPercent); //set object to id
        txtMealCost = (EditText)findViewById(R.id.mealCost);
        textTipPercent = (TextView)findViewById(R.id.editText);
        textTotalMealCost = (TextView)findViewById(R.id.editText2);

        //grab raw value from txtTipPercentage and parses it to a string
        double tipPercentage = Double.parseDouble(txtTipPercentage.getText().toString());
        double mealCost = Double.parseDouble(txtMealCost.getText().toString());
        double totalMealCost;
        double totalTip;

        tipPercentage = tipPercentage * 0.01;
        totalTip = mealCost * tipPercentage; //tip  = cost of meal X tip percentage
        totalMealCost = mealCost + totalTip; // total meal cost =  meal + tip

        //round tip and total meal cost
        String roundTip = String.format("%1.2f", totalTip); //(argument, variable)
        String roundTotalMealCost = String.format("%1.2f", totalMealCost);

        //changing screen text to new value
        textTipPercent.setText("How Much To Tip: $" + roundTip);
        textTotalMealCost.setText("Total Meal Cost: $" + roundTotalMealCost);
    }

    private void clearApp(){
        //sets the text back to empty fields because hints already exists
        txtTipPercentage.setText("");
        txtMealCost.setText("");

        //set text back to starting values
        textTipPercent.setText("How Much To Tip: $0.00");
        textTotalMealCost.setText("Total Meal Cost: $0.00");

    }

    private void dismissKeyboard(){

        //after clicking buttons, keyboard should dismiss
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(txtMealCost.getWindowToken(), 0);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
