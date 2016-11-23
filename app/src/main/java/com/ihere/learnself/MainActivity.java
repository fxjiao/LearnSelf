package com.ihere.learnself;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ihere.learnself.activity.mayenglish.MayEnglishMainActivity;
import com.ihere.learnself.activity.mayfinancial.MayFinancialMainActivity;
import com.ihere.learnself.helper.DatabaseHelper;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

//        InitData.initMySalaryNew(this);
//        ((TextView)findViewById(R.id.tx_count)).setText("OK");

        ((TextView)findViewById(R.id.tx_count)).setText("" + (DatabaseHelper.getHelper(getApplicationContext()).getAllMayWords(this).size()));
    }

    public void initView(){
        findViewById(R.id.english).setOnClickListener(this);
        findViewById(R.id.financial).setOnClickListener(this);
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
        return false;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.english:
                startActivityForResult(new Intent(MainActivity.this, MayEnglishMainActivity.class),1001);
                break;
            case R.id.financial:
                startActivity(new Intent(MainActivity.this, MayFinancialMainActivity.class));
                break;
            default:break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK ){
            switch (requestCode){
                case 1001:
                    Toast.makeText(MainActivity.this,"1001",Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            moveTaskToBack(true);
            Toast.makeText(MainActivity.this,"KEYCODE_BACK",Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
