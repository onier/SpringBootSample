/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sb.demo.mybatis;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

/**
 *
 * @author MyPC
 */
@MappedTypes(PW.class)
public class PWTypeHandler extends BaseTypeHandler<PW> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, PW parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.getPassword());
    }

    @Override
    public PW getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return new PW(rs.getString(columnName));
    }

    @Override
    public PW getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return new PW(rs.getString(columnIndex));
    }

    @Override
    public PW getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return new PW(cs.getString(columnIndex));
    }

}
