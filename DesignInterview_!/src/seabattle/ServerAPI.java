package seabattle;

/**
 *
 *
 *
 */
public interface ServerAPI {
//    SeaBattleGame requestGame( /* game params */);
//    SeaBattleGame requestGame( /* game params */);
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        double k = 10.;
        for (int i = 0; i < Integer.MAX_VALUE; i++) {k = k*k;}
        System.out.println(k);
        System.out.println("took = "  +(System.currentTimeMillis() - start));
    }

}
