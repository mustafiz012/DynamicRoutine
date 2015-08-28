package routine.kuet.musta;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Map;

public class Saturday extends ActionBarActivity implements View.OnClickListener {

	Button createCourses, deleteCourses, home;
	LinearLayout showCourse;
	TextView[] tvName;
	int counter =0;
	SharedPreferences spCreateName, deleteSharedPreferences, editSharedPreferences;
	SharedPreferences.Editor editor, eCreateName, eDelete, eEdit;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_saturday);
		initialization();
	}

	public void initialization(){
		spCreateName = getSharedPreferences("MyCourses", 0);
		eCreateName = spCreateName.edit();
		createCourses = (Button) findViewById(R.id.createCourse);
		deleteCourses = (Button) findViewById(R.id.deleteOne);
		home  = (Button) findViewById(R.id.home);
		showCourse = (LinearLayout) findViewById(R.id.showCourses);
		createCourses.setOnClickListener(this);
		deleteCourses.setOnClickListener(this);
		home.setOnClickListener(this);
		courseViewer();
		tvName = new TextView[100];
	}

	@Override
	public void onClick(View v) {
		if (v == createCourses){
			counter++;
			final String count = String.valueOf(counter);
			AlertDialog.Builder createBuilder = new AlertDialog.Builder(this);
			createBuilder.setTitle("Name Your Course");
			final EditText courseName = new EditText(this);
			courseName.setHint("Name & Time & Teacher Name");
			if (courseName != null)
				createBuilder.setView(courseName);
			createBuilder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					final String cName = courseName.getText().toString();
					if (cName.isEmpty()) {
						Toast.makeText(Saturday.this, "Don't make it blank!!", Toast.LENGTH_SHORT).show();
					} else {
						//counter++;
						Toast.makeText(Saturday.this, "Courses Added: " + cName, Toast.LENGTH_SHORT).show();
						eCreateName.putString(cName, cName);
						eCreateName.apply();
						courseViewer();
						eCreateName.commit();
					}
				}
			});
			createBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.cancel();
				}
			});
			AlertDialog alertDialog = createBuilder.create();
			alertDialog.show();
		}
		else if(v == deleteCourses){
			final AlertDialog.Builder deleteCourse = new AlertDialog.Builder(this);
			deleteCourse.setTitle("Delete Course");
			final EditText courseName = new EditText(this);
			final int cID = courseName.getId();
			courseName.setHint("which one U wanna delete:");
			Map<String, ?> addedCourses = spCreateName.getAll();
			int i =0;
			for (Map.Entry<String, ?> entry : addedCourses.entrySet()) {
				final String deleteAddedCourses = entry.getKey();
				i++;
				TextView hints = new TextView(this);
				hints.setText(deleteAddedCourses);
				deleteCourse.setMessage("Select : "+cID);
			}
			deleteCourse.setView(courseName);
			deleteCourse.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					final String cName = courseName.getText().toString();
					if (cName.isEmpty()) {
						Toast.makeText(Saturday.this, "Nothing To Delete!", Toast.LENGTH_SHORT).show();
					} else {
						eCreateName.remove(cName);
						eCreateName.apply();
						eCreateName.apply();
						courseViewer();
					}
				}
			});
			deleteCourse.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.cancel();
				}
			});
			AlertDialog test = deleteCourse.create();
			test.show();
		}
		else if(v == home)
			startActivity(new Intent(this, MainActivity.class));
	}

	public void courseViewer() {

		try {
			showCourse.removeAllViews();
		} catch (Exception ignored)
		{ }
		Map<String, ?> allName = spCreateName.getAll();
		int i = 0;
		for (Map.Entry<String, ?> entry : allName.entrySet()) {
			final String courseName = entry.getKey();
			i++;
			//Toast.makeText(Saturday.this, courseName, Toast.LENGTH_SHORT).show();
			TextView rowTextView = new TextView(this);
			rowTextView.setGravity(Gravity.CENTER);
			//rowTextView.setAllCaps(true);
			rowTextView.setText(i+". "+courseName);
			rowTextView.setTextSize(20);
			rowTextView.setId(i);
			rowTextView.setPadding(5, 5, 5, 5);
			rowTextView.setTextColor(getResources().getColor(R.color.button_material_dark));
			rowTextView.setTextIsSelectable(true);
			rowTextView.setCursorVisible(deleteDatabase(courseName));
			rowTextView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Toast.makeText(Saturday.this, "This is "+courseName, Toast.LENGTH_SHORT).show();
				}
			});
			showCourse.addView(rowTextView);
		}
	}
}
