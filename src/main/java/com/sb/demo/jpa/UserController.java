/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sb.demo.jpa;

import com.sb.demo.mybatis.Organization;
import com.sb.demo.mybatis.OrganizationMapper;
import com.sb.demo.mybatis.User;
import com.sb.demo.mybatis.UserMapper;
import com.sb.demo.util.CommonUtils;
import com.sb.demo.util.CommonUtils.Status;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
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

//    @PreAuthorize("hasAnyAuthority('Bll')")
    @RequestMapping(value = {"/list", "/listAll"})
    public List<User> getUsers() {
        return userMapper.findAll();
    }

    @RequestMapping(value = {"/save"})
    public void save(User user) {
        userMapper.insert(user);
    }

    @RequestMapping(value = {"/listOrgs", "/getOrgs"})
    public List<Organization> getOrganizations() {
        return organizationMapper.findAll();
    }

    @RequestMapping(value = {"/saveOrg"})
    public Status save(Organization organization) {
        if (organization.getName() != null && !organization.getName().trim().isEmpty()) {
            organizationMapper.insert(organization);
            Organization parent = organizationMapper.findOrganizationBean(organization.getParent_id() + "");
            if (parent != null) {
                if (parent.getOrg_path() == null || parent.getOrg_path().trim().length() == 0) {
                    parent.setOrg_path(parent.getId() + "");
                    organizationMapper.update(parent);
                }
                organization.setDepth(parent.getDepth() + 1);
                organization.setOrg_path(parent.getOrg_path() + "/" + organization.getId());
            } else {
                organization.setDepth(0);
                organization.setOrg_path(organization.getId() + "");
            }
            organizationMapper.update(organization);
            return CommonUtils.createResponseStatus(1, "");
        }
        return CommonUtils.createResponseStatus(2, "组织名为空");
    }

    @RequestMapping(value = {"/deleteOrg/{orgID}"})
    public Status deleteOrg(@PathVariable String orgID) {
        List<Organization> orgs = organizationMapper.findOrganizationByParentID(orgID);
        if (orgs == null || orgs.isEmpty()) {
            organizationMapper.deleteOrganization(orgID);
            return CommonUtils.createResponseStatus(1, "");
        } else {
            return CommonUtils.createResponseStatus(2, "改组织包含子组织无法删除");
        }
    }

    @RequestMapping(value = {"/editor"})
    public Status editorOrganization(Organization organization) {
        Organization child = organization;
        Organization parent = organizationMapper.findOrganizationBean(organization.getParent_id() + "");
        if (parent != null && child != null) {
            child.setParent_id(parent.getId());
            child.setDepth(parent.getDepth() + 1);
            if (parent.getOrg_path() == null || parent.getOrg_path().trim().length() == 0) {
                return CommonUtils.createResponseStatus(2, "上级组织没有完整路径");
            }
            child.setOrg_path(parent.getOrg_path() + "/" + child.getId());
            organizationMapper.update(child);
            organizationMapper.update(parent);
        } else if (child != null) {
            organizationMapper.update(child);
        }
        return CommonUtils.createResponseStatus(1, "");
    }

}
