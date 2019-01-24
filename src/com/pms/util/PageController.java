package com.pms.util;




public class PageController
{
	
	private int pageSize = Constants.pageSize; 

	private int totalRecord; 
	private int totalPage; 
	private int currentPage;

	public int getPageSize()
	{
		return pageSize;
	}

	public void setPageSize(int pageSize)
	{
		this.pageSize = pageSize;
		
	}

	public int getTotalRecord()
	{
		return totalRecord;
	}
	public void setTotalRecord(int totalRecord)
	{
		this.totalRecord = totalRecord;
	}
	
	
	public int getTotalPage()
	{
		return totalPage;
	}

	public void setTotalPage()
	{
		
		if (this.totalRecord % this.pageSize == 0)
		{
			this.totalPage = this.totalRecord / this.pageSize;
		} else
		{
			this.totalPage = this.totalRecord / this.pageSize + 1;
		}
		System.out.println("pc countpage:"+this.totalRecord);
	}

	public int getCurrentPage()
	{
		return currentPage;
	}

	public void setCurrentPage(int currentPage)
	{
		this.currentPage = currentPage;
	}

}
