package ru.saa.carpet.service;

import org.junit.Test;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: saa
 * Date: 4/4/13
 * Time: 23:16 PM
 * To change this template use File | Settings | File Templates.
 */
public class GeneratorTest {


    @Test
    public void normalDistribution(){

        Random r = new Random();

        for (int  i = 0; i<10 ;i++){

//        System.out.print(r.nextInt(10) + " ");
//            System.out.println();
        System.out.print((int)(10*r.nextGaussian()) + " ");

        }


    }
}
