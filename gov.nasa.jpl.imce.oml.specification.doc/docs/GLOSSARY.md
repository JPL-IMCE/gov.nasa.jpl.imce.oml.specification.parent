{% include "./external-links.md" %}
# OML Glossary Summary

The vocabulary of the Ontological Modeling Language, OML, consists of 90 definitions
(36 abstract and 54 concrete).
This OML vocabulary is the basis of the Ontological Modeling Framework (OMF), which is 
a collection of multiple technology-based Application Programming Interfaces (APIs) & libraries.

- **EMF/CDO** OMF APIs and libraries based on the [Eclipse Modeling Framework] and [Connected Data Objects]

  All 90 definitions induce corresponding EMF-based APIs and libraries.
  For the 54 concrete definitions, the *EMF/CDO* APIs
  include all the 51 *Normalized* APIs, all the 52 *Functional* APIs,
  and 2 definitions uniquely intended for *EMF/CDO*.
  
- **Normalized** OMF APIs and libraries based on polyglot functional programming in Java, JavaScript and Scala

  A subset of 51 definitions from the 54 concrete definitions
  constitute the set of normalized relational database schema tables for the technology-agnostic OML tabular interchange representation.
  These definitions generate language-friendly functional programming APIs for Java, JavaScript and Scala.
  Note that Scala is the only language that can provide strong compile-time guarantees of the referential transparency of the OML functional APIs.
  For Java and JavaScript, the OML functional APIs are intended to be referentially transparent; 
  however, these languages do not provide any guarantees at compile time or runtime for preserving these properties.
  
- **Functional** OMF APIs and libraries in Scala for in-memory processing of OML tabular interchange representations

  A subset of 52 definitions from the 54 concrete definitions
  augment the polyglot functional programming OMF APIs for the in-memory processing of OMF information
  extracted from parsing the OML tabular interchange representation.

# OML Glossary of 36 Abstract Definitions {#oml-abstract-glossary}

## OML Axiom

An OML Axiom maps to a pattern of [OWL2 Axioms].

{APIs: **Normalized**, **Functional**, **EMF/CDO**}

Abstract definition with 1 generalization:
 - OML TerminologyBoxStatement

and with 2 specializations:
 - OML ScalarOneOfLiteralAxiom
 - OML TermAxiom

## OML ConceptTreeDisjunction

An OML ConceptTreeDisjunction represents the root & non-leaf nodes of a concept taxonomy:
- Root node is a RootConceptTaxonomyAxiom.
- Non-leaf nodes are AnonymousConceptTaxonomyAxioms.

{APIs: **Normalized**, **Functional**, **EMF/CDO**}

Abstract with 2 specializations:
 - OML AnonymousConceptTaxonomyAxiom
 - OML RootConceptTaxonomyAxiom

## OML ConceptualEntity

An OML ConceptualEntity is an OML Entity
that can be either abstract or concrete.
An abstract OML ConceptualEntity cannot have any instance
in a final OML DescriptionBox.
A concrete OML ConceptualEntity can be partially instantiated
as an OML ConceptualEntitySingletonInstance in any OML DescriptionBox.
If is partially instantiated if some essential OML EntityRelationship
or OML DataRelationshipFromEntity with `isIdentityCriteria=true` lacks
an OML TerminologyInstanceAssertion specifying its reference or value respectively.

{APIs: **Normalized**, **Functional**, **EMF/CDO**}

Abstract with 2 specializations:
 - OML Concept
 - OML ReifiedRelationship

## OML ConceptualEntitySingletonInstance

An OML ConceptualEntitySingletonInstance defines an instance of an OML ConceptualEntity.
An OML ConceptualEntitySingletonInstance maps to an [OWL2 NamedIndividual].
The semantics depends on the kind of OML ConceptualEntity classifier:
- If the OML ConceptualEntity is the domain for at least at least
  one OML DataRelationshipFromEntity with `isIdentityCriteria=true`,
  then the [OWL2-DL] mapping includes an [OWL2 Key Axiom]
  forcing that all distinctly named OML ConceptualEntitySingletonInstance
  must have different values for each OML DataRelationshipFromEntity with `isIdentityCriteria=true`.
- otherwise, distinctly named OML ConceptualEntitySingletonInstance
  represent semantically different instances.

{APIs: **Normalized**, **Functional**, **EMF/CDO**}

Abstract definition with 1 generalization:
 - OML SingletonInstance

and with 2 specializations:
 - OML ConceptInstance
 - OML ReifiedRelationshipInstance

## OML Context

An OML Context maps to an [OWL2-DL Ontology];
it is a kind of OML Resource that is a logical container of OML TerminologyThings
and a non-logical container of OML Annotations.

{APIs: **Normalized**, **Functional**, **EMF/CDO**}

Abstract definition with 2 generalizations:
 - OML Resource
 - OML TerminologyThing

and with 2 specializations:
 - OML DescriptionBox
 - OML TerminologyBox

## OML DataRange

An OWL DataRange corresponds to an [OWL2 DataRange] with arity=1.

{APIs: **Normalized**, **Functional**, **EMF/CDO**}

Abstract definition with 1 generalization:
 - OML Datatype

