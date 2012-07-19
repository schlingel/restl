package at.fundev.restl.examples.notes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import at.fundev.restl.examples.notes.models.Note;

public class NotesListAdapter extends BaseAdapter {
	private Note[] notes;
	
	private LayoutInflater inflater;
	
	public NotesListAdapter(Context context, Note[] notes) {
		this.inflater = LayoutInflater.from(context);
		this.notes = notes;
	}
	
	public int getCount() {
		return notes == null ? 0 : notes.length;
	}

	public Object getItem(int position) {
		if(notes == null || position < 0 || position >= notes.length) {
			return notes[position];
		} 
		
		return null;
	}

	public long getItemId(int position) {
		
		return notes[position].hashCode();
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		if(convertView == null) {
			convertView = inflater.inflate(R.layout.note, null);
		}
		
		TextView lblTitle = (TextView)convertView.findViewById(R.id.lblTitle);
		TextView lblNote = (TextView)convertView.findViewById(R.id.lblNote);
		Note note = (Note)getItem(position);
		
		lblTitle.setText(note.getTitle());
		lblNote.setText(note.getNote());
		
		return convertView;
	}

}