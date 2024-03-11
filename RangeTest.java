package org.jfree.data;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RangeTest {
    private Range exampleRange;
    private Range myRange;
    private Range positiveShiftedRange;
    private Range negativeShiftedRange;
    private Range noShiftRange;
    private Range largeShiftedRange;
    private Range incorrectRange;
    private Range noLengthRange;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
        exampleRange = new Range(-1, 1); //Create the range from -1 to 1
        myRange = new Range(-1, 1);
        positiveShiftedRange = Range.shift(myRange, 2);
        negativeShiftedRange = Range.shift(myRange, -2);
        noShiftRange = Range.shift(myRange, 0);
        largeShiftedRange = Range.shift(myRange, 1000000);
        noLengthRange = new Range(1,1);
    }

    @Test
    public void testAboveConstrain() { //Any value above 1 should be constrained to 1
        assertEquals("A value above the range should be constrained to 1", 1, exampleRange.constrain(2), 0.000000001d);
    }
    
    @Test
    public void testBelowConstrain() { //Any value below -1 should be constrained to -1
        assertEquals("A value below the range should be constrained to -1", -1, exampleRange.constrain(-2), 0.000000001d);
    }
    
    @Test
    public void testInsideConstrain() { //Checking nominal value inside the boundary range
        assertEquals("A value inside the range should give itself", 0, exampleRange.constrain(0), 0.000000001d);
    }
    
    @Test
	public void testLowerBoundNegative() { //Checking negative value
		exampleRange = new Range(-10, 10);
		assertEquals("Should return lowerbound of range", -10 ,exampleRange.getLowerBound(),0.000000001d);
	}
    
    @Test(expected = IllegalArgumentException.class)
    public void testIncorrectRange() {
    	incorrectRange = new Range(1, -1);
    }
	
	@Test
	public void testLowerBoundZero() { //Checking zero value
		exampleRange = new Range(0, 10);
		assertEquals("Should return lowerbound of range", 0, exampleRange.getLowerBound(),0.000000001d);
	}
	
	@Test
	public void testLowerBoundPositive() { //Checking positive value
		exampleRange = new Range(1,10);
		assertEquals("Should return lowerbound of range", 1, exampleRange.getLowerBound(),0.000000001d);
	}
	
	@Test
	public void testEqualUpperAndLowerBound() { //Checking if upper and lower bounds are equal
		exampleRange = new Range(2,2);
		assertEquals("Should return lowerbound of range", 2, exampleRange.getLowerBound(),0.000000001d);
	}
	
	@Test
	public void testUpperBoundNegative() { //Checking negative value
		exampleRange = new Range(-20, -10);
		assertEquals("Should return upperbound of range", -10 ,exampleRange.getUpperBound(),0.000000001d);
	}
	
	@Test
	public void testUpperBoundZero() { //Checking zero value
		exampleRange = new Range(-10, 0);
		assertEquals("Should return upperbound of range", 0, exampleRange.getUpperBound(),0.000000001d);
	}
	
	@Test
	public void testUpperBoundPositive() { //Checking positive value
		exampleRange = new Range(1, 10);
		assertEquals("Should return upperbound of range", 10, exampleRange.getUpperBound(),0.000000001d);
	}
	
	@Test
	public void testEqualLowerAndUpperBound() { //Checking if upper and lower bounds are equal
		exampleRange = new Range(2, 2);
		assertEquals("Should return upperbound of range", 2, exampleRange.getUpperBound(),0.000000001d);
	}
	
	@Test 
    public void testValidRange() {
    	exampleRange = new Range(0, 10);
    	assertEquals("Should give a valid range ", 10, exampleRange.getLength(),0.000000001d);
    }
    @Test 
    public void testFloatingPointRange() {
    	exampleRange = new Range(2.5, 7.5);
    	assertEquals("Range test given between decimals", 5, exampleRange.getLength(),0.000000001d);
    }
    @Test 
    public void testZeroLengthRange() {
    	exampleRange = new Range(5, 5);
    	assertEquals("Shuld return a range of zero", 0, exampleRange.getLength(),0.000000001d);
    }
    
    @Test 
    public void testNegativeStartRange() {
    	exampleRange = new Range(-2, 0);
    	assertEquals("Given a negative calue should retrun a valid range", 2, exampleRange.getLength(),0.000000001d);
    }
    
    @Test 
    public void testLargeValueRange() {
    	exampleRange = new Range(0, 10000000);
    	assertEquals("Should return a value given a very rlarge range", 10000000, exampleRange.getLength(),0.000000001d);
    	
    }
    @Test 
    public void testBoundaryRange() {
    	exampleRange = new Range(0, 1);
    	assertEquals("Shoudl return the Smallest possible valid range", 1, exampleRange.getLength(),0.000000001d);
    	
    }
    @Test 
    public void testRandomRange() {
    	exampleRange = new Range(3, 18);
    	assertEquals("Should return the correct range of a random input of values", 15, exampleRange.getLength(),0.000000001d);
    	
    }
    
    @Test
    public void testPositiveShiftLowerBound() {
        assertEquals("Positive shift lower bound should be 1", 1, positiveShiftedRange.getLowerBound(), .000000001d);
    }

    @Test
    public void testPositiveShiftUpperBound() {
        assertEquals("Positive shift upper bound should be 3", 3, positiveShiftedRange.getUpperBound(), .000000001d);
    }

    @Test
    public void testNegativeShiftLowerBound() {
        assertEquals("Negative shift lower bound should be -3", -3, negativeShiftedRange.getLowerBound(), .000000001d);
    }

    @Test
    public void testNegativeShiftUpperBound() {
        assertEquals("Negative shift upper bound should be -1", -1, negativeShiftedRange.getUpperBound(), .000000001d);
    }

    @Test
    public void testNoShiftLowerBound() {
        assertEquals("No shift lower bound should remain unchanged", -1, noShiftRange.getLowerBound(), .000000001d);
    }

    @Test
    public void testNoShiftUpperBound() {
        assertEquals("No shift upper bound should remain unchanged", 1, noShiftRange.getUpperBound(), .000000001d);
    }
    
    @Test
    public void testShiftWithLargeDeltaLowerBound() {
        assertEquals("Lower bound after large shift should be correct",
                999999, largeShiftedRange.getLowerBound(), .000000001d);
    }
    
    @Test
    public void testShiftWithLargeDeltaUpperBound() {
        assertEquals("Upper bound after large shift should be correct",
                1000001, largeShiftedRange.getUpperBound(), .000000001d);
    }
    
    @Test
    public void testShiftedLength() {
        double shiftedSize = positiveShiftedRange.getLength();
        assertEquals("Shifted range size should remain constant",
                2.0, shiftedSize, .000000001d);
    }
   
    @Test
    public void testPositiveShiftedCentralValue() {
    	double shiftedCentralValue = positiveShiftedRange.getCentralValue();
        assertEquals("Shifted central value should be 2",
                2.0, shiftedCentralValue, .000000001d);
    }
    
    @Test
    public void testPositiveShiftedIntersect() {
        assertEquals("Shifted Range shouold intersect.",
                true, positiveShiftedRange.intersects(1, 3));
    }  
    
    @Test
    public void testPositiveShiftedNoIntersect() {
        assertEquals("Shifted Range shouold intersect.",
                false, exampleRange.intersects(-2, 6));
    }  
    
    @Test
    public void testRepeatedShifts() {
        Range initialRange = new Range(-100, 100);
        Range shiftedRight = Range.shift(initialRange, 200, true);
        Range shiftedLeftBack = Range.shift(shiftedRight, -200, true);
        assertEquals("Shifting right then back should return to original lower bound",
                     initialRange.getLowerBound(), shiftedLeftBack.getLowerBound(), 0.000000001d);
    }
    @Test
    public void testNoZeroCrossingWithRangeZero() {
    	//Grabs line 416 in the range class
    	Range zeroCrossRange = new Range(0, 0);
    	Range shiftedRight = Range.shift(zeroCrossRange, 0);
    	assertEquals("Should return a range of five", 0, shiftedRight.getLength(),0.000000001d);
    	
    }
    
    @Test
    public void testIntersect() {
        assertEquals("Range should intersect.",
                false, exampleRange.intersects(-2, -5));
    } 
    
    @Test
    public void testIntersectB() {
        assertEquals("Range should intersect.",
                true, exampleRange.intersects(0, 6));
    } 
    
    @Test
    public void testIntersectC() {
        assertEquals("Range should intersect.",
                false, exampleRange.intersects(4, 6));
    } 
    
    @Test
    public void testIntersectD() {
        assertEquals("Range should intersect.",
                false, exampleRange.intersects(0, -5));
    } 
    
    @Test
    public void testIntersectE() {
        assertEquals("Range should intersect.",
                true, exampleRange.intersects(largeShiftedRange));
    } 
    
    @Test
    public void testCombine() {
    	Range combineRange = null;
        assertEquals("Null range1 should return range2.",
                exampleRange, Range.combine(combineRange, exampleRange));
    }  
    
    @Test
    public void testCombineB() {
    	Range combineRange = null;
        assertEquals("Null range1 should return range2.",
                exampleRange, Range.combine(exampleRange, combineRange));
    } 
    
    @Test
    public void testCombineC() {
    	Range combineRange = new Range (-5, 0);
    	Range resultRange = new Range(-5, 1);
        assertEquals("Combine Ranges.",
                resultRange, Range.combine(exampleRange, combineRange));
    } 
    
    @Test
    public void testCombineIgnoringNaN() {
    	Range combineRange = null;
        assertEquals("Combine Ranges.",
                exampleRange, Range.combineIgnoringNaN(exampleRange, combineRange));
    } 
    
    @Test
    public void testCombineIgnoringNaNB() {
    	Range combineRange = null;
        assertEquals("Combine Ranges.",
                exampleRange, Range.combineIgnoringNaN(combineRange, exampleRange));
    } 
    
    @Test
    public void testCombineIgnoringNaNC() {
    	Range combineRange = null;
        assertEquals("Combine Ranges.",
                null, Range.combineIgnoringNaN(combineRange, combineRange));
    } 
    
    @Test
    public void testCombineIgnoringNaND() {
    	Range combineRange = null;
    	Range combineRangeB = new Range('A', 'B');
    	Range outputRange = new Range (65, 66);
        assertEquals("Combine Ranges.",
                outputRange, Range.combineIgnoringNaN(combineRange, combineRangeB));
    } 
    
    @Test
    public void testCombineIgnoringNaNE() {
    	Range combineRange = new Range (-5, 0);
    	Range outputRange = new Range (-5, 1);
        assertEquals("Combine Ranges.",
                outputRange, Range.combineIgnoringNaN(combineRange, exampleRange));
    }
    
    @Test
    public void testCombineIgnoringNaNF() {
    	Range combineRange = new Range (7, 'Z');
    	Range outputRange = new Range (-1, 90);
        assertEquals("Combine Ranges.",
                outputRange, Range.combineIgnoringNaN(combineRange, exampleRange));
    }
    
    @Test
    public void testExpandToInclude() {
    	Range resultRange = new Range(-5,1);
        assertEquals("Expand Ranges.",
                resultRange, Range.expandToInclude(exampleRange, -5));
    }
    
    @Test
    public void testExpandToIncludeB() {
    	Range resultRange = new Range(-1, 5);
        assertEquals("Expand Ranges.",
                resultRange, Range.expandToInclude(exampleRange, 5));
    }
    
    @Test
    public void testExpandToIncludeC() {
    	Range resultRange = new Range(-1, 1);
        assertEquals("Expand Ranges.",
                resultRange, Range.expandToInclude(exampleRange, 0));
    }
    
    @Test
    public void testExpandToIncludeD() {
    	Range resultRange = null;
    	Range outputRange = new Range (5, 5);
        assertEquals("Expand Ranges.",
                outputRange, Range.expandToInclude(resultRange, 5));
    }
    
    @Test
    public void testExpand() {
    	Range resultRange = new Range (-11, 11);
        assertEquals("Expand Ranges.",
                resultRange, Range.expand(exampleRange, 5, 5));
    }
    
    @Test
    public void testScale() {
    	Range resultRange = new Range (-5, 5);
        assertEquals("Expand Ranges.",
                resultRange, Range.scale(exampleRange, 5));
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testScaleB() {
    	Range resultRange = new Range (-5, 5);
        assertEquals("Expand Ranges.",
                resultRange, Range.scale(exampleRange, -5));
    }
    
    @Test
    public void testHashCode() {
        assertEquals("Expand Ranges.",
                -31457280, exampleRange.hashCode());
    }
    
    @Test
    public void testToString() {
        assertEquals("Check toString.",
                "Range[-1,1]", exampleRange.toString());
    }
    @Test
    public void testEqualsOfDifObjects() {
    	Range objRange = new Range(-5, 5);
    	Double d1 = 3.0;
    	d1.equals(objRange);
    	objRange.equals(d1);

    	assertEquals("Check to see if range object is the same Double object should be false", false, objRange.equals(d1));    	
    }
    @Test
    public void testEqualsOfSameObjects() {
    	Range objRange = new Range(-5, 5);
    	Range objRange2 = new Range(-4, 5);
    	objRange.equals(objRange2);
   
    	assertEquals("Check to see if two lower bound values are the same should be false", false, objRange.equals(objRange2));
    	
    }
    @Test
    public void testEqualsOfSameObjectsLowerMatching() {
    	Range objRange = new Range(-4, 5);
    	Range objRange2 = new Range(-4, 4);
    	objRange.equals(objRange2);
    	
    	assertEquals("Check to see if two lower bound values are the same should be false", false, objRange.equals(objRange2));
    }
 

    @Test
    public void testCombineIgnoringNaNFirstNaNRange() {
        Range range1 = new Range(Double.NaN, Double.NaN);
        Range range2 = new Range(1, 2);
        assertEquals("Combining a NaN range with a non-NaN range should return the non-NaN range.",
                range2, Range.combineIgnoringNaN(range1, range2));
    }

    @Test
    public void testCombineIgnoringNaNSecondNaNRange() {
        Range range1 = new Range(1, 2);
        Range range2 = new Range(Double.NaN, Double.NaN);
        assertEquals("Combining a non-NaN range with a NaN range should return the non-NaN range.",
                range1, Range.combineIgnoringNaN(range1, range2));
    }
    
    @Test
    public void testCombineIgnoringNaNBoth() {
    	Range range1 = new Range(Double.NaN, Double.NaN);
        Range range2 = new Range(Double.NaN, Double.NaN);
        assertEquals("Combining a NaN range with a NaN range should return the NaN range.",
                range1, Range.combineIgnoringNaN(range1, range2));
    }
    
    // Carissa
    // Add tests for getLength() to improve coverage
    @Test
    public void testGetLengthDifferentBounds() {
        Range range = new Range(-5, 10);
        assertEquals("Length should be 15", 15, range.getLength(), 0.000000001d);
    }

    @Test
    public void testGetLengthZeroLengthRange() {
        Range range = new Range(7, 7);
        assertEquals("Length should be 0 for zero-length range", 0, range.getLength(), 0.000000001d);
    }

    // Add tests for getLowerBound() and getUpperBound() to improve coverage
    @Test
    public void testGetLowerBoundDifferentRange() {
        Range range = new Range(-3, 7);
        assertEquals("Lower bound should be -3", -3, range.getLowerBound(), 0.000000001d);
    }

    @Test
    public void testGetUpperBoundDifferentRange() {
        Range range = new Range(-2, 8);
        assertEquals("Upper bound should be 8", 8, range.getUpperBound(), 0.000000001d);
    }

    // Add tests for expand(Range, double, double) to improve coverage
    @Test
    public void testExpandWithNegativeValues() {
        Range baseRange = new Range(-2, 2);
        Range expandedRange = Range.expand(baseRange, -1, -1);
        assertEquals("Expanded lower bound should be -3", -3, expandedRange.getLowerBound(), 0.000000001d);
        assertEquals("Expanded upper bound should be 3", 3, expandedRange.getUpperBound(), 0.000000001d);
    }

    // Add tests for constrain(double) to improve coverage
    @Test
    public void testConstrainValueWithinRange() {
        Range range = new Range(0, 10);
        assertEquals("Constrained value should be 5", 5, range.constrain(5), 0.000000001d);
    }

    @Test
    public void testConstrainValueAboveRange() {
        Range range = new Range(0, 10);
        assertEquals("Constrained value should be the upper bound (10)", 10, range.constrain(15), 0.000000001d);
    }

    @Test
    public void testConstrainValueBelowRange() {
        Range range = new Range(0, 10);
        assertEquals("Constrained value should be the lower bound (0)", 0, range.constrain(-5), 0.000000001d);
    }
    
    // Carissa's Tests (rough drafts)
    @Test
    public void testGetLengthPositiveRange() {
        Range positiveRange = new Range(2, 5);
        assertEquals("Length of positive range should be 3", 3, positiveRange.getLength(), 0.000000001d);
    }

    @Test
    public void testGetLengthNegativeRange() {
        Range negativeRange = new Range(-5, -2);
        assertEquals("Length of negative range should be 3", 3, negativeRange.getLength(), 0.000000001d);
    }

    @Test
    public void testGetLengthZeroLengthRange2() {
        Range zeroLengthRange = new Range(1, 1);
        assertEquals("Length of zero-length range should be 0", 0, zeroLengthRange.getLength(), 0.000000001d);
    }

    @Test
    public void testGetLengthValidMixedBounds() {
        Range mixedBoundsRange = new Range(-2, 2);
        assertEquals("Length of valid mixed bounds range should be 4", 4, mixedBoundsRange.getLength(), 0.000000001d);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetLengthInvalidRangeReversedBounds() {
        Range invalidRange = new Range(5, 2);
        invalidRange.getLength(); // This should throw an IllegalArgumentException
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetLengthInvalidRangeEqualBounds() {
        Range invalidRange = new Range(3, 3);
        invalidRange.getLength(); // This should throw an IllegalArgumentException
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetLengthInvalidRangeEqualBoundsNegative() {
        Range invalidRange = new Range(-3, -3);
        invalidRange.getLength(); // This should throw an IllegalArgumentException
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetLengthInvalidRangeEqualBoundsZero() {
        Range invalidRange = new Range(0, 0);
        invalidRange.getLength(); // This should throw an IllegalArgumentException
    }

    @Test
    public void testCombineIgnoringNaNWithRange2NaN() {
        // Arrange
        Range range1 = null;
        Range range2 = new Range(Double.NaN, Double.NaN);

        // Act
        Range result = Range.combineIgnoringNaN(range1, range2);

        // Assert
        assertNull(result);
    }
    
    @After
    public void tearDown() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }
}

