/*
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

package gov.nasa.jpl.imce.oml.specification.resolver.impl

import gov.nasa.jpl.imce.oml.specification._

case class TerminologyExtent private[impl] 
(
 override val annotationProperties: scala.collection.immutable.SortedSet[resolver.api.AnnotationProperty],
 override val bundles: scala.collection.immutable.SortedSet[resolver.api.Bundle],
 override val terminologyGraphs: scala.collection.immutable.SortedSet[resolver.api.TerminologyGraph]
)
extends resolver.api.TerminologyExtent
{

  override val hashCode
  : scala.Int
  = (annotationProperties, bundles, terminologyGraphs).##

  override def equals(other: scala.Any): scala.Boolean = other match {
	  case that: TerminologyExtent =>
	    (that canEqual this) &&
	    (this.annotationProperties == that.annotationProperties) &&
	    (this.bundles == that.bundles) &&
	    (this.terminologyGraphs == that.terminologyGraphs)

	  case _ =>
	    false
  }
}
