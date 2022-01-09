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

	@Column(name="user_id")
	private long user_id;

	@ManyToOne
	@JoinColumn(name="user_id", referencedColumnName = "id", nullable = false)
	private User user;

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

	public long getUser_id() {
		return user_id;
	}

	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}
}