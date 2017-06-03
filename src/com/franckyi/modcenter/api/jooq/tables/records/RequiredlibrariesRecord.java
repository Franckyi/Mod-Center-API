/*
 * This file is generated by jOOQ.
*/
package com.franckyi.modcenter.api.jooq.tables.records;


import com.franckyi.modcenter.api.jooq.tables.Requiredlibraries;

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
public class RequiredlibrariesRecord extends UpdatableRecordImpl<RequiredlibrariesRecord> implements Record2<Integer, Integer> {

    private static final long serialVersionUID = 1416147453;

    /**
     * Setter for <code>modcenter.requiredLibraries.fileId</code>.
     */
    public void setFileid(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>modcenter.requiredLibraries.fileId</code>.
     */
    public Integer getFileid() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>modcenter.requiredLibraries.projectId</code>.
     */
    public void setProjectid(Integer value) {
        set(1, value);
    }

    /**
     * Getter for <code>modcenter.requiredLibraries.projectId</code>.
     */
    public Integer getProjectid() {
        return (Integer) get(1);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Record2<Integer, Integer> key() {
        return (Record2) super.key();
    }

    // -------------------------------------------------------------------------
    // Record2 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row2<Integer, Integer> fieldsRow() {
        return (Row2) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row2<Integer, Integer> valuesRow() {
        return (Row2) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field1() {
        return Requiredlibraries.REQUIREDLIBRARIES.FILEID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field2() {
        return Requiredlibraries.REQUIREDLIBRARIES.PROJECTID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value1() {
        return getFileid();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value2() {
        return getProjectid();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RequiredlibrariesRecord value1(Integer value) {
        setFileid(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RequiredlibrariesRecord value2(Integer value) {
        setProjectid(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RequiredlibrariesRecord values(Integer value1, Integer value2) {
        value1(value1);
        value2(value2);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached RequiredlibrariesRecord
     */
    public RequiredlibrariesRecord() {
        super(Requiredlibraries.REQUIREDLIBRARIES);
    }

    /**
     * Create a detached, initialised RequiredlibrariesRecord
     */
    public RequiredlibrariesRecord(Integer fileid, Integer projectid) {
        super(Requiredlibraries.REQUIREDLIBRARIES);

        set(0, fileid);
        set(1, projectid);
    }
}