and with 2 specializations:
 - OML RestrictedDataRange
 - OML Scalar

## OML DataRelationship

An OML DataRelationship is an OML DirectedBinaryRelationshipKind
where the domain or the range is some kind of OML Datatype.

{APIs: **Normalized**, **Functional**, **EMF/CDO**}

Abstract definition with 2 generalizations:
 - OML DirectedBinaryRelationshipKind
 - OML Term

and with 4 specializations:
 - OML EntityScalarDataProperty
 - OML EntityStructuredDataProperty
 - OML ScalarDataProperty
 - OML StructuredDataProperty

## OML DataRelationshipDomain

An OML DataRelationshipDomain is an abstraction
for the domain of an OML DataRelationship

{APIs: **Normalized**, **Functional**, **EMF/CDO**}

Abstract with 2 specializations:
 - OML DataRelationshipFromEntity
 - OML DataRelationshipFromStructure

## OML DataRelationshipFromEntity

An OML DataRelationshipFromEntity is an OML DataRelationship
whose domain is an OML Entity.

{APIs: **Normalized**, **Functional**, **EMF/CDO**}

Abstract definition with 1 generalization:
 - OML DataRelationshipDomain

and with 2 specializations:
 - OML EntityScalarDataProperty
 - OML EntityStructuredDataProperty

## OML DataRelationshipFromStructure

An OML DataRelationshipFromStructure is an OML DataRelationship
whose domain is an OML Structure.

{APIs: **Normalized**, **Functional**, **EMF/CDO**}

Abstract definition with 1 generalization:
 - OML DataRelationshipDomain

and with 2 specializations:
 - OML ScalarDataProperty
 - OML StructuredDataProperty

## OML DataRelationshipRange

An OML DataRelationshipRange is an abstraction
for the range of an OML DataRelationship

{APIs: **Normalized**, **Functional**, **EMF/CDO**}

Abstract with 2 specializations:
 - OML DataRelationshipToScalar
 - OML DataRelationshipToStructure

## OML DataRelationshipToScalar

An OML DataRelationshipToScalar is an OML DataRelationship
whose range is an OML DataRange.

{APIs: **Normalized**, **Functional**, **EMF/CDO**}

Abstract definition with 1 generalization:
 - OML DataRelationshipRange

and with 2 specializations:
 - OML EntityScalarDataProperty
 - OML ScalarDataProperty

## OML DataRelationshipToStructure

An OML DataRelationshipToStructure is an OML DataRelationship
whose range is an OML Structure.

{APIs: **Normalized**, **Functional**, **EMF/CDO**}

Abstract definition with 1 generalization:
 - OML DataRelationshipRange

and with 2 specializations:
 - OML EntityStructuredDataProperty
 - OML StructuredDataProperty

## OML Datatype

An OML Datatype is a common abstraction for what should be, in principle,
[OWL2 DataRange] of arity 1 (atomic) or > 1 (tuple structure).
In practice, an OML DataType maps to the [OWL2-DL] subset; which means
that an atomic OML Datatype maps to an [OWL2 Datatype] whereas
a structured OML Datatype maps to an [OWL2 Class].

{APIs: **Normalized**, **Functional**, **EMF/CDO**}

Abstract definition with 1 generalization:
 - OML Term

and with 2 specializations:
 - OML DataRange
 - OML Structure

## OML DescriptionBoxRelationship

An OML DescriptionBoxRelationship is a directed binary relationship
from an OML DescriptionBox source to an OML Context target.

{APIs: **Normalized**, **Functional**, **EMF/CDO**}

Abstract definition with 1 generalization:
 - OML TerminologyThing

and with 2 specializations:
 - OML DescriptionBoxExtendsClosedWorldDefinitions
 - OML DescriptionBoxRefinement

## OML DirectedBinaryRelationshipKind

An OML DirectedBinaryRelationshipKind is an abstraction
for the category of OML Terms
that are relationships with arity 2

{APIs: **Normalized**, **Functional**, **EMF/CDO**}

Abstract with 2 specializations:
 - OML DataRelationship
 - OML EntityRelationship

## OML DisjointUnionOfConceptsAxiom

An OML DisjointUnionOfConceptsAxioms represents non-leaf & leaf nodes of a concept taxonomy:
- Non-leaf nodes are AnonymousConceptTaxonomyAxioms.
- Leaf nodes are SpecificDisjointConceptAxioms.

{APIs: **Normalized**, **Functional**, **EMF/CDO**}

Abstract definition with 1 generalization:
 - OML TerminologyBundleStatement

and with 2 specializations:
 - OML AnonymousConceptTaxonomyAxiom
 - OML SpecificDisjointConceptAxiom

## OML Entity

An OML Entity is an abstraction for an OML Term
that is either an OML UnaryTermKind or
an OML DirectedBinaryRelationshipKind whose subject
and range are both a kind of OML Entity.

{APIs: **Normalized**, **Functional**, **EMF/CDO**}

Abstract definition with 1 generalization:
 - OML Term

and with 3 specializations:
 - OML Aspect
 - OML Concept
 - OML ReifiedRelationship

## OML EntityRelationship

