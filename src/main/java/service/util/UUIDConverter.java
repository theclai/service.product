/**
 * Tapp
 * Copyright (c) 20042021 All Rights Reserved.
 */
package service.util;

import java.sql.Types;
import java.util.UUID;

import org.eclipse.persistence.internal.helper.DatabaseField;
import org.eclipse.persistence.mappings.DatabaseMapping;
import org.eclipse.persistence.mappings.DirectCollectionMapping;
import org.eclipse.persistence.mappings.OneToOneMapping;
import org.eclipse.persistence.mappings.converters.Converter;
import org.eclipse.persistence.sessions.Session;

/**
 * @author faisalrahman
 * @version $Id: UUIDConverter.java, v 0.1 20210104 12.02 faisalrahman Exp $$
 */
public class UUIDConverter implements Converter {

    private static final long serialVersionUID = 1L;

    @Override
    public UUID convertObjectValueToDataValue(Object objectValue, Session session)
    {
        return (UUID) objectValue;
    }

    @Override
    public UUID convertDataValueToObjectValue(Object dataValue, Session session)
    {
        return (UUID) dataValue;
    }

    @Override
    public boolean isMutable()
    {
        return false;
    }

    @Override
    public void initialize(DatabaseMapping mapping, Session session)
    {
        DatabaseField field = mapping.getField();
        field.setSqlType(Types.OTHER);
        field.setTypeName("java.util.UUID");
        field.setColumnDefinition("UUID");

        for (DatabaseMapping m : mapping.getDescriptor().getMappings())
        {
            if (m instanceof OneToOneMapping)
            {
                for (DatabaseField relationshipField : ((OneToOneMapping) m).getForeignKeyFields())
                {
                    relationshipField.setSqlType(Types.OTHER);
                    relationshipField.setColumnDefinition("UUID");
                    relationshipField.setTypeName("java.util.UUID");
                }
            }
        }
    }
}