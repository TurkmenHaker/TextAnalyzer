package com.example.instinctools.TextAnalyzer;

import java.util.ArrayList;
import java.util.List;

public class RepeatedElements {

    public List<Entry> findSameEl(String data) {
        List<Entry> entries = new ArrayList<>();
        Integer counter;
        data=data.replaceAll("[\\p{Ps}\\p{Pe}]", "");
        String[] text = data.toLowerCase().split(" ");
        for (int i = 0; i < text.length; i++) {
            Entry entry = new Entry(text[i]);
            if (!entries.contains(entry)) {
                entry.setText(text[i]);
                counter = 1;
                for (int j = 0; j < text.length; j++)
                    if (text[i].equals(text[j]) && i != j)
                        counter++;
                entry.setCount(counter);
                entries.add(entry);
            }
        }
        return entries;
    }
}
