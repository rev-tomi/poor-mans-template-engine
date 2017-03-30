package com.trev.pmte;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

public class TemplateEngine {
	
	private final List<TemplateDefinition> templates = new ArrayList<>();

	public void addTemplate(String templateCall, String templateTarget, String... params) {
		templates.add(new TemplateDefinition(templateCall, templateTarget, params));
	}

	public String applyTemplates(String src) {
		final StringBuilder builder = new StringBuilder(src);
		for (TemplateDefinition def : templates) {
			replacePatterns(builder, def);
		}
		return builder.toString();
	}

	private void replacePatterns(final StringBuilder builder, TemplateDefinition def) {
		Matcher matcher = def.getPattern().matcher(builder);
		while (matcher.find()) {
			String target = new String(def.getTarget());
			List<String> params = def.getParams();
			for (int i = 0, size = params.size(); i < size; i++) {
				target = target.replace("${" + params.get(i) + "}", matcher.group(i + 1));
			}
			builder.replace(matcher.start(), matcher.end(), target);
		}
	}

}
