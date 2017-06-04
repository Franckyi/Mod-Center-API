/*
 * This file is generated by jOOQ.
*/
package com.franckyi.modcenter.api.jooq.tables;


import com.franckyi.modcenter.api.jooq.FranckyiModcenter;
import com.franckyi.modcenter.api.jooq.Keys;
import com.franckyi.modcenter.api.jooq.tables.records.ProjectsRecord;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
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
public class Projects extends TableImpl<ProjectsRecord> {

    private static final long serialVersionUID = 536380353;

    /**
     * The reference instance of <code>franckyi_modcenter.projects</code>
     */
    public static final Projects PROJECTS = new Projects();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<ProjectsRecord> getRecordType() {
        return ProjectsRecord.class;
    }

    /**
     * The column <code>franckyi_modcenter.projects.projectId</code>.
     */
    public final TableField<ProjectsRecord, Integer> PROJECTID = createField("projectId", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>franckyi_modcenter.projects.name</code>.
     */
    public final TableField<ProjectsRecord, String> NAME = createField("name", org.jooq.impl.SQLDataType.VARCHAR.length(255).nullable(false), this, "");

    /**
     * The column <code>franckyi_modcenter.projects.author</code>.
     */
    public final TableField<ProjectsRecord, String> AUTHOR = createField("author", org.jooq.impl.SQLDataType.VARCHAR.length(255).nullable(false), this, "");

    /**
     * The column <code>franckyi_modcenter.projects.totalDl</code>.
     */
    public final TableField<ProjectsRecord, Integer> TOTALDL = createField("totalDl", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>franckyi_modcenter.projects.updated</code>.
     */
    public final TableField<ProjectsRecord, Date> UPDATED = createField("updated", org.jooq.impl.SQLDataType.DATE.nullable(false), this, "");

    /**
     * The column <code>franckyi_modcenter.projects.description</code>.
     */
    public final TableField<ProjectsRecord, String> DESCRIPTION = createField("description", org.jooq.impl.SQLDataType.VARCHAR.length(256).nullable(false), this, "");

    /**
     * The column <code>franckyi_modcenter.projects.projectUrl</code>.
     */
    public final TableField<ProjectsRecord, String> PROJECTURL = createField("projectUrl", org.jooq.impl.SQLDataType.VARCHAR.length(255).nullable(false), this, "");

    /**
     * The column <code>franckyi_modcenter.projects.thumbnail</code>.
     */
    public final TableField<ProjectsRecord, String> THUMBNAIL = createField("thumbnail", org.jooq.impl.SQLDataType.VARCHAR.length(255).nullable(false), this, "");

    /**
     * Create a <code>franckyi_modcenter.projects</code> table reference
     */
    public Projects() {
        this("projects", null);
    }

    /**
     * Create an aliased <code>franckyi_modcenter.projects</code> table reference
     */
    public Projects(String alias) {
        this(alias, PROJECTS);
    }

    private Projects(String alias, Table<ProjectsRecord> aliased) {
        this(alias, aliased, null);
    }

    private Projects(String alias, Table<ProjectsRecord> aliased, Field<?>[] parameters) {
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
    public UniqueKey<ProjectsRecord> getPrimaryKey() {
        return Keys.KEY_PROJECTS_PRIMARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<ProjectsRecord>> getKeys() {
        return Arrays.<UniqueKey<ProjectsRecord>>asList(Keys.KEY_PROJECTS_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Projects as(String alias) {
        return new Projects(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Projects rename(String name) {
        return new Projects(name, null);
    }
}
