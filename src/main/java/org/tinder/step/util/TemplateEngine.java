package org.tinder.step.util;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import lombok.extern.log4j.Log4j2;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

@Log4j2
public class TemplateEngine {

  private final Configuration config;

  public TemplateEngine() throws IOException {
    this.config = new Configuration(Configuration.VERSION_2_3_28) {{
        setDirectoryForTemplateLoading(new File("./src/main/resources/templates"));
        setDefaultEncoding(String.valueOf(StandardCharsets.UTF_8));
        setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        setLogTemplateExceptions(false);
        setWrapUncheckedExceptions(true);
      }};
  }

  public static TemplateEngine folder(String root_location) throws IOException {
    return new TemplateEngine();
  }

  public void render(String templateFile, HashMap<String, Object> data, HttpServletResponse resp) throws IOException {
//    BufferedWriter bw = new BufferedWriter(new FileWriter(new File("")));
    resp.setCharacterEncoding(String.valueOf(StandardCharsets.UTF_8));
    try (PrintWriter w = resp.getWriter()) {
      // we can write do any Writer
      config.getTemplate(templateFile).process(data, w);
    } catch (TemplateException | IOException x) {
        log.error(new RuntimeException("Freemarker error", x));
        resp.sendRedirect("/login");
//      throw new RuntimeException("Freemarker error", x);
    }

  }
}
