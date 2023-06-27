package it.polito.tdp.PremierLeague.model;

public class Evento implements Comparable<Evento>{

	public enum EventType{
		GOAL,
		ESPULSIONE,
		INFORTUNIO
	}
	
	private int time;
	private EventType type;
	
	public Evento(int time, EventType type) {
		super();
		this.time = time;
		this.type = type;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public EventType getType() {
		return type;
	}

	public void setType(EventType type) {
		this.type = type;
	}
	

	@Override
	public String toString() {
		return "Evento [time=" + time + ", type=" + type + "]";
	}

	@Override
	public int compareTo(Evento o) {
		return this.time-o.time;
	}

}
