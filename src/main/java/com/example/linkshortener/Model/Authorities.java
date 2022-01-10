package com.example.linkshortener.Model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Table(name = "authorities",
	uniqueConstraints = @UniqueConstraint(columnNames = {"user_id","authority"}))
public class Authorities implements GrantedAuthority {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private long id;

	@Column(name = "authority")
	private String authority;

	@ManyToOne
	@JoinColumn(name="user_id", referencedColumnName = "id", nullable = false)
	private User user;

	public Authorities(String authority,User user){
		this.authority=authority;
		this.user=user;
	}

	public Authorities() {

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

}