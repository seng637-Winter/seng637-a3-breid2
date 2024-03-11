package org.jfree.data;

import static org.junit.Assert.*;
import org.junit.*;
import org.jmock.*;

public class DataUtilitiesTest {
    private Mockery mockingContext;
    private Values2D values;
    private KeyedValues kv;
    
    @Before
    public void setUp() throws Exception {
        this.mockingContext = new Mockery();
        this.values = this.mockingContext.mock(Values2D.class);
        this.kv = mockingContext.mock(KeyedValues.class);
    }
    
	/********************************************************************
	 * ******************************************************************
	 * * Test Suite: calculateColumnTotal(Values2D, column) 			*
	 * * Tester: Ben, Chris                                             *
	 * ******************************************************************
	 ********************************************************************/
	
	@Test(expected = IllegalArgumentException.class)
	public void calculateColumnTotalNullData() {
		final int column = 0;
		DataUtilities.calculateColumnTotal(null, column);
	}
	
	 @Test //to determine if the method can sum up a column of 2 values
	    public void calculateColumnTotalForTwoValues() {
	        // setup Mocking
	        Mockery mockingContext = new Mockery();
	        final Values2D values = mockingContext.mock(Values2D.class);
	
	        mockingContext.checking(new Expectations() {{
	            oneOf(values).getRowCount();
	            will(returnValue(2)); //Initializing 2 rows
	            oneOf(values).getValue(0, 0);
	            will(returnValue(7.5)); //Setting first value to 7.5
	            oneOf(values).getValue(1, 0);
	            will(returnValue(2.5)); //Setting second value to 2.5
	        }});
	
	        // exercise
	        double result = DataUtilities.calculateColumnTotal(values, 0);
	
	        // verify
	        assertEquals(10.0, result, .000000001d);
	
	}
	
	 
	 
	 
	 @Test //to determine if the method can sum up negative values
	    public void calculateColumnTotalForNegativeValues() {
	        // setup Mocking
	        Mockery mockingContext = new Mockery();
	        final Values2D values = mockingContext.mock(Values2D.class);
	
	        mockingContext.checking(new Expectations() {{
	            oneOf(values).getRowCount();
	            will(returnValue(2)); //Initialize 2 rows
	            oneOf(values).getValue(0, 0);
	            will(returnValue(-5.0)); //Setting first value to -5.0
	            oneOf(values).getValue(1, 0);
	            will(returnValue(-2.2)); //Setting second value to -2.2
	        }});
	
	        // exercise
	        double result = DataUtilities.calculateColumnTotal(values, 0);
	
	        // verify
	        assertEquals(-7.2, result, .000000001d);
	
	}
	
	@Test
	public void calculateColumnTotal() {
		final int column = 1;
		
	    mockingContext.checking(new Expectations() {{
				// Setup 3 rows
				oneOf(values).getRowCount(); will(returnValue(3));
				// Setup 3 columns
				oneOf(values).getColumnCount(); will(returnValue(3));
				
				// Mocking the behavior for 3 rows and a specific column
	        oneOf(values).getValue(0, column); will(returnValue(2.5));
	        oneOf(values).getValue(1, column); will(returnValue(3.0));
	        oneOf(values).getValue(2, column); will(returnValue(4.5));
	     }});
	    
	    Double result = DataUtilities.calculateColumnTotal(values, column);
	    assertEquals("The column total should be 0.0", 10.0, result, 0.00001d);
	}
	
	
	@Test
	public void calculateColumnTotalWithNullData() {
		final int column = 1;
		
	    mockingContext.checking(new Expectations() {{
				// Setup 3 rows
				oneOf(values).getRowCount(); will(returnValue(3));
				// Setup 3 columns
				oneOf(values).getColumnCount(); will(returnValue(3));
				
				// Mocking the behavior for 3 rows and a specific column
	        oneOf(values).getValue(0, column); will(returnValue(2.5));
	        oneOf(values).getValue(1, column); will(returnValue(null));
	        oneOf(values).getValue(2, column); will(returnValue(4.5));
	     }});
	    
	    Double result = DataUtilities.calculateColumnTotal(values, column);
	    assertEquals("The column total should be 0.0", 7.0, result, 0.00001d);
	}
    
