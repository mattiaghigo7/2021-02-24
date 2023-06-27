package it.polito.tdp.PremierLeague.model;

import java.util.*;
import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import it.polito.tdp.PremierLeague.db.PremierLeagueDAO;

public class Model {
	
	private PremierLeagueDAO dao;
	
	private List<Match> allMatches;
	private List<Player> allPlayer;
	private Map<Integer,Player> playerMap;
	
	private Graph<Player, DefaultWeightedEdge> grafo;
	private List<Player> vertici;
	private List<Coppia> archi;
	
	private double delta;
	
	Simulatore sim;
	private int t1;
	private int t2;
	private List<Player> team1;
	private List<Player> team2;
	
	public Model() {
		this.dao=new PremierLeagueDAO();
		this.allMatches=new ArrayList<>(dao.listAllMatches());
		this.allPlayer=new ArrayList<>(dao.listAllPlayers());
		this.playerMap=new HashMap<>();
		for(Player p : this.allPlayer) {
			this.playerMap.put(p.playerID, p);
		}
	}
	
	public void creaGrafo(Match m) {
		this.grafo=new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);
		this.vertici=new ArrayList<>(dao.getVertici(m.matchID, playerMap));
		Graphs.addAllVertices(this.grafo, this.vertici);
//		System.out.println(this.grafo.vertexSet().size());
		
		this.archi=new ArrayList<>(dao.getArchi(m.matchID, playerMap));
		for(Coppia c : this.archi) {
			double differenza = c.getE1()-c.getE2();
			if(differenza>=0) {
				Graphs.addEdgeWithVertices(this.grafo, c.getP1(), c.getP2(), differenza);
			}
			else if(differenza<0) {
				Graphs.addEdgeWithVertices(this.grafo, c.getP2(), c.getP1(), -differenza);
			}
		}
//		System.out.println(this.grafo.edgeSet().size());
		this.t1=dao.getTeamHome(m.matchID);
		this.t1=dao.getTeamAway(m.matchID);
		this.team1=dao.getPlayer1(m.matchID, t1, playerMap);
		this.team1=dao.getPlayer1(m.matchID, t2, playerMap);
	}
	
	public List<Match> getAllMatches(){
		this.allMatches.sort(null);
		return this.allMatches;
	}
	
	public int getVerticiSize() {
		return this.grafo.vertexSet().size();
	}
	
	public int getArchiSize() {
		return this.grafo.edgeSet().size();
	}
	
	public Player getGiocatoreMigliore() {
		Player migliore = null;
		delta = 0;
		for(Player p : this.vertici) {
			double diff = 0;
			for(DefaultWeightedEdge d : this.grafo.edgeSet()) {
				if(grafo.getEdgeSource(d).equals(p)) {
					diff+=grafo.getEdgeWeight(d);
				}
				else if(grafo.getEdgeTarget(d).equals(p)) {
					diff-=grafo.getEdgeWeight(d);
				}
			}
			if(diff>delta) {
				migliore=p;
				delta=diff;
			}
		}
		return migliore;
	}
	
	public double getDelta() {
		return delta;
	}
	
	public void simula(int N) {
		sim=new Simulatore(N, getGiocatoreMigliore(), team1, team2);
		sim.initialize();
		sim.run();
	}
	
	public int getGoal1() {
		return sim.getGoal1();
	}

	public int getGoal2() {
		return sim.getGoal2();
	}

	public int getEsp1() {
		return sim.getEsp1();
	}

	public int getEsp2() {
		return sim.getEsp2();
	}
}
