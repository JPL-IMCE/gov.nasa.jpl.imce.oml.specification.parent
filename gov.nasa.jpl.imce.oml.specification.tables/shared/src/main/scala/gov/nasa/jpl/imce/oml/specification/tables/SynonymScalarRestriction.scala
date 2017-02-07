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

 
package gov.nasa.jpl.imce.oml.specification.tables

import scala.annotation.meta.field
import scala.scalajs.js.annotation.JSExport
import scala._
import scala.Predef._

/**
  * @param graphUUID[1,1]
  * @param uuid[1,1]
  * @param name[1,1]
  * @param restrictedRangeUUID[1,1]
  */
@JSExport
case class SynonymScalarRestriction
(
  @(JSExport @field) graphUUID: UUID,
  @(JSExport @field) uuid: UUID,
  @(JSExport @field) name: LocalName,
  @(JSExport @field) restrictedRangeUUID: UUID
) {
  override val hashCode
  : scala.Int 
  = (graphUUID, uuid, name, restrictedRangeUUID).##
  
  override def equals(other: scala.Any): scala.Boolean = other match {
  	case that: SynonymScalarRestriction =>
  	  (this.graphUUID == that.graphUUID) &&
  	  (this.uuid == that.uuid) &&
  	  (this.name == that.name) &&
  	  (this.restrictedRangeUUID == that.restrictedRangeUUID)
    case _ =>
      false
  }
  
}

@JSExport
object SynonymScalarRestrictionHelper {

  val TABLE_JSON_FILENAME 
  : scala.Predef.String 
  = "SynonymScalarRestrictions.json"
  
  implicit val w
  : upickle.default.Writer[SynonymScalarRestriction]
  = upickle.default.macroW[SynonymScalarRestriction]

  @JSExport
  def toJSON(c: SynonymScalarRestriction)
  : String
  = upickle.default.write(expr=c, indent=0)

  implicit val r
  : upickle.default.Reader[SynonymScalarRestriction]
  = upickle.default.macroR[SynonymScalarRestriction]

  @JSExport
  def fromJSON(c: String)
  : SynonymScalarRestriction
  = upickle.default.read[SynonymScalarRestriction](c)

}	