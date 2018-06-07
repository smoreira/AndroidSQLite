package br.edu.unirv.auladb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import dao.PessoaDao;
import modelo.Pessoa;

public class MainActivity extends AppCompatActivity {

    ListView listaVisivel;
    Button btnNovoCadastro;

    Pessoa pessoa;
    PessoaDao pessoaDao;

    ArrayList<Pessoa> arrayListPessoa;
    ArrayAdapter<Pessoa> arrayAdapterPessoa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaVisivel = (ListView) findViewById(R.id.listPessoas);
        btnNovoCadastro = (Button) findViewById(R.id.btnCadastro);

        btnNovoCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,FormContato.class);
                startActivity(i);
            }
        });

    }

    public void populaLista(){
        pessoaDao = new PessoaDao(MainActivity.this);

        arrayListPessoa = pessoaDao.selectAllPessoas();
        pessoaDao.close();

        if(listaVisivel != null){
            arrayAdapterPessoa = new ArrayAdapter<Pessoa>(MainActivity.this,
                android.R.layout.simple_list_item_1,arrayListPessoa);
            listaVisivel.setAdapter(arrayAdapterPessoa);
        }
    }



    @Override
    protected void onResume(){
        super.onResume();
        populaLista();
    }

}
