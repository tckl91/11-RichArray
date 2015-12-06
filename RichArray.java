//Kai Cheng

package richArray;

import java.util.Arrays;

public class RichArray {
	
	private int[][] array2D;

	public RichArray(int[][] array) throws NullPointerException {
		if (array == null) {
			throw new NullPointerException("Null");
		}
		else {
			for (int n = 0; n < array.length; n++){
				this.array2D = new int[array.length][];
				for (int i = 0; i < array.length; i++) {
					array2D[i] = Arrays.copyOf(array[i], array[i].length);
				}
			}
		}
	}
	
	public RichArray(int[] array) throws NullPointerException {
		if (array == null) {
			throw new NullPointerException("Null");
		}
		else {
			for (int n = 0; n < array.length; n++){
				array2D = new int[1][array.length];
				for (int i = 0; i < array.length; i++) {
					array2D[0][i] = array[i];
				}
			}
		}
	}
	
	public RichArray(int length){
		for (int n = 0; n < length; n++){
			this.array2D[0][n] = (n + 1);
		}
	}
	
	public RichArray(int rows, int columns){
		int count = 1;
		
		for (int n = 0; n < rows; n++){
			for (int m = 0; m < columns; m++){
				this.array2D[n][m] = count;
				count++;
			}
		}
	}
	
	public RichArray reverse(){
		int[][] reverseArray = new int[this.array2D.length][this.array2D[0].length];
		
		for (int n = 0; n < this.array2D.length; n++){
			int count = this.array2D[0].length - 1;
			for (int m = 0; m < this.array2D[0].length; m++){
				reverseArray[n][m] = this.array2D[n][count];
				count--;
			}
		}
		return new RichArray (reverseArray);
	}
	
	public RichArray rotateRight(){
		int [][] rightArray = new int [this.array2D[0].length][this.array2D.length];
		
		for(int n = 0; n < this.array2D[0].length; n++){
			int count = this.array2D.length - 1;
			for(int m = 0; m < this.array2D.length; m++){
				rightArray[n][m] = this.array2D[count][n];
				count--;
			}
		}
		return new RichArray(rightArray);
	}
	
	public RichArray rotateLeft(){
		int [][] leftArray = new int [this.array2D[0].length][this.array2D.length];
		int columnCount = this.array2D[0].length - 1;
		
		for(int n = 0; n < this.array2D[0].length; n++){
			int rowCount = 0;

			for(int m = 0; m < this.array2D.length; m++){
				leftArray[n][m] = this.array2D[rowCount][columnCount];
				rowCount++;
			}
			columnCount--;
		}
		return new RichArray(leftArray);
	}
	
	public RichArray transpose(){
		int [][] transposeArray = new int [this.array2D[0].length][this.array2D.length];
		int columnCount = 0;
		
		for(int n = 0; n < this.array2D[0].length; n++){
			int rowCount = 0;
			for(int m = 0; m < this.array2D.length; m++){
				transposeArray[n][m] = this.array2D[rowCount][columnCount];
				rowCount++;
			}
			columnCount++;
		}
		return new RichArray(transposeArray);
	}
	
	public RichArray ravel(int n){
		int ravelColumn = n;
		if (this.array2D[0].length % n != 0){
			throw new IllegalArgumentException("RichArray Not Divisible by n!");	
		}
		else {
			int ravelRow = this.array2D[0].length / n;
			int [][] ravelArray = new int [ravelRow][ravelColumn];
			int count = 0;
			
			for(int x = 0; x < ravelRow; x++){
				for(int m = 0; m < ravelColumn; m++){
					ravelArray[x][m] = this.array2D[0][count];
					count++;
				}
			}
			return new RichArray(ravelArray);
		}
	}
	
	public RichArray unravel(){
		int arraySize = this.array2D.length * this.array2D[0].length;
		int [][] unravelArray = new int [1][arraySize];
		int count = 0;
		
		for(int x = 0; x < this.array2D.length; x++){
			for(int m = 0; m < this.array2D[0].length; m++){
				unravelArray[0][count] = this.array2D[x][m];
				count++;
			}
		}	
		return new RichArray(unravelArray);
	}
	
	public RichArray reshape(int n){
		int totalSize = this.array2D.length * this.array2D[0].length;
		int newColumn = n;
		if (totalSize % n != 0){
			throw new IllegalArgumentException("RichArray Not Divisible by n!");	
		}
		else {
			int newRow = totalSize / n;
			int [][] newArray = new int [1][totalSize];
			int [][] shapeArray = new int [newRow][newColumn];
			int count = 0;
			
			for(int x = 0; x < this.array2D.length; x++){
				for(int m = 0; m < this.array2D[0].length; m++){
					newArray[0][count] = this.array2D[x][m];
					count++;
				}
			}
			
			count = 0;
			
			for(int x = 0; x < newRow; x++){
				for(int m = 0; m < newColumn; m++){
					shapeArray[x][m] = newArray[0][count];
					count++;
				}
			}
			return new RichArray(shapeArray);
		}
	}
	
