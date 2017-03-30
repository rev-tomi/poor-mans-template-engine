package com.trev.pmte;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class TemplateTest {

	@Test
	public void testSimpleTemplate() {
		// GIVEN
		String src = "asdf\n${TemplateCall}\nghij";
		
		// WHEN
		TemplateEngine engine = new TemplateEngine();
		engine.configure("TemplateCall", "<TemplateTarget>");
		String target = engine.applyTemplates(src);
		
		// THEN
		assertEquals(target, "asdf\n<TemplateTarget>\nghij");
	}
}
