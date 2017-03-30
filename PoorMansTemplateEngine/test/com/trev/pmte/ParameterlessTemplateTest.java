package com.trev.pmte;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class ParameterlessTemplateTest {
	
	private TemplateEngine engine;
	
	@Before
	public void setUp() {
		this.engine = new TemplateEngine();
		engine.addTemplate("TemplateCall", "<TemplateTarget>");
	}

	@Test
	public void testNoTemplate() {
		// GIVEN
		String src = "TextAsIs";
		
		// WHEN
		String target = engine.applyTemplates(src);
		
		// THEN
		assertEquals(target, src);
	}

	@Test
	public void testOneTemplate() {
		// GIVEN
		String src = "asdf\n${TemplateCall}\nghij";
		
		// WHEN
		String target = engine.applyTemplates(src);
		
		// THEN
		assertEquals(target, "asdf\n<TemplateTarget>\nghij");
	}

	@Test
	public void testTemplateTwice() {
		// GIVEN
		String src = "asdf\n${TemplateCall}\nghij\n${TemplateCall}\nasdf";
		
		// WHEN
		engine.addTemplate("OtherTemplateCall", "<OtherTemplateTarget>");
		String target = engine.applyTemplates(src);
		
		// THEN
		assertEquals(target, "asdf\n<TemplateTarget>\nghij\n<TemplateTarget>\nasdf");
	}

	@Test
	public void testTwoTemplates() {
		// GIVEN
		String src = "asdf\n${TemplateCall}\nghij\n${TemplateCall}\nasdf${OtherTemplateCall}";
		
		// WHEN
		engine.addTemplate("OtherTemplateCall", "<OtherTemplateTarget>");
		String target = engine.applyTemplates(src);
		
		// THEN
		assertEquals(target, "asdf\n<TemplateTarget>\nghij\n<TemplateTarget>\nasdf<OtherTemplateTarget>");
	}

	@Test
	public void testLongTemplates() {
		// GIVEN
		String src = "${OtherTemplateCall}\nasdf\n${OtherTemplateCall}\nghij\n${TemplateCall}\nasdf";
		
		// WHEN
		engine.addTemplate("OtherTemplateCall", "<OtherTemplateTarget 123456789 123456789 123456789 123456789>");
		String target = engine.applyTemplates(src);
		
		// THEN
		assertEquals(target, "<OtherTemplateTarget 123456789 123456789 123456789 123456789>\nasdf\n<OtherTemplateTarget 123456789 123456789 123456789 123456789>\nghij\n<TemplateTarget>\nasdf");
	}
}
