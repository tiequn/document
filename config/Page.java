package com.keishengit.util;

import java.util.List;

public class Page<T> {

	private Integer totalPage; 		// 总页码
	private Integer pageNo;			// 当前页码
	private List<T> items;         	// 当前页面的数据
	private Integer pageSize = 5;   // 每页显示的数量
	private Integer start;          // 起始行
	
	public Page(int pageNo, int count) {
		
		// 计算总页面
		int totalPage = count / pageSize;
		if(count % pageSize !=0) {
			totalPage++;
		}

		// 如果当前页码大于总页码，则取值最后一页
		if(pageNo > totalPage) {
			pageNo = totalPage;
		}
		
		// 如果小于1，则取值第一页
		if(pageNo < 1) {
			pageNo = 1;
		}
		
		//  获得起始页数  
		this.start = (pageNo - 1) * this.pageSize; 
		this.pageNo = pageNo;
		this.totalPage = totalPage;
	}

	public Integer getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public List<T> getItems() {
		return items;
	}

	public void setItems(List<T> items) {
		this.items = items;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

}
