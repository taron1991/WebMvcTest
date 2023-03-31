package org.pro.mockito.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MainClass {

    private final Dependency dependency;

    @Autowired
    public MainClass(Dependency dependency) {
        this.dependency = dependency;
    }

    public int findTheGreatestFromAllData() {
        int[] data = dependency.retrieveAllData();
        int greatest = Integer.MIN_VALUE;

        for (int value : data) {
            if (value > greatest) {
                greatest = value;
            }
        }
        return greatest;
    }

    public int findArrayLength() {
        return dependency.arrayLength().length;
    }
}
