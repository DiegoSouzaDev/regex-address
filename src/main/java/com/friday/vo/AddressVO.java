package com.friday.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class AddressVO {
	
	private final String street;
	
	private final String housenumber;
}
