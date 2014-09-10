package com.kaykisiz.web.controller;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.kaykisiz.web.entity.posts.Post;


@ManagedBean(name = "sessionController")
@SessionScoped
public class SessionController implements Serializable {

	/**
	 * Oturum içerisinde tutulacak bilgiler.
	 */
	private static final long serialVersionUID = -916435091826519311L;
//
//	Aranacak olan kullanıcının adı.
//	
	private String SearchUser;

	public String getSearchUser() {
		return SearchUser;
	}

	public void setSearchUser(String searchUser) {
		SearchUser = searchUser;
	}

}
