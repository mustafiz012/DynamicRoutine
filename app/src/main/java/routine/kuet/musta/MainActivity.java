package routine.kuet.musta;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {

	Button saturday, sunday, monday, tuesday, wednesday, thursday, play;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initializer();
	}

	public void initializer(){
		saturday = (Button) findViewById(R.id.saturday);
		sunday = (Button) findViewById(R.id.sunday);
		monday = (Button) findViewById(R.id.monday);
		tuesday = (Button) findViewById(R.id.tuesday);
		wednesday = (Button) findViewById(R.id.wednesday);
		thursday = (Button) findViewById(R.id.thursday);
		play = (Button) findViewById(R.id.song);
		saturday.setOnClickListener(this);
		sunday.setOnClickListener(this);
		monday.setOnClickListener(this);
		tuesday.setOnClickListener(this);
		wednesday.setOnClickListener(this);
		thursday.setOnClickListener(this);
		play.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if (v == saturday)
			startActivity(new Intent(this, Saturday.class));
		else if (v == sunday)
			startActivity(new Intent(this, Sunday.class));
		else if (v == monday)
			startActivity(new Intent(this, Monday.class));
		else if (v == tuesday)
			startActivity(new Intent(this, Tuesday.class));
		else if (v == wednesday)
			startActivity(new Intent(this, Wednesday.class));
		else if (v == thursday)
			startActivity(new Intent(this, Thursday.class));
		else if(v == play){
			Toast.makeText(MainActivity.this, "This will play my favourite song!!", Toast.LENGTH_SHORT).show();
		}
	}
}
