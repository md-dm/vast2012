/**
 * 
 */
package com.md.dm.vi.vast.web;

import java.util.List;

import javax.inject.Inject;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.md.dm.vi.vast.model.MetaRepository;
import com.md.dm.vi.vast.web.treemap.TreeMapVO;

/**
 * @author diego
 * 
 */
@Controller
@RequestMapping("/treemap")
public class TreeMapController {

	@Inject
	private MetaRepository metaRepository;

	
	// db.meta.group({key:{machineClass: true}, initial:{Total:0}, reduce: function(items, prev){prev.Total += 1}});
	// db.meta.group({key:{machineFunction: true}, initial:{Total:0}, reduce: function(items, prev){prev.Total += 1}});
	// 
	
	@RequestMapping(method = RequestMethod.GET, produces = "application/json", value = "data1")
	@ResponseBody
	public TreeMapVO data1() {

//		long countDocuments = metaRepository.countDocuments("meta", new Query(
//				Criteria.where("bussinesUnit").is("headquarters")));

		List bussinesUnitNames = metaRepository
				.distinct("meta", "bussinesUnit");
		List machineClassNames = metaRepository
				.distinct("meta", "machineClass");

		TreeMapVO treeMapVO = new TreeMapVO("root", "BankWorld", "500",
				"#AA5532", "http://www.vacommunity.org/display324", 500);

		for (Object bussinesUnitName : bussinesUnitNames) {

			for (Object machineClassName : machineClassNames) {

				treeMapVO
						.add(new TreeMapVO(
								bussinesUnitName.toString() + "-id",
								bussinesUnitName.toString(),
								"100",
								"#8E7032",
								"http://userserve-ak.last.fm/serve/300x300/11403219.jpg",
								100));
			}

		}

//		treeMapVO.add(new TreeMapVO("album-Mer De Noms", "Mer De Noms", "200",
//				"#906E32",
//				"http://userserve-ak.last.fm/serve/300x300/11393921.jpg", 200));

		return treeMapVO;
	}

}
