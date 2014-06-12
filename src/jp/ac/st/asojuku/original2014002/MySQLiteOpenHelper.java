package jp.ac.st.asojuku.original2014002;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteOpenHelper extends SQLiteOpenHelper {


	/**
	 *
	 * @param context　呼び出しコンテクスト
	 * @param name　利用DB名
	 * @param factory　カーソルファクトリー
	 * @param version DBバージョン
	 */
	public MySQLiteOpenHelper(Context context){

		super(context, "20140021201723.sqlite3", null, 1);
	}



	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO 自動生成されたメソッド・スタブ
		Log.d("Helper:onCreate","まだつくってないよ");
		db.execSQL("CREATE TABLE IF NOT EXISTS Hitokoto ( _id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL , phrase TEXT )" );
			Log.d("Helper:onCreate","CREATE TABLE");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO 自動生成されたメソッド・スタブ
		db.execSQL("DROP TABLE Hitokoto");
		onCreate(db);
	}

	/**
	 * 引数のフレーズをHitokotoテーブルインインサートするプライベートメソッド
	 * 	@param SQLiteDatabase インサート先DBのインスタンス変数
	 * @param inputMsg インサートするメッセージ
	 */

	public void insertHitokoto(SQLiteDatabase db, String inputMsg){

		String sqlstr = "INSERT INTO Hitokoto (phrase) VALUES(' " + inputMsg + " ');";

		try{
			//トランザクション開始
			db.beginTransaction();
			db.execSQL(sqlstr);
			//トランザクション成功
			db.setTransactionSuccessful();
		} catch(SQLException e) {
			Log.e("ERROR", e.toString());
		}finally{
			//トランザクション終了
			db.endTransaction();
		}
	return;
	}

	/**
	 * 引数のフレーズをHitokotoテーブルにインサートするプライベートメソッド
	 * @param SQLiteDatabese インサート先DBのインスタンス変数
	 * @param inputMsg インサートするメッセージ
	 */

	public String selectRandomHitokoto(SQLiteDatabase db){

		String rtString = null;
		String sqlstr = "SELECT _id, phrase FROM Hitokoto ORDER BY RANDOM();";
		try{
			//トランザクション開始
			SQLiteCursor cursor = (SQLiteCursor)db.rawQuery(sqlstr, null);
			if(cursor.getCount() != 0){
				//カーソル開始位置を先頭にする
				cursor.moveToFirst();
				rtString = cursor.getString(1);
			}
			cursor.close();


		} catch(SQLException e) {
			Log.e("ERROR", e.toString());
		}finally{
			//すでにカーソルもcloseしているので、なにもしない
		}
	return rtString;
	}




}
