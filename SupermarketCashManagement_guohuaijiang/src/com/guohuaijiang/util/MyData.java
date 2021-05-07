package com.guohuaijiang.util;

import com.guohuaijiang.bean.Goods;
import com.guohuaijiang.bean.Member;
import com.guohuaijiang.bean.User;

public class MyData {
	/**
	 *  类的静态变量-用于封装用户的信息
	 */
	public static User userArray[];
	public static Goods goodsArray[];
	public static Goods cartArray[];
	/**
	 *  类的静态变量-用于封装会员的信息
	 */
	public static Member memberArray[];
	/**
	 *  数据的初始化
	 */
	public MyData() {		
		initData();
	}

	private void initData() {
		/**
		 *  1 用户数组进行初始化
		 */
		userArray = new User[10];
		/**
		 * 1.1 对用户数组赋第一个值
		 */
		User user1 = new User();
		user1.setUser_no("1001");
		user1.setUser_name("郭怀江");
		user1.setUser_pwd("123456");
		user1.setUser_type("1");// 收银管理
		userArray[0] = user1;
		/**
		 *  1.2 对用户数组赋第二个值
		 */
		User user2 = new User();
		user2.setUser_no("1002");
		user2.setUser_name("小刘");
		user2.setUser_pwd("123456");
		user2.setUser_type("2");// 库存管理
		userArray[1] = user2;
		/**
		 *  1.3 对用户数组赋第三个值
		 */
		User user3 = new User();
		user3.setUser_no("1003");
		user3.setUser_name("王五");
		user3.setUser_pwd("123456");
		user3.setUser_type("0");// 无用户类型
		userArray[2] = user3;
		
		/**
		 *对购物车数组进行初始化
		 */
		cartArray  =new Goods[50];
		
		/**
		 * 对商品数组的初始化
		 */
		goodsArray = new Goods[50];
        /**
         * 对第一个商品数组赋值
         */
		
		Goods goods1 = new Goods();
		goods1.setGoods_no("1003");
		goods1.setGoods_name("脉动水蜜桃");
		goods1.setGoods_price(7.0);
		goods1.setGoods_unit("1.5l");
		goods1.setGoods_num(50);
		goodsArray[0] = goods1;
		/**
		 * 对第二个商品数组赋值
		 */
		Goods goods2 = new Goods();
		goods2.setGoods_no("1004");
		goods2.setGoods_name("旺仔苹果Q糖");
		goods2.setGoods_price(2.5);
		goods2.setGoods_unit("70g");
		goods2.setGoods_num(70);
		goodsArray[1] = goods2;
		/**
		 * 对第三个商品数组赋值
		 */
		Goods goods3 = new Goods();
		goods3.setGoods_no("1001");
		goods3.setGoods_name("吉百芝麻油");
		goods3.setGoods_price(9.5);
		goods3.setGoods_unit("125ml");
		goods3.setGoods_num(20);
		goodsArray[2] = goods3;
		/**
		 * 对第四个商品数组赋值
		 */
		Goods goods4 = new Goods();
		goods4.setGoods_no("1002");
		goods4.setGoods_name("康帅傅泡面");
		goods4.setGoods_price(1.5);
		goods4.setGoods_unit("13g");
		goods4.setGoods_num(200);
		goodsArray[3] = goods4;
		/**
		 * 对第五个商品数组赋值
		 */
		Goods goods5 = new Goods();
		goods5.setGoods_no("1007");
		goods5.setGoods_name("雷碧");
		goods5.setGoods_price(1.0);
		goods5.setGoods_unit("400g");
		goods5.setGoods_num(15);
		goodsArray[4] = goods5;
		/**
		 * 对第六个商品数组赋值
		 */
		Goods goods6 = new Goods();
		goods6.setGoods_no("1005");
		goods6.setGoods_name("桃李熟切片");
		goods6.setGoods_price(6.5);
		goods6.setGoods_unit("400g");
		goods6.setGoods_num(10);
		goodsArray[5] = goods6;
		/**
		 * 对第七个商品数组赋值
		 */
		Goods goods7 = new Goods();
		goods7.setGoods_no("1006");
		goods7.setGoods_name("白玉黄豆芽");
		goods7.setGoods_price(6.5);
		goods7.setGoods_unit("400g");
		goods7.setGoods_num(10);
		goodsArray[6] = goods7;
				
		
		memberArray = new Member[50];
		
		Member member1=new Member();
		member1.setMember_no("0001");
		member1.setMember_integral(10000);
		memberArray[0]=member1;
		
		Member member2=new Member();
		member2.setMember_no("0002");
		member2.setMember_integral(2000);
		memberArray[1]=member2;
	}

}