    /********************************************************************
     * ******************************************************************
     * * Test Suite: calculateColumnTotal(Values2D, column , validRows) *
     * * Tester: Chris                                                  *
     * ******************************************************************
     ********************************************************************/
	@Test(expected = IllegalArgumentException.class)
    public void calculateColumnTotalWithRowsNullData() {
		final int[] validRows = {0, 1, 2};
		final int column = 1;
		DataUtilities.calculateColumnTotal(null, 0, validRows);
    }
	
	@Test(expected = NullPointerException.class)
	public void calculateColumnTotalWithRowsEmpty() {
    	final int column = 1;
    	final int[] validRows = {};
    	
        mockingContext.checking(new Expectations() {{
 			// Setup 3 rows
 			oneOf(values).getRowCount(); will(returnValue(3));
 			// Setup 3 columns
 			oneOf(values).getColumnCount(); will(returnValue(3));
 			
 			// Mocking the behavior for 3 rows and a specific column
            oneOf(values).getValue(0, column); will(returnValue(2.5));
            oneOf(values).getValue(1, column); will(returnValue(3.0));
            oneOf(values).getValue(2, column); will(returnValue(4.5));
         }});
        Double result = DataUtilities.calculateColumnTotal(values, column, validRows);
        assertEquals("The column total should be 0.0", 0.0, result, 0.00001d);
	}
	
	@Test
	public void calculateColumnTotalWithNumOfRowsGreaterThanColumn() {
    	final int column = 1;
    	final int[] validRows = {0, 1, 2, 3};
    	
        mockingContext.checking(new Expectations() {{
 			// Setup 3 rows
 			oneOf(values).getRowCount(); will(returnValue(3));
 			
 			// Setup 3 columns
 			oneOf(values).getColumnCount(); will(returnValue(3));
             
 			// Mocking the behavior for 3 rows and a specific column
             oneOf(values).getValue(0, column); will(returnValue(2.5));
             oneOf(values).getValue(1, column); will(returnValue(3.0));
             oneOf(values).getValue(2, column); will(returnValue(4.5));
         }});
        double result = DataUtilities.calculateColumnTotal(values, column, validRows);
        assertEquals("The column total should be 10.0", 10.0, result, 0.00001d);
	}

	@Test
	public void calculateTotalWithNumRowsWithNull() {
    	final int column = 1;
    	final int[] validRows = {0, 1, 2};
    	
        mockingContext.checking(new Expectations() {{
 			// Setup 3 rows
 			oneOf(values).getRowCount(); will(returnValue(3));
 			
 			// Setup 3 columns
 			oneOf(values).getColumnCount(); will(returnValue(3));
             
 			// Mocking the behavior for 3 rows and a specific column
             oneOf(values).getValue(0, column); will(returnValue(2.5));
             oneOf(values).getValue(1, column); will(returnValue(null));
             oneOf(values).getValue(2, column); will(returnValue(4.5));
         }});
        double result = DataUtilities.calculateColumnTotal(values, column, validRows);
        assertEquals("The column total should be 7.0", 7.0, result, 0.00001d);
	}
	
    @Test
    public void calculateColumnTotalWithRowsValidInputs() {
    	final int column = 1;
    	final int[] validRows = {0, 1, 2};
    	
        mockingContext.checking(new Expectations() {{
			// Setup 3 rows
			oneOf(values).getRowCount(); will(returnValue(3));
			
			// Setup 3 columns
			oneOf(values).getColumnCount(); will(returnValue(3));
            
			// Mocking the behavior for 3 rows and a specific column
            oneOf(values).getValue(0, column); will(returnValue(2.5));
            oneOf(values).getValue(1, column); will(returnValue(3.0));
            oneOf(values).getValue(2, column); will(returnValue(4.5));
        }});
        double result = DataUtilities.calculateColumnTotal(values, column, validRows);
        assertEquals("The column total should be 10.0", 10.0, result, 0.00001d);
    }
    /********************************************************************
     * ******************************************************************
     * * Test Suite: calculateRowTotal(Values2D data, int row) *
     * * Tester: Braden                                                  *
     * ******************************************************************
     ********************************************************************/
	@Test 
	public void calcRowTotalWithValidInputs(){
		final int row = 0;
    	final int[] validRows = {0, 1, 2};
        mockingContext.checking(new Expectations() {{
			// Setup 3 rows
			oneOf(values).getRowCount(); will(returnValue(1));
			
			// Setup 3 columns
			oneOf(values).getColumnCount(); will(returnValue(3));
            
			// Mocking the behavior for 3 rows and a specific column
            oneOf(values).getValue(row, 0); will(returnValue(2.5));
            oneOf(values).getValue(row, 1); will(returnValue(3.0));
            oneOf(values).getValue(row, 2); will(returnValue(4.5));
        }});
        double result = DataUtilities.calculateRowTotal(values, row);
        assertEquals("The column total should be 10.0", 10.0, result, 0.00001d);
	}
	
