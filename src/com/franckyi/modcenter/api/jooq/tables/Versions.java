/*
 * This file is generated by jOOQ.
*/
package com.franckyi.modcenter.api.jooq.tables;


import com.franckyi.modcenter.api.jooq.FranckyiModcenter;
import com.franckyi.modcenter.api.jooq.Keys;
import com.franckyi.modcenter.api.jooq.tables.records.VersionsRecord;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.9.3"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Versions extends TableImpl<VersionsRecord> {

    private static final long serialVersionUID = -1457966597;

    /**
     * The reference instance of <code>franckyi_modcenter.versions</code>
     */
    public static final Versions VERSIONS = new Versions();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<VersionsRecord> getRecordType() {
        return VersionsRecord.class;
    }

    /**
     * The column <code>franckyi_modcenter.versions.fileId</code>.
     */
    public final TableField<VersionsRecord, Integer> FILEID = createField("fileId", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>franckyi_modcenter.versions.version</code>.
     */
    public final TableField<VersionsRecord, String> VERSION = createField("version", org.jooq.impl.SQLDataType.VARCHAR.length(20).nullable(false), this, "");

    /**
     * Create a <code>franckyi_modcenter.versions</code> table reference
     */
    public Versions() {
        this("versions", null);
    }

    /**
     * Create an aliased <code>franckyi_modcenter.versions</code> table reference
     */
    public Versions(String alias) {
        this(alias, VERSIONS);
    }

    private Versions(String alias, Table<VersionsRecord> aliased) {
        this(alias, aliased, null);
    }

    private Versions(String alias, Table<VersionsRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Schema getSchema() {
        return FranckyiModcenter.FRANCKYI_MODCENTER;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<VersionsRecord> getPrimaryKey() {
        return Keys.KEY_VERSIONS_PRIMARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<VersionsRecord>> getKeys() {
        return Arrays.<UniqueKey<VersionsRecord>>asList(Keys.KEY_VERSIONS_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ForeignKey<VersionsRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<VersionsRecord, ?>>asList(Keys.VERSIONS_IBFK_1);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Versions as(String alias) {
        return new Versions(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Versions rename(String name) {
        return new Versions(name, null);
    }
}
