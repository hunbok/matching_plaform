package com.matching.plaform.domain;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Member {
	
	private String id;
	private String name;
	private String pass;
	private String email;
	private String mobile;
	private String zipcode;
	private String address1;
	private String address2;
	private String email_get;
	private Timestamp reg_date;
	private String nickname;
	
	
}
