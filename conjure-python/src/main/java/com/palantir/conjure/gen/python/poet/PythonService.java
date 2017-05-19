/*
 * Copyright 2017 Palantir Technologies, Inc. All rights reserved.
 */

package com.palantir.conjure.gen.python.poet;

import com.google.common.collect.ImmutableSet;
import com.palantir.conjure.defs.ConjureImmutablesStyle;
import com.palantir.conjure.defs.types.names.ConjurePackage;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.immutables.value.Value;

@Value.Immutable
@ConjureImmutablesStyle
public interface PythonService extends PythonClass {

    ImmutableSet<PythonImport> DEFAULT_IMPORTS = ImmutableSet.of(
            PythonImport.of(PythonClassName.of(ConjurePackage.of("typing"), "List"), Optional.empty()),
            PythonImport.of(PythonClassName.of(ConjurePackage.of("typing"), "Set"), Optional.empty()),
            PythonImport.of(PythonClassName.of(ConjurePackage.of("typing"), "Dict"), Optional.empty()),
            PythonImport.of(PythonClassName.of(ConjurePackage.of("typing"), "Tuple"), Optional.empty()),
            PythonImport.of(PythonClassName.of(ConjurePackage.of("typing"), "Optional"), Optional.empty()),
            PythonImport.of(PythonClassName.of(ConjurePackage.of("httpremoting"), "Service"), Optional.empty()),
            PythonImport.of(PythonClassName.of(ConjurePackage.of("conjure"), "*"), Optional.empty()));

    @Override
    String packageName();

    @Override
    @Value.Default
    default Set<PythonImport> requiredImports() {
        return DEFAULT_IMPORTS;
    }

    String className();

    Optional<String> docs();

    List<PythonEndpointDefinition> endpointDefinitions();

    @Override
    default void emit(PythonPoetWriter poetWriter) {
        poetWriter.maintainingIndent(() -> {
            poetWriter.writeIndentedLine(String.format("class %s(Service):", className()));
            poetWriter.increaseIndent();
            docs().ifPresent(docs -> poetWriter.writeIndentedLine(String.format("'''%s'''", docs.trim())));

            endpointDefinitions().forEach(endpointDefinition -> {
                poetWriter.writeLine();
                endpointDefinition.emit(poetWriter);
            });
            poetWriter.writeLine();

            poetWriter.decreaseIndent();
        });
    }

    class Builder extends ImmutablePythonService.Builder {}

    static Builder builder() {
        return new Builder();
    }

}
