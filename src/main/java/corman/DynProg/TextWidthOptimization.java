package corman.DynProg;

import org.junit.Test;

import java.util.*;

/**
 * Given a text and the maxWidth
 * need to split it to lines, so that it looks the best
 */
public class TextWidthOptimization {
    private int maxWidth;
    private List<String> words;
    private Map<Integer, Integer> splitAtNextBest = new HashMap<Integer, Integer>();
    private int bestFirstSplit;

    public List<List<String>> doTextJustification(List<String> words, int maxWidth) {
        this.words = words;
        this.maxWidth = maxWidth;
        getBestBadnessAtSplitPostion(0);
        System.out.println(splitAtNextBest);

        int nextSplitAt = bestFirstSplit;
        int prevSplitAt = 0;
        while (splitAtNextBest.containsKey(nextSplitAt)) {
            List<String> line = words.subList(prevSplitAt, nextSplitAt);
            StringBuffer sline = new StringBuffer("");
            for (String word:line) sline.append(word +" ");
            System.out.println(sline);
            prevSplitAt = nextSplitAt;
            nextSplitAt = splitAtNextBest.get(nextSplitAt);
        }
        return null;
    }
    private double getBestBadnessAtSplitPostion(int startAt) {
        //System.out.println("doTextJustificationRec startAt="+startAt);
        if(splitAtNextBest.containsKey(startAt)) return splitAtNextBest.get(startAt);
        int i = startAt;
        int j = startAt;
        if(j == words.size()) {return 0;}
        double bestBadness = badness(i, j) + getBestBadnessAtSplitPostion(j+1);
        j++;
        int bestJ = j;

        for(; j < words.size(); j++) {
            double curBestBadness = badness(i, j) + getBestBadnessAtSplitPostion(j+1);
            if(curBestBadness < bestBadness) {
                bestJ = j;
                bestBadness = curBestBadness;
            }

        }
        //System.out.println("bestFactor for startAt:"+startAt+" is:"+bestBadness+" best split at:"+bestJ);
        splitAtNextBest.put(i, bestJ);
        if(startAt == 0) bestFirstSplit = bestJ;
        return bestBadness;
    }

    private double badness(int startWordId, int endWordId) {
        int sumLength = sumLength(startWordId, endWordId);
        if(sumLength > maxWidth) return Integer.MAX_VALUE;
        int diff = maxWidth - sumLength;
        return diff*diff;
    }

    int sumLength(int startWordId, int endWordId) {
        int sum = 0;
        for(int i = startWordId; i <= endWordId; i++) {
            sum += words.get(i).length() + 1; //word length plus space
        }
        if(sum > 0) sum --;
        return sum;
    }

    @Test
    public void doTest() {
        TextWidthOptimization tj = new TextWidthOptimization();
        //String s = "Frédéric François Chopin (/ˈʃoʊpæn/; French pronunciation: ​[fʁe.de.ʁik ʃɔ.pɛ̃]; 22 February or 1 March 1810 – 17 October 1849), born Fryderyk Franciszek Chopin,[n 1] was a Polish composer of the Romantic era. A child prodigy, Chopin was born in what was then the Duchy of Warsaw. He grew up in Warsaw, which after 1815 became part of Congress Poland, and there completed his musical education and composed many of his works before leaving Poland, aged 20, less than a month before the outbreak of the November 1830 Uprising.";
        String s = "Frédéric François Chopin (/ˈʃoʊpæn/; French pronunciation: ​[fʁe.de.ʁik ʃɔ.pɛ̃]; 22 February or 1 March 1810 – 17 October 1849), born Fryderyk Franciszek Chopin,[n 1] was a Polish composer of the Romantic era. A child prodigy, Chopin was born in what was then the Duchy of Warsaw. He grew up in Warsaw, which after 1815 became part of Congress Poland, and there completed his musical education and composed many of his works before leaving Poland, aged 20, less than a month before the outbreak of the November 1830 Упресинг. Ат the age of 21 he settled in Paris, obtaining French citizenship in 1835. During the remaining 18 years of his life, he gave only some 30 public performances, preferring the more intimate atmosphere of the salon; he supported himself by selling his compositions and as a sought-after piano teacher, and gained renown as a leading virtuoso of his generation. He formed a friendship with Franz Liszt and was admired by many of his musical contemporaries, including Robert Schumann. After a failed engagement with a Polish girl, from 1837 to 1847 he maintained an often troubled relationship with the French writer George Sand. A brief and unhappy visit with Sand to Majorca in 1838–39 was one of his most productive periods of composition. In his last years, he was financially supported by his admirer Jane Stirling, who also arranged for him to visit Scotland in 1848. Through most of his life, Chopin suffered from poor health; he died in Paris in 1849, probably of tuberculosis. Алл of Chopin's compositions include the piano; most are for solo piano, although he also wrote two piano concertos, a few chamber pieces, and some songs to Polish lyrics. His keyboard style, which is highly individual, is often technically demanding; his own performances were noted for their nuance and sensitivity. Chopin invented the concept of instrumental ballade; his major piano works also include sonatas, mazurkas, waltzes, nocturnes, polonaises, études, impromptus, scherzos, and preludes, some published only after his death. Many contain elements of both Polish folk music and of the classical tradition of J.S. Bach, Mozart and Schubert, whom he particularly admired. His innovations in style, musical form, and harmony, and his association of music with nationalism, were influential throughout and after the late Romantic period. Ботн in his native Poland and beyond, Chopin's music, his status as one of music's earliest 'superstars', his association (if only indirect) with political insurrection, his amours and his early death have made him, in the public consciousness, a leading symbol of the Romantic era. His works remain popular, and he has been the subject of numerous films and biographies of varying degrees of historical accuracy.";
        List text = new ArrayList(Arrays.asList(s.split(" ")));
        tj.doTextJustification(text , 100);
    }


}
