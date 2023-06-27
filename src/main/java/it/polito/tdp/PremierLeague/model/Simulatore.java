package it.polito.tdp.PremierLeague.model;

import java.time.LocalDate;
import java.util.List;
import java.util.PriorityQueue;
import it.polito.tdp.PremierLeague.model.Evento.EventType;

public class Simulatore {

	private int azioni;
	private int giocatori1;
	private int giocatori2;
	private Player migliore;
	private List<Player> team1;
	private List<Player> team2;
	
	private int goal1;
	private int goal2;
	private int esp1;
	private int esp2;
	
	private PriorityQueue<Evento> queue;
	
	public Simulatore(int azioni, Player migliore, List<Player> team1, List<Player> team2) {
		this.azioni = azioni;
		this.giocatori1 = 11;
		this.giocatori2 = 11;
		this.migliore = migliore;
		this.team1 = team1;
		this.team2 = team2;
		this.goal1 = 0;
		this.goal2 = 0;
		this.esp1 = 0;
		this.esp2 = 0;
		this.queue = new PriorityQueue<>();
	}
	
	public void initialize() {
		for(int i=0;i<azioni;i++) {
			double prob = Math.random();
//			System.out.println(prob);
			if(prob<0.5) {
				this.queue.add(new Evento(i, EventType.GOAL));
			}
			else if(prob>=0.5 && prob<0.8) {
				this.queue.add(new Evento(i, EventType.ESPULSIONE));
			}
			else if(prob>=0.8){
				this.queue.add(new Evento(i, EventType.INFORTUNIO));
			}
		}
	}
	
	public void run() {
		while(!this.queue.isEmpty()) {
			Evento e = this.queue.poll();
			int time = e.getTime();
			EventType type = e.getType();
			System.out.println(type+" "+time);
			
			switch(type) {
			case GOAL:
				if(giocatori1>giocatori2) {
					this.goal1++;
				}
				else if(giocatori1<giocatori2) {
					this.goal2++;
				}
				else if(giocatori1==giocatori2) {
					boolean a = false;
					for(Player p : this.team1) {
						if(p.equals(this.migliore)) {
							this.goal1++;
							a=true;
						}
					}
					if(a==false) {
						this.goal2++;
					}
				}
				break;
				
			case ESPULSIONE:
				double probE = Math.random();
				if(probE<0.6) {
					boolean a = false;
					for(Player p : this.team1) {
						if(p.equals(this.migliore)) {
							this.giocatori1--;
							this.esp1++;
							a=true;
						}
					}
					if(a==false) {
						this.giocatori2--;
						this.esp2++;
					}
				}
				else {
					boolean a = false;
					for(Player p : this.team1) {
						if(p.equals(this.migliore)) {
							this.giocatori2--;
							this.esp2++;
							a=true;
						}
					}
					if(a==false) {
						this.giocatori1--;
						this.esp1++;
					}
				}
				break;
				
			case INFORTUNIO:
				double probI = Math.random();
				if(probI<0.5) {
					for(int i=azioni;i<(azioni+2);i++) {
						double prob = Math.random();
						if(prob<0.5) {
							this.queue.add(new Evento(i, EventType.GOAL));
						}
						else if(prob>=0.5 && prob<0.8) {
							this.queue.add(new Evento(i, EventType.ESPULSIONE));
						}
						else if(prob>=0.8){
							this.queue.add(new Evento(i, EventType.INFORTUNIO));
						}
					}
					azioni+=2;
				}
				else {
					for(int i=azioni;i<(azioni+3);i++) {
						double prob = Math.random();
						if(prob<0.5) {
							this.queue.add(new Evento(i, EventType.GOAL));
						}
						else if(prob>=0.5 && prob<0.8) {
							this.queue.add(new Evento(i, EventType.ESPULSIONE));
						}
						else if(prob>=0.8){
							this.queue.add(new Evento(i, EventType.INFORTUNIO));
						}
					}
					azioni+=3;
				}
				break;
			}
		}
	}

	public int getGoal1() {
		return goal1;
	}

	public int getGoal2() {
		return goal2;
	}

	public int getEsp1() {
		return esp1;
	}

	public int getEsp2() {
		return esp2;
	}
	
}
