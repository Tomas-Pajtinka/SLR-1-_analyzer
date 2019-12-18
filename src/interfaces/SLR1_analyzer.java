package interfaces;

public interface SLR1_analyzer {

	/** Analyzuje vstup
	 * * @param vstup na analyzovanie
	 * * @throws ak nastala syntakticka chyba
	 **/
	public boolean analyze(String input) throws Exception;
}
