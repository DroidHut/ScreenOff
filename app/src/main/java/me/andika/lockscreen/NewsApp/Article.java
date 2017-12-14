package me.andika.lockscreen.NewsApp;

import java.io.Serializable;
import java.util.Date;

public class Article implements Serializable {

    private String title;
    private String author;
    private String link;
    private Date pubDate;
    private String description;
    private String content;
    private String image;
    private String logo;
    private String name;
    private String category;

    public Article(String title, String description,String image,String link,String name,Date pubDate,String category) {
        this.title=title;
        this.description=description;
        this.image=image;
        this.link=link;
        this.name=name;
        this.pubDate=pubDate;
        this.category=category;
    }
    public Article() {
    }
    public String getTitle() {
        return title;

    }
    public String getName() {
        return name;
    }
    public String getLogo(){
        return logo;
    }

    public String getAuthor() {

        return author;

    }

    public String getLink() {

        return link;

    }

    public Date getPubDate() {

        return pubDate;

    }

    public String getDescription() {

        return description;

    }

    public String getContent() {

        return content;

    }

    public String getImage() {

        return image;

    }

    public String getCategory() {

        return category;

    }

    public void setCategory(String category) {

        this.category = category;

    }

    public void setTitle(String title) {

        this.title = title;

    }
    public void setLogo(String logo) {

        this.logo = logo;
    }
    public void setName(String name) {
        this.name = name;
    }



    public void setAuthor(String author) {

        this.author = author;

    }

    public void setLink(String link) {

        this.link = link;

    }

     public void setPubDate(Date pubDate) {

        this.pubDate = pubDate;

    }

    public void setDescription(String description) {

        this.description = description;

    }

    public void setContent(String content) {

        this.content = content;

    }

    public void setImage(String image) {

        this.image = image;

    }

    @Override
    public String toString() {

        return "Article{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", link='" + link + '\'' +
                ", pubDate=" + pubDate +
                ", description='" + description + '\'' +
                ", content='" + content + '\'' +
                ", image='" + image + '\'' +
                '}';

    }

   

}