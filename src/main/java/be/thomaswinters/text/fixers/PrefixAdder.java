package be.thomaswinters.text.fixers;

public class PrefixAdder implements ISentenceFixer {

	public final String prefix;

	public PrefixAdder(String prefix) {
		this.prefix = prefix;
	}

	@Override
	public String fix(String text) {
		return prefix + text;
	}
}
