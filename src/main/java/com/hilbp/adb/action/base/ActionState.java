package com.hilbp.adb.action.base;

import java.util.List;

import com.hilbp.adb.entity.Action;
import com.hilbp.adb.entity.Coord;
import com.hilbp.adb.entity.Node;

public abstract class ActionState {
	
	protected Action action;
	protected List<Node> nodes;
	protected List<Coord> coords;
	
	public Action getAction() {
		
		return this.action;
	}
	
	public List<Node> getNodes() {
		return this.nodes;
	}
	
	public List<Coord> getCoords() {
		return this.coords;
	}
	
	public void setState(Action action, List<Node> nodes, List<Coord> coords) {
		this.action = action;
		this.nodes = nodes;
		this.coords = coords;
	}
	
	public void clear() {
		
		this.action = null;
		this.coords = null;
		this.nodes = null;

	}
	
	public void setAction(Action action) {
		
		this.action = action;
	}

	public void setNodes(List<Node> nodes) {
		this.nodes = nodes;
	}
	
	public void setCoords(List<Coord> coords) {
		this.coords = coords;
	}
	 
}
