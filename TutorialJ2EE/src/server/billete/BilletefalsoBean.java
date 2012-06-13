package server.billete;

import javax.ejb.Stateless;

@Stateless
public class BilletefalsoBean implements BilletefalsoRemote {

	@Override
	public boolean numeroValido(String nummer) throws BilletefalsoException {
		if (nummer == null)
			throw new BilletefalsoException("Null-Parameter");
		if (nummer.length() != 12)
			throw new BilletefalsoException(
					"Die Nummer muss 12 Zeichen lang sein");

		char[] ziffern = nummer.toUpperCase().toCharArray();
		int summe = 0;

		for (char zeichen : ziffern) {
			/**
			 * '0' bis '9' entspricht 0 bis 9
			 * 'A' bis 'Z' entspricht 1 bis 26
			 */
			if (zeichen >= '0' && zeichen <= '9')
				summe += zeichen - '0';
			else if (zeichen >= 'A' && zeichen <= 'Z')
				summe += zeichen - 'A' + 1;
			else
				throw new BilletefalsoException("Simbolo no valido " + zeichen);
		}

		int quersumme = 0;
		while (summe != 0) {
			quersumme += summe % 10;
			summe /= 10;
		}

		if (quersumme == 8)
			return true;
		else
			return false;
	}
	}

	

