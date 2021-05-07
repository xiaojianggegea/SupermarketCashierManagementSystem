package com.guohuaijiang.oerator;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import com.guohuaijiang.bean.Goods;
import com.guohuaijiang.bean.Member;
/**
*
* @������ ������
* @��� ������Ľӿڵ�ʵ����
* @��ʼ���� 2020-07-19
* @�������� 2020-07-29
* @�汾 v1.0
* @˵�� ���ڷ�װ�û��Ĳ�����Ϣ-���ܵ�ʵ��
*/
import com.guohuaijiang.bean.User;
import com.guohuaijiang.service.UserService;
import com.guohuaijiang.service.UserServiceImpl;
import com.guohuaijiang.util.MyData;

public class OperatorImpl implements Operator {

	private static User login_user = null;

	/**
	 * ϵͳ����������
	 */
	@Override
	public void stract() {
		displaySystemGUI();
	}

	/**
	 * ��ʾϵͳ��������
	 */
	private void displaySystemGUI() {

		System.out.println("**********��ӭʹ�� ��������ϵͳ **********");
		System.out.println("  1 ��½      ");
		System.out.println(" 2 �˳���½  ");
		System.out.print("����ѡ�� 1��2��: ");
		boolean flag = true;
		do {
			Scanner scanner = new Scanner(System.in);
			String numString = scanner.next();

			switch (numString) {
			case "1":
				showLoginGUI();
				flag = false;
				break;
			case "2":
				System.out.println("��ллʹ��!��");
				System.exit(0);
				flag = false;
				break;
			default:
				System.out.println("��������1��2��:");
				break;
			}
		} while (flag);
	}

	/**
	 * ��ʾ��¼���ܵĽ���
	 */
	private void showLoginGUI() {

		boolean flag = true;
		do {
			//�û����ݽ���
			Scanner scanner = new Scanner(System.in);
			System.out.print("���û����ơ�:");
			String username = scanner.next();
			System.out.print("���û����롿:");
			String userpwd = scanner.next();

			//�����ݴ��ݸ�ҵ�����д���
			UserService userService = new UserServiceImpl();
			login_user = userService.loginByUsernameAndUsernamepwd(username, userpwd);
			if (login_user != null) {
				
				/**
				 * �������ѡ��
				 */
				if (login_user.getUser_type() != null && login_user.getUser_type().equals("1")) {
					System.out.println("����½�ɹ�!��");
					System.out.println("********��ӭʹ����������ϵͳ********");
					cashManageGUI();
					flag = false;
				} else if (login_user.getUser_type() != null && login_user.getUser_type().equals("2")) {
					System.out.println("����½�ɹ�!��");
					System.out.println("********��ӭʹ�ÿ�����ϵͳ********");
					storeManageGUI();
					flag = false;
				} else {
					System.out.println("����ɫδ����!�� ");
				}
			} else {
				System.out.println("���û�����������󣬵�½ʧ��!����������:�� ");
				flag = true;
			}
		} while (flag);
	}

	/**
	 * ��ʾ���������������
	 */
	private void cashManageGUI() {
		System.out.println("����ѡ��Ҫ���еĲ���:�� ");
		System.out.println("��1.ɨ����Ʒ  2.�޸�����   3.����     4.�˳�   5 �鿴���ﳵ��");
		boolean flag = true;
		do {
			Scanner scanner = new Scanner(System.in);
			String numString = scanner.next();
			switch (numString) {
			case "1":
				scannerGoods();
				flag = false;
				break;
			case "2":
				reviseTheQuantity();
				flag = false;
				break;
			case "3":
				settleAccounts();
				flag = false;
				break;
			case "4":
				displaySystemGUI();
				System.out.println("��ллʹ��!��");
				flag = false;
				break;
			case "5":
				showCartArray();
				System.out.println("��ллʹ��!��");
				flag = false;
				break;
			default:
				System.out.println("��������1-5֮�������:��");
				break;
			}
		} while (flag);
	}