An OML EntityRelationship is a kind of OML Term that
is an OML DirectedBinaryRelationshipKind between a
domain OML Entity and a range OML Entity.

{APIs: **Normalized**, **Functional**, **EMF/CDO**}

Abstract definition with 2 generalizations:
 - OML DirectedBinaryRelationshipKind
 - OML Term

and with 2 specializations:
 - OML ReifiedRelationship
 - OML UnreifiedRelationship

## OML EntityRestrictionAxiom

An OML EntityRestrictionAxiom maps to an [OWL2 Object Property Restiction]
for an OML EntityRelationship.

{APIs: **Normalized**, **Functional**, **EMF/CDO**}

Abstract definition with 1 generalization:
 - OML TermAxiom

and with 2 specializations:
 - OML EntityExistentialRestrictionAxiom
 - OML EntityUniversalRestrictionAxiom

## OML EntityScalarDataPropertyRestrictionAxiom

An OML EntityScalarDataPropertyRestrictionAxiom maps to
some kind of OWL2 Data Property Restriction.

{APIs: **Normalized**, **Functional**, **EMF/CDO**}

Abstract definition with 1 generalization:
 - OML TermAxiom

and with 3 specializations:
 - OML EntityScalarDataPropertyExistentialRestrictionAxiom
 - OML EntityScalarDataPropertyParticularRestrictionAxiom
 - OML EntityScalarDataPropertyUniversalRestrictionAxiom

## OML Resource

An OML Resource is an abstraction for
everything in a vocabulary that is identifiable
locally by name within the vocabulary and
globally by an IRI across multiple vocabularies.
For a particular OML Resource, the constraints
between its name and its IRI depend on what kind of OML Resource it is.

{APIs: **Normalized**, **Functional**, **EMF/CDO**}

Abstract with 3 specializations:
 - OML Context
 - OML Term
 - OML TerminologyInstanceAssertion

## OML RestrictedDataRange

An OML RestrictedDataRange corresponds to an [OWL2 DataRange] defined in terms of some kind of restriction of some other OML DataRange.
Instead of arbitrary OWL2 FacetRestrictionAxioms as constructors of [OWL2 DataRange]s,
the specializations of RestrictedDataRange correspond to the allowed restrictions in OWL2-DL datatype maps.
Node that the vocabulary of XSD fundamental facets is not included in OWL2-DL, consequently,
there is no support in OML for specifying datatype restrictions involving XSD fundamental facets as well.
@see https://www.w3.org/TR/owl2-syntax/#Data_Ranges
@see https://www.w3.org/TR/owl2-syntax/#Datatype_Maps

{APIs: **Normalized**, **Functional**, **EMF/CDO**}

Abstract definition with 1 generalization:
 - OML DataRange

and with 8 specializations:
 - OML BinaryScalarRestriction
 - OML IRIScalarRestriction
 - OML NumericScalarRestriction
 - OML PlainLiteralScalarRestriction
 - OML ScalarOneOfRestriction
 - OML StringScalarRestriction
 - OML SynonymScalarRestriction
 - OML TimeScalarRestriction

## OML SingletonInstance

An OML SingletonInstance defines an instance of either an OML ConceptualEntity or of an OML Structure.

{APIs: **Normalized**, **Functional**, **EMF/CDO**}

Abstract definition with 1 generalization:
 - OML TerminologyInstanceAssertion

and with 2 specializations:
 - OML ConceptualEntitySingletonInstance
 - OML DataStructureTuple

## OML SpecializationAxiom

An OML SpecializationAxiom is a logical axiom
about a taxonomic relationship between a specific and a general OML Entity.

{APIs: **Normalized**, **Functional**, **EMF/CDO**}

Abstract definition with 1 generalization:
 - OML TermAxiom

and with 3 specializations:
 - OML AspectSpecializationAxiom
 - OML ConceptSpecializationAxiom
 - OML ReifiedRelationshipSpecializationAxiom

## OML Term

An OML Term map to the declaration of an [OWL2-DL Entity] of some kind.

{APIs: **Normalized**, **Functional**, **EMF/CDO**}

Abstract definition with 2 generalizations:
 - OML Resource
 - OML TerminologyBoxStatement

and with 4 specializations:
 - OML DataRelationship
 - OML Datatype
 - OML Entity
 - OML EntityRelationship

## OML TermAxiom

An OML TermAxiom is a logical axiom about an OML Term.

{APIs: **Normalized**, **Functional**, **EMF/CDO**}

Abstract definition with 1 generalization:
 - OML Axiom

and with 3 specializations:
 - OML EntityRestrictionAxiom
 - OML EntityScalarDataPropertyRestrictionAxiom
 - OML SpecializationAxiom

## OML TerminologyAxiom

An OML TerminologyAxiom is asserted in a TerminologyBox of some kind.

{APIs: **Normalized**, **Functional**, **EMF/CDO**}

Abstract definition with 1 generalization:
 - OML TerminologyThing

and with 2 specializations:
 - OML TerminologyBoxAxiom
 - OML TerminologyBundleAxiom

## OML TerminologyBox

