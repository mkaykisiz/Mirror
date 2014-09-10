package com.kaykisiz.web.util;

import java.util.List;

import com.kaykisiz.web.entity.posts.Post;

/*
 * Bu sınıftaki metotlar gelene Post-content içeriklerini formatlama için kullanılacak.
 * 
 * */
public class PostUtil {

	/*
	 * Bu metot gönderilen Post-content içeriğindeki twitter username adını
	 * ayıklamaktadır.
	 */
	public static String selectUserName(String content) {
		boolean at = false;
		String username = "";
		for (char ch : content.toCharArray()) {
			if (ch == ' ' || ch == '*' || ch == '-' || ch == '@')
				at = false;

			if (ch == '@')
				at = true;

			if (at) {
				username = username + ch;
			}
		}

		return username;
	}

	/*
	 * Bu metot gönderilen Post-content içeriğine twitter linki vermek için
	 * kullanılacak.
	 */

	@SuppressWarnings("null")
	public static List<Post> createHtmlViewContent(List<Post> contents) {
		String username;
		String urlContent;
		String urlall;
String a="";
		for (Post post : contents){
		username = selectUserName(post.getContent());
		urlContent = "https://twitter.com/" + username;
		urlall = "<a href=\"" + urlContent + "\" target=\"_blank\" >" + username + "</a>";
		a=post.getContent();
		post.setContent(a.replaceAll(username, urlall));
		System.out.println(urlall);
		System.out.println(a.replaceAll(username, urlall));
		}
		return contents;
	}

}
