package com.tiquillo.aatreeimplementation;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    // Text labels
    TextView warningOnAddLabel;
    TextView numberOfNodesBox;

    // Text boxes (numeric)
    EditText numberToAddBox;

    // Buttons
    Button addButton;
    Button addRanButton;
    Button generate500Button;

    int numberOfNodes = 0;
    AATree aaTree = new AATree();
    Random ran = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // EditTexts
        warningOnAddLabel = findViewById(R.id.warningOnAdd);
        numberOfNodesBox = findViewById(R.id.numberOfNodes);
        numberToAddBox = findViewById(R.id.numberToAdd);

        CreateButtons();
    }

    public void UpdateBoxes () {
        numberOfNodesBox.setText((CharSequence) ("Number of nodes " + numberOfNodes));
        numberToAddBox.setText((CharSequence) "");
    }

    private void CreateButtons() {
        // asign buttons
        addButton = (Button) findViewById(R.id.addButton);
        addRanButton = (Button) findViewById(R.id.addRanButton);
        generate500Button = (Button) findViewById(R.id.generate500Button);

        // add manually button
        addButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                if (numberToAddBox.getText().toString().equals("")) { return; }

                long longNumber = Integer.parseInt(numberToAddBox.getText().toString());

                if (longNumber > 100000) {
                    warningOnAddLabel.setTextColor(Color.RED);
                    warningOnAddLabel.setText((CharSequence) "Number out of bound.");
                }

                int number = (int)longNumber;

                if (aaTree.search(number)){
                    // labeling on red
                    warningOnAddLabel.setText( (CharSequence) ("Number " + number + " already added!"));
                    warningOnAddLabel.setTextColor(Color.RED);

                } else {
                    // adding
                    aaTree.insert(number);
                    numberOfNodes++;

                    // labeling
                    warningOnAddLabel.setText((CharSequence) ("Number " + number + " succesfully added"));
                    warningOnAddLabel.setTextColor(Color.BLACK);
                }

                UpdateBoxes();
            }
        });

        // random add button
        addRanButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                int counter = 0;

                int ranInt = ran.nextInt(100000);
                while (aaTree.search(ranInt)) {
                    ranInt = ran.nextInt(100000);

                    counter++;
                    if (counter == 800) {
                        warningOnAddLabel.setTextColor(Color.RED);
                        warningOnAddLabel.setText((CharSequence) "No numbers available");
                        return;
                    }
                }
                aaTree.insert(ranInt);
                numberOfNodes++;

                // labeling
                warningOnAddLabel.setText((CharSequence) ("Number " + ranInt + " successfully added"));
                warningOnAddLabel.setTextColor(Color.BLACK);

                UpdateBoxes();
            }
        });

        // add 500 random button
        generate500Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int counter = 0;

                if (aaTree.isEmpty()) { aaTree.insert(ran.nextInt(100000)); }
                numberOfNodes ++;
                for (int i = 0; i < 499; i++){
                    int ranInt = ran.nextInt(100000);
                    while (aaTree.search(ranInt)){
                        ranInt = ran.nextInt(100000);
                        counter++;
                        if (counter == 800) {
                            warningOnAddLabel.setTextColor(Color.RED);
                            warningOnAddLabel.setText((CharSequence) "No numbers available");
                            return;
                        }
                    }
                    aaTree.insert(ranInt);
                    numberOfNodes++;
                }

                UpdateBoxes();
            }
        });
    }
}