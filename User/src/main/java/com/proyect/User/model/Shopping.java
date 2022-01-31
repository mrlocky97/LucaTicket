package com.proyect.User.model;

import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.springframework.context.annotation.Primary;
import com.proyect.User.model.User;

import lombok.Data;

@Data
@Entity
@Table(name="shopping")
public class Shopping {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idshopping;
	
	@OneToMany( targetEntity=User.class )
    private List<User> user;

	private String code;
    private String price;
    private String name;

	@Override
	public String toString() {
		return "Shopping [idshopping=" + idshopping + ", code=" + code
				+ ", price=" + price + ", name=" + name + "]";
	}
  
}
