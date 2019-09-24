package com.nishh.springbatchexample1.batch;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nishh.springbatchexample1.model.User;
import com.nishh.springbatchexample1.repository.UserRepository;

@Component
public class DBWriter implements ItemWriter<User> {

	@Autowired
	private UserRepository repos;

	@Override
	public void write(List<? extends User> users) throws Exception {
		System.out.println("Data saved for users" + users);
		repos.save(users);

	}

}
