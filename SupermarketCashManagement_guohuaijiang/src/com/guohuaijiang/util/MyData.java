package com.guohuaijiang.util;

import com.guohuaijiang.bean.Goods;
import com.guohuaijiang.bean.Member;
import com.guohuaijiang.bean.User;

public class MyData {
	/**
	 *  ��ľ�̬����-���ڷ�װ�û�����Ϣ
	 */
	public static User userArray[];
	public static Goods goodsArray[];
	public static Goods cartArray[];
	/**
	 *  ��ľ�̬����-���ڷ�װ��Ա����Ϣ
	 */
	public static Member memberArray[];
	/**
	 *  ���ݵĳ�ʼ��
	 */
	public MyData() {		
		initData();
	}

	private void initData() {
		/**
		 *  1 �û�������г�ʼ��
		 */
		userArray = new User[10];
		/**
		 * 1.1 ���û����鸳��һ��ֵ
		 */
		User user1 = new User();
		user1.setUser_no("1001");
		user1.setUser_name("������");
		user1.setUser_pwd("123456");
		user1.setUser_type("1");// ��������
		userArray[0] = user1;
		/**
		 *  1.2 ���û����鸳�ڶ���ֵ
		 */
		User user2 = new User();
		user2.setUser_no("1002");
		user2.setUser_name("С��");
		user2.setUser_pwd("123456");
		user2.setUser_type("2");// ������
		userArray[1] = user2;
		/**
		 *  1.3 ���û����鸳������ֵ
		 */
		User user3 = new User();
		user3.setUser_no("1003");
		user3.setUser_name("����");
		user3.setUser_pwd("123456");
		user3.setUser_type("0");// ���û�����
		userArray[2] = user3;
		
		/**
		 *�Թ��ﳵ������г�ʼ��
		 */
		cartArray  =new Goods[50];
		
		/**
		 * ����Ʒ����ĳ�ʼ��
		 */
		goodsArray = new Goods[50];
        /**
         * �Ե�һ����Ʒ���鸳ֵ
         */
		
		Goods goods1 = new Goods();
		goods1.setGoods_no("1003");
		goods1.setGoods_name("����ˮ����");
		goods1.setGoods_price(7.0);
		goods1.setGoods_unit("1.5l");
		goods1.setGoods_num(50);
		goodsArray[0] = goods1;
		/**
		 * �Եڶ�����Ʒ���鸳ֵ
		 */
		Goods goods2 = new Goods();
		goods2.setGoods_no("1004");
		goods2.setGoods_name("����ƻ��Q��");
		goods2.setGoods_price(2.5);
		goods2.setGoods_unit("70g");
		goods2.setGoods_num(70);
		goodsArray[1] = goods2;
		/**
		 * �Ե�������Ʒ���鸳ֵ
		 */
		Goods goods3 = new Goods();
		goods3.setGoods_no("1001");
		goods3.setGoods_name("����֥����");
		goods3.setGoods_price(9.5);
		goods3.setGoods_unit("125ml");
		goods3.setGoods_num(20);
		goodsArray[2] = goods3;
		/**
		 * �Ե��ĸ���Ʒ���鸳ֵ
		 */
		Goods goods4 = new Goods();
		goods4.setGoods_no("1002");
		goods4.setGoods_name("��˧������");
		goods4.setGoods_price(1.5);
		goods4.setGoods_unit("13g");
		goods4.setGoods_num(200);
		goodsArray[3] = goods4;
		/**
		 * �Ե������Ʒ���鸳ֵ
		 */
		Goods goods5 = new Goods();
		goods5.setGoods_no("1007");
		goods5.setGoods_name("�ױ�");
		goods5.setGoods_price(1.0);
		goods5.setGoods_unit("400g");
		goods5.setGoods_num(15);
		goodsArray[4] = goods5;
		/**
		 * �Ե�������Ʒ���鸳ֵ
		 */
		Goods goods6 = new Goods();
		goods6.setGoods_no("1005");
		goods6.setGoods_name("��������Ƭ");
		goods6.setGoods_price(6.5);
		goods6.setGoods_unit("400g");
		goods6.setGoods_num(10);
		goodsArray[5] = goods6;
		/**
		 * �Ե��߸���Ʒ���鸳ֵ
		 */
		Goods goods7 = new Goods();
		goods7.setGoods_no("1006");
		goods7.setGoods_name("����ƶ�ѿ");
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
