package com.kaykisiz.web.controller.post;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import com.kaykisiz.web.dao.posts.IPostDao;
import com.kaykisiz.web.util.PostUtil;

/*
 * Silinecek kullanıcı gönderileri içinkullanılan controller.
 */
@ManagedBean(name = "deleteController")
@RequestScoped
public class DeletePostController {
	private String userName;

	@ManagedProperty(value = "#{postDao}")
	private IPostDao postDao;

	public IPostDao getPostDao() {
		return postDao;
	}

	public void setPostDao(IPostDao postDao) {
		this.postDao = postDao;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	//
	// Aldığı kullanıcı adını gerekli foksiyona gönderir.
	//
	public void deleteUser() {
		try {
			postDao.delete(userName);
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					PostUtil.selectUserName(userName)
							+ " Adlı kişi için Kayıtlar silindi !", null);
			FacesContext.getCurrentInstance().addMessage(null, message);
			userName = "";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
