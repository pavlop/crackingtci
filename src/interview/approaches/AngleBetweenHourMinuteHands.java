package interview.approaches;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by pavlop on 2/16/14.
 */
public class AngleBetweenHourMinuteHands {

    public static double perform(int hours, int minutes) throws Exception {
        double minutesAlfa = 360.0 * minutes / 60;
        double hoursAlfa = 360.0 * (hours % 12 + minutesAlfa / 360.) / 12.;
        double angle = Math.abs(hoursAlfa - minutesAlfa);
        if (angle > 180) return 360 - angle;
        return angle;
    }

    @Test
    public void positive1() throws Exception {
        assertEquals(0.0, AngleBetweenHourMinuteHands.perform(12, 00));
        assertEquals(30.0, AngleBetweenHourMinuteHands.perform(13, 00));
        assertEquals(30.0, AngleBetweenHourMinuteHands.perform(1, 00));
        assertEquals(105.0, AngleBetweenHourMinuteHands.perform(2, 30));
        assertEquals(37.5, AngleBetweenHourMinuteHands.perform(4, 15));
        assertEquals(150.0, AngleBetweenHourMinuteHands.perform(7, 00));
        assertEquals(67.5, AngleBetweenHourMinuteHands.perform(17, 15));
    }
    
}



