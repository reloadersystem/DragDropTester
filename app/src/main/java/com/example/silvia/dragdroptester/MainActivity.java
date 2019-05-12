package com.example.silvia.dragdroptester;


import android.content.ClipData;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.DragEvent;
import android.view.View;
import android.widget.TextView;

import static android.view.View.X;
import static android.view.View.Y;

public class MainActivity extends AppCompatActivity {

    TextView txt1, txt2, txt3, txt4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        txt1 = findViewById(R.id.txt1);
        txt2 = findViewById(R.id.txt2);
        txt3 = findViewById(R.id.txt3);
        txt4 = findViewById(R.id.target);

        txt1.setOnLongClickListener(longClickListener);
        txt2.setOnLongClickListener(longClickListener);
        txt3.setOnLongClickListener(longClickListener);

        txt4.setOnDragListener(dragListener);


    }

    View.OnLongClickListener longClickListener = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {

            ClipData data = ClipData.newPlainText("", "");
            View.DragShadowBuilder myShadowBuilder = new View.DragShadowBuilder(v);
            v.startDrag(data, myShadowBuilder, v, 0);
            return true;
        }
    };

    View.OnDragListener dragListener = new View.OnDragListener() {
        @Override
        public boolean onDrag(View v, DragEvent event) {

            int dragEvent = event.getAction();
            final View view = (View) event.getLocalState();


            switch (dragEvent) {
                case DragEvent.ACTION_DRAG_ENTERED:


                    //tomamos un texto lo arrastramos al contenedor y lo soltamos "como es adentro"

                    if (view.getId() == R.id.txt1) {
                        txt4.setText("TextView 1 Is Entered");
                    } else if (view.getId() == R.id.txt2) {
                        txt4.setText("TextView 2 Is Entered");
                    } else if (view.getId() == R.id.txt3) {
                        txt4.setText("TextView 3 Is Entered");
                    }
                    break;


                case DragEvent.ACTION_DRAG_EXITED:



                    //tomamos un texto lo arrastramos al contenedor pero solo lo acercamos cambia el texto previo de "como es afuera"

                    if (view.getId() == R.id.txt1) {
                        txt4.setText("TextView 1 Is Exitted");
                    } else if (view.getId() == R.id.txt2) {
                        txt4.setText("TextView 2 Is Exitted");
                    } else if (view.getId() == R.id.txt3) {
                        txt4.setText("TextView 3 Is Exitted");
                    }
                    break;
                case DragEvent.ACTION_DROP:

                    //se queda dentro

                    if (view.getId() == R.id.txt1) {
                        txt4.setText("TextView 1 Is Dropped");
                    } else if (view.getId() == R.id.txt2) {
                        txt4.setText("TextView 2 Is Dropped");
                    } else if (view.getId() == R.id.txt3) {
                        txt4.setText("TextView 3 Is Dropped");
                    }

                    // hasta  aqui solo reemplaza el texto

                    //Aqui mueve los  text  todos  y los deja en la caja
                    view.animate()
                            .x(txt4.getX())
                            .y(txt4.getY())
                            .setDuration(700)
                            .start();

                    break;


            }

            return true;
        }
    };

}
