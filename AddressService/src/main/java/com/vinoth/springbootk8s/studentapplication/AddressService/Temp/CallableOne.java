package com.vinoth.springbootk8s.studentapplication.AddressService.Temp;

import java.util.concurrent.Callable;

public class CallableOne implements Callable<Integer> {
	
	private int nVal;

	public CallableOne(int nVal) {
		super();
		this.nVal = nVal;
	}

	@Override
	public Integer call() throws Exception {
//		Thread.sleep(2000);
		int sum = 0;
		for(int i=1; i<=nVal; i++) {
			sum+=i;
			System.out.println(Thread.currentThread().getName());
		}
		return sum;
	}

}
