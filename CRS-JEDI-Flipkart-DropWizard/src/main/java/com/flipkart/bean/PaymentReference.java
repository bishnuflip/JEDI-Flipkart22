package com.flipkart.bean;

public class PaymentReference {
	private int referenceNo;
	private String payeeName;
	private float amount;
	private int paymentStatus;
	/**
	 * @return the referenceNo
	 */
	public int getReferenceNo() {
		return referenceNo;
	}
	/**
	 * @param referenceNo the referenceNo to set
	 */
	public void setReferenceNo(int referenceNo) {
		this.referenceNo = referenceNo;
	}
	/**
	 * @return the payeeName
	 */
	public String getPayeeName() {
		return payeeName;
	}
	/**
	 * @param payeeName the payeeName to set
	 */
	public void setPayeeName(String payeeName) {
		this.payeeName = payeeName;
	}
	/**
	 * @return the amount
	 */
	public float getAmount() {
		return amount;
	}
	/**
	 * @param amount the amount to set
	 */
	public void setAmount(float amount) {
		this.amount = amount;
	}
	/**
	 * @return the paymentStatus
	 */
	public int getPaymentStatus() {
		return paymentStatus;
	}
	/**
	 * @param paymentStatus the paymentStatus to set
	 */
	public void setPaymentStatus(int paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

}
