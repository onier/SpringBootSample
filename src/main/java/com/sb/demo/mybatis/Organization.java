/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sb.demo.mybatis;

/**
 *
 * @author MyPC
 */
public class Organization {

    private long id = 0;
    private String name;
    private String description;
    private int depth;
    private long parent_id;
    private String org_path;

    public Organization() {
    }

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the depth
     */
    public int getDepth() {
        return depth;
    }

    /**
     * @param depth the depth to set
     */
    public void setDepth(int depth) {
        this.depth = depth;
    }

    /**
     * @return the parent_id
     */
    public long getParent_id() {
        return parent_id;
    }

    /**
     * @param parent_id the parent_id to set
     */
    public void setParent_id(long parent_id) {
        this.parent_id = parent_id;
    }

    /**
     * @return the org_path
     */
    public String getOrg_path() {
        return org_path;
    }

    /**
     * @param org_path the org_path to set
     */
    public void setOrg_path(String org_path) {
        this.org_path = org_path;
    }

}
