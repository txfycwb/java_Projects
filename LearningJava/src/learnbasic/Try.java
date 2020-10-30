package learnbasic;


public class Try {
	public Try1 try1; 
	
	public static void main(String[] args) {
		Object object = new S();
		F f = (F)object;
		System.out.println(1==1?1:0);
		Pair<F> pairF = new Pair<>();	
		Pair<S> pairS = new Pair<>();	
		System.out.println(pairF.getClass().getSuperclass());
		System.out.println(pairS.getClass().getSuperclass());
		}
	
	public void tryAgain() {
		
	}
}


class  Pair<T extends F>{
	
}

class F{
	
}

class S extends F{
	
}
enum Size{
	SMALL("s"), LARGE("l");
	public String value;
	
	Size(String string){
		// TODO Auto-generated constructor stub
		this.value =string;
	}
	
}


/**
 * java.lang.ArrayIndexOutOfBoundsException: Index -1 out of bounds for length 26
  at line 22, Solution.reorganizeString
  at line 57, __DriverSolution__.__helper__
  at line 85, __Driver__.main
 */
