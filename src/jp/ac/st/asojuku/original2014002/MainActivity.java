package jp.ac.st.asojuku.original2014002;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnClickListener {

	@Override
	protected void onResume() {
		// TODO 自動生成されたメソッド・スタブ
		super.onResume();
		//MainActivityのボタンを変数に登録
		Button btnCe = (Button)findViewById(R.id.btnCheck);
		btnCe.setOnClickListener(this);
		Button btnTo = (Button)findViewById(R.id.btnTouroku);
		btnTo.setOnClickListener(this);
		Button btnMe = (Button)findViewById(R.id.btnMente);
		btnMe.setOnClickListener(this);
	}

	@Override
	protected void onPause() {
		// TODO 自動生成されたメソッド・スタブ
		super.onPause();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO 自動生成されたメソッド・スタブ
		switch (v.getId()){
		case R.id.btnCheck:
			Intent ic = new Intent (this,);
			startActivity(ic);
			break;

		case R.id.btnMente:
			Intent im = new Intent(this,);
			startActivity(ic);
			break;

		case R.id.btnTouroku:

			break;
		}

	}

}
