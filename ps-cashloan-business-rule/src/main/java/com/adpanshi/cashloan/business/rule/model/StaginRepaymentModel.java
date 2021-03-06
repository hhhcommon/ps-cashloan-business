package com.adpanshi.cashloan.business.rule.model;

import java.io.Serializable;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2017年12月25日下午10:01:58
 **/
public class StaginRepaymentModel implements Serializable{

	private static final long serialVersionUID = 1L;

	private String createTime;//创建时间(改为订单实际还款时间)
	private String orderNo;   //订单号
	private Double interests; //利息
	private String state;     //状态
	private Long borrowId;   //订单id
	private String repayWay; //还款方式 
	private Double amount;   //分期总金额
	private Double penaltyAmout;//逾期费
	private Double fee;         //手续费
	
	private String byStages;//第N期
	
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public Double getInterests() {
		return interests;
	}
	public void setInterests(Double interests) {
		this.interests = interests;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Long getBorrowId() {
		return borrowId;
	}
	public void setBorrowId(Long borrowId) {
		this.borrowId = borrowId;
	}
	public String getRepayWay() {
		return repayWay;
	}
	public void setRepayWay(String repayWay) {
		this.repayWay = repayWay;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public Double getPenaltyAmout() {
		return penaltyAmout;
	}
	public void setPenaltyAmout(Double penaltyAmout) {
		this.penaltyAmout = penaltyAmout;
	}
	public Double getFee() {
		return fee;
	}
	public void setFee(Double fee) {
		this.fee = fee;
	}
	public String getByStages() {
		return byStages;
	}
	public void setByStages(String byStages) {
		this.byStages = byStages;
	}
	
}
