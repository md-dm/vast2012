package com.md.dm.vi.vast.d3.valueobject;

import java.util.List;

public class TreeVO extends NodeVO{
	
	private List<NodeVO> children;
	
	public TreeVO(String name, List<NodeVO> children) {
		this.children = children;
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public List<NodeVO> getChildren() {
		return children;
	}

	public TreeVO(List<NodeVO> children) {
		super();
		this.children = children;
	}

//	@Override
//	public boolean isLeaf() {
//		return false;
//	}
	
	
	
	
	
	

}
