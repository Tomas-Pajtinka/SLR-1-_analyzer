package SLR1_automat;


import grammar.GrammarForAutomat;
import grammar.Rule;
import java.util.HashSet;



public class Rule_in_State implements interfaces.Rule_in_State{
	
	private Rule rule;
	private int pointer;
	private HashSet<String> follow;
	private boolean processed;
	private GrammarForAutomat grammar;
	
	/** Vytvori pravidlo v stave a sucasne mi urci mnozinu follow (pokial je to potrebne. to znamena, 
	 * ze bolo nacitane cele pravidlo alebo je to zaciatocne pravidlo gramatiky a chyba mu posledny symbol do rozpoznania)
	 * * @param Rule (pravidlo z gramatiky)
	 * * @param int (pointer pravidla - urcuje kolko s pravidla uz bolo nacitane)
	 * * @param gramatika
	 * * @return Pravidlo v stave
	 **/
	public Rule_in_State(Rule rule, int pointer, GrammarForAutomat grammar )throws Exception{
		this.rule = rule;
		this.pointer = pointer;
		this.grammar = grammar;
		if(is_pointer_at_the_end()){
			set_follow();
			processed = true;
		}else if(get_pointer_element().equals(grammar.getEpsilon())){
			this.pointer++;
			set_follow();
			processed = true;
		}else if(rule.getLeftSide().get(0).equals(grammar.getStartsymbol())){
			this.pointer++;
			if(is_pointer_at_the_end()){
				this.pointer--;
				follow = new HashSet<String>();
				if(grammar.getTerminals().contains(get_pointer_element())){
					follow.add(get_pointer_element());
				}else{
					follow.addAll(grammar.follow(get_pointer_element()));
				}
				this.pointer++;
				processed = true;
			}else{
				this.pointer--;
			}
		}else{
			if(grammar.getNonterminals().contains(get_pointer_element())){
				processed = false;
			}else{
				processed = true;
			}
		}
	}
	
	public HashSet<String> get_follow(){
		return follow;
	}
	
	public Rule get_rule(){
		return rule;
	}
	
	public boolean compareTo(Rule_in_State rule_in_state){
		if(this.rule.getLeftSide().equals(rule_in_state.rule.getLeftSide()) && this.rule.getRightSide().equals(rule_in_state.rule.getRightSide()) && this.pointer == rule_in_state.pointer){
			 return true;
		}else{
			return false;
		}
	}
	public int get_pointer_index(){
		return pointer;
	}
	
	public void set_processed(){
		this.processed = true;
	}
	
	public boolean is_processed(){
		return processed;
	}
	
	private void set_follow() throws Exception{
		try {
			follow = grammar.follow(rule.getLeftSide().get(0));
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public String get_pointer_element(){
		return rule.getRightSide().get(pointer);
	}
	
	public int get_next_pointer(){
		return pointer + 1;
	}
	
	public boolean is_pointer_at_the_end(){
		try{
			rule.getRightSide().get(pointer);
			return false;
		}catch(Exception e){
			return true;
		}
	}
}
