/*
 * Copyright (C) 2011 Everit Kft. (http://www.everit.org)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.everit.json.schema;

import org.everit.json.schema.internal.JSONPrinter;

import java.util.Objects;

/**
 * Boolean schema validator.
 */
public class BooleanSchema extends Schema {

    /**
     * Builder class for {@link BooleanSchema}.
     */
    public static class Builder extends Schema.Builder<BooleanSchema> {

        private Boolean defaultValue;

        @Override
        public BooleanSchema build() {
            return new BooleanSchema(this);
        }

        public Builder defaultValue(final Boolean defaultValue) {
            this.defaultValue = defaultValue;
            return this;
        }

    }

    public static final BooleanSchema INSTANCE = new BooleanSchema(builder());

    public static Builder builder() {
        return new Builder();
    }

    private final Boolean defaultValue;

    public BooleanSchema(final Builder builder) {
        super(builder);
        defaultValue = builder.defaultValue;
    }

    @Override
    public void validate(final Object subject) {
        if (!(subject instanceof Boolean)) {
            throw failure(Boolean.class, subject);
        }
    }

    @Override
    void describePropertiesTo(final JSONPrinter writer) {
        writer.key("type");
        writer.value("boolean");
        writer.ifPresent("default", defaultValue);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o instanceof BooleanSchema) {
            BooleanSchema that = (BooleanSchema) o;
            return that.canEqual(this) &&
                    Objects.equals(this.defaultValue, that.defaultValue) &&
                    super.equals(that);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), defaultValue);
    }

    @Override
    protected boolean canEqual(final Object other) {
        return other instanceof BooleanSchema;
    }
}
