{% include "./external-links.md" %}

# How to Read this Specification{how-to-read]

## Specification format

This document specifies the Ontological Modeling Language, OML, whose definitions are grouped into structural clauses.
Since OML emphasizes modularity, each clause describes definitions pertaining to common modules or to a specific kind of module.

Since OML is closely related to the [OWL 2 Web Ontology Language], the organization of this document is intended
to facilitate cross-referencing with the corresponding specifications. Structurally, each definition in OML corresponds
to a structural pattern of ontologies, axioms and annotations in [OWL2-DL] with [SWRL] rules where the semantics
of that definition is precisely that of its corresponding pattern of [OWL2-DL] with [SWRL] rules.

Where it is appropriate, this document includes cross-references to corresponding modeling specifications from the [Object Management Group];
in particular, the [OMG Unified Modeling Language] and the [OMG Systems Modeling Language].

This specification uses [Eclipse Xcore] as a metalanguage for specifying the OML.
This enables illustrating OML definitions using UML-like class diagrams.
Although the colorful notation of the [Eclipse Xcore] class diagrams is different than that
of the typical black and white UML class diagrams used in other specifications,
the underlying abstract syntax of the OML metamodel is fully compliant with [OMG Meta-Object Facility].

## Functional Metamodeling

In this specification document, Functional Metamodeling refers to a refinement of the
[OMF MOF 2.5]-based metamodeling techniques applied to the [OMG UML 2.4] and [OMG UML 2.5] metamodels
 designed to ensure the following strong guarantees:

- A Functional Metamodel deterministically maps to an object-oriented metamodel API in the Eclipse Modeling Framework (EMF).
- A Functional Metamodel deterministically maps to a pure functional API for representing instance models using immutable data structures only.  
- A Functional Metamodel deterministically induces a unique 5-th normal-form relational database schema (called a Functional Schema).
  The Functional Schema deterministically ensures a strong guarantee that every well-formed instance of the Functional Metamodel has
  a syntactically unique serialization as sorted tuple rows for each table defined in the Functional Schema.
- The three views of a Functional Metamodel; the OO API, the pure functional API and the Functional Schema are representationally equivalent to each other.
  This means that a well-formed instance model of a Functional Metamodel can be created in any of the 3 views and converted to any other view
  without loss of information and converted back, thereby resulting in an instance model that is structurally equivalent to the original.

These strong guarantees follow from adopting the well-formedness constraints defined
for CMOF metamodels in [OMG MOF 2.5] augmented with the following well-formedness constraints:

### Commitment to identity

- No DataTypes other than the following PrimitiveTypes: String, Boolean, Integer, Unlimited Natural.

- Any attribute property typed by a PrimitiveType must be essential (i.e., its multiplicity must be `[1..1]`)

- Any reference property that is not an association end and that is typed by a metaclass must be essential (i.e., its multiplicity must be `[1..1]`)

- Any binary association must be a composition of `[0..*]` instances of the target contained metaclass exclusively 
  owned by an instance of the source container metaclass. Furthermore, the source metaclass must be the only metaclass 
  that can compositinally own instances of the target metaclass. These constraints imply that the multiplicity of 
  the source container end property must be `[1..1]` instead of `[0..1]` because every instance of the target contained
  metaclass must be owned by 1 instance of the source container metaclass.

- Each metaclass must reflect one of the following purposes:

  | Kind | Purpose |
  |------|---------|
  | Entity | An identifiable entity in the domain of the metamodel |
  | Value | A tuple of essential attribute or reference properties ordered by property name |
  | Extent | An in-memory container representing the tree of all entities and values involved in a model instance of the metamodel |

- Each instance of an entity metaclass must be globally identifiable via a deterministic version-5 UUID.
This UUID is derived from a namespace string constructed from identifying information available from the instance only according to the following:

  | Identity commitment for the metaclass | Identifying information |
  |---------------------------------------|------------------------------|
  | Entity metaclass with intrinsic identity | Values of each `identity criteria` essential attribute property typed by a PrimitiveType |
  | Entity metaclass with extrinsic identity | UUID of the instances of each essential reference property typed by an Entity metaclass |

- The organization of a Functional Metamodel in one or more packages has no logical significance except 
  that there must be one package that, directly or indirectly, imports all the logical contents of a Functional metamodel.
  
- An Extent metaclass cannot have any essential property of any kind. It can only be the source of binary composite associations
  to Entity or Value metaclasses.
  
