//Kai Cheng

package richArray;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class RichArrayTest {
	
	RichArray test1;
	RichArray test2;
	RichArray test3;
	RichArray test4;
	RichArray test1A;
	RichArray test2A;
	RichArray test4A;

	int[] oneD = new int[] {1, 2, 3, 4, 5};
	int[] oneD2 = new int[] {1, 2, 3, 4, 5, 6};
	int[][] twoD = new int[][] {{1, 2, 3}, {4, 5, 6}};
	int[][] twoD2 = new int[][] {{1, 2}, {4, 5}};
	int[][] threeD = new int[][] {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
	int[][] fourD = new int[][] {{1, 2, 3, 4, 5}, {6, 7, 8, 9, 10}, {11, 12, 13, 14, 15}, {16, 17, 18, 19, 20}};
	int[][] fourD2 = new int[][] {{55, 66, 77}, {88, 99, 100}};
	
	@Before
	public void setUp(){
		test1 = new RichArray(oneD);
		test2 = new RichArray(twoD);
		test3 = new RichArray(threeD);
		test4 = new RichArray(fourD);
		test1A = new RichArray(oneD2);
		test2A = new RichArray(twoD2);
		test4A = new RichArray(fourD2);
	}

	@Rule
    public  ExpectedException exception = ExpectedException.none();
	
	@Test
	public void testReverse() {
		 assertArrayEquals(test1.reverse().toArray2D(), new int[][] {{5, 4, 3, 2, 1}});
	     assertArrayEquals(test2.reverse().toArray2D(), new int[][] {{3, 2, 1}, {6, 5, 4}});
	     assertArrayEquals(test3.reverse().toArray2D(), new int[][] {{4, 3, 2, 1}, {8, 7, 6, 5}, {12, 11, 10, 9}});
	}

	@Test
	public void testRotateRight(){
		assertArrayEquals(test1.rotateRight().toArray2D(), new int[][] {{1}, {2}, {3}, {4}, {5}});
		assertArrayEquals(test2.rotateRight().toArray2D(), new int[][] {{4, 1}, {5, 2}, {6, 3}});
		assertArrayEquals(test3.rotateRight().toArray2D(), new int[][] {{9, 5, 1}, {10, 6, 2}, {11, 7, 3}, {12, 8, 4}});
	}
	
	@Test
	public void testRotateLeft(){
		assertArrayEquals(test1.rotateLeft().toArray2D(), new int[][] {{5}, {4}, {3}, {2}, {1}});
		assertArrayEquals(test2.rotateLeft().toArray2D(), new int[][] {{3, 6}, {2, 5}, {1, 4}});
		assertArrayEquals(test3.rotateLeft().toArray2D(), new int[][] {{4, 8, 12}, {3, 7, 11}, {2, 6, 10}, {1, 5, 9}});
	}
	
	@Test
	public void testTranspose(){
		assertArrayEquals(test1.transpose().toArray2D(), new int[][] {{1}, {2}, {3}, {4}, {5}});
		assertArrayEquals(test2.transpose().toArray2D(), new int[][] {{1, 4}, {2, 5}, {3, 6}});
		assertArrayEquals(test3.transpose().toArray2D(), new int[][] {{1, 5, 9}, {2, 6, 10}, {3, 7, 11}, {4, 8, 12}});
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testRavel(){
		assertArrayEquals(test1.ravel(2).toArray2D(), new int[][] {{1}, {2}, {3}, {4}, {5}});
		assertArrayEquals(test1.ravel(1).toArray2D(), new int[][] {{1}, {2}, {3}, {4}, {5}});
		assertArrayEquals(test1A.ravel(3).toArray2D(), new int[][] {{1, 2, 3}, {4, 5, 6}});
	}
	
	@Test
	public void testUnravel(){
		assertArrayEquals(test1.unravel().toArray2D(), new int[][] {oneD});
		assertArrayEquals(test2.unravel().toArray2D(), new int[][] {{1, 2, 3, 4, 5, 6}});
		assertArrayEquals(test3.unravel().toArray2D(), new int[][] {{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12}});
		assertArrayEquals(test1A.unravel().toArray2D(), new int[][] {oneD2});
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testReshape(){
		assertArrayEquals(test1.reshape(1).toArray2D(), new int[][] {{1}, {2}, {3}, {4}, {5}});
		assertArrayEquals(test2.reshape(2).toArray2D(), new int[][] {{1, 2}, {3, 4}, {5, 6}});
		assertArrayEquals(test2.reshape(5).toArray2D(), new int[][] {{1, 2}, {3, 4}, {5, 6}});
		assertArrayEquals(test3.reshape(3).toArray2D(), new int[][] {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}, {10, 11, 12}});
		assertArrayEquals(test1A.reshape(1).toArray2D(), new int[][] {{1}, {2}, {3}, {4}, {5}, {6}});
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testJoin(){
		assertArrayEquals(test1.join(test1A).toArray2D(), new int[][] {{1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 6}});
		assertArrayEquals(test2.join(test2A).toArray2D(), new int[][] {{1, 2, 3, 1, 2}, {4, 5, 6, 4, 5}});
		assertArrayEquals(test2.join(test3).toArray2D(), new int[][] {{1, 2}, {3, 4}, {5, 6}});
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testStack(){
		assertArrayEquals(test1.stack(test1).toArray2D(), new int[][] {{1, 2, 3, 4, 5}, {1, 2, 3, 4, 5}});
		assertArrayEquals(test2.stack(test2).toArray2D(), new int[][] {{1, 2, 3}, {4, 5, 6}, {1, 2, 3}, {4, 5, 6}});
		assertArrayEquals(test2.stack(test3).toArray2D(), new int[][] {{1, 2}, {3, 4}, {5, 6}});
	}
	
	@Test
	public void testRowCount(){
		assertEquals(1, test1.rowCount());
		assertEquals(2, test2.rowCount());
		assertEquals(3, test3.rowCount());
	}
	
	@Test
	public void testColumnCount(){
		assertEquals(5, test1.columnCount());
		assertEquals(3, test2.columnCount());
		assertEquals(4, test3.columnCount());
	}
	
	@Test
	public void testRows(){
		assertArrayEquals(test1.rows(0, 0).toArray2D(), new int[][] {oneD});
		assertArrayEquals(test2.rows(0, 1).toArray2D(), new int[][] {{1, 2, 3}, {4, 5, 6}});
		assertArrayEquals(test3.rows(1, 2).toArray2D(), new int[][] {{5, 6, 7, 8}, {9, 10, 11, 12}});
	}
	
	@Test
	public void testColumn(){
		assertArrayEquals(test1.columns(0, 2).toArray2D(), new int[][] {{1, 2, 3}});
		assertArrayEquals(test2.columns(1, 2).toArray2D(), new int[][] {{2, 3}, {5, 6}});
		assertArrayEquals(test3.columns(1, 3).toArray2D(), new int[][] {{2, 3, 4}, {6, 7, 8}, {10, 11, 12}});
	}
	
	@Test
	public void testSlice(){
		assertArrayEquals(test2.slice(0, 1, 1, 2).toArray2D(), new int[][] {{2, 3}, {5, 6}});
		assertArrayEquals(test3.slice(1, 2, 2, 3).toArray2D(), new int[][] {{7, 8}, {11, 12}});
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testReplace(){
		assertArrayEquals(test3.replace(test2, 1, 1).toArray2D(), new int[][] {{1, 2, 3, 4}, {5, 1, 2, 3}, {9, 4, 5, 6}});
		assertArrayEquals(test4.replace(test4A, 1, 2).toArray2D(), new int[][] {{1, 2, 3, 4, 5}, {6, 7, 55, 66, 77}, {11, 12, 88, 99, 100}, {16, 17, 18, 19, 20}});
		assertArrayEquals(test4.replace(test4A, 3, 3).toArray2D(), new int[][] {{1, 2, 3, 4, 5}, {6, 7, 55, 66, 77}, {11, 12, 88, 99, 100}, {16, 17, 18, 19, 20}});
	}
	
	@Test
	public void testEquals(){
		RichArray o = new RichArray(twoD2);
		String o1 = "Not an Instance.";
		assertFalse(equals(o1));
		assertFalse(test2.equals(o));		
		assertTrue(test1.equals(new RichArray(oneD)));
	}
	
	@Test
	public void testToArray1D(){
		assertArrayEquals(test2.toArray1D(), new int[] {1, 2, 3, 4, 5, 6});
	    assertArrayEquals(test1.toArray1D(), oneD);
	}
	
	@Test
	public void testToArray2D(){
		assertArrayEquals(test2.toArray2D(), twoD);
	    assertArrayEquals(test1.toArray2D(), new int[][] {{1, 2, 3, 4, 5}});
	}
	
	@Test
	public void testAt(){
		assertEquals(test1.at(0, 1), 2);
		assertEquals(test2.at(1, 2), 6);
		assertEquals(test3.at(0, 3), 4);
	}
}
