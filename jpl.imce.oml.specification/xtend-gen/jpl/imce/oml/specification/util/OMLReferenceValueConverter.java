/**
 * Copyright 2016 California Institute of Technology ("Caltech").
 * U.S. Government sponsorship acknowledged.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * License Terms
 */
package jpl.imce.oml.specification.util;

import com.google.inject.Inject;
import org.eclipse.xtext.conversion.IValueConverterService;
import org.eclipse.xtext.conversion.ValueConverterException;
import org.eclipse.xtext.conversion.impl.AbstractValueConverter;
import org.eclipse.xtext.nodemodel.INode;

@SuppressWarnings("all")
public class OMLReferenceValueConverter extends AbstractValueConverter<String> {
  @Inject
  protected IValueConverterService valueConverterService;
  
  public String getDelegateRuleName() {
    return "QNAME";
  }
  
  public String toString(final String value) {
    if ((value.startsWith("<") && value.endsWith(">"))) {
      return value;
    } else {
      String _delegateRuleName = this.getDelegateRuleName();
      return this.valueConverterService.toString(value, _delegateRuleName);
    }
  }
  
  public String toValue(final String string, final INode node) throws ValueConverterException {
    if ((string.startsWith("<") && string.endsWith(">"))) {
      return string;
    } else {
      String _delegateRuleName = this.getDelegateRuleName();
      Object _value = this.valueConverterService.toValue(string, _delegateRuleName, null);
      return ((String) _value);
    }
  }
}
