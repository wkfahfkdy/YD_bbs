package com.yedam.product.vo;

public class CartVO {
	private String id;
	private String itemCode;
	private int itemQty;
	private String itemName;
	private String itemImage;
	private int salePrice;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	public int getItemQty() {
		return itemQty;
	}
	public void setItemQty(int itemQty) {
		this.itemQty = itemQty;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getItemImage() {
		return itemImage;
	}
	public void setItemImage(String itemImage) {
		this.itemImage = itemImage;
	}
	public int getSalePrice() {
		return salePrice;
	}
	public void setSalePrice(int salePrice) {
		this.salePrice = salePrice;
	}
	
	@Override
	public String toString() {
		return "CartVO [id=" + id + ", itemCode=" + itemCode + ", itemQty=" + itemQty + ", itemName=" + itemName
				+ ", itemImage=" + itemImage + ", salePrice=" + salePrice + "]";
	}
}
