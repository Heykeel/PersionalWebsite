package com.heykeel.util;

import com.heykeel.entity.Blog;

public class PageUtil {

	public static String getPagination(String url, Integer totalNum, Integer currentPage, Integer pageSize) {
		// 计算总页数
		Integer totalPage = totalNum % pageSize == 0 ? totalNum / pageSize : totalNum / pageSize + 1;
		if(totalPage==0){
			return "无数据！";
		}else {
			StringBuffer pageCode = new StringBuffer();
			if(currentPage > 1){
				pageCode.append("<li><a href='"+url+"?page=1"+"'>首页</a></li>");
				pageCode.append("<li><a href='"+url+"?page="+(currentPage-1)+"'>上一页</a></li>");
			}else {
				pageCode.append("<li class='disabled'><a>首页</a></li>");
				pageCode.append("<li class='disabled'><a>上一页</a></li>");	
			}
			for(int i = currentPage-2;i<=currentPage+2;i++){
				if(i<1||i>totalPage){
					continue;
				}
				if(i == currentPage){
					pageCode.append("<li class='active'><a href='"+url+"?page="+i+"'>"+i+"</a></li>");
				}else {
					pageCode.append("<li><a href='"+url+"?page="+i+"'>"+i+"</a></li>");
				}
			}
			if(currentPage<totalPage){
				pageCode.append("<li><a href='"+url+"?page="+(currentPage+1)+"'>下一页</a></li>");
				pageCode.append("<li><a href='"+url+"?page="+totalPage+"'>尾页</a></li>");
			}else {
				pageCode.append("<li class='disabled'><a>下一页</a></li>");	
				pageCode.append("<li class='disabled'><a>尾页</a></li>");
			}
			return pageCode.toString();
		}
	}
	
	public static String getPrevAndNextArticle(Blog prev, Blog next,String projectContent) {
		StringBuffer pageCode = new StringBuffer();
		
		if(next == null && prev != null) {
			pageCode.append("<p>上一篇：<a href='"+projectContent+"/blog/articles/"+prev.getId()+".html'>"+prev.getTitle()+"</a></p>");
			pageCode.append("<p>下一篇：无</p>");
		}else if(next != null && prev == null) {
			pageCode.append("<p>上一篇：无</p>");
			pageCode.append("<p>下一篇：<a href='"+projectContent+"/blog/articles/"+next.getId()+".html'>"+next.getTitle()+"</a></p>");
		}else {
			pageCode.append("<p>上一篇：<a href='"+projectContent+"/blog/articles/"+prev.getId()+".html'>"+prev.getTitle()+"</a></p>");
			pageCode.append("<p>下一篇：<a href='"+projectContent+"/blog/articles/"+next.getId()+".html'>"+next.getTitle()+"</a></p>");
		}
		
		return pageCode.toString();
	}

}
