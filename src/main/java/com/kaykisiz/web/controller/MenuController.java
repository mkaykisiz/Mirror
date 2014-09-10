package com.kaykisiz.web.controller;


import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
/*
 *Menü yönlendirmeleri yapılır.
 */
@ManagedBean(name = "menuController")
@RequestScoped
public class MenuController implements Serializable{

	private static final long serialVersionUID = 3623744119479077604L;
	
	public String navigatepostListToUser(){
		return "userMirrors";
	}
	
	public String navigateLastPosts(){
		return "lastPost";
	}
	
	public String navigateSearchUser(){
		return "searchUser";
	}
	
}