package test;

import com.example.scale.utils.CalTools;

public class TestCal {

	/**
	 * @param args
	 */
	public static void main(String[] args) {//IsCal_S
//		System.out.println(CalTools.IsCal_Cnogas(A, B, p, t, Si, Yt, Qg, Qw, Qo));
		
		double cwithgas = CalTools.IsCal_Cwithgas(152, 897, 3.44, 87.78, 0.24, 0.01941, 0.01246, 46.74, 20.99);
		System.out.println(cwithgas);
		
		System.out.println("\n==============================");
		double cnogas = CalTools.IsCal_Cnogas(152, 897, 3.44, 87.78, 0.24, 0.01941, 0.01246, 46.74, 20.99);
		System.out.println(cnogas);
		
		System.out.println("\n==============================");
		double[] data = CalTools.IsCal_S(22825, 29.66, 104.4, 0.00015, 0.00015, 0.0005, 0.00375);
		for(double d : data){
			System.out.println(d);
		}
		
	}

}
