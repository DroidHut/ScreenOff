package me.andika.lockscreen.NewsApp;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.Observable;

class XMLParser extends Observable {

    private ArrayList<Article> articles;
    private Article currentArticle;

     XMLParser() {
        articles = new ArrayList<>();
        currentArticle = new Article();
    }

    void parseXML(String xml) {

        try {

            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();

            factory.setNamespaceAware(true);
            XmlPullParser xmlPullParser = factory.newPullParser();
            // XmlPullParser parser = Xml.newPullParser();
           // xmlPullParser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, true);

            xmlPullParser.setInput(new StringReader(xml));
            boolean insideItem = false;
            boolean outsideItem = false;
            int eventType = xmlPullParser.getEventType();

            while (eventType != XmlPullParser.END_DOCUMENT) {

                if (eventType == XmlPullParser.START_TAG) {
                    if (xmlPullParser.getName().equalsIgnoreCase("title")) {

                        outsideItem = true;
                    } else if (xmlPullParser.getName().equalsIgnoreCase("title")) {

                        if (outsideItem) {

                            String htmlData = xmlPullParser.nextText();
                            currentArticle.setName(htmlData);

                            if (htmlData == null) {
                                currentArticle.setName(null);
                            }
                            currentArticle.setContent(htmlData);

                        }
                    }
                  /* else if (xmlPullParser.getName().equalsIgnoreCase("title")) {

                       if (outsideItem) {

                           String title = xmlPullParser.nextText();
                           currentArticle.setName(title);
                       }

                   }*/

                    if (xmlPullParser.getName().equalsIgnoreCase("item")) {

                        insideItem = true;

                    } else if (xmlPullParser.getName().equalsIgnoreCase("title")) {
                        
                        if (insideItem) {

                            String title = xmlPullParser.nextText();
                            if(title.equals(null)){
                                xmlPullParser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES,true);
                                String t = xmlPullParser.nextText();
                                currentArticle.setTitle(t);
                            }else{
                                currentArticle.setTitle(title);
                            }
                          
                        }

                    } else if (xmlPullParser.getName().equalsIgnoreCase("link")) {

                        if (insideItem) {

                            String link = xmlPullParser.nextText();
                            currentArticle.setLink(link);
                        }

                    } else if (xmlPullParser.getName().equalsIgnoreCase("story")) {

                        if (insideItem) {

                            String des = xmlPullParser.nextText();
                            currentArticle.setDescription(des);
                        }

                    } else if (xmlPullParser.getName().equalsIgnoreCase("News:Image")) {

                        if (insideItem) {

                            String des = xmlPullParser.nextText();
                            currentArticle.setImage(des);
                        }

                    } else if (xmlPullParser.getName().equalsIgnoreCase("dc:creator")) {

                        if (insideItem) {

                            String author = xmlPullParser.nextText();
                            currentArticle.setAuthor(author);
                        }

                    } else if (xmlPullParser.getName().equalsIgnoreCase("category")) {

                        if (insideItem) {

                            String category = xmlPullParser.nextText();
                            currentArticle.setCategory(category);
                        }

                    } else if (xmlPullParser.getName().equalsIgnoreCase("description")) {

                        if (insideItem) {

                            String htmlData = xmlPullParser.nextText();
                            Document doc = Jsoup.parse(htmlData);
                            try {
                                //choose the first image found in the article
                                String pic = doc.select("img").attr("abs:src");
                                if (pic != null) {
                                    //String pic2 = doc.select("img").attr("abs:src");    
                                    currentArticle.setImage(pic);
                                } else {

                                    String des = doc.text();
                                    if (des.length() > 175) {
                                        currentArticle.setDescription(des.substring(0, 175).concat("..."));
                                    } else {
                                        currentArticle.setDescription(des);
                                    }
                                }
                                String des = doc.text();
                                if (des.length() > 175) {
                                    currentArticle.setDescription(des.substring(0, 175).concat("..."));
                                } else {
                                    currentArticle.setDescription(des);
                                }


                            } catch (NullPointerException e) {

                                currentArticle.setImage(null);

                            }

                            currentArticle.setContent(htmlData);
                        }

                    } else if (xmlPullParser.getName().equalsIgnoreCase("image")) {

                        if (insideItem) {
                            try {
                                String img = xmlPullParser.nextText();
                                currentArticle.setImage(img);
                                if(img.equals(null)){
                                    xmlPullParser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, true);
                                    
                                    String image = xmlPullParser.getAttributeValue(null,"href");
                                            currentArticle.setImage(image);
                                        
                                }
                            } catch(NullPointerException e){
                                    currentArticle.setImage(null);
                                }

                            
                        }

                    } else if (xmlPullParser.getName().equalsIgnoreCase("thumbnail")) {
                        xmlPullParser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, true);
                        if (insideItem) {
                            try {
                                String img = xmlPullParser.getAttributeValue(null,"url");
                                currentArticle.setImage(img);
                            } catch (NullPointerException e) {
                                currentArticle.setImage(null);

                            }
                        }


                    }
                   
                    else if (xmlPullParser.getName().equalsIgnoreCase("content")) {
                        xmlPullParser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, true);
                        if (insideItem) {
                            try {
                                String img = xmlPullParser.getAttributeValue(null,"url");
                                currentArticle.setImage(img);
                            } catch (NullPointerException e) {
                                currentArticle.setImage(null);

                            }
                        }


                    }
                    else if (xmlPullParser.getName().equalsIgnoreCase("fullimage")) {

                        if (insideItem) {
                            try {
                                String img = xmlPullParser.nextText();
                                currentArticle.setImage(img);
                            } catch (NullPointerException e) {
                                currentArticle.setImage(null);

                            }
                        }

                    } /*else if (xmlPullParser.getPrefix().equals("media")) {
                        try {
                            if (insideItem) {
                                String img = xmlPullParser.getNamespace();
                             //  Toast.makeText(,img,Toast.LENGTH_LONG).show();
                                Log.i("NAMESPACE",img);
                                currentArticle.setImage(img);
                            }
                        } catch (NullPointerException e) {
                        }
                    } */else if (xmlPullParser.getName().equalsIgnoreCase("enclosure")) {
                        if (insideItem) {

                            try {
                                String img = xmlPullParser.nextText();
                                Document doc = Jsoup.parse(img);
                                String pic = doc.select("enclosure").attr("url");

                                currentArticle.setImage(pic);
                            } catch (NullPointerException e) {
                                currentArticle.setImage(null);

                            }
                        }
                    } else if (xmlPullParser.getName().equalsIgnoreCase("content:encoded")) {

                        if (insideItem) {

                            String htmlData = xmlPullParser.nextText();
                            Document doc = Jsoup.parse(htmlData);
                            try {

                                //choose the first image found in the article
                                String pic = doc.select("img").first().attr("abs:src");
                                if (pic == null) {
                                    String pic2 = doc.select("image").text();
                                    currentArticle.setImage(pic2);
                                    if (pic2 == null) {
                                        String pic3 = doc.select("enclosure").attr("link");
                                        currentArticle.setImage(pic3);
                                        if (pic3 == null) {
                                            String pic4 = doc.select("media").attr("url");
                                            currentArticle.setImage(pic4);
                                        }
                                    }
                                }


                                currentArticle.setImage(pic);

                            } catch (NullPointerException e) {
                                currentArticle.setImage(null);

                            }

                            currentArticle.setContent(htmlData);
                        }

                    } else if (xmlPullParser.getName().equalsIgnoreCase("pubDate")) {

                        @SuppressWarnings("deprecation")
                        Date pubDate = new Date(xmlPullParser.nextText());
                        currentArticle.setPubDate(pubDate);
                    }

                } else if (eventType == XmlPullParser.END_TAG && xmlPullParser.getName().equalsIgnoreCase("item")) {

                    insideItem = false;
                    articles.add(currentArticle);
                    currentArticle = new Article();
                }
                eventType = xmlPullParser.next();
            }

            triggerObserver();

        } catch (Exception e) {

            e.printStackTrace();

        }
    }

    private void triggerObserver() {

        setChanged();
        notifyObservers(articles);
    }
}