package com.jacmobile.technews.networking.objects;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by User on 7/19/2014.
 */
@Root(name="rss")
public class RssConfig
{
    @Element(name="channel")
    private RssChannel rssChannel;

    public RssChannel getRssChannel()
    {
        return rssChannel;
    }

    public class RssChannel
    {
        @Element(name="title")
        private String title;
        @Element(name="link")
        private String link;
        @Element(name="description")
        private String description;
        @Element(name="language")
        private String language;
        @Element(name="copyright")
        private String copyright;
        @Element(name="pubDate")
        private String pubDate;
        @Element(name="lastBuildDate")
        private String lastBuildDate;
        @Element(name="ttl")
        private String ttl;
        @Element(name="image")
        private Image image;
        @Element(name="item")
        private Item item;

        public Item getItem()
        {
            return item;
        }

        public Image getImage()
        {
            return image;
        }

        public String getTitle()
        {
            return title;
        }

        public String getLink()
        {
            return link;
        }

        public String getDescription()
        {
            return description;
        }

        public String getLanguage()
        {
            return language;
        }

        public String getCopyright()
        {
            return copyright;
        }

        public String getPubDate()
        {
            return pubDate;
        }

        public String getLastBuildDate()
        {
            return lastBuildDate;
        }

        public String getTtl()
        {
            return ttl;
        }
    }

    public class Image
    {
        @Element(name="title")
        private String title;
        @Element(name="url")
        private String url;
        @Element(name="link")
        private String link;

        public String getTitle()
        {
            return title;
        }

        public String getUrl()
        {
            return url;
        }

        public String getLink()
        {
            return link;
        }
    }
    public class Item
    {
        private String tile;
        private String link;
        private String description;
        private String category;
        private String pubDate;
        private String guid;
        private String dcCreator;
        private String mediaCredit;
        private String mediaDescription;
        private String mediaContent;

        public String getMediaContent()
        {
            return mediaContent;
        }

        public String getTile()
        {
            return tile;
        }

        public String getLink()
        {
            return link;
        }

        public String getDescription()
        {
            return description;
        }

        public String getCategory()
        {
            return category;
        }

        public String getPubDate()
        {
            return pubDate;
        }

        public String getGuid()
        {
            return guid;
        }

        public String getDcCreator()
        {
            return dcCreator;
        }

        public String getMediaCredit()
        {
            return mediaCredit;
        }

        public String getMediaDescription()
        {
            return mediaDescription;
        }
    }
}
