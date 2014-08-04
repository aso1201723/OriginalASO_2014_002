package jp.ac.st.asojuku.original2014002;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity implements View.OnClickListener {

	SQLiteDatabase sdb = null;
	MySQLiteOpenHelper helper = null;


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

		//クラスのフィールド変数がNULLなら、データベース空間をオープン
		if(sdb == null) {
			helper = new MySQLiteOpenHelper(getApplicationContext());
		}
		try{
			sdb = helper.getWritableDatabase();
		} catch(SQLiteException e){
			//異常終了
			return;
		}

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
		Intent inte = null;
		switch (v.getId()){
		case R.id.btnCheck: //一言チェックボタン

			// MySQLiteOpenHelperのセレクト一言メソッドを呼び出し
			String strHitokoto = helper.selectRandomHitokoto(sdb);

			//インテントのインスタンス生成
			inte = new Intent (this, CheckActivity.class);
			//インテントに一言を入れる
			inte.putExtra("hitokoto", strHitokoto);

			startActivity(inte);
			break;

		case R.id.btnMente://メンテボタンが押された
			inte = new Intent(this, MenteActivity.class);
			startActivity(inte);
			break;

		case R.id.btnTouroku: //登録ボタンが押された
			// エディットテキストからの入力を取り出す
			EditText edt1 = (EditText)findViewById(R.id.editTxt1);
			String inputMsg = edt1.getText().toString();

			Log.d("MainActivity", inputMsg);

			// inputMsgがnullでない、かつ、からでない場合のみ、if文を実行
			if(inputMsg != null && !inputMsg.isEmpty()) {
				//MySQLiteOpenHelperのインサート
				helper.insertHitokoto(sdb, inputMsg);
			}

			//入力欄クリア
			edt1.setText("");

			break;
		}


	}

}
