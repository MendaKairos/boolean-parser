package bool_processor;

public class BoolMethods {

	private final static char OPERATIONS[]= {'-','&','|','>','=','^'};
	
	private BoolMethods() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	public static boolean stringToBool(String s)
	{
		s=s.replaceAll(" ","");
		test(s);
		return Boolean.parseBoolean(getBaseCase(s));
	}
	
	static boolean test(String s)
	{
		s=removeAll(s,"true");
		s=removeAll(s,"false");
		s=removeAll(s,"(");
		s=removeAll(s,")");
		for(int i=0;i<OPERATIONS.length;i++)
		{
			s=removeAll(s,""+OPERATIONS[i]);
		}
		if(s.length()==0)
		{
			return true;
		}
		throw new IllegalArgumentException();
	}
	
	 static String removeAll(String s, String target)
	{
		if(s.length()>=target.length())
		{
			if(s.substring(0, target.length()).equalsIgnoreCase(target))
			{
				return removeAll(s.substring(target.length()),target);
			}
			else
			{
				return s.charAt(0)+removeAll(s.substring(1),target);
			}
		}
		return s;
	}
	
	//()()(())
	private static String getBaseCase(String s)
	{
		//System.out.println(s);
		boolean found=false;
		
		int open=0;
		int close=0;
		for(int i=0;i<s.length()&&!found;i++)
		{
			if(s.charAt(i)=='(')
			{
				open=i;
			}
			else if(s.charAt(i)==')')
			{
				close=i;
				found=true;
			}
		}
		if(found)
		{
			return getBaseCase(s.substring(0, open)+getBaseCase(s.substring(open+1,close))+s.substring(close+1));
		}
		else
		{
			return baseCase(s);
		}
		
	}
	
	private static String base(String s)
	{
		boolean a=!Boolean.parseBoolean(s);
		return a+"";
	}
	
	private static String base(String s,char c)
	{
		int i=s.indexOf(c);
		
		boolean a= Boolean.parseBoolean(s.substring(0,i));
		boolean b= Boolean.parseBoolean(s.substring(i+1,s.length()));
		switch(c)
		{
			case '&':
				return (a&&b)+"";
			case '|':
				return (a||b)+"";
			case '>':
				return (!a||b)+"";
			case '=':
				return (a==b)+"";
			case '^':
				return (a!=b)+"";
		
		}
		return s;
	}
	//true;
	//false
	private static String baseCase(String s)
	{
		//String ns="";
		for(int j=0;j<s.length();j++)
		{
			if(s.charAt(j)==OPERATIONS[0])
			{
				int w=Boolean.parseBoolean(s.substring(j+1, j+5))?5:6;
				s=s.substring(0,j)+ base(s.substring(j+1, j+w))+ s.substring(j+w,s.length());
				j=0;
			}
		}			
		
		for(int i=1;i<OPERATIONS.length;i++)
		{
			for(int j=0;j<s.length();j++)
			{
				if(s.charAt(j)==OPERATIONS[i])
				{
					//System.out.println(j+" "+s.charAt(j));
					int w=Boolean.parseBoolean(s.substring(j+1, j+5))?5:6;
					int q=Boolean.parseBoolean(s.substring(j-4, j))?4:5;
					s=s.substring(0,j-q)+ base(s.substring(j-q, j+w),OPERATIONS[i])+ s.substring(j+w,s.length());
					j=0;
				}
			}			
		}
		
		
		return s;
	}
	
	
	
	/*
	 * conjunction/And   &
	 * disjunction/Or    |
	 * not      -
	 * implication >
	 * equivalence  =
	 * XOR  ^
	 */
	
	public static void main(String[] args) {
		String a="true>false";
		
		//String b="((false&true)>true)=true";

		
		System.out.println(stringToBool(a));
		//System.out.println(a.substring(j-4, j));
	}
}