	/**
	 * ��ʾ���ﳵ��ɨ�����Ʒ
	 */
	private void showCartArray() {
		System.out.println("********** ��Ĺ��ﳵ **********");
		Goods cartArray[] = MyData.cartArray;
		System.out.println("��Ʒ����\t\t\t����\t����\t���");
		for (Goods goods : cartArray) {
			if (goods != null) {
				System.out.println(
						"------------------------------------------------------------------------------------");
				System.out.println("(" + goods.getGoods_no() + ")" + goods.getGoods_name() + goods.getGoods_unit()
						+ "\t" + goods.getGoods_price() + "\t" + goods.getGoods_num() + "\t"
						+ (goods.getGoods_price() * goods.getGoods_num()));
				System.out.println(
						"------------------------------------------------------------------------------------");
			}
		}
		cashManageGUI();
	}

	/**
	 * ����
	 */
	private void settleAccounts() {
		// �жϹ��ﳵ�Ƿ�Ϊ��
		boolean isEmpty = false;
		for (int i = 0; i < MyData.cartArray.length; i++) {
			if (MyData.cartArray[i] != null) {
				isEmpty = true;
				break;
			}

		}
	//  �Թ��ﳵ״̬������Ӧ
		if (isEmpty) {
			//���ﳵ��Ϊ�գ����㹺�ﳵ�е��ܽ��
			double sum = 0;
			for (int i = 0; i < MyData.cartArray.length; i++) {
				Goods temp_cart_goods = MyData.cartArray[i];
				if (temp_cart_goods != null) {
					double amount = temp_cart_goods.getGoods_num() * temp_cart_goods.getGoods_price();
					sum += amount;
				}
			}
			System.out.println("�ܽ����:" + sum);
			System.out.println("��1 ��ͨ����  2 ��Ա���ˡ�");
			do {
				Scanner scanner = new Scanner(System.in);
				String numString = scanner.next();
				switch (numString) {
				case "1":
					generalSettleAccounts(sum);
					break;
				case "2":
					memberSettleAccounts(sum);
					break;

				default:
					System.out.println("����ѡ��1��2��");
					break;
				}
			} while (true);

		} else {
			//���ﳵΪ��,��ʾ������������
			System.out.println("�����ﳵΪ�գ���ѡ��1ɨ����Ʒ������");
			cashManageGUI();
		}
	}

	/**
	 * ��Ա����
	 */
	private void memberSettleAccounts(double sum) {

		String member_no = "";
		Member cuurent_memberMember = null;
		boolean flag = true;
		do {
			Scanner scanner = new Scanner(System.in);
			System.out.print("����������Ļ�Ա����:��");
			member_no = scanner.next();
			boolean result = false;
			for (int i = 0; i < MyData.memberArray.length; i++) {
				if (MyData.memberArray[i] != null && MyData.memberArray[i].getMember_no().equals(member_no)) {
					cuurent_memberMember = MyData.memberArray[i];
					result = true;
					break;
				}
			}
			if (result) {
				break;
			} else {
				System.out.println("����Ա���Ŵ��󣡣���");
			}

		} while (flag);

		// ����֧�����
		System.out.print("����������Ҫ֧���Ľ��:��");
		double money = 0;
		do {
			try {
				@SuppressWarnings("resource")
				Scanner scanner = new Scanner(System.in);
				money = scanner.nextDouble();
				if (money < sum) {
					System.out.println("��֧������������������!!!��");
					continue;
				}
				break;
			} catch (Exception e) {
				System.out.print("����������ȷ�Ľ��!!!��");
			}
		} while (true);

		// ��ӡСƱ
		System.out.println("********************    ����            ********************");
		Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		String xiaopiaohaoString = simpleDateFormat.format(date);
		System.out.println("��ӪԱ��:" + this.login_user.getUser_no() + "                  СƱ��:" + xiaopiaohaoString);
		System.out.println("���\t ��Ʒ����\t\t\t����\t����\t���");
		System.out.println("---------------------------------------------------------------");
		int totalNum = 0;
		for (int i = 0; i < MyData.cartArray.length; i++) {
			Goods temp_cart_goods = MyData.cartArray[i];
			if (temp_cart_goods != null) {
				String goods_name = "(" + temp_cart_goods.getGoods_no() + ")" + temp_cart_goods.getGoods_name();
				double amount = temp_cart_goods.getGoods_price() + temp_cart_goods.getGoods_num();
				System.out.println((i + 1) + "\t" + goods_name + "\t\t" + temp_cart_goods.getGoods_price() + "\t"
						+ temp_cart_goods.getGoods_num() + "\t" + amount + "\t");
				totalNum += temp_cart_goods.getGoods_num();
			}
		}
		System.out.println("---------------------------------------------------------------");
		System.out.println("�ܽ��:" + totalNum + "\t\t\tӦ��:" + sum);
		System.out.println("ʵ��:" + money + "\t\t\t����:" + (money - sum));
		
		System.out.println("�����ۼƻ��ֳɹ�               ����:" + member_no);
		System.out.println("����ǰ����+���λ���=���Ѻ����");
		int ThisIntegral = (int) sum;
		int AfterConsumptionIntegral = cuurent_memberMember.getMember_integral() + (int) sum;
		System.out.println(
				cuurent_memberMember.getMember_integral() + "+" + ThisIntegral + "=" + AfterConsumptionIntegral);
		for (int i = 0; i < MyData.memberArray.length; i++) {
			Member temp_member = MyData.memberArray[i];
			if (temp_member != null && temp_member.getMember_no().equals(member_no)) {
				temp_member.setMember_integral(AfterConsumptionIntegral);
				MyData.memberArray[i] = temp_member;
				break;
			}
		}

		simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd    HH:mm:ss");
		String dataString = simpleDateFormat.format(date);
		System.out.println(dataString);
		System.out.println("�������Ʊ���СƱ���Ա����衿");
		System.out.println("===============================================================");
		MyData.cartArray = new Goods[50];
		cashManageGUI();
	}

