package pe.edu.sistemas.unayoe.core.transformer;

import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Interface Transformer.
 *
 * @param <X> the generic type
 * @param <Y> the generic type
 */
public interface Transformer<X,Y> {

	/**
	 * Transformer.
	 *
	 * @param input the input
	 * @return the y
	 */
	public Y transformer(final X input);
	
	/**
	 * Transformer.
	 *
	 * @param input the input
	 * @return the list
	 */
	public List<Y> transformer(final List<X> input);
}
