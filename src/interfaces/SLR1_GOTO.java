package interfaces;

import SLR1_automat.State;

public interface SLR1_GOTO {
	
	/** Vrati stav, ktory sa ma vlozit do zasobnika
	 * * @param stav z vrchu zasobnika 
	 * * @param symbol na prechod
	 * * @return State
	 * * @throws ak nie je v tabulke zaznameny prechod na vstupny symbol
	 **/
	public State go_to(int state, String symbol)throws Exception;
}
