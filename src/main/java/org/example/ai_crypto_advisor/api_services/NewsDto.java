package org.example.ai_crypto_advisor.api_services;

public class NewsDto {

    private String title;
    private String description;
    private String publishedAt;

    public NewsDto() {
    }

    public NewsDto(String title, String description, String publishedAt) {
        this.title = title;
        this.description = description;
        this.publishedAt = publishedAt;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }
}
