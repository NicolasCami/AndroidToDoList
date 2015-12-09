package fr.univpau.util;


public class Task {
	
	private int id;
	private String title;
	//private Calendar date;
	private String date;
	private boolean done;
	
	public Task() {
		this.title = "";
		//this.date = new GregorianCalendar();
		this.date = "";
		this.done = false;
	}
	
	//public Task(String title, Calendar date, boolean done) {
	public Task(String title, String date, boolean done) {
		this.title = title;
		this.date = date;
		this.done = done;
	}
	
	public Task(int id, String title, String date, boolean done) {
		this.id = id;
		this.title = title;
		this.date = date;
		this.done = done;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	//public Calendar getDate() {
	public String getDate() {
		return date;
	}
	//public void setDate(Calendar date) {
	public void setDate(String date) {
		this.date = date;
	}
	public boolean isDone() {
		return done;
	}
	public void setDone(boolean done) {
		this.done = done;
	}

}
