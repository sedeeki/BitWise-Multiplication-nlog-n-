
// zmnozi function is the one you will put where your professor said.
// the rest functions are helper functions which you can copy after below the zmnozi function at the end
public class Main 
{
	public static void main(String arg[])
	{
		byte[] x = {1,0,1,0};
		byte[] y = {1,0,0,1};
		byte[] answer = zmnozi(x,y);
		System.out.print("Binary value is: ");
		for (int i = 0; i < answer.length; i++)
			System.out.print(answer[i]);
	}
	
	
	public static byte[] zmnozi(byte[] x, byte[] y)
	{
		int value = multiplication(x,y);
		System.out.println("Value is: " + value);
		int temp = value;
		int size = 0;
		while (temp != 0)
		{
			temp = temp/2;
			size++;
		}
		byte answer[] = new byte[size];
		int index = size - 1;
		while (value != 0)
		{
			if (value%2 == 0)
				answer[index] = 0;
			else
				answer[index] = 1;
			value = value/2;
			index--;
		}
		return answer;
	}
	
	public static int multiplication(byte[] x, byte[] y)
	{
		int xSize = x.length;
		int ySize = y.length;
		byte temp[];
		int size = 0;
		if (xSize > ySize)
		{
			size = xSize;
			int index = 0;
			temp = new byte[xSize];
			for (int i = 0; i < xSize; i++)
			{
				if (i < xSize - ySize)
					temp[i] = 0;
				else
				{
					temp[i] = y[index];
					index++;
				}
			}
			y = temp;
		}
		
		else if (ySize > xSize)
		{
			size = ySize;
			temp = new byte[ySize];
			int index = 0;
			for (int i = 0; i < ySize; i++)
			{
				if (i < ySize - xSize)
				{
					temp[i] = 0;
					//System.out.println(i);
				}
				else
				{
					temp[i] = x[index];
					//System.out.println(i + " " + index);
					index++;
				}
				
			}
			x = temp;
		}
		else
			size = xSize;
		//for (int i = 0; i < x.length; i++)
			//System.out.print(x[i]);
		//System.out.println(" ");
		//for (int i = 0; i < y.length; i++)
		//	System.out.print(y[i]);
		//System.out.println(" ");
		if (size == 0)
			return 0;
		if (size == 1)
		{
			if (x[0] == 0 || y[0] == 0)
				return 0;
			else if (x[0] == 1 && y[0] == 1)
				return 1;
		}
		int left = size/2;
		int right = size - left;
		byte xLeft[] = new byte[left];
		byte xRight[] = new byte[right];
		int indexL = 0;
		int indexR = 0;
		for (int i = 0; i < size; i++)
		{
			if (i < left)
				xLeft[indexL++] = x[i]; 
			else
				xRight[indexR++] = x[i];
		}
		byte yLeft[] = new byte[left];
		byte yRight[] = new byte[right];
		indexL = 0;
		indexR = 0;
		for (int i = 0; i < size; i++)
		{
			if (i < left)
				yLeft[indexL++] = y[i]; 
			else
				yRight[indexR++] = y[i];
		}
		int leftPart = multiplication(xLeft,yLeft);
		int rightPart = multiplication(xRight,yRight);
		byte[] addX = add(xLeft,xRight);
		byte[] addY = add(yLeft,yRight);
		int middlePart = multiplication(addX,addY);
		return (leftPart*(1<<(2*right)))+((middlePart-leftPart-rightPart)*(1<<right))+rightPart;
	}
	
	
	
	public static byte[] add(byte[] x, byte[] y)
	{
		int xSize = x.length;
		int ySize = y.length;
		byte temp[];
		int size = 0;
		if (xSize > ySize)
		{
			size = xSize;
			int index = 0;
			temp = new byte[xSize];
			for (int i = 0; i < xSize; i++)
			{
				if (i < xSize - ySize)
					temp[i] = 0;
				else
				{
					temp[i] = y[index];
					index++;
				}
			}
			y = temp;
		}
		
		else if (ySize > xSize)
		{
			size = ySize;
			temp = new byte[ySize];
			int index = 0;
			for (int i = 0; i < ySize; i++)
			{
				if (i < ySize - xSize)
				{
					temp[i] = 0;
				}
				else
				{
					temp[i] = x[index];
					index++;
				}
				
			}
			x = temp;
		}
		else
			size = xSize;
		
		
		temp = new byte[size];
		int sum = 0;
		int carry = 0;
		for (int i = size - 1; i > -1; i--)
		{
			sum = x[i] + y[i] + carry;
			if (sum == 0)
				temp[i] = 0;
			else if (sum == 1)
				temp[i] = 1;
			else if (sum == 2)
			{
				temp[i] = 0;
				carry = 1;
			}
			else if (sum == 3)
			{
				temp[i] = 1;
				carry = 1;
			}
		}
		if (carry == 1)
		{
			byte newTemp[] = new byte[temp.length + 1];
			newTemp[0] = 1;
			for (int i = 0; i < temp.length; i++)
				newTemp[i+1] = temp[i];
			return newTemp;
		}
		
		else
			return temp;
			
	}
	
}
