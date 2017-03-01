# Composite project for JPL's Integrated Model-Centric Engineering (IMCE) Ontological Modeling Language (OML) Specification

This is a multi-project of mostly Xcore/Xtext/Xtend sources used to generate several components (abstract syntax metamodel; concrete syntax parser, doc, ...).
There are several Xtend-based code generators that produce Scala source code.
Unfortunately, it has been more difficult than originally anticipated to achieve the following objectives:

- Reproducible & consistent builds everywhere from a unix-like terminal and from the IDE (Eclipse or Intellij IDEA).
- No duplication of configuration information (e.g. dependencies specified only in one place).
- All Generated artifacts should be location-neutral & platform-neutral 
  and should re-generate without any difference if the inputs haven't changed.
- A machine-readable report of the provenance of each generated artifact 
  to a particular generation tool, inputs and configuration settings.
- Unified dependency management for both Eclipse P2-based repositories 
  and Maven artifact repositories for both jar artifacts and zip aggregates of jar artifacts.

Currently, this project reflects an attempt at using Gradle to achieve the above objectives; however, the result is far from achieving all the objectives.

Travis CI: [![Build Status](https://travis-ci.org/JPL-IMCE/jpl.imce.oml.specification.parent.svg?branch=master)](https://travis-ci.org/JPL-IMCE/jpl.imce.oml.specification.parent)

Bintray: [ ![Download](https://api.bintray.com/packages/jpl-imce/gov.nasa.jpl.imce.p2/jpl.imce.oml.specification.p2/images/download.svg) ](https://bintray.com/jpl-imce/gov.nasa.jpl.imce.p2/jpl.imce.oml.specification.p2/_latestVersion)

P2: See [jpl.imce.oml.specification.repository/README.md](jpl.imce.oml.specification.repository/README.md)

This composite project comprises several sub-projects:
- [An ecore metamodel for the OML specification](./jpl.imce.oml.specification.ecore)
- [A textual concrete syntax for OML models](./jpl.imce.oml.specification)
- Several projects generated from XCore IDE wizards

## Installation in Eclipse using a version of the [JPL IMCE OML Specification P2](https://bintray.com/jpl-imce/gov.nasa.jpl.imce.p2/jpl.imce.oml.specification.p2) repository

See [jpl.imce.oml.specification.repository/README.md](jpl.imce.oml.specification.repository/README.md)

## Developer Notes

This project is developed using two IDEs: Eclipse & Intellij IDEA Ultimate.
It would be nice if a single IDE could handle all the requirements.

The projects are primarily intended to be built using scripts for continuous
integration (i.e., gradle or sbt). This means that the project metadata
(e.g. dependencies, output directories, ...) is configured to work with
these build scripts. 

### Eclipse Neon.2 

Specifically, the Eclipse Neon.2 Modeling package which includes EMF, CDO, EGIT augmented with the following:
- XText Complete SDK (2.10.0)
- Modeling/EMF - Eclipse Modeling Framework Xcore SDK (1.4.0)
- Modeling/Sirus * (4.1.2)
- Buildship Gradle Integration 2.0
- Scala IDE (4.5.0)

In principle, it should be possible to use Eclipse Oomph to configure
a package with the above; in practice, it is unfortunately not obvious
how to do this despite several attempts. 

Recommendations:

- Turn off `Project | Build Automatically`. 

  Use a gradle and/or sbt commands in a terminal to perform build-related operations
  on the project or sub-projects.

- Execute [scripts/travis-build.sh] before importing this project in the Eclipse IDE

  This will generate code from several files (e.g., *.xcore, *.xtend)
  and generate the necessary Eclipse metadata files (e.g., .classpath, .project, ..).

- Set the Eclipse Plug-In Development active target to [tooling-e46.target]

  This is necessary to ensure that the Eclipse builders resolve dependencies 
  in the same way that the gradle scripts do. The explicit target definition
  facilitates managing versioned dependencies because the versions are specified
  only in one place -- i.e., the target definition in [tooling-e46.target].
  Eclipse & gradle dependencies in `plugin.xml`, `META-INF/MANIFEST.MF` or gradle build scripts
  refer to a dependency by name alone without duplicating the particular version.
  
  There are exceptions:
  
  - The Xtext dependency is specified both in [tooling-e46.target] and in [xtextVersion.txt].
  - Some dependencies that are not in a P2-based repository are copied (e.g., [java-uuid-generator-3.1.3.jar])
  - SBT projects use a different versioned dependency management technique based
  on grouping multiple versioned dependencies into a single mavenized zip versioned 
  dependency aggregate. This helps avoid duplicating versioned dependencies
  in several places.
  
Practical difficulties with Eclipse IDE:

- After a successful gradle build and refreshing , Eclipse IDE sometimes reports errors in *.xcore files like this:

  | Description	| Resource	| Path	| Location	| Type |
  |-------------|------------|-------|-----------|------|
  |The type Annotation is already defined in Annotation.java. | OMLCommon.xcore | /jpl.imce.oml.specification.ecore/model | line: 263 /jpl.imce.oml.specification.ecore/model/OMLCommon.xcore	| Xcore Problem |
  
  It seems that Eclipse somehow ignores the settings where the Xcore-generated
  source code should be (i.e. 'build/xcore/main' per the generated metadata in '.settings/org.eclipse.emf.ecore.xcore.Xcore.prefs') thereby considering definitions in that location
  to be duplicates with the definitions it implicitly assumes will be in the default location
  (i.e., 'src-gen'). The non-default 'build/xcore/main' location was chosen to facilitate
  cleaning up all generated files in gradle (i.e., delete the 'build' folder).

- The Eclipse Buildship 2.0 supports executing gradle tasks as long as everything works fine.
 
  It is surprisingly difficult to understand what's going on when an error happens.
  Developing/debugging gradle build scripts in Java or Groovy is surprisingly difficult in Eclipse.
  
  Reusing some of the Eclipse Buildship scripts functionality is surprisingly not supported.
  See: Bugzilla – Bug 469287 (CLOSED WONTFIX)](https://bugs.eclipse.org/bugs/show_bug.cgi?id=469287)
 
- Poor support in Eclipse IDE for developing/debugging SBT build scripts
  
- Poor support in the Eclipse-based Scala IDE tooling for SBT/Scala projects

  These projects are generated using Eclipse code generators from *.xcore & *.xtend
  but are built/developed with IntelliJ IDEA instead of Eclipse.
  
- Poor support in the Eclipse-based Scala IDE tooling for polyglot programming with Scala cross-compilation

  The sub-project [gov.nasa.jpl.imce.oml.specification.tables] is mostly Scala source code
  generated from *.xcore and *.xtend files; which means that this sub-project
  is part of this multi-project where the code generation happens from gradle build scripts.
  However, to work on this sub-project, it is highly recommended to use the Intellij IDEA
  which offers much better support than the Eclise-based Scala IDE does for cross-compilation.
  
- Location-independent development

  Eclipse metadata files tend to include absolute paths that depend on the development host.
  It is difficult to avoid this problem with the Eclipse IDE because there are a multitude of "build"
  mechanisms involved that involve a multitude of a settings in several places.
  
  This project follows a different approach: generate IDE-specific metadata from command-line build commands.
  Both Gradle and SBT have special build commands to generate Eclipse metadata files. 
  For Eclipse, it means that the project has to be built before importing it in the IDE.
  Intellij typically can import the project as-is without building it beforehand.
  
- Difficult to automate Eclipse builds without the IDE

  The Eclipse EMF-based builders depend on EMF registrations (e.g., Ecore, GenModel, Xcore, Xtend, ...)
  and on having an Eclipse "workspace". Outside of the Eclipse IDE, it is difficult to replicate
  the same build behavior in some kind of command line script.
  
### Intellij IDEA Ultimate

All the SBT build configuration was developed using IDEA.
The [gov.nasa.jpl.imce.oml.specification.tables] module is a polyglot functional API 
for the normalized relational table schema of the Ontological Modeling Language.
This API is written in Scala; however, it is cross-compiled for both the JVM and Node/JavaScript.
Correspondingly, this SBT project has two sub-projects, `tablesJVM` and `tablesJS` respectively.
Most of the Scala source code is shared; however, some Scala source code is specific to `tablesJVM`
and other to `tablesJS`. There is also some source code in Java and JavaScript.

### Benefits of SBT

It handles a lot of the continuous integration paradigms used across several projects
(see https://github.com/JPL-IMCE). In particular:

- Third-party dependencies are aggregated in a big zip archive.

  In principle, this could be avoided using a powerful Maven repository like Artifactory
  that can be configured for proxying selected artifacts from other repositories.
  In practice, it is still an advanced topic, one that seems to be restricted to a 
  non-public deployment of Artifactory. 
  
  This aggregation in SBT is a simple idea enables Continuous Integration on https://travis-ci.org/jpl-imce.

- Cross building

- Compile-time type checking of build scripts

### Benefits of Gradle

Thanks to the Gradle-based Eclipse Buildship, it is possible to:
- construct a local Mavenized repository from specific Eclipse feature target dependencies
- run all the Xtext/Xtend/Xcore-based code generation 
- build the Eclipse-based projects plugins, features & p2 repos 
- execute the unit tests

Gradle build scripts can be difficult to develop & understand because gradle relies
extensively on dependency injection and runtime configuration. 

### Caveats about Eclipse Xtext/Xtend/Xcore

This project depends on Xtext 2.11 for grammar-based formatting
(see: https://github.com/eclipse/xtext-extras/issues/30)

There is a different bug in the EMF generator used by the XcoreGenerator
that prevents using GenModel annotations that would generate Eclipse metadata files
like `plugin.xml` (see: https://www.eclipse.org/forums/index.php?t=msg&th=1084555&goto=1754620&#msg_1754620)

Some Xtend programs behavior surprisingly differently.
E.g. See [OMLLoadTest.xtend](gov.nasa.jpl.imce.oml.specification.scala.generators/src/gov/nasa/jpl/imce/oml/specification/scala/generators/OMLLoadTest.xtend)

In the Eclipse IDE, it is possible to have several *.xcore files that cross-reference each other.
However, outside the Eclipse IDE, even after calling `EcoreUtil.resolveAll(ResourceSet)` 
forward cross-references remain unresolved proxies.

For OML, the forward cross-references had to be commented out so that the *.xcore files
can be loaded properly for executing the code generators that depend on everything being fully resolved.

## Continuous Integration

For continuous integration & development, this project should be buildable from a shell (unix/cygwin) using gradle.
The first step involves installing an Eclipse target platform according to the features defined in [tooling-e46.target](tooling-e46.target).

	% cd .../jpl.imce.oml.specification.parent
	% gradle :installTargetPlatform
	
This can take a while....

The target is installed in ` ~/.tooling/eclipse/targetPlatforms/46`

The second step is to build the remaining projects:

	% gradle build


It is possible to operate on a particular sub-project, either by switching to that sub-project's directory
or by specifying a sub-project task. To get the gradle syntax for specifying sub-project specific tasks, use:

	% cd .../jpl.imce.oml.specification.parent
	% gradle projects


This should produce something like this:

	:projects
	------------------------------------------------------------
	Root project
	------------------------------------------------------------
	
    Root project 'jpl.imce.oml.specification.parent'
    +--- Project ':gov.nasa.jpl.imce.oml.specification.doc'
    +--- Project ':gov.nasa.jpl.imce.oml.specification.scala.generators'
    +--- Project ':jpl.imce.oml.specification'
    +--- Project ':jpl.imce.oml.specification.ecore'
    +--- Project ':jpl.imce.oml.specification.ecore.edit'
    +--- Project ':jpl.imce.oml.specification.ecore.editor'
    +--- Project ':jpl.imce.oml.specification.feature'
    +--- Project ':jpl.imce.oml.specification.ide'
    +--- Project ':jpl.imce.oml.specification.idea'
    +--- Project ':jpl.imce.oml.specification.repository'
    +--- Project ':jpl.imce.oml.specification.tests'
    +--- Project ':jpl.imce.oml.specification.ui'
    +--- Project ':jpl.imce.oml.specification.ui.tests'
    \--- Project ':jpl.imce.oml.specification.web'

	To see a list of the tasks of a project, run gradle <project-path>:tasks
	For example, try running gradle :jpl.imce.oml.specification:tasks
	
	BUILD SUCCESSFUL
	
	Total time: 0.674 secs

-

For example, to build the Xcore concrete syntax from the *.xtext & *.xtend sources, either:

	% cd .../jpl.imce.oml.specification.parent
	% gradle :jpl.imce.oml.specification:build

or:

	% cd .../jpl.imce.oml.specification.parent/jpl.imce.oml.specification
	% gradle build

Either way, gradle will use the common settings specified in the parent (this project);
in particular, the repositories for resolving dependencies.

## Versioning

### Summary

For changing the version, either:
- update the [gradle.properties](gradle.properties) file.
- override it on the command line with `-Pversion=...`

### Details

For Tycho projects, there is a useful utility to change all version strings:

```
mvn org.eclipse.tycho:tycho-versions-plugin:set-version -DnewVersion=1.2.0
```

This project includes enhancements to [Eclipse Buildship](https://github.com/eclipse/buildship) for automatically updating
the version information in manifests, feature.xml and pom.xml files according to the kind of buildship project.

### BuildDefinitionPlugin

This project includes an enhancement 
of [Eclipse Buildship](https://github.com/eclipse/buildship)'s 
[BuildDefinitionPlugin](https://github.com/eclipse/buildship/blob/master/buildSrc/src/main/groovy/eclipsebuild/BuildDefinitionPlugin.groovy)
to update the `version` setting in `pom.xml` files automatically from the project's version:

- [pom.xml](pom.xml)

### BundlePlugin

This project includes an enhancement 
of [Eclipse Buildship](https://github.com/eclipse/buildship)'s 
[BundlePlugin](https://github.com/eclipse/buildship/blob/master/buildSrc/src/main/groovy/eclipsebuild/BundlePlugin.groovy)
to update the `Bundle-Version` setting in manifests and pom.xml, if any, automatically from the project's version:

- [jpl.imce.oml.specification/META-INF/MANIFEST.MF](jpl.imce.oml.specification/META-INF/MANIFEST.MF) 
- [jpl.imce.oml.specification/pom.xml](jpl.imce.oml.specification/pom.xml)
- [jpl.imce.oml.specification.ecore/META-INF/MANIFEST.MF](jpl.imce.oml.specification.ecore/META-INF/MANIFEST.MF) 
- [jpl.imce.oml.specification.ecore.edit/META-INF/MANIFEST.MF](jpl.imce.oml.specification.ecore.edit/META-INF/MANIFEST.MF) 
- [jpl.imce.oml.specification.ecore.editor/META-INF/MANIFEST.MF](jpl.imce.oml.specification.ecore.editor/META-INF/MANIFEST.MF)
- [jpl.imce.oml.specification.ide/META-INF/MANIFEST.MF](jpl.imce.oml.specification.ide/META-INF/MANIFEST.MF)
- [jpl.imce.oml.specification.ide/pom.xml](jpl.imce.oml.specification.ide/pom.xml)
- [jpl.imce.oml.specification.tests/META-INF/MANIFEST.MF](jpl.imce.oml.specification.tests/META-INF/MANIFEST.MF)
- [jpl.imce.oml.specification.ui/META-INF/MANIFEST.MF](jpl.imce.oml.specification.ui/META-INF/MANIFEST.MF)
- [jpl.imce.oml.specification.ui/pom.xml](jpl.imce.oml.specification.ui/pom.xml)
- [jpl.imce.oml.specification.ui.tests/META-INF/MANIFEST.MF](jpl.imce.oml.specification.ui.tests/META-INF/MANIFEST.MF)
- [jpl.imce.oml.specification.ui.tests/pom.xml](jpl.imce.oml.specification.ui.tests/pom.xml)

### FeaturePlugin

This project includes an enhancement 
of [Eclipse Buildship](https://github.com/eclipse/buildship)'s 
[FeaturePlugin](https://github.com/eclipse/buildship/blob/master/buildSrc/src/main/groovy/eclipsebuild/FeaturePlugin.groovy)
to update the `version` setting in `feature.xml` files automatically from the project's version:

- [jpl.imce.oml.specification.feature/feature.xml](jpl.imce.oml.specification.feature/feature.xml)
- [jpl.imce.oml.specification.feature/pom.xml](jpl.imce.oml.specification.feature/pom.xml)
