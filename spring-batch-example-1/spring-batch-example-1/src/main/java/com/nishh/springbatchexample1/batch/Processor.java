package com.nishh.springbatchexample1.batch;

import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.nishh.springbatchexample1.model.User;

@Component
public class Processor implements ItemProcessor<User, User> {

	private static final Map<String, String> DEPT_NAME = new HashMap<>();

	public Processor() {
		DEPT_NAME.put("001", "FSD2");
		DEPT_NAME.put("002", "SD");
		DEPT_NAME.put("003", "FSD");
	}

	@Override
	public User process(User user) throws Exception {

		String deptCode = user.getDept();
		String dept = DEPT_NAME.get(deptCode);
		user.setDept(dept);
		System.out.println(String.format("Converted from [%s] to [%s]", deptCode, dept));
		return user;
	}
}
