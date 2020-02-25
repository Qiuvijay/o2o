package com.qwj.o2o.entity;

import java.util.Date;

public class ProductImg {
	private Long poductImgId;
	private String imgAddr;
	private String imdDesc;
	private Integer priority;
	private Date createTime;
	private Long productId;

	public Long getPoductImgId() {
		return poductImgId;
	}

	public void setPoductImgId(Long poductImgId) {
		this.poductImgId = poductImgId;
	}

	public String getImgAddr() {
		return imgAddr;
	}

	public void setImgAddr(String imgAddr) {
		this.imgAddr = imgAddr;
	}

	public String getImdDesc() {
		return imdDesc;
	}

	public void setImdDesc(String imdDesc) {
		this.imdDesc = imdDesc;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

}
