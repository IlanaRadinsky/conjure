/*
 * (c) Copyright 2017 Palantir Technologies Inc. All rights reserved.
 */

package com.palantir.conjure.gen.python.types;

import com.palantir.conjure.defs.types.BaseObjectTypeDefinition;
import com.palantir.conjure.defs.types.TypesDefinition;
import com.palantir.conjure.gen.python.PackageNameProcessor;
import com.palantir.conjure.gen.python.poet.PythonClass;

public interface PythonBeanGenerator {

    enum ExperimentalFeatures {}

    PythonClass generateObject(
            TypesDefinition types,
            PackageNameProcessor packageNameProvider,
            BaseObjectTypeDefinition typeDef);

}
