# Poor Man's Template Engine
A minimalist template engine that you can copy with few keytrokes

Sometimes you can't just download a library from the internet. Sometimes company policy won't let you use builtin template engines like XSLT. In this case you need to roll your own.

You can also manually copy the Poor Mans Template Engine. It gives you the basic template-replacement functionality with < 100 lines of code.

What does this for you?
 * An API for defining templates
 * A syntax for invoking the defined templates
 * Parameter substitutions

## The most simple case

You can create subtitutions like this:

```java
TemplateEngine engine = new TemplateEngine();
engine.addTemplate("TemplateCall", "<TemplateTarget With Long Text>");
engine.addTemplate("OtherTemplateCall", "<Other TemplateTarget With Long Text>");
```

Then your template would be a java String, something like this:
```java
String template = "" +
    "Some Text\n" + 
    "${TemplateCall}\n" + // mind the wrapping ${}
    "Some More Text\n" + 
    "${OtherTemplateCall}";
```

Finally you can run the substitutions:

```java
String replacedText = engine.applyTemplates(template);
```

This will give you the following text:
```
Some Text
<TemplateTarget With Long Text>
Some More Text
<Other TemplateTarget With Long Text>
```

## Parameters

You can specify parameters too. Just make sure you use the same name in the parameter list as in the template target definition:
```java
TemplateEngine engine = new TemplateEngine();
engine.addTemplate("TemplateCall", "<TemplateTarget ${firstParam} ${secondParam} ${not a param}>",
    "firstParam", "secondParam"); // mind the param names
```

Then you can run it as usual:
```java
String template = "" +
    "Some Text\n" + 
    "${TemplateCall 'asdf' 'ghij'}\n";
String replacedText = engine.applyTemplates(template);
```

This produces the following replaced text:
```
Some Text
<TemplateTarget asdf ghij ${not a param}>
```
You can see, you didn't specify a parameter called ```not a param``` so that doesn't get substituted.

## Quotes and apostrophes

You can use quotes too, not only apostrophes. I.e. this gives you the same replaced text:

```java
String template = "" +
    "Some Text\n" + 
    "${TemplateCall \"asdf\" \"ghij\"}\n";
String replacedText = engine.applyTemplates(template);
```

You can use the delimiter within the text. I.e. if you use apostrophes, just use two apostrophes instead of one within the parameter:
```java
String template = "" +
    "Some Text\n" + 
    "${TemplateCall 'didn''t know it worked like sql'}\n";
String replacedText = engine.applyTemplates(template);
// In replacedText you'll see: "didn't know it worked like sql"
```

Same for quotes:
```java
String template = "" +
    "Some Text\n" + 
    "${TemplateCall \"Quotes \"\"within quotes\"\", so postmodern\"}\n";
String replacedText = engine.applyTemplates(template);
// In replacedText you'll see: "Quotes "within quotes", so postmodern"
```

