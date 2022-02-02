import java.awt.Graphics2D;

class Curve
{
    public int t;
    Polygon[] p;
    int c = 0;

    public Curve( int type )
    {
        t = type;
        if ( type == 3 )
        {
            Polygon po = new Polygon();
            po.noFill = true;
            p = add( po );
        }
        else if ( type == 4 )
        {
            Polygon po = new Polygon();
            po.strict = true;
            p = add( po );
        }
        else
        {
            p = add( new Polygon() );
        }
    }

    public Polygon[] add( Polygon poly )
    {
        Polygon[] temp = new Polygon[p.length + 1];
        System.arraycopy( p, 0, temp, 0, p.length );
        temp[p.length] = poly;
        return temp;
    }

    public void addVertex( Vertex a )
    {
        if ( this.t == 1 )
        {
            if ( p[c].vert.length == 3 )
            {
                c += 1;
                p = add( new Polygon() );
                p[c].addVertex( a );
            }
            else
            {
                p[c].addVertex( a );
            }
        }
        else if ( this.t == 2 )
        {
            if ( p[c].vert.length == 4 )
            {
                c += 1;
                p = add( new Polygon() );
                p[c].addVertex( a );
            }
            else
            {
                p[c].addVertex( a );
            }
        }
        else
        {
            p[c].addVertex( a );
        }

    }

    public void draw( Graphics2D g, Painting_instr pa )
    {
        for ( Polygon po : p )
        {
            po.draw( g, pa );
        }
    }
}
