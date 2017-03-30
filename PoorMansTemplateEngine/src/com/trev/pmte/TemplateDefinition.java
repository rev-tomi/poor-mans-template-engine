package com.trev.pmte;

import java.util.regex.Pattern;

public class TemplateDefinition {

	private final Pattern pattern;
	private final String target;
	
	public TemplateDefinition(String pattern, String target) {
		this.pattern = Pattern.compile("\\$\\{" + pattern + "\\}");
		this.target = target;
	}

	public Pattern getPattern() {
		return pattern;
	}

	public String getTarget() {
		return target;
	}
	
	
}
