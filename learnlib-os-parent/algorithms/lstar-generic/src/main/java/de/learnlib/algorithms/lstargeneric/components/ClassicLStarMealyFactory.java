package de.learnlib.algorithms.lstargeneric.components;

import java.util.Collections;
import java.util.List;

import net.automatalib.words.Alphabet;
import net.automatalib.words.Word;
import de.learnlib.algorithms.lstargeneric.ce.ClassicLStarCEXHandler;
import de.learnlib.algorithms.lstargeneric.ce.ObservationTableCEXHandler;
import de.learnlib.algorithms.lstargeneric.closing.CloseFirstStrategy;
import de.learnlib.algorithms.lstargeneric.closing.ClosingStrategy;
import de.learnlib.algorithms.lstargeneric.mealy.ClassicLStarMealy;
import de.learnlib.api.LearningAlgorithm;
import de.learnlib.api.MembershipOracle;
import de.learnlib.components.LLComponent;
import de.learnlib.components.LLComponentFactory;
import de.learnlib.components.LLComponentParameter;

@LLComponent(name = "ClassicLStarMealy", type = LearningAlgorithm.class)
public class ClassicLStarMealyFactory<I,O> implements LLComponentFactory<ClassicLStarMealy<I, O>> {
	
	private Alphabet<I> alphabet;
	private MembershipOracle<I, Word<O>> oracle;
	private List<Word<I>> initialSuffixes = Collections.emptyList();
	private ObservationTableCEXHandler<I, O> cexHandler = ClassicLStarCEXHandler.getInstance();
	private ClosingStrategy<I, O> closingStrategy = CloseFirstStrategy.getInstance();

	
	@LLComponentParameter(name = "alphabet", required = true)
	public void setAlphabet(Alphabet<I> alphabet) {
		this.alphabet = alphabet;
	}
	
	@LLComponentParameter(name = "oracle", required = true)
	public void setOracle(MembershipOracle<I,Word<O>> oracle) {
		this.oracle = oracle;
	}
	
	@LLComponentParameter(name = "initialSuffixes")
	public void setInitialSuffix(List<Word<I>> initialSuffixes) {
		this.initialSuffixes = initialSuffixes;
	}
	
	@LLComponentParameter(name = "cexHandler")
	public void setCEXHandler(ObservationTableCEXHandler<I, O> cexHandler) {
		this.cexHandler = cexHandler;
	}
	
	@LLComponentParameter(name = "closingStrategy")
	public void setClosingStrategy(ClosingStrategy<I,O> closingStrategy) {
		this.closingStrategy = closingStrategy;
	}
	
	@Override
	public ClassicLStarMealy<I, O> instantiate() {
		return ClassicLStarMealy.createForWordOracle(alphabet, oracle, initialSuffixes, cexHandler, closingStrategy);
	}

}
