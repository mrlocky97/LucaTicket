package com.proyect.User.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="shopping")
public class Shopping {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idshopping;
	
	//@OneToMany( targetEntity=User.class )
    private String user;

	private String code;
    private String price;
    private String name;

	@Override
	public String toString() {
		return "Shopping [idshopping=" + idshopping + ", code=" + code
				+ ", price=" + price + ", name=" + name + "]";
	}
  
}
