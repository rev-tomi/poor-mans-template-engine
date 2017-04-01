package com.trev.pmte;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class TemplateWithDifferentQuotesTest {

	private TemplateEngine engine;
	
	@Before
	public void setUp() {
		this.engine = new TemplateEngine();
	}
	
	@Test
	public void testInlineApostroph() {
		// GIVEN
		engine.addTemplate("TemplateCall", "<Template Target \"${param}\">", "param");
		String src = "asdf${TemplateCall 'don''t do that'}ghij";
		
		// WHEN
		String target = engine.applyTemplates(src);
		
		// THEN
		assertEquals("asdf<Template Target \"don't do that\">ghij", target);
	}
	
	@Test
	public void testQuotes() {
		// GIVEN
		engine.addTemplate("TemplateCall", "<Template Target \"${param}\">", "param");
		String src = "asdf${TemplateCall \"don't do that\"}ghij";
		
		// WHEN
		String target = engine.applyTemplates(src);
		
		// THEN
		assertEquals("asdf<Template Target \"don't do that\">ghij", target);
	}
	
	@Test
	public void testInlineQuotes() {
		// GIVEN
		engine.addTemplate("TemplateCall", "<Template Target \"${param}\">", "param");
		String src = "asdf${TemplateCall \"Quotes \"\"within quotes\"\", so postmodern.\"}ghij";
		
		// WHEN
		String target = engine.applyTemplates(src);
		
		// THEN
		assertEquals("asdf<Template Target \"Quotes \"within quotes\", so postmodern.\">ghij", target);
	}
}
