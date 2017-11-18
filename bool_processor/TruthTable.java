package bool_processor;

import java.util.ArrayList;

public class TruthTable {
	
	private String statment;
	private boolean table[][];
	private boolean answers[];
	private String statments[];
	private int varibleNum;
	private String varibleName[];
	
	public TruthTable(String statment) {
		
		BoolMethods.test(testPrep(statment));
		this.statment=statment;
		varibleNum=countDifVar(statment);
		table = new boolean[(int)Math.pow(2, varibleNum)][varibleNum];
		inputGen();
		answers();
	}
	
	public String toString()
	{
		String table = "";
		for(int i=0;i<varibleNum;i++)
		{
			table+=varibleName[i]+" ";
		}
		table+=": "+statment+"\n";
		int pow = (int)Math.pow(2, varibleNum);
		for(int j=0;j<pow;j++)
		{
			for(int i=0;i<varibleNum;i++)
			{				
				table+=this.table[j][i]+" ";			
			}
			table+=": "+statments[j]+" : "+answers[j]+"\n";
		}
		answers=new boolean[pow];
		return table;
	}
	private void answers()
	{
		int pow= (int)Math.pow(2, varibleNum);
		answers=new boolean[pow];
		statments=new String[pow];
		for(int i=0;i<pow;i++)
		{
			String statment=this.statment;
			for(int j=0;j<varibleNum;j++)
			{
				statment= replaceAll(statment, varibleName[j], ""+table[i][j]);
			}
			statments[i]=statment;
			answers[i]=BoolMethods.stringToBool(statment);
		}
		
	}
	static String replaceAll(String s, String target,String replaceWith)
	{
		if(s.length()>=target.length())
		{
			if(target.equals(s.substring(0, target.length())))
			{
				return replaceWith+replaceAll(s.substring(target.length()),target,replaceWith);
			}
			else
			{
				return s.charAt(0)+replaceAll(s.substring(1),target,replaceWith);
			}
				
		}
		return s;
	}
	
	private void inputGen()
	{
		int inputNum=(int)Math.pow(2, varibleNum);
		for(int i=0;i<varibleNum;i++)
		{
			boolean a=true;
			for(int j=0;j<inputNum;j++)
			{
				a=(j%Math.pow(2, i)==0)?!a:a;
				table[j][i]=a;
			}
		}
	}
	
	private int countDifVar(String s)
	{
		boolean varible=false;
		int start=0;
		int answer=0;
		ArrayList<String> names=new ArrayList<String>();
		for(int i=0;i<s.length();i++)
		{
			if(varible)
			{
				if(s.charAt(i)==']')
				{
					String varibleName=s.substring(start, i+1);
					answer++;
					s=BoolMethods.removeAll(s, varibleName);
					varible=false;
					i=0;
					names.add(varibleName);
				}
			}
			else if(s.charAt(i)=='[')
			{
				varible=true;
				start=i;
			}
			varibleName=names.toArray(new String[0]);
		}
		return answer;
	}
	
	/* removes variable names to make use of BoolMethods test 
	 * 
	 */
	private String testPrep(String s)
	{
		String newS="";
		boolean varible=false;
		for(int i=0;i<s.length();i++)
		{
			if(varible)
			{
				varible=s.charAt(i)!=']';				
			}
			else if(s.charAt(i)=='[')
			{
				varible=true;
			}
			else
			{
				newS+=s.charAt(i);
			}
		}
		return newS;
	}
	public static void main(String[] args) {
		
	}

}
