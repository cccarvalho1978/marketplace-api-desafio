package com.cristiano.marketplace.dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.time.DateUtils;

public class NewsApiDTO {

	private String status;
	private Integer totalResults;
	private List<Article> articles = null;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getTotalResults() {
		return totalResults;
	}

	public void setTotalResults(Integer totalResults) {
		this.totalResults = totalResults;
	}

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}

	
	public Integer getTotalByDateSearch(Date dateSearch) {
		Integer total = 0;
		for (Article article : articles) {
			if( article.isArticlePublishedInDate(dateSearch) ) {
				total += 1;
			}
		}
		return total;
	}
	
}

class Article {

	private String publishedAt;

	public String getPublishedAt() {
		return publishedAt;
	}

	public void setPublishedAt(String publishedAt) {
		this.publishedAt = publishedAt;
	}

	public boolean isArticlePublishedInDate(Date dateSearch) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date dateSearchTrunc = DateUtils.truncate(dateSearch, Calendar.DATE);
			Date published = sdf.parse(publishedAt.substring(0,10));
			if(published.equals(dateSearchTrunc)) {
				return true;
			}else {
				return false;
			}
		} catch (ParseException e) {
			return false;
		}
	}

}
