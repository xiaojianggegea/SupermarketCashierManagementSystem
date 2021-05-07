package com.guohuaijiang.oerator;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import com.guohuaijiang.bean.Goods;
import com.guohuaijiang.bean.Member;
/**
*
* @开发者 郭怀江
* @类别 控制类的接口的实现类
* @开始日期 2020-07-19
* @结束日期 2020-07-29
* @版本 v1.0
* @说明 用于封装用户的操作信息-功能的实现
*/
import com.guohuaijiang.bean.User;
import com.guohuaijiang.service.UserService;
import com.guohuaijiang.service.UserServiceImpl;
import com.guohuaijiang.util.MyData;

public class OperatorImpl implements Operator {

	private static User login_user = null;

	/**
	 * 系统的启动方法
	 */
	@Override
	public void stract() {
		displaySystemGUI();
	}

	/**
	 * 显示系统的主界面
	 */
	private void displaySystemGUI() {

		System.out.println("**********欢迎使用 超市收银系统 **********");
		System.out.println("  1 登陆      ");
		System.out.println(" 2 退出登陆  ");
		System.out.print("【请选择 1或2】: ");
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
				System.out.println("【谢谢使用!】");
				System.exit(0);
				flag = false;
				break;
			default:
				System.out.println("【请输入1或2】:");
				break;
			}
		} while (flag);
	}

	/**
	 * 显示登录功能的界面
	 */
	private void showLoginGUI() {

		boolean flag = true;
		do {
			//用户数据接收
			Scanner scanner = new Scanner(System.in);
			System.out.print("【用户名称】:");
			String username = scanner.next();
			System.out.print("【用户密码】:");
			String userpwd = scanner.next();

			//将数据传递给业务层进行处理
			UserService userService = new UserServiceImpl();
			login_user = userService.loginByUsernameAndUsernamepwd(username, userpwd);
			if (login_user != null) {
				
				/**
				 * 主界面的选择
				 */
				if (login_user.getUser_type() != null && login_user.getUser_type().equals("1")) {
					System.out.println("【登陆成功!】");
					System.out.println("********欢迎使用收银管理系统********");
					cashManageGUI();
					flag = false;
				} else if (login_user.getUser_type() != null && login_user.getUser_type().equals("2")) {
					System.out.println("【登陆成功!】");
					System.out.println("********欢迎使用库存管理系统********");
					storeManageGUI();
					flag = false;
				} else {
					System.out.println("【角色未分配!】 ");
				}
			} else {
				System.out.println("【用户名或密码错误，登陆失败!请重新输入:】 ");
				flag = true;
			}
		} while (flag);
	}

	/**
	 * 显示收银管理的主界面
	 */
	private void cashManageGUI() {
		System.out.println("【请选择要进行的操作:】 ");
		System.out.println("【1.扫描商品  2.修改数量   3.结账     4.退出   5 查看购物车】");
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
				System.out.println("【谢谢使用!】");
				flag = false;
				break;
			case "5":
				showCartArray();
				System.out.println("【谢谢使用!】");
				flag = false;
				break;
			default:
				System.out.println("【请输入1-5之间的数字:】");
				break;
			}
		} while (flag);
	}

	/**
	 * 显示购物车中扫描的商品
	 */
	private void showCartArray() {
		System.out.println("********** 你的购物车 **********");
		Goods cartArray[] = MyData.cartArray;
		System.out.println("商品名称\t\t\t单价\t数量\t金额");
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
	 * 结账
	 */
	private void settleAccounts() {
		// 判断购物车是否为空
		boolean isEmpty = false;
		for (int i = 0; i < MyData.cartArray.length; i++) {
			if (MyData.cartArray[i] != null) {
				isEmpty = true;
				break;
			}

		}
	//  对购物车状态做出响应
		if (isEmpty) {
			//购物车不为空，计算购物车中的总金额
			double sum = 0;
			for (int i = 0; i < MyData.cartArray.length; i++) {
				Goods temp_cart_goods = MyData.cartArray[i];
				if (temp_cart_goods != null) {
					double amount = temp_cart_goods.getGoods_num() * temp_cart_goods.getGoods_price();
					sum += amount;
				}
			}
			System.out.println("总金额是:" + sum);
			System.out.println("【1 普通结账  2 会员结账】");
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
					System.out.println("【请选择1或2】");
					break;
				}
			} while (true);

		} else {
			//购物车为空,提示进行其他操作
			System.out.println("【购物车为空，请选择1扫描商品！！】");
			cashManageGUI();
		}
	}

	/**
	 * 会员结账
	 */
	private void memberSettleAccounts(double sum) {

		String member_no = "";
		Member cuurent_memberMember = null;
		boolean flag = true;
		do {
			Scanner scanner = new Scanner(System.in);
			System.out.print("【请输入你的会员卡号:】");
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
				System.out.println("【会员卡号错误！！】");
			}

		} while (flag);

		// 接受支付金额
		System.out.print("【请输入你要支付的金额:】");
		double money = 0;
		do {
			try {
				@SuppressWarnings("resource")
				Scanner scanner = new Scanner(System.in);
				money = scanner.nextDouble();
				if (money < sum) {
					System.out.println("【支付金额不够，请重新输入!!!】");
					continue;
				}
				break;
			} catch (Exception e) {
				System.out.print("【请输入正确的金额!!!】");
			}
		} while (true);

		// 打印小票
		System.out.println("********************    超市            ********************");
		Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		String xiaopiaohaoString = simpleDateFormat.format(date);
		System.out.println("收营员号:" + this.login_user.getUser_no() + "                  小票号:" + xiaopiaohaoString);
		System.out.println("序号\t 商品名称\t\t\t单价\t数量\t金额");
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
		System.out.println("总金额:" + totalNum + "\t\t\t应收:" + sum);
		System.out.println("实收:" + money + "\t\t\t找零:" + (money - sum));
		
		System.out.println("本次累计积分成功               卡号:" + member_no);
		System.out.println("消费前积分+本次积分=消费后积分");
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
		System.out.println("【请妥善保管小票，以备不需】");
		System.out.println("===============================================================");
		MyData.cartArray = new Goods[50];
		cashManageGUI();
	}

	/**
	 * 普通结账
	 */
	private void generalSettleAccounts(double sum) {
		//接收支付金额
		System.out.print("【请输入你要支付的金额:】");
		double money = 0;
		do {
			try {
				@SuppressWarnings("resource")
				Scanner scanner = new Scanner(System.in);
				money = scanner.nextDouble();
				if (money < sum) {
					System.out.println("【支付金额不够，请重新输入!!!】");
					continue;
				}
				break;
			} catch (Exception e) {
				System.out.print("【请输入正确的金额!!!】");
			}
		} while (true);
		//打印小票
		System.out.println("******************** 超市  ******************** ");
		Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		String xiaopiaohaoString = simpleDateFormat.format(date);
		System.out.println("收营员号:" + this.login_user.getUser_no() + "                  小票号:" + xiaopiaohaoString);
		System.out.println("序号\t 商品名称\t\t\t单价\t数量\t金额");
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
		System.out.println("总金额:" + totalNum + "\t\t\t应收:" + sum);
		System.out.println("实收:" + money + "\t\t\t找零:" + (money - sum));

		simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd    HH:mm:ss");
		String dataString = simpleDateFormat.format(date);
		System.out.println(dataString);
		System.out.println("【请妥善保管小票，以备不需】");
		System.out.println("===============================================================");
		MyData.cartArray = new Goods[50];
		cashManageGUI();
	}

	/**
	 * 修改数量
	 */
	private void reviseTheQuantity() {
		// 购物车中是否有对象
		boolean flag = false;
		for (int i = 0; i < MyData.cartArray.length; i++) {
			if (MyData.cartArray[i] != null) {
				flag = true;
				break;
			}
		}

		if (flag == true) {
			// 接收商品编号
			String goods_no = null;
			boolean isExit = true;
			do {
				Scanner scanner = new Scanner(System.in);
				System.out.print("【输入要修改的商品编号:】");
				goods_no = scanner.next();
				// 判断购物车中是否有该商品
				boolean remark = false;
				for (int i = 0; i < MyData.cartArray.length; i++) {
					if (MyData.cartArray[i] != null && MyData.cartArray[i].getGoods_no().equals(goods_no)) {
						remark = true;
						break;
					}
				}
				if (remark == false) {
					System.out.println("【没有扫描此商品!】");
					isExit = true;
				} else {
					isExit = false;
				}
			} while (isExit);
			//接受修改数量
			boolean isInteger = true;
			int goods_num = 0;
			do {
				try {
					System.out.print("【输入修改数量】");
					Scanner scanner = new Scanner(System.in);
					goods_num = scanner.nextInt();
					if (goods_num <= 0) {
						System.out.println("【请输入大于等于0的数字！！】");
						isInteger = true;
						continue;
					}
					break;
				} catch (Exception e) {
					System.out.print("【请输入正确的数量:】");
					isInteger = true;
				}
			} while (isInteger);
			//调整购物车数组中有该商品的数量
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
			//显示修改商品数量的状态
			if (isModify) {
				System.out.println("【修改成功】");
			} else {
				System.out.println("【修改失败】");
			}
			if (cart_goods != null) {
				System.out.println("商品名称\t\t\t单价\t数量\t金额");
				System.out.println(
						"------------------------------------------------------------------------------------");
				System.out.println("(" + cart_goods.getGoods_no() + ")"+ cart_goods.getGoods_name() + "\t"
						+ cart_goods.getGoods_unit() +"\t"+ cart_goods.getGoods_price() + "\t" + cart_goods.getGoods_num()
						+ "\t" + (cart_goods.getGoods_price() * cart_goods.getGoods_num()));
				System.out.println(
						"------------------------------------------------------------------------------------");
			}
		} else {
			System.out.println("【购物车中无商品，请选择1.扫描商品!】");
		}
		//切换到收银主菜单
		cashManageGUI();
	}

	/**
	 * 扫描商品
	 */
	private void scannerGoods() {
		// 接受要扫描的商品编号
		System.out.print("【输入要扫描的商品编号：】");
		Scanner scanner = new Scanner(System.in);
		String goods_no = scanner.next();
		// 判断该商品编号是否存在
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
			// 判断购物车中是否有此商品
			boolean result = false;
			for (int i = 0; i < MyData.cartArray.length; i++) {
				Goods cart_goods = MyData.cartArray[i];
				if (cart_goods != null && cart_goods.getGoods_no().equals(goods_no)) {
					result = true;
					break;
				}
			}
			// 根据判断结果进行购物车中信息的调整
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
					System.out.println("【添加成功】");
				} else {
					System.out.println("【添加失败】");
				}
			} else {
				// new_cart_goods新增购物车商品对象
				Goods new_cart_goods = null;
				// 查找该商品编号的所有商品信息，并将库存数量调整成购买数量
				for (int i = 0; i < MyData.goodsArray.length; i++) {
					Goods temp_goods = MyData.goodsArray[i];
					if (temp_goods != null && temp_goods.getGoods_no().equals(goods_no)) {
						temp_goods.setGoods_num(1);
						new_cart_goods = temp_goods;
						break;
					}

				}
				// 将新增购物车商品对象添加到购物车的数组中去
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
					System.out.println("【添加成功】");
				} else {
					System.out.println("【添加失败】");
				}
			}
			if (cart_goods != null) {
				System.out.println("商品名称\t\t\t单价\t数量\t金额");
				System.out.println(
						"------------------------------------------------------------------------------------");
				System.out.println("(" + cart_goods.getGoods_no() + ")" + cart_goods.getGoods_name() + "\t\t"+cart_goods.getGoods_unit()
						+"\t"+ cart_goods.getGoods_price() + "\t" + cart_goods.getGoods_num() + "\t"
						+ (cart_goods.getGoods_price() * cart_goods.getGoods_num()));
				System.out.println(
						"------------------------------------------------------------------------------------");
			}
		} else {
			System.out.println("【对不起，没有此商品，扫描失败！！！】");
		}
		// 切换回收银主菜单界面
		cashManageGUI();
	}

	/**
	 * 显示仓库管理的主界面
	 */
	private void storeManageGUI() {

		System.out.println("【请选择要进行的操作: 】");
		System.out.println("【1.商品入库  2.商品出库  3.新增商品  4.查询全部商品  5.按查询号查询商品   6.退出 】   ");
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
				System.out.println("【谢谢使用!】");
				storeManageGUI();
				flag = false;
				break;
			default:
				System.out.println("【请输入1-6之间的数:】");
				break;
			}
		} while (flag);

	}

	/**
	 * 按查询号查询商品
	 */
	private void queryProductByQueryNumber() {
		//接收商品编号
		System.out.print("【请输入商品编号:】");
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		String goods_no = scanner.next();
		//比对商品数组，如果存在，输入商品信息
		Goods goodsArray[] = MyData.goodsArray;
		boolean flag = true;
		for (Goods goods : goodsArray) {
			if (goods != null) {
				if (goods.getGoods_no().equals(goods_no)) {
					System.out.println("商品编号  \t\t商品名称  \t\t商品单价  \t\t单位  \t\t数量");
					System.out.println(goods.getGoods_no() + "\t\t" + goods.getGoods_name() + "\t\t"+goods.getGoods_unit()
							+ goods.getGoods_price() + "\t\t" + goods.getGoods_unit() + "\t\t" + goods.getGoods_num());
					flag = false;
					break;
				}
			}
		}
		if (flag) {
			System.out.println("【对不起!没有该商品，请重新输入:】");
		}
		//跳回仓库管理菜单
		storeManageGUI();

	}

	private void queryAllProducts() {

		Goods goodsArray[] = MyData.goodsArray;
		System.out.println("商品编号  \t\t商品名称  \t\t商品单价  \t\t单位  \t\t数量");
		for (Goods goods : goodsArray) {
			if (goods != null) {
				System.out.println(goods.getGoods_no() + "\t\t" + goods.getGoods_name() + "\t\t"
						+ goods.getGoods_price() + "\t\t" + goods.getGoods_unit() + "\t\t" + goods.getGoods_num());
			}
		}
		storeManageGUI();
	}
         /**
          * 新增商品
          */
	private void addGoods() {
		Scanner scanner = null;
		// 接收商品编号
		String goods_no = "";
		Goods goodsArray[] = MyData.goodsArray;
		boolean flag = true;
		do {
			scanner = new Scanner(System.in);
			System.out.print("【输入商品编号:】");
			goods_no = scanner.next();
			boolean remark = true;
			for (int i = 0; i < MyData.goodsArray.length; i++) {
				Goods goods = MyData.goodsArray[i];
				if (goods != null && goods.getGoods_no().equals(goods_no)) {
					System.out.println("【该编号已存在!请重新输入:】");
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
		// 接收商品名称
		String goods_name = "";
		scanner = new Scanner(System.in);
		System.out.print("【输入商品名称:】");
		goods_name = scanner.next();
		// 接收商品价格
		double goods_price = 0;
		do {
			try {
				System.out.print("【输入商品价格:】");
				scanner = new Scanner(System.in);
				goods_price = scanner.nextDouble();
				flag = false;
				break;
			} catch (Exception e) {
				System.out.println("【请输入商品价格:】");
				flag = true;
			}
		} while (flag);

		// 接收商品单位
		String goods_unit = "";
		scanner = new Scanner(System.in);
		System.out.print("【输入商品单位:】");
		goods_unit = scanner.next();

		// 接收商品数量
		int goods_num = 0;
		do {
			try {
				scanner = new Scanner(System.in);
				System.out.print("【输入商品数量:】");
				goods_num = scanner.nextInt();
				flag = false;
				break;
			} catch (Exception e) {
				System.out.println("【请输入商品数量:】");
				flag = true;
			}
		} while (flag);

		// 保存信息，并对保存的状态进行反馈
		Goods new_goods = new Goods();

		new_goods.setGoods_no(goods_no);
		new_goods.setGoods_name(goods_name);
		new_goods.setGoods_price(goods_price);
		new_goods.setGoods_unit(goods_unit);
		new_goods.setGoods_num(goods_num);

		// 将信息存入原有商品数组
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
			System.out.println("【商品添加成功!】");
		} else {
			System.out.println("【商品添加失败!】");
		}
		//查询全部商品界面
		queryAllProducts();
	}

	/**
	 * 商品出库
	 */
	private void goodsWarehouseout() {
		Scanner scanner = null;

		String goods_no = "";
		// 接收商品编号并验证是否有此商品
		do {
			scanner = new Scanner(System.in);
			System.out.print("【输入商品编号:】");
			goods_no = scanner.next();

			//验证
			boolean result = false;
			for (int i = 0; i < MyData.goodsArray.length; i++) {
				Goods temp_goods = MyData.goodsArray[i];
				if (temp_goods != null && temp_goods.getGoods_no().equals(goods_no)) {
					result = true;
					break;
				}
			}
			if (result == false) {
				System.out.println("【无此商品!请重新输入商品编号:】");
				continue;
			}
			break;
		} while (true);
		// 出库数量
		int out_num = 0;
		do {
			try {
				scanner = new Scanner(System.in);
				System.out.print("【输入要出库的数量:】");
				out_num = scanner.nextInt();
				if (out_num <= 0) {
					System.out.println("【请输入大于0的整数:】");
					continue;
				}
				break;
			} catch (Exception e) {
				System.out.println("【请输入正确的数量:】");
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
			System.out.println("【出库成功！】");
			queryAllProducts();
		} else {
			System.out.println("【库存不足！请核实！出库失败！！】");
			System.out.println("商品编号  \t\t商品名称  \t\t商品单价  \t\t单位  \t\t数量");
			System.out.println(
					old_goods.getGoods_no() + "\t\t" + old_goods.getGoods_name() + "\t\t" + old_goods.getGoods_price()
							+ "\t\t" + old_goods.getGoods_unit() + "\t\t" + old_goods.getGoods_num());
			storeManageGUI();
		}
	}

	/**
	 * 商品入库
	 */
	private void goodsWarehousing() {

		//接收商品编号，验证是否有此商品
		String goods_no = "";
		Scanner scanner = new Scanner(System.in);
		System.out.print("【输入商品编号:】");
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
			//若有则接收数量
			int goods_num = 0;
			do {
				try {
					scanner = new Scanner(System.in);
					System.out.print("【输入要添加的数量:】");
					goods_num = scanner.nextInt();
					if (goods_num <= 0) {
						System.out.println("【请输入大于0的整数:】");
						continue;
					}
					break;
				} catch (Exception e) {
					System.out.println("【请输入正确的数量:】");
				}
			} while (result);
			
			//对该商品的数量进行调整
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
				System.out.println("【入库失败！】");
			} else {
				System.out.println("【入库成功！】");
			}
		} else {
			//没有此商品
			System.out.println("【没有此商品，请选择编号 3：新增商品！】");
		}
		//显示库存管理界面
		queryAllProducts();
	}

}
