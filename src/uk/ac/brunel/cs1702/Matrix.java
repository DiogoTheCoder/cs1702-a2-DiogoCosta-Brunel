package uk.ac.brunel.cs1702;

public class Matrix {
	int[][] array;
	
	/**
	 * Constructor to create a matrix of size rows by cols and to initialise
	 * each element with the value of initialValue
	 * 
	 * @param rows:
	 *            an integer value indicating the number of rows
	 * @param cols:
	 *            an integer value indicating the number of columns
	 * @param initialValue:
	 *            each element of the matrix is initialised to this value
	 */
	public Matrix(int rows, int cols, int initialValue) {
		this.array = new int[rows][cols];
		
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				this.array[i][j] = initialValue;
			}
		}
	}

	/**
	 * Constructor to create a matrix of size equivalent to the size of int[][]
	 * initialData and to initialise each element with the values in
	 * initialData.
	 * 
	 * @param initialData:
	 *            An array of array of integers (int[][]) whose size determines
	 *            the size of the matrix. The array is cloned into the Matrix
	 *            object.
	 */
	public Matrix(int[][] initialData) {
		this.array = initialData;
		
	}

	/**
	 * @return Returns the number of rows of the Matrix object.
	 */
	public int getRows() {
		return this.array.length;
	}

	/**
	 * @return Returns the number of columns of the Matrix object.
	 */
	public int getCols() {
		return this.array[0].length;
	}

	/**
	 * @param i:
	 *            data element row index starting from 0
	 * @param j:
	 *            data element column index starting from 0
	 * @return: Returns the element at row i and column j of the Matrix object
	 */
	public int getData(int i, int j) {
		return this.array[i][j];
	}

	/**
	 * @return Returns a 2-dimensional array of integers (int[][]) where the
	 *         matrix elements are stored
	 */
	public int[][] getData() {
		return this.array;
	}

	/**
	 * @param matrix:
	 *            Matrix object to be added to the caller matrix
	 * @return Returns a new matrix object which is the sum of self and the
	 *         parameter matrix. Returns null if dimensions do not match.
	 */
	public Matrix add(Matrix matrix) {
		if (array.length == matrix.array.length) {
			int[][] sumArray = new int[this.getRows()][this.getCols()];
			
			for (int i = 0; i < this.getRows(); i++) {
				for (int j = 0; j < this.getCols(); j++) {
					sumArray[i][j] = array[i][j] + matrix.array[i][j];
				}
			}
			
			Matrix sumMatrix = new Matrix(sumArray);
			return sumMatrix;
		} else {
			return null;
		}
	}

	/**
	 * @param matrix:
	 *            Matrix object to be subtracted from the caller matrix
	 * @return Returns a new Matrix object which is the difference of self and
	 *         the parameter matrix. Returns null if dimensions do not match.
	 */
	public Matrix sub(Matrix matrix) {
		if (array.length == matrix.array.length) {
			int[][] subArray = new int[this.getRows()][this.getCols()];
			
			for (int i = 0; i < this.getRows(); i++) {
				for (int j = 0; j < this.getCols(); j++) {
					subArray[i][j] = this.array[i][j] - matrix.array[i][j];
				}
			}
			
			Matrix subMatrix = new Matrix(subArray);
			return subMatrix;
		} else {
			return null;
		}
	}

	/**
	 * @return Returns the transpose of the matrix as a new Matrix object
	 */
	public Matrix transpose() {
		int[][] transposeArray = new int[this.getCols()][this.getRows()];
		
	    for(int x = 0; x < this.getRows(); x++)
	    {
	        for(int y = 0; y < this.getCols(); y++)
	        {
	        	transposeArray[y][x] = this.array[x][y];
	        }
	    }
	    
	    Matrix transposeMatrix = new Matrix(transposeArray);
	    return transposeMatrix;
	}

	/**
	 * @param matrix:
	 *            Matrix object to be concatenated to the caller matrix.
	 * @return: Returns the concatenation of the two matrices as a new Matrix
	 *          object. Returns null if the number of columns does not match.
	 */
	public Matrix concat(Matrix matrix) {
		
		if (this.getCols() == matrix.getCols()) {
			
			int colAmount = this.getCols();
			int rowAmount = this.getRows() + matrix.getRows();
			
			// Rows then columns
			int[][] concatArray = new int[rowAmount][colAmount];
			
			// 1st Array
			// Row
		    for(int x = 0; x < this.getRows(); x++)
		    {
		    	// Column
		        for(int y = 0; y < this.getCols(); y++)
		        {
		        	concatArray[x][y] = this.array[x][y];
		        }
		    }
		    
		    // 2nd Array
		    for(int x = 0; x < matrix.getRows(); x++)
		    {
		        for(int y = 0; y < matrix.getCols(); y++)
		        {
		        	concatArray[this.getRows() + x][y] = matrix.array[x][y];
		        }
		    }
		    
		    Matrix concatMatrix = new Matrix(concatArray);
			return concatMatrix;
		}
		
		else {
			return null;
		}
	}

	/**
	 * @param row:
	 *            row index starting from 1
	 * @return: Returns an array (int[]) containing the requested row. Returns null if the row does not exist.
	 */
	public int[] getRow(int row) {		
		if (row <= this.getRows() && row > 0) {
			int[] rowArray = new int[this.getCols()];
			
			for (int x = 0; x < rowArray.length; x++) {
				rowArray[x] = this.getData(row - 1, x);

			}
			
			return rowArray;
		} else {
			return null;
		}

	}

	/**
	 * @param col:
	 *            column index starting from 1
	 * @return : Returns an array (int[]) containing the requested column. Returns null if the row does not exist.
	 */
	public int[] getCol(int col) {		
		if (col <= this.getCols() && col > 0) {
			int[] colArray = new int[this.getRows()];
			
			for (int x = 0; x < colArray.length; x++) {
				
				colArray[x] = this.getData(x, col - 1);
			}
			
			return colArray;
		} else {
			return null;
		}

	}
	
	/**
	 * @return : Returns a String representation of the Matrix.
	 */
	public String toString(){
		String result ="";
	    String ls = System.getProperty("line.separator");
		
		for (int i = 0; i < this.getRows(); i++){
			for (int j = 0; j < this.getCols(); j++)
				result = result.concat(Integer.toString(this.getData(i,j)) + " ");
			result = result.concat(ls);
		}
		return result;
	}
	
	public static void main(String[] args){
		int[][] dataA = {{2, 2},{3, 3}};
		int[][] dataB = {{1, 1},{1, 1}};
		
		Matrix matrixA = new Matrix(dataA);
		Matrix matrixB = new Matrix(dataB);
		
		
		System.out.println("A+B");
		System.out.println(matrixA.add(matrixB).toString());
		System.out.println("A-B");
		System.out.println(matrixA.sub(matrixB).toString());
		System.out.println("transpose(A)");
		System.out.println(matrixA.transpose().toString());
		System.out.println("A concat B");
		System.out.println(matrixA.concat(matrixB).toString());
		System.out.println("B concat A");
		System.out.println(matrixB.concat(matrixA).toString());
		System.out.println("(A concat B) + (B concat A)");
		System.out.println(matrixA.concat(matrixB).add(matrixB.concat(matrixA)).toString());
		System.out.println("A+B-B");
		System.out.println(matrixA.add(matrixB).sub(matrixB).toString());
	}
}