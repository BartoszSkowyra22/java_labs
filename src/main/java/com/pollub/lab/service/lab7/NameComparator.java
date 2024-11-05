package com.pollub.lab.service.lab7;

import java.util.Comparator;

public class NameComparator implements Comparator {

    private static String nameWithoutTitles(String name) {
        String[] titles = {"mgr ", "inż. ", "dr ", "hab. ", "prof. ", "uczelni"};
        String titlePattern = String.join("|", titles);


        return name.replaceAll("\\b(" + titlePattern + ")\\b", "").replaceAll(" +", " ").trim();
    }

    @Override
    public int compare(Object o1, Object o2) {
        String s1 = (String) o1;
        String s2 = (String) o2;

        s1 = nameWithoutTitles(s1).toLowerCase();
        s2 = nameWithoutTitles(s2).toLowerCase();
        if(s1.compareTo(s2) > 0)
            return 1;
        if(s1.compareTo(s2) < 0)
            return -1;

        return 0;
    }
}
