package com.example.labsoftware1.personasbasededatos;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class Registrar extends AppCompatActivity {
    private EditText cajaCedula;
    private EditText cajaNombre;
    private EditText cajaApellido;
    private RadioButton rdMasculino;
    private RadioButton rdFemenino;
    private CheckBox chkProgramar;
    private CheckBox chkLeer;
    private CheckBox chkBailar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        cajaCedula = (EditText) findViewById(R.id.txtcedula);
        cajaNombre = (EditText) findViewById(R.id.txtNombre);
        cajaApellido = (EditText) findViewById(R.id.txtApellido);
        rdMasculino = (RadioButton) findViewById(R.id.r1);
        rdFemenino = (RadioButton) findViewById(R.id.r2);
        chkProgramar = (CheckBox) findViewById(R.id.chkProgramar);
        chkLeer = (CheckBox) findViewById(R.id.chkLeer);
        chkBailar = (CheckBox) findViewById(R.id.chkBailar);
        ocultartodo();
    }

    public boolean validartodo() {
        if (cajaCedula.getText().toString().isEmpty()) {
            cajaCedula.setError("Digite cedula");
            cajaCedula.requestFocus();
            return false;
        }
        if (cajaNombre.getText().toString().isEmpty()) {
            cajaNombre.setError("Diguite Nombre");
            cajaNombre.requestFocus();
            return false;
        }
        if (cajaApellido.getText().toString().isEmpty()) {
            cajaApellido.setError(("Diguite Apellido"));
            cajaApellido.requestFocus();
            return false;
        }
        if ((!chkProgramar.isChecked()) && (!chkLeer.isChecked()) && (!chkBailar.isChecked())) {
            new AlertDialog.Builder(this).setMessage("Seleccione por lo menos un pasa tiempos").setCancelable(true).show();
            return false;
        }
        return true;
    }


    public void guardar(View v) {
        String foto, cedula, nombre, apellido, sexo, pasatiempo = "";
        Persona p;
        if (validartodo()) {
            cedula = cajaCedula.getText().toString();
            foto = String.valueOf(fotoAleatoria());
            nombre = cajaNombre.getText().toString();
            apellido = cajaApellido.getText().toString();

            //Verifico si es masculino o Femenino
            if (rdMasculino.isChecked()) sexo = getResources().getString(R.string.masculino);
            else sexo = getResources().getString(R.string.femenino);

            //Verifico cuales estan chekeados
            if (chkProgramar.isChecked()) {
                pasatiempo = getResources().getString(R.string.programar) + ", ";
            }
            if (chkLeer.isChecked()) {
                pasatiempo = pasatiempo + getResources().getString(R.string.leer) + ", ";
            }
            if (chkBailar.isChecked()) {
                pasatiempo = pasatiempo + getResources().getString(R.string.bailar) + ", ";
            }

            //Elimina la , y el espacio final
            pasatiempo = pasatiempo.substring(0, pasatiempo.length() - 2);

            p = new Persona(foto, cedula, nombre, apellido, sexo, pasatiempo);
            p.guardar(getApplicationContext());

            new AlertDialog.Builder(this).setMessage("Persona Guardada Exitosamente!").setCancelable(true).show();
            limpiar();
        }
    }

    public int fotoAleatoria() {
        int fotos[] = {R.drawable.images, R.drawable.images2, R.drawable.images3};
        int numero = (int) (Math.random() * 3);
        return fotos[numero];
    }


    public void limpiar() {
        cajaCedula.setText("");
        cajaNombre.setText("");
        cajaApellido.setText("");
        rdMasculino.setChecked(true);
        rdFemenino.setChecked(false);
        chkProgramar.setChecked(false);
        chkBailar.setChecked(false);
        chkLeer.setChecked(false);

    }


    public void limpia(View v) {
        limpiar();
    }


    public boolean validarCedula() {
        if (cajaCedula.getText().toString().isEmpty()) {
            cajaCedula.setError("Digite cedula");
            cajaCedula.requestFocus();
            return false;
        }
        return true;
    }


    public void buscar(View v) {
        Persona p;
        if (validarCedula()) {

            p = Datos.buscarPersona(getApplicationContext(), cajaCedula.getText().toString());
            if (p != null) {
                //llena campos de Nombre y Apellido
                cajaNombre.setText(p.getNombre());
                cajaApellido.setText(p.getApellido());
                //chekea sexo
                if (p.getSexo().equalsIgnoreCase(getResources().getString(R.string.masculino)))
                    rdMasculino.setChecked(true);
                else rdFemenino.setChecked(true);
                //chekea pasatiempos
                if (p.getPasatiempo().contains(getResources().getString(R.string.programar)))
                    chkProgramar.setChecked(true);
                if (p.getPasatiempo().contains(getResources().getString(R.string.leer)))
                    chkLeer.setChecked(true);
                if (p.getPasatiempo().contains(getResources().getString(R.string.bailar)))
                    chkBailar.setChecked(true);

                //Ocultar teclado luego de buscar
                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(cajaCedula.getWindowToken(), 0);
                mostrartodo();
            } else {
                new AlertDialog.Builder(this).setMessage("Cedula no encontrada registrese").setCancelable(true).show();
                mostrartodo();
            }
        }
    }


    public void eliminar(final View v){
        final Persona p;
        if (validarCedula()) {

            p = Datos.buscarPersona(getApplicationContext(), cajaCedula.getText().toString());
            if (p != null) {
                new AlertDialog.Builder(this)
                        .setTitle("Eliminar entrada")
                        .setMessage("Estas seguro de eliminar a: " + "\n" +"Cedula: "+ cajaCedula.getText()+ "\n"+"Nombre: "+ cajaNombre.getText()+"\n"+"Apellido: "+cajaApellido.getText())
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                p.eliminar(getApplicationContext());
                                limpiar();
                                Toast toast = Toast.makeText(getApplicationContext(), "Persona Eliminada Exitosamente!", Toast.LENGTH_LONG);
                                toast.show();
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)

                        .show();
            }
        }
    }



    public void modificar (View v) {
        Persona p, p2;
        String nombre, apellido, sexo, pasatiempo="";
        if (validarCedula()) {
            p = Datos.buscarPersona(getApplicationContext(), cajaCedula.getText().toString());
            if (p != null) {

                nombre = cajaNombre.getText().toString();
                apellido = cajaApellido.getText().toString();

                //Verifico si es masculino o Femenino
                if (rdMasculino.isChecked()) sexo = getResources().getString(R.string.masculino);
                else sexo = getResources().getString(R.string.femenino);

                //Verifico cuales estan chekeados
                if (chkProgramar.isChecked()) {
                    pasatiempo = getResources().getString(R.string.programar) + ", ";
                }
                if (chkLeer.isChecked()) {
                    pasatiempo = pasatiempo + getResources().getString(R.string.leer) + ", ";
                }
                if (chkBailar.isChecked()) {
                    pasatiempo = pasatiempo + getResources().getString(R.string.bailar) + ", ";
                }
                //Elimina la , y el espacio final
                pasatiempo = pasatiempo.substring(0, pasatiempo.length() - 2);

                p2 = new Persona(p.getFoto(), p.getCedula(), nombre, apellido, sexo, pasatiempo);
                p2.modificar(getApplicationContext());
                new AlertDialog.Builder(this).setMessage("Usuario modificado exitosamente").setCancelable(true).show();
                limpiar();
            }
        }
    }



    public void ocultartodo(){
        cajaNombre.setEnabled(false);
        cajaApellido.setEnabled(false);

        rdMasculino.setEnabled(false);
        rdFemenino.setEnabled(false);

        chkLeer.setEnabled(false);
        chkProgramar.setEnabled(false);
        chkBailar.setEnabled(false);

    }


    public void mostrartodo(){
        cajaNombre.setEnabled(true);
        cajaApellido.setEnabled(true);

        rdMasculino.setEnabled(true);
        rdFemenino.setEnabled(true);

        chkLeer.setEnabled(true);
        chkProgramar.setEnabled(true);
        chkBailar.setEnabled(true);
        cajaNombre.requestFocus();
        cedulaoff();
    }

    public void cedulaoff(){
        cajaCedula.setEnabled(false);
    }

    public void cedulaon() {
        cajaCedula.setEnabled(true);
        cajaCedula.requestFocus();
        ocultartodo();
    }



}
