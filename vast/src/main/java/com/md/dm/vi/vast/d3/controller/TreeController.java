package com.md.dm.vi.vast.d3.controller;

import java.util.ArrayList;
import java.util.Iterator;

import javax.inject.Inject;

import org.springframework.data.mongodb.core.mapreduce.GroupByResults;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.md.dm.vi.vast.d3.valueobject.GroupVO;
import com.md.dm.vi.vast.d3.valueobject.LeafVO;
import com.md.dm.vi.vast.d3.valueobject.NodeVO;
import com.md.dm.vi.vast.d3.valueobject.TreeVO;
import com.md.dm.vi.vast.model.MetaRepository;

@Controller
@RequestMapping("/d3/tree")
public class TreeController {

	@Inject
	private MetaRepository metaRepository;
	
	// db.meta.group({key:{machineClass: true}, initial:{Total:0}, reduce:
	// function(items, prev){prev.Total += 1}});
	// db.meta.group({key:{machineFunction: true}, initial:{Total:0}, reduce:
	// function(items, prev){prev.Total += 1}});
	//

	@RequestMapping(method = RequestMethod.GET, produces = "application/json", value = "tree1")
	@ResponseBody
	public TreeVO tree1() {

		GroupByResults<GroupVO> group = metaRepository.group("meta");
		Iterator<GroupVO> iterator = group.iterator();
		
		ArrayList<NodeVO> childs = new ArrayList<NodeVO>();
		TreeVO root = new TreeVO("headquarter", childs);
		
		while(iterator.hasNext()){
			GroupVO next = iterator.next();
			TreeVO child = (TreeVO)root.getChild(next.getBussinesUnit());
			if (child == null) {
				child = new TreeVO(next.getBussinesUnit(), new ArrayList<NodeVO>());
				root.getChildren().add(child);
			}
			
			NodeVO child2 = child.getChild(next.getMachineClass());
			if(child2 == null) {
				child2 = new LeafVO(next.getMachineClass(), next.getCount());
				child.getChildren().add(child2);
			}
		}
		
		return root;
		
//		LeafVO child1 = new LeafVO("child1", 10);
//		ArrayList<NodeVO> childs = new ArrayList<NodeVO>();
//		childs.add(child1);
//		return new TreeVO("headquarter", childs);
	}
	
	@RequestMapping(method = RequestMethod.GET, produces = "application/json", value = "group")
	@ResponseBody
	public GroupByResults<GroupVO> group() {

		GroupByResults<GroupVO> group = metaRepository.group("meta");
		return group;
	}
	
}
