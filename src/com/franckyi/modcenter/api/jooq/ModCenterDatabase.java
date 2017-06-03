/*
 * This file is generated by jOOQ.
*/
package com.franckyi.modcenter.api.jooq;


import com.franckyi.modcenter.api.jooq.tables.Categories;
import com.franckyi.modcenter.api.jooq.tables.Files;
import com.franckyi.modcenter.api.jooq.tables.Optionallibraries;
import com.franckyi.modcenter.api.jooq.tables.Projects;
import com.franckyi.modcenter.api.jooq.tables.Requiredlibraries;
import com.franckyi.modcenter.api.jooq.tables.Versions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Catalog;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;


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
public class ModCenterDatabase extends SchemaImpl {

    private static final long serialVersionUID = -1642061795;

    /**
     * The reference instance of <code>modcenter</code>
     */
    public static final ModCenterDatabase MOD_CENTER_DATABASE = new ModCenterDatabase();

    /**
     * The table <code>modcenter.categories</code>.
     */
    public final Categories CATEGORIES = com.franckyi.modcenter.api.jooq.tables.Categories.CATEGORIES;

    /**
     * The table <code>modcenter.files</code>.
     */
    public final Files FILES = com.franckyi.modcenter.api.jooq.tables.Files.FILES;

    /**
     * The table <code>modcenter.optionalLibraries</code>.
     */
    public final Optionallibraries OPTIONALLIBRARIES = com.franckyi.modcenter.api.jooq.tables.Optionallibraries.OPTIONALLIBRARIES;

    /**
     * The table <code>modcenter.projects</code>.
     */
    public final Projects PROJECTS = com.franckyi.modcenter.api.jooq.tables.Projects.PROJECTS;

    /**
     * The table <code>modcenter.requiredLibraries</code>.
     */
    public final Requiredlibraries REQUIREDLIBRARIES = com.franckyi.modcenter.api.jooq.tables.Requiredlibraries.REQUIREDLIBRARIES;

    /**
     * The table <code>modcenter.versions</code>.
     */
    public final Versions VERSIONS = com.franckyi.modcenter.api.jooq.tables.Versions.VERSIONS;

    /**
     * No further instances allowed
     */
    private ModCenterDatabase() {
        super("modcenter", null);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Table<?>> getTables() {
        List result = new ArrayList();
        result.addAll(getTables0());
        return result;
    }

    private final List<Table<?>> getTables0() {
        return Arrays.<Table<?>>asList(
            Categories.CATEGORIES,
            Files.FILES,
            Optionallibraries.OPTIONALLIBRARIES,
            Projects.PROJECTS,
            Requiredlibraries.REQUIREDLIBRARIES,
            Versions.VERSIONS);
    }
}
