// Licensed to the Apache Software Foundation (ASF) under one
// or more contributor license agreements.  See the NOTICE file
// distributed with this work for additional information
// regarding copyright ownership.  The ASF licenses this file
// to you under the Apache License, Version 2.0 (the
// "License"); you may not use this file except in compliance
// with the License.  You may obtain a copy of the License at
//
//   http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing,
// software distributed under the License is distributed on an
// "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
// KIND, either express or implied.  See the License for the
// specific language governing permissions and limitations
// under the License.

package org.apache.doris.nereids.trees.expressions.functions.scalar;

import org.apache.doris.catalog.FunctionSignature;
import org.apache.doris.nereids.trees.expressions.Expression;
import org.apache.doris.nereids.trees.expressions.functions.AlwaysNullable;
import org.apache.doris.nereids.trees.expressions.functions.ExplicitlyCastableSignature;
import org.apache.doris.nereids.trees.expressions.shape.BinaryExpression;
import org.apache.doris.nereids.trees.expressions.visitor.ExpressionVisitor;
import org.apache.doris.nereids.types.BigIntType;
import org.apache.doris.nereids.types.IntegerType;
import org.apache.doris.nereids.types.SmallIntType;
import org.apache.doris.nereids.types.TinyIntType;
import org.apache.doris.nereids.types.VarcharType;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;

import java.util.List;

/**
 * scalar function Ipv4NumToString
 */
public class Ipv4NumToString extends ScalarFunction
        implements BinaryExpression, ExplicitlyCastableSignature, AlwaysNullable {

    public static final List<FunctionSignature> SIGNATURES = ImmutableList.of(
            FunctionSignature.ret(VarcharType.SYSTEM_DEFAULT).args(TinyIntType.INSTANCE),
            FunctionSignature.ret(VarcharType.SYSTEM_DEFAULT).args(SmallIntType.INSTANCE),
            FunctionSignature.ret(VarcharType.SYSTEM_DEFAULT).args(IntegerType.INSTANCE),
            FunctionSignature.ret(VarcharType.SYSTEM_DEFAULT).args(BigIntType.INSTANCE));

    public Ipv4NumToString(Expression arg0) {
        super("ipv4numtostring", arg0);
    }

    @Override
    public Ipv4NumToString withChildren(List<Expression> children) {
        Preconditions.checkArgument(children.size() == 1,
                "ipv4numtostring accept 1 args, but got %s (%s)",
                children.size(),
                children);
        return new Ipv4NumToString(children.get(0));
    }

    @Override
    public List<FunctionSignature> getSignatures() {
        return SIGNATURES;
    }

    @Override
    public <R, C> R accept(ExpressionVisitor<R, C> visitor, C context) {
        return visitor.visitIpv4NumToString(this, context);
    }
}
