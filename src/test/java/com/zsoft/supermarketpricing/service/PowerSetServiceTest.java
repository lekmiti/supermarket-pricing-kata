package com.zsoft.supermarketpricing.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.*;

import static com.zsoft.supermarketpricing.service.PowerSetService.powerSet;

import static org.junit.Assert.*;


@RunWith(Parameterized.class)
public class PowerSetServiceTest {

    private Set<Integer> set;

    private int setSize, subSetsNbr;


    public PowerSetServiceTest(int setSize, int subSetsNbr) {
        this.setSize = setSize;
        this.subSetsNbr = subSetsNbr;

    }

    @Before
    public void setUp() {

        set = new HashSet<Integer>(setSize);

    }


    @Parameterized.Parameters
    public static Collection setSizes() {
        return Arrays.asList(new Object[][]{
                // {setSize, subSetsNbr}
                {0, 1},
                {1, 2},
                {2, 4},
                {3, 8},
                {4, 16},
                {5, 32},
                {6, 64},
                {7, 128},
                {8, 256},
                {9, 512}
        });
    }

    @Test
    public void powerSetTest() {
        set.clear();
        for (int elem = 0; elem < setSize; elem++) {
            set.add(elem);
        }

        assertEquals(String.format("The subsets number of set which contains %d elements is %d", setSize, subSetsNbr), subSetsNbr, powerSet(set).size());

    }
}