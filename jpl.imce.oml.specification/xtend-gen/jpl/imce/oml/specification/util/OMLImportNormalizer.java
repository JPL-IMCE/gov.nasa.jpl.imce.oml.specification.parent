package jpl.imce.oml.specification.util;

import com.google.common.base.Objects;
import jpl.imce.oml.specification.util.OMLQualifiedNameConverter;
import org.eclipse.xtext.naming.IQualifiedNameConverter;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.scoping.impl.ImportNormalizer;

@SuppressWarnings("all")
public class OMLImportNormalizer extends ImportNormalizer {
  private IQualifiedNameConverter qnc = new OMLQualifiedNameConverter();
  
  private String prefix;
  
  private String namespace;
  
  public OMLImportNormalizer(final QualifiedName importedNamespace, final String prefix) {
    super(importedNamespace, false, false);
    this.prefix = prefix;
    String _string = this.qnc.toString(importedNamespace);
    this.namespace = _string;
  }
  
  @Override
  public QualifiedName resolve(final QualifiedName relativeName) {
    String relative = this.qnc.toString(relativeName);
    if ((relative.startsWith("<") && relative.endsWith(">"))) {
      boolean _equals = this.namespace.equals(relative);
      if (_equals) {
        return relativeName;
      } else {
        if ((Objects.equal(this.prefix, "") && (!relative.contains(":")))) {
          int _length = relative.length();
          int _minus = (_length - 1);
          String _substring = relative.substring(1, _minus);
          relative = _substring;
          return QualifiedName.create(((("<" + this.namespace) + relative) + ">"));
        }
      }
    } else {
      boolean _equals_1 = this.prefix.equals(relative);
      if (_equals_1) {
        return this.qnc.toQualifiedName((("<" + this.namespace) + ">"));
      } else {
        boolean _contains = relative.contains(":");
        if (_contains) {
          final String[] data = relative.split(":");
          Object _get = data[0];
          boolean _equals_2 = Objects.equal(this.prefix, _get);
          if (_equals_2) {
            String _get_1 = data[1];
            String _plus = (("<" + this.namespace) + _get_1);
            String _plus_1 = (_plus + ">");
            return this.qnc.toQualifiedName(_plus_1);
          }
        } else {
          boolean _equals_3 = Objects.equal(this.prefix, "");
          if (_equals_3) {
            return this.qnc.toQualifiedName(((("<" + this.namespace) + relative) + ">"));
          }
        }
      }
    }
    return null;
  }
  
  @Override
  public QualifiedName deresolve(final QualifiedName fullyQualifiedName) {
    String qualified = this.qnc.toString(fullyQualifiedName);
    if ((qualified.startsWith("<") && qualified.endsWith(">"))) {
      int _length = qualified.length();
      int _minus = (_length - 1);
      String _substring = qualified.substring(1, _minus);
      qualified = _substring;
      boolean _startsWith = qualified.startsWith(this.namespace);
      if (_startsWith) {
        final String name = qualified.replaceFirst(this.namespace, "");
        boolean _notEquals = (!Objects.equal(this.prefix, ""));
        if (_notEquals) {
          return QualifiedName.create(((this.prefix + ":") + name));
        } else {
          return QualifiedName.create(name);
        }
      }
    }
    return null;
  }
}