	/**
	 * ��ͨ����
	 */
	private void generalSettleAccounts(double sum) {
		//����֧�����
		System.out.print("����������Ҫ֧���Ľ��:��");
		double money = 0;
		do {
			try {
				@SuppressWarnings("resource")
				Scanner scanner = new Scanner(System.in);
				money = scanner.nextDouble();
				if (money < sum) {
					System.out.println("��֧������������������!!!��");
					continue;
				}
				break;
			} catch (Exception e) {
				System.out.print("����������ȷ�Ľ��!!!��");
			}
		} while (true);
		//��ӡСƱ
		System.out.println("******************** ����  ******************** ");
		Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		String xiaopiaohaoString = simpleDateFormat.format(date);
		System.out.println("��ӪԱ��:" + this.login_user.getUser_no() + "                  СƱ��:" + xiaopiaohaoString);
		System.out.println("���\t ��Ʒ����\t\t\t����\t����\t���");
		System.out.println("---------------------------------------------------------------");
		int totalNum = 0;
		for (int i = 0; i < MyData.cartArray.length; i++) {
			Goods temp_cart_goods = MyData.cartArray[i];
			if (temp_cart_goods != null) {
				String goods_name = "(" + temp_cart_goods.getGoods_no() + ")" + temp_cart_goods.getGoods_name();
				double amount = temp_cart_goods.getGoods_price() + temp_cart_goods.getGoods_num();
				System.out.println((i + 1) + "\t" + goods_name + "\t\t" + temp_cart_goods.getGoods_price() + "\t"
						+ temp_cart_goods.getGoods_num() + "\t" + amount + "\t");
				totalNum += temp_cart_goods.getGoods_num();
			}
		}
		System.out.println("---------------------------------------------------------------");
		System.out.println("�ܽ��:" + totalNum + "\t\t\tӦ��:" + sum);
		System.out.println("ʵ��:" + money + "\t\t\t����:" + (money - sum));

		simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd    HH:mm:ss");
		String dataString = simpleDateFormat.format(date);
		System.out.println(dataString);
		System.out.println("�������Ʊ���СƱ���Ա����衿");
		System.out.println("===============================================================");
		MyData.cartArray = new Goods[50];
		cashManageGUI();
	}

