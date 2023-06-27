package it.polito.tdp.PremierLeague.model;

import java.util.Objects;

public class Coppia {
	
	private Player p1;
	private int t1;
	private double e1;
	private Player p2;
	private int t2;
	private double e2;
	
	public Coppia(Player p1, int t1, double e1, Player p2, int t2, double e2) {
		super();
		this.p1 = p1;
		this.t1 = t1;
		this.e1 = e1;
		this.p2 = p2;
		this.t2 = t2;
		this.e2 = e2;
	}

	public Player getP1() {
		return p1;
	}

	public void setP1(Player p1) {
		this.p1 = p1;
	}

	public int getT1() {
		return t1;
	}

	public void setT1(int t1) {
		this.t1 = t1;
	}

	public double getE1() {
		return e1;
	}

	public void setE1(double e1) {
		this.e1 = e1;
	}

	public Player getP2() {
		return p2;
	}

	public void setP2(Player p2) {
		this.p2 = p2;
	}

	public int getT2() {
		return t2;
	}

	public void setT2(int t2) {
		this.t2 = t2;
	}

	public double getE2() {
		return e2;
	}

	public void setE2(double e2) {
		this.e2 = e2;
	}

	@Override
	public int hashCode() {
		return Objects.hash(e1, e2, p1, p2, t1, t2);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coppia other = (Coppia) obj;
		return Double.doubleToLongBits(e1) == Double.doubleToLongBits(other.e1)
				&& Double.doubleToLongBits(e2) == Double.doubleToLongBits(other.e2) && Objects.equals(p1, other.p1)
				&& Objects.equals(p2, other.p2) && t1 == other.t1 && t2 == other.t2;
	}

	@Override
	public String toString() {
		return "Coppia [p1=" + p1 + ", t1=" + t1 + ", e1=" + e1 + ", p2=" + p2 + ", t2=" + t2 + ", e2=" + e2 + "]";
	}
	
}
