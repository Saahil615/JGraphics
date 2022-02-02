import java.io.*;
public class File
{
    private BufferedReader br;
    private BufferedWriter bw;
    private final String path;

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
    public void write(String w)
    {
        try
        {
            bw.write( w );
            bw.close();
        }catch(IOException e)
        {
            e.printStackTrace();
        }
    }
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
}
