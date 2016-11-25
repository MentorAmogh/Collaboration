package com.collaborationserver.model;

import javax.persistence.*;
@Entity
@Table(name="users")
public class Users
{
	@Column
    private String FName;
    @Id
    private String username;
    @Column
    private String address;
    @Column 
    private String email;
    @Column
    private long phno;
   /* @Column
    private byte[]  imagepth;
    public byte[] getImagepth() {
		return imagepth;
	}
	public void setImagepth(byte[] imagepth) {
		this.imagepth = imagepth;
	}
	*/
	@Transient
    private String password;
	public String getPassword() {
		return password;
	}
	public void setPassword(String passwor) {
		FName = passwor;
	}
	public String getFName() {
		return FName;
	}
	public void setFName(String fName) {
		FName = fName;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public long getPhno() {
		return phno;
	}
	public void setPhno(long phno) {
		this.phno = phno;
	}
}