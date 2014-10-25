package crackinginterview;

import junit.framework.Assert;
import org.junit.Test;

/**
 *
 *
 *
 */
public class NewtonMethod {
    public double eps = 0.00001;
    public double N;

    public double sqrt(double n) {
        N = n;
        double oldX = n/2;
        double newX = n;
        while (Math.abs(oldX - newX) > eps) {
            double tmp = newX;
            newX = newX - f(newX)/df(newX);
            oldX = tmp;
        }
        return newX;
    }

    public double f(double n) { return n*n - N; }

    public double df(double n) { return 2*n; }

    @Test
    public void test() {
        System.out.println("sqrt(2)="+sqrt(2));
        System.out.println("sqrt(25)="+sqrt(25));
        System.out.println("sqrt(1)="+sqrt(1));
        System.out.println("sqrt(4)="+sqrt(4));
        System.out.println("sqrt(100)="+sqrt(100));
        System.out.println("sqrt(0.09)="+sqrt(0.09));
    }
}
