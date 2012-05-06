package com.md.dm.vi.vast.web.treemap;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Returns Data from server side to implements TreeMap from thejit project.
 * 
 * 
 * @author diego
 * 
 * @see http://thejit.org/
 */
public class TreeMapVO {

	private List<TreeMapVO> children;
	private DataVO data;
	private String id;
	private String name;

	public TreeMapVO(String id, String name, String playcount, String color,
			String image, int area) {
		children = new ArrayList<TreeMapVO>();
		this.id = id;
		this.name = name;
		this.data = new DataVO(playcount, color, image, area);
	}

	/**
	 * @return the children
	 */
	public List<TreeMapVO> getChildren() {
		return children;
	}

	/**
	 * @param children
	 *            the children to set
	 */
	public void setChildren(List<TreeMapVO> children) {
		this.children = children;
	}

	/**
	 * @return the data
	 */
	public DataVO getData() {
		return data;
	}

	/**
	 * @param data
	 *            the data to set
	 */
	public void setData(DataVO data) {
		this.data = data;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param treeMapVO
	 * @return
	 * @see java.util.List#add(java.lang.Object)
	 */
	public boolean add(TreeMapVO treeMapVO) {
		return children.add(treeMapVO);
	}

	@JsonAutoDetect
	public static class DataVO {

		private String playcount;
		@JsonProperty("$color")
		private String color;
		private String image;
		@JsonProperty("$area")
		private int area;

		public DataVO(String playcount, String color, String image, int area) {
			super();
			this.playcount = playcount;
			this.color = color;
			this.image = image;
			this.area = area;
		}

		/**
		 * @return the playcount
		 */
		public String getPlaycount() {
			return playcount;
		}

		/**
		 * @param playcount
		 *            the playcount to set
		 */
		public void setPlaycount(String playcount) {
			this.playcount = playcount;
		}

		/**
		 * @return the color
		 */
		public String getColor() {
			return color;
		}

		/**
		 * @param color
		 *            the color to set
		 */
		public void setColor(String color) {
			this.color = color;
		}

		/**
		 * @return the image
		 */
		public String getImage() {
			return image;
		}

		/**
		 * @param image
		 *            the image to set
		 */
		public void setImage(String image) {
			this.image = image;
		}

		/**
		 * @return the area
		 */
		public int getArea() {
			return area;
		}

		/**
		 * @param area
		 *            the area to set
		 */
		public void setArea(int area) {
			this.area = area;
		}
	}
}