	/**
	 * �޸�����
	 */
	private void reviseTheQuantity() {
		// ���ﳵ���Ƿ��ж���
		boolean flag = false;
		for (int i = 0; i < MyData.cartArray.length; i++) {
			if (MyData.cartArray[i] != null) {
				flag = true;
				break;
			}
		}

		if (flag == true) {
			// ������Ʒ���
			String goods_no = null;
			boolean isExit = true;
			do {
				Scanner scanner = new Scanner(System.in);
				System.out.print("������Ҫ�޸ĵ���Ʒ���:��");
				goods_no = scanner.next();
				// �жϹ��ﳵ���Ƿ��и���Ʒ
				boolean remark = false;
				for (int i = 0; i < MyData.cartArray.length; i++) {
					if (MyData.cartArray[i] != null && MyData.cartArray[i].getGoods_no().equals(goods_no)) {
						remark = true;
						break;
					}
				}
				if (remark == false) {
					System.out.println("��û��ɨ�����Ʒ!��");
					isExit = true;
				} else {
					isExit = false;
				}
			} while (isExit);
			//�����޸�����
			boolean isInteger = true;
			int goods_num = 0;
			do {
				try {
					System.out.print("�������޸�������");
					Scanner scanner = new Scanner(System.in);
					goods_num = scanner.nextInt();
					if (goods_num <= 0) {
						System.out.println("����������ڵ���0�����֣�����");
						isInteger = true;
						continue;
					}
					break;
				} catch (Exception e) {
					System.out.print("����������ȷ������:��");
					isInteger = true;
				}
			} while (isInteger);
			//�������ﳵ�������и���Ʒ������
			boolean isModify = false;
			Goods cart_goods = null;
			for (int i = 0; i < MyData.cartArray.length; i++) {
				Goods temp_cart_goods = MyData.cartArray[i];
				if (temp_cart_goods != null && temp_cart_goods.getGoods_no().equals(goods_no)) {
					temp_cart_goods.setGoods_num(goods_num);
					MyData.cartArray[i] = temp_cart_goods;
					cart_goods = MyData.cartArray[i];
					isModify = true;
					break;
				}
			}
			//��ʾ�޸���Ʒ������״̬
			if (isModify) {
				System.out.println("���޸ĳɹ���");
			} else {
				System.out.println("���޸�ʧ�ܡ�");
			}
			if (cart_goods != null) {
				System.out.println("��Ʒ����\t\t\t����\t����\t���");
				System.out.println(
						"------------------------------------------------------------------------------------");
				System.out.println("(" + cart_goods.getGoods_no() + ")"+ cart_goods.getGoods_name() + "\t"
						+ cart_goods.getGoods_unit() +"\t"+ cart_goods.getGoods_price() + "\t" + cart_goods.getGoods_num()
						+ "\t" + (cart_goods.getGoods_price() * cart_goods.getGoods_num()));
				System.out.println(
						"------------------------------------------------------------------------------------");
			}
		} else {
			System.out.println("�����ﳵ������Ʒ����ѡ��1.ɨ����Ʒ!��");
		}
		//�л����������˵�
		cashManageGUI();
	}

