package org.eee.model.entity;


import lombok.Data;
import stark.coderaider.fluentschema.commons.NamingConvention;
import stark.coderaider.fluentschema.commons.annotations.Column;
import stark.coderaider.fluentschema.commons.annotations.Table;

@Data
@Table(namingConvention = NamingConvention.LOWER_CASE_WITH_UNDERSCORE)
public class Role extends DomainBase
{
    @Column(type = "VARCHAR(20)")
    private String roleName;

    @Column(type = "BIGINT")
    private Long  userId;

    @Column(type = "INT")
    private Integer roleCode;
}
