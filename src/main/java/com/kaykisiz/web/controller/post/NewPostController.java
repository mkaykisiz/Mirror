package com.kaykisiz.web.controller.post;

import java.io.Serializable;

import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import com.kaykisiz.web.dao.posts.IPostDao;
import com.kaykisiz.web.entity.posts.Post;
import com.kaykisiz.web.util.PostUtil;

@ManagedBean(name = "NewPostController")
@RequestScoped
public class NewPostController implements Serializable {

	/**
	 * Yeni girilecek postun controllerıdır. "newPostString" adlı stringe değeri
	 * alır.
	 */
	private static final long serialVersionUID = 1L;

	private String newPostString;

	@ManagedProperty(value = "#{postDao}")
	private IPostDao postDao;

	public IPostDao getPostDao() {
		return postDao;
	}

	public void setPostDao(IPostDao postDao) {
		this.postDao = postDao;
	}

	public String getNewPostString() {
		return newPostString;
	}

	public void setNewPostString(String newPostString) {
		this.newPostString = newPostString;
	}

	//
	//Bu metotta yeni gelen gönderinin kayıt işlemi yapılır.
	//
	public void AddNewPost() {
		if (newPostString.equals("")) {
			System.out.println("BOŞŞŞ");
		} else {
			try {
				//Gönderi en az 6 karakter uzunluğunda olmalıdır.
				if (newPostString.length() > 6) {
					Post newPost = new Post();
					newPost.setContent(newPostString);
					newPost.setCreatedDate(new Date());
					postDao.insert(newPost);
					System.out.println(newPost.getContent());

					FacesMessage message = new FacesMessage(
							FacesMessage.SEVERITY_INFO,
							PostUtil.selectUserName(newPostString)
									+ " Adlı kişi için Mirror Kaydedildi !",
							null);
					FacesContext.getCurrentInstance().addMessage(null, message);
					newPostString = "";
				} else {
					FacesMessage message = new FacesMessage(
							FacesMessage.SEVERITY_INFO,
							" Lütfen daha uzun birşeyler yazın ! ", null);
					FacesContext.getCurrentInstance().addMessage(null, message);
				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
