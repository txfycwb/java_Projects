package designpattern;
import java.time.*;
import java.time.format.*;
public class LocalDateFactory {

	public static LocalDate fromInt(int yyyyMMdd) {
		int year = yyyyMMdd/10000;
		int month = (yyyyMMdd%10000)/100;
		int day =yyyyMMdd%100;
		LocalDate ld = LocalDate.of(year,month,day);
		return ld;
	}
	public static void main(String[] args) {
		DateTimeFormatter dtf = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).withZone(ZoneId.systemDefault());
		System.out.println(dtf.format(fromInt(19981001)));

	}

}
