package com.trev.pmte;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TemplateEngine {
	
	private final List<TemplateDefinition> templates = new ArrayList<>();

	public void addTemplate(String templateCall, String templateTarget, String... params) {
		final Pattern pattern = createPattern(templateCall, params.length);
		templates.add(new TemplateDefinition(pattern, templateTarget, params));
	}
	
	private Pattern createPattern(String templateName, int paramsLength) {
		final StringBuilder patternSrc = new StringBuilder("\\$\\{\\s*" + templateName);
		for (int i = 0; i < paramsLength; i++) {
			patternSrc.append("\\s+(?:"). // spaces, then an alternative
				append("'((?>[^']+|'')*)'|"). // 1st alternative: apostrophe is the delimiter
				append("\"((?>[^\"]+|\"\")*)\")"); // 2nd alternative: quote is the delimiter
		}
		patternSrc.append("\\s*\\}");
		return Pattern.compile(patternSrc.toString());
	}

	public String applyTemplates(String src) {
		final StringBuilder builder = new StringBuilder(src);
		for (final TemplateDefinition def : templates) {
			replacePatterns(builder, def);
		}
		return builder.toString();
	}

	private void replacePatterns(final StringBuilder builder, TemplateDefinition def) {
		Matcher matcher = def.getPattern().matcher(builder);
		while (matcher.find()) {
			final String target = createTemplateTarget(def, matcher);
			builder.replace(matcher.start(), matcher.end(), target);
			matcher.region(matcher.regionStart() + target.length(), builder.length());
		}
	}

	private String createTemplateTarget(TemplateDefinition def, Matcher matcher) {
		String target = new String(def.getTarget());
		final List<String> params = def.getParams();
		for (int paramNum = 0, size = params.size(); paramNum < size; paramNum++) {
			final int apostrophGroupNum = 2 * paramNum + 1;
			final boolean isApostrophGroup = matcher.group(apostrophGroupNum) != null;
			final int groupNum = isApostrophGroup ? apostrophGroupNum : apostrophGroupNum + 1;
			final String quote = isApostrophGroup ? "'" : "\"";
 			target = target.replace("${" + params.get(paramNum) + "}", matcher.group(groupNum).replace(quote + quote, quote));
		}
		return target;
	}

}