	/**
	 * ɨ����Ʒ
	 */
	private void scannerGoods() {
		// ����Ҫɨ�����Ʒ���
		System.out.print("������Ҫɨ�����Ʒ��ţ���");
		Scanner scanner = new Scanner(System.in);
		String goods_no = scanner.next();
		// �жϸ���Ʒ����Ƿ����
		boolean flag = false;
		if (MyData.goodsArray!=null) {
			for (int i = 0; i < MyData.goodsArray.length; i++) {
				Goods temp_goods = MyData.goodsArray[i];
				if (temp_goods != null && temp_goods.getGoods_no().equals(goods_no)) {
					flag = true;
					break;
				}
			}
		}
		
		if (flag) {
			// �жϹ��ﳵ���Ƿ��д���Ʒ
			boolean result = false;
			for (int i = 0; i < MyData.cartArray.length; i++) {
				Goods cart_goods = MyData.cartArray[i];
				if (cart_goods != null && cart_goods.getGoods_no().equals(goods_no)) {
					result = true;
					break;
				}
			}
			// �����жϽ�����й��ﳵ����Ϣ�ĵ���
			Goods cart_goods = null;
			if (result) {
				boolean Lethe = false;
				for (int i = 0; i < MyData.cartArray.length; i++) {
					Goods temp_cart_goods = MyData.cartArray[i];
					if (temp_cart_goods != null && temp_cart_goods.getGoods_no().equals(goods_no)) {
						temp_cart_goods.setGoods_num(temp_cart_goods.getGoods_num() + 1);
						MyData.cartArray[i] = temp_cart_goods;
						cart_goods = MyData.cartArray[i];
						Lethe = true;
						break;
					}
				}
				if (Lethe) {
					System.out.println("����ӳɹ���");
				} else {
					System.out.println("�����ʧ�ܡ�");
				}
			} else {
				// new_cart_goods�������ﳵ��Ʒ����
				Goods new_cart_goods = null;
				// ���Ҹ���Ʒ��ŵ�������Ʒ��Ϣ������������������ɹ�������
				for (int i = 0; i < MyData.goodsArray.length; i++) {
					Goods temp_goods = MyData.goodsArray[i];
					if (temp_goods != null && temp_goods.getGoods_no().equals(goods_no)) {
						temp_goods.setGoods_num(1);
						new_cart_goods = temp_goods;
						break;
					}

				}
				// ���������ﳵ��Ʒ������ӵ����ﳵ��������ȥ
				boolean remark = false;
				if (new_cart_goods != null) {
					for (int i = 0; i < MyData.cartArray.length; i++) {
						if (MyData.cartArray[i] == null) {
							MyData.cartArray[i] = new_cart_goods;
							cart_goods = MyData.cartArray[i];
							remark = true;
							break;
						}
					}
				}
				if (remark) {
					System.out.println("����ӳɹ���");
				} else {
					System.out.println("�����ʧ�ܡ�");
				}
			}
			if (cart_goods != null) {
				System.out.println("��Ʒ����\t\t\t����\t����\t���");
				System.out.println(
						"------------------------------------------------------------------------------------");
				System.out.println("(" + cart_goods.getGoods_no() + ")" + cart_goods.getGoods_name() + "\t\t"+cart_goods.getGoods_unit()
						+"\t"+ cart_goods.getGoods_price() + "\t" + cart_goods.getGoods_num() + "\t"
						+ (cart_goods.getGoods_price() * cart_goods.getGoods_num()));
				System.out.println(
						"------------------------------------------------------------------------------------");
			}
		} else {
			System.out.println("���Բ���û�д���Ʒ��ɨ��ʧ�ܣ�������");
		}
		// �л����������˵�����
		cashManageGUI();
	}

	/**
	 * ��ʾ�ֿ�����������
	 */
	private void storeManageGUI() {

		System.out.println("����ѡ��Ҫ���еĲ���: ��");
		System.out.println("��1.��Ʒ���  2.��Ʒ����  3.������Ʒ  4.��ѯȫ����Ʒ  5.����ѯ�Ų�ѯ��Ʒ   6.�˳� ��   ");
		boolean flag = true;
		do {
			Scanner scanner = new Scanner(System.in);
			String numString = scanner.next();
			switch (numString) {
			case "1":
				goodsWarehousing();
				flag = false;
				break;
			case "2":
				goodsWarehouseout();
				flag = false;
				break;
			case "3":
				addGoods();
				flag = false;
				break;
			case "4":
				queryAllProducts();
				flag = false;
				break;
			case "5":
				queryProductByQueryNumber();
				flag = false;
				break;
			case "6":
				System.out.println("��ллʹ��!��");
				storeManageGUI();
				flag = false;
				break;
			default:
				System.out.println("��������1-6֮�����:��");
				break;
			}
		} while (flag);

	}