An OML TerminologyBox is an OML Context for defining a domain-specific vocabulary
as a logical set of OML TerminologyBoxStatements,
possibly by reuse of other vocabularies via OML TerminologyBoxAxioms.
The semantics of an OML TerminologyBox domain-specific vocabulary is defined
by the mapping to [OWL2-DL] of the other vocabularies it reuses, if any, and
that of its OML TerminologyBoxAxioms and OML TerminologyBoxStatements
according to its OML TerminologyGraphKind.

{APIs: **Normalized**, **Functional**, **EMF/CDO**}

Abstract definition with 1 generalization:
 - OML Context

and with 2 specializations:
 - OML Bundle
 - OML TerminologyGraph

## OML TerminologyBoxAxiom

An OML TerminologyBoxAxiom is a TerminologyAxiom that asserts a logical statement about a Term.

{APIs: **Normalized**, **Functional**, **EMF/CDO**}

Abstract definition with 1 generalization:
 - OML TerminologyAxiom

and with 3 specializations:
 - OML ConceptDesignationTerminologyAxiom
 - OML TerminologyExtensionAxiom
 - OML TerminologyNestingAxiom

## OML TerminologyBoxStatement

An OML TerminologyBoxStatement is a logical axiom about an OML TerminologyThing
asserted in the context of an OML TerminologyBox.

{APIs: **Normalized**, **Functional**, **EMF/CDO**}

Abstract definition with 1 generalization:
 - OML TerminologyThing

and with 2 specializations:
 - OML Axiom
 - OML Term

## OML TerminologyBundleAxiom

An OML TerminologyBundleAxiom is a TerminologyAxiom that asserts a logical statement in the context of a Bundle.

{APIs: **Normalized**, **Functional**, **EMF/CDO**}

Abstract definition with 1 generalization:
 - OML TerminologyAxiom

and with 1 specialization:
 - OML BundledTerminologyAxiom

## OML TerminologyBundleStatement

An OML TerminologyBundleStatement is a logical axiom about an OML TerminologyThing
asserted in the context of an OML Bundle.

{APIs: **Normalized**, **Functional**, **EMF/CDO**}

Abstract definition with 1 generalization:
 - OML TerminologyThing

and with 2 specializations:
 - OML DisjointUnionOfConceptsAxiom
 - OML RootConceptTaxonomyAxiom

## OML TerminologyInstanceAssertion

An OML TerminologyInstanceAssertion is a logical OML TerminologyThing defined in an OML TerminologyDescription.

{APIs: **Normalized**, **Functional**, **EMF/CDO**}

Abstract definition with 2 generalizations:
 - OML Resource
 - OML TerminologyThing

and with 6 specializations:
 - OML ReifiedRelationshipInstanceDomain
 - OML ReifiedRelationshipInstanceRange
 - OML ScalarDataPropertyValue
 - OML SingletonInstance
 - OML StructuredDataPropertyValue
 - OML UnreifiedRelationshipInstanceTuple

## OML TerminologyThing

An OML TerminologyThing is a logical abstraction for everything
in a vocabulary that is globally identified by a UUID.

{APIs: **Normalized**, **Functional**, **EMF/CDO**}

Abstract with 6 specializations:
 - OML Context
 - OML DescriptionBoxRelationship
 - OML TerminologyAxiom
 - OML TerminologyBoxStatement
 - OML TerminologyBundleStatement
 - OML TerminologyInstanceAssertion

## OML UnaryTermKind

An OML UnaryTermKind is an abstraction for the category of OML Terms
that are relationships with arity 1

{APIs: **Normalized**, **Functional**, **EMF/CDO**}

Abstract with 4 specializations:
 - OML Aspect
 - OML Concept
 - OML Scalar
 - OML Structure

# OML Glossary of 54 Concrete Definitions {#oml-concrete-glossary}

## OML Annotation

An OML Annotation maps to an [OWL2 Annotation] and is similarly
a non-logical statement in an OML Context
associating some information as the value of an
OML AnnotationProperty for describing a subject (an OML TerminologyThing).

{APIs: **Functional**}

## OML AnnotationEntry

For the OML tabular interchange representation,
an OML AnnotationEntry (for a given OML AnnotationProperty) is a triple:
- an OML Context in which the OML AnnotationEntry appears
- an annotated OML TerminologyThing subject
- a String value as the representation of some information
  about the subject in that context.

{APIs: **Normalized**, **Functional**, **EMF/CDO**}

## OML AnnotationProperty

An OML AnnotationProperty maps to an [OWL2 AnnotationProperty]
and is similarly a non-logical property for associating some information
to any OML TerminologyThing in an OML Context.

{APIs: **Normalized**, **Functional**, **EMF/CDO**}

## OML AnnotationPropertyTable

For the OML tabular interchange representation,
an OML AnnotationPropertyTable pairs an OML AnnotationProperty key
with a set of OML AnnotationEntry values.

{APIs: **Functional**}

## OML AnnotationSubjectPropertyValue

An OML AnnotationSubjectPropertyValue is an in-memory construct
pairing an OML AnnotationProperty with a String value.

{APIs: **Normalized**, **EMF/CDO**}

## OML AnnotationSubjectTable

An OML AnnotationSubjectTable is an in-memory construct
pairing an OML TerminologyThing subject key with a set
of OML AnnotationSubjectPropertyValue tuples.

