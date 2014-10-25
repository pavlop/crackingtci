package crackinginterview;

import junit.framework.Assert;
import org.junit.Test;

/**
 *
 */
public class ValidNumber {

    public boolean isValidNumber(String s) {
        State curState = State.START;
        int i = 0;
        while (curState != State.INVALID && i < s.length()) {
            curState = curState.nextState(s.charAt(i));
            i++;
        }
        return curState.isValidEnd();
    }

    @Test
    public void test() {
        Assert.assertTrue(isValidNumber("1"));
        Assert.assertTrue(isValidNumber("+1"));
        Assert.assertTrue(isValidNumber("+1."));
        Assert.assertTrue(isValidNumber("+.08"));
        Assert.assertTrue(isValidNumber("-001."));
        Assert.assertTrue(isValidNumber("-001.22"));
        Assert.assertTrue(isValidNumber("-001.22e123"));
        Assert.assertTrue(isValidNumber("-001.e1"));

        Assert.assertFalse(isValidNumber("+."));
        Assert.assertFalse(isValidNumber("+1.24e"));
        Assert.assertFalse(isValidNumber(".24e"));
        Assert.assertFalse(isValidNumber(".e12"));
    }

}

enum State {

    SIGN {
        State nextState(char c) {
            if (c <= '9' && c >= '0') return INTEGER;
            if (c == '.') return LEADING_DOT;
            else return INVALID;
        }
    },

    INVALID {
        State nextState(char c) { return null; }
    },

    INTEGER {
        State nextState(char c) {
            if (c <= '9' && c >= '0')  return INTEGER;
            if (c == '.') return DELIMITER_DOT;
            else return INVALID;
        }
        boolean isValidEnd() { return true; }
    },

    DECIMAL {
        State nextState(char c) {
            if (c <= '9' && c >= '0') return DECIMAL;
            if (c == 'e') return EXPONENT_SIGN;
            else return INVALID;
        }
        boolean isValidEnd() { return true; }
    },

    EXPONENT_SIGN {
        State nextState(char c) {
            if (c <= '9' && c >= '0')  return EXPONENT_NUMBER;
            else return INVALID;
        }
        boolean isValidEnd() { return false; }
    },


    EXPONENT_NUMBER {
        State nextState(char c) {
            if (c <= '9' && c >= '0') return EXPONENT_NUMBER;
            else return INVALID;
        }
        boolean isValidEnd() { return true; }
    },

    LEADING_DOT {
        State nextState(char c) {
            if (c <= '9' && c >= '0')  return DECIMAL;
            else return INVALID;
        }
    },

    DELIMITER_DOT {
        State nextState(char c) {
            if (c <= '9' && c >= '0')  return DECIMAL;
            if (c == 'e')  return EXPONENT_SIGN;
            else return INVALID;
        }
        boolean isValidEnd() { return true; }
    },

    START {
        State nextState(char c) {
            if (c == '+' || c == '-') return SIGN;
            if (c <= '9' && c >= '0')  return INTEGER;
            if (c == '.') return LEADING_DOT;
            return INVALID;
        }
    };

    abstract State nextState(char c);
    boolean isValidEnd() {
        return false;
    }
}