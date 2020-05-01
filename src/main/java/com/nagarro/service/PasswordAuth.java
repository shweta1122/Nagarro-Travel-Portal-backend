package com.nagarro.service;

import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class PasswordAuth {
	public String passwordGen() {
		return new Random().ints(10, 33, 122).mapToObj(i -> String.valueOf((char) i)).collect(Collectors.joining());
	}
}