	 /********************************************************************
     * ******************************************************************
     * * Test Suite: calculateRowTotal(Values2D data, int row) *
     * * Tester: Braden                                                  *
     * ******************************************************************
     ********************************************************************/
	@Test 
	public void calcRowTotalWithNullInputs(){
		final int row = 0;
    	final int[] validRows = {0, 1, 2};
        mockingContext.checking(new Expectations() {{
			// Setup 3 rows
			oneOf(values).getRowCount(); will(returnValue(1));
			
			// Setup 3 columns
			oneOf(values).getColumnCount(); will(returnValue(3));
            
			// Mocking the behavior for 3 rows and a specific column
            oneOf(values).getValue(row, 0); will(returnValue(null));
            oneOf(values).getValue(row, 1); will(returnValue(null));
            oneOf(values).getValue(row, 2); will(returnValue(null));
        }});
        double result = DataUtilities.calculateRowTotal(values, row);
        assertEquals("The column total should be 0", 0, result, 0.00001d);
	}
	
	 /********************************************************************
     * ******************************************************************
     * * Test Suite: calculateRowTotal(Values2D data, int row,
             int[] validCols) *
     * * Tester: Braden                                                  *
     * ******************************************************************
     ********************************************************************/
	@Test 
	public void calcRowTotalWithValidInputsSpecificCols(){
		final int row = 0;
    	final int[] validRows = {0, 1, 2};
        mockingContext.checking(new Expectations() {{
			// Setup 3 rows
			oneOf(values).getRowCount(); will(returnValue(1));
			
			// Setup 3 columns
			oneOf(values).getColumnCount(); will(returnValue(3));
            
			// Mocking the behavior for 3 rows and a specific column
            oneOf(values).getValue(row, 0); will(returnValue(2.5));
            oneOf(values).getValue(row, 1); will(returnValue(3.0));
            oneOf(values).getValue(row, 2); will(returnValue(4.5));
        }});
        double result = DataUtilities.calculateRowTotal(values, row, validRows);
        assertEquals("The column total should be 10", 10, result, 0.00001d);
	}
    /********************************************************************
     * ******************************************************************
     * * Test Suite: createNumberArray(double[] data) *
     * * Tester: Braden                                                  *
     * ******************************************************************
     ********************************************************************/
    @Test
    public void createNumberArray() {
    	double[] inputData = {1.0, 2.0, 3.0, 4.0};
        Number[] expected = {1.0, 2.0, 3.0, 4.0};
        Number[] actual = DataUtilities.createNumberArray(inputData);
        assertArrayEquals("The Number array should match the input double array", expected, actual);
    }
    /********************************************************************
     * ******************************************************************
     * * Test Suite: createNumberArray2D(double[][] data) *
     * * Tester: Braden                                                  *
     * ******************************************************************
     ********************************************************************/
    @Test
    public void createNumberArray2D() {
    	double[][] input = {{1.0, 2.0, 3.0}, {4.0,5.0,6.0}};
		Number[][] expected = {{1.0, 2.0, 3.0}, {4.0,5.0,6.0}};
		Number[][] result = DataUtilities.createNumberArray2D(input);
		assertArrayEquals("The 2D Number array should match the input 2D array", expected, result);

    }
    /********************************************************************
     * ******************************************************************
     * * Test Suite: equal(double[][] a, double[][] b) *
     * * Tester: Braden                                                  *
     * ******************************************************************
     ********************************************************************/
    @Test
	public void equalValidArrays(){
    	double[][] arr1 = {{1.0, 2.0, 3.0}, {4.0,5.0,6.0}};
    	double[][] arr2 = {{1.0, 2.0, 3.0}, {4.0,5.0,6.0}};
    	assertEquals("The two arrays should be equal", true, DataUtilities.equal(arr1, arr2));
	}
    /********************************************************************
     * ******************************************************************
     * * Test Suite: equal(double[][] a, double[][] b) *
     * * Tester: Braden                                                  *
     * ******************************************************************
     ********************************************************************/
    @Test
	public void equalANullValidArrays(){
    	double[][] arr1 = null;
    	double[][] arr2 = {{1.0, 2.0, 3.0}, {4.0,5.0,6.0}};
    	assertEquals("The two arrays should not be equal ", false, DataUtilities.equal(arr1, arr2));
	}
    /********************************************************************
     * ******************************************************************
     * * Test Suite: equal(double[][] a, double[][] b) *
     * * Tester: Braden                                                  *
     * ******************************************************************
     ********************************************************************/
    @Test
	public void equalBNullValidArrays(){
    	double[][] arr1 = {{1.0, 2.0, 3.0}, {4.0,5.0,6.0}};
    	double[][] arr2 = null;
    	assertEquals("The two arrays should not be equal ", false, DataUtilities.equal(arr1, arr2));
	}
    /********************************************************************
     * ******************************************************************
     * * Test Suite: equal(double[][] a, double[][] b) *
     * * Tester: Braden                                                  *
     * ******************************************************************
     ********************************************************************/
    @Test
	public void equalBothNullValidArrays(){
    	double[][] arr1 = null;
    	double[][] arr2 = null;
    	assertEquals("The two arrays should not be equal ", true, DataUtilities.equal(arr1, arr2));
	}
    /********************************************************************
     * ******************************************************************
     * * Test Suite: equal(double[][] a, double[][] b) *
     * * Tester: Braden                                                  *
     * ******************************************************************
     ********************************************************************/
    @Test
	public void equalSizeDifRowArrays(){
    	double[][] arr1 = {{1.0, 2.0}};
    	double[][] arr2 = {{1.0, 2.0}, {4.0,5.0,6.0}};
    	assertEquals("The two arrays should be equal", false, DataUtilities.equal(arr1, arr2));
	}
    /********************************************************************
     * ******************************************************************
     * * Test Suite: equal(double[][] a, double[][] b) *
     * * Tester: Braden                                                  *
     * ******************************************************************
     ********************************************************************/
    @Test
	public void equalSizeDifArrays(){
    	double[][] arr1 = {{1.0}, {4.0,5.0}};
    	double[][] arr2 = {{1.0, 2.0}, {4.0,5.0,6.0}};
    	assertEquals("The two arrays should be equal", false, DataUtilities.equal(arr1, arr2));
	}
    /********************************************************************
     * ******************************************************************
     * * Test Suite: clone(double[][] source) *
     * * Tester: Braden                                                  *
     * ******************************************************************
     ********************************************************************/
    @Test
	public void validCloneCheck(){
    	double[][] arr1 = {{1.0}, {4.0,5.0}};
    	assertArrayEquals("The two arrays should be equal", arr1, DataUtilities.clone(arr1));
	}
    /********************************************************************
     * ******************************************************************
     * * Test Suite: clone(double[][] source) *
     * * Tester: Braden                                                  *
     * ******************************************************************
     ********************************************************************/
    @Test
	public void cloneNullArray(){
    	double[][] source2 = {{1.0, 2.0, 3.0},null,{7.0, 8.0, 9.0}};
    	double[][] result2 = DataUtilities.clone(source2);
    	assertArrayEquals("The two arrays should be equal", source2, result2);
	}
    /********************************************************************
     * ******************************************************************
     * * Test Suite: getCumulativePercentages(KeyedValues data)  *
     * * Tester: Braden                                                  *
     * ******************************************************************
     ********************************************************************/
    @Test
    public void checkKeyedValue() {
    	Mockery mockingContext = new Mockery();
		final KeyedValues data = mockingContext.mock(KeyedValues.class);
		mockingContext.checking(new Expectations() {
			{
				allowing(data).getItemCount();
				will(returnValue(3));
				allowing(data).getValue(0);
				will(returnValue(5));
				allowing(data).getValue(1);
				will(returnValue(9));
				allowing(data).getValue(2);
				will(returnValue(2));
				allowing(data).getKey(0);
				will(returnValue(0));
				allowing(data).getKey(1);
				will(returnValue(1));
				allowing(data).getKey(2);
				will(returnValue(2));
			}
		});
		
		KeyedValues result = DataUtilities.getCumulativePercentages(data);
		assertEquals(0.3125, result.getValue(0));
	    assertEquals(0.875, result.getValue(1));
	    assertEquals(1.0, result.getValue(2));
    	
    }
   
    
   
    
    
}
