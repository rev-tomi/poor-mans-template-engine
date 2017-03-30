package com.trev.pmte;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;


public class SingleParamTest {

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
}
