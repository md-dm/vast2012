package com.md.dm.vi.vast.d3.valueobject;

public class LeafVO extends NodeVO{
	
	private int size;

	
	public LeafVO(String name, int size) {
		super();
		this.name = name;
		this.size = size;
	}
	
	
	public int getSize() {
		return size;
	}

//	@Override
//	public boolean isLeaf() {
//		return true;
//	}
	
	

}
