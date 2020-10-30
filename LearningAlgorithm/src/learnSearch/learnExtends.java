package learnSearch;

public class learnExtends {
	class InnerFather{
		void print() {
			System.out.println("我是父亲内部类");
		}
	}
	
	public void show() {
		new InnerFather().print();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new OuterSon().new InnerFather().print();
		new OuterSon().show();
	}

}

class OuterSon extends learnExtends{
	class InnerFather extends learnExtends.InnerFather{
		void print() {
			System.out.println("我是儿子内部类");
		}
	}
}
