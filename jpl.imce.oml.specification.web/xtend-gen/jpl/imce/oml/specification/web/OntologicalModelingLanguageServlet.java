/**
 * generated by Xtext 2.10.0
 */
package jpl.imce.oml.specification.web;

import com.google.inject.Provider;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;
import javax.servlet.annotation.WebServlet;
import jpl.imce.oml.specification.web.OntologicalModelingLanguageWebSetup;
import org.eclipse.xtext.web.servlet.XtextServlet;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;

/**
 * Deploy this class into a servlet container to enable DSL-specific services.
 */
@WebServlet(name = "XtextServices", urlPatterns = "/xtext-service/*")
@SuppressWarnings("all")
public class OntologicalModelingLanguageServlet extends XtextServlet {
  private final List<ExecutorService> executorServices = CollectionLiterals.<ExecutorService>newArrayList();
  
  public void init() {
    try {
      super.init();
      final Provider<ExecutorService> _function = new Provider<ExecutorService>() {
        public ExecutorService get() {
          ExecutorService _newCachedThreadPool = Executors.newCachedThreadPool();
          final Procedure1<ExecutorService> _function = new Procedure1<ExecutorService>() {
            public void apply(final ExecutorService it) {
              OntologicalModelingLanguageServlet.this.executorServices.add(it);
            }
          };
          return ObjectExtensions.<ExecutorService>operator_doubleArrow(_newCachedThreadPool, _function);
        }
      };
      final Provider<ExecutorService> executorServiceProvider = _function;
      OntologicalModelingLanguageWebSetup _ontologicalModelingLanguageWebSetup = new OntologicalModelingLanguageWebSetup(executorServiceProvider);
      _ontologicalModelingLanguageWebSetup.createInjectorAndDoEMFRegistration();
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  public void destroy() {
    final Consumer<ExecutorService> _function = new Consumer<ExecutorService>() {
      public void accept(final ExecutorService it) {
        it.shutdown();
      }
    };
    this.executorServices.forEach(_function);
    this.executorServices.clear();
    super.destroy();
  }
}