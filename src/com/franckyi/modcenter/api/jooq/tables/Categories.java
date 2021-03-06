/*
 * This file is generated by jOOQ.
*/
package com.franckyi.modcenter.api.jooq.tables;


import com.franckyi.modcenter.api.jooq.FranckyiModcenter;
import com.franckyi.modcenter.api.jooq.Keys;
import com.franckyi.modcenter.api.jooq.tables.records.CategoriesRecord;

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
public class Categories extends TableImpl<CategoriesRecord> {

    private static final long serialVersionUID = 1968003198;

    /**
     * The reference instance of <code>franckyi_modcenter.categories</code>
     */
    public static final Categories CATEGORIES = new Categories();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<CategoriesRecord> getRecordType() {
        return CategoriesRecord.class;
    }

    /**
     * The column <code>franckyi_modcenter.categories.projectId</code>.
     */
    public final TableField<CategoriesRecord, Integer> PROJECTID = createField("projectId", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>franckyi_modcenter.categories.category</code>.
     */
    public final TableField<CategoriesRecord, String> CATEGORY = createField("category", org.jooq.impl.SQLDataType.VARCHAR.length(100).nullable(false), this, "");

    /**
     * Create a <code>franckyi_modcenter.categories</code> table reference
     */
    public Categories() {
        this("categories", null);
    }

    /**
     * Create an aliased <code>franckyi_modcenter.categories</code> table reference
     */
    public Categories(String alias) {
        this(alias, CATEGORIES);
    }

    private Categories(String alias, Table<CategoriesRecord> aliased) {
        this(alias, aliased, null);
    }

    private Categories(String alias, Table<CategoriesRecord> aliased, Field<?>[] parameters) {
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
    public UniqueKey<CategoriesRecord> getPrimaryKey() {
        return Keys.KEY_CATEGORIES_PRIMARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<CategoriesRecord>> getKeys() {
        return Arrays.<UniqueKey<CategoriesRecord>>asList(Keys.KEY_CATEGORIES_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ForeignKey<CategoriesRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<CategoriesRecord, ?>>asList(Keys.CATEGORIES_IBFK_1);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Categories as(String alias) {
        return new Categories(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Categories rename(String name) {
        return new Categories(name, null);
    }
}
