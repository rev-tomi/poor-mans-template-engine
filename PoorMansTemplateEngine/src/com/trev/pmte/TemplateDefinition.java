package com.trev.pmte;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

class TemplateDefinition {

	private final Pattern pattern;
	private final String target;
	private final String[] params;
	
	public TemplateDefinition(String pattern, String target, String... params) {
		this.pattern = createPattern(pattern, params.length);
		this.target = target;
		this.params = params;
	}
	
	Pattern createPattern(String pattern, int paramsLength) {
		StringBuilder patternSrc = new StringBuilder("\\$\\{" + pattern);
		for (int i = 0; i < paramsLength; i++) {
			patternSrc.append("\\s+'((?>[^']+|'')*)'");
		}
		patternSrc.append("\\}");
		return Pattern.compile(patternSrc.toString());
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
