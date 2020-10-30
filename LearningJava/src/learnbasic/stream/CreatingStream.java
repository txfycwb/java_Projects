package learnbasic.stream;
/**
 * 学习流的多种创建模式
 * @author guo
 *
 */
import java.util.stream.*;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;
import java.util.regex.Pattern;


public class CreatingStream {
	public static <T> void show(String title, Stream<T> stream){
		final int SIZE = 10;
		List<T> firstElements = stream.limit(SIZE+1).collect(Collectors.toList());
		System.out.println(title+":");
		for(int i = 0; i < firstElements.size(); i++) {
			if(i>0) System.out.print(", ");
			if(i<SIZE) System.out.print(firstElements.get(i));
			else System.out.print("......");
		}
		System.out.println();
	}
	
	public static void main(String[] args) throws IOException {
		Path path = Paths.get("src\\learnbasic\\test_io.txt");
		var contents = new String(Files.readAllBytes(path), StandardCharsets.UTF_8);
		
		Stream<String> words = Stream.of(contents.split("\\s"));
		show("words", words);
		Stream<String> song = Stream.of("LZ", "天下", "NUMBER", "ONE", "!!!");
		show("song", song);
		Stream<String> silence = Stream.empty();
		show("silence", silence);
		
		Stream<String> echos = Stream.generate(()->"ECHO");
		show("echos", echos);
		
		Stream<Double> randoms = Stream.generate(Math::random);
		show("randoms", randoms);
		
		Stream<BigInteger> integers = Stream.iterate(BigInteger.ONE,n->n.add(BigInteger.TWO));
		show("integers", integers);
		
		Stream<String> wordsAnotherWay = Pattern.compile("\\PL+").splitAsStream(contents);
		show("wordsAnotherWay", wordsAnotherWay);
		
		try(Stream<String> lines = Files.lines(path, StandardCharsets.UTF_8)){
			show("lines", lines);
		}
		
		Iterable<Path> iterable = FileSystems.getDefault().getRootDirectories();
		Stream<Path> rootDirectories = StreamSupport.stream(iterable.spliterator(), false);
		show("rootDirectories", rootDirectories);
		
		Iterator<Path> iterator = Paths.get("src\\learnbasic").iterator();
		Stream<Path> show_iterator = StreamSupport.stream(Spliterators.spliteratorUnknownSize(iterator, Spliterator.ORDERED), false);
		show("show_iterator", show_iterator);
	}
}
