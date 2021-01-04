/**
 * Tapp
 * Copyright (c) 20042021 All Rights Reserved.
 */
package service.util;

import java.util.UUID;

import org.eclipse.persistence.internal.helper.DatabaseField;
import org.eclipse.persistence.mappings.DatabaseMapping;
import org.eclipse.persistence.mappings.DirectCollectionMapping;
import org.eclipse.persistence.mappings.converters.Converter;
import org.eclipse.persistence.sessions.Session;

/**
 * @author faisalrahman
 * @version $Id: UUIDConverter.java, v 0.1 20210104 12.02 faisalrahman Exp $$
 */
public class UUIDConverter implements Converter {

    @Override
    public Object convertObjectValueToDataValue(Object objectValue, Session session) {
        return objectValue;
    }

    @Override
    public UUID convertDataValueToObjectValue(Object dataValue, Session session) {
        return (UUID) dataValue;
    }

    @Override
    public boolean isMutable() {
        return true;
    }

    @Override
    public void initialize(DatabaseMapping mapping, Session session) {

        final DatabaseField field;
        if (mapping instanceof DirectCollectionMapping) {
            // handle @ElementCollection...
            field = ((DirectCollectionMapping) mapping).getDirectField();
        } else {
            field = mapping.getField();
        }

        field.setSqlType(java.sql.Types.OTHER);
        field.setTypeName("uuid");
        field.setColumnDefinition("UUID");
    }
}