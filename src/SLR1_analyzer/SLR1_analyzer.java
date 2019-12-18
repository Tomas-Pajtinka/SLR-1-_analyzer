package SLR1_analyzer;


import java.util.ArrayList;
import SLR1_automat.State;
import grammar.ContextFreeGrammar;


public class SLR1_analyzer implements interfaces.SLR1_analyzer  {
	
	private GOTO goto_table;
	private ACTION action_table;
	private ArrayList<State> states;
	private ContextFreeGrammar grammar;
	
	/*
	 * Vytvori SLR(1) analyzator (SLR(1) automat, tabulky ACTION a GOTO) podla zadanej gramatiky
	 */
	public SLR1_analyzer(ContextFreeGrammar grammar) throws Exception{
		
	}
	
	
	public boolean analyze(String input) throws Exception{
		return false;
	}
}
