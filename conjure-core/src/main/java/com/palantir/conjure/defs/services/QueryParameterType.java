/*
 * (c) Copyright 2018 Palantir Technologies Inc. All rights reserved.
 */

package com.palantir.conjure.defs.services;

import com.palantir.conjure.defs.ConjureImmutablesStyle;
import org.immutables.value.Value;

@Value.Immutable
@ConjureImmutablesStyle
public interface QueryParameterType extends ParameterType {

    ParameterId paramId();

    static QueryParameterType query(String paramId) {
        return ImmutableQueryParameterType.builder()
                .paramId(ParameterId.of(paramId)).build();
    }
}
