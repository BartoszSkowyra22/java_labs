package com.pollub.lab.service.lab7;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;

public class Lab7 {
    public void runLab7() throws IOException {
        Map<String, List<String>> staff = exercise1();
        exercise2(staff);
        exercise3();
    }

    static void exercise3() throws IOException {
        System.out.println("EXERCISE 3");
        String newsUrl = "https://cs.pollub.pl/news/";
        Document doc1 = Jsoup.connect(newsUrl).get();
        Elements article1 = doc1.select(".summary");
        Map<String, String> map = new HashMap<>();
        for (Element e : article1) {
            Elements el = e.children();

            if(el.isEmpty() || el.first() == null || el.size() < 2){
                continue;
            }
            map.put(el.first().text(), el.get(1).text());
        }

        for (String s : map.keySet()) {
            System.out.println(s + "\n" + map.get(s) + "\n");
        }
    }

    static Map<String, List<String>> exercise1() throws IOException {
        System.out.println("EXERCISE 1");
        String blogUrl = "https://cs.pollub.pl/staff";
        String last = "";
        Document doc = Jsoup.connect(blogUrl).get();
        Map<String, List<String>> staff = new HashMap<>();
        Elements article = doc.select("article");
        Elements elements = article.first().children();
        Comparator nameComparator = new NameComparator();

        for (Element e : elements) {
            System.out.println(e);
            if (e.text().contains("dr hab.") && e.is("h3")) {
                staff.put("Kierownik Katedry", new ArrayList<>());
                staff.get("Kierownik Katedry").add("dr hab. Tomasz Zientarski");
            } else {
                if (e.is("h3")) {
                    staff.put(e.text(), new ArrayList<>());
                    last = e.text();
                }

                if (e.is("p")) {
                    Elements employee = e.select("a");
                    for (Element h : employee) {
                        staff.get(last).add(h.text());
                    }
                }
            }
        }

        for (String s : staff.keySet()) {
            System.out.println(s + ": " + staff.get(s));
        }

        for (String s : staff.keySet()) {
            Collections.sort(staff.get(s), nameComparator);
        }
        System.out.println("SORTED:");

        for (String s : staff.keySet()) {
            System.out.println(s + ": " + staff.get(s));
        }
        return staff;
    }

    static void exercise2(Map<String, List<String>> staff) {
        System.out.println("EXERCISE 2\n");
        System.out.println("List of dr and dr inz.:");
        for (String s : staff.keySet()) {
            for (String person : staff.get(s)) {
                if (person.contains("dr") || person.contains("dr inż.")) {
                    System.out.println(person);
                }
            }
        }
    }
}