{APIs: **Normalized**, **EMF/CDO**}

## OML AnonymousConceptTaxonomyAxiom

An OML AnonymousConceptTaxonomyAxiom is an anonymous taxonomy tree of DisjointUnionOfEntityAxioms.

{APIs: **Normalized**, **Functional**, **EMF/CDO**}

Concrete definition with 2 generalizations:
 - OML ConceptTreeDisjunction
 - OML DisjointUnionOfConceptsAxiom

## OML Aspect

An OML Aspect is a kind of OML Entity of OML UnaryTermKind.
It is intended to be used as a specialization parent
for one or more OML ConceptualEntity.

{APIs: **Normalized**, **Functional**, **EMF/CDO**}

Concrete definition with 2 generalizations:
 - OML Entity
 - OML UnaryTermKind

## OML AspectSpecializationAxiom

An OML AspectSpecializationAxiom is a logical axiom
about a taxonomic relationship between a specific OML Aspect
and a general OML Entity.

{APIs: **Normalized**, **Functional**, **EMF/CDO**}

Concrete definition with 1 generalization:
 - OML SpecializationAxiom

## OML BinaryScalarRestriction

An OML BinaryScalarRestriction is a data range that specifies how one binary scalar adds facet restrictions to another.
Applicable when the restricted scalar represents [OWL2 Binary Data].

The restricted scalar must be directly or indirectly a restriction of:
- [xsd:base64Binary]
- [xsd:hexBinary]

Facets:
- [xsd:length]
- [xsd:minLength]
- [xsd:maxLength]

{APIs: **Normalized**, **Functional**, **EMF/CDO**}

Concrete definition with 1 generalization:
 - OML RestrictedDataRange

## OML Bundle

An OML Bundle is an OML TerminologyBox that is
an acyclic logical aggregate of other OML TerminologyBoxes
and that logically assert OML TerminologyBundleStatements.

{APIs: **Normalized**, **Functional**, **EMF/CDO**}

Concrete definition with 1 generalization:
 - OML TerminologyBox

## OML BundledTerminologyAxiom

An OML BundledTerminologyAxiom identifies an OML TerminologyBox that an OML Bundle aggregates.
An OML BundledTerminologyAxiom allows an OML Bundle to
make references (via OML TerminologyStatements) to OML TerminologyThings
within the transitive closure of a bundledTerminology.

{APIs: **Normalized**, **Functional**, **EMF/CDO**}

Concrete definition with 1 generalization:
 - OML TerminologyBundleAxiom

## OML Concept

An OML Concept is an OML ConceptualEntity of OML UnaryTermKind.

{APIs: **Normalized**, **Functional**, **EMF/CDO**}

Concrete definition with 3 generalizations:
 - OML ConceptualEntity
 - OML Entity
 - OML UnaryTermKind

## OML ConceptDesignationTerminologyAxiom

An OML ConceptDesignationTerminologyAxiom establishes
a relationship from a source OML TerminologyBox
where a designated OML Concept is defined to
a target OML TerminologyGraph in which the internal
structure of the designated OML Concept can be defined.

{APIs: **Normalized**, **Functional**, **EMF/CDO**}

Concrete definition with 1 generalization:
 - OML TerminologyBoxAxiom

## OML ConceptInstance

An OML ConceptInstance is an OML ConceptualEntitySingletonInstance classified by an OML Concept.

{APIs: **Normalized**, **Functional**, **EMF/CDO**}

Concrete definition with 1 generalization:
 - OML ConceptualEntitySingletonInstance

## OML ConceptSpecializationAxiom

An OML ConceptSpecializationAxiom is a logical axiom
about a taxonomic relationship between a specific and general
OML Concept.

{APIs: **Normalized**, **Functional**, **EMF/CDO**}

Concrete definition with 1 generalization:
 - OML SpecializationAxiom

## OML DataStructureTuple

An OML DataStructureTuple defines an structured tuple instance of an OML Structure.

{APIs: **Normalized**, **Functional**, **EMF/CDO**}

Concrete definition with 1 generalization:
 - OML SingletonInstance

## OML DescriptionBox

An OML DescriptionBox maps to an [OWL2-DL Ontology]
about [OWL2-DL NamedIndividuals] mapped from OML TerminologyInstanceAssertions.

{APIs: **Normalized**, **Functional**, **EMF/CDO**}

Concrete definition with 1 generalization:
 - OML Context

## OML DescriptionBoxExtendsClosedWorldDefinitions

An OML DescriptionBoxExtendsClosedWorldDefinitions specifies
an OML DescriptionBoxRelationship from an OML DescriptionBox
to an OML TerminologyBox such that an OML SingletonInstance defined in the
former can be an instance of an OML Term defined in the latter.

{APIs: **Normalized**, **Functional**, **EMF/CDO**}

Concrete definition with 1 generalization:
 - OML DescriptionBoxRelationship

## OML DescriptionBoxRefinement

An OML DescriptionBoxRefinement is an OML DescriptionBoxRelationship
from an OML DescriptionBox to another OML DescriptionBox where
the former refines the descriptions of the latter.

{APIs: **Normalized**, **Functional**, **EMF/CDO**}

