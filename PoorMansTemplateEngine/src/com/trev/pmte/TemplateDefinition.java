package com.trev.pmte;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

class TemplateDefinition {

	private final Pattern pattern;
	private final String target;
	private final String[] params;
	
	public TemplateDefinition(Pattern pattern, String target, String... params) {
		this.pattern = pattern;
		this.target = target;
		this.params = params;
	}

	public Pattern getPattern() {
		return pattern;
	}

	public String getTarget() {
		return target;
	}
	
	public List<String> getParams() {
		return Arrays.asList(params);
	}
	
	
}
