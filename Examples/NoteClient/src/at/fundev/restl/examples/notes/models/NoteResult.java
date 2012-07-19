package at.fundev.restl.examples.notes.models;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

public class NoteResult implements Serializable {
	private static final long serialVersionUID = -8496828106213572400L;

	@SerializedName("result")
	private boolean result;
	
	@SerializedName("data")
	private Note[] data;

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	public Note[] getData() {
		return data;
	}

	public void setData(Note[] data) {
		this.data = data;
	}
}