	/**
	 * ����ѯ�Ų�ѯ��Ʒ
	 */
	private void queryProductByQueryNumber() {
		//������Ʒ���
		System.out.print("����������Ʒ���:��");
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		String goods_no = scanner.next();
		//�ȶ���Ʒ���飬������ڣ�������Ʒ��Ϣ
		Goods goodsArray[] = MyData.goodsArray;
		boolean flag = true;
		for (Goods goods : goodsArray) {
			if (goods != null) {
				if (goods.getGoods_no().equals(goods_no)) {
					System.out.println("��Ʒ���  \t\t��Ʒ����  \t\t��Ʒ����  \t\t��λ  \t\t����");
					System.out.println(goods.getGoods_no() + "\t\t" + goods.getGoods_name() + "\t\t"+goods.getGoods_unit()
							+ goods.getGoods_price() + "\t\t" + goods.getGoods_unit() + "\t\t" + goods.getGoods_num());
					flag = false;
					break;
				}
			}
		}
		if (flag) {
			System.out.println("���Բ���!û�и���Ʒ������������:��");
		}
		//���زֿ����˵�
		storeManageGUI();

	}

	private void queryAllProducts() {

		Goods goodsArray[] = MyData.goodsArray;
		System.out.println("��Ʒ���  \t\t��Ʒ����  \t\t��Ʒ����  \t\t��λ  \t\t����");
		for (Goods goods : goodsArray) {
			if (goods != null) {
				System.out.println(goods.getGoods_no() + "\t\t" + goods.getGoods_name() + "\t\t"
						+ goods.getGoods_price() + "\t\t" + goods.getGoods_unit() + "\t\t" + goods.getGoods_num());
			}
		}
		storeManageGUI();
	}
         /**
          * ������Ʒ
          */
	private void addGoods() {
		Scanner scanner = null;
		// ������Ʒ���
		String goods_no = "";
		Goods goodsArray[] = MyData.goodsArray;
		boolean flag = true;
		do {
			scanner = new Scanner(System.in);
			System.out.print("��������Ʒ���:��");
			goods_no = scanner.next();
			boolean remark = true;
			for (int i = 0; i < MyData.goodsArray.length; i++) {
				Goods goods = MyData.goodsArray[i];
				if (goods != null && goods.getGoods_no().equals(goods_no)) {
					System.out.println("���ñ���Ѵ���!����������:��");
					remark = false;
					break;
				}
			}
			if (remark == true) {
				flag = false;
			} else {
				flag = true;
			}
		} while (flag);
		// ������Ʒ����
		String goods_name = "";
		scanner = new Scanner(System.in);
		System.out.print("��������Ʒ����:��");
		goods_name = scanner.next();
		// ������Ʒ�۸�
		double goods_price = 0;
		do {
			try {
				System.out.print("��������Ʒ�۸�:��");
				scanner = new Scanner(System.in);
				goods_price = scanner.nextDouble();
				flag = false;
				break;
			} catch (Exception e) {
				System.out.println("����������Ʒ�۸�:��");
				flag = true;
			}
		} while (flag);

		// ������Ʒ��λ
		String goods_unit = "";
		scanner = new Scanner(System.in);
		System.out.print("��������Ʒ��λ:��");
		goods_unit = scanner.next();

		// ������Ʒ����
		int goods_num = 0;
		do {
			try {
				scanner = new Scanner(System.in);
				System.out.print("��������Ʒ����:��");
				goods_num = scanner.nextInt();
				flag = false;
				break;
			} catch (Exception e) {
				System.out.println("����������Ʒ����:��");
				flag = true;
			}
		} while (flag);

		// ������Ϣ�����Ա����״̬���з���
		Goods new_goods = new Goods();

		new_goods.setGoods_no(goods_no);
		new_goods.setGoods_name(goods_name);
		new_goods.setGoods_price(goods_price);
		new_goods.setGoods_unit(goods_unit);
		new_goods.setGoods_num(goods_num);

		// ����Ϣ����ԭ����Ʒ����
		boolean result = false;

		for (int i = 0; i < MyData.goodsArray.length; i++) {
			Goods temp_goodsGoods = MyData.goodsArray[i];
			if (temp_goodsGoods == null) {
				MyData.goodsArray[i] = new_goods;
				result = true;
				break;
			}
		}
		if (result) {
			System.out.println("����Ʒ��ӳɹ�!��");
		} else {
			System.out.println("����Ʒ���ʧ��!��");
		}
		//��ѯȫ����Ʒ����
		queryAllProducts();
	}

