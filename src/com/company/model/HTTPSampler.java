package com.company.model;

public class HTTPSampler {
    public String name;
    public int id;
    public String bodyData;

    public HTTPSampler(int id, String name, String bodyData) {
        this.name = name;
        this.id = id;
        this.bodyData = bodyData;
    }

    @Override
    public String toString() {
        return name + " " + bodyData + "\n";
    }

    public String test () {
        return name;
    }
}

