/*
 * This file is generated by jOOQ.
*/
package com.franckyi.modcenter.api.jooq.tables.records;


import com.franckyi.modcenter.api.jooq.tables.Categories;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record2;
import org.jooq.Row2;
import org.jooq.impl.UpdatableRecordImpl;


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
public class CategoriesRecord extends UpdatableRecordImpl<CategoriesRecord> implements Record2<Integer, String> {

    private static final long serialVersionUID = 1565953708;

    /**
     * Setter for <code>franckyi_modcenter.categories.projectId</code>.
     */
    public void setProjectid(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>franckyi_modcenter.categories.projectId</code>.
     */
    public Integer getProjectid() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>franckyi_modcenter.categories.category</code>.
     */
    public void setCategory(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>franckyi_modcenter.categories.category</code>.
     */
    public String getCategory() {
        return (String) get(1);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Record2<Integer, String> key() {
        return (Record2) super.key();
    }

    // -------------------------------------------------------------------------
    // Record2 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row2<Integer, String> fieldsRow() {
        return (Row2) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row2<Integer, String> valuesRow() {
        return (Row2) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field1() {
        return Categories.CATEGORIES.PROJECTID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field2() {
        return Categories.CATEGORIES.CATEGORY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value1() {
        return getProjectid();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value2() {
        return getCategory();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CategoriesRecord value1(Integer value) {
        setProjectid(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CategoriesRecord value2(String value) {
        setCategory(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CategoriesRecord values(Integer value1, String value2) {
        value1(value1);
        value2(value2);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached CategoriesRecord
     */
    public CategoriesRecord() {
        super(Categories.CATEGORIES);
    }

    /**
     * Create a detached, initialised CategoriesRecord
     */
    public CategoriesRecord(Integer projectid, String category) {
        super(Categories.CATEGORIES);

        set(0, projectid);
        set(1, category);
    }
}
