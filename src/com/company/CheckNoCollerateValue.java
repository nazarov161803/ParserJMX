package com.company;

import com.company.model.HTTPSampler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckNoCollerateValue {

    static Map<String, List<String>> valueInBody = new HashMap<>();
    static Map<String, List<String>> dateInBody = new HashMap<>();


    public static void findNoCollerateValueInBody(ArrayList<HTTPSampler> httpSampler) {

        for (int i = 0; i < httpSampler.size(); i++) {
            String result = httpSampler.get(i).toString();

            Matcher numberMatcher = Pattern.compile("[:\"](\\d{3,})[}\"\\\\]").matcher(result);
            while (numberMatcher.find()) {


                if (valueInBody.containsKey(numberMatcher.group(1))) {
                    valueInBody.get(numberMatcher.group(1)).add(httpSampler.get(i).test());
                } else {
                    List<String> list = new ArrayList<>();
                    list.add(httpSampler.get(i).test());
                    valueInBody.put(numberMatcher.group(1), list);
                }
            }
        }
        for (Map.Entry<String, List<String>> stringListEntry : valueInBody.entrySet()) {
            System.out.println(stringListEntry);

        }
        System.out.println("___________________________________________________________________________");
        System.out.println("Всего найдено значений : " + valueInBody.size());
        System.out.println();
    }

    public static void findNoColerateDateInBody(ArrayList<HTTPSampler> httpSampler) {
        for (int i = 0; i < httpSampler.size(); i++) {
            String result = httpSampler.get(i).toString();

            Matcher numberMatcher = Pattern.compile("(\\d{4})-(\\d{2})-(\\d{2})").matcher(result);
            while (numberMatcher.find()) {
                if (dateInBody.containsKey(numberMatcher.group())) {
                    dateInBody.get(numberMatcher.group()).add(httpSampler.get(i).test());
                } else {
                    List<String> list = new ArrayList<>();
                    list.add(httpSampler.get(i).test());
                    dateInBody.put(numberMatcher.group(), list);
                }
            }
        }
        for (Map.Entry<String, List<String>> stringListEntry : dateInBody.entrySet()) {
            System.out.println(stringListEntry);

        }
        System.out.println("___________________________________________________________________________");
        System.out.println("Всего найдено дат : " + dateInBody.size());
        System.out.println();
    }
}


