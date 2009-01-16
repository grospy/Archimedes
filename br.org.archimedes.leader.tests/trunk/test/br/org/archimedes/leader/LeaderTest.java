
package br.org.archimedes.leader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import br.org.archimedes.Tester;
import br.org.archimedes.exceptions.IllegalActionException;
import br.org.archimedes.exceptions.InvalidArgumentException;
import br.org.archimedes.exceptions.NullArgumentException;
import br.org.archimedes.model.Point;

/**
 * Belongs to package com.tarantulus.archimedes.model.elements.
 * 
 * @author jefsilva
 */
public class LeaderTest extends Tester {

    @Test
    public void rotatingALeaderRotatesBothLines ()
            throws InvalidArgumentException {

        Leader leader = createSafeLeader(new Point( -50, 0), new Point(0, 50),
                new Point(50, 0));
        Leader expected;

        try {
            leader.rotate(null, Math.PI / 2);
            fail("Should throw a exception.");
        }
        catch (NullArgumentException e) {

        }

        safeRotate(leader, new Point(0, 0), -Math.PI / 2);
        expected = createSafeLeader(new Point(0, 50), new Point(50, 0),
                new Point(0, -50));
        assertEquals("Leader should be equals.", expected, leader);

        leader = createSafeLeader(new Point( -50, 0), new Point(0, 50),
                new Point(50, 0));
        safeRotate(leader, new Point(50, 0), Math.PI / 2);
        expected = createSafeLeader(new Point(50, -100), new Point(0, -50),
                new Point(50, 0));
        assertEquals("Leader should be equals.", expected, leader);

        leader = createSafeLeader(new Point( -50, 0), new Point(0, 50),
                new Point(50, 0));
        safeRotate(leader, new Point(0, 50), Math.PI);
        expected = createSafeLeader(new Point(50, 100), new Point(0, 50),
                new Point( -50, 100));
        assertEquals("Leader should be equals.", expected, leader);

        leader = createSafeLeader(new Point( -50, 0), new Point(0, 50),
                new Point(50, 0));
        safeRotate(leader, new Point(0, 0), Math.PI / 4);
        expected = createSafeLeader(new Point( -50 * COS_45, -50 * COS_45),
                new Point( -50 * COS_45, 50 * COS_45), new Point(50 * COS_45,
                        50 * COS_45));
        assertEquals("Leader should be equals.", expected, leader);
    }

    /**
     * @param point
     *            First point of the Leader
     * @param point2
     *            Second point of the Leader
     * @param point3
     *            Third point of the Leader
     * @return The created leader
     */
    private Leader createSafeLeader (Point point, Point point2, Point point3) {

        Leader leader = null;
        try {
            leader = new Leader(point, point2, point3);
        }
        catch (Exception e) {
            fail("Should not throw any exception but got " + e);
        }
        return leader;
    }

    @Test
    public void scalingALeaderScalesBothLines () {

        Leader leader = null;
        Leader expected = null;
        Point start = new Point(0, 0);
        Point middle = new Point(1, 1);
        Point end = new Point(5, 1);
        try {
            leader = createSafeLeader(start, middle, end);
            leader.scale(new Point(0, 0), 0.8);
            Point startExepected = new Point(0, 0);
            Point middleExepected = new Point(0.8, 0.8);
            Point endExepected = new Point(4, 0.8);
            expected = createSafeLeader(startExepected, middleExepected,
                    endExepected);
            assertEquals("Leader should be as expected", expected, leader);

            start = new Point(0, 0);
            middle = new Point(1, 1);
            end = new Point(5, 1);
            leader = createSafeLeader(start, middle, end);
            leader.scale(new Point(1, 0), 0.5);
            startExepected = new Point(0.5, 0);
            middleExepected = new Point(1, 0.5);
            endExepected = new Point(3, 0.5);
            expected = createSafeLeader(startExepected, middleExepected,
                    endExepected);
            assertEquals("Leader should be as expected", expected, leader);
        }
        catch (Exception e) {
            e.printStackTrace();
            fail("Should not throw any exception");
        }

        leader = createSafeLeader(start, middle, end);
        try {
            leader.scale(new Point(0, 0), -0.5);
            fail("Should throw IllegalActionException");
        }
        catch (NullArgumentException e) {
            fail("Should not throw NullArgumentException");
        }
        catch (IllegalActionException e) {
            // It's OK
        }

        try {
            leader.scale(null, 0.5);
            fail("Should throw NullArgumentException");
        }
        catch (NullArgumentException e) {
            // It's OK
        }
        catch (IllegalActionException e) {
            fail("Should not throw IllegalActionException");
        }
    }
}