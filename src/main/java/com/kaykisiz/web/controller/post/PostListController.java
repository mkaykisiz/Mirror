package com.kaykisiz.web.controller.post;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.kaykisiz.web.controller.SessionController;
import com.kaykisiz.web.util.PostUtil;

import com.kaykisiz.web.dao.posts.IPostDao;
import com.kaykisiz.web.entity.posts.Post;

@ManagedBean(name = "postListController")
@ViewScoped
public class PostListController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{postDao}")
	private IPostDao postDao;

	@ManagedProperty(value = "#{sessionController}")
	private SessionController sessionController;

	public PostListController() {
	}

	public IPostDao getPostDao() {
		return postDao;
	}

	public void setPostDao(IPostDao postDao) {
		this.postDao = postDao;
	}

	public SessionController getSessionController() {
		return sessionController;
	}

	public void setSessionController(SessionController sessionController) {
		this.sessionController = sessionController;
	}

	//
	//Tüm gönderilern listelenmesi için kullanılır. View için gerekli formata dönüştürülmek için "createHtmlViewContent metoduna gönderilir."
	//
	public List<Post> getPostList() throws Exception {
		return PostUtil.createHtmlViewContent(getPostDao().fetchRecordsAll());
	}

	public List<Post> getPostListToUser() throws Exception {
		return PostUtil.createHtmlViewContent(getPostDao().fetchPostRecordsUserLike(
				sessionController.getSearchUser()));
	}
}
