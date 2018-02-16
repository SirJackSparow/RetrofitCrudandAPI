package com.example.user.crudretrofitlatest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    public static final String URL = "http://10.0.2.2/retrofitbaru/";
    private RadioButton radioSexButton;
    private ProgressDialog progress;

    @BindView(R.id.radioSesi) RadioGroup radioGroup;
    @BindView(R.id.editTextNPM) EditText editTextNPM;
    @BindView(R.id.editTextNama) EditText editTextNama;
    @BindView(R.id.editTextKelas) EditText editTextKelas;

    @OnClick(R.id.buttonLihat) void lihat (){
        startActivity(new Intent(MainActivity.this, ViewActivity.class));
        finish();
    }


    @OnClick(R.id.buttonDaftar) void daftar() {

        if(editTextNPM.getText().toString().length()==0 ){
            editTextNPM.setError("Npm diperlukan!");
        }
        else if (editTextNama.getText().toString().length()==0){
            editTextNama.setError("Nama kosong");
        }
        else if(editTextKelas.getText().toString().length()==0){
            editTextKelas.setError("kelas kosong");
        }
        else {
            //membuat progress dialog
            progress = new ProgressDialog(this);
            progress.setCancelable(false);
            progress.setMessage("Loading ...");
            progress.show();

            //mengambil data dari edittext

            String npm = editTextNPM.getText().toString();

            String nama = editTextNama.getText().toString();
            String kelas = editTextKelas.getText().toString();


            int selectedId = radioGroup.getCheckedRadioButtonId();
            // mencari id radio button
            radioSexButton = (RadioButton) findViewById(selectedId);
            String sesi = radioSexButton.getText().toString();

            retrofit2.Retrofit retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            RegisterAPI api = retrofit.create(RegisterAPI.class);
            Call<Value> call = api.daftar(npm, nama, kelas, sesi);
            call.enqueue(new Callback<Value>() {
                @Override
                public void onResponse(Call<Value> call, Response<Value> response) {
                    String value = response.body().getValue();
                    String message = response.body().getMessage();
                    progress.dismiss();
                    if (value.equals("1")) {
                        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Value> call, Throwable t) {
                    t.printStackTrace();
                    progress.dismiss();
                    Toast.makeText(MainActivity.this, "Jaringan Error!", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
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