- The shape of an Extent metaclass is a tree of Entity and Value metaclasses constructed inductively as follows:

  	- The root of the tree is the Extent metaclass
  	- The nodes of the tree correspond to all Entity and Value metaclasses reachable from the root via tree edges only.
  	- Each binary composite association from a source container metaclass node 
  	  to a target contained metaclass node induces a tree edge from the source node to the target node.
  	- Each essential property defined in an Entity or Value metaclass node induces 
  	  a cross-reference edge from that node to a node corresponding to the type of the essential property.
  	  An essential property is ill-formed if it is typed by a metaclass node that is not reachable from the root via tree edges only.
  
### What is a model instance of a Functional Metamodel?

An instance of an Extent metaclass is a model of a Functional Metamodel in the sense that such an instance
represents a possible instantiation of the shape of the Extent metaclass.

### Induced 5-th normal form relational database schema

The well-formedness restrictions for a Functional Metamodel ensure that there is a lossless mapping of all information
pertaining to the shape of an Extent to a corresponding 5-th normal form relational database schema.
This mapping is inductively defined according to all the metaclasses involved in the shape (Entity and Value),
all the binary composite associations and all the essential properties (reference and attribute) as follows:

all the information in a well-formed instance model
is completely reflected in the information represented in a Functional Schema unambiguously derived from the Functional Metamodel
as a 5-th normal-form relational database schema based on the following correspondence mapping:

- An abstract entity metaclass has no corresponding schema table.
- A concrete entity metaclass maps to a corresponding schema table as follows:

  - There is a single primary UUID key column.
  - Each essential attribute property (owned or inherited) typed by a PrimitiveType maps to a data column.
  - Each essential reference property (owned or inherited) typed by another entity metaclass maps to a corresponding foreign UUID key column.
  - For binary associations, the source container end property maps to a corresponding foreign UUID key column as if it were owned by the target contained metaclass
    (per the above restrictions, its multiplicity is exactly `[1..1]`); the target containment end property is not mapped.
    
- A value metaclass maps like a concrete entity metaclass except that it does not have a primary UUID key column. 

- An extent metaclass is not mapped (it is logically equivalent to the set of all schema tables)

The syntactic unique serialization guarantee follows from the sorting constraints for
the derived Functional Schema and from the fact that all essential information in the Functional Metamodel
is completely representable with only the values of all essential properties (i.e., with `[1..1]` multiplicity).
A non-essential property must be, per the above constraints, a composite target end of a binary association
whose opposite must be an essential source container end property, which is serialized. Therefore, the information
from the non-essential composite target end property can be reconstructed from the values of the serialized
essential source container end properties across all instances of the target metaclass.

The following sorting constraints ensure a unique serialization for a well-formed instance of the Functional Metamodel
as sorted tuple rows of the derived Functional Schema:

- The tuple rows for an entity schema table are sorted by the table's primary UUID key only in ascending order.

- The tuple rows for a value schema table are sorted in ascending order of the values of each column
  (i.e., foreign UUID key for a metaclass reference property or primitive value for an attribute property).

## [Eclipse Xcore] Class Diagram Notation

The notation for a metaclass is a rounded rectangle where the color indicates the following:

|Color  | Meaning |
|-------|---------|
|Gray   | Abstract entity metaclass |
|Yellow | Concrete entity metaclass |
|Pink   | Value metaclass |

Every abstract and concrete metaclass has a globally unique Version 5 UUID identifier.
A Value class has no intrinsic identity; it is a tuple for representing values for all the class properties.

The notation for an enumeration is a green rectangle.

The notation for generalization relationships is that of UML; that is, a line with a hollow trangle as an arrowhead
pointing to the symbol representing the general metaclass.

Where multiple generalization relationships refer to the same general metaclass
with a common arrowhead in a tree-style notation, the semantics is that the specialized metaclasses
are mutually disjoint with each other. In terms of the UML class diagram notation,
this is equivalent to hiding a GeneralizationSet with `{isDisjoint=true}`.

The UML association-like notation is used for two distinct cases:

- A composite binary association from a source metaclass (the ownership container) to a target metaclass (the composite containment).
  Unlike typical metamodels where the multiplicity of the source ownership container end property is `[0..1]`, it is `[1..1]`
  in OML because the source metaclass is the only possible container metaclass that can own instances of the target metaclass.
  In OML, the target composite containment end property always has multiplicity `[0..*]`.

- The UML association-like notation for a reference property owned by a metaclass and typed by another metaclass.
  In OML, such a property always has multiplicity `[1..1]`.

## Normalized Relational Database Schema

Although the OML Metamodel fully conforms to the well-formedness constraints of [OMG Meta-Object Facility], it is designed
to conform to additional well-formeness constraints designed to ensure the following strong guarantees:
