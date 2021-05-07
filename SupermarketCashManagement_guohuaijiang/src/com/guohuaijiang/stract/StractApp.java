package com.guohuaijiang.stract;

import com.guohuaijiang.oerator.Operator;
import com.guohuaijiang.oerator.OperatorImpl;
import com.guohuaijiang.util.MyData;

/**
 *
 * @开发者 郭怀江
 * @类别 控制类的接口的实现类
 * @开始日期 2020-07-19
 * @结束日期 2020-07-29
 * @版本 v1.0
 * @说明 用于封装用户的操作信息-功能的实现
 */
public class StractApp {

	public static void main(String[] args) {
		// 1数据初始化
		MyData mydata = new MyData();
		// 2启动项目
		Operator operator = new OperatorImpl();
		operator.stract();
	}

}
