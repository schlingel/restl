package at.fundev.restl.examples.notes.models;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

public class Note implements Serializable {
	private static final long serialVersionUID = 6899332403560006022L;

	@SerializedName("title")
	private String title;
	
	@SerializedName("note")
	private String note;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
}
