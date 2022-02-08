public class Matrix
{
    private float[][] matrix;
    private int rows;
    private int columns;

    /**
     * Creates a new matrix that has the specified dimensions and is filled with zeros
     *
     * @param rows    the number of rows in the new matrix
     * @param columns the number of columns in the new matrix
     */
    public Matrix( int rows, int columns )
    {
        this.rows = rows;
        this.columns = columns;
        matrix = new float[rows][columns];
    }

    /**
     * Creates a new Matrix with the specified dimensions and fills it with random values between 0 and 1
     *
     * @param rows    the number of rows in the new matrix
     * @param columns the number of columns in the new matrix
     */
    public static Matrix random( int rows, int columns )
    {
        Matrix m = new Matrix( rows, columns );
        for ( int i = 0; i < rows; i++ )
        {
            for ( int j = 0; j < columns; j++ )
            {
                m.set( i, j, (float) Math.random() );
            }
        }
        return m;
    }

    /**
     * Creates a matrix with the data from the specified array
     *
     * @param matrix the array to create the matrix from
     */
    public Matrix( float[][] matrix )
    {
        this.rows = matrix.length;
        this.columns = matrix[0].length;
        this.matrix = matrix;
    }

    /**
     * Creates a copy of the input matrix
     *
     * @param m the matrix to copy
     */
    public Matrix( Matrix m )
    {
        this.rows = m.rows;
        this.columns = m.columns;
        this.matrix = m.matrix;
    }

    /**
     * Gets the element from the matrix at the specified row and column
     *
     * @param row    the row of the element to get
     * @param column the column of the element to get
     * @return the element at the specified row and column
     */
    public float get( int row, int column )
    {
        return matrix[row][column];
    }

    /**
     * Sets the element at the specified row and column to the specified value
     *
     * @param row    the row of the element to set
     * @param column the column of the element to set
     * @param value  the value to set the element to
     */
    public void set( int row, int column, float value )
    {
        matrix[row][column] = value;
    }

    /**
     * @return the number of rows in the matrix
     */
    public int getRows()
    {
        return rows;
    }

    /**
     * @return the number of columns in the matrix
     */
    public int getColumns()
    {
        return columns;
    }

    /**
     * @return a 2D array containing the data from the matrix
     */
    public float[][] getMatrix()
    {
        return matrix;
    }

    /**
     * Sets the data of the matrix to the specified array
     *
     * @param matrix the array to set the matrix to
     */
    public void setMatrix( float[][] matrix )
    {
        this.matrix = matrix;
    }

    /**
     * Adds the specified matrix to this matrix
     *
     * @param m the matrix to add to this matrix
     * @return a new matrix containing the result of the addition
     */
    public Matrix add( Matrix m )
    {
        if ( this.rows != m.rows || this.columns != m.columns )
        {
            throw new IllegalArgumentException( "Matrices must be of the same order for addition" );
        }
        Matrix result = new Matrix( this.rows, this.columns );
        for ( int i = 0; i < this.rows; i++ )
        {
            for ( int j = 0; j < this.columns; j++ )
            {
                result.set( i, j, get( i, j ) + m.get( i, j ) );
            }
        }
        return result;
    }

    /**
     * Subtracts the specified matrix from this matrix
     *
     * @param m the matrix to subtract from this matrix
     * @return a new matrix containing the result of the subtraction
     */
    public Matrix subtract( Matrix m )
    {
        if ( this.rows != m.rows || this.columns != m.columns )
        {
            throw new IllegalArgumentException( "Matrices must be of the same order for subtraction" );
        }
        Matrix result = new Matrix( this.rows, this.columns );
        for ( int i = 0; i < this.rows; i++ )
        {
            for ( int j = 0; j < this.columns; j++ )
            {
                result.set( i, j, get( i, j ) - m.get( i, j ) );
            }
        }
        return result;
    }

    /**
     * Multiplies this matrix by the specified scalar
     *
     * @param scalar the scalar to multiply this matrix by
     */
    public void multiply( float scalar )
    {
        for ( int i = 0; i < this.rows; i++ )
        {
            for ( int j = 0; j < this.columns; j++ )
            {
                matrix[i][j] *= scalar;
            }
        }
    }

    /**
     * Divides this matrix by the specified scalar
     *
     * @param scalar the scalar to divide this matrix by
     */
    public void divide( float scalar )
    {
        for ( int i = 0; i < this.rows; i++ )
        {
            for ( int j = 0; j < this.columns; j++ )
            {
                matrix[i][j] /= scalar;
            }
        }
    }

    /**
     * Multiplies this matrix by the specified matrix
     *
     * @param m the matrix to multiply this matrix by
     * @return a new matrix containing the result of the multiplication
     */
    public Matrix multiply( Matrix m )
    {
        if ( this.columns != m.rows )
        {
            throw new IllegalArgumentException( "Matrices are not compatible for multiplication" );
        }
        Matrix result = new Matrix( this.rows, m.columns );
        for ( int i = 0; i < this.columns; i++ )
        {
            for ( int j = 0; j < m.rows; j++ )
            {
                for ( int k = 0; k < this.rows; k++ )
                {
                    result.set( j, i, result.get( j, i ) + this.get( j, k ) * m.get( k, i ) );
                }
            }
        }
        return result;
    }

    /**
     * Transposes this matrix
     *
     * @return a new matrix containing the transpose of this matrix
     */
    public Matrix transpose()
    {
        Matrix result = new Matrix( columns, rows );
        for ( int i = 0; i < rows; i++ )
        {
            for ( int j = 0; j < columns; j++ )
            {
                result.set( j, i, matrix[i][j] );
            }
        }
        return result;
    }

    /**
     * @return A String representation of the matrix
     */
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        for ( float[] row : matrix )
        {
            sb.append( "|" );
            for ( float element : row )
            {
                sb.append( element );
            }
            sb.append( "|\n" );
        }
        return sb.toString();
    }

    /**
     * Checks equality between this matrix and the specified matrix
     *
     * @param o the object to compare this matrix to
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals( Object o )
    {
        if ( !( o instanceof Matrix m ) )
        {
            return false;
        }
        if ( this.rows != m.rows || this.columns != m.columns )
        {
            return false;
        }
        for ( int i = 0; i < this.rows; i++ )
        {
            for ( int j = 0; j < this.columns; j++ )
            {
                if ( get( i, j ) != m.get( i, j ) )
                {
                    return false;
                }
            }
        }
        return true;
    }

}
