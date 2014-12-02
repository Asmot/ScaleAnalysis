package com.example.scale.utils;

import static java.lang.Math.*;

/**A��ˮ�и�����Ũ��, mol / L��
*B����ˮ����̼�������Ũ��, mol / L��
*p ���� �ܾ���ѹ����MPa��
*t ���� �¶ȣ��棻
*Si ���� ����ǿ�ȣ�mol / L��
*fg������CH4��CO2�������(����CO2������) CO2���ݶ�ϵ�����ݶ��Ǽ������������ѹ���������ݶ����������������㹫ʽ�е�ѹ������ù�ʽ���������ڷ��������塣�ݶ�ϵ��
*fai= f / p  ��ʾ�ݶ���ʵ��ѹ��f ��p�ı�ֵ����˵�����������������������ƫ��̶ȣ���
*Yg������һ���¶ȡ�ѹ��������CO2�������еĺ�����mol%��%��
*Yt��������������CO2�������͡���ˮ�����ϵ��(��ҪΪ������)�ĺ�����mol%��%��
*Qg ���� �ڱ�׼�¶ȡ�ѹ��������ÿ�ղɳ�����������, 10^6 m3��
*Ca ���� ÿ������ˮ�����вɳ���CO2������mol / L��
*Qw ���� ÿ�ղɳ���ˮ��, m3��
*Qo ���� ÿ�ղɳ�������, m3��
*Nt�����ڱ�׼�¶ȡ�ѹ��������ÿ�ղɳ���CO2����, 10^6 m3��
**/

public class CalTools {

	//���������ʱ��̼���ι�-����ָ������
	public static double IsCal_Cwithgas(double A, double B, double p, double t, double Si, double Yt, double Qg, double Qw, double Qo)
	{
		double fg = exp(145 * p*(2.84 / 10000 - 0.255 / (1.8*t + 492)));
		double Yg = Yt*(1 + (145 * fg*6.29*(5 * Qw + 10 * Qo) / 100000) / (35.32*Qg*(1.8*t + 492)));
		return (log(A* pow(B,2) / 145 * Yg * fg) + 5.85 + 15.19 * pow(10,-3) * (1.8 * t + 32) - 1.64 * pow(10,-6) * pow((1.8 * t + 32) , 2 )- 764.15 * pow(10,-5) * p - 3.334 * sqrt(Si) + 1.431 * Si);
	}

	//���������ʱ��̼���ι�-����ָ��
	public static double IsCal_Cnogas(double A, double B, double p, double t, double Si, double Yt, double Qg, double Qw, double Qo)
	{
		double Nt = Yt*35.32*Qg;
		double Ca = 7289.3*Nt / 6.29*(Qw + 3.04*Qo);
		return (log(A * pow(B, 2) / Ca) + 3.63 + 8.68 / 1000 * (1.8 * t + 32) + 8.85 / 1000000 * pow((1.8 * t + 32), 2) - 951.2 / 100000 * p - 3.42 * sqrt(Si) + 1.373 * Si);
	}
	/*
	*TDS-----------���ܿ󻯶�
	*p-------------ѹ��
	*t-------------�¶ȣ����϶�
	*B-------------���������Ũ��
	*C-------------������Ũ��
	*H-------------������Ũ��
	*D-------------������Ũ��
	*/

	//��������һ��һά���飬�ֱ��Ӧ��Ӧ�����ֵ;�����ι��ı���ָ�����ȶ���������
	public static double[] IsCal_S(double TDS, double p, double t, double B, double C, double H, double D)
	{
		double[] Is = { 0 };
		double Si = TDS / 53470;
		Is[0] = 1.86 + 4.5 / 1000 * (1.8 * t + 32) - 1.2 / 1000000 * pow((1.8 * t + 32), 2) + 1551.5 / 100000 * p - 2.38 * sqrt(Si) + 0.58 * Si - 1.3 / 1000 * sqrt(Si) * (1.8 * t + 32);
		Is[1] = log(B * H) + 6.11 + 2 / 1000 * (1.8 * t + 32) + 6.4 / 1000000 * pow((1.8 * t + 32), 2) - 667 / 100000 * p - 1.89 * sqrt(Si) + 0.67 * Si - 1.9 / 1000 * sqrt(Si) * (1.8 * t + 32);
		Is[2] = log(B * C) + 10.03 - 4.8 / 1000 * (1.8 * t + 32) + 11.4 / 1000000 * (1.8 * t + 32) - 696 / 100000 * p - 2.62 * sqrt(Si) + 0.89 * Si - 2 / 1000 * sqrt(Si) * (1.8 * t + 32);
		if (t < 80) Is[3] = log(B * D) + 3.47 + 1.8 / 1000 * (1.8 * t + 32) + 2.5 / 1000000 * pow((1.8 * t + 32), 2) - 855.5 / 100000 * p - 1.13 * sqrt(Si) + 0.37 * Si - 2 / 1000 * sqrt(Si) * (1.8 * t + 32);
		else if (t > 121) Is[3] = log(B * D) + 2.52 + 9.98 / 1000 * (1.8 * t + 32) - 0.97 / 1000000 * pow((1.8 * t + 32), 2) - 445.15 / 100000 * p - 1.09 *sqrt(Si) + 0.37 * Si - 3.3 / 1000 * sqrt(Si) * (1.8 * t + 32);
		else Is[3] = log(B * D) + 3.47 + 1.8 / 1000 * (1.8 * t + 32) + 2.5 / 1000000 * pow((1.8 * t + 32), 2) - 855.5 / 100000 * p - 1.13 * sqrt(Si) + 0.37 * Si - 2 / 1000 * sqrt(Si) * (1.8 * t + 32);
		return Is;
	}


	//����Is�ж��Ƿ�Ṹ�����������Ҫ�޸������GUI������
	public static void judge(double Is)
	{
		if (Is < 0) System.out.println("δ�ﵽ���ͣ������ܽṸ");
		else if(Is==0) System.out.println("����ƽ��״̬");
		else System.out.println("�нṸ����");
	}
	
}