	public RichArray join(RichArray array){
		int count;
		
		if (this.array2D.length != array.array2D.length){
			throw new IllegalArgumentException("Arrays Don't Have the Same Row Number");
		}
		else {
			int newColumn = this.array2D[0].length + array.array2D[0].length;
			int [][] joinArray = new int [this.array2D.length][newColumn];
			
			for(int n = 0; n < this.array2D.length; n++){
				for(int m = 0; m < this.array2D[0].length; m++){
					joinArray[n][m] = this.array2D[n][m];
				}
			}
			for(int n = 0; n < array.array2D.length; n++){
				count = this.array2D[0].length;
				for(int m = 0; m < array.array2D[0].length; m++){
					joinArray[n][count] = array.array2D[n][m];
					count++;
				}
			}
			return new RichArray(joinArray);
		}
	}
	
	public RichArray stack(RichArray array){
		int oldCount, newCount;
		
		if (this.array2D[0].length != array.array2D[0].length){
			throw new IllegalArgumentException("Arrays Don't Have the Same Column Number");
		}
		else {
			int newRow = this.array2D.length + array.array2D.length;
			int [][] stackArray = new int [newRow][this.array2D[0].length];
			oldCount = 0;
			newCount = 0;		
			
			for(int n = 0; n < newRow; n++){
				if(oldCount < this.array2D.length){
					for(int m = 0; m < this.array2D[0].length; m++){
						stackArray[n][m] = this.array2D[oldCount][m];
					}
					oldCount++;
				}
				else {
					for(int m = 0; m < this.array2D[0].length; m++){
						stackArray[n][m] = array.array2D[newCount][m];
					}
					newCount++;
				}
			}
			return new RichArray(stackArray);
		}
	}
	
	public int rowCount(){
		int row;
		row = this.array2D.length;
		return row;
	}
	
	public int columnCount(){
		int column;
		column = this.array2D[0].length;
		return column;
	}
	
	public RichArray rows(int firstRow, int lastRow){
		int rowNum = lastRow - firstRow + 1;
		int [][] rowsArray = new int [rowNum][this.array2D[0].length];
		int rowCount = firstRow;
		
		for(int n = 0; n < rowNum; n++){
			for(int m = 0; m < this.array2D[0].length; m++){
				rowsArray[n][m] = this.array2D[rowCount][m];
			}
			rowCount++;
		}
		return new RichArray(rowsArray);
	}
	
	public RichArray columns(int firstColumn, int lastColumn){
		int columnNum = lastColumn - firstColumn + 1;
		int [][] columnsArray = new int [this.array2D.length][columnNum];
		int columnCount;
		
		for(int n = 0; n < this.array2D.length; n++){
			columnCount = firstColumn;
			for(int m = 0; m < columnNum; m++){
				columnsArray[n][m] = this.array2D[n][columnCount];
				columnCount++;
			}
		}
		return new RichArray(columnsArray);
	}
	
	public RichArray slice(int firstRow, int lastRow, int firstColumn, int lastColumn){
		int rowNum = lastRow - firstRow + 1;
		int columnNum = lastColumn - firstColumn + 1;
		int [][] sliceArray = new int [rowNum][columnNum];
		int columnCount, rowCount = firstRow;
		
		for(int n = 0; n < rowNum; n++){
			columnCount = firstColumn;
			for(int m = 0; m < columnNum; m++){
				sliceArray[n][m] = this.array2D[rowCount][columnCount];
				columnCount++;
			}
			rowCount++;
		}
		return new RichArray(sliceArray);
	}
	
	public RichArray replace(RichArray array, int row, int column){
		if ((row + array.array2D.length) > this.array2D.length){
			throw new IllegalArgumentException ("Out of Bounds");
		}
		if ((column + array.array2D[0].length) > this.array2D[0].length){
			throw new IllegalArgumentException ("Out of Bounds");
		}
		
		int rowCount = 0, columnCount;
		int [][] replaceArray = new int [this.array2D.length][this.array2D[0].length];
		replaceArray = this.array2D;
		
		for(int n = row; n < (row + array.array2D.length); n++){
			columnCount = 0;
			for(int m = column; m < (column + array.array2D[0].length); m++){
				replaceArray[n][m] = array.array2D[rowCount][columnCount];
				columnCount++;
			}
			rowCount++;
		}
		return new RichArray(replaceArray);
	}
	
	@Override
	public boolean equals(Object o){
		if(o instanceof RichArray){
			RichArray that = (RichArray)o;
			if(this.array2D.length == that.array2D.length){
				if(this.array2D[0].length == that.array2D[0].length){
					for(int i =0;i<array2D.length;i++){
						for(int j =0;j<array2D[0].length;j++){
							if(this.array2D[i][j]!= that.array2D[i][j]){
								return false;
							}
						}
					}
				}
				else {
					return false;
				}
			}
			else {
				return false;
			}
		return true;
		}
		else {
			return false;
		}
	}
	
	@Override
	public int hashCode(){
		return this.array2D.hashCode();
	}
	
	public int[] toArray1D(){
		if (Arrays.deepEquals(array2D, new int[0][0])){
			return new int[0];
		}
		return this.unravel().array2D[0];
	}
	
	public int[][] toArray2D(){
		int[][] newArray2D = new RichArray(array2D).array2D;
		
		return newArray2D;
	}
	
	public int at(int row, int column){
		return this.array2D[row][column];
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
