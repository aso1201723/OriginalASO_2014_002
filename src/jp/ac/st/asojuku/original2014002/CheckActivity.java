package jp.ac.st.asojuku.original2014002;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class CheckActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自動生成されたメソッド・スタブ
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_check);
	}

	@Override
	protected void onResume() {
		// TODO 自動生成されたメソッド・スタブ
		super.onResume();

		//画面に渡されたインテントを取得
		Intent inte = this.getIntent();
		//inteから、Extraに入った値を取得
		String strHitokoto = inte.getStringExtra("hitokoto");

		//取得したStringを、txtCheck1にセット
		TextView txtHitokoto = (TextView)findViewById(R.id.txtCheck1);
		txtHitokoto.setText(strHitokoto);
	}

	@Override
	protected void onPause() {
		// TODO 自動生成されたメソッド・スタブ
		super.onPause();
	}

}
