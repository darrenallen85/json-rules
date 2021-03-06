/*
 * Copyright (c) 2016 Santanu Sinha <santanu.sinha@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package io.appform.jsonrules.expressions.array;

import java.util.Set;

import com.fasterxml.jackson.databind.JsonNode;

import io.appform.jsonrules.ExpressionType;
import io.appform.jsonrules.expressions.preoperation.PreOperation;
import io.appform.jsonrules.utils.ComparisonUtils;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Singular;
import lombok.ToString;

/**
 * Checks if an object is present in the defined collection
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class InExpression extends CollectionJsonPathBasedExpression {

    public InExpression() {
        super(ExpressionType.in);
    }

    @Builder
    public InExpression(String path, @Singular Set<Object> values, boolean extractValues, String valuesPath,
            boolean defaultResult, PreOperation<?> preoperation) {
        super(ExpressionType.in, path, values, extractValues, valuesPath, defaultResult, preoperation);
    }

    @Override
    protected boolean evaluate(JsonNode evaluatedNode, Set<Object> values) {
        return !ComparisonUtils.isNodeMissingOrNull(evaluatedNode)
                && values.stream().anyMatch(value -> ComparisonUtils.compare(evaluatedNode, value) == 0);
    }
}
