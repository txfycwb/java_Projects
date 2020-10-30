package learnSearch;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
/**
 * 	在一个单元测试中，我们经常编写多个@Test方法，来分组、分类对目标代码进行测试。
	在测试的时候，我们经常遇到一个对象需要初始化，测试完可能还需要清理的情况。如果每个@Test方法都写一遍这样的重复代码，显然比较麻烦。
	JUnit提供了编写测试前准备、测试后清理的固定代码，我们称之为Fixture。
	通过@BeforeEach来初始化，通过@AfterEach来清理资源.
	
	还有一些资源初始化和清理可能更加繁琐，而且会耗费较长的时间，例如初始化数据库。
	JUnit还提供了@BeforeAll和@AfterAll，它们在运行所有@Test前后运行.
	因为@BeforeAll和@AfterAll在所有@Test方法运行前后仅运行一次，因此，它们只能初始化静态变量
	事实上，@BeforeAll和@AfterAll也只能标注在静态方法上。
	
	注意到每次运行一个@Test方法前，JUnit首先创建一个XxxTest实例，因此，每个@Test方法内部的成员变量都是独立的，
	不能也无法把成员变量的状态从一个@Test方法带到另一个@Test方法。
	
	JUnit提供assertThrows()来期望捕获一个指定的异常。
	
	在运行测试的时候，有些时候，我们需要排出某些@Test方法，不要让它运行，这时，我们就可以给它标记一个@Disabled
	类似@Disabled这种注解就称为条件测试，JUnit根据不同的条件注解，决定是否运行当前的@Test方法。
 * @author Guo
 * 
 *
 */
public class BSTTest {
	BST<String, Integer> bst;
	
	@BeforeEach
	public void setBst() {
		this.bst = new BST<>();
		for(int i=0;i<26;i++) {
			String temp = new String((char)('a'+i)+"");
			bst.put(temp, i+1);
		}
	}
	
	@AfterEach
	public void clear() {
		this.bst=null;
	}
	
	@Test
    void testFact1() {
        assertEquals((char)('a'+3)+"", bst.select(3+2));
    }
	
	@Test
    void testFact2() {
        assertEquals((char)('a'+2)+"", bst.select(1+2));
    }
}
