package leetcode;

/**
There are N children standing in a line. Each child is assigned a rating value.

You are giving candies to these children subjected to the following requirements:

- Each child must have at least one candy.
- Children with a higher rating get more candies than their neighbors.

What is the minimum candies you must give?

e.g.
Rating: 1 3 5
Candies:1 2 3


Rating: 1 5 7 1 5
Candies:1 2 3 1 2


---------SOLUTION---------

//two loops
// left -> right loop, give more candies to cur child, if the left child has less rating
// right -> left loop, give more candies to cur child, if the right child has less rating

//in: 	[4,2,3,4,1]
// loop1: 1,1,2,3,1
// loop2: 2,1,2,3,1

 */

import junit.framework.Assert;
import org.junit.Test;

import java.util.Arrays;

public class CandyToChilderen {

    public int candy(int[] ratings) {
        int[] candiesToGive = candyArray(ratings);
        int result = 0;
        for (int i = 0; i < candiesToGive.length; i++) result += candiesToGive[i];
        return result;
    }

    public int[] candyArray(int[] ratings) {

            int[] candiesToGive = new int[ratings.length];

            for (int i = 0; i < candiesToGive.length; i++) candiesToGive[i] = 1;


            for (int i = 1; i < ratings.length; i++) {
                if(ratings[i] > ratings[i-1] ) {
                    candiesToGive[i] = candiesToGive[i-1] + 1;
                }
            }

            for (int i = ratings.length - 2; i >= 0 ; i--) {
                if(ratings[i] > ratings[i+1] && candiesToGive[i] <= candiesToGive[i+1]) {
                    candiesToGive[i] = candiesToGive[i+1] + 1;
                }
            }
            return  candiesToGive;
    }



    @Test
    public void test() {
        int[] ratings;

        ratings = new int[]{2,2,1};
        Assert.assertEquals("[1, 2, 1]", Arrays.toString(candyArray(ratings)));

        ratings = new int[]{1,2,3,4,5, 4, 3};
        Assert.assertEquals("[1, 2, 3, 4, 5, 2, 1]", Arrays.toString(candyArray(ratings)));


        ratings = new int[]{2, 2};
        Assert.assertEquals("[1, 1]", Arrays.toString(candyArray(ratings)));
        Assert.assertEquals(2, candy(ratings));

        ratings = new int[]{1,2,2};
        Assert.assertEquals("[1, 2, 1]", Arrays.toString(candyArray(ratings)));


        ratings = new int[]{1, 2, 3, 4, 3, 2, 1, 1, 1, 2, 3, 4};
        Assert.assertEquals("[1, 2, 3, 4, 3, 2, 1, 1, 1, 2, 3, 4]", Arrays.toString(candyArray(ratings)));

        ratings = new int[]{1, 3, 5};
        Assert.assertEquals("[1, 2, 3]", Arrays.toString(candyArray(ratings)));
        Assert.assertEquals(6, candy(ratings));

        ratings = new int[]{6, 5, 7, 1, 5};
        Assert.assertEquals("[2, 1, 2, 1, 2]", Arrays.toString(candyArray(ratings)));

        ratings = new int[]{5, 3, 1};
        Assert.assertEquals("[3, 2, 1]", Arrays.toString(candyArray(ratings)));

        //20 sec
        ratings = new int[100000];
        for (int i = 0; i < ratings.length; i++) ratings[i] = i+1;
        candy(ratings);
    }
}
