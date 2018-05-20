/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sb.demo.jpa.test;

import com.sb.demo.mybatis.Organization;
import com.sb.demo.mybatis.OrganizationMapper;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author MyPC
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrgTest {

    @Autowired
    OrganizationMapper organizationMapper;

    @Test
    public void contexLoads() throws Exception {
        assertThat(organizationMapper).isNotNull();
    }
}
