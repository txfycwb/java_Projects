package learnString;

/**
 * 查找子字符串，暴力查找法一般与N+M成正比，最坏NM（Java中String的indexOf（））
 * @author guo
 * 除暴力法外，都要对pat进行预处理。
 * KMP、BM、RK算法均如此。前二者要额外空间，RK要若干次算术运算。
 * BM从右向左扫描pat，需要right[]记录每个字符在pat中出现的最右位置。亚线性级别
 * RK指纹查找法：找到一种快速的散列值计算，再将pat和txt中所有可能的子字符串的散列值一一比较。线性级别。
 */
public class StringFindSub {

}
/**
	KMP算法基本思想：当出现不匹配时，就能知晓一部份文本的内容（因为匹配失败前它们已经和模式相匹配），
				 我们可以利用这些信息，避免将指针回退到这些已知字符前。
	实现：使用确定有限状态机DFA，由状态和转换构成。dft[txt.charAt(i)][j]代表pat比较了0到j
	后下一步，i+1和pat中第几个元素比较。
	性能：访问次数不超过M+N次。
	优点：不需要回退，适合输入流比较。
 */
class KMP{
	private String pat;
	private int[][] dfa;
	public KMP(String pat) {
		super();
		this.pat = pat;
		int M = pat.length();
		int R = 256;
		this.dfa = new int[R][M];
		dfa[pat.charAt(0)][0] = 1;
		for(int X=0, j=1;j<M;j++) {
			for(int c=0;c<R;c++)
				dfa[c][j]=dfa[c][X];//pat中第j元素匹配失败
			dfa[pat.charAt(j)][j] = j+1;//pat中第j元素匹配成功
			//更新X的值：j+1步匹配失败，则考虑第j个元素在已经匹配了X个元素下的下一pat指针处
			X = dfa[pat.charAt(j)][X];
		}
	}
	
	public int search(String txt) {
		int i,j,N=txt.length(),M=pat.length();
		//j代表状态机的状态，pat中0~j-1个字符均匹配
		for(i=0,j=0;i<N&&j<M;i++)
			j = dfa[txt.charAt(i)][j];
		if(j==M) return i-j;
		return N;
	}
	
}