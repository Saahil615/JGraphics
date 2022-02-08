public class Matrix
{
    private float[][] matrix;
    private int rows;
    private int columns;

    public Matrix( int rows, int columns )
    {
        this.rows = rows;
        this.columns = columns;
        matrix = new float[rows][columns];
    }

    //write a constructor to input number of rows and columns and set each value to a random number between 0 and 1
    public Matrix( int rows, int columns, boolean random )
    {
        this.rows = rows;
        this.columns = columns;
        matrix = new float[rows][columns];
        if ( random )
        {
            for ( int i = 0; i < rows; i++ )
            {
                for ( int j = 0; j < columns; j++ )
                {
                    matrix[i][j] = (float)Math.random();
                }
            }
        }
    }

    public Matrix( float[][] matrix )
    {
        this.rows = matrix.length;
        this.columns = matrix[0].length;
        this.matrix = matrix;
    }

    public Matrix( Matrix m )
    {
        this.rows = m.rows;
        this.columns = m.columns;
        this.matrix = m.matrix;
    }

    public float get( int row, int column )
    {
        return matrix[row][column];
    }

    public void set( int row, int column, float value )
    {
        matrix[row][column] = value;
    }

    public int getRows()
    {
        return rows;
    }

    public int getColumns()
    {
        return columns;
    }

    public void setRows( int rows )
    {
        this.rows = rows;
    }

    public void setColumns( int columns )
    {
        this.columns = columns;
    }

    public float[][] getMatrix()
    {
        return matrix;
    }

    public void setMatrix( float[][] matrix )
    {
        this.matrix = matrix;
    }

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
    public Matrix subtract(Matrix m){
        if(this.rows != m.rows || this.columns != m.columns){
            throw new IllegalArgumentException("Matrices must be of the same order for subtraction");
        }
        Matrix result = new Matrix(this.rows, this.columns);
        for(int i = 0; i < this.rows; i++){
            for(int j = 0;j<this.columns;j++){
                result.set(i,j,get(i,j)-m.get(i,j));
            }
        }
        return result;
    }
    public void multiply(float scalar){
        for(int i = 0; i < this.rows; i++){
            for(int j = 0;j<this.columns;j++){
                matrix[i][j] *= scalar;
            }
        }
    }
    public void divide(float scalar){
        for(int i = 0; i < this.rows; i++){
            for(int j = 0;j<this.columns;j++){
                matrix[i][j] /= scalar;
            }
        }
    }
    public Matrix multiply(Matrix m)
    {
        if ( this.columns != m.rows )
        {
            throw new IllegalArgumentException( "Matrices are not compatible for multiplication" );
        }
        Matrix result = new Matrix( this.rows, m.columns );
        for(int i = 0;i<this.columns;i++){
            for(int j = 0;j<m.rows;j++){
                for(int k = 0;k<this.rows;k++){
                    result.set( j, i, result.get( j, i ) + this.get( j, k ) * m.get( k, i ) );
                }
            }
        }
        return result;
    }
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(float[] row:matrix){
            sb.append("|");
            for(float element:row){
                sb.append(element);
            }
            sb.append("|\n");
        }
        return sb.toString();
    }
    @Override
    public boolean equals(Object o){
        if(!( o instanceof Matrix m ))
            return false;
        if(this.rows != m.rows || this.columns != m.columns){
            return false;
        }
        for(int i = 0; i < this.rows; i++){
            for(int j = 0; j < this.columns; j++){
                if(get(i,j) != m.get(i,j)){
                    return false;
                }
            }
        }
        return true;
    }

}
