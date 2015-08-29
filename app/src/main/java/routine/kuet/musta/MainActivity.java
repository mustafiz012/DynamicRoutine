package routine.kuet.musta;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {

	Button saturday, sunday, monday, tuesday, wednesday, thursday, play, stop;
	private static final String Tag = "MainActivity";
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
		play = (Button) findViewById(R.id.songStart);
		stop = (Button) findViewById(R.id.songStop);
		saturday.setOnClickListener(this);
		sunday.setOnClickListener(this);
		monday.setOnClickListener(this);
		tuesday.setOnClickListener(this);
		wednesday.setOnClickListener(this);
		thursday.setOnClickListener(this);
		play.setOnClickListener(this);
		stop.setOnClickListener(this);
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
			Log.d(Tag, "starting service!!");
			startService(new Intent(this, MyService.class));
		}
		else if(v == stop){
			Log.d(Tag, "stopping service!!");
			stopService(new Intent(this, MyService.class));
		}
	}
}
