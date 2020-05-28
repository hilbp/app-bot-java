package com.hilbp.adb.state;

import java.util.ArrayList;
import java.util.List;

import com.hilbp.adb.entity.Action;
import com.hilbp.adb.entity.Coord;
import com.hilbp.adb.entity.Node;
import com.hilbp.adb.util.UiAutoMatorUtil;

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
	
	public List<Coord> getCoordList() {
		return this.coords;
	}
	
	public List<Coord> getCoordListFromNodes() {
		List<Coord> coords = new ArrayList<Coord>();
		for(Node node : this.nodes) {
			Coord coord = UiAutoMatorUtil.boundsToCoord(node.getBounds());
			coords.add(coord);
		}
		return coords;
	}
	
	public void setState(Action action, List<Node> nodes, List<Coord> coords) {
		this.action = action;
		this.nodes = nodes;
		this.coords = coords;
	}
	
	public void clear() {
		this.action = null;
		this.nodes = null;
		this.coords = null;
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
