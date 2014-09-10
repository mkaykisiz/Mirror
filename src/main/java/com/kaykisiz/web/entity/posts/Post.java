package com.kaykisiz.web.entity.posts;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.kaykisiz.web.entity.BaseEntity;
/*
 * Post entitymiz.İçerik ve oluşturma terihi değişkenleri mevcut.
 */
@Entity
@Table(name = "PostTable")
@SequenceGenerator(name = "idSequence", sequenceName = "SEQ_DOCTABLE")
public class Post extends BaseEntity implements Serializable {

	private static final long serialVersionUID = -7256978939664958161L;

	@Column(name = "CONTENT")
	private String content;


	@Column(name = "CREATED")
	private Date createdDate;


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public Date getCreatedDate() {
		return createdDate;
	}


	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}


}
