package br.edu.granbery.sisestagio.teste;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class CompareDateTeste {

	public static void main(String args[]) {

		 Calendar cal = Calendar.getInstance();
		 Calendar currentcal = Calendar.getInstance();
		 cal.set(2013, Calendar.JUNE, 12);
		 currentcal.set(currentcal.get(Calendar.YEAR),
		 currentcal.get(Calendar.MONTH),
		 currentcal.get(Calendar.DAY_OF_MONTH));
		
		 if (cal.before(currentcal)) {
		 System.out.print("A data atual ("
		 + new SimpleDateFormat("dd/MM/yyyy").format(currentcal
		 .getTime()) + ") é maior que a data "
		 + new SimpleDateFormat("dd/MM/yyyy").format(cal.getTime()));
		 } else if (cal.after(currentcal)) {
		 System.out.print("A data atual("
		 + new SimpleDateFormat("dd/MM/yyyy").format(currentcal
		 .getTime()) + ") é menor que a data "
		 + new SimpleDateFormat("dd/MM/yyyy").format(cal.getTime()));
		 } else {
		 System.out.print("As duas datas são iguais.");
		 }

	}
}
