package com.Mutation.Tr.portfolio;

import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TestController {
    static int[] picks = {1, 3, 2};
    static String[] minerals = {"diamond", "diamond", "diamond", "iron", "iron", "diamond", "iron", "stone"};
    static int[] sort_mineral = new int[3];
    static int mineral = 2;
    static double stamina = 0;

    @Value("${portfolio.path}")
    private static String path;
    public static void main(String[] args) {
        System.err.println(path);
    }

}
