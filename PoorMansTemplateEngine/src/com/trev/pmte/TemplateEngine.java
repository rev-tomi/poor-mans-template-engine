package com.trev.pmte;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

public class TemplateEngine {
	
	private final List<TemplateDefinition> templates = new ArrayList<>();

	public void configure(String templateCall, String templateTarget) {
		templates.add(new TemplateDefinition(templateCall, templateTarget));
	}

	public String applyTemplates(String src) {
		final StringBuilder builder = new StringBuilder(src);
		for (TemplateDefinition def : templates) {
			Matcher matcher = def.getPattern().matcher(builder);
			while (matcher.find()) {
				builder.replace(matcher.start(), matcher.end(), def.getTarget());
			}
		}
		return builder.toString();
	}

}
