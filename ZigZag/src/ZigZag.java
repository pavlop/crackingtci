import java.util.*;
import java.math.*;
import static java.lang.Math.*;

public class ZigZag {
    int maxLen = 1;

//	public int longestZigZag(int[] sequence) {
//        if(sequence.length <= 1) return sequence.length;
//
//        int previouslyIncluded = 0;
//        int prevPreviouslyIncluded = -1;
//        for (int i = 1 ; i < sequence.length - 1; i++) {
//            if (isSequenceContinued(sequence,  i, previouslyIncluded,  prevPreviouslyIncluded)
//                    && isSequenceContinued(sequence, i+1, i, previouslyIncluded)) {
//                prevPreviouslyIncluded = previouslyIncluded;
//                previouslyIncluded = i;
//                maxLen++;
//            }
//        }
//
//        if(isSequenceContinued(sequence,   sequence.length - 1, previouslyIncluded,  prevPreviouslyIncluded)) maxLen++;
//        return maxLen;
//    }
//    public boolean isSequenceContinued(int[] sequence, int id, int previouslyIncluded, int prevPreviouslyIncluded) {
//        if(prevPreviouslyIncluded < 0 ) {
//            if (sequence[previouslyIncluded] != sequence[id]) return true;
//            else return false;
//        }
//        if (sequence[id] < sequence[previouslyIncluded] && sequence[previouslyIncluded] > sequence[prevPreviouslyIncluded]) return true;
//        if (sequence[id] > sequence[previouslyIncluded] && sequence[previouslyIncluded] < sequence[prevPreviouslyIncluded]) return true;
//        return false;
//    }


    public int longestZigZag(int[] sequence) {
        if(sequence.length <= 1) return sequence.length;
        boolean movedUpPrviously = true;
        maxLen = 1;
        for (int i = 1; i < sequence.length; i++) {
            if (sequence[i - 1] == sequence[i]) continue;

            if (maxLen == 1) {
                movedUpPrviously = sequence[0] < sequence[i];
                maxLen++;
            }

            boolean nextMoveUp = sequence[i - 1] < sequence[i];
            if (nextMoveUp == movedUpPrviously) continue;
            else {
                movedUpPrviously = nextMoveUp;
                maxLen++;
            }
        }

        return maxLen;
    }
}
