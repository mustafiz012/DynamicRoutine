package routine.kuet.musta;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.content.Loader;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class MyService extends Service {
	private static final String Tag = "MyService";
	MediaPlayer player;

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		Toast.makeText(this, "My Service Created!", Toast.LENGTH_SHORT).show();
		Log.d(Tag, "onCreate");
		player = MediaPlayer.create(this, R.raw.shondhera_jokhon);
		player.setLooping(false);
	}

	@Override
	public void onDestroy() {
		Toast.makeText(this, "Service Stopped", Toast.LENGTH_SHORT).show();
		Log.d(Tag, "onDestroy");
		player.stop();
	}

	@Override
	public void onStart(Intent intent, int startId) {
		player.start();
	}
}
