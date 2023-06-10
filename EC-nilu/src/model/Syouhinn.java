package model;

import java.io.Serializable;

public class Syouhinn implements Serializable{
		private long user_id,kanri_id;
		private String goods,goods_detail;
		private int selling_price,cost_price,stock;
		private String nsin;
		
		public Syouhinn() {
		}
		public Syouhinn(long kanri_id, String goods, String goods_detail, int selling_price,
				int cost_price, int stock,long user_id) {
			this.user_id = user_id;
			this.kanri_id = kanri_id;
			this.goods = goods;
			this.goods_detail = goods_detail;
			this.selling_price = selling_price;
			this.cost_price = cost_price;
			this.stock = stock;
		}
		public Syouhinn(long kanri_id, String goods, String goods_detail, int selling_price,
				int cost_price, int stock,long user_id,String nsin) {
			this.user_id = user_id;
			this.kanri_id = kanri_id;
			this.goods = goods;
			this.goods_detail = goods_detail;
			this.selling_price = selling_price;
			this.cost_price = cost_price;
			this.stock = stock;
			this.nsin = nsin;
		}
		public Syouhinn(long kanri_id, String goods, String goods_detail, int selling_price, int cost_price,
				int stock) {
			this.kanri_id = kanri_id;
			this.goods = goods;
			this.goods_detail = goods_detail;
			this.selling_price = selling_price;
			this.cost_price = cost_price;
			this.stock = stock;
		}
		public long getUser_id() {
			return user_id;
		}
		public void setUser_id(long user_id) {
			this.user_id = user_id;
		}
		public long getKanri_id() {
			return kanri_id;
		}
		public void setKanri_id(long kanri_id) {
			this.kanri_id = kanri_id;
		}
		public String getGoods() {
			return goods;
		}
		public void setGoods(String goods) {
			this.goods = goods;
		}
		public String getGoods_detail() {
			return goods_detail;
		}
		public void setGoods_detail(String goods_detail) {
			this.goods_detail = goods_detail;
		}
		public int getSelling_price() {
			return selling_price;
		}
		public void setSelling_price(int selling_price) {
			this.selling_price = selling_price;
		}
		public int getCost_price() {
			return cost_price;
		}
		public void setCost_price(int cost_price) {
			this.cost_price = cost_price;
		}
		public int getStock() {
			return stock;
		}
		public void setStock(int stock) {
			this.stock = stock;
		}
		public String getNsin() {
			return nsin;
		}
		public void setNsin(String nsin) {
			this.nsin = nsin;
		}		
}