Concrete definition with 1 generalization:
 - OML DescriptionBoxRelationship

## OML EntityExistentialRestrictionAxiom

An OML EntityExistentialRestrictionAxiom maps
to an [OWL2 Object Property Existential Restriction].

{APIs: **Normalized**, **Functional**, **EMF/CDO**}

Concrete definition with 1 generalization:
 - OML EntityRestrictionAxiom

## OML EntityScalarDataProperty

An OML EntityScalarDataProperty is an OML DataRelationship
whose domain is an OML Entity and range is an OML DataRange.

{APIs: **Normalized**, **Functional**, **EMF/CDO**}

Concrete definition with 3 generalizations:
 - OML DataRelationship
 - OML DataRelationshipFromEntity
 - OML DataRelationshipToScalar

## OML EntityScalarDataPropertyExistentialRestrictionAxiom

An OML EntityScalarDataPropertyExistentialRestrictionAxiom maps to an
OWL2 DataSomeValuesFrom restriction (the range must be explicitly defined as a Scalar datatype)

{APIs: **Normalized**, **Functional**, **EMF/CDO**}

Concrete definition with 1 generalization:
 - OML EntityScalarDataPropertyRestrictionAxiom

## OML EntityScalarDataPropertyParticularRestrictionAxiom

*
An OML EntityScalarDataPropertyParticularRestrictionAxiom maps to an OWL2 DataHasValue restriction.

{APIs: **Normalized**, **Functional**, **EMF/CDO**}

Concrete definition with 1 generalization:
 - OML EntityScalarDataPropertyRestrictionAxiom

## OML EntityScalarDataPropertyUniversalRestrictionAxiom

An OML EntityScalarDataPropertyUniversalRestrictionAxiom maps to an
OWL2 DataAllValuesFrom (the range must be explicitly defined as a Scalar datatype)

{APIs: **Normalized**, **Functional**, **EMF/CDO**}

Concrete definition with 1 generalization:
 - OML EntityScalarDataPropertyRestrictionAxiom

## OML EntityStructuredDataProperty

An OML EntityStructuredDataProperty is an OML DataRelationship
whose domain is an OML Entity and range is an OML Structure.

{APIs: **Normalized**, **Functional**, **EMF/CDO**}

Concrete definition with 3 generalizations:
 - OML DataRelationship
 - OML DataRelationshipFromEntity
 - OML DataRelationshipToStructure

## OML EntityUniversalRestrictionAxiom

An OML EntityUniversalRestrictionAxiom maps
to an [OWL2 Object Property Universal Restriction].

{APIs: **Normalized**, **Functional**, **EMF/CDO**}

Concrete definition with 1 generalization:
 - OML EntityRestrictionAxiom

## OML IRIScalarRestriction

An OML IRIScalarRestriction is a data range that specifies how one IRI scalar adds facet restrictions to another.
Applicable when the restricted scalar represents an [OWL2 IRI].

The restricted scalar must be directly or indirectly a restriction of:
- [xsd:anyURI]

Facets:
- [xsd:length]
- [xsd:minLength]
- [xsd:maxLength]
- [xsd:pattern]

{APIs: **Normalized**, **Functional**, **EMF/CDO**}

Concrete definition with 1 generalization:
 - OML RestrictedDataRange

## OML NumericScalarRestriction

An OML NumericScalarRestriction is a data range that specifies how one numeric scalar range adds facet restrictions to another.
Applies when the restricted scalar represents [OWL2 Real Numbers, Decimal Numbers and Integers] or [OWL2 Floating-Point Numbers].

The restricted scalar must be directly or indirectly a restriction of:
- [owl:real]
- [owl:rational]
- [xsd:decimal]
- [xsd:integer]
- [xsd:nonNegativeInteger]
- [xsd:nonPositiveInteger]
- [xsd:positiveInteger]
- [xsd:negativeInteger]
- [xsd:long]
- [xsd:int]
- [xsd:short]
- [xsd:byte]
- [xsd:unsignedLong]
- [xsd:unsignedInt]
- [xsd:unsignedShort]
- [xsd:unsignedByte]
- [xsd:double]
- [xsd:float]

Facets:
- [xsd:minInclusive]
- [xsd:maxInclusive]
- [xsd:minExclusive]
- [xsd:maxExclusive]

{APIs: **Normalized**, **Functional**, **EMF/CDO**}

Concrete definition with 1 generalization:
 - OML RestrictedDataRange

## OML PlainLiteralScalarRestriction

An OML PlainLiteralScalarRestriction is a data range that specifies how one plain literal scalar adds facet restrictions to another.
Applicable when the restricted scalar represents [rdf:PlainLiteral].

The restricted scalar must be directly or indirectly a restriction of:
- [rdf:PlainLiteral]

Facets:
- [xsd:langRange]
- [xsd:length]
- [xsd:minLength]
- [xsd:maxLength]
- [xsd:pattern]

{APIs: **Normalized**, **Functional**, **EMF/CDO**}

Concrete definition with 1 generalization:
 - OML RestrictedDataRange

## OML ReifiedRelationship

