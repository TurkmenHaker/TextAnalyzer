package com.example.instinctools.TextAnalyzer;

import java.util.Objects;

public class Entry {
    private String text;
    private Integer count;

    public Entry() {
    }

    public Entry(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return text + ':' + count + "  ";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entry entry = (Entry) o;
        return Objects.equals(text, entry.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text);
    }
}
