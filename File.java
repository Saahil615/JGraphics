import java.io.*;

/**
 * This class simplifies the process of reading and writing files.
 */
public class File
{
    private BufferedReader br;
    private BufferedWriter bw;
    private final String path;

    /**
     * Constructor for objects of class File
     * @param f The path of the file to be opened.
     */
    public File(String f)
    {
        path = f;
        try
        {
            FileReader fr = new FileReader( path );
            br = new BufferedReader( fr );
        }catch(FileNotFoundException e)
        {
            try
            {
                java.io.File file = new java.io.File( path );
                file.createNewFile();
            } catch (IOException e2)
            {
                e2.printStackTrace();
            }
        }
    }

    /**
     * Resets the file to the beginning for reading.
     * Should be called before reading the file using nextLine().
     */
    public void openFile()
    {
        try
        {
            FileReader fr = new FileReader( path );
            br = new BufferedReader( fr );

        }catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Closes the file.
     */
    public void closeFile()
    {
        try
        {
            br.close();
            bw.close();
        }catch(IOException e)
        {
            e.printStackTrace();
        }

    }

    /**
     * Returns the next line of the file.
     * Should be called after opening the file using openFile().
     * @return The next line of the file.
     */
    public String nextLine()
    {
        try
        {
            return br.readLine();
        }
        catch (java.io.IOException ioe)
        {
            ioe.printStackTrace();
        }
        return null;
    }

    /**
     * Returns every line in the file.
     * @return A String array containing every line in the file as a different entry.
     */
    public String[] readFile()
    {
        String[] data;
        openFile();
        String line = "";
        int nol = 0;
        while(line != null)
        {
            nol++;
            line = nextLine();
        }
        data = new String[nol];
        closeFile();
        openFile();
        for (int i = 0; i < nol; i++)
        {
            data[i] = nextLine();
        }
        closeFile();
        return data;
    }

    /**
     * Writes a String to the end of the file without a new line.
     * @param w The String to be written.
     */
    public void write(String w)
    {
        try
        {
            FileWriter fw = new FileWriter( path,true);
            bw = new BufferedWriter( fw );
            bw.write( w );
            bw.close();
        }catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Clears the file, overwriting everything inside it.
     */
    public void clear()
    {
        try
        {
            FileWriter fw = new FileWriter( path,false);
            bw = new BufferedWriter( fw );
            bw.write("");
            bw.close();
            fw.close();
        }catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Inserts a newline at the end of the file.
     */
    public void newLine()
    {
        try
        {
            FileWriter fw = new FileWriter( path,true);
            bw = new BufferedWriter( fw );
            bw.write( "\n" );
            bw.close();
            fw.close();
        }catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Writes a String to the end of the file with a new line.
     * @param w The String to be written.
     */
    public void writeLine(String w){
        try
        {
            FileWriter fw = new FileWriter( path,true);
            bw = new BufferedWriter( fw );
            bw.write( w + "\n" );
            bw.close();
            fw.close();
        }catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}
