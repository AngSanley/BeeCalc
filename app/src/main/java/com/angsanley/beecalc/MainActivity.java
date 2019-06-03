/*
 * Copyright 2019 Stanley. All rights reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.angsanley.beecalc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    TextView resultText;
    TextView aboutBtn;
    MaterialButton calculateBtn;
    TextInputEditText numberOneEdit;
    TextInputEditText numberTwoEdit;
    AutoCompleteTextView operationEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultText = findViewById(R.id.resultText);
        aboutBtn = findViewById(R.id.about_btn);
        calculateBtn = findViewById(R.id.calculate_button);
        numberOneEdit = findViewById(R.id.number_one_textedit);
        numberTwoEdit = findViewById(R.id.number_two_textedit);
        operationEdit = findViewById(R.id.operation_textedit);

        String[] OPERATION = new String[] {getString(R.string.addition), getString(R.string.subtraction), getString(R.string.multiplication), getString(R.string.division)};

        ArrayAdapter adapter = new ArrayAdapter<>(
                this,
                R.layout.dropdown_menu_popup_item,
                OPERATION);

        operationEdit.setAdapter(adapter);

        numberOneEdit.requestFocus();

        numberTwoEdit.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                doCalculation();
                return false;
            }
        });

        calculateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doCalculation();
            }
        });

        aboutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AboutActivity.class));
            }
        });
    }

    private void doCalculation() {
        boolean fieldError = false;

        if (numberTwoEdit.getText().toString().trim().equalsIgnoreCase("")) {
            numberTwoEdit.setError(getString(R.string.field_required));
            numberTwoEdit.requestFocus();
            fieldError = true;
        }

        if (numberOneEdit.getText().toString().trim().equalsIgnoreCase("")) {
            numberOneEdit.setError(getString(R.string.field_required));
            numberOneEdit.requestFocus();
            fieldError = true;
        }

        if (!fieldError) {
            int numberOne = Integer.parseInt(numberOneEdit.getText().toString());
            int numberTwo = Integer.parseInt(numberTwoEdit.getText().toString());

            // addition
            if (operationEdit.getText().toString().equals(getString(R.string.addition))) {
                int result = numberOne + numberTwo;
                resultText.setText(String.valueOf(result));
            }

            // subtraction
            if (operationEdit.getText().toString().equals(getString(R.string.subtraction))) {
                int result = numberOne - numberTwo;
                resultText.setText(String.valueOf(result));
            }

            // multiplication
            if (operationEdit.getText().toString().equals(getString(R.string.multiplication))) {
                int result = numberOne * numberTwo;
                resultText.setText(String.valueOf(result));
            }

            // division
            if (operationEdit.getText().toString().equals(getString(R.string.division))) {
                String output = "";
                try {
                    int result = numberOne / numberTwo;
                    output = String.valueOf(result);
                } catch (Exception e) {
                    numberOneEdit.setError(e.getLocalizedMessage());
                    numberTwoEdit.setError(e.getLocalizedMessage());
                    numberOneEdit.requestFocus();
                    output = getString(R.string.error);
                } finally {
                    resultText.setText(output);
                }
            }
        }
    }
}
