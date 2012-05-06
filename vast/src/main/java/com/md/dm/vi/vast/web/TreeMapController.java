/**
 * 
 */
package com.md.dm.vi.vast.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.md.dm.vi.vast.web.treemap.TreeMapVO;

/**
 * @author diego
 * 
 */
@Controller
@RequestMapping("/treemap")
public class TreeMapController {

	@RequestMapping(method = RequestMethod.GET, produces = "application/json", value = "data1")
	@ResponseBody
	public TreeMapVO data1() {

		TreeMapVO treeMapVO = new TreeMapVO("album-Above",
				"Above", "500", "#AA5532",
				"http://userserve-ak.last.fm/serve/300x300/32349839.jpg", 500);
		
		treeMapVO.add(new TreeMapVO("album-Thirteenth Step", "Thirteenth Step",
				"100", "#8E7032",
				"http://userserve-ak.last.fm/serve/300x300/11403219.jpg", 100));
		treeMapVO.add(new TreeMapVO("album-Mer De Noms", "Mer De Noms",
				"200", "#906E32",
				"http://userserve-ak.last.fm/serve/300x300/11393921.jpg", 200));

		return treeMapVO;
	}

}
