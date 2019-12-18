package interfaces;

import grammar.Rule;

public interface SLR1_ACTION {

	/** Vrati Pravidlo podla, ktoreho sa ma redukovat, pripadne ak vrati null, znaci to posun
	 * * @param stav z vrchu zasobnika 
	 * * @param symbol na vstupe 
	 * * @return Rule, pripadne null
	 * * @throws ak nie je v tabulke pre vstupny symbol evidovana akcia
	 **/
	public Rule action(int state, String symbol) throws Exception;
}
