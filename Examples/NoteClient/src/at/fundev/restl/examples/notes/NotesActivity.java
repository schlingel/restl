package at.fundev.restl.examples.notes;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

public class NotesActivity extends Activity {
	private Button btnAddNote;
	
	private ListView lvNotes;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
}