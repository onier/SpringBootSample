/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sb.demo.jpa;

import com.sb.demo.mybatis.Organization;
import com.sb.demo.mybatis.OrganizationMapper;
import com.sb.demo.mybatis.UserMapper;
import com.sb.demo.util.CommonUtils;
import com.sb.demo.util.CommonUtils.Status;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author MyPC
 */
@RestController
@RequestMapping(value = {"/user"}, method = {RequestMethod.GET, RequestMethod.POST})
@CrossOrigin
public class UserController {

//    @Autowired
//    private UserService userService;
    @Autowired
    private OrganizationMapper organizationMapper;
    @Autowired
    private UserMapper userMapper;

    @RequestMapping(value = {"/list", "/listAll"})
    public List<User> getUsers() {
        return userMapper.findAll();
    }

    @RequestMapping(value = {"/save"})
    public void save(User user) {
        userMapper.insert(user);
    }

    @RequestMapping(value = {"/listOrgs", "getOrgs"})
    public List<Organization> getOrganizations() {
        return organizationMapper.findAll();
    }

    @RequestMapping(value = {"/saveOrg"})
    public void save(Organization organization) {
        organizationMapper.insert(organization);
    }

    @RequestMapping(value = {"/deleteOrg/{orgID}"})
    public Status deleteOrg(@PathVariable String orgID) {
        List<Organization> orgs = organizationMapper.findOrganizationByParentID(orgID);
        if (orgs == null || orgs.isEmpty()) {
            return CommonUtils.createResponseStatus(2, "改组织包含子组织无法删除");
        }
        organizationMapper.deleteOrganization(orgID);
        return CommonUtils.createResponseStatus(1, "");
    }

    @RequestMapping(value = {"/editor/{parentID}/{childID}"})
    public Status editorOrganization(String parentID, String childID) {
        Organization parent = organizationMapper.findOrganizationBean(parentID);
        Organization child = organizationMapper.findOrganizationBean(childID);
        if (parent != null && child != null) {
            child.setParent_id(parent.getId());
            child.setDepth(child.getDepth() + 1);
            child.setOrg_path(parent.getOrg_path() + "/" + child.getId());
            organizationMapper.update(child);
        }
        return CommonUtils.createResponseStatus(1, "");
    }
}
