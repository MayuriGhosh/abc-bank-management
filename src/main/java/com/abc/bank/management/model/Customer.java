package com.abc.bank.management.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Customer {

	@Id
	private int acctID;
	private String custName;
	private String city;
	private String state;
	private String country;
	private long phoneNo;
	private String password;

	

}