An OML ReifiedRelationship is an OML ConceptualEntity and a kind of OML EntityRelationship
where an instance has an intrinsic identity. This means that
an OML ReifiedRelationship can be involved as the domain or the
range of another OML EntityRelationship as well as the
domain of an OML DataRelationshipFromEntity.

{APIs: **Normalized**, **Functional**, **EMF/CDO**}

Concrete definition with 3 generalizations:
 - OML ConceptualEntity
 - OML Entity
 - OML EntityRelationship

## OML ReifiedRelationshipInstance

An OML ReifiedRelationshipInstance is an OML ConceptualEntitySingletonInstance  classified by an OML ReifiedRelationship.

{APIs: **Normalized**, **Functional**, **EMF/CDO**}

Concrete definition with 1 generalization:
 - OML ConceptualEntitySingletonInstance

## OML ReifiedRelationshipInstanceDomain

An OML ReifiedRelationshipInstanceDomain specifies which OML ConceptualEntitySingletonInstance
plays the role of the domain for an OML ReifiedRelationshipInstance.
An OML ReifiedRelationshipInstanceDomain has no intrinsic identity; instead,
an OML ReifiedRelationshipInstanceDomain is semantically equivalent
to another OML ReifiedRelationshipInstanceDomain referencing the same property and domain.

{APIs: **Normalized**, **Functional**, **EMF/CDO**}

Concrete definition with 1 generalization:
 - OML TerminologyInstanceAssertion

## OML ReifiedRelationshipInstanceRange

An OML ReifiedRelationshipInstanceRange specifies which OML ConceptualEntitySingletonInstance
plays the role of the range for an OML ReifiedRelationshipInstance.
An OML ReifiedRelationshipInstanceRange has no intrinsic identity; instead,
an OML ReifiedRelationshipInstanceRange is semantically equivalent
to another OML ReifiedRelationshipInstanceRange referencing the same property and range.

{APIs: **Normalized**, **Functional**, **EMF/CDO**}

Concrete definition with 1 generalization:
 - OML TerminologyInstanceAssertion

## OML ReifiedRelationshipSpecializationAxiom

An OML ReifiedRelationshipSpecializationAxiom is a logical axiom
about a taxonomic relationship between a specific and general
OML ReifiedRelationship.

{APIs: **Normalized**, **Functional**, **EMF/CDO**}

Concrete definition with 1 generalization:
 - OML SpecializationAxiom

## OML RootConceptTaxonomyAxiom

An OML RootConceptTaxonomyAxiom asserts that, in the context of a Bundle, a particular Entity
is the root of a taxonomy of specializations of that Entity.

{APIs: **Normalized**, **Functional**, **EMF/CDO**}

Concrete definition with 2 generalizations:
 - OML ConceptTreeDisjunction
 - OML TerminologyBundleStatement

## OML Scalar

An OML Scalar corresponds to an OWL2 Declaration of a Datatype with arity=1.

{APIs: **Normalized**, **Functional**, **EMF/CDO**}

Concrete definition with 2 generalizations:
 - OML DataRange
 - OML UnaryTermKind

## OML ScalarDataProperty

An OML ScalarDataProperty is an OML DataRelationship
whose domain is an OML Structure and range is an OML DataRange.

{APIs: **Normalized**, **Functional**, **EMF/CDO**}

Concrete definition with 3 generalizations:
 - OML DataRelationship
 - OML DataRelationshipFromStructure
 - OML DataRelationshipToScalar

## OML ScalarDataPropertyValue

An OML ScalarDataPropertyValue defines a tuple for representing the atomic String value
of an OML DataRelationshipToScalar for a particular OML SingletonInstance.

{APIs: **Normalized**, **Functional**, **EMF/CDO**}

Concrete definition with 1 generalization:
 - OML TerminologyInstanceAssertion

## OML ScalarOneOfLiteralAxiom

An OML ScalarOneOfLiteralAxiom specifies a literal in a ScalarOneOfRestriction data range.

{APIs: **Normalized**, **Functional**, **EMF/CDO**}

Concrete definition with 1 generalization:
 - OML Axiom

## OML ScalarOneOfRestriction

An OML ScalarOneOfRestriction is a data range that specifies how a scalar is a restricted set of literal values of another.
Semantics: OWL2 DataOneOf

{APIs: **Normalized**, **Functional**, **EMF/CDO**}

Concrete definition with 1 generalization:
 - OML RestrictedDataRange

## OML SpecificDisjointConceptAxiom

An OML SpecificDisjointConceptAxiom specifies a leaf in a taxonomy tree.

{APIs: **Normalized**, **Functional**, **EMF/CDO**}

Concrete definition with 1 generalization:
 - OML DisjointUnionOfConceptsAxiom

## OML StringScalarRestriction

An OML StringScalarRestriction is a data range that specifies how one string scalar adds facet restrictions to another.
Applicable when the restricted scalar represents [OWL2 Strings].

The restricted scalar must be directly or indirectly a restriction of:
- [xsd:string]
- [xsd:normalizedString]
- [xsd:token]
- [xsd:language]
- [xsd:Name]
- [xsd:NCName]
- [xsd:NMTOKEN]

Facets:
- [xsd:length]
- [xsd:minLength]
- [xsd:maxLength]
- [xsd:pattern]

