package com.golike.magicgankclient.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2018/2/2.
 */

public class SearchData {

    boolean error;

    private List<GANK> results;

    public List<GANK> getResults() {
        return results;
    }

    public void setResults(List<GANK> results) {
        this.results = results;
    }

    public class GANK {

        String _id;

        String createdAt;

        public String desc;

        private String url;

        private List<String> images;

        private String publishedAt;

        private String source;

        private String type;

        private String who;

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getWho() {
            return who;
        }

        public void setWho(String who) {
            this.who = who;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }


        public List<String> getImages() {
            if (images == null) {
                return new ArrayList<>();
            }
            return images;
        }
    }

}
