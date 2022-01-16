package com.vinoth.springbootk8s.studentapplication.AddressService.Temp;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecTest {

	public static void main(String[] args) throws InterruptedException, ExecutionException {

		ExecutorService executorService = Executors.newCachedThreadPool();
		List<Future<Integer>> list = new ArrayList<>();
		List<Future<Integer>> list2 = new ArrayList<>();
		for(int i=1; i<5; i++) {
			Future<Integer> future = executorService.submit(new CallableOne(i));
			list.add(future);
			Future<Integer> future2 = executorService.submit(new Callable<Integer>() {

				@Override
				public Integer call() throws Exception {
					return future.get() * 2;
				}
				
			});
			list2.add(future2);
		}
		
			
		list.stream().forEach(eachList->{
			try {
				System.out.println(eachList.get());
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		});
		
		list2.stream().forEach(eachList->{
			try {
				System.out.println(eachList.get());
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		});
     }
}
