package com.guohuaijiang.stract;

import com.guohuaijiang.oerator.Operator;
import com.guohuaijiang.oerator.OperatorImpl;
import com.guohuaijiang.util.MyData;

/**
 *
 * @������ ������
 * @��� ������Ľӿڵ�ʵ����
 * @��ʼ���� 2020-07-19
 * @�������� 2020-07-29
 * @�汾 v1.0
 * @˵�� ���ڷ�װ�û��Ĳ�����Ϣ-���ܵ�ʵ��
 */
public class StractApp {

	public static void main(String[] args) {
		// 1���ݳ�ʼ��
		MyData mydata = new MyData();
		// 2������Ŀ
		Operator operator = new OperatorImpl();
		operator.stract();
	}

}
