package jp.ac.st.asojuku.original2014002;

import android.app.Activity;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class MenteActivity extends Activity implements View.OnClickListener, AdapterView.OnItemClickListener{

	//SQLiteデータベース空間を操作するインスタンス変数を宣言
	SQLiteDatabase sdb = null;

	//MySQLiteOpenHelperを操作するインスタンス変数を宣言
	MySQLiteOpenHelper helper = null;

	// リストにて選択したHitokotoテーブルのレコードの「_id」カラム値を保持する変数を宣言
	int selectedID = -1;

	//リストにて選択した行番号を保持する変数の宣言
	int lastPosition = -1;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自動生成されたメソッド・スタブ
		super.onCreate(savedInstanceState);
		setContentView(R.layout.actvity_mente);

	}

	@Override
	protected void onResume() {
		// TODO 自動生成されたメソッド・スタブ
		super.onResume();

		Button btnDelete = (Button)findViewById(R.id.btnDELETE);
		Button btnMente_Back = (Button)findViewById(R.id.btnMente_BACK);
		ListView lstHitokoto = (ListView)findViewById(R.id.listHitokoto);

		//各ButtonにOnClickListenerをセット
		btnDelete.setOnClickListener(this);
		btnMente_Back.setOnClickListener(this);

		// ListViewにOnItemClickListenerをセット
		lstHitokoto.setOnItemClickListener(this);

		//ListViewmにDBの値をセット
		this.setDBValuetoList(lstHitokoto);


	}

	@Override
	protected void onPause() {
		// TODO 自動生成されたメソッド・スタブ
		super.onPause();
	}

	/**
	 * 引数のListViewにDBのデータをセット
	 * @param lstHitokoto 対象となるListView
	 */
	private void setDBValuetoList(ListView lstHitokoto) {

		SQLiteCursor cursor = null;

		// クラスのフィールド変数がNULLなら、データベース空間をオープン
		if(sdb == null) {
			helper = new MySQLiteOpenHelper(getApplicationContext());
		}
		try{
			sdb = helper.getWritableDatabase();
		} catch(SQLiteException e){
			//異常終了
			Log.e("ERROR", e.toString());
		}
		// MySQLiteOpenHelperにSELECT文を実行させて結果のカーソルを受け取る
		cursor = this.helper.selectHitokotoList(sdb);

		//dblayout: ListViewにさらにレイアウトを指定するもの
		int db_layout = android.R.layout.simple_list_item_activated_1;
		//from: カーソルからListviewに指定するカラムの値を指定するもの
		String[] from = {"phrase"};
		//to: Lisetviewの中に指定したdb_layoutに配置する、各行のview部品のid
		int[] to = new int[]{android.R.id.text1};

		//ListViewにセットするアダプターを生成
		//カーソルをもとに、fromの列から、toのViewへ値のマッピングがおこなわれる。
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,db_layout,cursor,from,to,0);

		// アダプターを指定する
		lstHitokoto.setAdapter(adapter);
	}

	@Override
	public void onClick(View v){

		switch(v.getId()){

		case R.id.btnMente_BACK: //戻るボタンを押したときの処理
			finish();
			break;

		case R.id.btnDELETE: //削除ボタンを押したときの処理
			if(selectedID != -1){
			this.helper.deleteHitokoto(sdb,selectedID); // ＩＮＴ型のselectedIDを使ってdeleteHitokotoメソッドを呼び出し
			Toast.makeText(getApplicationContext(),selectedID + "を削除しました",Toast.LENGTH_LONG).show();
			}
			break;
		}

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		// TODO 自動生成されたメソッド・スタブ

		ListView listView = (ListView) parent;
		SQLiteCursor cursor = (SQLiteCursor)listView.getItemAtPosition(position);
		String Id = cursor.getString(cursor.getColumnIndex(BaseColumns._ID));
		//Toast.makeText(getApplicationContext(), Id, Toast.LENGTH_LONG).show();

		selectedID = cursor.getInt(cursor.getColumnIndex(BaseColumns._ID)); // トーストにindexを表示

	}

}