{APIs: **Normalized**, **Functional**, **EMF/CDO**}

Concrete definition with 1 generalization:
 - OML RestrictedDataRange

## OML Structure

An OML Structure would correspond to an OWL2 Declaration of a Datatype with arity > 1.
However, since OWL2 Datatypes in the [OWL2-DL] are restricted to have arity=1,
the OML mapping to [OWL2-DL] involves a pattern-based usage of an [OWL2-DL Class] to represent an OML Structure.
The arity corresponds to the cardinality of the set of ScalarDataProperty & StructuredDataProperty
relationships whose domain is this structure.

{APIs: **Normalized**, **Functional**, **EMF/CDO**}

Concrete definition with 2 generalizations:
 - OML Datatype
 - OML UnaryTermKind

## OML StructuredDataProperty

An OML StructuredDataProperty is an OML DataRelationship
whose domain is an OML Structure and range is an OML Structure.

{APIs: **Normalized**, **Functional**, **EMF/CDO**}

Concrete definition with 3 generalizations:
 - OML DataRelationship
 - OML DataRelationshipFromStructure
 - OML DataRelationshipToStructure

## OML StructuredDataPropertyValue

An OML StructuredDataPropertyValue defines a tuple for representing the structured tuple value
of an OML DataRelationshipToStructure for a particular OML SingletonInstance.

{APIs: **Normalized**, **Functional**, **EMF/CDO**}

Concrete definition with 1 generalization:
 - OML TerminologyInstanceAssertion

## OML SynonymScalarRestriction

An OML SynonymScalarRestriction is a data range that is defined as a synonym for another (i.e. the restrictedRange).

{APIs: **Normalized**, **Functional**, **EMF/CDO**}

Concrete definition with 1 generalization:
 - OML RestrictedDataRange

## OML TerminologyExtensionAxiom

An OML TerminologyExtensionAxiom allows an extendingTerminology to
make references (via TerminologyStatements) to TerminologyThings
within the transitive closure of the extendedTerminlogy.

{APIs: **Normalized**, **Functional**, **EMF/CDO**}

Concrete definition with 1 generalization:
 - OML TerminologyBoxAxiom

## OML TerminologyExtent

An OML TerminologyExtent defines an in-memory tuple
about each OML Context involved in modeling and reasoning about domain-specific
vocabularies and systems descriptions using such vocabularies:
- An OML TerminologyGraph for defining a vocabulary about a domain or a description of a system in a domain;
- An OML Bundle for aggregating OML TerminologyBoxes for as modular [OWL2-DL Ontologies] for monotonic refinement and reasoning;
- An OML DescriptionBox for describing actual systems according to one or more domain-specific OML TerminologyBox vocabularies.

{APIs: **Functional**}

## OML TerminologyGraph

An OML TerminologyGraph is an OML TerminologyBox with no statements our axioms involving OML Bundles.

{APIs: **Normalized**, **Functional**, **EMF/CDO**}

Concrete definition with 1 generalization:
 - OML TerminologyBox

## OML TerminologyNestingAxiom

An OML TerminologyNestingAxiom provides support for relating
a white-box OML TerminologyGraph as a nested terminology
providing details about a black-box OML Concept defined
in a nesting OML TerminologyBox.

{APIs: **Normalized**, **Functional**, **EMF/CDO**}

Concrete definition with 1 generalization:
 - OML TerminologyBoxAxiom

## OML TimeScalarRestriction

An OML TimeScalarRestriction is a data range that specifies how one time scalar adds facet restrictions to another.
Applicable when the restricted scalar represents [OWL2 Time Instants].

The restricted scalar must be directly or indirectly a restriction of:
- [xsd:dateTime]
- [xsd:dateTimeStamp]

Facets:
- [xsd:minInclusive]
- [xsd:maxInclusive]
- [xsd:minExclusive]
- [xsd:maxExclusive]

{APIs: **Normalized**, **Functional**, **EMF/CDO**}

Concrete definition with 1 generalization:
 - OML RestrictedDataRange

## OML UnreifiedRelationship

An OML UnreifiedRelationship is a kind of OML EntityRelationship
where an instance has no intrinsic identity but rather structural
equivalence semantics as a tuple of references. This means
that an OML UnreifiedRelationship cannot be involved as the domain
or range of any kind of OML DirectedBinaryRelationshipKind.

{APIs: **Normalized**, **Functional**, **EMF/CDO**}

Concrete definition with 1 generalization:
 - OML EntityRelationship

## OML UnreifiedRelationshipInstanceTuple

An OML UnreifiedRelationshipInstanceTuple specifies a triple involving
a reference to an OML ConceptualEntitySingletonInstance playing the role of the domain
of an OML UnreifiedRelationship whose range is played by a reference to an OML ConceptualEntitySingletonInstance.
An OML UnreifiedRelationshipInstanceTuple has no intrinsic identity; instead,
an OML UnreifiedRelationshipInstanceTuple is semantically equivalent
to another OML UnreifiedRelationshipInstanceTuple referencing the same domain, property and range.

{APIs: **Normalized**, **Functional**, **EMF/CDO**}

Concrete definition with 1 generalization:
 - OML TerminologyInstanceAssertion