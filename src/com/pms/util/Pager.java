package com.pms.util;

import java.util.List;

/**
 * @author sunyu
 */

public class Pager<T>
{
	/**
	 * currentPage
	 */
	private int currentPage;
	/**
	 * pagesize
	 */
	private int pageSize = 10;
	/**
	 * totalRecord
	 */
	private int totalRecord;
	/**
	 *totalPage
	 */
	private int totalPage;
	/**
	 * 
	 */
	private List<T> datas;
	/**
	 * 
	 * @return
	 */
	public int getCurrentPage()
	{
		return currentPage;
	}
	/**
	 * @param currentPage the currentPage to set
	 */
	public void setCurrentPage(int currentPage)
	{
		this.currentPage = currentPage;
	}
	/**
	 * @return the pageSize
	 */
	public int getPageSize()
	{
		return pageSize;
	}
	/**
	 * @param pageSize the pageSize to set
	 */
	public void setPageSize(int pageSize)
	{
		this.pageSize = pageSize;
	}
	/**
	 * @return the totalRecord
	 */
	public int getTotalRecord()
	{
		return totalRecord;
	}
	/**
	 * @param totalRecord the totalRecord to set
	 */
	public void setTotalRecord(int totalRecord)
	{
		this.totalRecord = totalRecord;
	}
	/**
	 * @return the totalPage
	 */
	public int getTotalPage()
	{
		return totalPage;
	}
	/**
	 * @param totalPage the totalPage to set
	 */
	public void setTotalPage()
	{
		if (this.totalRecord % this.pageSize == 0)
		{
			this.totalPage = this.totalRecord / this.pageSize;
		} else
		{
			this.totalPage = this.totalRecord / this.pageSize + 1;
		}
		//this.totalPage = totalPage;
	}
	/**
	 * @return the datas
	 */
	public List<T> getDatas()
	{
		return datas;
	}
	/**
	 * @param datas the datas to set
	 */
	public void setDatas(List<T> datas)
	{
		this.datas = datas;
	}
	
	
}