	/**
	 * ��Ʒ����
	 */
	private void goodsWarehouseout() {
		Scanner scanner = null;

		String goods_no = "";
		// ������Ʒ��Ų���֤�Ƿ��д���Ʒ
		do {
			scanner = new Scanner(System.in);
			System.out.print("��������Ʒ���:��");
			goods_no = scanner.next();

			//��֤
			boolean result = false;
			for (int i = 0; i < MyData.goodsArray.length; i++) {
				Goods temp_goods = MyData.goodsArray[i];
				if (temp_goods != null && temp_goods.getGoods_no().equals(goods_no)) {
					result = true;
					break;
				}
			}
			if (result == false) {
				System.out.println("���޴���Ʒ!������������Ʒ���:��");
				continue;
			}
			break;
		} while (true);
		// ��������
		int out_num = 0;
		do {
			try {
				scanner = new Scanner(System.in);
				System.out.print("������Ҫ���������:��");
				out_num = scanner.nextInt();
				if (out_num <= 0) {
					System.out.println("�����������0������:��");
					continue;
				}
				break;
			} catch (Exception e) {
				System.out.println("����������ȷ������:��");
			}
		} while (true);
		boolean remark = false;
		Goods old_goods = null;
		for (int i = 0; i < MyData.goodsArray.length; i++) {
			Goods temp_goods = MyData.goodsArray[i];
			if (temp_goods != null && temp_goods.getGoods_no().equals(goods_no)) {
				if (temp_goods.getGoods_num() >= out_num) {
					temp_goods.setGoods_num(temp_goods.getGoods_num() - out_num);
					MyData.goodsArray[i] = temp_goods;
					remark = true;
					break;
				}
				old_goods = temp_goods;
			}
		}
		if (remark) {
			System.out.println("������ɹ�����");
			queryAllProducts();
		} else {
			System.out.println("����治�㣡���ʵ������ʧ�ܣ�����");
			System.out.println("��Ʒ���  \t\t��Ʒ����  \t\t��Ʒ����  \t\t��λ  \t\t����");
			System.out.println(
					old_goods.getGoods_no() + "\t\t" + old_goods.getGoods_name() + "\t\t" + old_goods.getGoods_price()
							+ "\t\t" + old_goods.getGoods_unit() + "\t\t" + old_goods.getGoods_num());
			storeManageGUI();
		}
	}

	/**
	 * ��Ʒ���
	 */
	private void goodsWarehousing() {

		//������Ʒ��ţ���֤�Ƿ��д���Ʒ
		String goods_no = "";
		Scanner scanner = new Scanner(System.in);
		System.out.print("��������Ʒ���:��");
		goods_no = scanner.next();
		boolean result = false;
		for (int i = 0; i < MyData.goodsArray.length; i++) {
			Goods temp_goods = MyData.goodsArray[i];
			if (temp_goods != null && temp_goods.getGoods_no().equals(goods_no)) {
				result = true;
				break;
			}
		}
		if (result) {
			//�������������
			int goods_num = 0;
			do {
				try {
					scanner = new Scanner(System.in);
					System.out.print("������Ҫ��ӵ�����:��");
					goods_num = scanner.nextInt();
					if (goods_num <= 0) {
						System.out.println("�����������0������:��");
						continue;
					}
					break;
				} catch (Exception e) {
					System.out.println("����������ȷ������:��");
				}
			} while (result);
			
			//�Ը���Ʒ���������е���
			boolean remark = true;
			for (int i = 0; i < MyData.goodsArray.length; i++) {
				Goods temp_goods = MyData.goodsArray[i];
				if (temp_goods != null && temp_goods.getGoods_no().equals(goods_no)) {
					temp_goods.setGoods_num(temp_goods.getGoods_num() + goods_num);
					MyData.goodsArray[i] = temp_goods;
					remark = false;
					break;
				}
			}
			if (remark) {
				System.out.println("�����ʧ�ܣ���");
			} else {
				System.out.println("�����ɹ�����");
			}
		} else {
			//û�д���Ʒ
			System.out.println("��û�д���Ʒ����ѡ���� 3��������Ʒ����");
		}
		//��ʾ���������
		queryAllProducts();
	}

}
