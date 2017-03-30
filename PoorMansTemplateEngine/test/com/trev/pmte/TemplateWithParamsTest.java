package com.trev.pmte;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;


public class TemplateWithParamsTest {

	private TemplateEngine engine;
	
	@Before
	public void setUp() {
		this.engine = new TemplateEngine();
	}
	
	@Test
	public void testSingleParamTemplace() {
		// GIVEN
		engine.addTemplate("TemplateCall", "<Template Target \"${param}\">", "param");
		String src = "asdf${TemplateCall 'kakukk'}ghij";
		
		// WHEN
		String target = engine.applyTemplates(src);
		
		// THEN
		assertEquals("asdf<Template Target \"kakukk\">ghij", target);
	}
	
	@Test
	public void testSingleParamTwoOccurrencesTemplace() {
		// GIVEN
		engine.addTemplate("TemplateCall", "<Template Target \"${param}\" ${param}>", "param");
		String src = "asdf${TemplateCall 'kakukk'}ghij";
		
		// WHEN
		String target = engine.applyTemplates(src);
		
		// THEN
		assertEquals("asdf<Template Target \"kakukk\" kakukk>ghij", target);
	}
	
	@Test
	public void testTwoParamsTemplace() {
		// GIVEN
		engine.addTemplate("TemplateCall", "<Template Target attr1=\"${param1}\" ${param2}=\"salala\">", "param1", "param2");
		String src = "asdf${TemplateCall 'kakukk'   'meow'}ghij";
		
		// WHEN
		String target = engine.applyTemplates(src);
		
		// THEN
		assertEquals("asdf<Template Target attr1=\"kakukk\" meow=\"salala\">ghij", target);
	}
}
