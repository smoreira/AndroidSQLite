package br.edu.unirv.auladb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import dao.PessoaDao;
import modelo.Pessoa;

public class FormContato extends AppCompatActivity {

    EditText edtNome, edtIdade, edtEnd, edtTel;
    Button btnVariavel;

    Pessoa pessoa, altpessoa;
    PessoaDao pessoaDao;
    long retornoDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_contato);

        Intent i = getIntent();
        altpessoa = (Pessoa) i.getSerializableExtra("pessoa-enviada");

        pessoa = new Pessoa();
        pessoaDao = new PessoaDao(FormContato.this);

        edtNome = (EditText)findViewById(R.id.editNome);
        edtIdade = (EditText)findViewById(R.id.editIdade);
        edtEnd = (EditText)findViewById(R.id.editEndereco);
        edtTel = (EditText)findViewById(R.id.editTelefone);

        btnVariavel = (Button) findViewById(R.id.btnVariavel);

        if(altpessoa != null){
            btnVariavel.setText("ALTERAR");
        }else{
            btnVariavel.setText("SALVAR");
        }

        btnVariavel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pessoa.setNome(edtNome.getText().toString());
                pessoa.setIdade(Integer.parseInt(edtIdade.getText().toString()));
                pessoa.setEndereco(edtEnd.getText().toString());
                pessoa.setTelefone(edtTel.getText().toString());


                if(btnVariavel.getText().toString().equals("SALVAR")){
                    //insert
                    retornoDB = pessoaDao.salvarPessoa(pessoa);
                    if (retornoDB == -1){
                        //deu erro
                        alerta("ERRO NA GRAVAÇÃO");
                    }else{
                        //sucesso
                        alerta("CADASTRADO COM SUCESSO");
                    }

                }else{
                    //update

                }

                finish();
            }
        });

    }

    private void alerta(String s){
        Toast.makeText(this,s,Toast.LENGTH_SHORT).show();
    }
}
