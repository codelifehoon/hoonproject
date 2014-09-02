package ref.sample.DecomposeConditional;

public class DecomposeConditional {

	public int compute(int cond , int a , int b, int c)
	{
		int r= 0 ;
		
		if ( cond > a  || cond > b ) 
		{
			r = a*b/2;
		}
		else if ( cond > b || cond > c)
		{
			r = a*b*2;
		}
		else
		{
			r = a*b;
		}
		
		
		return r;
		
	}
	
}
