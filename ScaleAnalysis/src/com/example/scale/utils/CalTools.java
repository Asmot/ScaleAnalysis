package com.example.scale.utils;

import static java.lang.Math.*;

/**
*A—水中钙离子浓度, mol / L；<br>
*B——水中重碳酸根离子浓度, mol / L；<br>
*p —— 总绝对压力，MPa；<br>
*t —— 温度，℃；<br>
*Si —— 离子强度，mol / L；<br>
*fg——在CH4和CO2混合气中(其中CO2含量少) CO2的逸度系数（逸度是假想的理想气体压力。利用逸度来替代理想气体计算公式中的压力，则该公式即可适用于非理想气体。逸度系数<br>
*fai= f / p  表示逸度与实际压力f 与p的比值，可说明非理想气体与理想气体的偏离程度）；<br>
*Yg——在一定温度、压力条件下CO2在气相中的含量，mol%或%；<br>
*Yt——地面条件下CO2在气、油、盐水混合体系中(主要为气相中)的含量，mol%或%；<br>
*Qg —— 在标准温度、压力条件下每日采出的气体总量, 10^6 m3；<br>
*Ca —— 每日在盐水和油中采出的CO2含量，mol / L；<br>
*Qw —— 每日采出的水量, m3；<br>
*Qo —— 每日采出的油量, m3；<br>
*Nt——在标准温度、压力条件下每日采出的CO2气量, 10^6 m3。<br>
**/
public class CalTools {

	/**有气相存在时的碳酸盐垢-饱和指数函数<br>
	*A—水中钙离子浓度, mol / L；<br>
	*B——水中重碳酸根离子浓度, mol / L；<br>
	*p —— 总绝对压力，MPa；<br>
	*t —— 温度，℃；<br>
	*Si —— 离子强度，mol / L；<br>
	*Yt——地面条件下CO2在气、油、盐水混合体系中(主要为气相中)的含量，mol%或%；<br>
	*Qg —— 在标准温度、压力条件下每日采出的气体总量, 10^6 m3；<br>
	*Qw —— 每日采出的水量, m3；<br>
	*Qo —— 每日采出的油量, m3；<br>
	 * */
	public static double IsCal_Cwithgas(double A, double B, double p, double t, double Si, double Yt, double Qg, double Qw, double Qo)
	{
		double fg = exp(145 * p*(2.84 / 10000 - 0.255 / (1.8*t + 492)));
		double Yg = Yt*(1 + (145 * p * fg*6.29*(5 * Qw + 10 * Qo) / 100000) / (35.32*Qg*(1.8*t + 492)));
		return (log(A* pow(B,2) / 145 * Yg * fg) + 5.85 + 15.19 * pow(10,-3) * (1.8 * t + 32) - 1.64 * pow(10,-6) * pow((1.8 * t + 32) , 2 )- 764.15 * pow(10,-5) * p - 3.334 * sqrt(Si) + 1.431 * Si);
	}

	/**
	 * 无气相存在时的碳酸盐垢-饱和指数<br>
	*A—水中钙离子浓度, mol / L；<br>
	*B——水中重碳酸根离子浓度, mol / L；<br>
	*p —— 总绝对压力，MPa；<br>
	*t —— 温度，℃；<br>
	*Si —— 离子强度，mol / L；<br>
	*Yt——地面条件下CO2在气、油、盐水混合体系中(主要为气相中)的含量，mol%或%；<br>
	*Qg —— 在标准温度、压力条件下每日采出的气体总量, 10^6 m3；<br>
	*Qw —— 每日采出的水量, m3；<br>
	*Qo —— 每日采出的油量, m3；<br>
	 */
	public static double IsCal_Cnogas(double A, double B, double p, double t, double Si, double Yt, double Qg, double Qw, double Qo)
	{
		double Nt = Yt*35.32*Qg;
		double Ca = 7289.3*Nt / 6.29*(Qw + 3.04*Qo);
		return (log(A * pow(B, 2) / Ca) + 3.63 + 8.68 / 1000 * (1.8 * t + 32) + 8.85 / 1000000 * pow((1.8 * t + 32), 2) - 951.2 / 100000 * p - 3.42 * sqrt(Si) + 1.373 * Si);
	}
	/**
	 * 函数返回一个一维数组，分别对应对应组件的值;硫酸盐垢的饱和指数和稳定常数函数<br>
	*TDS-----------总总矿化度<br>
	*p-------------压力<br>
	*t-------------温度，摄氏度<br>
	*B-------------硫酸根离子浓度<br>
	*C-------------钡离子浓度<br>
	*H-------------锶离子浓度<br>
	*D-------------钙离子浓度<br>
	*/
	public static double[] IsCal_S(double TDS, double p, double t, double B, double C, double H, double D)
	{
		double[] Is = { 0.0, 0.0, 0.0, 0.0};
		double Si = TDS / 53470;
		Is[0] = pow(10, 1.86 + 4.5 / 1000 * (1.8 * t + 32) - 1.2 / 1000000 * pow((1.8 * t + 32), 2) + 1551.5 / 100000 * p - 2.38 * sqrt(Si) + 0.58 * Si - 1.3 / 1000 * sqrt(Si) * (1.8 * t + 32));
		Is[1] = log(B * H) + 6.11 + 2 / 1000 * (1.8 * t + 32) + 6.4 / 1000000 * pow((1.8 * t + 32), 2) - 667 / 100000 * p - 1.89 * sqrt(Si) + 0.67 * Si - 1.9 / 1000 * sqrt(Si) * (1.8 * t + 32);
		Is[2] = log(B * C) + 10.03 - 4.8 / 1000 * (1.8 * t + 32) + 11.4 / 1000000 * (1.8 * t + 32) - 696 / 100000 * p - 2.62 * sqrt(Si) + 0.89 * Si - 2 / 1000 * sqrt(Si) * (1.8 * t + 32);
		if (t < 80) Is[3] = log(B * D) + 3.47 + 1.8 / 1000 * (1.8 * t + 32) + 2.5 / 1000000 * pow((1.8 * t + 32), 2) - 855.5 / 100000 * p - 1.13 * sqrt(Si) + 0.37 * Si - 2 / 1000 * sqrt(Si) * (1.8 * t + 32);
		else if (t > 121) Is[3] = log(B * D) + 2.52 + 9.98 / 1000 * (1.8 * t + 32) - 0.97 / 1000000 * pow((1.8 * t + 32), 2) - 445.15 / 100000 * p - 1.09 *sqrt(Si) + 0.37 * Si - 3.3 / 1000 * sqrt(Si) * (1.8 * t + 32);
		else Is[3] = log(B * D) + 3.47 + 1.8 / 1000 * (1.8 * t + 32) + 2.5 / 1000000 * pow((1.8 * t + 32), 2) - 855.5 / 100000 * p - 1.13 * sqrt(Si) + 0.37 * Si - 2 / 1000 * sqrt(Si) * (1.8 * t + 32);
		return Is;
	}


	//根据Is判断是否结垢，这个函数需要修改以配合GUI完成输出
	public static String judge(double Is)
	{
		if (Is < 0) return "未达到饱和，不可能结垢";
		else if(Is==0) return "处于平衡状态";
		else return "有结垢趋势";
	}
	
}
