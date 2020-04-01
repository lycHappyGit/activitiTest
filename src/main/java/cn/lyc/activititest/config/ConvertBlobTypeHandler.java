package cn.lyc.activititest.config;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.*;

public class ConvertBlobTypeHandler extends BaseTypeHandler<byte[]> {
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, byte[] parameter, JdbcType jdbcType)
            throws SQLException {
        //luoq 2018年5月10日17:17:38 云上 流程 有会签节点是操作异常。原因：注释的连接mycat有问题。
//        ByteArrayInputStream bis = new ByteArrayInputStream(parameter);
//        ps.setBinaryStream(i, bis, parameter.length);
        ps.setBytes(i, parameter);
    }

    public byte[] getNullableResult(ResultSet rs, String columnName) throws SQLException {
        Blob blob = rs.getBlob(columnName);
        return this.getBytes(blob);
    }

    public byte[] getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        Blob blob = rs.getBlob(columnIndex);
        return this.getBytes(blob);
    }

    public byte[] getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        Blob blob = cs.getBlob(columnIndex);
        return this.getBytes(blob);
    }

    private byte[] getBytes(Blob blob) throws SQLException {
        byte[] returnValue = null;
        if (blob != null) {
            returnValue = ByteArrayUtils.convertToObjectArray(blob.getBytes(1L, (int)blob.length()));
        }

        return returnValue;
    }
}

