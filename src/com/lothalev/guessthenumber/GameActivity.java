package com.lothalev.guessthenumber;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import android.os.Build;


public class GameActivity extends ActionBarActivity {
	
	static NumberGame ng; // game logic class 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	     SharedPreferences prefs = getSharedPreferences (SettingsActivity.PREFS_NAME, 0);
		ng = new NumberGame(prefs.getInt("GameLevel", 4));

	}

	public void onTryClicked(View v) {
		Integer ansInt;
		
		EditText answer = (EditText) findViewById(R.id.answer);
		String ansStr = answer.getText().toString();
		answer.setText("");

		
		try {
			ansInt = Integer.parseInt(ansStr);
		} catch(Exception e) {
			Toast.makeText(GameActivity.this, "The answer must be a number", Toast.LENGTH_SHORT).show();
			return;
		}
		
		
		if (ansStr.trim().length()==0) {
			Toast.makeText(GameActivity.this, "Please give an answer", Toast.LENGTH_SHORT).show();
			return;
		}
		
		if (ansInt<=ng.currentMin || ansInt>=ng.currentMax){
			Toast.makeText(GameActivity.this, "Please give a guess between "+ng.currentMin+" and "+ng.currentMax, Toast.LENGTH_SHORT).show();
			return;
		}
		if (ng.Guess(ansInt)) {
			Toast.makeText(GameActivity.this, "CONGRATULATIONS! Number guessed in "+ng.tries+" tries.", Toast.LENGTH_LONG).show();
			startActivity(new Intent(getApplicationContext(), MainActivity.class));
		}
		UpdateGameDisplay(v);
	}
	
	public void onKeypadKeyClicked(View v){
		Button button = (Button)v;
		EditText answer = (EditText) findViewById(R.id.answer);
		answer.append(button.getText().toString());
	
	}

	public void onKeypadBackClicked(View v){
		EditText answer = (EditText) findViewById(R.id.answer);
		String str = answer.getText().toString();
		if (str.isEmpty())
			return;
		answer.setText(str.substring(0, str.length()-1));
	
	}
	
	public void UpdateGameDisplay(View v) {
		TextView min = (TextView) findViewById(R.id.lblGreater);
		TextView max = (TextView) findViewById(R.id.lblSmaller);
		TextView status = (TextView) findViewById(R.id.lblStatus);
		min.setText(ng.currentMin+"");
		max.setText(ng.currentMax+"");
		status.setText("You have tried "+ng.tries+" times.");
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.game, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_game, container,
					false);
			TextView minN = (TextView) rootView.findViewById(R.id.lblSmaller);
			TextView maxN = (TextView) rootView.findViewById(R.id.lblGreater);
			minN.setText(GameActivity.ng.currentMax+""); 
			maxN.setText(GameActivity.ng.currentMin+"");

			return rootView;
		}
	}

}
