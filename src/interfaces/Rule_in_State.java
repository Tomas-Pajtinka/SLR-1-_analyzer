package interfaces;

import grammar.Rule;
import java.util.HashSet;


public interface Rule_in_State {
	
	/** Vrati mnozinu FOLLOW daneho pravilda
	 * * @return HashSet
	 **/
	public HashSet<String> get_follow();
	
	/** Vrati pravidlo gramatiky
	 * * @return Rule
	 **/
	public Rule get_rule();
	
	/** Porovna pravidlo so vstupnym pravidlom a urci ci su rovnake
	 * * @return true ak su pravidla rovnake , inak false
	 **/
	public boolean compareTo(SLR1_automat.Rule_in_State rule_in_state);
	
	/** Vrati, kolko z daneho pravidla bolo uz nacitane
	 * * @return int (ktory bude predstavovat index ocakavaneho prvku v poli)
	 **/
	public int get_pointer_index();
	
	/** Oznaci pravidlo ako spracovane (pre operaciu uzaveru)
	 **/
	public void set_processed();
	
	/** Vrati, ci je dane pravidlo uz spracovane operaciou uzaveru
	 * * @return true ak bol spracovany, inak false
	 **/
	public boolean is_processed();
	
	/** Vrati, ocakavany symbol daneho pravidla
	 * * @return String (symbol)
	 **/
	public String get_pointer_element();
	
	/** Vrati hodnotu pointeru vzysenu o jedna;
	 * * @return int
	 **/
	public int get_next_pointer();
	
	/** Zisti, ci uz sme nacitali cele pravidlo (podla pointera)
	 * * @return true ak je nacitane cele pravidlo, inak false
	 **/
	public boolean is_pointer_at_the_end();
}
