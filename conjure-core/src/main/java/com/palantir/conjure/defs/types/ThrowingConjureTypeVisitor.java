/*
 * (c) Copyright 2017 Palantir Technologies Inc. All rights reserved.
 */

package com.palantir.conjure.defs.types;

import com.palantir.conjure.defs.types.builtin.AnyType;
import com.palantir.conjure.defs.types.builtin.BinaryType;
import com.palantir.conjure.defs.types.builtin.DateTimeType;
import com.palantir.conjure.defs.types.collect.ListType;
import com.palantir.conjure.defs.types.collect.MapType;
import com.palantir.conjure.defs.types.collect.OptionalType;
import com.palantir.conjure.defs.types.collect.SetType;
import com.palantir.conjure.defs.types.primitive.PrimitiveType;
import com.palantir.conjure.defs.types.reference.LocalReferenceType;

public interface ThrowingConjureTypeVisitor<T> extends ConjureTypeVisitor<T> {
    @Override
    default T visitAny(AnyType type) {
        throw createException(type);
    }

    @Override
    default T visitList(ListType type) {
        throw createException(type);
    }

    @Override
    default T visitMap(MapType type) {
        throw createException(type);
    }

    @Override
    default T visitOptional(OptionalType type) {
        throw createException(type);
    }

    @Override
    default T visitPrimitive(PrimitiveType type) {
        throw createException(type);
    }

    @Override
    default T visitLocalReference(LocalReferenceType type) {
        throw createException(type);
    }

    @Override
    default T visitSet(SetType type) {
        throw createException(type);
    }

    @Override
    default T visitBinary(BinaryType type) {
        throw createException(type);
    }

    @Override
    default T visitDateTime(DateTimeType type) {
        throw createException(type);
    }
    default UnsupportedOperationException createException(Object type) {
        return new UnsupportedOperationException(String.format(
                "visit() method not implemented for visitor %s and type %s",
                this.getClass().getSimpleName(), type.getClass().getSimpleName()));
    }
}
