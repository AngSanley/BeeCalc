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
import android.os.Bundle;
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
    }
}
